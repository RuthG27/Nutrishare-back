package com.nutrishare.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
    private T data;
    private String message;
    private boolean success;

    public ApiResponse() {
    }

    public ApiResponse(T data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, null, true);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(null, message, false);
    }
}
