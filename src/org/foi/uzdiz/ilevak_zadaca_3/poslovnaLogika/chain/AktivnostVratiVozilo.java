/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Osoba;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Racun;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Vozilo;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.VrstaVozila;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderPodaciOperacijaVozila.PodaciOperacije;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderPodaciOperacijaVozila.PodaciOperacijeDirektor;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chainRacun.AbstractRacun;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chainRacun.LanacRacuna;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite.Lokacija;

/**
 *
 * @author ivale
 */
public class AktivnostVratiVozilo extends AbstractAktivnost {

    int potrebanBrojArg = 6;
    int mogucBrojArg = 7;

    public AktivnostVratiVozilo(int level) {
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
                vratiVozilo(podaci);
            } else {
                error=podaci.getUpozorenje();
            }
        }

    }

    private boolean provjeriTocnost(String naredba, String[] elementiNaredbe) {
        return provjeriNaredbu(naredba) 
                && postaviNovoVirtualnoVrijeme(elementiNaredbe[1]) 
                && provjeriBrojArg(elementiNaredbe, potrebanBrojArg, mogucBrojArg);
    }

    private PodaciOperacije pronadiPodatke(String[] elementiNaredbe) {
        int idOsoba = stringUInteger(elementiNaredbe[2]);
        int idLokacije = stringUInteger(elementiNaredbe[3]);
        int idVozila = stringUInteger(elementiNaredbe[4]);
        int brojPrijedenihKilometara = stringUInteger(elementiNaredbe[5]);
        String opisKvara = "";
        if (mogucBrojArg == elementiNaredbe.length) {
            opisKvara = elementiNaredbe[6];
        }
        PodaciOperacijeDirektor podaciOperacijeDirektor
                = kreirajDirektor();
        return podaciOperacijeDirektor
                .upisi(
                        idVozila,
                        idLokacije,
                        idOsoba,
                        brojPrijedenihKilometara,
                        opisKvara);
    }

    private boolean vratiVozilo(PodaciOperacije podaci) {
        Lokacija lokacija = podaci.getLokacija();
        VrstaVozila vrstaVozila = podaci.getVrstaVozila();
        if (provjeraRaspMjestaLokacije(lokacija, vrstaVozila)) {
            Osoba osoba = podaci.getOsoba();
            Vozilo vracenoVozilo = osoba.vratiVozilo(vrstaVozila.getId(),
                    podaci.getBrojPrijedenihKm(), podaci.getOpisKvara());
            if (vracenoVozilo != null) {
                lokacija.popisVozila.add(vracenoVozilo);
                if (!podaci.getOpisKvara().isBlank()) {
                    osoba.povecajBrojKvarova();
                }
                potvrda += "Vratili ste vozilo!";
                izdajRacun(podaci, vracenoVozilo);
                return true;
            } else {
                error = "Osoba nema vozilo s id: "
                        + vrstaVozila.getId();
                return false;
            }
        } else {
            error = "Nema raspolozivih mjesta za vozilo " + vrstaVozila.getNaziv()
                    + " na lokaciji " + lokacija.getNaziv();
            return false;
        }

    }

    private boolean provjeraRaspMjestaLokacije(Lokacija lokacija,
            VrstaVozila vrstaVozila) {
        return lokacija.getBrojRaspMjesta(vrstaVozila.getId()) != 0;
    }

    public void izdajRacun(PodaciOperacije podaci, Vozilo vozilo) {
        Racun racun = new Racun();
        racun.setPrijedeniBrKm(podaci.getBrojPrijedenihKm());
        racun.setVozilo(vozilo);
        racun.setIdLokacijaVracanja(podaci.getLokacija().getId());
        racun.setIdOsobe(podaci.getOsoba().getId());
        if (podaci.getOsoba().postojanjeUgovora()) {
            potvrda += LanacRacuna.getLanacAktivnosti().izvrsi(racun, AbstractRacun.EVIDENCIJA).toString()+"\n RAČUN NIJE PLAČEN!";
        } else {
            potvrda += LanacRacuna.getLanacAktivnosti().izvrsi(racun, AbstractRacun.OBRADA).toString()+"\n RAČUN JE PLAČEN!";
        }
    }

}
