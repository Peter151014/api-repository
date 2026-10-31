package org.example.restapi.Service;

import org.example.restapi.Entity.Messages;
import org.example.restapi.Entity.User;
import org.example.restapi.Repository.UserRepository;
import org.example.restapi.dto.PostMessage;
import org.example.restapi.dto.RequestResponse;
import org.example.restapi.dto.UserRequest;
import org.example.restapi.exception.UserAlreadyExists;
import org.example.restapi.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<Messages> getMessages(String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        return user.getMessages();
    }

    public ResponseEntity<RequestResponse> register(UserRequest userRequest) {

        User user = userRepository.findByEmail(userRequest.getEmail());

        if (user != null) {
            throw new UserAlreadyExists("User already exists");
        }

        user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        userRepository.save(user);

        return ResponseEntity.status(201)
                .body(new RequestResponse("Successfully created user account", 201));
    }

    @Transactional
    public ResponseEntity<RequestResponse> addMessage(PostMessage postMessage) {

        User user = userRepository.findByEmail(postMessage.getEmail());

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        Messages addMessage = new Messages();
        addMessage.setMessage(postMessage.getMessage());

        addMessage.setUser(user);
        user.getMessages().add(addMessage);

        userRepository.save(user);

        return ResponseEntity.ok(
                new RequestResponse("Successfully added message", 200)
        );
    }

    public ResponseEntity<RequestResponse> updateUsername(String username, String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        user.setUsername(username);
        userRepository.save(user);

        return ResponseEntity.ok(
                new RequestResponse("Successfully updated username", 200)
        );
    }

    public ResponseEntity<RequestResponse> updatePassword(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        user.setPassword(password);
        userRepository.save(user);

        return ResponseEntity.ok(
                new RequestResponse("Successfully updated password", 200)
        );
    }

    // Delete User
    public ResponseEntity<RequestResponse> deleteUser(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        userRepository.delete(user);

        return ResponseEntity.ok(
                new RequestResponse("Successfully deleted user", 200)
        );
    }
}