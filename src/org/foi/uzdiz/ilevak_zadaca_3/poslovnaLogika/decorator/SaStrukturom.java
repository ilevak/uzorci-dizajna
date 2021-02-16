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
public class SaStrukturom  extends TablicaDecorator {
    
    String format=formatTekst;
    public SaStrukturom(TablicaDec t) {
        super(t);
    }

    @Override
    public String getFormat() {
        return super.getFormat() + format; 
    }

    @Override
    public String getVertikala() {
        int brojRazdjelnika=1;
        return super.getVertikala()+napraviVertikalu(maxTekst+brojRazdjelnika); 
    }

    @Override
    public String getZaglavlje() { 
        String formatZaglavljeStrukt =  formatTekst;
        return super.getZaglavlje()+String.format(formatZaglavljeStrukt, "Naziv OJ"); 
    }

   @Override
    public String napraviRedak(Redak redak) {
        String dodatakRetku=String.format(format, redak.getNazivLok());
        return super.napraviRedak(redak)+dodatakRetku;
    }
    
    
}
