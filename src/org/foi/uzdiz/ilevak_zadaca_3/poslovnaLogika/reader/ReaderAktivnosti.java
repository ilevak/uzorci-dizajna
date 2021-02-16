/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.reader;

import java.io.IOException;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.Controller.Controller;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.View.InteraktivnoView;
import org.foi.uzdiz.ilevak_zadaca_3.izvodenje.Pocetak;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.OdgovorImplement;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain.AbstractAktivnost;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain.Lanac;

/**
 *
 * @author ivale
 */
public class ReaderAktivnosti extends Reader {

    boolean radi = true;

    public ReaderAktivnosti() {
        super();
    }

    @Override
    public void read(String putDatoteke) throws IOException, ProvjeraException {
        kreirajCitacDatoteke(putDatoteke);
        procitajLinijuDatoteke();
        String linijaDatoteke;
        String porukaUpozorenja = "";
        while ((linijaDatoteke = procitajLinijuDatoteke()) != null && AbstractAktivnost.radi) {

            String poruka = linijaDatoteke
                    .replace("„", "")
                    .replace("“", "")
                    .replaceAll("( )+", " ");
            odgovor = new OdgovorImplement()
                    .setStatus("OK")
                    .setPovratnaPoruka(poruka)
                    .build();
            Pocetak.modelOdogovor.setOdgovor(odgovor);
            String[] elementiNaredbe = srediLiniju(linijaDatoteke)
                    .split(";");

            if (linijaDatoteke.startsWith("5;")) {
                porukaUpozorenja += "Unutar datoteke aktivnosti ne mozete procitati novu datoteku!";
            } else if (elementiNaredbe.length > 0 && AbstractAktivnost.provjeraIdAktivnosti(elementiNaredbe[0])) {
                int idAktivnosti = Integer.parseInt(elementiNaredbe[0]);
                Lanac.getLanacAktivnosti().izvrsiAktivnost(idAktivnosti, srediLiniju(linijaDatoteke));
            } else {
                porukaUpozorenja += "Preskacem liniju: " + linijaDatoteke;
            }
            if (!porukaUpozorenja.isBlank()) {
                odgovor = new OdgovorImplement()
                        .setStatus("ERR")
                        .setPovratnaPoruka(porukaUpozorenja)
                        .build();
                Pocetak.modelOdogovor.setOdgovor(odgovor);
            }
        }
        Controller.interaktivno = true;
        Pocetak.modelOdogovor.setPogledPrikaza(InteraktivnoView.naziv);

    }

    private static String srediLiniju(String linijaDatoteke) {
        return linijaDatoteke
                .replace('„', ' ')
                .replace('“', ' ')
                .replace("\"", "")
                .replace('\u201c', ' ')
                .replace('\u201d', ' ')
                .replace('\u010d', 'c')
                .replaceAll("[\u010d]", "c")
                .replaceAll("[č]", "c")
                .replace("č", "c")
                .replaceAll("( )+", " ");
    }

}
