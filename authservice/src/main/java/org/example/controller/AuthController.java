package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.dto.AuthResponseDTO;
import org.example.dto.UserDTO;
import org.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Залогиниться", description = "Авторизация по логину и паролю")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserDTO userDTO) {
        return authService.login(userDTO);
    }

    @PostMapping("/register")
    @Operation(summary = "Зарегистрироваться", description = "Регистрация по имени и паролю")
    public ResponseEntity<Void> register(@RequestBody UserDTO userDTO) {
        return authService.register(userDTO);
    }

}
