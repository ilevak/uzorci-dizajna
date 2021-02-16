/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.iterator;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ilevak_zadaca_3.izvodenje.Pocetak;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.Odgovor;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.OdgovorImplement;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite.AbstractOJ;

/**
 *
 * @author ivale
 */
public class OrganizacijaIterator implements Iterator {

    private List<AbstractOJ> orgJedinice = new ArrayList<>();
    int pozicija;

    public OrganizacijaIterator(List<AbstractOJ> orgJedinice) {
        this.orgJedinice = orgJedinice;
    }

    @Override
    public boolean hasNext() {
        if (orgJedinice.isEmpty()) {
            Odgovor odgovor = new OdgovorImplement()
                    .setStatus("ERR")
                    .setPovratnaPoruka("Lista lokacija je prazna")
                    .build();
            Pocetak.modelOdogovor.setOdgovor(odgovor);
            return false;
        } else return !(pozicija >= orgJedinice.size() || orgJedinice.get(pozicija) == null);
    }

    @Override
    public AbstractOJ next() {
        AbstractOJ orgJedinica = orgJedinice.get(pozicija);
        pozicija++;
        return orgJedinica;
    }

    @Override
    public void osvjezi() {
        pozicija = 0;
    }

}
