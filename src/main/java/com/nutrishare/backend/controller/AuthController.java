package com.nutrishare.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutrishare.backend.dto.ApiResponse;
import com.nutrishare.backend.dto.AuthResponse;
import com.nutrishare.backend.dto.LoginRequest;
import com.nutrishare.backend.dto.RegisterRequest;
import com.nutrishare.backend.dto.UserResponse;
import com.nutrishare.backend.exception.UserAlreadyExistsException;
import com.nutrishare.backend.model.User;
import com.nutrishare.backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest req) {
        try {
            User user = authService.login(req.getEmail(), req.getPassword());
            String token = authService.generateToken(user);
            AuthResponse response = new AuthResponse(token, UserResponse.fromUser(user));
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(ApiResponse.error("Credenciales incorrectas"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Error interno del servidor"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody RegisterRequest request) {
        try {
            User user = authService.register(request);
            String token = authService.generateToken(user);
            AuthResponse response = new AuthResponse(token, UserResponse.fromUser(user));
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(409).body(ApiResponse.error(e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Error interno del servidor"));
        }
    }
}
