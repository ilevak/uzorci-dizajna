/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite.AbstractOJ;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.SaStanjem;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.SaStrukturom;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.SaVozilom;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.TablicaDec;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Tablica;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.TablicaPocetak;

/**
 *
 * @author ivale
 */
public class AktivnostStanje extends AbstractAktivnost {

    String sintaksa = "^(stanje|struktura)( stanje| struktura)?( \\d+)?$";
    TablicaDec tablicaDecorator;

    int duzinaNaredbeBezId = 0;

    public AktivnostStanje(int level) {
        this.level = level;

    }

    @Override
    protected void izvrsi(String naredba) {
        potvrda = "";
        error = "";

        if (provjeriNaredbu(naredba)) {
            String[] elementiNaredbe = naredba.split(";");
            String element = " " + elementiNaredbe[1].trim() + " ";
            if (patternMatch(sintaksa, element)) {
                kreirajTablicu(element);
                String[] elementi = element.trim().split(" ");
                int duzinaNaredbe = elementi.length;
                napuniTablicu(duzinaNaredbe, elementi);

                potvrda=(tablica.getTablica());

            } else {
                error="Krivo ste upisali naredbu! potrebno je unjeti :"
                        + "(stanje|struktura)!( stanje| struktura)? id?"
                        + "pri čemu su oni sa ! obavezni a sa ? opcionalni";
            }
        }
    }

    private void kreirajTablicu(String element) {
        kreirajDecorator(element);
        tablica = new Tablica(tablicaDecorator);
    }

    private void napuniTablicu(int duzinaNaredbe, String[] elementi) {
        if (duzinaNaredbe == duzinaNaredbeBezId) {
            Podaci.korijen.napuniTablicuStanja();
        } else if (duzinaNaredbe == duzinaNaredbeBezId + 1) {
            int idJedinice = stringUInteger(elementi[duzinaNaredbeBezId]);
            AbstractOJ oj = Podaci.korijen.pronadiJedinicu(idJedinice);
            if (oj != null) {
                oj.napuniTablicuStanja();
            }
        } 
    }

    private void kreirajDecorator(String element) {
        tablicaDecorator = new TablicaPocetak();
        if (element.contains("struktura")) {
            tablicaDecorator = new SaStrukturom(tablicaDecorator);
            duzinaNaredbeBezId++;
        }
        if (element.contains("stanje")) {
            tablicaDecorator = new SaVozilom(tablicaDecorator);
            tablicaDecorator = new SaStanjem(tablicaDecorator);
            duzinaNaredbeBezId++;
        }
    }

}
