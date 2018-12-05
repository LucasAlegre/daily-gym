package com.grupo06.dailygym.usuario.DAO;

import java.time.DayOfWeek;
import java.util.Set;

import com.grupo06.dailygym.usuario.model.Usuario;

public interface UsuarioDAO {

	/**
	 * Cria usuário no Banco de Dados
	 * @param nome
	 * @param idade
	 * @param altura
	 * @param metaDiaria
	 * @param diasDisponiveis
	 * @return Modelo do usuário criado
	 */
	public Usuario criaUsuario(String nome, int idade, float altura, int metaDiaria, Set<DayOfWeek> diasDisponiveis);
	
	/**
	 * Atualiza usuário no banco de dados com base nos dados do modelo passado
	 * @param usuario Usuário atualizado
	 */
	public void atualizaUsuario(Usuario usuario);
	
	/**
	 * Apaga usuário do Banco de Dados
	 */
	public void removeUsuario();
	
	/**
	 * Retorna usuário no Banco de Dados
	 * @return
	 */
	public Usuario getUsuario();
	
	/**
	 * Apaga histórico de exercícios e medidas do Banco de Dados
	 */
	public void apagarHistoricoUsuario();
	
	/**
	 * Checa se o usuário está criado no Banco de Dados
	 * @return True se usuário existe, falso caso contrário
	 */
	public boolean usuarioExiste();
}
