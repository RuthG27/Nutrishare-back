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
import com.nutrishare.backend.dto.RegisterRequest;
import com.nutrishare.backend.exception.UserAlreadyExistsException;

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
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            logger.error("Login fallido - usuario no encontrado: {}", email);
            throw new BadCredentialsException("Credenciales inválidas");
        }

        if (!encoder.matches(password, user.getPasswordHash())) {
            logger.error("Login fallido - password incorrecto para email: {}", email);
            throw new BadCredentialsException("Credenciales inválidas");
        }

        return user;
    }

    @Override
    public User register(RegisterRequest request) {
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new RuntimeException("El nombre de usuario es obligatorio");
        }

        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new RuntimeException("El email es obligatorio");
        }

        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new RuntimeException("La contraseña es obligatoria");
        }

        User existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("El email ya está registrado");
        }

        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setPasswordHash(encoder.encode(request.getPassword()));

        User savedUser = userRepository.save(newUser);
        logger.info("Usuario registrado exitosamente: {}", request.getEmail());
        return savedUser;
    }

    @Override
    public String generateToken(User user) {
        return jwtService.generateToken(user.getId());
    }
}
