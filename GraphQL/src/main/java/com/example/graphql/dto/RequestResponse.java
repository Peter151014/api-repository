package com.example.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestResponse {

    private String message;

    public RequestResponse(String message, int i) {
    }

    public RequestResponse(String message){

    }
}
