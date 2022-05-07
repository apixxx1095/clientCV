package com.centrovaccinale.centrovaccinale.utils;

/**
 * Singleton che serve per verificare se si ha effettuato la login.
 * Se l'utente effettua la login, username viene impostato con lo user id dell'utente e
 * connected viene impostato a true.
 * In caso di logout, il riferimento del login viene impostato a null.
 * @author Jean Pierre Sotamba 728447 VA
 * @author Simone Caputo 736816 VA
 * @author Laura Gjetja 738629 VA
 * @author Erica Bonetti 740946 VA
 * @version 0.0.1
 */
public class Login {
    private static Login instance;

    private final String username;
    private final boolean connected;

    private Login(String username, boolean connected){
        this.username = username;
        this.connected = connected;
    }

    /**
     * Metodo per impostare i parametri per il login.
     * @param username User id dell'utente.
     * @return Istanza login con i parametri dell'utente.
     */
    public static Login setInstance(String username){
        if(instance == null){
            instance = new Login(username, true);
        }
        return instance;
    }

    /**
     * @return Istanza di login dell'utente.
     */
    public static Login getInstance(){
        return instance;
    }

    /**
     * @return User id dell'utente.
     */
    public String getUsername(){
        return username;
    }

    /**
     * Metodo che controlla se l'utente e' connesso.
     * @return true se il login e' andato a buon fine, false altrimenti.
     */
    public boolean getConnected(){
        return connected;
    }

    /**
     * Imposta l'istanza del login dell'utente a null.
     */
    public static void logout(){
        instance = null;
    }
}
