package com.grupo06.dailygym.smartwatch.gui;

import java.net.URL;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.grupo06.dailygym.smartwatch.facade.SmartWatchFacade;
import com.grupo06.dailygym.usuario.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;

public class SmartWatchViewController implements Initializable {
	
    @FXML
    private Button consultarBt;

    @FXML
    private Button criarBt;

    @FXML
    private Button atualizarBt;

    @FXML
    private Button removerBt;

	private SmartWatchFacade smartWatchFacade;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		smartWatchFacade = new SmartWatchFacade();
	}
	
    @FXML
    void onGerenciarPerfilClick(ActionEvent event) {
    	updateButtonsStatus();
    }

    @FXML
    void onReiniciarAcompanhamentoClick(ActionEvent event) {

    }

    @FXML
    void onSugestaoDeTreinoClick(ActionEvent event) {

    }

    @FXML
    void onCriarClick(ActionEvent event) {
    	Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("Criar Perfil");
        dialog.setHeaderText("Preencha todos os campos:");
        dialog.initModality(Modality.WINDOW_MODAL);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nome = new TextField();
        nome.setPromptText("Nome");
        TextField idade = new TextField();
        idade.setPromptText("Idade");
        TextField altura = new TextField();
        altura.setPromptText("Altura");

        grid.add(new Label("Nome"), 0, 0);
        grid.add(nome, 1, 0);
        grid.add(new Label("Idade"), 0, 1);
        grid.add(idade, 1, 1);
        grid.add(new Label("Altura"), 0, 2);
        grid.add(altura, 1, 2);

        grid.add(new Label("Dias disponíveis para treino:"), 0, 3);
        HashMap<DayOfWeek, CheckBox> diasDisponiveis = new HashMap<DayOfWeek, CheckBox>();
        diasDisponiveis.put(DayOfWeek.MONDAY, new CheckBox("Segunda"));
        diasDisponiveis.put(DayOfWeek.TUESDAY, new CheckBox("Terça"));
        diasDisponiveis.put(DayOfWeek.WEDNESDAY, new CheckBox("Quarta"));
        diasDisponiveis.put(DayOfWeek.THURSDAY, new CheckBox("Quinta"));
        diasDisponiveis.put(DayOfWeek.FRIDAY, new CheckBox("Sexta"));
        diasDisponiveis.put(DayOfWeek.SATURDAY, new CheckBox("Sábado"));
        diasDisponiveis.put(DayOfWeek.SUNDAY, new CheckBox("Domingo"));
        grid.add(diasDisponiveis.get(DayOfWeek.MONDAY), 0, 4);
        grid.add(diasDisponiveis.get(DayOfWeek.TUESDAY), 1, 4);
        grid.add(diasDisponiveis.get(DayOfWeek.WEDNESDAY), 2, 4);
        grid.add(diasDisponiveis.get(DayOfWeek.THURSDAY), 0, 5);
        grid.add(diasDisponiveis.get(DayOfWeek.FRIDAY), 1, 5);
        grid.add(diasDisponiveis.get(DayOfWeek.SATURDAY), 2, 5);
        grid.add(diasDisponiveis.get(DayOfWeek.SUNDAY), 0, 6);

        dialog.getDialogPane().setContent(grid);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        final Button btOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        btOk.addEventFilter(
            ActionEvent.ACTION, 
            evento -> {
                // Check whether some conditions are fulfilled
                if (!validaNome(nome.getText()) || !validaIdade(idade.getText()) || !validaAltura(altura.getText()) || !validaDiasDiponiveis(diasDisponiveis)) {
                    // The conditions are not fulfilled so we consume the event to prevent the dialog to close
                    evento.consume();
                }
            }
        );

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new String[]{nome.getText(), idade.getText(), altura.getText()};
            }
            return null;
        });

        Optional<String[]> result = dialog.showAndWait();
        Set<DayOfWeek> diasDiponiveisPerfil = new HashSet<DayOfWeek>();
        for(Entry<DayOfWeek, CheckBox> e : diasDisponiveis.entrySet()) {
        	if (e.getValue().isSelected()) {
        		diasDiponiveisPerfil.add(e.getKey());
        	}
        }
        if(result.isPresent()) {
        	String nomePerfil = result.get()[0];
        	int idadePerfil = Integer.parseInt(result.get()[1]);
        	float alturaPerfil = Float.parseFloat(result.get()[2]);
        	
        	smartWatchFacade.criaPerfil(nomePerfil, idadePerfil, alturaPerfil, diasDiponiveisPerfil);
        	
            Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
            dialogoResultado.setHeaderText("Aviso");
            dialogoResultado.setContentText("Use a Balança para continuar!");
            dialogoResultado.show();
            
            updateButtonsStatus();
        }
    }
    
    Boolean validaNome(String nome) {
    	if(nome.length() >= 100) {
    		return false;
    	}
    	String regx = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nome);
        return matcher.find();
    }
    
    Boolean validaIdade(String idadeString) {
    	try {
    		int idade = Integer.parseInt(idadeString);
        	if(idade > 0 && idade < 100)
        		return true;
        	else
        		return false;
    	} catch(NumberFormatException e) {
    		return false;
    	}
    }
    
    Boolean validaAltura(String alturaString) {
    	try {
    		float altura = Float.parseFloat(alturaString);
        	if(altura > 0.0 && altura < 3.0)
        		return true;
        	else
        		return false;
    	} catch(NumberFormatException e) {
    		return false;
    	}
    }
    
    Boolean validaDiasDiponiveis(HashMap<DayOfWeek, CheckBox> diasDisponiveis) {
    	for(CheckBox c : diasDisponiveis.values()) {
    		if(c.isSelected())
    			return true;
    	}
    	// Nenhum dia marcado
    	return false;
    }

    @FXML
    void onAtualizarClick(ActionEvent event) {

    }

    @FXML
    void onConsultarClick(ActionEvent event) {

    }

    @FXML
    void onRemoverBt(ActionEvent event) {
    	
    	updateButtonsStatus();
    }
    
    void updateButtonsStatus() {
    	boolean isUserCreated = smartWatchFacade.isUserCreated();	
		criarBt.setDisable(isUserCreated);
		consultarBt.setDisable(!isUserCreated);
		removerBt.setDisable(!isUserCreated);
		atualizarBt.setDisable(!isUserCreated);
    }
    
    void disableButtons() {
    	criarBt.setDisable(true);
    	consultarBt.setDisable(true);
    	atualizarBt.setDisable(true);
    	removerBt.setDisable(true);
    }

}
