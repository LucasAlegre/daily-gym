package com.grupo06.dailygym.smartwatch.view;

import java.net.URL;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.grupo06.dailygym.balanca.control.Medida;
import com.grupo06.dailygym.smartwatch.control.SmartWatchFacade;
import com.grupo06.dailygym.usuario.DAO.UsuarioDAO;
import com.grupo06.dailygym.usuario.DAO.UsuarioDAOBancoFicticio;
import com.grupo06.dailygym.usuario.model.Usuario;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
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
    
    @FXML
    private TextField batimentoField;

	private SmartWatchFacade smartWatchFacade;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		smartWatchFacade = new SmartWatchFacade();
		daemonBatimentoCardiaco();
	}
	
    @FXML
    void onGerenciarPerfilClick(ActionEvent event) {
    	updateButtonsStatus();
    }

    @FXML
    void onReiniciarAcompanhamentoClick(ActionEvent event) {

    }

    @FXML
    void onTreinoCustomizadoClick(ActionEvent event) {
    	
    }
    
    @FXML
    void onSugestaoDeTreinoClick(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Sugest�o de Treino");
    	alert.setHeaderText("Qual a intensidade?");
    	alert.setContentText("Leve, moderado ou intenso:");

    	ButtonType buttonTypeOne = new ButtonType("LEVE");
    	ButtonType buttonTypeTwo = new ButtonType("MODERADO");
    	ButtonType buttonTypeThree = new ButtonType("INTENSO");
    	ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

    	alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == buttonTypeOne){
    	    // ... user chose "One"
    	} else if (result.get() == buttonTypeTwo) {
    	    // ... user chose "Two"
    	} else if (result.get() == buttonTypeThree) {
    	    // ... user chose "Three"
    	} else {
    	    // ... user chose CANCEL or closed the dialog
    	}
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
        TextField meta = new TextField();
        meta.setPromptText("Meta Di�ria (cal)");

        grid.add(new Label("Nome"), 0, 0);
        grid.add(nome, 1, 0);
        grid.add(new Label("Idade"), 0, 1);
        grid.add(idade, 1, 1);
        grid.add(new Label("Altura"), 0, 2);
        grid.add(altura, 1, 2);
        grid.add(new Label("Meta Di�ria (cal)"), 0, 3);
        grid.add(meta, 1, 3);

        grid.add(new Label("Dias dispon�veis para treino:"), 0, 4);
        HashMap<DayOfWeek, CheckBox> diasDisponiveis = new HashMap<DayOfWeek, CheckBox>();
        diasDisponiveis.put(DayOfWeek.MONDAY, new CheckBox("Segunda"));
        diasDisponiveis.put(DayOfWeek.TUESDAY, new CheckBox("Ter�a"));
        diasDisponiveis.put(DayOfWeek.WEDNESDAY, new CheckBox("Quarta"));
        diasDisponiveis.put(DayOfWeek.THURSDAY, new CheckBox("Quinta"));
        diasDisponiveis.put(DayOfWeek.FRIDAY, new CheckBox("Sexta"));
        diasDisponiveis.put(DayOfWeek.SATURDAY, new CheckBox("S�bado"));
        diasDisponiveis.put(DayOfWeek.SUNDAY, new CheckBox("Domingo"));
        grid.add(diasDisponiveis.get(DayOfWeek.MONDAY), 0, 5);
        grid.add(diasDisponiveis.get(DayOfWeek.TUESDAY), 1, 5);
        grid.add(diasDisponiveis.get(DayOfWeek.WEDNESDAY), 2, 5);
        grid.add(diasDisponiveis.get(DayOfWeek.THURSDAY), 0, 6);
        grid.add(diasDisponiveis.get(DayOfWeek.FRIDAY), 1, 6);
        grid.add(diasDisponiveis.get(DayOfWeek.SATURDAY), 2, 6);
        grid.add(diasDisponiveis.get(DayOfWeek.SUNDAY), 0, 7);

        dialog.getDialogPane().setContent(grid);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        final Button btOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        btOk.addEventFilter(
            ActionEvent.ACTION, 
            evento -> {
                // Check whether some conditions are fulfilled
                if (!validaNome(nome.getText()) || !validaIdade(idade.getText()) || !validaMetaDiaria(meta.getText()) ||
                	!validaAltura(altura.getText()) || !validaDiasDiponiveis(diasDisponiveis)) {
                    // The conditions are not fulfilled so we consume the event to prevent the dialog to close
                    evento.consume();
                }
            }
        );

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new String[]{nome.getText(), idade.getText(), altura.getText(), meta.getText()};
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
        	int metaPerfil = Integer.parseInt(result.get()[3]);
        	
        	smartWatchFacade.criaPerfil(nomePerfil, idadePerfil, alturaPerfil, metaPerfil, diasDiponiveisPerfil);
        	
            Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
            dialogoResultado.setHeaderText("Aviso");
            dialogoResultado.setContentText("Use a Balan�a para continuar!");
            dialogoResultado.show();
            
            updateButtonsStatus();
        }
    }
    
    private Boolean validaNome(String nome) {
    	if(nome.length() >= 100) {
    		return false;
    	}
    	String regx = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nome);
        return matcher.find();
    }
    
    private Boolean validaIdade(String idadeString) {
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
    
    private Boolean validaAltura(String alturaString) {
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
    
    private Boolean validaMetaDiaria(String metaDiaria) {
    	try {
    		int meta = Integer.parseInt(metaDiaria);
        	if(meta > 0)
        		return true;
        	else
        		return false;
    	} catch(NumberFormatException e) {
    		return false;
    	}
    }
    
    private Boolean validaDiasDiponiveis(HashMap<DayOfWeek, CheckBox> diasDisponiveis) {
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
    	Usuario usuario = smartWatchFacade.consultaPerfil();
    	mostrarUsuario(usuario);
    }

    @FXML
    void onRemoverBt(ActionEvent event) {
        // TODO: Alert tem certeza?
    	smartWatchFacade.removePerfil();
    	updateButtonsStatus();
    }
    
    private void updateButtonsStatus() {
    	boolean isUserCreated = smartWatchFacade.isUserCreated();	
		criarBt.setDisable(isUserCreated);
		consultarBt.setDisable(!isUserCreated);
		removerBt.setDisable(!isUserCreated);
		atualizarBt.setDisable(!isUserCreated);
    }
    
    private void mostrarUsuario(Usuario usuario) {
    	Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Consulta do Perfil");
        dialog.setHeaderText("Dados:");
        dialog.initModality(Modality.WINDOW_MODAL);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Nome: " + usuario.getNome()), 0, 0);
        grid.add(new Label("Idade: " + usuario.getIdade()), 0, 1);
        grid.add(new Label("Altura: " + usuario.getAltura()), 0, 2);
        grid.add(new Label("Meta Di�ria (cal): " + usuario.getMetaDiaria()), 0, 3);
        grid.add(new Label("IMC: " + usuario.getIMC()), 0, 4);
        grid.add(new Label("Peso (Kg): " + usuario.getPesoAtual()), 0, 5);
        grid.add(new Label("�gua (%): " + usuario.getPorcentualAguaAtual()), 0, 6);
        grid.add(new Label("Gordura (%): " + usuario.getPorcentualGorduraAtual()), 0, 7);

        grid.add(new Label("Dias dispon�veis para treino:"), 0, 8);
        String dias = "";
        for(DayOfWeek day : usuario.getDiasDisponiveis()) {
        	dias += day.toString() + " ";
        }
        grid.add(new Label(dias), 0, 9);
        grid.add(new Label("Dist�ncia Percoridda Hoje (Km): " + usuario.getDistanciaPercorridaHoje()/1000.0), 0, 10);

        dialog.getDialogPane().setContent(grid);
        dialog.show();

    }
    
    private void daemonBatimentoCardiaco() {
    	Thread daemon = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                	
                	int batimentoCardiaco = smartWatchFacade.getBatimentoCardiaco();
                	batimentoField.setText(Integer.toString(batimentoCardiaco));
                	
                	if(smartWatchFacade.isBatimentoElevado()) {
                		Platform.runLater(new Runnable() {
                			@Override
                			public void run() {
	                            Alert dialogoResultado = new Alert(Alert.AlertType.WARNING);
	                            dialogoResultado.setHeaderText("Batimento Card�aco Elevado!");
	                            dialogoResultado.setContentText("Por favor, procure um m�dico.");
	                            dialogoResultado.showAndWait();
                			}
                		});
                	}
	            	try {
	            		Thread.sleep(3000);
	            	} catch(InterruptedException e) {
	            		
	            	}              	
                }   
            }
		});
		daemon.setDaemon(true);
		daemon.start();
    }

}
