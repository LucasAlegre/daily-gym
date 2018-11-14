package com.grupo06.dailygym.database;

import java.security.InvalidParameterException;
import java.time.DayOfWeek;
import java.util.Set;

import com.grupo06.dailygym.usuario.Usuario;

public class UsuarioDAOBancoMockado implements UsuarioDAO {
	
	private static UsuarioDAOBancoMockado instance = null;
	private Usuario usuarioNoBD;
	private boolean usuarioExiste = false;
	
	public static UsuarioDAOBancoMockado getInstance(){
		if(instance == null){
			synchronized(UsuarioDAOBancoMockado.class){
				if(instance == null){
					instance = new UsuarioDAOBancoMockado();
				}
			}
		}
		return instance;
	}
	
	private UsuarioDAOBancoMockado() {
		
	}

	@Override
	public Usuario criaUsuario(String nome, int idade, float altura, int metaDiaria, Set<DayOfWeek> diasDisponiveis) throws InvalidParameterException {
		this.usuarioNoBD = new Usuario(nome, idade, altura, metaDiaria, diasDisponiveis);
		usuarioExiste = true;
		return this.usuarioNoBD;
	}

	@Override
	public void atualizaUsuario(Usuario usuario) {
		this.usuarioNoBD = usuario;
	}

	@Override
	public Usuario getUsuario() {
		return usuarioNoBD;
	}

	@Override
	public void apagarHistoricoUsuario() {
		this.usuarioNoBD.deletaHistorico();
	}

	@Override
	public void removeUsuario() {
		this.usuarioNoBD = null;
		this.usuarioExiste = false;
	}

	@Override
	public boolean usuarioExiste() {
		return this.usuarioExiste;
	}
	

}
