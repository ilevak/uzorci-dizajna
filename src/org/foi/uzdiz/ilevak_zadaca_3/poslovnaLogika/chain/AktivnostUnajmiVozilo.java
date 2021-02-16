/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Osoba;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Vozilo;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.VrstaVozila;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderPodaciOperacijaVozila.PodaciOperacije;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderPodaciOperacijaVozila.PodaciOperacijeDirektor;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite.Lokacija;



/**
 *
 * @author ivale
 */
public class AktivnostUnajmiVozilo extends AbstractAktivnost {

    int potrebanBrojArg = 5;

    public AktivnostUnajmiVozilo(int level) {
        this.level = level;
    }

    @Override
    protected void izvrsi(String naredba) {
        
        potvrda = "";
        error = "";
        if (provjeriNaredbu(naredba)) {
            String[] elementiNaredbe = naredba.split(";");
            if (postaviNovoVirtualnoVrijeme(elementiNaredbe[1]) && provjeriBrojArg(elementiNaredbe, potrebanBrojArg)) {
                PodaciOperacije podaci
                        = pronadiPodatke(elementiNaredbe);
                if (podaci.getUpozorenje().isBlank()) {
                    unajmiVozilo(podaci);
                } else {
                    error=podaci.getUpozorenje();
                }
                
            }
        }
    }

    private PodaciOperacije pronadiPodatke(String[] elementiNaredbe) {

        int idVozila = stringUInteger(elementiNaredbe[4]);
        int idLokacije = stringUInteger(elementiNaredbe[3]);
        int idOsoba = stringUInteger(elementiNaredbe[2]);
        PodaciOperacijeDirektor podaciOperacijeDirektor
                = kreirajDirektor();
        return podaciOperacijeDirektor
                .upisi(idVozila, idLokacije, idOsoba);
    }



    private void unajmiVozilo(PodaciOperacije podaci) {
        Vozilo voziloNajam;
        Osoba osoba = podaci.getOsoba();
        VrstaVozila vrstaVozila = podaci.getVrstaVozila();
        if (provjeraVrsteVozilaOsobe(osoba, vrstaVozila)
                && provjeraDugovanjaOsobe(osoba)) {
            Lokacija lokacija = podaci.getLokacija();
            voziloNajam = lokacija.iznajmiVozilo(vrstaVozila.getId());
            if (voziloNajam != null) {
                osoba.setIznajmljenoVozilo(voziloNajam);
                osoba.setDatumZadnjegNajma(voziloNajam.getDatumNajma());
                potvrda = "Osoba " + osoba.getImePrezime()
                        + " je unajmila vozilo " + vrstaVozila.getNaziv()
                        + " na lokaciji " + lokacija.getNaziv();
            } else {
                error = "Sva vozila: " + vrstaVozila.getNaziv()
                        + " na lokaciji: " + lokacija.getNaziv()
                        + " su iznajmljena ili su na punjenju";
            }
        }
    }

    private boolean provjeraVrsteVozilaOsobe(Osoba osoba,
            VrstaVozila vrstaVozila) {
        if (osoba.pronadiIznajmljenoVozilo(vrstaVozila.getId()) != null) {
            error=(
                    "Osoba " + osoba.getImePrezime()
                    + " vec vozi vozilo :" + vrstaVozila.getNaziv()
            );
            return false;
        }
        return true;
    }

    private boolean provjeraDugovanjaOsobe(Osoba osoba) {
        if (osoba.getDugovanje() > Podaci.getInstance().getMaxDugovanje()) {
            error=(
                    "Osoba " + osoba.getImePrezime()
                    + " ima vece dugovanje od maksimalno dopustenog!"
                    + Podaci.getInstance().getMaxDugovanje()
            );
            return false;
        }
        return true;
    }

}
