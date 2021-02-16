/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.reader;

import java.io.IOException;
import org.foi.uzdiz.ilevak_zadaca_3.izvodenje.Pocetak;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.VrstaVozila;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.OdgovorImplement;

/**
 *
 * @author ivale
 */
public class ReaderVrsteVozila extends Reader {

    private VrstaVozila vrstaVozila;
    int potrebniBrojElemenataLinije = 4;

    public ReaderVrsteVozila() {
        super();
    }

    @Override
    public void read(String putDatoteke) throws IOException, ProvjeraException {
        kreirajCitacDatoteke(putDatoteke);
        procitajLinijuDatoteke();
        String linijaDatoteke;
        String porukaUpozorenja = "";
        while ((linijaDatoteke = procitajLinijuDatoteke()) != null) {
            try {
                validacijaSadrzajaLinije(linijaDatoteke);
                upisiVoziloUListu(linijaDatoteke.split(";"));
            } catch (NumberFormatException e) {
                porukaUpozorenja += "Linija:" + linijaDatoteke
                        + " Niste upisali dobre podatke "
                        + "u liniju (idVrsteVozila, naziv, "
                        + "vrijemePunjenjaBaterije, domet)";
            }
            if (!porukaUpozorenja.isBlank()) {
                odgovor = new OdgovorImplement()
                        .setStatus("ERR")
                        .setPovratnaPoruka(porukaUpozorenja)
                        .build();
                Pocetak.modelOdogovor.setOdgovor(odgovor);
            }
        }

    }

    private boolean validacijaSadrzajaLinije(String linijaDatoteke) {
        return provjeraBrojaElemenata(linijaDatoteke, potrebniBrojElemenataLinije);
    }

    private void upisiVoziloUListu(String[] elementi) throws NumberFormatException {
        vrstaVozila = new VrstaVozila();
        vrstaVozila.setId(Integer.parseInt(elementi[0].trim()));
        vrstaVozila.setNaziv(elementi[1].trim());
        vrstaVozila.setVrijemePunjenaBaterije(Integer.parseInt(elementi[2].trim()));
        vrstaVozila.setDomet(Integer.parseInt(elementi[3].trim()));
        Podaci.popisVrsteVozila.add(vrstaVozila);
    }

}
