package com.grupo06.dailygym.esteira.control;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.grupo06.dailygym.balanca.control.Medida;
import com.grupo06.dailygym.balanca.sensores.BalancaSensores;
import com.grupo06.dailygym.balanca.sensores.IBalancaSensores;
import com.grupo06.dailygym.esteira.sensores.EsteiraSensores;
import com.grupo06.dailygym.esteira.sensores.IEsteiraSensores;
import com.grupo06.dailygym.smartwatch.control.SmartWatchFacade;
import com.grupo06.dailygym.usuario.model.Usuario;


public class Esteira implements IEsteira {
	
	private static Esteira instance = null;
	private ArrayList<Treino> treinos;
	private ArrayList<Exercicio> exercicios;
	private String ip;
	public Usuario usuario = null;
	private IEsteiraSensores sensores;
	
	
	public static IEsteira connectEsteira(String ip) {
		
		// Aqui se realizaria conexão via Socket através do IP
		if(instance == null){
			synchronized(Esteira.class){
				if(instance == null){
					instance = new Esteira(ip);
				}
			}
		}
		return instance;
	}
	
	private Esteira(String ip){
		this.ip = ip;
		this.sensores = new EsteiraSensores();
		this.treinos = new ArrayList<Treino>();
		this.exercicios = new ArrayList<Exercicio>();
	}
	
	@Override
	public void sugerirTreino(Usuario usuario, Intensidade intensidade) {
		Treino treino = new Treino(intensidade, usuario);
		this.treinos.add(treino);
	}
	
	@Override
	public void setTreinoCustomizado(int tempo, float[] velocidades){
		int[] intervalos = new int[velocidades.length];
		intervalos = Treino.calcularIntervalos(velocidades.length, tempo);
		
		for (int i = 0; i < velocidades.length; i++) {
			Treino treino = new Treino(Intensidade.CUSTOMIZADO, intervalos[i], velocidades[i]);
			this.treinos.add(treino);
		}
	}
	
	@Override
	public Treino iniciarTreino() {
		return this.treinos.remove(0);
	}
	
	
	@Override
	public Exercicio finalizarTreino(Treino treino){
		int distancia = this.sensores.getDistanciaPercorrida();
		int calorias = treino.calculaCalorias(distancia);
		Exercicio exercicio =  new Exercicio(calorias,distancia);
		this.exercicios.add(exercicio);
		return exercicio;
	}
	

	@Override
	public synchronized ArrayList<Exercicio> getExercicios() {
		ArrayList<Exercicio> exercicios = this.exercicios;
		this.exercicios = new ArrayList<Exercicio>();
		return exercicios;
	}
	
}
