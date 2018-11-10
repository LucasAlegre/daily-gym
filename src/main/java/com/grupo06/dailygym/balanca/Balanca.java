package com.grupo06.dailygym.balanca;

import com.grupo06.dailygym.balanca.sensores.BalancaSensores;
import com.grupo06.dailygym.usuario.Usuario;

public class Balanca implements IBalanca {
	
	private static Balanca instance = null;
	private BalancaSensores sensores;
	private String ip;
	
	public static IBalanca connectBalanca(String ip) {
		
		// Aqui se realizaria conexão via Socket através do IP
		
		if(instance == null){
			synchronized(Balanca.class){
				if(instance == null){
					instance = new Balanca(ip);
				}
			}
		}
		return instance;
	}
	
	private Balanca(String ip){
		this.ip = ip;
		this.sensores = new BalancaSensores();
	}

	@Override
	public Medida realizarMedicao() {
		int pesoAtual = sensores.getPeso();
		int porcentagemGorduraAtual = sensores.getPorcentagemGordura();
		int porcentagemAguaAtual = sensores.getPorcentagemAgua();
		
		Medida medida = new Medida(pesoAtual, porcentagemGorduraAtual, porcentagemAguaAtual);
		
		Usuario usuario = Usuario.getInstance();
		if(usuario.isCreated())
			usuario.adicionaMedida(medida);
		
		return medida;
	}
	
}
