package com.grupo06.dailygym.esteira.control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TreinoIntercalado extends Treino {
	//private float velocidade;
	//private int tempo;
	static List<TreinoIntercalado> treinos = new ArrayList<TreinoIntercalado>();

	public TreinoIntercalado(int tempo, float velocidade) {
		this.tempo = tempo;
		this.velocidade = velocidade;
		this.intensidade = intensidade.CUSTOMIZADO;
		this.data = LocalDate.now();
	}
	
	public static List<TreinoIntercalado> treinoIntercalado(int tempo, float[] velocidades) {
		int[] intervals = new int[velocidades.length];
		intervals = calculateIntervals(velocidades.length, tempo);
		
		for (int i = 0; i < velocidades.length; i++) {
			TreinoIntercalado treino = new TreinoIntercalado(intervals[i], velocidades[i]);
			treinos.add(treino);
		}
		printTreinos(treinos);
		return treinos;
	}
	
	private static int[] calculateIntervals(int quantidade, int tempo) {
		int[] intervals = new int[quantidade];
		double x = tempo / quantidade;
		for (int i = 0; i < quantidade; i++) {
			if (i == 0) {
				intervals[i] = (int) Math.floor(x + tempo % quantidade);
			} else {
				intervals[i] = (int) Math.floor(x);
			}
		}
		return intervals; 
	}
	
	public static int getTempo(TreinoIntercalado treino) {
		return treino.tempo;
	}
	
	public static float getVelocidade(TreinoIntercalado treino) {
		return treino.velocidade;
	}
	
	// facilitar debug
	public static void printTreinos(List<TreinoIntercalado> treinos) {
		for (TreinoIntercalado treino : treinos) {
			int x = getTempo(treino);
			float y = getVelocidade(treino);
			System.out.println(x);
			System.out.println(y);
		}
	}
}
