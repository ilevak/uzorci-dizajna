/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.podaci;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.foi.uzdiz.ilevak_zadaca_3.izvodenje.Pocetak;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.Odgovor;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.OdgovorImplement;

/**
 *
 * @author ivale
 */
public class VirtualnoVrijeme {

    private static VirtualnoVrijeme instancaVirutalnoVrijeme = null;

    private static Date sadasnjeVrijeme = null;

    private static final SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private VirtualnoVrijeme() {

    }

    public static VirtualnoVrijeme getInstance() {
        if (instancaVirutalnoVrijeme == null) {
            instancaVirutalnoVrijeme = new VirtualnoVrijeme();
        }

        return instancaVirutalnoVrijeme;
    }

    public boolean setSadasnjeVrijeme(String unesenoVrijeme) {
        Date datumNovi = VirtualnoVrijeme.getInstance().stringUDatum(unesenoVrijeme);
        if (datumNovi == null) {
            return false;
        }
        if (VirtualnoVrijeme.sadasnjeVrijeme == null) {
            VirtualnoVrijeme.sadasnjeVrijeme = datumNovi;
            return true;
        } else if (provjeriSljednostDatuma(datumNovi)) {
            VirtualnoVrijeme.sadasnjeVrijeme = datumNovi;
            return true;
        }
        return false;
    }

    public Date getSadasnjeVrijeme() {
        return sadasnjeVrijeme;
    }

    public Date stringUDatum(String datumString) {
        Date datum ;
        try {
            datum = formater.parse(datumString);
        } catch (ParseException ex) {
            Odgovor odgovor = new OdgovorImplement()
                    .setStatus("ERR")
                    .setPovratnaPoruka("Unjeli ste krivi zapis datuma! potrebno: "
                            + "YYYY-MM-DD HH:MM:SS napisano: " + datumString)
                    .build();
            Pocetak.modelOdogovor.setOdgovor(odgovor);
            return null;
        }
        return datum;
    }

    public boolean provjeriSljednostDatuma(Date datum) {
        if (datum.before(sadasnjeVrijeme)) {
            Odgovor odgovor = new OdgovorImplement()
                    .setStatus("ERR")
                    .setPovratnaPoruka(("Vrijeme je u proslosti! Treba unjeti vrijeme"
                            + " vece od: " + datumUString(sadasnjeVrijeme)+" ").replace("\n", ""))
                    .build();
            Pocetak.modelOdogovor.setOdgovor(odgovor);
            return false;
        }
        return true;
    }

    
    /**
     * Vraca true ukoliko je drugi datum poslje prvog datuma Ponasa se kao
     * datumOd<datumDo
     * @param datumOd
     * @param datumDo
     * @return 
     */
    public static boolean usporediDvaDatuma(Date datumOd, Date datumDo) {
        return !datumDo.before(datumOd);
    }

    public String datumUString(Date datum) {
        String formatiraniDatum;
        formatiraniDatum = formater.format(datum);
        return formatiraniDatum;
    }

}
