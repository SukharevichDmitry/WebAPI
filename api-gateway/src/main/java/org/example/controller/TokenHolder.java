package org.example.controller;

import org.springframework.stereotype.Service;

@Service
public class TokenHolder {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isTokenValid() {
        return token != null && token.split("\\.").length == 3; // Проверка на валидность токена
    }
}
