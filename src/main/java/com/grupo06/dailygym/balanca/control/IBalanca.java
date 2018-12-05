package com.grupo06.dailygym.balanca.control;

import java.util.ArrayList;

public interface IBalanca {

	/**
	 * Retorna as medições no buffer da balança, limpando o buffer
	 * @return Medições no buffer
	 */
	public ArrayList<Medida> getMedicoes();
	
	/**
	 * Lê os sensores da balança e adiciona uma nova medição no buffer
	 * @return Medida realizada
	 */
	public Medida realizarMedicao();
}
