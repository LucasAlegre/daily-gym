package com.grupo06.dailygym.smartwatch.sensores;

public interface ISmartWatchSensores {

	/**
	 * Medição de calorias perdidas do usuário
	 * @return calorias perdidas em kcal
	 */
	int getCaloriasPerdidas();
	
	/**
	 * Retorna batimento cardíaco lido em bpm
	 * @return Batimentos por minuto lido
	 */
	int getBatimentoCardiaco();
	
	/**
	 * Sensor de distância percorrida pelo usuário com relógio no pulso
	 * @return Distância em Km
	 */
	int getDistanciaPercorrida();
}
