package com.grupo06.dailygym.usuario.model;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.InvalidParameterException;
import java.time.DayOfWeek;

import com.grupo06.dailygym.balanca.control.Medida;
import com.grupo06.dailygym.esteira.Treino;
import com.grupo06.dailygym.smartwatch.control.Exercicio;

public class Usuario {

	private String nome;
	private int idade;
	private float altura;
	private int metaDiaria;
	private Set<DayOfWeek> diasDisponiveisTreino;
	private ArrayList<Exercicio> exercicios;
	private ArrayList<Medida> medidas;
	private ArrayList<Treino> treinos;
	
	public Usuario(String nome, int idade, float altura, int metaDiaria, Set<DayOfWeek> diasDisponiveis) throws InvalidParameterException {
		this.setNome(nome);
		this.setIdade(idade);
		this.setAltura(altura);
		this.setDiasDisponiveis(diasDisponiveis);
		this.exercicios = new ArrayList<Exercicio>();
		this.medidas = new ArrayList<Medida>();
		this.treinos = new ArrayList<Treino>();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
    	if(nome.length() < 100) {
        	String regx = "^[\\p{L} .'-]+$";
            Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(nome);
            if(matcher.find()) {
            	this.nome = nome;
            }
    	}
    	else {
    		throw new InvalidParameterException();
    	}
	}
	
	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		if(idade > 0 && idade < 100) {
			this.idade = idade;
		}
    	else {
    		throw new InvalidParameterException();
    	}
	}

	public float getAltura() {
		return altura;
	}
	
	public int getMetaDiaria() {
		return this.metaDiaria;
	}
	
	public void setMetaDiaria(int meta) {
		if(meta > 0) {
			this.metaDiaria = meta;
		}
		else {
			throw new InvalidParameterException();
		}
	}

	public void setAltura(float altura) {
		if(altura > 0.0 && altura < 3.0) {
			this.altura = altura;
		}
    	else {
    		throw new InvalidParameterException();
    	}
	}

	public Set<DayOfWeek> getDiasDisponiveis() {
		return diasDisponiveisTreino;
	}

	public void setDiasDisponiveis(Set<DayOfWeek> diasDisponiveis) {
		if(!diasDisponiveis.isEmpty()) {
			this.diasDisponiveisTreino = diasDisponiveis;
		}
    	else {
    		throw new InvalidParameterException();
    	}
	}

	public float getPesoAtual() {
		return medidas.get(medidas.size() - 1).getPeso();
	}

	public float getIMC() {
		return getPesoAtual() / (altura * altura);
	}

	public void deletaHistorico() {
		exercicios.clear();
		medidas.clear();
		treinos.clear();
	}

	public void adicionaMedida(Medida medida) {
		medidas.add(medida);
	}

	public void adicionaExercicio(Exercicio exercicio) {
		exercicios.add(exercicio);
	}
}	