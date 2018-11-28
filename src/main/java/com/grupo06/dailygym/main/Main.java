package com.grupo06.dailygym.main;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
	        Parent root = FXMLLoader.load(getClass().getResource("SmartWatch.fxml"));
	        Scene scene = new Scene(root);
			
			primaryStage.setTitle("Daily Gym");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			/*
			Parent rootBalanca = FXMLLoader.load(getClass().getResource("Balanca.fxml"));
			Stage balancaStage = new Stage();
			balancaStage.setScene(new Scene(rootBalanca));
			balancaStage.setTitle("Balan�a");
			balancaStage.setX(600);
			balancaStage.show();
			*/
			/*
			Parent rootEsteira = FXMLLoader.load(getClass().getResource("Esteira.fxml"));
			Stage esteiraStage = new Stage();
			esteiraStage.setScene(new Scene(rootEsteira));
			esteiraStage.setX(1100);
			esteiraStage.setTitle("Esteira");
			esteiraStage.show();
			*/
			
			Parent rootTreino = FXMLLoader.load(getClass().getResource("Treino.fxml"));
			Stage treinoStage = new Stage();
			treinoStage.setScene(new Scene(rootTreino));
			treinoStage.setTitle("Treino");
			treinoStage.setX(1100);
			treinoStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
