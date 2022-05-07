package com.centrovaccinale.centrovaccinale.entita;

import java.io.Serializable;

/**
 * Classe mappa riferita alla tabella <b>eventi avversi</b> del DB.
 * Serve per quando il client invoca il metodo riguardo la registrazione
 * di un <b>Evento Avverso</b> e lo passa come parametro del metodo del server.
 * @author Jean Pierre Sotamba 728447 VA
 * @author Simone Caputo 736816 VA
 * @author Laura Gjetja 738629 VA
 * @author Erica Bonetti 740946 VA
 * @version 0.0.1
 * */
public class EventoAvverso implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String nomeCentro;
    private final String evento;
    private final int severita;
    private final String note;

    /**
     * @param nomeCentro nome del centro per cui si registra l'evento.
     * @param evento tipo di evento (mal di testa, febbre, dolori articolari, ecc...).
     * @param severita livello di severita riguardante l'evento (1-5).
     * @param note campo facoltativo, nel caso in cui si voglia aggiungere una nota.
     */
    public EventoAvverso(String nomeCentro, String evento, int severita, String note){
        this.nomeCentro = nomeCentro;
        this.evento = evento;
        this.severita = severita;
        this.note = note;
    }

    /**
     * Metodo che restituisce il nome del centro vaccinale per cui stiamo registrando un evento.
     * @return Nome del centro vaccinale per cui stiamo registrando un evento.
     */
    public String getNomeCentro() {
        return nomeCentro;
    }

    /**
     * Metodo che restituisce il tipo di evento avverso.
     * @return Tipo di evento registrato.
     */
    public String getEvento() {
        return evento;
    }

    /**
     * Metodo che restituisce la severita dell'evento avverso.
     * @return Severita riferita all'evento da registrare.
     */
    public int getSeverita() {
        return severita;
    }

    /**
     * Metodo che restituisce le note facoltative riguardanti l'evento avverso.
     * @return Nota facoltativa sull'evento da registrare.
     */
    public String getNote() {
        return note;
    }
}
