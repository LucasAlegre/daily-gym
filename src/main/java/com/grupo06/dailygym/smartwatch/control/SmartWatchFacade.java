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
	
	
	//TODO : Métodos relacionados ao smart watch
	
	public void criaPerfil(String nome, int idade, float altura, int metaDiaria, Set<DayOfWeek> diasDisponiveis) {
		UsuarioDAO usuarioDao = UsuarioDAOBancoFicticio.getInstance();
		try {
			usuarioDao.criaUsuario(nome, idade, altura, metaDiaria, diasDisponiveis);
		} catch(InvalidParameterException e) {
			System.out.println("Parâmetros para criação do usuário inválidos!");
		}
	}
	
	public void atualizaPerfil(String nome, int idade, float altura, int metaDiaria, Set<DayOfWeek> diasDisponiveis) {
		UsuarioDAO usuarioDao = UsuarioDAOBancoFicticio.getInstance();
		Usuario usuario = usuarioDao.getUsuario();
		try {
			usuario.atualizaDados(nome, idade, altura, metaDiaria, diasDisponiveis);
			usuarioDao.atualizaUsuario(usuario);
		} catch(InvalidParameterException e) {
			System.out.println("Parâmetros para atualização do usuário inválidos!");
		}
	}
	
	public Usuario consultaPerfil() {
		UsuarioDAO usuarioDao = UsuarioDAOBancoFicticio.getInstance();
		Usuario usuario = usuarioDao.getUsuario();
		return usuario;
	}
	
	public void removePerfil() {
		UsuarioDAO usuarioDao = UsuarioDAOBancoFicticio.getInstance();
		usuarioDao.removeUsuario();
	}
	
	public void reiniciarAcompanhamento() {
		UsuarioDAO usuarioDao = UsuarioDAOBancoFicticio.getInstance();
		usuarioDao.apagarHistoricoUsuario();
	}
	
	public Boolean isUserCreated() {
		UsuarioDAO usuarioDao = UsuarioDAOBancoFicticio.getInstance();
		return usuarioDao.usuarioExiste();
	}
}
