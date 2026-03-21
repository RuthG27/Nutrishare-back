package com.nutrishare.backend.repository;

import org.bson.types.ObjectId;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nutrishare.backend.model.Receta;

public interface RecetaRepository extends MongoRepository<Receta, ObjectId> {
    List<Receta> findByUserId(String userId);
}
