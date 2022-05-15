package com.centrovaccinale.centrovaccinale.utils;

import com.centrovaccinale.centrovaccinale.rmi.ClientImpl;
import com.centrovaccinale.centrovaccinale.rmi.Server;

import java.rmi.AccessException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Classe che fa da client per l'architettura RMI.
 * Vengono implementati i metodi dell'interfaccia Client.
 * @author Jean Pierre Sotamba 728447 VA
 * @author Simone Caputo 736816 VA
 * @author Laura Gjetja 738629 VA
 * @author Erica Bonetti 740946 VA
 * @version 0.0.1
 */
public class RunnerRMI {

    static final int OK = 1;
    static final int KO = 0;
    private static RunnerRMI instance;
    private final ClientImpl client;
    private final Registry registry;
    private Server server;

    // TODO: PASSARE PARAMETRO HOST E PORTA, IN MODO CHE SIA L'UTENTE A DIRE DOVE SI TROVA
    private RunnerRMI(String host, int port) throws RemoteException, NotBoundException {
        // IMPOSTAZIONE DELLE POLICY DI SICUREZZA
        System.setProperty("java.security.policy", "src/main/java/policies");
        System.setSecurityManager(new SecurityManager());
        //CONNESSIONE CON IL SERVER
        this.registry = LocateRegistry.getRegistry(host, port);
        this.server = (Server) registry.lookup("ServerCentroVaccinale");
        this.client = new ClientImpl();
        System.err.println("Client: " + client);
        System.err.println("Registry: " + registry);
        System.err.println("Server: " + server);
        if(server.connectionOK(client)){
            System.out.println("Connessione al server andata a buon fine!\n");
        }
    }

    /**
     * Metodo che si occupa della connessione col server.
     * Gli vengono passati host e port per indicare al registry dove si trova il server.
     * @param host host del server.
     * @param port porta in cui il registry cerca il server.
     */
    public static synchronized RunnerRMI setInstance(String host, int port) {
        try {
            if(instance == null){
                instance = new RunnerRMI(host, port);
            }
        } catch (NotBoundException | RemoteException e) {
            return null;
        }
        return instance;
    }

    /**
     * @return RunnerRMI Oggetto il quale inizializza il client
     */
    public static synchronized RunnerRMI getInstance(){
        return instance;
    }

    /**
     * Metodo che si occupa della verifica della connessione fra client e server.
     * Nel caso in cui il server venga terminato si ritenta la connessione.
     * @return true nel caso in cui la connessione va a buon fine, false altrimenti.
     * @throws NotBoundException nel caso in cui non si trova il server.
     * @throws RemoteException nel caso in cui ci siano problemi di comunicazione.
     */
    public static synchronized boolean tryConnectionServer() throws NotBoundException, RemoteException {
        boolean connesso = false;
        try {
            if(RunnerRMI.getInstance().getServer() != null){
                connesso =  RunnerRMI.getInstance().getServer().verifyConnection(RunnerRMI.getInstance().getClient());
            }
            else{
                RunnerRMI.getInstance().setServer((Server) RunnerRMI.getInstance().getRegistry().lookup("ServerCentroVaccinale"));
                if(RunnerRMI.getInstance().getServer() != null){
                    if(RunnerRMI.getInstance().getServer().connectionOK(RunnerRMI.getInstance().getClient())){
                        System.out.println("Connessione con il server ristabilita!");
                        connesso = true;
                    }
                }
            }
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Non si riesci a connettersi al server, prova a riavviarlo!");
        }
        return connesso;
    }

    /**
     * Metodo che ritorna il riferimento al server collegato.
     * @return Server Riferimento al server collegato.
     * */
    public Server getServer() {
        return server;
    }

    /**
     * Metodo che setta un nuovo server nel caso in cui si perde la connessione.
     * @param server riferimento al server.
     */
    public synchronized void setServer(Server server) {
        this.server = server;
    }

    /**
     * @return Registry
     * */
    public synchronized Registry getRegistry() {
        return registry;
    }

    /**
     * @return ClientImpl
     * */
    public synchronized ClientImpl getClient(){
        return client;
    }

}
