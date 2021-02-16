/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain;

import java.util.Date;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import static org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain.AbstractAktivnost.patternMatch;
import static org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain.AbstractAktivnost.tablica;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite.AbstractOJ;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.SaNajmom;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.SaStrukturom;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.SaVozilom;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.SaZaradom;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.TablicaDec;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Tablica;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.TablicaPocetak;

/**
 *
 * @author ivale
 */
public class AktivnostZaradaNajam extends AbstractAktivnost {

    String sintaksa = "^(struktura|najam|zarada)( struktura| najam| zarada)*( \\d{1,2}\\.\\d{1,2}\\.\\d{4}){2}( \\d+)?$";
    TablicaDec tablicaDecorator;

    int duzinaNaredbeBezId = 2;

    final static int indexPocDatumOdKraja = 2;
    final static int indexKraDatumOdkraja = 1;

    public AktivnostZaradaNajam(int level) {
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
                napuniTablicu(elementi);
                potvrda = (tablica.getTablica());
            } else {
                error = "Niste upisali dobro naredbu!! Potrebno je napisati"
                        + " (struktura|najam|zarada)!( struktura| najam| zarada)?"
                        + " datum1! datum2! id? pri čemu su oni sa ?  "
                        + "opcionalni a sad ! obavezni";
            }
        }
    }

    private void napuniTablicu(String[] elementi) {
        int duzinaNaredbe = elementi.length;
        if (duzinaNaredbe == duzinaNaredbeBezId) {
            int index = duzinaNaredbe - indexPocDatumOdKraja;
            Date datumOd = pronadiVrijeme(elementi, index);
            index = duzinaNaredbe - indexKraDatumOdkraja;
            Date datumDo = pronadiVrijeme(elementi, index);

            if (datumOd.before(datumDo)) {
                Podaci.korijen.napuniTablicuNajmaZarade(datumOd, datumDo);
            }
        } else if (duzinaNaredbe == duzinaNaredbeBezId + 1) {
            int index = duzinaNaredbe - indexPocDatumOdKraja - 1;
            Date datumOd = pronadiVrijeme(elementi, index);
            index = duzinaNaredbe - indexKraDatumOdkraja - 1;
            Date datumDo = pronadiVrijeme(elementi, index);
            int idJedinice = stringUInteger(elementi[duzinaNaredbeBezId]);
            AbstractOJ oj = Podaci.korijen.pronadiJedinicu(idJedinice);
            if (oj != null) {

                if (datumOd.before(datumDo)) {
                    oj.napuniTablicuNajmaZarade(datumOd, datumDo);
                }
            }
        }
    }

    private void kreirajTablicu(String element) {
        kreirajDecorator(element);

        tablica = new Tablica(tablicaDecorator);
    }

    private void kreirajDecorator(String element) {
        tablicaDecorator = new TablicaPocetak();
        if (element.contains("struktura")) {
            tablicaDecorator = new SaStrukturom(tablicaDecorator);
            duzinaNaredbeBezId++;
        }
        if (element.contains("najam") || element.contains("zarada")) {
            tablicaDecorator = new SaVozilom(tablicaDecorator);
            if (element.contains("najam")) {
                tablicaDecorator = new SaNajmom(tablicaDecorator);
                duzinaNaredbeBezId++;
            }
            if (element.contains("zarada")) {
                tablicaDecorator = new SaZaradom(tablicaDecorator);
                duzinaNaredbeBezId++;
            }
        }
    }
}
