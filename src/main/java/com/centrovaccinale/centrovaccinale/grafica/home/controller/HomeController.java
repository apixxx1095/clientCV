package com.centrovaccinale.centrovaccinale.grafica.home.controller;

import com.centrovaccinale.centrovaccinale.grafica.cittadino.menu.main.CittadinoMenuApplication;
import com.centrovaccinale.centrovaccinale.grafica.operatore.menu.main.OperatoreApplication;
import com.centrovaccinale.centrovaccinale.utils.LoadStage;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Button btnOperatore;
    @FXML
    private Button btnCittadino;

    @FXML
    protected void eventAction(Event event) {
        Object evt = event.getSource();
        if(evt.equals(btnOperatore)){
            LoadStage.loadStage(OperatoreApplication.class, event);
        }else if(evt.equals(btnCittadino)){
            LoadStage.loadStage(CittadinoMenuApplication.class, event);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
