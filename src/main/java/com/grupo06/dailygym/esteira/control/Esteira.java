package com.grupo06.dailygym.esteira.control;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.grupo06.dailygym.usuario.DAO.UsuarioDAO;
import com.grupo06.dailygym.usuario.model.Usuario;
import java.time.DayOfWeek;


public class Esteira implements IEsteira {
	
	private static Esteira instance = null;
	private String ip;
	Set<DayOfWeek> diasDisponiveis = new HashSet<DayOfWeek>();	
	public Usuario usuario = null;
	//usuario = UsuarioDAO.getUsuario();

	
	public static IEsteira connectEsteira(String ip) {
		
		// Aqui se realizaria conex�o via Socket atrav�s do IP
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
		
		
		Treino treino = new TreinoUniforme();
		treino.velocidade = generateVelocidade(intensidade);
		treino.tempo = calculateTempo(intensidade, caloriasParaQueimar);
		
		
	}
	
	public int calculateTempo(float velocidade, int calorias) {
		diasDisponiveis.add(DayOfWeek.MONDAY);
		diasDisponiveis.add(DayOfWeek.SUNDAY);
		usuario = new Usuario("Bruna", 29, (float)1.64, 400, diasDisponiveis);
		int tempo = (int)(calorias / ( (0.035 * usuario.getPesoAtual()) + ((velocidade/usuario.getAltura())) * (0.029) * (usuario.getPesoAtual())));
		
		return tempo;
	}
	
	public float generateVelocidade(Intensidade intensidade) {
		Random generate = new Random();
		float velocidade;
		
		if (intensidade.equals(Intensidade.LEVE)) {
			velocidade = (float)(5.0 + generate.nextFloat()*(7.5 - 5.0));
		} else if (intensidade.equals(Intensidade.MODERADO)) {
			velocidade = (float)(7.6 + generate.nextFloat()*(10 - 7.6));
		} else if (intensidade.equals(Intensidade.INTENSO)) {
			velocidade = (float)(10.1 + generate.nextFloat()*(10.1 - 15.0)); 
		} else {
			//TODO: usuario digita na tela a velocidade e o tempo 
			velocidade = 0.0f;
		}
		return velocidade;
	}
	
	@Override
	public void executaTreino(Treino treino) {
		// TODO Auto-generated method stub
		
	}
	
}
