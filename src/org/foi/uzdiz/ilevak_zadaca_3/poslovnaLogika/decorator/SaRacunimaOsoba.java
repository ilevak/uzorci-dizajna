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
public class SaRacunimaOsoba extends TablicaDecorator {

    RacunIterator iterator;
    String format = formatBrojCijeli
            + formatBrojDec
            + formatTekst
            + formatTekst
            + formatTekst
            + formatTekst;

    public SaRacunimaOsoba(TablicaDec t) {
        super(t);
    }

    @Override
    public String getFormat() {
        return super.getFormat()
                + format;
    }

    @Override
    public String getVertikala() {
        int brojRazdjeljnika = 4;
        int brojTocki = 1;
        return super.getVertikala() + napraviVertikalu(
                maxTekst * 4 + maxCijeli*2
                + maxDecim * 1 + brojRazdjeljnika + brojTocki);
    }

    @Override
    public String getZaglavlje() {
        String formatZaglavljeZarad
                = formatTekstCijeli
                + formatTekstDecim
                + formatTekst
                + formatTekst
                + formatTekst
                + formatTekst;
        return super.getZaglavlje()
                + String.format(formatZaglavljeZarad,
                        "Id racun",
                        "Iznos",
                        "Datum izdavanja",
                        "Status",
                        "Vrsta vozila",
                        "Lokacija Najma");
    }

    @Override
    public String napraviRedak(Redak redak) {
        String dodatakRetku = "";
        Racun racun;
        String datumZadnjegNajma;
        String nazivLokacije;
        String status;
        iterator = new RacunIterator(redak.getListaRacuna());
        while (iterator.hasNext()) {
            dodatakRetku += super.napraviRedak(redak);
            racun = iterator.next();
            datumZadnjegNajma = datumUString(racun.getDatumVracanja());
            nazivLokacije = pronadiNazivLokacije(racun.getIdLokacijaNajma());
            status = pronadiStatusRacuna(racun.isPlacen());
            dodatakRetku += String.format(format, racun.getId(),
                    racun.getUkupniIznos(), datumZadnjegNajma, status,
                    racun.getVozilo().getVrstaVozila().getNaziv(), nazivLokacije);

        }
        return dodatakRetku;
    }

    private String datumUString(Date datum) {
        return VirtualnoVrijeme.getInstance().datumUString(datum);
    }

    private String pronadiNazivLokacije(int idLokacija) {
        return Podaci.getInstance().pronadiLokacijuPutemId(idLokacija).getNaziv();
    }

    private String pronadiStatusRacuna(boolean placen) {
        if (placen) {
            return "PLACENO";
        } else {
            return "DUG";
        }
    }

}
