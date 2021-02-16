/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Redak;

/**
 *
 * @author ivale
 */
public class TablicaPocetak implements TablicaDec{

    
    
    @Override
    public String getZaglavlje() {
        return "\n|";
    }

    @Override
    public String getFormat() {
       return "%n|";
    }

    @Override
    public String getVertikala() {
       return "\n-";
    }

    @Override
    public String napraviRedak(Redak redak) {
        return "\n|";
    }
    


    
 


 
}
