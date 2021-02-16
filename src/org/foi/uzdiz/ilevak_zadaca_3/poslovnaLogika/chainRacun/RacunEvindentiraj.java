/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chainRacun;

import java.util.Date;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Osoba;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Racun;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Vozilo;

/**
 *
 * @author ivale
 */
public class RacunEvindentiraj extends AbstractRacun {

    public RacunEvindentiraj(int level) {
        this.level = level;
    }

    @Override
    public Racun izvrsi(Racun racun, int level) {
        if (racun.getIdLokacijaVracanja() != 0) {
            racun=evidentirajRacun(racun);
        } 
        if (level > this.level) {
            return sljedecaAkcija.izvrsi(racun, level);
        }
        return racun;
    }

    private Racun evidentirajRacun(Racun racun) {
        Vozilo vozilo = racun.getVozilo();
        int trajanje = izracunajVrijeme(
                vozilo.getDatumNajma(),
                vozilo.getDatumVracanja()
        );
        Osoba osoba=Podaci.getInstance().pronadiOsobuPutemId(racun.getIdOsobe());
        osoba.povecajBrojNeplaenihRacuna();
        racun.setVrijemeNajma(trajanje);
        racun.setDatumNajma(vozilo.getDatumNajma());
        racun.setDatumVracanja(vozilo.getDatumNajma());
        racun.setIdLokacijaNajma(vozilo.getIdLokacijeNajma());
        racun.setId(LanacRacuna.brojacRacuna);
        LanacRacuna.brojacRacuna++;
        osoba.setDugovanje(osoba.getDugovanje()+racun.getUkupniIznos());
        Podaci.popisRacuna.add(racun);
        return racun;
    }

    /**
     * Vraca sate koji su protekli, zaokruzuje na visu
     *
     * @param vozilo
     * @return
     */
    private int izracunajVrijeme(Date datumNajma, Date datumVracanja) {
        double datum1 = datumNajma.getTime();
        double datum2 = datumVracanja.getTime();
        double sekunde = 1000 * 60 * 60;
        return (int) Math.ceil(((datum2 - datum1) / (sekunde)));
    }

}
