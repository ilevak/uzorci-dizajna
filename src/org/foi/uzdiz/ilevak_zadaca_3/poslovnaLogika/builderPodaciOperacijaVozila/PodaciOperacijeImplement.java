/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderPodaciOperacijaVozila;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Osoba;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.VrstaVozila;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite.Lokacija;

/**
 *
 * @author ivale
 */
public class PodaciOperacijeImplement implements PodaciOperacijeBuilder {

    PodaciOperacije podaciOperacije;

    public PodaciOperacijeImplement() {
        this.podaciOperacije = new PodaciOperacije();
    }

    @Override
    public PodaciOperacije build() {
        return podaciOperacije;
    }

    @Override
    public PodaciOperacijeBuilder setBrojPrijedenihKm(int brojPrijedenihKm) {
        if (podaciOperacije.getVrstaVozila().getDomet() < brojPrijedenihKm) {
            podaciOperacije.setUpozorenje("Vraceno vozilo ima veci broj kilometara od dometa vrstevozila");
        } else {
            podaciOperacije.setBrojPrijedenihKm(brojPrijedenihKm);
        }
        return this;
    }

    @Override
    public PodaciOperacijeBuilder setVrstaVozila(int idVrsteVozila) {
        VrstaVozila vrstaVozila = Podaci.getInstance().pronadiVrstuVozilaPutemId(idVrsteVozila);
        if (vrstaVozila != null) {
            podaciOperacije.setVrstaVozila(vrstaVozila);
        } else {
            podaciOperacije.setUpozorenje("Vozilo sa id: " + idVrsteVozila + " ne postoji");
        }
        return this;
    }

    @Override
    public PodaciOperacijeBuilder setLokacija(int idLokacija) {
        Lokacija lokacija = Podaci.getInstance().pronadiLokacijuPutemId(idLokacija);
        if (lokacija != null) {
            podaciOperacije.setLokacija(lokacija);
        } else {
            podaciOperacije.setUpozorenje("Lokacija sa id: " + idLokacija + " ne postoji");

        }
        return this;
    }

    @Override
    public PodaciOperacijeBuilder setOsoba(int idOsoba) {
        Osoba osoba = Podaci.getInstance().pronadiOsobuPutemId(idOsoba);
        if (osoba != null) {
            podaciOperacije.setOsoba(osoba);
        } else {
            podaciOperacije.setUpozorenje("Osoba sa id: " + idOsoba + " ne postoji");

        }
        return this;
    }

    @Override
    public PodaciOperacijeBuilder setOpisKvara(String opisKvara) {
       podaciOperacije.setOpisKvara(opisKvara);
        return this;
    }

}
