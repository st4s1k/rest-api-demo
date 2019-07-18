package com.st4s1k.restapidemo;

import com.google.gson.Gson;
import com.st4s1k.restapidemo.controller.UserController;
import com.st4s1k.restapidemo.entity.User;
import com.st4s1k.restapidemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
@SpringBootTest
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getAllUsers() throws Exception {
        List<User> mockUsersList = generateMockUsersList();

        Mockito.when(
                userService.getAllUsers()
        ).thenReturn(mockUsersList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        mockUsersList.sort(Comparator.comparing(User::getName));

        String expected = new Gson().toJson(mockUsersList);

        JSONAssert.assertEquals(
                expected,
                result.getResponse().getContentAsString(),
                false);
    }

    private List<User> generateMockUsersList() {
        List<User> mockUsersList = new ArrayList<>();

        mockUsersList.add(new User(UUID.randomUUID(), "Alex"));
        mockUsersList.add(new User(UUID.randomUUID(), "Bob"));
        mockUsersList.add(new User(UUID.randomUUID(), "Ion"));
        mockUsersList.add(new User(UUID.randomUUID(), "Maggie"));
        mockUsersList.add(new User(UUID.randomUUID(), "Rosetta"));

        return mockUsersList;
    }

//    @Test
//    public void createUser() {
//        ResponseEntity response = mockMvc.createUser("Patrick");
//        HttpStatus status = response.getStatusCode();
//        Assert.assertEquals(status, HttpStatus.CREATED);
//    }
}
