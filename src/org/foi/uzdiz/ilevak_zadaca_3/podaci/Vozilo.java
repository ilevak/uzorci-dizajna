package org.foi.uzdiz.ilevak_zadaca_3.podaci;

import java.util.Date;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.stateVozilo.SlobodnoState;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.stateVozilo.StateVozilo;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ivale
 */
public class Vozilo {

    private StateVozilo status;
    private Date datumNajma = null;
    private Date datumVracanja = null;
    private float postotakUtroseneBaterije = 0;
    private int ukupanBrKm = 0;
    private int id = 0;
    private VrstaVozila vrstaVozila = new VrstaVozila();
    public int brojNajma = 0;
    private String opisKvara = "";
    private int idLokacijeNajma;

    public Vozilo() {
        this.status = new SlobodnoState();
    }

    public Vozilo(Vozilo vozilo) {
        if (vozilo != null) {
            this.status = new SlobodnoState();
            this.vrstaVozila = vozilo.vrstaVozila;
            this.postotakUtroseneBaterije = vozilo.postotakUtroseneBaterije;
            this.ukupanBrKm = vozilo.ukupanBrKm;
            this.datumNajma = vozilo.datumNajma;
            this.datumVracanja = vozilo.datumVracanja;
        }
    }

    public VrstaVozila getVrstaVozila() {
        return vrstaVozila;
    }

    public void setVrstaVozila(VrstaVozila vrstaVozila) {
        this.vrstaVozila = vrstaVozila;
    }

    public int getIdLokacijeNajma() {
        return idLokacijeNajma;
    }

    public void setIdLokacijeNajma(int idLokacijeNajma) {
        this.idLokacijeNajma = idLokacijeNajma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vozilo kloniraj() {
        return new Vozilo(this);
    }

    public float getPostotakUtroseneBaterije() {
        return postotakUtroseneBaterije;
    }

    public void setPostotakUtroseneBaterije(float postotakUtroseneBaterije) {
        this.postotakUtroseneBaterije = postotakUtroseneBaterije;
    }

    public int getUkupanBrKm() {
        return ukupanBrKm;
    }

    public void setUkupanBrKm(int ukupanBrojKilometara) {
        this.ukupanBrKm = ukupanBrojKilometara;
    }

    public Date getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(Date datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public Date getDatumNajma() {
        return datumNajma;
    }

    public void setDatumNajma(Date datumNajma) {
        this.datumNajma = datumNajma;
    }

    public int getBrojNajma() {
        return brojNajma;
    }

    public void setBrojNajma(int brojNajma) {
        this.brojNajma = brojNajma;
    }

    public String getOpisKvara() {
        return opisKvara;
    }

    public void setOpisKvara(String opisKvara) {
        this.opisKvara = opisKvara;
    }

    public void removeDatumVracanja() {
        this.datumVracanja = null;
    }

    public void removeDatumNajma() {
        this.datumNajma = null;
    }

    public int getIdVrsteVozila() {
        return vrstaVozila.getId();
    }

    public StateVozilo getStatus() {
        return status;
    }

    public void setStatus(StateVozilo status) {
        this.status = status;
    }

    public boolean sljedeceStanje(int idVrste) {
        return status.sljedeceStanje(this, idVrste);
    }

    public boolean iznajmi(int idVrste) {
        return status.iznajmiVozilo(this, idVrste);
    }

    public boolean vrati(int idVrste, int brojPrijedenihKm, String opisKvara) {
        return status.vratiVozilo(this, idVrste, opisKvara, brojPrijedenihKm);
    }

    public boolean jeRaspolozivo(int idVrste) {
        return status.raspolozivo(this, idVrste);
    }

    public boolean jeStrgano(int idVrste) {
        return status.strgano(this, idVrste);
    }

}
