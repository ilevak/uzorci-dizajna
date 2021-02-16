/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.iterator;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.VrstaVozila;

/**
 *
 * @author ivale
 */
public class VrsteVozilaIterator implements Iterator {

    private List<VrstaVozila> popisVrsta = new ArrayList<>();
    int pozicija;

    public VrsteVozilaIterator(List<VrstaVozila> popisVrsta) {
        this.popisVrsta = popisVrsta;
    }

    @Override
    public boolean hasNext() {
        if (popisVrsta == null || popisVrsta.isEmpty()) {
            return false;
        } else return !(pozicija >= popisVrsta.size() || popisVrsta.get(pozicija) == null);
    }

    @Override
    public VrstaVozila next() {
        VrstaVozila vrsta = popisVrsta.get(pozicija);
        pozicija++;
        return vrsta;
    }

    @Override
    public void osvjezi() {
        pozicija = 0;
    }
}
