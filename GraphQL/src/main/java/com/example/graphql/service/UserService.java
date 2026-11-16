
package com.example.graphql.service;

import com.example.graphql.Entity.User;
import com.example.graphql.dto.UpdateRequest;
import com.example.graphql.dto.UserRequest;
import com.example.graphql.dto.RequestResponse;
import com.example.graphql.exception.UserAlreadyExists;
import com.example.graphql.exception.UserNotFound;
import com.example.graphql.repository.UserRepo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public RequestResponse createUser(UserRequest request) {

        if (userRepo.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExists("User Already Exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        userRepo.save(user);

        return new RequestResponse("Successfully User Created");
    }

    public RequestResponse updateUser(UpdateRequest request) {

        User user = userRepo.findById(request.getId())
                .orElseThrow(() ->
                        new UserNotFound("User Not Found"));

        if (request.getName() != null) {
            user.setName(request.getName());
        }


        userRepo.save(user);

        return new RequestResponse("Successfully Updated User");
    }

    public RequestResponse deleteUser(Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() ->
                        new UserNotFound("User Not Found"));

        userRepo.delete(user);

        return new RequestResponse("Successfully User Deleted");
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {

        return userRepo.findById(id)
                .orElseThrow(() ->
                        new UserNotFound("User Not Found"));
    }
}
