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
public class SaNajmom extends TablicaDecorator {

    String format = formatBrojCijeli + formatBrojCijeli;

    public SaNajmom(TablicaDec t) {
        super(t);
    }

    @Override
    public String getZaglavlje() {
        String formatZaglavljeNajma = formatTekstCijeli + formatTekstCijeli;
        return super.getZaglavlje() + String.format(formatZaglavljeNajma, "Broj najma", "Trajanje najma");
    }

    @Override
    public String getVertikala() {
        int brojRazdjeljnika = 2;
        return super.getVertikala() + napraviVertikalu(maxCijeli * 2 + brojRazdjeljnika);
    }

    @Override
    public String getFormat() {
        return super.getFormat() + format;
    }

    @Override
    public String napraviRedak(Redak redak) {
        String dodatakRetku = String
                .format(format,
                        redak.getBrojNajma(),
                        redak.getTrajanjeNajma()
                );
        return super.napraviRedak(redak) + dodatakRetku;
    }
}
