package com.grupo06.dailygym.balanca.control;

import java.util.ArrayList;

public interface IBalanca {

	/**
	 * Retorna as medi��es no buffer da balan�a, limpando o buffer
	 * @return Medi��es no buffer
	 */
	public ArrayList<Medida> getMedicoes();
	
	/**
	 * L� os sensores da balan�a e adiciona uma nova medi��o no buffer
	 * @return Medida realizada
	 */
	public Medida realizarMedicao();
}
