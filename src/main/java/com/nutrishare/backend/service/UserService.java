package com.nutrishare.backend.service;

import com.nutrishare.backend.dto.UserUpdateRequest;
import com.nutrishare.backend.model.User;

public interface UserService {
    User getUserById(String id);

    User updateUser(String id, UserUpdateRequest userUpdate);
}
