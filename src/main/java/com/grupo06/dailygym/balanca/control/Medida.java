package com.grupo06.dailygym.balanca.control;

import java.time.LocalDateTime;

public class Medida {

	private int peso;
	private int porcentagemGordura;
	private int porcentagemAgua;
	private LocalDateTime data;
	
	public Medida(int peso, int porcentagemGordura, int porcentagemAgua) {
		this.peso = peso;
		this.porcentagemGordura = porcentagemGordura;
		this.porcentagemAgua = porcentagemAgua;
		this.data = LocalDateTime.now();
	}
	
	public int getPeso() {
		return peso;
	}
	
	public int getPorcentagemGordura() {
		return porcentagemGordura;
	}
	
	public int getPorcentagemAgua() {
		return porcentagemAgua;
	}
	
	public LocalDateTime getData() {
		return data;
	}
}
