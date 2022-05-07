package com.centrovaccinale.centrovaccinale.rmi;


import com.centrovaccinale.centrovaccinale.entita.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia in cui vengono definiti i metodi (servizi) forniti dal server.
 * Il client tramite questi metodi riesce a comunicare con il server.
 * L'implementazione di tutti questi metodi viene fatta lato server.
 * @author Jean Pierre Sotamba 728447 VA
 * @author Simone Caputo 736816 VA
 * @author Laura Gjetja 738629 VA
 * @author Erica Bonetti 740946 VA
 * @version 0.0.1
 */
public interface Server extends Remote {
    // COMUNICAZIONE CONNESSIONE ANDATA A BUON FINE

    /**
     * Questo metodo serve per aggiungere il riferimento del client all'interno del server.
     * @param remoteClient Riferimento del client.
     * @return true se la connessione con il server è andata a buon fine, false altrimenti.
     * @throws RemoteException nel caso di problemi di comunicazione.
     */
    boolean connectionOK(Client remoteClient) throws RemoteException;

    /**
     * Rimuove il riferimento del client dalla lista dei client del server.
     * @param remoteClient Riferimento del client da rimuovere.
     * @throws RemoteException nel caso di problemi di comunicazione
     */
    void removeCliente(Client remoteClient) throws RemoteException;

    /**
     * Serve al Client per verificare se il server è ancora in attesa di richieste.
     * Nel caso in cui il server, per qualche motivo, venisse terminata la sua esecuzione
     * e venisse fatto ripartire, il client con questo metodo aggiunge alla lista
     * dei riferimenti il suo stesso riferimento.
     * @param remoteClient Riferimento del client da aggiungere alla lista dei client del server.
     * @return true se la connessione va a buon fine
     * @throws RemoteException nel caso di problemi di comunicazione
     */
    boolean verifyConnection(Client remoteClient) throws RemoteException;

    // OPERAZIONI OPERATORE

    /**
     * Metodo che si occupa della registrazione di un <b>Centro Vaccinale</b> a DB.
     * @param centroVaccinale Centro Vaccinale da registrare a DB.
     * @param remoteClient Riferimento del client che effettua la chiamata.
     * @return true nel caso sia registrato correttamente, false altrimenti.
     * @throws RemoteException nel caso di problemi di comunicazione.
     * @throws SQLException nel caso in cui ci siano problemi di query.
     */
    boolean registraCentroVaccinale(CentroVaccinale centroVaccinale, String remoteClient) throws RemoteException, SQLException;

    /**
     * Metodo che si occupa della registrazione di un <b>Cittadino vaccinato</b> a DB.
     * @param cittadinoVaccinato Cittadino Vaccinato da registrare a DB.
     * @param remoteClient Riferimento del client che effettua la chiamata.
     * @return true nel caso sia registrato correttamente, false altrimenti.
     * @throws RemoteException nel caso di problemi di comunicazione.
     * @throws SQLException nel caso in cui ci siano problemi di query.
     */
    boolean registraVaccinato(CittadinoVaccinato cittadinoVaccinato, String remoteClient) throws RemoteException, SQLException;

    // OPERAZIONI CITTADINO

    /**
     * Metodo che si occupa della registrazione del <b>cittadino</b> a DB.
     * @param cittadinoRegistrato Cittadino da registrare a DB.
     * @param remoteClient Riferimento del client che effettua la chiamata.
     * @return true nel caso sia registrato correttamente, false altrimenti.
     * @throws RemoteException nel caso di problemi di comunicazione.
     * @throws SQLException nel caso in cui ci siano problemi di query.
     */
    boolean registraCittadino(CittadinoRegistrato cittadinoRegistrato, String remoteClient) throws RemoteException, SQLException;

    /**
     * Metodo che si occupa della login.
     * @param username Username dell'utente.
     * @param password Password dell'utente.
     * @param remoteClient Riferimento del client che effettua la chiamata.
     * @return true nel caso in cui avvenga con successo, false altrimenti.
     * @throws RemoteException nel caso di problemi di comunicazione.
     * @throws SQLException nel caso in cui ci siano problemi di query.
     */
    boolean login(String username, String password, String remoteClient) throws RemoteException, SQLException;

    /**
     * Metodo che si occupa della registrazione di un <b>evento avverso</b> a DB.
     * @param eventoAvverso Evento avverso da registrare.
     * @param remoteClient Riferimento del client che effettua la chiamata.
     * @return true nel caso sia registrato correttamente, false altrimenti.
     * @throws RemoteException nel caso di problemi di comunicazione.
     * @throws SQLException nel caso in cui ci siano problemi di query.
     */
    boolean registraEventoAvverso(EventoAvverso eventoAvverso, String remoteClient) throws RemoteException, SQLException;

