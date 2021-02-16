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
public class SaVozilom extends TablicaDecorator{
    String format=formatTekst;
    public SaVozilom(TablicaDec t) {
        super(t);
    }
    @Override
    public String getFormat() {
        return super.getFormat() + format; 
    }

    @Override
    public String getVertikala() {
        int brojRazdjeljnika = 1;
        int brojZnakova = maxTekst + brojRazdjeljnika;
        return super.getVertikala()+napraviVertikalu(brojZnakova); 
    }

    @Override
    public String getZaglavlje() {
        String formatZaglavljeNazivVozila =  formatTekst;
        return super.getZaglavlje()+String.format(
                formatZaglavljeNazivVozila,
                "Naziv Vozila"); 
    }

    @Override
    public String napraviRedak(Redak redak) {
        String dodatakRetku=String.format(format, redak.getNazivVozila());
        return super.napraviRedak(redak)+dodatakRetku;
    }
    

    
}
