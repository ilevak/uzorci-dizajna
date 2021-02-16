/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.reader;

import java.io.IOException;
import org.foi.uzdiz.ilevak_zadaca_3.izvodenje.Pocetak;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Osoba;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.OdgovorImplement;

/**
 *
 * @author ivale
 */
public class ReaderOsobe extends Reader {

    private Osoba osoba;
    int potrebniBrojElemenataLinije = 3;

    public ReaderOsobe() {
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
                upisiOsobuUListu(linijaDatoteke.split(";"));
            } catch (NumberFormatException e) {
                porukaUpozorenja += "Linija:" + linijaDatoteke
                        + "Niste upisali dobre podatke "
                        + "u liniju (idOsobe, Ime i prezime)";
            } catch (ProvjeraException ex) {
                porukaUpozorenja += "Preskacem liniju: " + linijaDatoteke + ". " + ex;
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

    private void validacijaSadrzajaLinije(String linijaDatoteke) throws ProvjeraException {
        provjeraBrojaElemenata(linijaDatoteke, potrebniBrojElemenataLinije);
    }

    private void upisiOsobuUListu(String[] elementi) throws NumberFormatException, ProvjeraException {
        osoba = new Osoba();
        osoba.setId(Integer.parseInt(elementi[0].trim()));
        osoba.setImePrezime(elementi[1].trim());
        boolean postojanjeUgovora = pretvortiIntBool(Integer.parseInt(elementi[2].trim()));
        osoba.setPostojanjeUgovora(postojanjeUgovora);
        Podaci.popisOsoba.add(osoba);
    }

    private boolean pretvortiIntBool(int broj) throws ProvjeraException {
        if (broj != 1 && broj != 0) {
            throw new ProvjeraException("Upisali ste krivi broj za postojanje ugovora!(opcije 1 ili 0)");
        } else {
            return broj != 0;
        }
    }

}
