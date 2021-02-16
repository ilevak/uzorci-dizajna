/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chainRacun;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Racun;

/**
 *
 * @author ivale
 */
public abstract class AbstractRacun{

    protected AbstractRacun sljedecaAkcija;
    
    protected int level;
    
    public static int EVIDENCIJA = 1;
    
    public static int PRETRAGA = 2;
    
    public static int OBRADA = 3;

    abstract public Racun izvrsi(Racun racun, int level);

    public void postaviSljdecuAkciju(AbstractRacun sljedecaAkcija) {
        this.sljedecaAkcija = sljedecaAkcija;
    }

    
}
