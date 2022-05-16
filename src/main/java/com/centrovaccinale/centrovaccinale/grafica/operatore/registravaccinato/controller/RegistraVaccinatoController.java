package com.centrovaccinale.centrovaccinale.grafica.operatore.registravaccinato.controller;

import com.centrovaccinale.centrovaccinale.entita.CittadinoVaccinato;
import com.centrovaccinale.centrovaccinale.grafica.cittadino.menu.main.CittadinoMenuApplication;
import com.centrovaccinale.centrovaccinale.grafica.operatore.menu.main.OperatoreApplication;
import com.centrovaccinale.centrovaccinale.rmi.Server;
import com.centrovaccinale.centrovaccinale.utils.LoadStage;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegistraVaccinatoController implements Initializable, EventHandler<KeyEvent> {
    private LocalDate dataSomministrazioneVaccino = LocalDate.now();
    private String centroSelezionato;
    private short idVaccinazione;
    private String vaccinoSomministrato;

    @FXML
    private ToggleGroup toggleGroupVaccinoSomministrato;

    @FXML
    public RadioButton registraCittadino_vaccinoSomministrato_pfitzer;
    @FXML
    public RadioButton registraCittadino_vaccinoSomministrato_astraZeneca;
    @FXML
    public RadioButton registraCittadino_vaccinoSomministrato_moderna;
    @FXML
    public RadioButton registraCittadino_vaccinoSomministrato_jj;

    @FXML
    private ComboBox<String> menuListaCentri;
    @FXML
    private TextField registraCittadino_nomeCittadino;
    @FXML
    private TextField registraCittadino_cognomeCittadino;
    @FXML
    private TextField registraCittadino_codiceFiscale;
    @FXML
    private DatePicker registraCittadino_dataSomministrazioneVaccino;
    @FXML
    private Label errorLabel;

    @FXML
    private void aggiornataData(ActionEvent event){
        this.dataSomministrazioneVaccino = registraCittadino_dataSomministrazioneVaccino.getValue();
    }
    @FXML
    private void settaCentro(ActionEvent event){
        this.centroSelezionato = menuListaCentri.getSelectionModel().getSelectedItem();
    }
    @FXML
    private void registraVaccinato(ActionEvent actionEvent){
        try{
            if(vaccinoSomministrato != null && centroSelezionato != null && !registraCittadino_nomeCittadino.getText().isEmpty() &&
                    !registraCittadino_cognomeCittadino.getText().isEmpty() && !registraCittadino_codiceFiscale.getText().isEmpty()){

                CittadinoVaccinato cittadinoVaccinato = new CittadinoVaccinato(
                        centroSelezionato.toUpperCase(),
                        registraCittadino_nomeCittadino.getText(),
                        registraCittadino_cognomeCittadino.getText(),
                        registraCittadino_codiceFiscale.getText().toUpperCase(),
                        vaccinoSomministrato,
                        dataSomministrazioneVaccino,
                        generaIdVaccinazione()
                );
                RunnerRMI runnerRMI;
                if(RunnerRMI.tryConnectionServer()){
                    runnerRMI = RunnerRMI.getInstance();
                    Server server = runnerRMI.getServer();
                    if(server.registraVaccinato(cittadinoVaccinato, runnerRMI.getClient().getRef().remoteToString())){
                        registraCittadino_nomeCittadino.setText("");
                        registraCittadino_cognomeCittadino.setText("");
                        registraCittadino_codiceFiscale.setText("");
                        errorLabel.setText("");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Registrazione andata a buon fine!");
                        alert.setContentText("Cittadino vaccinato registrato correttamente!\nIdVaccinazione generato: " + this.idVaccinazione);
                        alert.showAndWait();
                    }
                }
            }
            else if(registraCittadino_nomeCittadino.getText().isEmpty() || registraCittadino_cognomeCittadino.getText().isEmpty() ||
            registraCittadino_codiceFiscale.getText().isEmpty() || toggleGroupVaccinoSomministrato.getSelectedToggle() == null) {
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Tutti i campi devono essere compilati");
            }
            else if(centroSelezionato == null){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Selezionare un centro vaccinale!");
            }
            else if(toggleGroupVaccinoSomministrato.getSelectedToggle() == null){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Selezionare un tipo di vaccino");
            }
            else if(RunnerRMI.getInstance().getServer().getListaCFVaccinati().contains(registraCittadino_codiceFiscale.getText().toUpperCase())){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText(registraCittadino_codiceFiscale.getText() + " risulta gia' registrato!");
            }
        } catch (NotBoundException | RemoteException e) {
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

    private short generaIdVaccinazione() {
        short idVaccinazione = (short) (Math.random() * Short.MAX_VALUE -1);
        try {
            if(RunnerRMI.getInstance() != null){
                if(RunnerRMI.getInstance().getServer().idVaccinazioneVaccinatiIsPresente(idVaccinazione)){
                    generaIdVaccinazione();
                }
            }
        } catch (SQLException | RemoteException e) {
            e.printStackTrace();
        }
        this.idVaccinazione = idVaccinazione;
        return idVaccinazione;
    }

    @FXML
    private void back(ActionEvent actionEvent){
        LoadStage.loadStage(OperatoreApplication.class, actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registraCittadino_vaccinoSomministrato_pfitzer.setUserData("Pfizer");
        registraCittadino_vaccinoSomministrato_astraZeneca.setUserData("AstraZeneca");
        registraCittadino_vaccinoSomministrato_moderna.setUserData("Moderna");
        registraCittadino_vaccinoSomministrato_jj.setUserData("J&J");

        toggleGroupVaccinoSomministrato.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if(toggleGroupVaccinoSomministrato != null){
                    vaccinoSomministrato = toggleGroupVaccinoSomministrato.getSelectedToggle().getUserData().toString();
                }
            }
        });

        System.out.println("Parte metodo initialize");
        List<String> listaCentri = new ArrayList<>();
        try{
            if(RunnerRMI.getInstance() != null){
                if(RunnerRMI.getInstance().getServer().getListaCentri().size() > 0){
                    listaCentri = RunnerRMI.getInstance().getServer().getListaCentri();
                }
            }else {
                listaCentri = new ArrayList<>();
                listaCentri.add("Centro1");
                listaCentri.add("Centro2");
                listaCentri.add("Centro3");
                listaCentri.add("Centro4");
            }
            menuListaCentri.getItems().addAll(listaCentri);
        } catch (SQLException | RemoteException e) {
            e.printStackTrace();
        }
        registraCittadino_codiceFiscale.addEventFilter(KeyEvent.KEY_TYPED, this);
        registraCittadino_nomeCittadino.addEventFilter(KeyEvent.KEY_TYPED, this);
        registraCittadino_cognomeCittadino.addEventFilter(KeyEvent.KEY_TYPED, this);
    }

    @Override
    public void handle(KeyEvent event) {
        errorLabel.setText("");
        Object evt = event.getSource();
        if(evt.equals(registraCittadino_codiceFiscale)){
            if(!Character.isLetter(event.getCharacter().charAt(0)) && !Character.isDigit(event.getCharacter().charAt(0)) || registraCittadino_codiceFiscale.getText().length() > 15){
                event.consume();
            }
        }
        else if(evt.equals(registraCittadino_nomeCittadino)){
            if(!Character.isLetter(event.getCharacter().charAt(0)) && !" ".equals(event.getCharacter())){
                event.consume();
            }
        }
        else if(evt.equals(registraCittadino_cognomeCittadino)){
            if(!Character.isLetter(event.getCharacter().charAt(0)) && !" ".equals(event.getCharacter())){
                event.consume();
            }
        }
    }
}
