package com.grupo06.dailygym.esteira;

import java.util.ArrayList;


public class Esteira implements IEsteira {
	
	private static Esteira instance = null;

	private String ip;
	
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
	public ArrayList<Treino> getSugestaoTreino(int tempo, int calorias, Intensidade intensidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void iniciaTreino(Treino treino) {
		// TODO Auto-generated method stub
		
	}
	
}
