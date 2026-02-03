package com.nutrishare.backend.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nutrishare.backend.dto.UserUpdateRequest;
import com.nutrishare.backend.model.User;
import com.nutrishare.backend.repository.UserRepository;
import com.nutrishare.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User getUserById(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            logger.error("Usuario no encontrado con id: " + id);
            throw new RuntimeException("Usuario no encontrado");
        }
        return user;
    }

    @Override
    public User updateUser(String id, UserUpdateRequest userUpdate) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            logger.error("Usuario no encontrado con id: " + id);
            throw new RuntimeException("Usuario no encontrado");
        }

        if (userUpdate.getName() != null)
            user.setName(userUpdate.getName());
        if (userUpdate.getEmail() != null)
            user.setEmail(userUpdate.getEmail());
        if (userUpdate.getPassword() != null)
            user.setPasswordHash(encoder.encode(userUpdate.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            logger.error("Usuario no encontrado con id: " + id);
            throw new RuntimeException("Usuario no encontrado");
        }

        userRepository.deleteById(id);
        logger.info("Usuario eliminado exitosamente con id: " + id);
    }
}