    /**
     * Elenco dei centri vaccinali dati come risultato per la ricerca per nome.
     * @param nomeCentro Nome del centro da cercare.
     * @param remoteClient  Riferimento del client che effettua la chiamata.
     * @return Lista dei centri come risultato della ricerca.
     * @throws RemoteException nel caso di problemi di comunicazione.
     * @throws SQLException nel caso in cui ci siano problemi di query.
     */
    List<String> cercaCentroPerNome(String nomeCentro, String remoteClient) throws RemoteException, SQLException;

    /**
     * Elenco degli eventi per il centro vaccinale dati come risultato.
     * @param nomeCentro Nome del centro di cui si vuole il riepilogo.
     * @param remoteClient  Riferimento del client che effettua la chiamata.
     * @return Lista degli eventi avversi per centro vaccinale come risultato della ricerca.
     * @throws RemoteException nel caso di problemi di comunicazione.
     * @throws SQLException nel caso in cui ci siano problemi di query.
     */
    List<RiepilogoEventi> riepilogoEventi(String nomeCentro, String remoteClient) throws RemoteException, SQLException;

    /**
     * Elenco dei centri vaccinali dati come risultato per la ricerca per comune e tipologia.
     * @param comune Comune del centro da cercare.
     * @param tipologia Tipologia del centro da cercare.
     * @param remoteClient Riferimento del client che effettua la chiamata.
     * @return Lista dei centri come risultato della ricerca.
     * @throws RemoteException nel caso di problemi di comunicazione.
     * @throws SQLException nel caso in cui ci siano problemi di query.
     */
    List<String> cercaCentroPerComuneTipologia(String comune, String tipologia, String remoteClient) throws RemoteException, SQLException;

    //LISTA NOMI DEI CENTRI/IDVACCINAZIONE/CF

    /**
     * Metodo per ricavare tutti i centri vaccinali.
     * @return Lista di tutti i centri vaccinali.
     * @throws RemoteException nel caso di problemi di comunicazione.
     * @throws SQLException nel caso in cui ci siano problemi di query.
     */
    List<String> getListaCentri() throws RemoteException, SQLException;

    /**
     * Metodo per ricavare tutti i codici fiscali dalla tabella Vaccinati.
     * @return Lista di tutti i codici fiscali.
     * @throws RemoteException nel caso di problemi di comunicazione.
     * @throws SQLException nel caso in cui ci siano problemi di query.
     */
    List<String> getListaCFVaccinati() throws RemoteException, SQLException;

    /**
     * Metodo per ricavare tutti i codici fiscali dalla tabella CittadiniRegistrati.
     * @return Lista di tutti i codici fiscali.
     * @throws RemoteException nel caso di problemi di comunicazione.
     * @throws SQLException nel caso in cui ci siano problemi di query.
     */
    List<String> getListaCFCittadiniRegistrati() throws RemoteException, SQLException;

    /**
     * Metodo che verifica se è presente un id di vaccinazione all'interno della tabella Vaccinati.
     * @param idVaccinazione Id di vaccinazione da verificare.
     * @return true se è presente, false altrimenti.
     * @throws RemoteException nel caso di problemi di comunicazione.
     * @throws SQLException nel caso in cui ci siano problemi di query.
     */
    boolean idVaccinazioneVaccinatiIsPresente(short idVaccinazione) throws RemoteException, SQLException;

    /**
     * Metodo che verifica se è presente un id di vaccinazione all'interno della tabella CittadiniRegistrati.
     * @param idVaccinazione Id di vaccinazione da verificare.
     * @return true se è presente, false altrimenti.
     * @throws RemoteException nel caso di problemi di comunicazione.
     * @throws SQLException nel caso in cui ci siano problemi di query.
     */
    boolean idVaccinazioneUtenzeIsPresente(short idVaccinazione) throws RemoteException, SQLException;

    /**
     * Metodo che verifica se è presente uno user id all'interno della tabella CittadiniRegistrati.
     * @param username User id da verificare.
     * @return true se è presente, false altrimenti.
     * @throws RemoteException nel caso di problemi di comunicazione.
     * @throws SQLException nel caso in cui ci siano problemi di query.
     */
    boolean usernameIsPresente(String username) throws RemoteException, SQLException;

    /**
     * Metodo che cerca il centro vaccinale di un determinato user id.
     * @param username User id dell'utente.
     * @return Nome del centro vaccinale.
     * @throws RemoteException nel caso di problemi di comunicazione.
     * @throws SQLException nel caso in cui ci siano problemi di query.
     */
    String cercaCentroUtente(String username) throws RemoteException, SQLException;
}