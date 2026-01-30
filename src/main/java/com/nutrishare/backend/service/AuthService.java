package com.nutrishare.backend.service;

import com.nutrishare.backend.dto.RegisterRequest;
import com.nutrishare.backend.model.User;

public interface AuthService {
    User login(String email, String password);

    User register(RegisterRequest request);

    String generateToken(User user);
}
