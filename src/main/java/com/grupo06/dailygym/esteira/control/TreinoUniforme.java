package com.grupo06.dailygym.esteira.control;

import java.time.LocalDate;
import java.util.Random;
import com.grupo06.dailygym.smartwatch.control.SmartWatchFacade;
import com.grupo06.dailygym.usuario.model.Usuario;

public class TreinoUniforme extends Treino {
	
	private Treino treino;
	private SmartWatchFacade facade;

	public TreinoUniforme(int tempo, float velocidade, Intensidade intensidade) {
		this.tempo = tempo;
		this.velocidade = velocidade;
		this.intensidade = intensidade;
		this.data = LocalDate.now();
	}
	
	public Treino geraTreinoUniforme(Intensidade intensidade, int caloriasParaQueimar) {
		int tempo = calculaTempo(this.velocidade, caloriasParaQueimar);
		float velocidade = generateVelocidade(intensidade);
		
		TreinoUniforme treino = new TreinoUniforme(tempo, velocidade, intensidade);
		
		return treino;
	}
	
	private int calculaTempo(float velocidade, int caloriasParaQueimar) {
		Usuario usuario = facade.consultaPerfil();
		int minutos =  (int) (caloriasParaQueimar / ( (0.035 * usuario.getPesoAtual()) + ((velocidade/usuario.getAltura())) * (0.029) * (usuario.getPesoAtual())));
		
		return minutos;
	}
	
	private float generateVelocidade(Intensidade intensidade) {
		Random generate = new Random();
		float velocidade;
		
		if (intensidade.equals(Intensidade.LEVE)) {
			velocidade = (float)(5.0 + generate.nextFloat()*(7.5 - 5.0));
		} else if (intensidade.equals(Intensidade.MODERADO)) {
			velocidade = (float)(7.6 + generate.nextFloat()*(10 - 7.6));
		} else {
			velocidade = (float)(10.1 + generate.nextFloat()*(10.1 - 15.0)); 
		} 
		
		return velocidade;
	}
	
	
}
