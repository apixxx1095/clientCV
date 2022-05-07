package com.centrovaccinale.centrovaccinale.grafica.home.main;

import com.centrovaccinale.centrovaccinale.utils.RunnerRMI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HomeApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        System.out.println("Entro nello start() di HomeApplication");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HomeApplication.class.getResource("/com/centrovaccinale/homeview/HomeView.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        primaryStage.setTitle("Home - CentroVaccinale");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // TODO: COPIARLI SU TUTTI
    @Override
    public void stop(){
        System.out.println("Entro nello stop() di HomeApplication");

        if(RunnerRMI.getInstance() != null){
            try {
                RunnerRMI.getInstance().getServer().removeCliente(RunnerRMI.getInstance().getClient());
                if(RunnerRMI.getInstance().getClient() != null){
                    UnicastRemoteObject.unexportObject(RunnerRMI.getInstance().getClient(), true);
                    System.out.println("Client chiuso");
                    System.exit(0);
                }
            } catch (RemoteException e) {
                System.err.println(e.getMessage());
                try {
                    if(UnicastRemoteObject.unexportObject(RunnerRMI.getInstance().getClient(), true)){
                        System.out.println("Client chiuso");
                        System.exit(0);
                    }
                } catch (RemoteException ex) {
                    System.err.println(e.getMessage());
                    System.exit(0);
                }
            }
        }
    }
}
