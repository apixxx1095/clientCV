package com.centrovaccinale.centrovaccinale.entita;

import java.io.Serializable;

/**
 * Classe in cui si inserisce il riepilogo eventi di un determinato centro vaccinale.
 * @author Jean Pierre Sotamba 728447 VA
 * @author Simone Caputo 736816 VA
 * @author Laura Gjetja 738629 VA
 * @author Erica Bonetti 740946 VA
 * @version 0.0.1
 */
public class RiepilogoEventi implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String nomeEvento;
    private final int numeroEventi;
    private final double mediaSeverita;

    /**
     * @param nomeEvento tipo dell'evento (mal di testa, febbre, ...)
     * @param numeroEventi frequenza di uno stesso evento.
     * @param mediaSeverita media di severita dell'evento.
     */
    public RiepilogoEventi(String nomeEvento, int numeroEventi, double mediaSeverita){
        this.nomeEvento = nomeEvento;
        this.numeroEventi = numeroEventi;
        this.mediaSeverita = mediaSeverita;
    }

    /**
     * @return nome della tipologia dell'evento avverso registrato.
     */
    public String getNomeEvento() {
        return nomeEvento;
    }

    /**
     * @return frequenza di uno stesso evento.
     */
    public int getNumeroEventi() {
        return numeroEventi;
    }

    /**
     * @return media delle severita di uno stesso evento.
     */
    public double getMediaSeverita() {
        return mediaSeverita;
    }

    @Override
    public String toString() {
        return "Evento: '" + nomeEvento + '\'' +
                "\nFrequenza: " + numeroEventi +
                "\nMedia severita: " + mediaSeverita;
    }
}
