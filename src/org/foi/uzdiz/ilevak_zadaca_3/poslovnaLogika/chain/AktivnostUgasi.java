/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain;

import org.foi.uzdiz.ilevak_zadaca_3.izvodenje.Pocetak;

/**
 *
 * @author ivale
 */
public class AktivnostUgasi extends AbstractAktivnost {

    int potrebanBrojArg = 2;

    public AktivnostUgasi(int level) {
        this.level = level;
    }

    @Override
    protected void izvrsi(String naredba) {
        potvrda = "";
        error = "";
        if (provjeriNaredbu(naredba)) {
            String[] elementiNaredbe = naredba.split(";");
            if (postaviNovoVirtualnoVrijeme(elementiNaredbe[1]) && provjeriBrojArg(elementiNaredbe, potrebanBrojArg)) {
                Pocetak.modelOdogovor.setUgasi(true);
            }
        }
    }

    

}
