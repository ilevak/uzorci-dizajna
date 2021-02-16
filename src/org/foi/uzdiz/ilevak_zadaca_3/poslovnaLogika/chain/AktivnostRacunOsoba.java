/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Racun;
import static org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain.AbstractAktivnost.tablica;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Redak;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.SaRacunimaOsoba;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Tablica;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.TablicaDec;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator.TablicaPocetak;

/**
 *
 * @author ivale
 */
public class AktivnostRacunOsoba extends AbstractAktivnost {

    String sintaksa = "^(\\d+)( \\d{1,2}\\.\\d{1,2}\\.\\d{4}){2}$";
    TablicaDec tablicaDecorator;

    public AktivnostRacunOsoba(int level) {
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
                kreirajTablicu();
                String[] elementi = element.trim().split(" ");
                napuniTablicu(elementi);
                potvrda=tablica.getTablica();
            } else {
                error="potrebnno je upisati pravilnu naredbu!";
            }
        }
    }

    private void kreirajTablicu() {
        kreirajDecorator();
        tablica = new Tablica(tablicaDecorator);
    }

    private void kreirajDecorator() {
        tablicaDecorator = new TablicaPocetak();
        tablicaDecorator = new SaRacunimaOsoba(tablicaDecorator);
    }

    private void napuniTablicu(String[] elementi) {
        Redak redak;
        int idOsobe = stringUInteger(elementi[0].trim());
        Date datumOd = stringUDatum(elementi[1].trim());
        Date datumDo = stringUDatum(elementi[2].trim());
        if (datumDo != null && datumOd != null && datumOd.before(datumDo)) {
            List<Racun> racuni = sortirajListuPopisRacuna(pronadiRacuneOsobe(idOsobe, datumOd, datumDo));
            redak = new Redak();
            redak.setIdOsoba(idOsobe);
            redak.setListaRacuna(racuni);
            tablica.addRedak(redak);
        }
    }

    private List<Racun> pronadiRacuneOsobe(int idOsobe, Date datumOd, Date datumDo) {
        return Podaci.getInstance().pronadiRacune(idOsobe, datumOd, datumDo);
    }

    
    public List<Racun> sortirajListuPopisRacuna(List<Racun> popisRacuna) {
         Collections.sort(popisRacuna, Comparator.comparing(Racun::isPlacen));
         return popisRacuna;
    }
    

}
