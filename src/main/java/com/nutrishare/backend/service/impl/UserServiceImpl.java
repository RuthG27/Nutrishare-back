package com.nutrishare.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nutrishare.backend.dto.UserUpdateRequest;
import com.nutrishare.backend.model.Receta;
import com.nutrishare.backend.model.User;
import com.nutrishare.backend.repository.RecetaRepository;
import com.nutrishare.backend.repository.UserRepository;
import com.nutrishare.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final RecetaRepository recetaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UserServiceImpl(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

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

        if (userUpdate.getName() != null) {
            user.setName(userUpdate.getName());
        }
        if (userUpdate.getEmail() != null) {
            user.setEmail(userUpdate.getEmail());
        }
        if (userUpdate.getPassword() != null) {
            user.setPasswordHash(encoder.encode(userUpdate.getPassword()));
        }

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

    @Override
    public User addFavorita(String userId, String recetaId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            logger.error("Usuario no encontrado con id: " + userId);
            throw new RuntimeException("Usuario no encontrado");
        }

        ObjectId recetaObjectId;
        try {
            recetaObjectId = new ObjectId(recetaId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("ID de receta no válido");
        }

        Receta receta = recetaRepository.findById(recetaObjectId).orElse(null);
        if (receta == null) {
            throw new RuntimeException("Receta no encontrada");
        }

        if (user.getFavoritasIds() == null) {
            user.setFavoritasIds(new ArrayList<>());
        }

        if (!user.getFavoritasIds().contains(recetaId)) {
            user.getFavoritasIds().add(recetaId);
        }

        return userRepository.save(user);
    }

    @Override
    public User removeFavorita(String userId, String recetaId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            logger.error("Usuario no encontrado con id: " + userId);
            throw new RuntimeException("Usuario no encontrado");
        }

        if (user.getFavoritasIds() != null) {
            user.getFavoritasIds().remove(recetaId);
        }

        return userRepository.save(user);
    }

    @Override
    public List<Receta> getFavoritas(String userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            logger.error("Usuario no encontrado con id: " + userId);
            throw new RuntimeException("Usuario no encontrado");
        }

        if (user.getFavoritasIds() == null || user.getFavoritasIds().isEmpty()) {
            return new ArrayList<>();
        }

        List<ObjectId> recetaIds = user.getFavoritasIds().stream()
                .map(id -> {
                    try {
                        return new ObjectId(id);
                    } catch (IllegalArgumentException e) {
                        throw new RuntimeException("ID de receta inválido en favoritas");
                    }
                })
                .collect(Collectors.toList());

        return recetaRepository.findAllById(recetaIds);
    }
}