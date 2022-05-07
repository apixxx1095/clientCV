package com.centrovaccinale.centrovaccinale.grafica.operatore.menu.controller;

import com.centrovaccinale.centrovaccinale.grafica.home.main.HomeApplication;
import com.centrovaccinale.centrovaccinale.grafica.operatore.registracentro.main.RegistraCentroApplication;
import com.centrovaccinale.centrovaccinale.grafica.operatore.registravaccinato.main.RegistraVaccinatoApplication;
import com.centrovaccinale.centrovaccinale.utils.LoadStage;
import com.centrovaccinale.centrovaccinale.utils.RunnerRMI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OperatoreController implements Initializable {
    @FXML
    private Button btnHome;
    @FXML
    private Button btnRegistraCentro;
    @FXML
    private Button btnRegistraVaccinato;

    @FXML
    private void actionEvent(ActionEvent event) throws IOException {
        Object evt = event.getSource();
        if(evt.equals(btnHome)){
//            String nameWindow = "Home - CentroVaccinale";
//            "/com/centrovaccinale/homeview/HomeView.fxml"
            LoadStage.loadStage(HomeApplication.class, event);
        }else if(evt.equals(btnRegistraCentro)){
//            String nameWindow = "Registrazione Nuovo Centro Vaccinale";
//            "/com/centrovaccinale/registrazionecentrovaccinale/RegistrazioneCentroVaccinaleView.fxml"
            LoadStage.loadStage(RegistraCentroApplication.class, event);
        }else if(evt.equals(btnRegistraVaccinato)){
            LoadStage.loadStage(RegistraVaccinatoApplication.class, event);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Entro nell'initialize() di OperatoreController");
        try{
            if(RunnerRMI.getInstance() != null){
                if(RunnerRMI.getInstance().getServer().getListaCentri().size() == 0){
                    btnRegistraVaccinato.setDisable(true);
                }
            }
        } catch (RemoteException e) {
            try {
                if(!RunnerRMI.tryConnectionServer()){
                    RunnerRMI.getInstance().setServer(null);
                }
            } catch (NotBoundException | RemoteException ex) {
                System.err.println(e.getMessage());
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
