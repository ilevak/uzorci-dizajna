/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Osoba;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Racun;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.VirtualnoVrijeme;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chainRacun.AbstractRacun;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chainRacun.LanacRacuna;

/**
 *
 * @author ivale
 */
public class AktivnostPlacanje extends AbstractAktivnost {

    int potrebanBrojArg = 2;
    String sintaksa = "^(\\d)+ (\\d)+(,?\\d*)$";

    public AktivnostPlacanje(int level) {
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
                String[] elementi = element.trim().split(" ");
                int idOsobe = stringUInteger(elementi[0].trim());
                float uplacenIznos = Float.parseFloat(elementi[1].trim().replace(",", "."));
                if (idOsobe != 0 && postojanjeOsobe(idOsobe)) {
                    if (uplacenIznos != 0) {
                        Osoba osoba = Podaci.getInstance()
                                .pronadiOsobuPutemId(idOsobe);
                        osoba.setUplacenIznos(uplacenIznos);
                        platiRacune(osoba);
                    } else {
                       error="Niste uplatili dobar iznos!";

                    }
                } else {
                    error="Korisnik sa id: "+idOsobe+" ne postoji!";
                }
            } else {
                error="Upisali ste krivu naredbu !";
            }
        }
    }

    private boolean postojanjeOsobe(int idOsobe) {
        return Podaci.getInstance()
                .pronadiOsobuPutemId(idOsobe) != null;
    }

    private void platiRacune(Osoba osoba) {
        Racun racun = new Racun();
        racun.setIdOsobe(osoba.getId());
        racun.setIdLokacijaVracanja(0);
        Racun placeniRacun;
        int brojNeplacenihRacuna=osoba.getBrojNeplacenihRacuna();
        for(int i=0; i<brojNeplacenihRacuna; i++){
            placeniRacun=LanacRacuna.getLanacAktivnosti().izvrsi(racun, AbstractRacun.OBRADA);
            if(placeniRacun!=null){
                potvrda+="ID RAČUNA:  "
                        + placeniRacun.getId() 
                        +" \nDATUM IZDAVANJA:"
                        +VirtualnoVrijeme.getInstance()
                                .datumUString(placeniRacun.getDatumVracanja())
                        +" \nPLAČENI IZNOS:"+placeniRacun.getUkupniIznos()+"\n";
            }
        }
        potvrda+="VRACENO: "+osoba.getUplacenIznos();
        osoba.setUplacenIznos(0);
    }
}
