/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.podaci;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Redak;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ilevak_zadaca_3.izvodenje.Pocetak;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.Odgovor;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.OdgovorImplement;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.TablicaDec;

/**
 *
 * @author ivale
 */
public class Tablica {

    List<Redak> retci = new ArrayList<>();
    TablicaDec tablicaDecorator;
    String tablica;
    String format;
    String vertikala;

    public Tablica(TablicaDec tablicaDec) {
        tablicaDecorator = tablicaDec;
        vertikala = tablicaDecorator.getVertikala();
        format = tablicaDecorator.getFormat();
    }

    /**
     * Potrebno je uvijek slati redak sa nazivom lokacije kako bi se moglo
     * nadodati na vec postojece retke
     *
     * @param redak
     */
    public void addRedak(Redak redak) {
        if (redak.getNazivLok() != null && redak.getNazivVozila() != null) {
            for (Redak r : retci) {
                if (r.getNazivLok().equals(redak.getNazivLok())
                        && r.getNazivVozila().equals(redak.getNazivVozila())) {
                    redak = dodajVrijednostiRetku(r, redak);
                }
            }
            retci.add(redak);
        } else if (redak.getIdOsoba() != 0) {

            retci.add(redak);
        }
    }

    public String getTablica() {
        tablica = tablicaDecorator.getZaglavlje();
        tablica += vertikala;
        String redakString;
        for (Redak redak : retci) {
            redakString = tablicaDecorator.napraviRedak(redak);
            if (!tablica.contains(redakString)) {
                tablica += redakString;
                //tablica += vertikala;
            }
        }
        tablica += vertikala;
        return tablica;
    }

    public void setTablica(String tablica) {
        this.tablica = tablica;
    }

    /**
     * Provjerava koje su varijable u retku popunjene i nadodaje na vec
     * napravljene retke
     *
     * @param r1 postojeci red na koji se nadodaju vrijednosti
     * @param r2 red koji je novi, koji se nadodaje na postojeci
     * @return r1
     */
    private Redak dodajVrijednostiRetku(Redak r1, Redak r2) {
        for (Field f : r2.getClass().getDeclaredFields()) {
            try {
                if (f.get(r2) != null) {
                    f.set(r1, f.get(r2));
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Odgovor odgovor=new OdgovorImplement().
                        setStatus("ERR")
                        .setStatus("Nesto je poslo po zlu sa upisom redaka!")
                        .build();
                Pocetak.modelOdogovor.setOdgovor(odgovor);
            }
        }
        return r1;
    }

}
