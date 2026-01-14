package com.nutrishare.backend.dto;

import com.nutrishare.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String email;

    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}
