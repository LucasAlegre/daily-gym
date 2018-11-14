package com.grupo06.dailygym.usuario.DAO;

import java.security.InvalidParameterException;
import java.time.DayOfWeek;
import java.util.Set;

import com.grupo06.dailygym.database.BancoDeDadosFicticio;
import com.grupo06.dailygym.usuario.model.Usuario;

public class UsuarioDAOBancoFicticio implements UsuarioDAO {
	
	private static UsuarioDAOBancoFicticio instance = null;
	private Usuario usuarioNoBD;
	private boolean usuarioExiste = false;
	private BancoDeDadosFicticio bancoDeDados;
	
	public static UsuarioDAOBancoFicticio getInstance(){
		if(instance == null){
			synchronized(UsuarioDAOBancoFicticio.class){
				if(instance == null){
					instance = new UsuarioDAOBancoFicticio();
				}
			}
		}
		return instance;
	}
	
	private UsuarioDAOBancoFicticio() {
		this.bancoDeDados = new BancoDeDadosFicticio();
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
