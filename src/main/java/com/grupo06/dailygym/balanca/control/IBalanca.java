package com.grupo06.dailygym.balanca.control;

import java.util.ArrayList;

public interface IBalanca {

	public ArrayList<Medida> getMedicoes();
	
	public Medida realizarMedicao();
}
