package org.example.restapi.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class UserRequest {

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
