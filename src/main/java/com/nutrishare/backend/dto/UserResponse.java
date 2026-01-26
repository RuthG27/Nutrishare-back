package com.nutrishare.backend.dto;

import com.nutrishare.backend.model.User;
import java.util.Objects;

public class UserResponse {
    private String id;
    private String email;

    public UserResponse() {
    }

    public UserResponse(String id, String email) {
        this.id = id;
        this.email = email;
    }

    // Método estático para convertir un User en UserResponse
    public static UserResponse fromUser(User user) {
        return new UserResponse(user.getId(), user.getEmail());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof UserResponse))
            return false;
        UserResponse other = (UserResponse) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "UserResponse [id=" + id + ", email=" + email + "]";
    }
}
