package com.centrovaccinale.centrovaccinale.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Metodo per la verifica della connessione del cliente
 * @author Jean Pierre Sotamba 728447 VA
 * @author Simone Caputo 736816 VA
 * @author Laura Gjetja 738629 VA
 * @author Erica Bonetti 740946 VA
 * @version 0.0.1
 */
public interface Client extends Remote {

    /**
     * Metodo per la verifica della connessione del cliente al server.
     * @return Username del riferimento del client.
     * @throws RemoteException nel caso in cui ci siano problemi di comunicazione.
     */
    String getUsername() throws RemoteException;
}
