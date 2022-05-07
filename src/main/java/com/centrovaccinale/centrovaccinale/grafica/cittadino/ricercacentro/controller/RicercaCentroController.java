package com.centrovaccinale.centrovaccinale.grafica.cittadino.ricercacentro.controller;

import com.centrovaccinale.centrovaccinale.entita.RiepilogoEventi;
import com.centrovaccinale.centrovaccinale.grafica.home.main.HomeApplication;
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
import java.util.List;
import java.util.ResourceBundle;

public class RicercaCentroController implements Initializable, EventHandler<KeyEvent> {

    private List<String> centriTrovati;

    @FXML
    private TextField ricerca_centro_nomeCentro;
    @FXML
    private TextField ricerca_centro_comuneCentro;
    @FXML
    private TextField ricerca_centro_tipologiaCentro;
    @FXML
    private Button ricercaNomeBtn;
    @FXML
    private Button ricercaComuneTipologiaBtn;
    @FXML
    private ListView<String> listaCentri;
    @FXML
    private TextArea informazioniCentro;
    @FXML
    private Label messagesLabel;


    @FXML
    private void ricerca(ActionEvent event){
        Object evt = event.getSource();
        if(listaCentri.getItems().size() > 0){
            listaCentri.getItems().clear();
        }
        if(centriTrovati != null && centriTrovati.size() > 0){
            centriTrovati.clear();
        }
        messagesLabel.setText("");
        informazioniCentro.setText("");
        if(evt.equals(ricercaNomeBtn)){
            System.out.println("Invocato la ricerca per nome\n");
            if(!ricerca_centro_nomeCentro.getText().isEmpty()){
                try {
                    if(RunnerRMI.getInstance() != null && RunnerRMI.getInstance().getServer() != null){
                        centriTrovati = RunnerRMI.getInstance().getServer().cercaCentroPerNome(ricerca_centro_nomeCentro.getText(), RunnerRMI.getInstance().getClient().getRef().toString());
                        if(centriTrovati.size() > 0){
                            ricerca_centro_nomeCentro.setText("");
                            listaCentri.getItems().addAll(centriTrovati);
                            listaCentri.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                                @Override
                                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                                    try {
                                        if(t1 != null){
                                            List<RiepilogoEventi> riepilogoEventi = RunnerRMI.getInstance().getServer().riepilogoEventi(t1, RunnerRMI.getInstance().getClient().getRef().toString());
                                            if(riepilogoEventi.size() > 0){
                                                for(RiepilogoEventi evento: riepilogoEventi){
                                                    informazioniCentro.setText("");
                                                    informazioniCentro.appendText(evento + "\n");
                                                }
                                            }else{
                                                informazioniCentro.setText("");
                                                informazioniCentro.appendText("Non sono stati trovati eventi registrati");
                                            }
                                        }
                                    } catch (SQLException | RemoteException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }else {
                            messagesLabel.setTextFill(Color.RED);
                            messagesLabel.setText("Non sono stati trovati centri con il nome: " + ricerca_centro_nomeCentro.getText());
                        }
                    }

                } catch (RemoteException e) {
                    messagesLabel.setTextFill(Color.RED);
                    messagesLabel.setText(e.getCause().getMessage());
                    try {
                        if(!RunnerRMI.tryConnectionServer()){
                            RunnerRMI.getInstance().setServer(null);
                        }
                    } catch (NotBoundException | RemoteException ex) {
                        messagesLabel.setTextFill(Color.RED);
                        messagesLabel.setText(e.getCause().getMessage());
                        System.err.println(e.getMessage());
                    }
                }
                catch (SQLException e) {
                    messagesLabel.setTextFill(Color.RED);
                    messagesLabel.setText(e.getCause().getMessage());
                }
            }
            else {
                messagesLabel.setTextFill(Color.RED);
                messagesLabel.setText("Inserire il nome di un centro!");
            }
        }
        else if(evt.equals(ricercaComuneTipologiaBtn)){
            System.out.println("Invocato la ricerca per comune e tipologia\n");
            if(!ricerca_centro_comuneCentro.getText().isEmpty() && !ricerca_centro_tipologiaCentro.getText().isEmpty()){
                try {
                    centriTrovati = RunnerRMI.getInstance().getServer().cercaCentroPerComuneTipologia(ricerca_centro_comuneCentro.getText(), ricerca_centro_tipologiaCentro.getText(), RunnerRMI.getInstance().getClient().getRef().toString());
                    if(centriTrovati.size() > 0){
                        ricerca_centro_comuneCentro.setText("");
                        ricerca_centro_tipologiaCentro.setText("");
                        listaCentri.getItems().addAll(centriTrovati);
                        listaCentri.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                            @Override
                            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                                try {
                                    if(t1 != null){
                                        List<RiepilogoEventi> riepilogoEventi = RunnerRMI.getInstance().getServer().riepilogoEventi(t1, RunnerRMI.getInstance().getClient().getRef().toString());
                                        if(riepilogoEventi.size() > 0){
                                            for(RiepilogoEventi evento: riepilogoEventi){
                                                informazioniCentro.clear();
                                                informazioniCentro.appendText(evento + "\n");
                                            }
                                        }
                                        else{
                                            informazioniCentro.setText("");
                                            informazioniCentro.appendText("Non sono stati trovati eventi registrati");
                                        }
                                    }
                                } catch (SQLException | RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    else {
                        messagesLabel.setTextFill(Color.RED);
                        messagesLabel.setText("Non sono stati trovati centri per il comune di " + ricerca_centro_comuneCentro.getText() + " di tipo " + ricerca_centro_tipologiaCentro.getText());
                    }

                } catch (RemoteException e) {
                    messagesLabel.setTextFill(Color.RED);
                    messagesLabel.setText(e.getCause().getMessage());
                    try {
                        if(!RunnerRMI.tryConnectionServer()){
                            RunnerRMI.getInstance().setServer(null);
                        }
                    } catch (NotBoundException | RemoteException ex) {
                        messagesLabel.setTextFill(Color.RED);
                        messagesLabel.setText(e.getCause().getMessage());
                        System.err.println(e.getMessage());
                    }
                }
                catch (SQLException e) {
                    messagesLabel.setTextFill(Color.RED);
                    messagesLabel.setText(e.getCause().getMessage());
                }
            }
            else if(ricerca_centro_comuneCentro.getText().isEmpty() && ricerca_centro_tipologiaCentro.getText().isEmpty()){
                messagesLabel.setTextFill(Color.RED);
                messagesLabel.setText("Comune e tipologia non devono essere vuoti!");
            }
            else if(ricerca_centro_comuneCentro.getText().isEmpty()){
                messagesLabel.setTextFill(Color.RED);
                messagesLabel.setText("Inserire il nome del comune!");
            }
            else if(ricerca_centro_tipologiaCentro.getText().isEmpty()){
                messagesLabel.setTextFill(Color.RED);
                messagesLabel.setText("Inserire la tipologia del centro!");
            }
        }
    }

    @FXML
    private void tornaHome(ActionEvent event){
        LoadStage.loadStage(HomeApplication.class, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ricerca_centro_nomeCentro.addEventFilter(KeyEvent.KEY_TYPED, this);
        ricerca_centro_comuneCentro.addEventFilter(KeyEvent.KEY_TYPED, this);
        ricerca_centro_tipologiaCentro.addEventFilter(KeyEvent.KEY_TYPED, this);

    }

    @Override
    public void handle(KeyEvent event) {
        messagesLabel.setText("");
        Object evt = event.getSource();
        if(evt.equals(ricerca_centro_nomeCentro)){
            if(" ".equals(event.getCharacter()) && ricerca_centro_nomeCentro.getText().length() == 0){
                event.consume();
            }
        }
        else if(evt.equals(ricerca_centro_comuneCentro)){
            if(" ".equals(event.getCharacter()) && ricerca_centro_comuneCentro.getText().length() == 0){
                event.consume();
            }
        }
        else if(evt.equals(ricerca_centro_tipologiaCentro)){
            if(" ".equals(event.getCharacter()) && ricerca_centro_tipologiaCentro.getText().length() == 0){
                event.consume();
            }
        }
    }
}
