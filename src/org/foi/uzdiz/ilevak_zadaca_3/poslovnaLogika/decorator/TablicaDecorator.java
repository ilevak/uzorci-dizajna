/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Redak;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;

/**
 *
 * @author ivale
 */
public abstract class TablicaDecorator implements TablicaDec {

    private final TablicaDec dekoriranaTablica;
    public int maxTekst;
    public int maxCijeli;
    public int maxDecim;

    public String formatTekst;
    public String formatTekstCijeli;
    public String formatBrojCijeli;
    public String formatTekstDecim;
    public String formatBrojDec;
    public String formatPocetakReda = "|";
    public String formatKrajReda = "%n";
    public String formatVertikala = "%s";

    public TablicaDecorator(TablicaDec t) {
        maxTekst = Podaci.getInstance().getMaxTekstTab();
        maxCijeli = Podaci.getInstance().getMaxCijeliTab();
        maxDecim = Podaci.getInstance().getMaxDecimTab();
        formatTekst = "%-" + maxTekst + "." + maxTekst + "s|";
        formatTekstCijeli = "%" + maxCijeli + "." + maxCijeli + "s|";
        int max = maxCijeli + maxDecim + 1;
        formatTekstDecim = "%" + max + "." + max + "s|";
        formatBrojCijeli = "%" + maxCijeli + "d|";
        formatBrojDec = "%" + max + "." + maxDecim + "f|";
        dekoriranaTablica = t;
    }

    @Override
    public String getZaglavlje() {
        return dekoriranaTablica.getZaglavlje();
    }

    @Override
    public String getVertikala() {
        return dekoriranaTablica.getVertikala();
    }

    @Override
    public String getFormat() {
        return dekoriranaTablica.getFormat();
    }

    @Override
    public String napraviRedak(Redak redak) {
        return dekoriranaTablica.napraviRedak(redak);
    }


    public static String napraviVertikalu(int broj) {
        String vertikalaString = "";

        for (int i = 0; i < broj; i++) {
            vertikalaString += "-";
        }

        return vertikalaString;
    }

    
}
