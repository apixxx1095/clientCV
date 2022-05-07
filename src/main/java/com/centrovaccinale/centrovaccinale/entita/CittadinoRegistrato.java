package com.centrovaccinale.centrovaccinale.entita;

import java.io.Serializable;

/**
 * Classe mappa riferita alla tabella <b>cittadini registrati</b> del DB.
 * Serve per quando il client invoca il metodo riguardo la registrazione
 * a sistema di un <b>Cittadino</b> e lo passa come parametro del metodo del server.
 * @author Jean Pierre Sotamba 728447 VA
 * @author Simone Caputo 736816 VA
 * @author Laura Gjetja 738629 VA
 * @author Erica Bonetti 740946 VA
 * @version 0.0.1
 * */
public class CittadinoRegistrato implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String nomeUtente;
    private final String cognomeUtente;
    private final String codFiscale;
    private final String email;
    private final String username;
    private final String password;
    private final short idVaccinazione;

    /**
     * @param nomeUtente nome del cittadino.
     * @param cognomeUtente cognome del cittadino.
     * @param codFiscale codice fiscale del cittadino.
     * @param email email del cittadino.
     * @param username username del cittadino.
     * @param password password del cittadino.
     * @param idVaccinazione id univoco di vaccinazione del cittadino.
     */
    public CittadinoRegistrato(
            String nomeUtente,
            String cognomeUtente,
            String codFiscale,
            String email,
            String username,
            String password,
            short idVaccinazione){
        this.nomeUtente = nomeUtente;
        this.cognomeUtente = cognomeUtente;
        this.codFiscale = codFiscale;
        this.email = email;
        this.username = username;
        this.password = password;
        this.idVaccinazione = idVaccinazione;
    }

    /**
     * Metodo che restituisce il nome dell'utente registrato.
     * @return Nome dell'utente registrato.
     */
    public String getNomeUtente() {
        return nomeUtente;
    }

    /**
     * Metodo che restituisce il cognome dell'utente registrato.
     * @return Cognome dell'utente registrato.
     */
    public String getCognomeUtente() {
        return cognomeUtente;
    }

    /**
     * Metodo che restituisce il codice fiscale dell'utente registrato.
     * @return Codice fiscale dell'utente registrato.
     */
    public String getCodFiscale() {
        return codFiscale;
    }

    /**
     * Metodo che restituisce l'email dell'utente registrato.
     * @return Email dell'utente registrato.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metodo che restituisce lo username dell'utente registrato.
     * @return Username dell'utente registrato.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Metodo che restituisce la passowrd dell'utente registrato.
     * @return Password dell'utente registrato.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Metodo che restituisce l'id univoco di vaccinazione dell'utente registrato.
     * @return Id univoco di vaccinazione dell'utente registrato.
     */
    public short getIdVaccinazione() {
        return idVaccinazione;
    }
}
