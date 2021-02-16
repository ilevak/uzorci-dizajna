/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.iterator;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Racun;

/**
 *
 * @author ivale
 */
public class RacunIterator implements Iterator {

    private List<Racun> racuni = new ArrayList<>();
    int pozicija;

    public RacunIterator(List<Racun> racuni) {
        this.racuni = racuni;
    }

    @Override
    public boolean hasNext() {
        if (racuni == null || racuni.isEmpty()) {
            return false;
        } else return !(pozicija >= racuni.size() || racuni.get(pozicija) == null);
    }

    @Override
    public Racun next() {
        Racun racun = racuni.get(pozicija);
        pozicija++;
        return racun;
    }

    @Override
    public void osvjezi() {
        pozicija = 0;
    }

}
