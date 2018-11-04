package com.grupo06.dailygym.database;

import com.grupo06.dailygym.usuario.Usuario;

public interface IDatabase {
	
	void salvarUsuario(Usuario usuario);
	
	void deletarUsuario();
	
	Usuario getUsuario();
	
	void deletarHistorico();
}
