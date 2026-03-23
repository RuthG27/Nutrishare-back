package com.nutrishare.backend.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.nutrishare.backend.model.Receta;

public interface RecetaService {
    Receta crearReceta(Receta receta);

    List<Receta> obtenerTodas();

    List<Receta> obtenerPorUserId(String userId);

    Receta obtenerPorId(ObjectId id);

    Receta actualizarReceta(Receta receta);

    void eliminarReceta(ObjectId id);

}
