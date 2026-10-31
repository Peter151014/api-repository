package org.example.restapi.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostMessage {
    private String email;
    private String message;
}
