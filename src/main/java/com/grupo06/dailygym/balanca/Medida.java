package com.grupo06.dailygym.balanca;

import java.util.Random;

public class Medida {

	private int peso;
	private int porcentagemGordura;
	private int porcentagemAgua;
	
	public Medida() {
		Random generator = new Random();
		peso = generator.nextInt(80 - 60 + 1) + 60;
		porcentagemGordura = generator.nextInt(24 - 16 + 1) + 16;
		porcentagemAgua = generator.nextInt(60 - 50 + 1) + 50;
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
}
