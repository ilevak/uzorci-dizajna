/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Redak;
import java.util.Date;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Racun;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.VirtualnoVrijeme;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.iterator.RacunIterator;

/**
 *
 * @author ivale
 */
public class SaRacunima extends TablicaDecorator {

    RacunIterator iterator;
    String format = formatBrojCijeli
            + formatTekst
            + formatTekst
            + formatBrojCijeli
            + formatTekst
            + formatBrojCijeli
            + formatTekst
            + formatBrojDec
            + formatBrojDec
            + formatBrojDec
            + formatBrojCijeli
            + formatBrojCijeli
            + formatBrojDec;
    String formatZaPrazanPrviRed = formatTekstCijeli
            + formatTekst
            + formatTekst
            + formatTekstCijeli
            + formatTekst
            + formatTekstCijeli
            + formatTekst
            + formatTekstDecim
            + formatTekstDecim
            + formatTekstDecim
            + formatTekstCijeli
            + formatTekstCijeli
            + formatTekstDecim;


    public SaRacunima(TablicaDec t) {
        super(t);
    }

    @Override
    public String getFormat() {
        return super.getFormat()
                + format;
    }

    @Override
    public String getVertikala() {
        int brojRazdjeljnika = 13;
        int brojTocki = 4;
        return super.getVertikala() + napraviVertikalu(
                maxTekst * 4 + maxCijeli * 9
                + maxDecim * 4 + brojRazdjeljnika + brojTocki);
    }

    @Override
    public String getZaglavlje() {
        String formatZaglavljeZarad
                = formatTekstCijeli
                + formatTekst
                + formatTekst
                + formatTekstCijeli
                + formatTekst
                + formatTekstCijeli
                + formatTekst
                + formatTekstDecim
                + formatTekstDecim
                + formatTekstDecim
                + formatTekstCijeli
                + formatTekstCijeli
                + formatTekstDecim;
        return super.getZaglavlje()
                + String.format(formatZaglavljeZarad,
                        "Id racun",
                        "Izdan",
                        "Osoba",
                        "IdNajma",
                        "Lokacija Najma",
                        "IdVracanje",
                        "Lokacija Vracanja",
                        "Cijena najma",
                        "Iznos sat",
                        "Iznos km",
                        "Prijedeni km",
                        "Broj sati",
                        "Ukupno");
    }

    @Override
    public String napraviRedak(Redak redak) {

        iterator = new RacunIterator(redak.getListaRacuna());
        String retci = prviRedak(redak);
        return retci + popuniRetkeRacun();
    }

    private String prviRedak(Redak redak) {
        String pocetakReda = super.napraviRedak(redak);
        return pocetakReda + popuniOstatakPrazno();
    }

    private String popuniOstatakPrazno() {
        return String.format(formatZaPrazanPrviRed, "", "", "", "", "", "","", "", "", "", "", "", "", "");
    }

    private String popuniRetkeRacun() {
        Racun racun;
        String retci = "";
        Redak redak=new Redak();
        redak.setNazivLok("");
        redak.setNazivVozila("");
        while (iterator.hasNext()) {
            racun = iterator.next();
            retci += super.napraviRedak(redak);
            retci += String
                    .format(format, racun.getId(),
                            datumUString(racun.getDatumNajma()),
                            pronadiImeOsobe(racun.getIdOsobe()),
                            racun.getIdLokacijaNajma(),
                            pronadiNazivLokacije(racun.getIdLokacijaNajma()),
                            racun.getIdLokacijaVracanja(),
                            pronadiNazivLokacije(racun.getIdLokacijaVracanja()),
                            racun.getCjenik().getCijenaNajma(),
                            racun.getCjenik().getCijenaPoKm(),
                            racun.getCjenik().getCijenaPoSatu(),
                            racun.getPrijedeniBrKm(),
                            racun.getVrijemeNajma(),
                            racun.getUkupniIznos()
                    );
        }
        return retci;
    }

    private String datumUString(Date datum) {
        return VirtualnoVrijeme.getInstance().datumUString(datum);
    }

    private String pronadiImeOsobe(int idOsoba) {
        return Podaci.getInstance().pronadiOsobuPutemId(idOsoba).getImePrezime();

    }
        private String pronadiNazivLokacije(int idLokacija) {
        return Podaci.getInstance().pronadiLokacijuPutemId(idLokacija).getNaziv();

    }

}
