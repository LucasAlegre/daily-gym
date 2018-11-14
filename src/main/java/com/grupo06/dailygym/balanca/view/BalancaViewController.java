package com.grupo06.dailygym.balanca.view;

import java.net.URL;

import java.util.ResourceBundle;

import com.grupo06.dailygym.balanca.control.Balanca;
import com.grupo06.dailygym.balanca.control.IBalanca;
import com.grupo06.dailygym.balanca.control.Medida;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class BalancaViewController implements Initializable {

    @FXML
    private TextField pesoField;

    @FXML
    private TextField gorduraField;

    @FXML
    private TextField aguaField;
    
	private IBalanca balanca;
	
    @FXML
    void onPesarClick(ActionEvent event) {
    	
    	Medida medida = balanca.realizarMedicao();
    	
    	pesoField.setText(Integer.toString(medida.getPeso()));
    	gorduraField.setText(Integer.toString(medida.getPorcentagemAgua()));
    	aguaField.setText(Integer.toString(medida.getPorcentagemGordura()));
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		balanca = Balanca.connectBalanca("1.xxx.xxx");
	}

	
}
