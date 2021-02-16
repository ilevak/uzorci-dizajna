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
public class AktivnostRaspVozila extends AbstractAktivnost {

    int potrebanBrojArg = 5;

    public AktivnostRaspVozila(int level) {
        this.level = level;
    }

    @Override
    protected void izvrsi(String naredba) {
        potvrda = "";
        error = "";

        String[] elementiNaredbe = naredba.split(";");
        if (provjeriTocnost(naredba, elementiNaredbe)) {
                PodaciOperacije podaci = pronadiPodatke(elementiNaredbe);
                if (podaci.getUpozorenje().isBlank()) {
                    ispisiRaspVozila(podaci);
                } else {
                    error=podaci.getUpozorenje();
                }

            
        }
    }

    private boolean provjeriTocnost(String naredba, String[] elementiNaredbe) {
        return provjeriNaredbu(naredba)&& postaviNovoVirtualnoVrijeme(elementiNaredbe[1])
                && provjeriBrojArg(elementiNaredbe, potrebanBrojArg);
    }

    private void ispisiRaspVozila(PodaciOperacije podaci) {
        Lokacija lokacija = podaci.getLokacija();
        VrstaVozila vrstaVozila = podaci.getVrstaVozila();
        int raspVozila = pronadiRaspVozila(lokacija, vrstaVozila);
        potvrda="Za vozilo " + vrstaVozila.getNaziv()
                + " na lokaciji "
                + lokacija.getNaziv()
                + " ima "
                + raspVozila
                + " raspolozivih vozila";
    }

    private PodaciOperacije pronadiPodatke(String[] elementiNaredbe) {
        int idVozila = stringUInteger(elementiNaredbe[4]);
        int idLokacije = stringUInteger(elementiNaredbe[3]);
        int idOsobe = stringUInteger(elementiNaredbe[2]);
        PodaciOperacijeDirektor podaciOperacijeDirektor
                = kreirajDirektor();
        return podaciOperacijeDirektor
                .upisi(idVozila, idLokacije, idOsobe);
    }

    private int pronadiRaspVozila(Lokacija lokacija, VrstaVozila vrstaVozila) {
        return lokacija.getBrojRaspVozila(vrstaVozila.getId());
    }

}
