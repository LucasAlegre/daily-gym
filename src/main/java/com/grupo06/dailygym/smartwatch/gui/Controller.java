package com.grupo06.dailygym.smartwatch.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.grupo06.dailygym.smartwatch.facade.SmartWatchFacade;

import javafx.fxml.Initializable;

public class Controller implements Initializable {

	private SmartWatchFacade smartWatch;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		smartWatch = new SmartWatchFacade();
	}
	
	

	

}
