package com.centrovaccinale.centrovaccinale.grafica.cittadino.login.controller;

import com.centrovaccinale.centrovaccinale.grafica.cittadino.menu.main.CittadinoMenuApplication;
import com.centrovaccinale.centrovaccinale.grafica.home.main.HomeApplication;
import com.centrovaccinale.centrovaccinale.rmi.Server;
import com.centrovaccinale.centrovaccinale.utils.LoadStage;
import com.centrovaccinale.centrovaccinale.utils.Login;
import com.centrovaccinale.centrovaccinale.utils.RunnerRMI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable, EventHandler<KeyEvent> {
	@FXML
    private Label labelMessage;

    @FXML
    private Button btnAccedi;
    @FXML
    private Button btnHome;

    @FXML
    private TextField textUsername;
    @FXML
    private PasswordField textPassword;


    @FXML
    protected void eventAction(ActionEvent event){
		Object evt = event.getSource();
		if(evt.equals(btnAccedi)) {
			String username = textUsername.getText();
			String password = textPassword.getText();
			try{
				if(RunnerRMI.tryConnectionServer()){
					if(!(password.isEmpty()) && !(username.isEmpty())){
						RunnerRMI runnerRMI;
						try {
							runnerRMI = RunnerRMI.getInstance();
							Server server = runnerRMI.getServer();
							if(server.login(username, password, "" + RunnerRMI.getInstance().getClient().getRef().remoteToString())){
								Login.setInstance(username);
								LoadStage.loadStage(CittadinoMenuApplication.class, event);
							}else{
								labelMessage.setText("");
								labelMessage.setTextFill(Color.RED);
								textUsername.setText("");
								textPassword.setText("");
								labelMessage.setText("Username o password errati!");
							}
						} catch (RemoteException | SQLException e) {
							System.err.println(e.getMessage());
						}
					}else if(username.isEmpty()){
						labelMessage.setTextFill(Color.RED);
						labelMessage.setText("Inserisci username!");
					}
					else {
						labelMessage.setTextFill(Color.RED);
						labelMessage.setText("Inserisci password!");
					}
				}
			} catch (NotBoundException | RemoteException e) {
				JOptionPane.showInternalMessageDialog(null, "Controlla che il server sia avviato!", "AVVERTENZA",JOptionPane.WARNING_MESSAGE);
				RunnerRMI.getInstance().setServer(null);
			}
		}
		else if(evt.equals(btnHome)) {
			LoadStage.loadStage(CittadinoMenuApplication.class, event);
		}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
		btnAccedi.setCursor(Cursor.HAND);
		textUsername.addEventFilter(KeyEvent.KEY_TYPED, this);
		textPassword.addEventFilter(KeyEvent.KEY_TYPED, this);
    }

	@Override
	public void handle(KeyEvent keyEvent) {
		Object evt = keyEvent.getSource();
		if(evt.equals(textUsername)){
			if(keyEvent.getCode() == KeyCode.SPACE || " ".equals(keyEvent.getCharacter())){
				keyEvent.consume();
			}
		}
		else if(evt.equals(textPassword)){
			if(keyEvent.getCode() == KeyCode.SPACE || " ".equals(keyEvent.getCharacter())){
				keyEvent.consume();
			}
		}
	}
}
