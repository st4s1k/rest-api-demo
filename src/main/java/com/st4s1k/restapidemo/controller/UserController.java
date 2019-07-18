package com.st4s1k.restapidemo.controller;

import com.st4s1k.restapidemo.entity.User;
import com.st4s1k.restapidemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getAllUsers() {
        List<User> userList = userService.getAllUsers();
        userList.sort(Comparator.comparing(User::getName));
        return userList.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping(params = {"name"})
    public ResponseEntity getAllUsersByName(@RequestParam String name) {
        List<User> userList = userService.getUsersByName(name);
        return userList.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                :new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping(params = {"id"})
    public ResponseEntity getUserById(@RequestParam String id) {
        ResponseEntity response;
        try{
            UUID userId = UUID.fromString(id);
            response = userService.getUserById(userId)
                    .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException exception){
            response = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @PostMapping(params = {"name"})
    public ResponseEntity createUser(@RequestParam String name) {
        Optional<User> newUser = userService.createUser(name);
        return newUser.isPresent()
                ? new ResponseEntity(HttpStatus.CREATED)
                : new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity deleteUser(@RequestParam String id) {
        ResponseEntity response;
        try{
            UUID userId = UUID.fromString(id);
            Optional<User> deletedUser = userService.deleteUserById(userId);
            response =  deletedUser.isPresent()
                    ? new ResponseEntity(HttpStatus.EXPECTATION_FAILED)
                    : new ResponseEntity(HttpStatus.OK);
        } catch (IllegalArgumentException exception){
            response = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
