/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.reader;

/**
 *
 * @author ivale
 */
public class ReaderFactory {

    private String datoteka;

    public ReaderFactory() {
    }

    public Reader fileReader(String datoteka) {
        if ("c".equals(datoteka) || "cjenik".equals(datoteka)) {
            return new ReaderCjenik();
        }
        if ("v".equals(datoteka) || "vozila".equals(datoteka)) {
            return new ReaderVrsteVozila();
        }
        if ("k".equals(datoteka) || "kapaciteti".equals(datoteka)) {
            return new ReaderLokacijaKapaciteti();
        }
        if ("l".equals(datoteka) || "lokacije".equals(datoteka)) {
            return new ReaderLokacije();
        }
        if ("o".equals(datoteka) || "osobe".equals(datoteka)) {
            return new ReaderOsobe();
        }
        if ("s".equals(datoteka) || "aktivnosti".equals(datoteka)) {
            return new ReaderAktivnosti();
        }
        if ("os".equals(datoteka) || "struktura".equals(datoteka)) {
            return new ReaderTvrtka();
        } else {
            return null;
        }
    }

    public String getDatoteka() {
        return datoteka;
    }

    public void setDatoteka(String datoteka) {
        this.datoteka = datoteka;
    }

    public Reader fileReader() {
       return datoteka.isBlank()? null: fileReader(datoteka);
    }
}
