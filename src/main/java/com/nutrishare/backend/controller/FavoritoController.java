package com.nutrishare.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutrishare.backend.dto.ApiResponse;
import com.nutrishare.backend.dto.FavoritoResponse;
import com.nutrishare.backend.model.Favorito;
import com.nutrishare.backend.model.User;
import com.nutrishare.backend.service.FavoritoService;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {
    @Autowired
    private FavoritoService favoritoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<FavoritoResponse>>> obtenerMisFavoritos() {
        try {
            User authenticatedUser = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            List<Favorito> favoritos = favoritoService.obtenerFavoritosPorUsuario(authenticatedUser.getId());
            List<FavoritoResponse> response = favoritos.stream()
                    .map(FavoritoResponse::fromFavorito)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Error interno del servidor"));
        }
    }

    @PostMapping("/agregar/{recetaId}")
    public ResponseEntity<ApiResponse<FavoritoResponse>> agregarFavorito(@PathVariable String recetaId) {
        try {
            User authenticatedUser = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            Favorito favorito = favoritoService.agregarFavorito(authenticatedUser.getId(), recetaId);
            FavoritoResponse response = FavoritoResponse.fromFavorito(favorito);

            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Error interno del servidor"));
        }
    }

    @DeleteMapping("/eliminar/{recetaId}")
    public ResponseEntity<ApiResponse<String>> eliminarFavorito(@PathVariable String recetaId) {
        try {
            User authenticatedUser = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            favoritoService.eliminarFavorito(authenticatedUser.getId(), recetaId);
            return ResponseEntity.ok(ApiResponse.success("Favorito eliminado exitosamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Error interno del servidor"));
        }
    }
}
