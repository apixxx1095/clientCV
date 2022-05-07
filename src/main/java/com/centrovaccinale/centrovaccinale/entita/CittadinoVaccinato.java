package com.centrovaccinale.centrovaccinale.entita;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe mappa riferita alla tabella <b>cittadini vaccinati</b> del DB.
 * Serve per quando il client invoca il metodo riguardo la registrazione
 * di un <b>Cittadino Vaccinato</b> e lo passa come parametro del metodo del server.
 * @author Jean Pierre Sotamba 728447 VA
 * @author Simone Caputo 736816 VA
 * @author Laura Gjetja 738629 VA
 * @author Erica Bonetti 740946 VA
 * @version 0.0.1
 * */
public class CittadinoVaccinato implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String nomeCentro;
    private final String nomeCittadino;
    private final String cognomeCittadino;
    private final String codFiscale;
    private final String tipoVaccino;
    private final LocalDate dataSomministrazioneVaccino;
    private final short idVaccinazione;

    public CittadinoVaccinato(
            String nomeCentro,
            String nomeCittadino,
            String cognomeCittadino,
            String codFiscale,
            String tipoVaccino,
            LocalDate dataSomministrazioneVaccino,
            short idVaccinazione){
        this.nomeCentro = nomeCentro;
        this.nomeCittadino = nomeCittadino;
        this.cognomeCittadino = cognomeCittadino;
        this.codFiscale = codFiscale;
        this.tipoVaccino = tipoVaccino;
        this.dataSomministrazioneVaccino = dataSomministrazioneVaccino;
        this.idVaccinazione = idVaccinazione;
    }

    /**
     * @return Nome del centro in cui si è vaccinato il cittadino.
     */
    public String getNomeCentro() {
        return nomeCentro;
    }

    /**
     * @return Nome del cittadino vaccinato.
     */
    public String getNomeCittadino() {
        return nomeCittadino;
    }

    /**
     * @return Cognome del cittadino vaccinato.
     */
    public String getCognomeCittadino() {
        return cognomeCittadino;
    }

    /**
     * @return Codice Fiscale del cittadino vaccinato.
     */
    public String getCodFiscale() {
        return codFiscale;
    }

    /**
     * @return Tipo di vaccino somministrato del cittadino vaccinato.
     */
    public String getTipoVaccino() {
        return tipoVaccino;
    }

    /**
     * @return Data della somministrazione del vaccino.
     */
    public LocalDate getDataSomministrazioneVaccino() {
        return dataSomministrazioneVaccino;
    }

    /**
     * @return Id di vaccinazione, generato automaticamente, del cittadino vaccinato.
     */
    public short getIdVaccinazione() {
        return idVaccinazione;
    }
}
