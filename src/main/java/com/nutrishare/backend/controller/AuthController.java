package com.nutrishare.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutrishare.backend.dto.LoginRequest;
import com.nutrishare.backend.dto.LoginResponse;
import com.nutrishare.backend.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        try {
            String token = authService.login(req.getEmail(), req.getPassword());
            LoginResponse response = new LoginResponse(token);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
