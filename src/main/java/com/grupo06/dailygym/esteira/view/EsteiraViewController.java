package com.grupo06.dailygym.esteira.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.grupo06.dailygym.esteira.control.Esteira;
import com.grupo06.dailygym.esteira.control.IEsteira;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class EsteiraViewController implements Initializable {

    @FXML
    private TextField velocidade;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private TextField tempo;
    
    private IEsteira esteira;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.esteira = Esteira.connectEsteira("1.xxx.xxx");
	}

}
