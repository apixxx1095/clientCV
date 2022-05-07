package com.centrovaccinale.centrovaccinale.utils;

import com.centrovaccinale.centrovaccinale.grafica.cittadino.login.main.LoginApplication;
import com.centrovaccinale.centrovaccinale.grafica.cittadino.menu.main.CittadinoMenuApplication;
import com.centrovaccinale.centrovaccinale.grafica.cittadino.registraevento.main.RegistraEventoApplication;
import com.centrovaccinale.centrovaccinale.grafica.cittadino.registrautente.main.RegistraUtenteApplication;
import com.centrovaccinale.centrovaccinale.grafica.cittadino.ricercacentro.main.RicercaCentroApplication;
import com.centrovaccinale.centrovaccinale.grafica.configurazione.main.ConfigurazioneApplication;
import com.centrovaccinale.centrovaccinale.grafica.home.main.HomeApplication;
import com.centrovaccinale.centrovaccinale.grafica.operatore.menu.main.OperatoreApplication;
import com.centrovaccinale.centrovaccinale.grafica.operatore.registracentro.main.RegistraCentroApplication;
import com.centrovaccinale.centrovaccinale.grafica.operatore.registravaccinato.main.RegistraVaccinatoApplication;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author Jean Pierre Sotamba 728447 VA
 * @author Simone Caputo 736816 VA
 * @author Laura Gjetja 738629 VA
 * @author Erica Bonetti 740946 VA
 * @version 0.0.1
 */
public class LoadStage<T>{
    /**
     * Metodo che si occupa del caricamento delle finestre per il passaggio da una finestra all'altra.
     * @param classe Riferimento della classe di cui si vuole creare l'istanza.
     * @param event Evento da cui si ricavano i parametri per il Primary Stage.
     */
    public static void loadStage(Class<?> classe, Event event) {
        try{
            // Questa riga riassume la parte sotto
            //((Node)(event.getSource())).getScene().getWindow().hide();

            //Parte sotto:
            Object evenSource = event.getSource();
            Node sourceAsNode = (Node) evenSource;
            Scene oldScene = sourceAsNode.getScene();
            Window window = oldScene.getWindow();
            Stage stage = (Stage) window;
            stage.hide();

            if(classe.equals(HomeApplication.class)){
                new HomeApplication().start(stage);
            }
            else if(classe.equals(OperatoreApplication.class)){
                new OperatoreApplication().start(stage);
            }
            else if(classe.equals(RegistraCentroApplication.class)){
                new RegistraCentroApplication().start(stage);
            }
            else if(classe.equals(RegistraVaccinatoApplication.class)){
                new RegistraVaccinatoApplication().start(stage);
            }
            else if(classe.equals(CittadinoMenuApplication.class)){
                new CittadinoMenuApplication().start(stage);
            }
            else if(classe.equals(LoginApplication.class)){
                new LoginApplication().start(stage);
            }
            else if(classe.equals(RegistraEventoApplication.class)){
                new RegistraEventoApplication().start(stage);
            }
            else if(classe.equals(RegistraUtenteApplication.class)){
                new RegistraUtenteApplication().start(stage);
            }
            else if(classe.equals(RicercaCentroApplication.class)){
                new RicercaCentroApplication().start(stage);
            }
            else if(classe.equals(ConfigurazioneApplication.class)){
                new ConfigurazioneApplication().start(stage);
            }

//            Parent root = FXMLLoader.load(Objects.requireNonNull(LoadStage.class.getResource(url)));
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            Stage newStage = new Stage();
//            newStage.setScene(scene);
//            newStage.setResizable(false);
//            newStage.setTitle(nameWindows);
//            newStage.show();
//
//            newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                @Override
//                public void handle(WindowEvent event) {
//                    Platform.exit();
//                }
//            });
        }catch (IOException e){
            Logger.getLogger(LoadStage.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
