/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.podaci;

import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite.Lokacija;

/**
 *
 * @author ivale
 */
public class KapacitetLokacije {

    Lokacija Lokacija;
    VrstaVozila vrstaVozila;
    int ukupniBrojMjesta;

    public KapacitetLokacije() {
    }

    public KapacitetLokacije(Lokacija Lokacija, VrstaVozila vrstaVozila, int ukupniBrojMjesta) {
        this.Lokacija = Lokacija;
        this.vrstaVozila = vrstaVozila;
        this.ukupniBrojMjesta = ukupniBrojMjesta;
    }

    public Lokacija getLokacija() {
        return Lokacija;
    }

    public void setLokacija(Lokacija Lokacija) {
        this.Lokacija = Lokacija;
    }

    public VrstaVozila getVrstaVozila() {
        return vrstaVozila;
    }

    public void setVrstaVozila(VrstaVozila vrstaVozila) {
        this.vrstaVozila = vrstaVozila;
    }

    public int getBrojMjestaZaVrstuVozila() {
        return ukupniBrojMjesta;
    }

    public void setBrojMjestaZaVrstuVozila(int brojMjestaZaVrstuVozila) {
        this.ukupniBrojMjesta = brojMjestaZaVrstuVozila;
    }

}
