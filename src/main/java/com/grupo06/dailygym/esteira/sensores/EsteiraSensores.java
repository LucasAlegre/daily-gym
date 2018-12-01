package com.grupo06.dailygym.esteira.sensores;

import java.util.Random;

public class EsteiraSensores implements IEsteiraSensores {
	@Override
	public int getDistanciaPercorrida() {
		Random generator = new Random();
		int distancia = generator.nextInt(2500 - 1000 + 1) + 1000;
		return distancia;
	}
}
