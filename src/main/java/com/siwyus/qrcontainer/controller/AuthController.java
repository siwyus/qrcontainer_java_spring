package com.siwyus.qrcontainer.controller;

import com.siwyus.qrcontainer.dto.AuthRequest;
import com.siwyus.qrcontainer.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthRequest authRequest) {
        try {
            userService.registerUser(authRequest.getEmail(), authRequest.getPassword());
            return ResponseEntity.ok("User registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthRequest authRequest) {
        try {
            userService.loginUser(authRequest.getEmail(), authRequest.getPassword());
            return ResponseEntity.ok("Login successful.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
