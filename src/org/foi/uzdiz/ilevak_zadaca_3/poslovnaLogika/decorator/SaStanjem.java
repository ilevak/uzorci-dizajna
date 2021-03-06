/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Redak;

/**
 *
 * @author ivale
 */
public class SaStanjem  extends TablicaDecorator {
    String format=formatBrojCijeli + formatBrojCijeli
                + formatBrojCijeli;
    public SaStanjem(TablicaDec t) {
        super(t);
    }

    @Override
    public String getFormat() {
        return super.getFormat()
                + format; 
    }

    @Override
    public String getVertikala() {
        int brojRazdjeljnika = 3;
        int brojZnakova = maxCijeli * 3 + brojRazdjeljnika;
        return super.getVertikala()+napraviVertikalu(brojZnakova); 
    }

    @Override
    public String getZaglavlje() {
        String formatZaglavljeStanja = formatTekstCijeli
                + formatTekstCijeli + formatTekstCijeli;
        return super.getZaglavlje()+String.format(
                formatZaglavljeStanja,
                 "Rasp. mjesta",
                "Rasp. vozila", "Vozila Kvar"
        ); 
    }
       @Override
    public String napraviRedak(Redak redak) {
        String dodatakRetku=String.format(format, redak.getBrojRaspMjesta(),
                redak.getBrojRaspVoz(), redak.getBrojStrganihVozila());
        return super.napraviRedak(redak)+dodatakRetku;
    }

}
