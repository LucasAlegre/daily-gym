package com.grupo06.dailygym.balanca.gui;

import java.net.URL;

import java.util.ResourceBundle;

import com.grupo06.dailygym.balanca.Balanca;
import com.grupo06.dailygym.balanca.IBalanca;
import com.grupo06.dailygym.balanca.Medida;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;

public class BalancaViewController implements Initializable {

	@FXML
	TextField aguaLabel;
	
	private IBalanca balanca;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		balanca = new Balanca("ip");
	}
	
	public void onPesarClick(MouseEvent event){
		Medida medida = balanca.realizarMedicao();
		aguaLabel.setText("oi");
	}

	
}
