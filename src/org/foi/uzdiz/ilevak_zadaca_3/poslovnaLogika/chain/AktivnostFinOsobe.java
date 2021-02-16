/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Osoba;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import static org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain.AbstractAktivnost.tablica;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Redak;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.SaFinancOsobe;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Tablica;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.TablicaDec;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.TablicaPocetak;

/**
 *
 * @author ivale
 */
public class AktivnostFinOsobe extends AbstractAktivnost {

    TablicaDec tablicaDecorator;

    public AktivnostFinOsobe(int level) {
        this.level = level;
    }

    @Override
    protected void izvrsi(String naredba) {
        potvrda = "";
        error = "";
        if ("9".equals(naredba.trim())) {
            kreirajTablicu();
            napuniTablicu();
           potvrda=(tablica.getTablica());
        }else{
            error="Krivo ste upisali! potrebno je upisati samo 9";
        }
    }

    private void kreirajTablicu() {
        kreirajDecorator();
        tablica = new Tablica(tablicaDecorator);
    }

    private void kreirajDecorator() {
        tablicaDecorator = new TablicaPocetak();
        tablicaDecorator = new SaFinancOsobe(tablicaDecorator);
    }

    private void napuniTablicu() {
        Redak redak;
        for (Osoba osoba : Podaci.popisOsoba) {
            redak = new Redak();
            redak.setIdOsoba(osoba.getId());
            tablica.addRedak(redak);
        }
    }

}
