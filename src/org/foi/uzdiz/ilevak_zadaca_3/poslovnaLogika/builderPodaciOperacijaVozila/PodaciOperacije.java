/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderPodaciOperacijaVozila;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Osoba;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.VrstaVozila;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite.Lokacija;

/**
 *
 * @author ivale
 */
public class PodaciOperacije {

    private VrstaVozila vrstaVozila;
    private Lokacija lokacija;
    private int brojPrijedenihKm = 0;
    private Osoba osoba;
    private String opisKvara = "";
    private String upozorenje = "";

    public VrstaVozila getVrstaVozila() {
        return vrstaVozila;
    }

    public void setVrstaVozila(VrstaVozila vrstaVozila) {
        this.vrstaVozila = vrstaVozila;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public int getBrojPrijedenihKm() {
        return brojPrijedenihKm;
    }

    public void setBrojPrijedenihKm(int brojPrijedenihKm) {
        this.brojPrijedenihKm = brojPrijedenihKm;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public String getOpisKvara() {
        return opisKvara;
    }

    public void setOpisKvara(String opisKvara) {
        this.opisKvara = opisKvara;
    }

    public String getUpozorenje() {
        return upozorenje;
    }

    public void setUpozorenje(String upozorenje) {
        this.upozorenje +=upozorenje;
    }

}
