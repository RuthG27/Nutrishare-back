package com.nutrishare.backend.model;

import java.util.Arrays;
import java.util.Objects;

import org.bson.types.ObjectId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ingredientes")
public class Ingrediente {
	@Id
	@JsonProperty("_id")
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
	private String nombre;
	private String categoria;
	@Field("valores_nutricionales")
	private ValoresNutricionales[] valoresNutricionales;
 	private String img;
 	
 	
	public Ingrediente() {
		super();
	}


	public Ingrediente(ObjectId id, String nombre, String categoria, ValoresNutricionales[] valoresNutricionales,
			String img) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.valoresNutricionales = valoresNutricionales;
		this.img = img;
	}


	public ObjectId getId() {
		return id;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public ValoresNutricionales[] getValoresNutricionales() {
		return valoresNutricionales;
	}


	public void setValoresNutricionales(ValoresNutricionales[] valoresNutricionales) {
		this.valoresNutricionales = valoresNutricionales;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", valoresNutricionales="
				+ Arrays.toString(valoresNutricionales) + ", img=" + img + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Ingrediente))
			return false;
		Ingrediente other = (Ingrediente) obj;
		return Objects.equals(id, other.id);
	}


	

}
