package com.grupo06.dailygym.smartwatch.control;

import java.time.DayOfWeek;
import java.util.Set;

import com.grupo06.dailygym.balanca.control.IBalanca;
import com.grupo06.dailygym.esteira.control.IEsteira;
import com.grupo06.dailygym.esteira.control.Intensidade;
import com.grupo06.dailygym.esteira.control.Treino;
import com.grupo06.dailygym.usuario.model.Usuario;

public interface ISmartWatch {
	
	boolean sincronizaEsteira(String ipEsteira);
	
	boolean sincronizaBalanca(String ipBalanca);
	
	int getBatimentoCardiaco();
	
	boolean isBatimentoElevado();
	
	Treino getSugestaoTreino(Intensidade intensidade);
	
	void executaTreino(Treino treino);
	
}
