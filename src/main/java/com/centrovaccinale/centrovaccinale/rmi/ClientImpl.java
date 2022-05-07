package com.centrovaccinale.centrovaccinale.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

/**
 * Classe che fa da client per l'architettura RMI.
 * Vengono implementati i metodi dell'interfaccia Client.
 * @author Jean Pierre Sotamba 728447 VA
 * @author Simone Caputo 736816 VA
 * @author Laura Gjetja 738629 VA
 * @author Erica Bonetti 740946 VA
 * @version 0.0.1
 */
public class ClientImpl extends UnicastRemoteObject implements Client, Serializable {
    private static final long serialVersionUID = 1L;
    private final String username;

    /**
     * @throws RemoteException in caso di errori.
     */
    public ClientImpl() throws RemoteException {
        super();
        this.username = this.getRef().toString();
    }

    /**
     * Metodo utilizzato dal server per verificare se il Client e' ancora connesso.
     * @return Riferimento della classe ClientImpl sottoforma di stringa.
     * @throws RemoteException nel caso in cui ci siano problemi di comunicazione tra client-server.
     */
    @Override
    public String getUsername() throws RemoteException {
        return username;
    }


    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if((obj == null) || getClass() != obj.getClass()){
            return false;
        }
        ClientImpl d = (ClientImpl) obj;
        return Objects.equals(username, d.username);
    }


    @Override
    public int hashCode() {
        int hash = 10;
        hash = 31 * hash + (username == null ? 0 : username.hashCode());
        System.err.println("hash: " + hash);
        return hash;
    }
}
