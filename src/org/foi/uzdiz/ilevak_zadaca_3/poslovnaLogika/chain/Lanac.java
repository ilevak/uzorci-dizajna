/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain;

/**
 *
 * @author ivale
 */
public class Lanac {

    private static Lanac instancaLanac = null;

    public Lanac() {

    }

    public static Lanac getInstance() {
        if (instancaLanac == null) {
            instancaLanac = new Lanac();
        }

        return instancaLanac;
    }

   public static AbstractAktivnost getLanacAktivnosti() {

        AbstractAktivnost aktivnostUgasi = new AktivnostUgasi(AbstractAktivnost.UGASI);
        AbstractAktivnost aktivnostRaspVozila = new AktivnostRaspVozila(AbstractAktivnost.RASPOLOZIVA_VOZILA);
        AbstractAktivnost aktivnostNajam = new AktivnostUnajmiVozilo(AbstractAktivnost.NAJAM);
        AbstractAktivnost aktivnostRaspMjesta = new AktivnostRaspMjesta(AbstractAktivnost.RASPOLOZIVA_MJESTA);
        AbstractAktivnost aktivnostVrati = new AktivnostVratiVozilo(AbstractAktivnost.VRACANJE);
        AbstractAktivnost aktivnostSkupni = new AktivnostUcitajSkupno(AbstractAktivnost.SKUPNI);
        AbstractAktivnost aktivnostStanje = new AktivnostStanje(AbstractAktivnost.STANJE);
        AbstractAktivnost aktivnostNajamZarada = new AktivnostZaradaNajam(AbstractAktivnost.NAJAM_ZARADA);
        AbstractAktivnost aktivnostRacuni = new AktivnostRacun(AbstractAktivnost.RACUNI);
        AbstractAktivnost aktivnostFinancije=new AktivnostFinOsobe(AbstractAktivnost.FINANCIJE_OSOBA);
        AbstractAktivnost aktivnostRacuniOsobe=new AktivnostRacunOsoba(AbstractAktivnost.RACUNI_OSOBA);
        AbstractAktivnost aktivnostPlati=new AktivnostPlacanje(AbstractAktivnost.PLATI);
        aktivnostUgasi.setNextAktivnost(aktivnostRaspVozila);
        aktivnostRaspVozila.setNextAktivnost(aktivnostNajam);
        aktivnostNajam.setNextAktivnost(aktivnostRaspMjesta);
        aktivnostRaspMjesta.setNextAktivnost(aktivnostVrati);
        aktivnostVrati.setNextAktivnost(aktivnostSkupni);
        aktivnostSkupni.setNextAktivnost(aktivnostStanje);
        aktivnostStanje.setNextAktivnost(aktivnostNajamZarada);
        aktivnostNajamZarada.setNextAktivnost(aktivnostRacuni);
        aktivnostRacuni.setNextAktivnost(aktivnostFinancije);
        aktivnostFinancije.setNextAktivnost(aktivnostRacuniOsobe);
        aktivnostRacuniOsobe.setNextAktivnost(aktivnostPlati);

        return aktivnostUgasi;

    }
}
