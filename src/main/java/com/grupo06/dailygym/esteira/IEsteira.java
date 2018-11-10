package com.grupo06.dailygym.esteira;

import java.util.ArrayList;

public interface IEsteira {
	
	ArrayList<Treino> getSugestaoTreino(int tempo, int calorias, Intensidade intensidade);
	
	void iniciaTreino(Treino treino);
}
