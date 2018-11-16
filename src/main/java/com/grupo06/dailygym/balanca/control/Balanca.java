package com.grupo06.dailygym.balanca.control;

import java.util.ArrayList;

import com.grupo06.dailygym.balanca.sensores.BalancaSensores;
import com.grupo06.dailygym.usuario.DAO.UsuarioDAO;
import com.grupo06.dailygym.usuario.DAO.UsuarioDAOBancoFicticio;
import com.grupo06.dailygym.usuario.model.Usuario;

public class Balanca implements IBalanca {
	
	private static Balanca instance = null;
	private BalancaSensores sensores;
	private ArrayList<Medida> bufferMedida;
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
		this.bufferMedida = new ArrayList<Medida>();
	}

	@Override
	public synchronized Medida realizarMedicao()  {
		int pesoAtual = sensores.getPeso();
		int porcentagemGorduraAtual = sensores.getPorcentagemGordura();
		int porcentagemAguaAtual = sensores.getPorcentagemAgua();
		
		Medida medida = new Medida(pesoAtual, porcentagemGorduraAtual, porcentagemAguaAtual);
		
		this.bufferMedida.add(medida);
		
		return medida;
	}

	@Override
	public synchronized ArrayList<Medida> getMedicoes() {
		ArrayList<Medida> medidas = this.bufferMedida;
		this.bufferMedida = new ArrayList<Medida>();
		return medidas;
	}
	
	
	
}
