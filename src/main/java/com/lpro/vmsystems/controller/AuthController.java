package com.lpro.vmsystems.controller;

import com.lpro.vmsystems.business.AuthService;
import com.lpro.vmsystems.dto.loginRequestDTO.LoginRequestDTO;
import com.lpro.vmsystems.dto.loginResponseDTO.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
}

