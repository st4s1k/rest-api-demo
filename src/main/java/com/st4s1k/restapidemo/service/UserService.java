package com.st4s1k.restapidemo.service;

import com.st4s1k.restapidemo.entity.User;
import com.st4s1k.restapidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<User> createUser(String name) {
        User newUser = userRepository.save(new User(name));
        return Optional.of(newUser);
    }

    public Optional<User> deleteUser(User user) {
        userRepository.delete(user);
        return userRepository.findById(user.getId());
    }

    public Optional<User> deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
        return userRepository.findById(userId);
    }
}
