package com.nutrishare.backend.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class Nutrientes {
	@Field("calories")
	private double calorias;
	@Field("protein_g")
	private double proteinas;
	@Field("fat_g")
	private double grasasTotales;
	@Field("carbs_g")
	private double carbohidratos;
	@Field("fiber_g")
	private double fibra;
	
	
	public Nutrientes() {
		super();
	}


	public Nutrientes(double calorias, double proteinas, double grasasTotales, double carbohidratos, double fibra) {
		super();
		this.calorias = calorias;
		this.proteinas = proteinas;
		this.grasasTotales = grasasTotales;
		this.carbohidratos = carbohidratos;
		this.fibra = fibra;
	}


	public double getCalorias() {
		return calorias;
	}


	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}


	public double getProteinas() {
		return proteinas;
	}


	public void setProteinas(double proteinas) {
		this.proteinas = proteinas;
	}


	public double getGrasasTotales() {
		return grasasTotales;
	}


	public void setGrasasTotales(double grasasTotales) {
		this.grasasTotales = grasasTotales;
	}


	public double getCarbohidratos() {
		return carbohidratos;
	}


	public void setCarbohidratos(double carbohidratos) {
		this.carbohidratos = carbohidratos;
	}


	public double getFibra() {
		return fibra;
	}


	public void setFibra(double fibra) {
		this.fibra = fibra;
	}


	@Override
	public String toString() {
		return "Nutrientes [calorias=" + calorias + ", proteinas=" + proteinas + ", grasasTotales=" + grasasTotales
				+ ", carbohidratos=" + carbohidratos + ", fibra=" + fibra + "]";
	}
	
	

	
}
