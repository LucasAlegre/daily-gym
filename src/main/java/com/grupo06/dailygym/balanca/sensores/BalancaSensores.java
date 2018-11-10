package com.grupo06.dailygym.balanca.sensores;

import java.util.Random;

public class BalancaSensores implements IBalancaSensores {

	@Override
	public int getPeso() {
		Random generator = new Random();
		int peso = generator.nextInt(80 - 60 + 1) + 60;
		return peso;
	}

	@Override
	public int getPorcentagemGordura() {
		Random generator = new Random();
		int porcentagemGordura = generator.nextInt(24 - 16 + 1) + 16;
		return porcentagemGordura;
	}

	@Override
	public int getPorcentagemAgua() {
		Random generator = new Random();
		int porcentagemAgua = generator.nextInt(60 - 50 + 1) + 50;
		return porcentagemAgua;
	}

}
