package org.example.restapi.Controller;

import org.example.restapi.Service.UserService;
import org.example.restapi.dto.PostMessage;
import org.example.restapi.dto.RequestResponse;
import org.example.restapi.dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/GetMessages")
    public ResponseEntity<?> getMessages(String email){
        return (ResponseEntity<?>) userService.getMessages(email);
    }

    @PostMapping("/register")
    public ResponseEntity<RequestResponse> registerUser(@RequestBody UserRequest userRequest) {
        return userService.register(userRequest);
    }

    @PostMapping("/addMessage")
    public ResponseEntity<RequestResponse> addMessage(@RequestBody PostMessage  postMessage) {
        return userService.addMessage(postMessage);
    }

    @PutMapping("/updateUsername")
    public ResponseEntity<RequestResponse> updateUsername(@RequestBody String username,@RequestBody String  email) {
        return userService.updateUsername(username,email);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<RequestResponse> updatePassword(@RequestBody String email,@RequestBody String  password) {
        return userService.updatePassword(email,password);
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<RequestResponse> deleteAccount(@RequestBody String  email,@RequestBody String  password) {
        return userService.deleteUser(email,password);
    }


}
