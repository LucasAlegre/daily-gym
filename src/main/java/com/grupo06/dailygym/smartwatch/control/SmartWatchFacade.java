package com.grupo06.dailygym.smartwatch.control;

import java.security.InvalidParameterException;
import java.time.DayOfWeek;
import java.util.Set;

import com.grupo06.dailygym.smartwatch.control.*;
import com.grupo06.dailygym.usuario.DAO.UsuarioDAO;
import com.grupo06.dailygym.usuario.DAO.UsuarioDAOBancoFicticio;
import com.grupo06.dailygym.usuario.model.Usuario;

public class SmartWatchFacade {
	
	private ISmartWatch smartWatch;
	
	public SmartWatchFacade(){
		smartWatch = new SmartWatch();
	}
	
	public int getBatimentoCardiaco() {
		return this.smartWatch.getBatimentoCardiaco();
	}
	
	public void criaPerfil(String nome, int idade, float altura, int metaDiaria, Set<DayOfWeek> diasDisponiveis) {
		UsuarioDAO usuarioDao = UsuarioDAOBancoFicticio.getInstance();
		try {
			usuarioDao.criaUsuario(nome, idade, altura, metaDiaria, diasDisponiveis);
		} catch(InvalidParameterException e) {
			System.out.println("Parametros para criação do usuário inválidas!");
		}
	}
	
	public Boolean isUserCreated() {
		UsuarioDAO usuarioDao = UsuarioDAOBancoFicticio.getInstance();
		return usuarioDao.usuarioExiste();
	}
}
