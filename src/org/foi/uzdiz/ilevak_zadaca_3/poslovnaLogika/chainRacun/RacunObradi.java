/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chainRacun;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Osoba;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Racun;

/**
 *
 * @author ivale
 */
public class RacunObradi extends AbstractRacun {

    public RacunObradi(int level) {
        this.level = level;
    }

    @Override
    public Racun izvrsi(Racun racun, int level) {
        
            return obradiRacun(racun);
        
    }

    private Racun obradiRacun(Racun racun) {

        Osoba osoba = Podaci.getInstance().pronadiOsobuPutemId(racun.getIdOsobe());
        if (osoba.postojanjeUgovora()) {
            if (osoba.getUplacenIznos() >= racun.getUkupniIznos()) {
                osoba.setDugovanje(osoba.getDugovanje() - racun.getUkupniIznos());
                osoba.setUplacenIznos(osoba.getUplacenIznos() - racun.getUkupniIznos());
                osoba.smanjiBrojNeplacenihRacuna();
                racun=Podaci.getInstance().pronadiRacunPutemId(racun.getId());
                racun.setPlacen(true);
                return racun;
            }
            return null;
        } else {
            osoba.smanjiBrojNeplacenihRacuna();
            racun.setPlacen(true);
            osoba.setDugovanje(0);
            return racun;
        }
    }

}
