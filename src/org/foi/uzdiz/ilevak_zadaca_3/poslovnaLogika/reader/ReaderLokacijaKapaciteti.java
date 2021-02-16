/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.reader;

import java.io.IOException;
import org.foi.uzdiz.ilevak_zadaca_3.izvodenje.Pocetak;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.KapacitetLokacije;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite.Lokacija;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Vozilo;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.VrstaVozila;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.OdgovorImplement;

/**
 *
 * @author ivale
 */
public class ReaderLokacijaKapaciteti extends Reader {

    private KapacitetLokacije kapacitetLokacije;
    int potrebniBrojElemenataLinije = 4;
    int ident = 1;
    Vozilo vozilo = new Vozilo();

    public ReaderLokacijaKapaciteti() {
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
                upisiKapacitetLokacijeUListu(linijaDatoteke.split(";"));
            } catch (NumberFormatException e) {
                porukaUpozorenja += "Linija:" + linijaDatoteke
                        + " Niste upisali dobre podatke "
                        + "u liniju (idLokacije, naziv, "
                        + "adresa, koordinate)";
            } catch (ProvjeraException ex) {
                porukaUpozorenja += "Preskacem liniju: " + linijaDatoteke+ ". " + ex ;
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

    private void upisiKapacitetLokacijeUListu(String[] elementi) throws NumberFormatException, ProvjeraException {

        int idVozila = Integer.parseInt(elementi[1].trim());
        int idLokacije = Integer.parseInt(elementi[0].trim());
        int brojVozila = Integer.parseInt(elementi[3].trim());
        int brojMjesta = Integer.parseInt(elementi[2].trim());
        if (provjeriPostojanjeLokacije(idLokacije) && provjeriPostojanjeVozila(idVozila)) {
            upisi(brojMjesta, idLokacije, idVozila, brojVozila);
        } else if (!provjeriPostojanjeVozila(idVozila)) {
            throw new ProvjeraException("Vozilo sa id: " + idVozila + " ne postoji!");
        } else {
            throw new ProvjeraException("Lokacija sa id: " + idLokacije + " ne postoji!");
        }

    }

    private void upisi(int brojMjesta, int idLokacije, int idVozila, int brojVozila) {
        kapacitetLokacije = new KapacitetLokacije();
        kapacitetLokacije.setBrojMjestaZaVrstuVozila(brojMjesta);
        Lokacija lokacija = Podaci.getInstance().pronadiLokacijuPutemId(idLokacije);
        kapacitetLokacije.setLokacija(lokacija);
        VrstaVozila vrstaVozila = Podaci.getInstance().pronadiVrstuVozilaPutemId(idVozila);
        kapacitetLokacije.setVrstaVozila(vrstaVozila);
        Podaci.popisLokacijaKapaciteti.add(kapacitetLokacije);

        vozilo.setVrstaVozila(vrstaVozila);
        kreirajVozilaNaLokaciji(lokacija, brojVozila);
    }

    private boolean provjeriPostojanjeLokacije(int idLokacije) {

        return !Podaci.popisLokacija.isEmpty() && Podaci.getInstance().pronadiLokacijuPutemId(idLokacije) != null;
    }

    private void kreirajVozilaNaLokaciji(Lokacija lokacija, int brojVozila) {
        Vozilo kloniranoVozilo;
        for (int i = 0; i < brojVozila; i++) {
            kloniranoVozilo = vozilo.kloniraj();
            kloniranoVozilo.setId(ident);
            ident++;
            lokacija.popisVozila.add(kloniranoVozilo);
        }
    }

}
