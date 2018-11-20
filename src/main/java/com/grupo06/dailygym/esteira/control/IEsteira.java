package com.grupo06.dailygym.esteira.control;

import java.util.ArrayList;

public interface IEsteira {
	
	Treino getSugestaoTreino(int caloriasParaQueimar, Intensidade intensidade);
	
	void executaTreino(Treino treino);
}
