package com.grupo06.dailygym.smartwatch.controle;

import java.time.DayOfWeek;
import java.util.Set;

import com.grupo06.dailygym.balanca.IBalanca;
import com.grupo06.dailygym.esteira.IEsteira;
import com.grupo06.dailygym.esteira.Treino;
import com.grupo06.dailygym.usuario.Usuario;

public interface ISmartWatch {
	
	boolean sincronizaEsteira(IEsteira esteira);
	
	boolean sincronizaBalanca(IBalanca balanca);
	
	void reiniciarAcompanhamento();
	
	Treino getSugestaoTreino();
	
	void iniciaTreino();
	
	void novoUsuario(String nome, float altura, Set<DayOfWeek> diasDisponiveis);
	
	void alterarUsuario(String nome, float altura, Set<DayOfWeek> diasDisponiveis);

	Usuario consultarUsuario();
	
	void deletarUsuario();
	
	void alterarDiasDisponiveis(Set<DayOfWeek> diasDisponiveis);
}
