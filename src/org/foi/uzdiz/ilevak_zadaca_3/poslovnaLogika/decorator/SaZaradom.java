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
public class SaZaradom extends TablicaDecorator {
    
    String format=formatBrojDec;
    public SaZaradom(TablicaDec t) {
        super(t);
    }

    @Override
    public String getFormat() {
        return super.getFormat() + format;
    }

    @Override
    public String getVertikala() {
        int brojRazdjeljnika = 2;
        return super.getVertikala()+napraviVertikalu(maxCijeli+maxDecim + brojRazdjeljnika); 
    }

    @Override
    public String getZaglavlje() {
        String formatZaglavljeZarad =  formatTekstDecim;
        return super.getZaglavlje()+String.format(formatZaglavljeZarad, "Zarada");
    }

    @Override
    public String napraviRedak(Redak redak) {
        String dodatakRetku=String.format(format, redak.getZarada());
        return super.napraviRedak(redak)+dodatakRetku;
    }
    
    
}
