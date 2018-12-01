package com.grupo06.dailygym.esteira.view;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import com.grupo06.dailygym.balanca.control.Medida;
import com.grupo06.dailygym.esteira.control.Esteira;
import com.grupo06.dailygym.esteira.control.Exercicio;
import com.grupo06.dailygym.esteira.control.IEsteira;
import com.grupo06.dailygym.esteira.control.Treino;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;

public class EsteiraViewController implements Initializable {

    @FXML
    private TextField velocidadeField;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private TextField tempoField;
    
    private Button iniciarBt;
    
    private IEsteira esteira;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.esteira = Esteira.connectEsteira("1.xxx.xxx");
	}

	public void onIniciarClick(){
		try{
			Treino treino = esteira.iniciarTreino();
			DayOfWeek diaAtual = LocalDate.now().getDayOfWeek();
			if(diaAtual == treino.getData().getDayOfWeek()) {
    			velocidadeField.setText(Float.toString(treino.getVelocidade()));
    			tempoField.setText(Integer.toString(treino.getTempo()));
    			 		
    			Service servico = new Service() {
                    @Override
                    protected Task createTask() {
                        return new Task() {
                            @Override
                            protected Void call() throws Exception {
                                Thread.sleep(300);
                                updateProgress(1, 10);
                                for (int i = 0; i < 10; i++) {
                                    updateProgress(i + 1, 10);
                                    Thread.sleep(300);
                                }
                                return null;
                            }
                        };
                    }
                };
                progressBar.progressProperty().bind(servico.progressProperty());
                servico.restart();  

                Exercicio exercicio = esteira.finalizarTreino(treino);
                mostrarExercicio(exercicio);

			}
			else{
				mostrarAviso("Nenhum treino agendado para hoje!");
			}
	    }
    	catch (IndexOutOfBoundsException e){
    		mostrarAviso("Nenhum treino agendado!");
    	}
		catch (NullPointerException e){
			
		}
	}
	
	private void mostrarAviso(String mensagem){
		Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
        dialogoResultado.setHeaderText("Aviso");
        dialogoResultado.setContentText(mensagem);
        dialogoResultado.show();
	}
	
	private void mostrarExercicio(Exercicio exercicio){
		Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Exercício");
        dialog.setHeaderText("O treino de agora teve:");
        dialog.initModality(Modality.WINDOW_MODAL);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Calorias queimadas (kcal): " + exercicio.getCaloriasQueimadas()), 0, 0);
        grid.add(new Label("Distância percorrida (Km): " + exercicio.getDistanciaPercorrida()/1000.0), 0, 1);
        
        
        dialog.getDialogPane().setContent(grid);
        dialog.show();
	}
}
