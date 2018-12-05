package com.grupo06.dailygym.esteira.control;

import java.util.ArrayList;

import com.grupo06.dailygym.balanca.control.Medida;
import com.grupo06.dailygym.usuario.model.Usuario;

public interface IEsteira {
	
	/**
	 * Sugere um novo treino
	 * @param usuario Informa��es do usu�rio
	 * @param intensidade Intensidade do treino a ser sugerido
	 * @return
	 */
	public Treino sugerirTreino(Usuario usuario, Intensidade intensidade);
	
	/**
	 * Adiciona treino na lista de treinos agendados
	 * @param treino
	 */
	public void agendarTreino(Treino treino);
	
	public void setTreinoCustomizado(int tempo, float[] velocidades);
	
	/**
	 * Executa o treino mais recente agendado e retorna o treino.
	 * @return Treino executado
	 */
	public Treino iniciarTreino();
	
	/**
	 * Finaliza o treino, retorna um objeto Exerc�cio com informa��es de caloridas e dist�ncia percorridas do treino
	 * @param treino Treino executado
	 * @return Informa��es referentes ao exerc�cio gerado durante o treino
	 */
	public Exercicio finalizarTreino(Treino treino);
	
	/**
	 * Retorna os exerc�cios guardados na esteira que o Smart Watch ir� ler.
	 * @return Lista de exerc�cios armazenados
	 */
	public ArrayList<Exercicio> getExercicios();
}
