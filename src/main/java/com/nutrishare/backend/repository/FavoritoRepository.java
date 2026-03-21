package com.nutrishare.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nutrishare.backend.model.Favorito;

public interface FavoritoRepository extends MongoRepository<Favorito, String> {
    List<Favorito> findByUserId(String userId);

    Optional<Favorito> findByUserIdAndRecetaId(String userId, String recetaId);

    void deleteByUserIdAndRecetaId(String userId, String recetaId);
}
