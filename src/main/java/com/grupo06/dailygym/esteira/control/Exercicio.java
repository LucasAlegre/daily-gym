package com.grupo06.dailygym.esteira.control;

import java.time.LocalDate;

public class Exercicio {

	private int caloriasQueimadas;
	private int distanciaPercorrida;
	private LocalDate data;
	
	public Exercicio(int calorias, int distancia) {
		this.caloriasQueimadas = calorias;
		this.distanciaPercorrida = distancia;
		this.data = LocalDate.now();
	}
	
	public int getCaloriasQueimadas() {
		return this.caloriasQueimadas;
	}
	
	public int getDistanciaPercorrida() {
		return this.distanciaPercorrida;
	}

	public LocalDate getData() {
		return this.data;
	}
	
}
