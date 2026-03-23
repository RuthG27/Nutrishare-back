package com.nutrishare.backend.service;

import java.util.List;

import com.nutrishare.backend.dto.UserUpdateRequest;
import com.nutrishare.backend.model.User;
import com.nutrishare.backend.model.Receta;

public interface UserService {
    User getUserById(String id);

    User updateUser(String id, UserUpdateRequest userUpdate);

    void deleteUser(String id);

    User addFavorita(String userId, String recetaId);

    User removeFavorita(String userId, String recetaId);

    List<Receta> getFavoritas(String userId);
}
