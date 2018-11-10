package com.grupo06.dailygym.smartwatch.facade;

import java.security.InvalidParameterException;
import java.time.DayOfWeek;
import java.util.Set;

import com.grupo06.dailygym.smartwatch.*;
import com.grupo06.dailygym.usuario.Usuario;

public class SmartWatchFacade {
	
	private ISmartWatch smartWatch;
	
	public SmartWatchFacade(){
		smartWatch = new SmartWatch();
	}
	
	public void criaPerfil(String nome, int idade, float altura, Set<DayOfWeek> diasDisponiveis) {
		Usuario usuario = Usuario.getInstance();
		try {
			usuario.criaUsario(nome, idade, altura, diasDisponiveis);
		} catch(InvalidParameterException e) {
			System.out.println("Parametros para criação do usuário inválidas!");
		}
	}
	
	public Boolean isUserCreated() {
		return Usuario.getInstance().isCreated();
	}
}
