package com.grupo06.dailygym.usuario;

import java.util.ArrayList;

import com.grupo06.dailygym.balanca.Medida;
import com.grupo06.dailygym.esteira.Exercicio;

public class Usuario implements IUsuario {

	private String nome;
	private float altura;
	
	private ArrayList<Exercicio> exercicios;
	private ArrayList<Medida> medidas;
	
	public Usuario() {
		
	}
	
	public float getPesoAtual() {
		if (!medidas.isEmpty()) {
			return medidas.get(medidas.size() - 1).getPeso();
		}
		else {
			return -1;
		}
	}

	@Override
	public float getIMC() {
		return getPesoAtual() / (altura * altura);
	}

	@Override
	public void deletaHistorico() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionaMedida(Medida medida) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionaExercicio(Exercicio exercicio) {
		// TODO Auto-generated method stub
		
	}
}	
