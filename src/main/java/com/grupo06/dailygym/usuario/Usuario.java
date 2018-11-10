package com.grupo06.dailygym.usuario;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.InvalidParameterException;
import java.time.DayOfWeek;

import com.grupo06.dailygym.balanca.Medida;
import com.grupo06.dailygym.esteira.Exercicio;
import com.grupo06.dailygym.esteira.Treino;

public class Usuario implements IUsuario {

	private static Usuario instance = null;
	
	private String nome;
	private int idade;
	private float altura;
	private Set<DayOfWeek> diasDisponiveisTreino;
	private ArrayList<Exercicio> exercicios;
	private ArrayList<Medida> medidas;
	private ArrayList<Treino> treinos;
	private Boolean isCreated = false;
	
	public static Usuario getInstance(){
		if(instance == null){
			synchronized(Usuario.class){
				if(instance == null){
					instance = new Usuario();
				}
			}
		}
		return instance;
	}
	
	private Usuario() {
		//TODO: le banco de dados
		medidas = new ArrayList<Medida>();
	}
	
	public void criaUsario(String nome, int idade, float altura, Set<DayOfWeek> diasDisponiveis) throws InvalidParameterException {
		this.setNome(nome);
		this.setIdade(idade);
		this.setAltura(altura);
		this.setDiasDisponiveis(diasDisponiveis);
		this.isCreated = true;
	}
	
	public void setCreated(Boolean created) {
		this.isCreated = created;
	}
	
	public Boolean isCreated() {
		return isCreated;
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

	@Override
	public float getIMC() {
		return getPesoAtual() / (altura * altura);
	}

	@Override
	public void deletaHistorico() {
		exercicios.clear();
		medidas.clear();
		treinos.clear();
	}

	@Override
	public void adicionaMedida(Medida medida) {
		medidas.add(medida);
	}

	@Override
	public void adicionaExercicio(Exercicio exercicio) {
		exercicios.add(exercicio);
	}
}	
