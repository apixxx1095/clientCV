package com.centrovaccinale.centrovaccinale.grafica.cittadino.registrautente.controller;

import com.centrovaccinale.centrovaccinale.entita.CittadinoRegistrato;
import com.centrovaccinale.centrovaccinale.grafica.home.main.HomeApplication;
import com.centrovaccinale.centrovaccinale.utils.LoadStage;
import com.centrovaccinale.centrovaccinale.utils.RunnerRMI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class
RegistraUtenteController implements Initializable, EventHandler<KeyEvent> {
    @FXML
    private Button tornaAllaHomeBtn;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField textNomeUtente;
    @FXML
    private TextField textCognomeUtente;
    @FXML
    private TextField textCodFiscale;
    @FXML
    private TextField textEmail;
    @FXML
    private TextField textUsername;
    @FXML
    private TextField textPassword;
    @FXML
    private TextField textIdVaccinazione; //range del short -32768 a 32767

    @FXML
    protected void registraUtenza(ActionEvent event){
        try{
            if(
                    (!textNomeUtente.getText().isEmpty() && !textCognomeUtente.getText().isEmpty() &&
                            !textCodFiscale.getText().isEmpty() && !textEmail.getText().isEmpty() &&
                            !textUsername.getText().isEmpty() && !textPassword.getText().isEmpty()) &&
                            !textIdVaccinazione.getText().isEmpty() &&
                            !RunnerRMI.getInstance().getServer().getListaCFCittadiniRegistrati().contains(textCodFiscale.getText().toUpperCase()) &&
                            !RunnerRMI.getInstance().getServer().idVaccinazioneUtenzeIsPresente(Short.parseShort(textIdVaccinazione.getText())) &&
                            !(Integer.parseInt(textIdVaccinazione.getText()) > 32767) &&
                            !RunnerRMI.getInstance().getServer().usernameIsPresente(textUsername.getText()) &&
                            RunnerRMI.getInstance().getServer().idVaccinazioneVaccinatiIsPresente(Short.parseShort(textIdVaccinazione.getText()))

            ){
                CittadinoRegistrato cittadinoRegistrato = new CittadinoRegistrato(
                        textNomeUtente.getText(),
                        textCognomeUtente.getText(),
                        textCodFiscale.getText().toUpperCase(),
                        textEmail.getText(),
                        textUsername.getText(),
                        textPassword.getText(),
                        Short.parseShort(textIdVaccinazione.getText())
                );
                if(RunnerRMI.getInstance().getServer().registraCittadino(cittadinoRegistrato,  "" + RunnerRMI.getInstance().getClient().getRef().remoteToString())){
                    System.out.println("Utente registrato a sistema correttamente!");

                    textNomeUtente.setText("");
                    textCognomeUtente.setText("");
                    textCodFiscale.setText("");
                    textEmail.setText("");
                    textUsername.setText("");
                    textPassword.setText("");
                    textIdVaccinazione.setText("");

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Operazione Riuscita");
                    alert.setContentText("Registrazione Andata a buon fine!");
                    alert.showAndWait();
                }
            }else if(
                    textNomeUtente.getText().isEmpty() || textCognomeUtente.getText().isEmpty() ||
                            textCodFiscale.getText().isEmpty() || textEmail.getText().isEmpty() || textUsername.getText().isEmpty()
                    || textPassword.getText().isEmpty() || textIdVaccinazione.getText().isEmpty()
            ){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Tutti i campi devono essere compilati!");
            }
            else if(RunnerRMI.getInstance().getServer().getListaCFCittadiniRegistrati().contains(textCodFiscale.getText().toUpperCase())){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Codice Fiscale risulta gia' registrato a sistema.");
            }
            else if(RunnerRMI.getInstance().getServer().usernameIsPresente(textUsername.getText())){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Username risultata gia' registrato a sistema.");
            }
            else if(textIdVaccinazione.getText().isEmpty()){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("IdVaccinazione non deve deve essere vuoto!");
            }
            else if(!RunnerRMI.getInstance().getServer().idVaccinazioneVaccinatiIsPresente(Short.parseShort(textIdVaccinazione.getText()))){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("IdVaccinazione inserito non esiste!");
            }
            else if(RunnerRMI.getInstance().getServer().idVaccinazioneUtenzeIsPresente(Short.parseShort(textIdVaccinazione.getText()))){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("IdVaccinazione risulta gia' registrato a sistema.");
            }
        } catch (RemoteException e) {
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

    @FXML
    private void tornaHome(ActionEvent actionEvent) throws IOException {
        LoadStage.loadStage(HomeApplication.class, actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textNomeUtente.addEventFilter(KeyEvent.KEY_TYPED, this);
        textCognomeUtente.addEventFilter(KeyEvent.KEY_TYPED, this);
        textCodFiscale.addEventFilter(KeyEvent.KEY_TYPED, this);
        textUsername.addEventFilter(KeyEvent.KEY_TYPED, this);
        textPassword.addEventFilter(KeyEvent.KEY_TYPED, this);
        textIdVaccinazione.addEventFilter(KeyEvent.KEY_TYPED, this);
    }

    @Override
    public void handle(KeyEvent event) {
        errorLabel.setText("");
        Object evt = event.getSource();
        if(evt.equals(textNomeUtente)){
            if(!Character.isLetter(event.getCharacter().charAt(0))){
                event.consume();
            }
        }
        else if(evt.equals(textCognomeUtente)){
            if(!Character.isLetter(event.getCharacter().charAt(0)) && !" ".equals(event.getCharacter())){
                event.consume();
            }
        }else if(evt.equals(textCodFiscale)){
            if(!Character.isLetter(event.getCharacter().charAt(0)) && !Character.isDigit(event.getCharacter().charAt(0)) || textCodFiscale.getText().length() > 15){
                event.consume();
            }
        }
        else if(evt.equals(textUsername)){
            if(event.getCode() == KeyCode.SPACE  || evt.equals(textPassword)){
                event.consume();
            }
        }else if(event.getCode() == KeyCode.SPACE  || evt.equals(textPassword)){
            if(" ".equals(event.getCharacter())){
                event.consume();
            }
        }
        else if(evt.equals(textIdVaccinazione)){
            if(!Character.isDigit(event.getCharacter().charAt(0))){
                event.consume();
            } else if (Integer.parseInt(textIdVaccinazione.getText() + event.getCharacter()) > 32767){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Il valore dell'id di vaccinazione non deve superare 32767");
            }
        }
    }
}
