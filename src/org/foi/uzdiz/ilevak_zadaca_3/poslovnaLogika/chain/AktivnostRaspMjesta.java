/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.VrstaVozila;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderPodaciOperacijaVozila.PodaciOperacije;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderPodaciOperacijaVozila.PodaciOperacijeDirektor;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite.Lokacija;

/**
 *
 * @author ivale
 */
public class AktivnostRaspMjesta extends AbstractAktivnost {

    int potrebanBrojArg = 5;

    public AktivnostRaspMjesta(int level) {
        this.level = level;
    }

    @Override
    protected void izvrsi(String naredba) {
        potvrda = "";
        error = "";
        if (provjeriNaredbu(naredba)) {
            String[] elementiNaredbe = naredba.split(";");
            if (provjeriElemente(elementiNaredbe)) {
                PodaciOperacije podaci = pronadiPodatke(elementiNaredbe);
                if (podaci.getUpozorenje().isBlank()) {
                    ispisiRaspMjesta(podaci);
                } else {
                    error=podaci.getUpozorenje();
                }
            }
        }
    }

    private boolean provjeriElemente(String[] elementiNaredbe) {
        return postaviNovoVirtualnoVrijeme(elementiNaredbe[1]) 
                && provjeriBrojArg(elementiNaredbe, potrebanBrojArg);
    }

    private PodaciOperacije pronadiPodatke(String[] elementiNaredbe) {
        int idVozila = stringUInteger(elementiNaredbe[4]);
        int idLokacije = stringUInteger(elementiNaredbe[3]);
        int idOsobe=stringUInteger(elementiNaredbe[2]);
        PodaciOperacijeDirektor podaciOperacijeDirektor
                = kreirajDirektor();
        return podaciOperacijeDirektor.upisi(idVozila, idLokacije, idOsobe);
    }

    private void ispisiRaspMjesta(PodaciOperacije podaci) {
        Lokacija lokacija = podaci.getLokacija();
        VrstaVozila vrstaVozila = podaci.getVrstaVozila();
        int brojMjesta = pronadiRaspMjesta(lokacija, vrstaVozila);
        potvrda
                = "Za vozilo: " + vrstaVozila.getNaziv()
                + " na lokaciji "
                + lokacija.getNaziv()
                + " ima "
                + brojMjesta
                + " raspolozivih mjesta";
    }

    private int pronadiRaspMjesta(Lokacija lokacija, VrstaVozila vrstaVozila) {
        return lokacija.getBrojRaspMjesta(vrstaVozila.getId());
    }

}
