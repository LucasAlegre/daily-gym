package com.grupo06.dailygym.esteira.control;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.grupo06.dailygym.usuario.model.Usuario;


public class Esteira implements IEsteira {
	
	private static Esteira instance = null;
	private static Treino treino;
	private static TreinoUniforme treinoUniforme;
	private String ip;
	Set<DayOfWeek> diasDisponiveis = new HashSet<DayOfWeek>();	
	public Usuario usuario = null;

	
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
	}
	
	@Override
	public Treino getSugestaoTreino(int caloriasParaQueimar, Intensidade intensidade) {
		treino = treinoUniforme.geraTreinoUniforme(intensidade, caloriasParaQueimar);
		return treino;
	}
	
	
	@Override
	public void executaTreino(Treino treino) {
		// TODO Auto-generated method stub
		
	}
	
}
