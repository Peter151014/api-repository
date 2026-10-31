package org.example.restapi.exception;

import org.example.restapi.dto.RequestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<RequestResponse> handleUserAlreadyExists(
            UserAlreadyExists ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new RequestResponse(ex.getMessage(), 409));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<RequestResponse> handleUserNotFound(
            UserNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new RequestResponse(ex.getMessage(), 404));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RequestResponse> handleException(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RequestResponse(
                        "Internal Server Error", 500));
    }
}