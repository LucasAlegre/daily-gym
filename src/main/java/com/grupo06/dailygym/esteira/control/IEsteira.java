package com.grupo06.dailygym.esteira.control;

import java.util.ArrayList;

import com.grupo06.dailygym.balanca.control.Medida;
import com.grupo06.dailygym.usuario.model.Usuario;

public interface IEsteira {
	public Treino sugerirTreino( Usuario usuario, Intensidade intensidade);
	
	public void agendarTreino(Treino treino);
	
	public void setTreinoCustomizado(int tempo, float[] velocidades);
	
	public Treino iniciarTreino();
	
	public Exercicio finalizarTreino(Treino treino);
	
	public ArrayList<Exercicio> getExercicios();
}
