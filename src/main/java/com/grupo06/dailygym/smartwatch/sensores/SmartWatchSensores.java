package com.grupo06.dailygym.smartwatch.sensores;

import java.util.Random;

public class SmartWatchSensores implements ISmartWatchSensores {

	@Override
	public int getCaloriasPerdidas() {
		Random generator = new Random();
		int calorias = generator.nextInt(50 - 40 + 1) + 40;
		return calorias;
	}

	@Override
	public int getBatimentoCardiaco() {
		Random generator = new Random();
		int batimento = generator.nextInt(80 - 75 + 1) + 75;
		return batimento;
	}

	@Override
	public int getDistanciaPercorrida() {
		Random generator = new Random();
		int distancia = generator.nextInt(200 - 180 + 1) + 180;
		return distancia;
	}

}
