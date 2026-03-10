package com.nutrishare.backend.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.nutrishare.backend.model.Ingrediente;


public interface IngredienteRepository extends MongoRepository<Ingrediente, ObjectId>{

}
