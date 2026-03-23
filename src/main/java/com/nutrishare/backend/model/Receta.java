package com.nutrishare.backend.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "recetas")

public class Receta {

	@Id
	private ObjectId id;
	private String nombre;
	private String cocina;
	private String dificultad;
	@Field("tiempo_preparacion")
	private String tiempoPreparacion;
	private String[] pasos;

	private List<ObjectId> ingredientes;
	@Field("nutrientes_totales")
	private Nutrientes nutrientes;
	private String img;
	private double puntuacion;

	public Receta() {
		super();
	}

	public Receta(ObjectId id, String nombre, String cocina, String dificultad, String tiempoPreparacion,
			String[] pasos, List<ObjectId> ingredientes, Nutrientes nutrientes, String img, double puntuacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cocina = cocina;
		this.dificultad = dificultad;
		this.tiempoPreparacion = tiempoPreparacion;
		this.pasos = pasos;
		this.ingredientes = ingredientes;
		this.nutrientes = nutrientes;
		this.img = img;
		this.puntuacion = puntuacion;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	//Para mostrar correctamente el id en el JSON
	@JsonProperty("id")
	public String getIdAsString() {
		return id != null ? id.toHexString() : null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCocina() {
		return cocina;
	}

	public void setCocina(String cocina) {
		this.cocina = cocina;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public String getTiempoPreparacion() {
		return tiempoPreparacion;
	}

	public void setTiempoPreparacion(String tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}

	public String[] getPasos() {
		return pasos;
	}

	public void setPasos(String[] pasos) {
		this.pasos = pasos;
	}

	public List<ObjectId> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<ObjectId> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public Nutrientes getNutrientes() {
		return nutrientes;
	}

	public void setNutrientes(Nutrientes nutrientes) {
		this.nutrientes = nutrientes;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	@Override
	public String toString() {
		return "Receta [id=" + id + ", nombre=" + nombre + ", cocina=" + cocina + ", dificultad=" + dificultad
				+ ", tiempoPreparacion=" + tiempoPreparacion + ", pasos=" + Arrays.toString(pasos) + ", ingredientes="
				+ ingredientes + ", nutrientes=" + nutrientes + ", img=" + img + ", puntuacion=" + puntuacion + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Receta))
			return false;
		Receta other = (Receta) obj;
		return Objects.equals(id, other.id);
	}

}
