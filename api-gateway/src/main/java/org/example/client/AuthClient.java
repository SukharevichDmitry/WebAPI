package org.example.client;

import org.example.dto.AuthResponseDTO;
import org.example.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "authservice", url = "${feign.client.auth.url}")
public interface AuthClient {

    @PostMapping("/auth/register")
    ResponseEntity<Void> register(@RequestBody UserDTO userDTO);

    @PostMapping("/auth/login")
    ResponseEntity<AuthResponseDTO> login(@RequestBody UserDTO userDTO);

}
