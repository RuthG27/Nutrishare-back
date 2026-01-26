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

import com.nutrishare.backend.model.Ingrediente;
import com.nutrishare.backend.service.IngredienteService;


@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping (value= "/todas")
	public List<Ingrediente> obtenerTodas(){
		return ingredienteService.obtenerTodos();
	}
	
	@GetMapping("/{id}")
	public Ingrediente obtenerPorId(@PathVariable ObjectId id) {
	    return ingredienteService.obtenerPorId(id);
	}
	
	@PostMapping
	public Ingrediente crearIngrediente(@RequestBody Ingrediente ingrediente) {
		return ingredienteService.crearIngrediente(ingrediente);
	}
	
	@PutMapping("/")
	public Ingrediente actualizarIngrediente(Ingrediente ingrediente) {
		return ingredienteService.actualizarIngrediente(ingrediente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarIngrediente(@PathVariable ObjectId id){
		ingredienteService.eliminarIngrediente(id);
		return ResponseEntity.ok("Ingrediente eliminado");
	}


}
