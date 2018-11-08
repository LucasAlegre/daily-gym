package com.grupo06.dailygym.usuario;

import com.grupo06.dailygym.balanca.Medida;
import com.grupo06.dailygym.esteira.Exercicio;

public interface IUsuario {
	
	float getIMC();
	
	void deletaHistorico();
	
	void adicionaMedida(Medida medida);
	
	void adicionaExercicio(Exercicio exercicio);
	
	
}
