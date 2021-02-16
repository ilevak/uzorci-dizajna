/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ilevak_zadaca_3.izvodenje.Pocetak;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Cjenik;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.OdgovorImplement;

/**
 *
 * @author ivale
 */
public class ReaderCjenik extends Reader {

    private Cjenik cjenik;
    int potrebniBrojElemenataLinije = 4;
    List<Cjenik> popisCijena = new ArrayList<>();

    public ReaderCjenik() {
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
                    upisiCjenikUListu(linijaDatoteke.split(";"));
                } catch (NumberFormatException e) {
                    porukaUpozorenja += "Linija:" + linijaDatoteke
                            + "Niste upisali dobre podatke "
                            + "u liniju (idVrsteVozila, cjenaNajma, "
                            + "cijenaPoSatu, cijenaPoKm)";
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

    private void validacijaSadrzajaLinije(String linijaDatoteke) throws ProvjeraException{
         provjeraBrojaElemenata(linijaDatoteke, potrebniBrojElemenataLinije);
    }

    private void upisiCjenikUListu(String[] elementi) throws NumberFormatException, ProvjeraException {
        int idVozila = Integer.parseInt(elementi[0].trim());
        if (provjeriPostojanjeVozila(idVozila)) {
            kreirajCjenik(idVozila, elementi);
            Podaci.popisCijena.add(cjenik);
        } else {
            throw new ProvjeraException("Vozilo sa id: " + idVozila + " ne postoji!");
        }
    }

    private void kreirajCjenik(int idVozila, String[] elementi) throws NumberFormatException {
        cjenik = new Cjenik();
        cjenik.setVrstaVozila(Podaci.getInstance().pronadiVrstuVozilaPutemId(idVozila));
        cjenik.setCijenaNajma(Float.parseFloat(elementi[1].trim().replace(",", ".")));
        cjenik.setCijenaPoSatu(Float.parseFloat(elementi[2].trim().replace(",", ".")));
        cjenik.setCijenaPoKm(Float.parseFloat(elementi[3].trim().replace(",", ".")));
    }

}
