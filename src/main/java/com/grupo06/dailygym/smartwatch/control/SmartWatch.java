package com.grupo06.dailygym.smartwatch.control;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import com.grupo06.dailygym.smartwatch.sensores.ISmartWatchSensores;
import com.grupo06.dailygym.smartwatch.sensores.SmartWatchSensores;
import com.grupo06.dailygym.usuario.DAO.UsuarioDAO;
import com.grupo06.dailygym.usuario.DAO.UsuarioDAOBancoFicticio;
import com.grupo06.dailygym.usuario.model.Usuario;
import com.grupo06.dailygym.balanca.control.Balanca;
import com.grupo06.dailygym.balanca.control.IBalanca;
import com.grupo06.dailygym.balanca.control.Medida;
import com.grupo06.dailygym.esteira.control.Esteira;
import com.grupo06.dailygym.esteira.control.IEsteira;
import com.grupo06.dailygym.esteira.control.Intensidade;
import com.grupo06.dailygym.esteira.control.Treino;
import com.grupo06.dailygym.esteira.sensores.*;

public class SmartWatch implements ISmartWatch {
	
	private IEsteira esteira;
	private IBalanca balanca;
	private ISmartWatchSensores sensores;
	
	public SmartWatch() {
		sincronizaEsteira("1.xxx.xxx");
		sincronizaBalanca("1.xxx.xxx");
		this.sensores = new SmartWatchSensores();
		
		daemonGetMedicoesBalanca();
		daemonDetectaExercicios();
	}		
		
	@Override
	public boolean sincronizaEsteira(String ipEsteira) {
		this.esteira = Esteira.connectEsteira(ipEsteira);
		return true;
	}

	@Override
	public boolean sincronizaBalanca(String ipBalanca) {
		this.balanca = Balanca.connectBalanca(ipBalanca);
		return true;
	}

	@Override
	public Treino getSugestaoTreino(Intensidade intensidade) {
		UsuarioDAO usuarioDao = UsuarioDAOBancoFicticio.getInstance();
		Usuario usuario = usuarioDao.getUsuario();
		
		DayOfWeek diaAtual = LocalDate.now().getDayOfWeek();
		if(usuario.getDiasDisponiveis().contains(diaAtual)) {
			
			return this.esteira.getSugestaoTreino(usuario.getMetaDiaria(), intensidade);
		}
		else {
			return null;
		}	
	}

	@Override
	public void executaTreino(Treino treino) {
		this.esteira.executaTreino(treino);
	}
	
	private void daemonGetMedicoesBalanca() {
		Thread daemon = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                	ArrayList<Medida> medidas = balanca.getMedicoes();
                	if(!medidas.isEmpty()) {
                		UsuarioDAO usuarioDao = UsuarioDAOBancoFicticio.getInstance();
                		if(usuarioDao.usuarioExiste()) {
                			Usuario usuario = usuarioDao.getUsuario();
                			for(Medida medida : medidas) {
                				usuario.adicionaMedida(medida);
                			}
                			usuarioDao.atualizaUsuario(usuario);
                		}
                	}
                	try {
                		Thread.sleep(1000);
                	} catch(InterruptedException e) {
                		
                	}
                	
                }
            }
		});
		daemon.setDaemon(true);
		daemon.start();
	}
	
	private void daemonDetectaExercicios() {
		Thread daemon = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                	int calorias = sensores.getCaloriasPerdidas();
                	int distancia = sensores.getDistanciaPercorrida();
                	Exercicio exercicio = new Exercicio(calorias, distancia);
            		UsuarioDAO usuarioDao = UsuarioDAOBancoFicticio.getInstance();
            		if(usuarioDao.usuarioExiste()) {
            			Usuario usuario = usuarioDao.getUsuario();
            			usuario.adicionaExercicio(exercicio);
            			usuarioDao.atualizaUsuario(usuario);
            		}        
                	try {
                		Thread.sleep(20000);
                	} catch(InterruptedException e) {
                		
                	}
                }
            }
		});
		daemon.setDaemon(true);
		daemon.start();
	}


	@Override
	public int getBatimentoCardiaco() {
		return this.sensores.getBatimentoCardiaco();
	}
	
	@Override
	public boolean isBatimentoElevado() {
		return this.sensores.getBatimentoCardiaco() > 150;
	}

}
