package com.centrovaccinale.centrovaccinale.grafica.cittadino.registraevento.controller;

import com.centrovaccinale.centrovaccinale.entita.EventoAvverso;
import com.centrovaccinale.centrovaccinale.grafica.home.main.HomeApplication;
import com.centrovaccinale.centrovaccinale.rmi.Server;
import com.centrovaccinale.centrovaccinale.utils.LoadStage;
import com.centrovaccinale.centrovaccinale.utils.Login;
import com.centrovaccinale.centrovaccinale.utils.RunnerRMI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegistraEventoController implements Initializable, EventHandler<KeyEvent> {
    private String nomeCentro;
    private String tipoEventoSelezionato;
    private int severita;

    @FXML
    private RadioButton registrazione_evento_severita_1;
    @FXML
    private RadioButton registrazione_evento_severita_2;
    @FXML
    private RadioButton registrazione_evento_severita_3;
    @FXML
    private RadioButton registrazione_evento_severita_4;
    @FXML
    private RadioButton registrazione_evento_severita_5;
    @FXML
    private TextField registrazione_evento_nomeCentro;
    @FXML
    private ChoiceBox<Object> registrazione_evento_menuTipoEvento;
    @FXML
    private ToggleGroup registrazione_evento_toggleSeverita;
    @FXML
    private TextArea registrazione_evento_notaEvento;
    @FXML
    private Button registraEventoBtn;

    @FXML
    private Label errorLabel;

    @FXML
    private void registraEventoAvverso(ActionEvent actionEvent){
        try{
            if(severita > 0 && tipoEventoSelezionato != null){
                errorLabel.setText("");
                EventoAvverso eventoAvverso = new EventoAvverso(nomeCentro, tipoEventoSelezionato, severita, registrazione_evento_notaEvento.getText());
                RunnerRMI runnerRMI;
                if(RunnerRMI.tryConnectionServer()){
                    runnerRMI = RunnerRMI.getInstance();
                    Server server = runnerRMI.getServer();
                    if(server.registraEventoAvverso(eventoAvverso, runnerRMI.getClient().getRef().remoteToString())){
                        registrazione_evento_menuTipoEvento.setValue("Seleziona tipo evento");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Registrazione andata a buon fine!");
                        alert.setContentText("Registrazione evento avverso andata a buon fine!");
                        alert.showAndWait();
                    }
                }
            }else if(tipoEventoSelezionato == null){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Selezionare un tipo di evento!");
            }else if(registrazione_evento_toggleSeverita.getSelectedToggle() == null){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Selezionare un grado di severita'!");
            }
        }
        catch (NotBoundException | RemoteException e) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText(e.getCause().getMessage());
            try {
                if(!RunnerRMI.tryConnectionServer()){
                    RunnerRMI.getInstance().setServer(null);
                }
            } catch (NotBoundException | RemoteException ex) {
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText(e.getCause().getMessage());
                System.err.println(e.getMessage());
            }
        }
        catch (SQLException e) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText(e.getCause().getMessage());
        }
    }
    @FXML
    private void tornaHome(ActionEvent actionEvent) {
        LoadStage.loadStage(HomeApplication.class, actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registrazione_evento_severita_1.setUserData("1");
        registrazione_evento_severita_2.setUserData("2");
        registrazione_evento_severita_3.setUserData("3");
        registrazione_evento_severita_4.setUserData("4");
        registrazione_evento_severita_5.setUserData("5");

        registrazione_evento_toggleSeverita.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if(registrazione_evento_toggleSeverita != null){
                    severita = Integer.parseInt(registrazione_evento_toggleSeverita.getSelectedToggle().getUserData().toString());
                }
            }
        });

        registrazione_evento_notaEvento.addEventFilter(KeyEvent.KEY_TYPED, this);

//        NEL CASO SI VOGLIA PROVARE SENZA CONNESSIONE AL SERVER
//        registrazione_evento_nomeCentro.setText("PROVA CENTRO");

        String[] tipoEvento = {
                "Mal di testa", "Febbre", "Dolori muscolari e articolare", "Linfoadenopatia", "Tachicardia", "Crisi ipertensiva"
        };

        try{
            if(RunnerRMI.getInstance() != null && RunnerRMI.getInstance().getServer() != null){
                this.nomeCentro = RunnerRMI.getInstance().getServer().cercaCentroUtente(Login.getInstance().getUsername());
                this.registrazione_evento_nomeCentro.setText(this.nomeCentro);
            }else {
                this.nomeCentro = "NOME CENTRO PROVA";
                this.registrazione_evento_nomeCentro.setText(this.nomeCentro);
            }
        } catch (SQLException | RemoteException e) {
            System.err.println("Errore nell'initialize di RegistraEventoController");
        }

        registrazione_evento_menuTipoEvento.setValue("Seleziona tipo evento");
        registrazione_evento_menuTipoEvento.getItems().addAll(tipoEvento);
        registrazione_evento_menuTipoEvento.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                System.out.println("Selezione: " + tipoEvento[t1.intValue()]);
                tipoEventoSelezionato = tipoEvento[t1.intValue()];
            }
        });
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        Object evt = keyEvent.getSource();
        if(evt.equals(registrazione_evento_notaEvento)){
            if(registrazione_evento_notaEvento.getText().length() > 255){
                keyEvent.consume();
            }
        }
    }
}
