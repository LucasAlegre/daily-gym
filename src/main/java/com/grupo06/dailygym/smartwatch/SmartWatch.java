package com.grupo06.dailygym.smartwatch;

import java.time.DayOfWeek;
import java.util.Set;

import com.grupo06.dailygym.balanca.IBalanca;
import com.grupo06.dailygym.esteira.IEsteira;
import com.grupo06.dailygym.esteira.Treino;
import com.grupo06.dailygym.usuario.Usuario;

public class SmartWatch implements ISmartWatch {
	
	private IEsteira esteira;
	private IBalanca balanca;
	
	public SmartWatch() {
		
	}

	@Override
	public boolean sincronizaEsteira(IEsteira esteira) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sincronizaBalanca(IBalanca balanca) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reiniciarAcompanhamento() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Treino getSugestaoTreino() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void iniciaTreino() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void novoUsuario(String nome, float altura, Set<DayOfWeek> diasDisponiveis) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterarUsuario(String nome, float altura, Set<DayOfWeek> diasDisponiveis) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario consultarUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletarUsuario() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterarDiasDisponiveis(Set<DayOfWeek> diasDisponiveis) {
		// TODO Auto-generated method stub
		
	}

}
