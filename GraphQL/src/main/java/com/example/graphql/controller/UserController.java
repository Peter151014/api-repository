
package com.example.graphql.controller;

import com.example.graphql.Entity.User;
import com.example.graphql.dto.RequestResponse;
import com.example.graphql.dto.UpdateRequest;
import com.example.graphql.dto.UserRequest;
import com.example.graphql.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @MutationMapping
    public RequestResponse createdUser(
            @Argument UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @MutationMapping
    public RequestResponse updateUser(
            @Argument UpdateRequest updateRequest) {
        return userService.updateUser(updateRequest);
    }

    @MutationMapping
    public RequestResponse deleteUser(
            @Argument Long id) {
        return userService.deleteUser(id);
    }

    @QueryMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @QueryMapping
    public User getUserById(@Argument Long id) {
        return userService.getUserById(id);
    }
}
