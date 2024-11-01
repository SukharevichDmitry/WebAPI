package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponseDTO {
    private String token;

    public AuthResponseDTO(String token) {
        this.token = token;
    }

}
