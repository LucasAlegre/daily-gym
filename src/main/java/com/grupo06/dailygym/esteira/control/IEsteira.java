package com.grupo06.dailygym.esteira.control;

import java.util.ArrayList;

import com.grupo06.dailygym.balanca.control.Medida;
import com.grupo06.dailygym.usuario.model.Usuario;

public interface IEsteira {
	
	/**
	 * Sugere um novo treino
	 * @param usuario Informações do usuário
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
	 * Finaliza o treino, retorna um objeto Exercício com informações de caloridas e distância percorridas do treino
	 * @param treino Treino executado
	 * @return Informações referentes ao exercício gerado durante o treino
	 */
	public Exercicio finalizarTreino(Treino treino);
	
	/**
	 * Retorna os exercícios guardados na esteira que o Smart Watch irá ler.
	 * @return Lista de exercícios armazenados
	 */
	public ArrayList<Exercicio> getExercicios();
}
