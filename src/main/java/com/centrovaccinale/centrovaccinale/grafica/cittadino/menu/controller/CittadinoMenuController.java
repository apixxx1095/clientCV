package com.centrovaccinale.centrovaccinale.grafica.cittadino.menu.controller;

import com.centrovaccinale.centrovaccinale.grafica.cittadino.login.main.LoginApplication;
import com.centrovaccinale.centrovaccinale.grafica.cittadino.registraevento.main.RegistraEventoApplication;
import com.centrovaccinale.centrovaccinale.grafica.cittadino.registrautente.main.RegistraUtenteApplication;
import com.centrovaccinale.centrovaccinale.grafica.cittadino.ricercacentro.main.RicercaCentroApplication;
import com.centrovaccinale.centrovaccinale.grafica.home.main.HomeApplication;
import com.centrovaccinale.centrovaccinale.utils.LoadStage;
import com.centrovaccinale.centrovaccinale.utils.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CittadinoMenuController implements Initializable {
    @FXML
    private Label labelUser;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnRegistrati;
    @FXML
    private Button btnCercaCentro;
    @FXML
    private Button btnRegistraEvento;
    @FXML
    private Button btnLogout;

    @FXML
    protected void actionEvent(ActionEvent event) throws IOException {
        Object evt = event.getSource();

        if(evt.equals(btnLogin)){
            LoadStage.loadStage(LoginApplication.class, event);
        }else if(evt.equals(btnHome)){
            LoadStage.loadStage(HomeApplication.class, event);
        }else if(evt.equals(btnRegistrati)){
            LoadStage.loadStage(RegistraUtenteApplication.class, event);
        }else if(evt.equals(btnCercaCentro)){
            LoadStage.loadStage(RicercaCentroApplication.class, event);
        }else if(evt.equals(btnRegistraEvento)){
            LoadStage.loadStage(RegistraEventoApplication.class, event);
        }else if(evt.equals(btnLogout)) {
            Login.logout();
            if (Login.getInstance() == null) {
                labelUser.setText("");
                btnLogin.setDisable(false);
                btnRegistrati.setDisable(false);
                btnRegistraEvento.setDisable(true);
                btnLogout.setDisable(true);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Login.getInstance() != null && Login.getInstance().getConnected()){
            btnLogin.setDisable(true);
            btnRegistrati.setDisable(true);
            btnRegistraEvento.setDisable(false);
            btnLogout.setDisable(false);
            labelUser.setTextFill(Color.WHITE);
            labelUser.setText(Login.getInstance().getUsername());
        }
    }
}
