/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chainRacun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Racun;

/**
 *
 * @author ivale
 */
public class RacunPretrazi extends AbstractRacun {

    public RacunPretrazi(int level) {
        this.level = level;
    }

    
    @Override
    public Racun izvrsi(Racun racun, int level) {
        if (racun.getIdLokacijaVracanja() == 0) {
            List<Racun> popisRacuna = new ArrayList<>();
            popisRacuna.addAll(Podaci.popisRacuna);
            popisRacuna.removeIf(r -> r.isPlacen() || r.getIdOsobe() != racun.getIdOsobe());
            Collections.sort(popisRacuna, Comparator.comparing(Racun::getId));
            Racun prviNeplaceni=null;
            Racun prviPlaceni;
            for (Racun r : popisRacuna) {
                if (level > this.level) {
                    prviPlaceni = sljedecaAkcija.izvrsi(r, level);
                    if (prviPlaceni != null) {
                        return prviPlaceni;
                    }
                } else {
                    if(r!=null && prviNeplaceni==null){
                    prviNeplaceni = r;
                    }
                }
            }

            return prviNeplaceni;
        } else {
            if (level > this.level) {
                return sljedecaAkcija.izvrsi(racun, level);
            }
            return racun;
        }

    }

}
