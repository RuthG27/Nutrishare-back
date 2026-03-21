package com.nutrishare.backend.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutrishare.backend.model.Receta;
import com.nutrishare.backend.repository.RecetaRepository;
import com.nutrishare.backend.service.RecetaService;

@Service

public class RecetaServiceImpl implements RecetaService {
	
    private static final Logger logger = LoggerFactory.getLogger(RecetaServiceImpl.class);

	
	@Autowired
	RecetaRepository recetaRepository;
	@Override
	public Receta crearReceta(Receta receta) {
		
		return recetaRepository.save(receta);
	}

	@Override
	public List<Receta> obtenerTodas() {

		return recetaRepository.findAll();
	}

	@Override
	public Receta obtenerPorId(ObjectId id) {

		Receta receta = recetaRepository.findById(id).orElse(null);
		if (receta == null) {
			 logger.error("La receta no existe");
		}
		return receta;
	}

	@Override
	public Receta actualizarReceta(Receta receta) {

		if (recetaRepository.existsById(receta.getId()))
			return recetaRepository.save(receta);
		else
			return null;
	}

	@Override
	public void eliminarReceta(ObjectId id) {
		
		recetaRepository.deleteById(id);
		
	}

	@Override
	public List<Receta> obtenerPorUserId(String userId) {
		return recetaRepository.findByUserId(userId);
	}

}
