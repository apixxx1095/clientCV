package com.centrovaccinale.centrovaccinale.grafica.configurazione.controller;

import com.centrovaccinale.centrovaccinale.grafica.home.main.HomeApplication;
import com.centrovaccinale.centrovaccinale.utils.LoadStage;
import com.centrovaccinale.centrovaccinale.utils.RunnerRMI;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConfigurazioneController implements Initializable, EventHandler<KeyEvent> {
    private static final String IPV4_REGEX =
            "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private static final Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);

    @FXML
    private Label errorLabel;
    @FXML
    private TextField hostServerText;
    @FXML
    private TextField portServerText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hostServerText.addEventFilter(KeyEvent.KEY_TYPED, this);
        portServerText.addEventFilter(KeyEvent.KEY_TYPED, this);
    }

    @FXML
    private void configurazioneConnessioneServer(ActionEvent event){
        errorLabel.setText("");
        Task<RunnerRMI> task = new Task<>() {
            @Override
            protected RunnerRMI call(){
                return RunnerRMI.setInstance(hostServerText.getText(), Integer.parseInt(portServerText.getText()));
            }
        };

        if(!hostServerText.getText().isEmpty() && !portServerText.getText().isEmpty() && isValidInet4Address(hostServerText.getText())){
            errorLabel.setTextFill(Color.GREEN);
            errorLabel.setText("Connessione in corso...");

            new Thread(task).start();

            task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, workerStateEvent -> {
                if(task.getValue() != null){
                    LoadStage.loadStage(HomeApplication.class, event);
                }else{
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("Errore: controlla che il server sia attivo, o che host e port siano corretti!");
                }
            });
        }else if(!isValidInet4Address(hostServerText.getText())) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Formato host non valido");
        }
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        Object evt = keyEvent.getSource();
        if(evt.equals(hostServerText)){
            if(!Character.isDigit(keyEvent.getCharacter().charAt(0)) && !".".equals(keyEvent.getCharacter())){
                keyEvent.consume();
            }
        }
        else if(evt.equals(portServerText)){
            if(!Character.isDigit(keyEvent.getCharacter().charAt(0))){
                keyEvent.consume();
            }

        }
    }
    private boolean isValidInet4Address(String ip) {
        if (ip == null) {
            return false;
        }
        Matcher matcher = IPv4_PATTERN.matcher(ip);

        return matcher.matches();
    }
}
