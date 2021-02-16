/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.reader;

import java.io.IOException;
import org.foi.uzdiz.ilevak_zadaca_3.izvodenje.Pocetak;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Koordinata;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite.Lokacija;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.OdgovorImplement;

/**
 *
 * @author ivale
 */
public class ReaderLokacije extends Reader {

    private Lokacija lokacije;
    int potrebniBrojElemenataLinije = 4;

    public ReaderLokacije() {
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
                upisiLokacijuUListu(linijaDatoteke.split(";"));
            } catch (NumberFormatException e) {
                porukaUpozorenja += "Linija:" + linijaDatoteke
                        + "Niste upisali dobre podatke "
                        + "u liniju (idLokacije, naziv, "
                        + "adresa, koordinate) ";
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

    private void upisiLokacijuUListu(String[] elementi) throws NumberFormatException {
        lokacije = new Lokacija();
        lokacije.setId(Integer.parseInt(elementi[0].trim()));
        lokacije.setNaziv(elementi[1].trim());
        lokacije.setAdresa(elementi[2].trim());
        lokacije.setKoordinata(kreirajKoordinatu(elementi[3]));
        Podaci.popisLokacija.add(lokacije);
    }

    private Koordinata kreirajKoordinatu(String elementKoordinate) throws ProvjeraException {
        String[] koordinate = elementKoordinate.split(",");
        int brojPodataka = koordinate.length;
        int potrebniBrojPodataka = 2;
        double geoSirina;
        double geoVisina;
        if (brojPodataka == potrebniBrojPodataka) {
            geoSirina = Double.parseDouble(koordinate[0]);
            geoVisina = Double.parseDouble(koordinate[1]);
            return new Koordinata(geoSirina, geoVisina);
        } else {
            throw new ProvjeraException("Niste upisali dobru koordinatu: " + koordinate[0]
                    + ", " + koordinate[1]);
        }

    }

}
