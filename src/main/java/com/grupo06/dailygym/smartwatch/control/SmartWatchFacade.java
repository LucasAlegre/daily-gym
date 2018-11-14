package com.grupo06.dailygym.smartwatch.control;

import java.security.InvalidParameterException;
import java.time.DayOfWeek;
import java.util.Set;

import com.grupo06.dailygym.database.UsuarioDAO;
import com.grupo06.dailygym.database.UsuarioDAOBancoMockado;
import com.grupo06.dailygym.smartwatch.control.*;
import com.grupo06.dailygym.usuario.Usuario;

public class SmartWatchFacade {
	
	private ISmartWatch smartWatch;
	
	public SmartWatchFacade(){
		smartWatch = new SmartWatch();
	}
	
	public void criaPerfil(String nome, int idade, float altura, int metaDiaria, Set<DayOfWeek> diasDisponiveis) {
		UsuarioDAOBancoMockado usuarioDB = UsuarioDAOBancoMockado.getInstance();
		try {
			usuarioDB.criaUsuario(nome, idade, altura, metaDiaria, diasDisponiveis);
		} catch(InvalidParameterException e) {
			System.out.println("Parametros para criação do usuário inválidas!");
		}
	}
	
	public Boolean isUserCreated() {
		UsuarioDAOBancoMockado usuarioDB = UsuarioDAOBancoMockado.getInstance();
		return usuarioDB.usuarioExiste();
	}
}
