/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.izvodenje;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.Properties;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.NeispravnaKonfiguracija;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.NemaKonfiguracije;

/**
 *
 * @author ivale
 */
public class Konfiguracija {

    protected String datoteka;
    protected Properties postavke;

    public Konfiguracija() {
        this.postavke = new Properties();
    }

    public Konfiguracija(String datoteka) {
        this.datoteka = datoteka;
        this.postavke = new Properties();
    }

    public void ucitajKonfiguraciju() throws NemaKonfiguracije, NeispravnaKonfiguracija {
        ucitajKonfiguraciju(this.datoteka);
    }

    public void ucitajKonfiguraciju(String datoteka) throws NemaKonfiguracije, NeispravnaKonfiguracija {
        this.obrisiSvePostavke();

        if (datoteka == null || datoteka.length() == 0) {
            throw new NemaKonfiguracije("Neispravni naziv datoteke!" + getClass());
        }
        int p = datoteka.lastIndexOf(".");
        if (p == -1) {
            throw new NeispravnaKonfiguracija("Nema ekstenzije!");
        }
        String ekstenzije = datoteka.substring(p + 1).toLowerCase();
        if (!ekstenzije.equals("txt")) {
            throw new NeispravnaKonfiguracija("Ekstenzija nije dobra!");
        }

        File f = new File(datoteka);

        if (f.exists() && f.isFile()) {
            try {
                byte[] data;
                data = Files.readAllBytes(f.toPath());
                String contents = new String(data).replace("\\", "/");
                
                this.postavke.load(new StringReader(contents));
            } catch (IOException ex) {
                throw new NeispravnaKonfiguracija("Problem kod učitavanja konfuguracije!");
            }
        } else {
            throw new NemaKonfiguracije("Datoteka pod nazivom " + datoteka + " ne postoji ili nije datoteka!");
        }
    }

    public String dajPostavku(String postavka) {
        return this.postavke.getProperty(postavka);
    }

  
    public boolean postojiPostavka(String postavka) {
        return this.postavke.containsKey(postavka);
    }
    public Properties dajSvePostavke() {
        return this.postavke;
    }
    public boolean obrisiSvePostavke() {
        if (this.postavke.isEmpty()) {
            return false;
        } else {
            this.postavke.clear();
            return true;
        }
    }

  

   
}
