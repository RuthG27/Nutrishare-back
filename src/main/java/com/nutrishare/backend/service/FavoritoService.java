package com.nutrishare.backend.service;

import java.util.List;

import com.nutrishare.backend.model.Favorito;

public interface FavoritoService {
    List<Favorito> obtenerFavoritosPorUsuario(String userId);

    Favorito agregarFavorito(String userId, String recetaId);

    void eliminarFavorito(String userId, String recetaId);
}
