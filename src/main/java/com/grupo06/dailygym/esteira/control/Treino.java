package com.grupo06.dailygym.esteira.control;

import java.time.LocalDate;
import java.util.Random;

import com.grupo06.dailygym.usuario.model.Usuario;

public class Treino {

	private LocalDate data;
	private Intensidade intensidade;
	private float velocidade;
	private int tempo;
	
	public Treino(Intensidade intensidade, Usuario usuario) {
		this.velocidade = calculaVelocidade(intensidade);
		this.tempo = calculaTempo(usuario);
		this.intensidade = intensidade;
		this.data = LocalDate.now();
	}
	
	public Treino(Intensidade intensidade, int tempo, float velocidade) {
		this.velocidade = velocidade;
		this.tempo = tempo;
		this.intensidade = intensidade;
		this.data = LocalDate.now();
	}
	
	private int calculaTempo(Usuario usuario) {
		int minutos =  (int) (usuario.getCaloriasParaPerder() / ( (0.35 * usuario.getPesoAtual()) + ((this.velocidade/usuario.getAltura())) * (0.029) * (usuario.getPesoAtual())));
		return minutos;
	}
	
	private float calculaVelocidade(Intensidade intensidade) {
		Random generate = new Random();
		float velocidade;
		
		if (intensidade.equals(Intensidade.LEVE)) {
			velocidade = (float)(5.0 + generate.nextFloat()*(7.5 - 5.0));
		} else if (intensidade.equals(Intensidade.MODERADO)) {
			velocidade = (float)(7.6 + generate.nextFloat()*(10 - 7.6));
		} else {
			velocidade = (float)(10.1 + generate.nextFloat()*(15.0 - 10.1)); 
		} 
		
		return velocidade;
	}
	
	public static int[] calcularIntervalos(int quantidade, int tempo) {
		int[] intervalos = new int[quantidade];
		double x = tempo / quantidade;
		for (int i = 0; i < quantidade; i++) {
			if (i == 0) {
				intervalos[i] = (int) Math.floor(x + tempo % quantidade);
			} else {
				intervalos[i] = (int) Math.floor(x);
			}
		}
		return intervalos; 
	}
	
	public int calculaCalorias(int distanciaPercorrida){
		return (int) ((distanciaPercorrida/1000) + (this.velocidade*10) + this.tempo);
	}
	
	public float getVelocidade(){
		return this.velocidade;
	}
	
	public int getTempo(){
		return this.tempo;
	}
	
	public LocalDate getData(){
		return this.data;
	}
	
}
