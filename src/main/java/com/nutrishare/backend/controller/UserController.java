package com.nutrishare.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutrishare.backend.dto.ApiResponse;
import com.nutrishare.backend.dto.UserResponse;
import com.nutrishare.backend.dto.UserUpdateRequest;
import com.nutrishare.backend.model.Receta;
import com.nutrishare.backend.model.User;
import com.nutrishare.backend.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> getCurrentUser() {
        try {
            User authenticatedUser = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            User user = userService.getUserById(authenticatedUser.getId());
            UserResponse userResponse = UserResponse.fromUser(user);
            return ResponseEntity.ok(ApiResponse.success(userResponse));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Error interno del servidor"));
        }
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> updateCurrentUser(@RequestBody UserUpdateRequest userUpdate) {
        try {
            User authenticatedUser = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            String id = authenticatedUser.getId();

            User updatedUser = userService.updateUser(id, userUpdate);
            UserResponse userResponse = UserResponse.fromUser(updatedUser);
            return ResponseEntity.ok(ApiResponse.success(userResponse));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Error interno del servidor"));
        }
    }

    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<String>> deleteCurrentUser() {
        try {
            User authenticatedUser = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            String id = authenticatedUser.getId();
            userService.deleteUser(id);
            return ResponseEntity.ok(ApiResponse.success("Perfil eliminado exitosamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Error interno del servidor"));
        }
    }

    @PostMapping("/me/favoritas/{recetaId}")
    public ResponseEntity<ApiResponse<UserResponse>> addFavorita(@PathVariable String recetaId) {
        try {
            User authenticatedUser = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            User updatedUser = userService.addFavorita(authenticatedUser.getId(), recetaId);
            UserResponse userResponse = UserResponse.fromUser(updatedUser);

            return ResponseEntity.ok(ApiResponse.success(userResponse));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Error interno del servidor"));
        }
    }

    @DeleteMapping("/me/favoritas/{recetaId}")
    public ResponseEntity<ApiResponse<UserResponse>> removeFavorita(@PathVariable String recetaId) {
        try {
            User authenticatedUser = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            User updatedUser = userService.removeFavorita(authenticatedUser.getId(), recetaId);
            UserResponse userResponse = UserResponse.fromUser(updatedUser);

            return ResponseEntity.ok(ApiResponse.success(userResponse));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Error interno del servidor"));
        }
    }

    @GetMapping("/me/favoritas")
    public ResponseEntity<ApiResponse<List<Receta>>> getFavoritas() {
        try {
            User authenticatedUser = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            List<Receta> favoritas = userService.getFavoritas(authenticatedUser.getId());

            return ResponseEntity.ok(ApiResponse.success(favoritas));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Error interno del servidor"));
        }
    }
}