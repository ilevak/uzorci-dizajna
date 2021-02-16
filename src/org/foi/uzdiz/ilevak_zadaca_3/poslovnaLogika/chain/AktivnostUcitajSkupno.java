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
public class AktivnostUcitajSkupno extends AbstractAktivnost {

    public AktivnostUcitajSkupno(int level) {
        this.level = level;
    }

    @Override
    protected void izvrsi(String naredba) {
        potvrda = "";
        error = "";
        if (provjeriNaredbu(naredba)) {
            String[] elementiNaredbe = naredba.split(";");
            ucitajDatotekuAktivnosti(elementiNaredbe);
        }
    }

    private void ucitajDatotekuAktivnosti(String[] elementiNaredbe) {
        int potrebanBrojArg = 2;
        if (provjeriBrojArg(elementiNaredbe, potrebanBrojArg)) {
            String put = elementiNaredbe[1];
            String naredba = "s " + put.trim();
            Pocetak.modelOdogovor.setPutDatotekeAktivnosti(naredba);
        }   
    }
}
