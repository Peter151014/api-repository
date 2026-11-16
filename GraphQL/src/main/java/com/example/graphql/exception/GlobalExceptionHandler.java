package com.example.graphql.exception;

import com.example.graphql.dto.RequestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<RequestResponse> handleUserRequest(UserAlreadyExists ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RequestResponse(ex.getMessage(),409));
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<RequestResponse> handleUserNotFoudn(UserNotFound ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RequestResponse(ex.getMessage()));
    }
}
