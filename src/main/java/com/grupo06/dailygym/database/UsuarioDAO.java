package com.grupo06.dailygym.database;

import java.time.DayOfWeek;
import java.util.Set;

import com.grupo06.dailygym.usuario.Usuario;

public interface UsuarioDAO {

	public Usuario criaUsuario(String nome, int idade, float altura, int metaDiaria, Set<DayOfWeek> diasDisponiveis);
	
	public void atualizaUsuario(Usuario usuario);
	
	public void removeUsuario();
	
	public Usuario getUsuario();
	
	public void apagarHistoricoUsuario();
	
	public boolean usuarioExiste();
}
