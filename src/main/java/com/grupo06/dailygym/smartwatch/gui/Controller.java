package com.grupo06.dailygym.smartwatch.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.grupo06.dailygym.smartwatch.controle.ISmartWatch;
import com.grupo06.dailygym.smartwatch.controle.SmartWatchControl;
import javafx.fxml.Initializable;

public class Controller implements Initializable {

	private ISmartWatch smartWatch;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		smartWatch = new SmartWatchControl();
		
	}


}
