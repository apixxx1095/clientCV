package com.centrovaccinale.centrovaccinale.entita;

import java.io.Serializable;

/**
 * Classe mappa riferita alla tabella <b>centri vaccinali</b> del DB.
 * Serve per quando il client invoca il metodo riguardo la registrazione
 * di un <b>Centro Vaccinale</b> e lo passa come parametro del metodo del server.
 * @author Jean Pierre Sotamba 728447 VA
 * @author Simone Caputo 736816 VA
 * @author Laura Gjetja 738629 VA
 * @author Erica Bonetti 740946 VA
 * @version 0.0.1
 * */
public class CentroVaccinale implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String nomeCentro;
    private final String qualificatore;
    private final String indirizzo;
    private final int numeroCivico;
    private final String comune;
    private final String provincia;
    private final String cap;
    private final String tipologia;


    /**
     * @param nomeCentro nome del centro vaccinale.
     * @param qualificatore qualificatore della via, viale o piazza per l'indirizzo del centro vaccinale.
     * @param indirizzo nome della via, viale o piazza del centro vaccinale.
     * @param numeroCivico numero civico della via, viale o piazza del centro vaccinale.
     * @param comune comune del centro vaccinale.
     * @param provincia provincia del centro vaccinale.
     * @param cap cap del centro vaccinale.
     * @param tipologia tipologia del centro vaccinale, hub, aziendale oppure ospedaliero.
     */
    public CentroVaccinale(
            String nomeCentro,
            String qualificatore,
            String indirizzo,
            int numeroCivico,
            String comune,
            String provincia,
            String cap,
            String tipologia){
        this.nomeCentro = nomeCentro;
        this.qualificatore = qualificatore;
        this.indirizzo = indirizzo;
        this.numeroCivico = numeroCivico;
        this.comune = comune;
        this.provincia = provincia;
        this.cap = cap;
        this.tipologia = tipologia;
    }

    /**
     * Metodo che restituisce il nome del centro vaccinale registrato.
     * @return Nome del centro vaccinale registrato.
     */
    public String getNomeCentro() {
        return nomeCentro;
    }

    /**
     * Metodo che restituisce il qualificatore dell'indirizzo del centro vaccinale registrato.
     * @return Via, viale o piazza dell'indirizzo del centro vaccinale registrato.
     */
    public String getQualificatore() {
        return qualificatore;
    }

    /**
     * Metodo che restituisce il nome della via, viale o piazza del centro vaccinale registrato.
     * @return Nome della via, viale o piazza del centro vaccinale registrato.
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Metodo che restituisce il numero civico della via, viale o piazza del centro vaccinale registrato.
     * @return Numero civico della via, viale o piazza del centro vaccinale registrato.
     */
    public int getNumeroCivico() {
        return numeroCivico;
    }

    /**
     * Metodo che restituisce il comune del centro vaccinale registrato.
     * @return Comune del centro vaccinale registrato.
     */
    public String getComune() {
        return comune;
    }

    /**
     * Metodo che restituisce la provincia del centro vaccinale registrato.
     * @return Provincia del centro vaccinale registrato.
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Metodo che restituisce il CAP del centro vaccinale registrato.
     * @return CAP del centro vaccinale registrato.
     */
    public String getCap() {
        return cap;
    }

    /**
     * Metodo che restituisce la tipologia del centro vaccinale registrato.
     * @return Tipologia di centro vaccinale registrato, hub, aziendale oppure ospedaliero.
     */
    public String getTipologia() {
        return tipologia;
    }

}
