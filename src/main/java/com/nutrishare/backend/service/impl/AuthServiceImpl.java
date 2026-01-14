package com.nutrishare.backend.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nutrishare.backend.model.User;
import com.nutrishare.backend.repository.UserRepository;
import com.nutrishare.backend.security.JwtService;
import com.nutrishare.backend.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public String login(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            logger.error("Login fallido - usuario no encontrado: {}", email);
            throw new BadCredentialsException("Credenciales inválidas");
        }

        if (!encoder.matches(password, user.getPasswordHash())) {
            logger.error("Login fallido - password incorrecto para email: {}", email);
            throw new BadCredentialsException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(user.getId());

        return token;
    }
}
