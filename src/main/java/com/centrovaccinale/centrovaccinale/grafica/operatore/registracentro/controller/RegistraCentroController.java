package com.centrovaccinale.centrovaccinale.grafica.operatore.registracentro.controller;

import com.centrovaccinale.centrovaccinale.entita.CentroVaccinale;
import com.centrovaccinale.centrovaccinale.grafica.operatore.menu.main.OperatoreApplication;
import com.centrovaccinale.centrovaccinale.rmi.Server;
import com.centrovaccinale.centrovaccinale.utils.LoadStage;
import com.centrovaccinale.centrovaccinale.utils.RunnerRMI;
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

public class RegistraCentroController implements Initializable, EventHandler <KeyEvent>{
    private String tipologiaCentro;
    private String qualificatoreIndirizzo;

    @FXML
    private ToggleGroup toggleGroupQualificatore;
    @FXML
    private ToggleGroup toggleGroupTipologia;

    @FXML
    public RadioButton registraCentro_tipologia_hub;
    @FXML
    public RadioButton registraCentro_tipologia_aziendale;
    @FXML
    public RadioButton registraCentro_tipologia_ospedaliero;

    @FXML
    public RadioButton registraCentro_qualificatore_piazza;
    @FXML
    public RadioButton registraCentro_qualificatore_viale;
    @FXML
    public RadioButton registraCentro_qualificatore_via;


    @FXML
    private TextField registraCentro_nomeCentro;
    @FXML
    private TextField registraCentro_indirizzo;
    @FXML
    private TextField registraCentro_numeroCivico;
    @FXML
    private TextField registraCentro_comune;
    @FXML
    private TextField registraCentro_provincia;
    @FXML
    private TextField registraCentro_cap;
    @FXML
    private Label errorLabel;

    @FXML
    private void registraCentroVaccinale(ActionEvent actionEvent){
        try{
            //List<String> listaCentri = !RunnerRMI.getInstance().getServer().getListaCentri();
            if(
                    tipologiaCentro != null && qualificatoreIndirizzo != null &&
                            (!registraCentro_nomeCentro.getText().isEmpty() && !registraCentro_indirizzo.getText().isEmpty() &&
                                    !registraCentro_numeroCivico.getText().isEmpty() && !registraCentro_comune.getText().isEmpty() &&
                                    !registraCentro_provincia.getText().isEmpty() && !registraCentro_cap.getText().isEmpty()) &&
                            !RunnerRMI.getInstance().getServer().getListaCentri().contains(registraCentro_nomeCentro.getText().toUpperCase())
            ){
                CentroVaccinale centroVaccinale = new CentroVaccinale(
                        registraCentro_nomeCentro.getText().toUpperCase(),
                        qualificatoreIndirizzo,
                        registraCentro_indirizzo.getText(),
                        Integer.parseInt(registraCentro_numeroCivico.getText()),
                        registraCentro_comune.getText(),
                        registraCentro_provincia.getText().toUpperCase(),
                        registraCentro_cap.getText(),
                        tipologiaCentro
                );
                RunnerRMI runnerRMI;
                if(RunnerRMI.tryConnectionServer()){
                    runnerRMI = RunnerRMI.getInstance();
                    Server server = runnerRMI.getServer();
                    if(server.registraCentroVaccinale(centroVaccinale, runnerRMI.getClient().getRef().remoteToString())){
                        registraCentro_nomeCentro.setText("");
                        registraCentro_indirizzo.setText("");
                        registraCentro_numeroCivico.setText("");
                        registraCentro_comune.setText("");
                        registraCentro_provincia.setText("");
                        registraCentro_cap.setText("");
                        errorLabel.setText("");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Operazione riuscita!");
                        alert.setContentText("Centro vaccinale registrato correttamente!");
                        alert.showAndWait();
                        System.out.println("Fine operazione");
//                        JOptionPane.showInternalMessageDialog(null, "Registrazione andata a buon fine!", "Operazione riuscita", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }else if(RunnerRMI.getInstance().getServer().getListaCentri().contains(registraCentro_nomeCentro.getText().toUpperCase())){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Centro vaccinale risulta gia' registrato!");
            }
            else if(toggleGroupTipologia.getSelectedToggle() == null){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Seleziona una tipologia!");
            }
            else if(toggleGroupQualificatore.getSelectedToggle() == null){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Seleziona un qualificatore!");
            }
            else {
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Tutti i campi devono essere compilati!");
            }
        }catch (NotBoundException | RemoteException e) {
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
        } catch (SQLException e) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText(e.getCause().getMessage());
        }
    }

    @FXML
    private void tornaHome(ActionEvent actionEvent) {
        LoadStage.loadStage(OperatoreApplication.class, actionEvent);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        registraCentro_tipologia_ospedaliero.setUserData("Ospedaliero");
        registraCentro_tipologia_aziendale.setUserData("Aziendale");
        registraCentro_tipologia_hub.setUserData("Hub");

        toggleGroupTipologia.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            if(toggleGroupTipologia != null){
                tipologiaCentro = toggleGroupTipologia.getSelectedToggle().getUserData().toString();
            }
        });

        registraCentro_qualificatore_piazza.setUserData("Piazza");
        registraCentro_qualificatore_via.setUserData("Via");
        registraCentro_qualificatore_viale.setUserData("Viale");

        toggleGroupQualificatore.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            if(toggleGroupQualificatore != null){
                qualificatoreIndirizzo = toggleGroupQualificatore.getSelectedToggle().getUserData().toString();
            }
        });

        registraCentro_numeroCivico.addEventFilter(KeyEvent.KEY_TYPED, this);
        registraCentro_provincia.addEventFilter(KeyEvent.KEY_TYPED, this);
        registraCentro_cap.addEventFilter(KeyEvent.KEY_TYPED, this);
        registraCentro_comune.addEventFilter(KeyEvent.KEY_TYPED, this);
        registraCentro_cap.addEventFilter(KeyEvent.KEY_TYPED, this);
    }
    @Override
    public void handle(KeyEvent event) {
        Object evt = event.getSource();
        if(evt.equals(registraCentro_numeroCivico)){
            if(!Character.isDigit(event.getCharacter().charAt(0))){
                event.consume();
            }
        }
        else if(evt.equals(registraCentro_provincia)){
            if(!Character.isLetter(event.getCharacter().charAt(0)) || registraCentro_provincia.getText().length() > 1){
                event.consume();
            }
        }else if(evt.equals(registraCentro_comune)){
            if(!Character.isLetter(event.getCharacter().charAt(0)) && !" ".equals(event.getCharacter())){
                event.consume();
            }
        }
        else if(evt.equals(registraCentro_cap)){
            if(!Character.isDigit(event.getCharacter().charAt(0)) || registraCentro_cap.getText().length() > 4){
                event.consume();
            }
        }
    }
}
