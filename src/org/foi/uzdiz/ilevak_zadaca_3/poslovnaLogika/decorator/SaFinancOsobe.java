/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Redak;
import java.util.Date;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Osoba;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.VirtualnoVrijeme;

/**
 *
 * @author ivale
 */
public class SaFinancOsobe extends TablicaDecorator{
    String format=formatBrojCijeli
            +formatTekst
            +formatBrojDec
            +formatTekst;
    
    public SaFinancOsobe(TablicaDec t) {
        super(t);
    }
    @Override
    public String getFormat() {
        return super.getFormat() + format; 
    }

    @Override
    public String getVertikala() {
        int brojRazdjeljnika = 5;
        int brojZnakova = maxTekst*2+maxCijeli*2+maxDecim + brojRazdjeljnika;
        return super.getVertikala()+napraviVertikalu(brojZnakova); 
    }

    @Override
    public String getZaglavlje() {
        String formatZaglavljeNazivVozila =  formatTekstCijeli
                +formatTekst
                +formatTekstDecim
                +formatTekst
                ;
        return super.getZaglavlje()+String.format(
                formatZaglavljeNazivVozila,
                "Id osobe", "Ime i prezime", "Dugovanje", "Datum zadnjeg najma"); 
    }

    @Override
    public String napraviRedak(Redak redak) {
        String datumZadnjegNajma;
        Osoba osoba=pronadiOsobu(redak.getIdOsoba());
        if(osoba.getDatumZadnjegNajma()!=null){
        datumZadnjegNajma=datumUString(osoba.getDatumZadnjegNajma());}
        else{
            datumZadnjegNajma="";
        }
        String dodatakRetku=String.format(format, osoba.getId(), 
                osoba.getImePrezime(), osoba.getDugovanje(), datumZadnjegNajma);
        return super.napraviRedak(redak)+dodatakRetku;
    }
    
      private String datumUString(Date datum) {
        return VirtualnoVrijeme.getInstance().datumUString(datum);
    }

    private Osoba pronadiOsobu(int idOsoba) {
       return Podaci.getInstance().pronadiOsobuPutemId(idOsoba);
    }

    
}

