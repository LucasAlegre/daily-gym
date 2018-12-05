package com.grupo06.dailygym.balanca.sensores;

public interface IBalancaSensores {
	
	/**
	 * Mede peso
	 * @return Peso lido no momento atual em Kg
	 */
	public int getPeso();
	
	/**
	 * Mede porcentagem de gordura
	 * @return Porcentagem de gordura medida no momento atual, valor entre 0-100
	 */
	public int getPorcentagemGordura();
	
	/**
	 * Mede porcentagem de água corporal
	 * @return Porcentagem de água medida no momento atual, valor entre 0-100
	 */
	public int getPorcentagemAgua();
}
