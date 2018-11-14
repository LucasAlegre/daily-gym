package com.grupo06.dailygym.smartwatch.control;

import java.time.DayOfWeek;
import java.util.Set;

import com.grupo06.dailygym.esteira.Esteira;
import com.grupo06.dailygym.esteira.IEsteira;
import com.grupo06.dailygym.esteira.Treino;
import com.grupo06.dailygym.usuario.model.Usuario;
import com.grupo06.dailygym.balanca.control.Balanca;
import com.grupo06.dailygym.balanca.control.IBalanca;
import com.grupo06.dailygym.esteira.*;

public class SmartWatch implements ISmartWatch {
	
	private IEsteira esteira;
	private IBalanca balanca;
	
	public SmartWatch() {
		sincronizaEsteira("1.xxx.xxx");
		sincronizaBalanca("1.xxx.xxx");
	}

	@Override
	public boolean sincronizaEsteira(String ipEsteira) {
		esteira = Esteira.connectEsteira(ipEsteira);
		return true;
	}

	@Override
	public boolean sincronizaBalanca(String ipBalanca) {
		balanca = Balanca.connectBalanca(ipBalanca);
		return true;
	}

	@Override
	public Treino getSugestaoTreino() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void iniciaTreino() {
		// TODO Auto-generated method stub
		
	}

}
