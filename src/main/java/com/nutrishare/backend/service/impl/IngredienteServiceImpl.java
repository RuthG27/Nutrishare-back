package com.nutrishare.backend.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutrishare.backend.model.Ingrediente;
import com.nutrishare.backend.repository.IngredienteRepository;
import com.nutrishare.backend.service.IngredienteService;

@Service
public class IngredienteServiceImpl implements IngredienteService {
	
    private static final Logger logger = LoggerFactory.getLogger(IngredienteServiceImpl.class);

	@Autowired
	IngredienteRepository ingredienteRepository;

	@Override
	public Ingrediente crearIngrediente(Ingrediente ingrediente) {
		return ingredienteRepository.save(ingrediente);
	}

	@Override
	public List<Ingrediente> obtenerTodos() {
		return ingredienteRepository.findAll();
	}

	@Override
	public Ingrediente obtenerPorId(ObjectId id) {

		Ingrediente ingrediente = ingredienteRepository.findById(id).orElse(null);
		if (ingrediente == null) {
			 logger.error("El ingrediente no existe");
		}
		return ingrediente;
	}

	@Override
	public Ingrediente actualizarIngrediente(Ingrediente ingrediente) {

		if (ingredienteRepository.existsById(ingrediente.getId()))
			return ingredienteRepository.save(ingrediente);
		else
			return null;
	}

	@Override
	public void eliminarIngrediente(ObjectId id) {
		ingredienteRepository.deleteById(id);
	}
}
