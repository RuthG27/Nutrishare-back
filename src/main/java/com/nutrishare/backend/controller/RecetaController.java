package com.nutrishare.backend.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutrishare.backend.model.Receta;
import com.nutrishare.backend.service.RecetaService;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

	@Autowired
	private RecetaService recetaService;

	@GetMapping(value = "/todas")
	public List<Receta> obtenerTodas() {
		return recetaService.obtenerTodas();
	}

	@GetMapping("/{id}")
	public Receta obtenerPorId(@PathVariable ObjectId id) {
		return recetaService.obtenerPorId(id);
	}

	@PostMapping
	public Receta crearReceta(@RequestBody Receta receta) {
		return recetaService.crearReceta(receta);
	}

	@PutMapping("/")
	public Receta actualizarReceta(Receta receta) {
		return recetaService.actualizarReceta(receta);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarReceta(@PathVariable ObjectId id) {
		recetaService.eliminarReceta(id);
		return ResponseEntity.ok("Receta eliminada");
	}

}
