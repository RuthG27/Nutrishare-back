package com.nutrishare.backend.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.nutrishare.backend.model.Ingrediente;


public interface IngredienteService {
	
	Ingrediente crearIngrediente(Ingrediente ingrediente);

    List<Ingrediente> obtenerTodos();
    
    Ingrediente obtenerPorId(ObjectId id);

    Ingrediente actualizarIngrediente(Ingrediente ingrediente);

    void eliminarIngrediente(ObjectId id);

}
