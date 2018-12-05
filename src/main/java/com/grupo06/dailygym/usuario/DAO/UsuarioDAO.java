package com.grupo06.dailygym.usuario.DAO;

import java.time.DayOfWeek;
import java.util.Set;

import com.grupo06.dailygym.usuario.model.Usuario;

public interface UsuarioDAO {

	/**
	 * Cria usu�rio no Banco de Dados
	 * @param nome
	 * @param idade
	 * @param altura
	 * @param metaDiaria
	 * @param diasDisponiveis
	 * @return Modelo do usu�rio criado
	 */
	public Usuario criaUsuario(String nome, int idade, float altura, int metaDiaria, Set<DayOfWeek> diasDisponiveis);
	
	/**
	 * Atualiza usu�rio no banco de dados com base nos dados do modelo passado
	 * @param usuario Usu�rio atualizado
	 */
	public void atualizaUsuario(Usuario usuario);
	
	/**
	 * Apaga usu�rio do Banco de Dados
	 */
	public void removeUsuario();
	
	/**
	 * Retorna usu�rio no Banco de Dados
	 * @return
	 */
	public Usuario getUsuario();
	
	/**
	 * Apaga hist�rico de exerc�cios e medidas do Banco de Dados
	 */
	public void apagarHistoricoUsuario();
	
	/**
	 * Checa se o usu�rio est� criado no Banco de Dados
	 * @return True se usu�rio existe, falso caso contr�rio
	 */
	public boolean usuarioExiste();
}
