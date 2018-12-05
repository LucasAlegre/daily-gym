package com.grupo06.dailygym.smartwatch.sensores;

public interface ISmartWatchSensores {

	/**
	 * Medi��o de calorias perdidas do usu�rio
	 * @return calorias perdidas em kcal
	 */
	int getCaloriasPerdidas();
	
	/**
	 * Retorna batimento card�aco lido em bpm
	 * @return Batimentos por minuto lido
	 */
	int getBatimentoCardiaco();
	
	/**
	 * Sensor de dist�ncia percorrida pelo usu�rio com rel�gio no pulso
	 * @return Dist�ncia em Km
	 */
	int getDistanciaPercorrida();
}
