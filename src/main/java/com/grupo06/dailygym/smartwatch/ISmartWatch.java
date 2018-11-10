package com.grupo06.dailygym.smartwatch;

import java.time.DayOfWeek;
import java.util.Set;

import com.grupo06.dailygym.balanca.IBalanca;
import com.grupo06.dailygym.esteira.IEsteira;
import com.grupo06.dailygym.esteira.Treino;
import com.grupo06.dailygym.usuario.Usuario;

public interface ISmartWatch {
	
	boolean sincronizaEsteira(String ipEsteira);
	
	boolean sincronizaBalanca(String ipBalanca);
	
	void reiniciarAcompanhamento();
	
	Treino getSugestaoTreino();
	
	void iniciaTreino();
	
}
