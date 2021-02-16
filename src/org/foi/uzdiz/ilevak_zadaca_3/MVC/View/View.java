/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.MVC.View;

import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.Odgovor;

/**
 *
 * @author ivale
 */

public interface View{
    
    String  vertikala="\n--------------------------------------";
    public void konzola();
    public String getNaziv();
    public void ispisi(Odgovor odgovor);
}
