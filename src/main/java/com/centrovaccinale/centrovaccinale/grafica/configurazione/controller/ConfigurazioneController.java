package com.centrovaccinale.centrovaccinale.grafica.configurazione.controller;

import com.centrovaccinale.centrovaccinale.grafica.home.main.HomeApplication;
import com.centrovaccinale.centrovaccinale.utils.LoadStage;
import com.centrovaccinale.centrovaccinale.utils.RunnerRMI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;


public class ConfigurazioneController implements Initializable, EventHandler<KeyEvent> {

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
        if(!hostServerText.getText().isEmpty() && !portServerText.getText().isEmpty()){
            try {
                RunnerRMI.setInstance(hostServerText.getText(), Integer.parseInt(portServerText.getText()));
                LoadStage.loadStage(HomeApplication.class, event);
            } catch (NotBoundException | RemoteException e) {
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Client exception: controlla che il server sia up!");
                System.err.println(e.getMessage());
                try {
                    if(RunnerRMI.getInstance().getClient() != null){
                        UnicastRemoteObject.unexportObject(RunnerRMI.getInstance().getClient(), true);
                    }
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    System.exit(0);
                }
            }
        }else {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("I campi non possono essere vuoti");
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
}
