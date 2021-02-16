/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import org.foi.uzdiz.ilevak_zadaca_3.izvodenje.Pocetak;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.Odgovor;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.OdgovorImplement;

/**
 *
 * @author ivale
 */
public abstract class Reader {

    public BufferedReader citacDatoteke = null;
    public String linija;
    protected Odgovor odgovor;

    /**
     *
     */
    public Reader() {

    }

    public abstract void read(String putDatoteke) throws IOException, ProvjeraException;

    public void kreirajCitacDatoteke(String putDatoteke) throws IOException, ProvjeraException {

        File datoteka = new File(putDatoteke);

        provjeraPopunjenostiDatoteke(datoteka, putDatoteke);
        provjeraTekstualneDatoteke(putDatoteke);
         citacDatoteke = new BufferedReader(new InputStreamReader(new FileInputStream(datoteka), Charset.forName("UTF-8")));
        
    }

    private void provjeraPopunjenostiDatoteke(File datoteka, String putDatoteke) throws ProvjeraException {
        if (datoteka.length() == 0) {
            throw new ProvjeraException("Datoteka " + putDatoteke + " je prazna!"
                    + "Vodite racuna o putanji konzole! ");
        }
    }

    public void provjeraTekstualneDatoteke(String putDatoteke) throws ProvjeraException {
        if (!putDatoteke.trim().endsWith(".txt")) {
            throw new ProvjeraException("Put datoteke" + putDatoteke
                    + "Nije napisano ispravno,"
                    + "putanja ne ukazuje na tekstualnu datoteku!");

        }
    }

    public String procitajLinijuDatoteke() {
        try {
            linija = citacDatoteke.readLine();
            return linija;
        } catch (IOException ex) {
            odgovor = new OdgovorImplement()
                    .setStatus("ERR")
                    .setPovratnaPoruka("Ne moze se procitati linija!")
                    .build();
            Pocetak.modelOdogovor.setOdgovor(odgovor);
        }
        return null;
    }

    public boolean provjeriPostojanjeVozila(int idVozila) {
        return Podaci.getInstance().pronadiVrstuVozilaPutemId(idVozila) != null;

    }

    public boolean provjeraBrojaElemenata(String linijaDatoteke, int potrebniBrojElemenataLinije) throws ProvjeraException {
        int brojElemenataLinije = linijaDatoteke.split(";").length;

        if (brojElemenataLinije != potrebniBrojElemenataLinije) {
            throw new ProvjeraException("Linija : " + linijaDatoteke
                    + "Nije napisana ispravno, potrebno je unjeti "
                    + potrebniBrojElemenataLinije + " elementa!");
        }
        return true;
    }

}
