package com.grupo06.dailygym.smartwatch.control;

import java.time.DayOfWeek;
import java.util.Set;

import com.grupo06.dailygym.balanca.control.IBalanca;
import com.grupo06.dailygym.esteira.control.IEsteira;
import com.grupo06.dailygym.esteira.control.Intensidade;
import com.grupo06.dailygym.esteira.control.Treino;
import com.grupo06.dailygym.usuario.model.Usuario;

public interface ISmartWatch {
	
	/**
	 * Conecta o smart watch com o dispositivo Esteira.
	 * @param ipEsteira Endere�o ip da Esteira
	 * @return True se conex�o foi realizada com sucesso
	 */
	boolean sincronizaEsteira(String ipEsteira);
	
	/**
	 * Conecta o smart watch com o dispositivo Balan�a.
	 * @param ipEsteira Endere�o ip da Balan�a
	 * @return True se conex�o foi realizada com sucesso
	 */
	boolean sincronizaBalanca(String ipBalanca);
	
	/**
	 * Retorna o batimento card�aco atual do usu�rio em bpm
	 * @return batimento card�aco (bpm)
	 */
	int getBatimentoCardiaco();
	
	/**
	 * Checa se batimento card�aco est� elevado e usu�rio deve procurar m�dico
	 * @return True se batimento elevado
	 */
	boolean isBatimentoElevado();
	
	/**
	 * Pede � esteira que sugira um treino
	 * @param intensidade Intensidade do treino sugerido
	 * @return Treino sugerido
	 */
	Treino sugerirTreino(Intensidade intensidade);
	
	/**
	 * Adiciona treino na lista de treinos agendados
	 * @param treino a ser agendado
	 */
	void agendarTreino(Treino treino);
		
	/**
	 * Agenda um treino customizado na Esteira
	 * @param tempo Dura��o do treino
	 * @param velocidades Velocidades das etapas do treino
	 */
	void setTreinoCustomizado(int tempo, float[] velocidades);
	
}
