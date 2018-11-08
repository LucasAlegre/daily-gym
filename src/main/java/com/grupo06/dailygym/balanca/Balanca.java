package com.grupo06.dailygym.balanca;

import com.grupo06.dailygym.usuario.Usuario;

public class Balanca implements IBalanca {
	
	private String ip;
	
	public Balanca(String ip){
		this.ip = ip;
	}

	@Override
	public Medida realizarMedicao() {
		Medida medida = new Medida();
		Usuario usuario = Usuario.getInstance();
		usuario.addNovaMedida(medida);
		
		return medida;
	}
	
}
