/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor;

/**
 *
 * @author ivale
 */
public class OdgovorImplement implements OdgovorBuilder{

      Odgovor odgovor;

    public OdgovorImplement() {
        this.odgovor = new Odgovor();
    }

    @Override
    public Odgovor build() {
        return odgovor;
    }
    
    @Override
    public OdgovorBuilder setStatus(String status) {
        odgovor.setStatus(status);
        return this;
    }

    @Override
    public OdgovorBuilder setPovratnaPoruka(String povratnaPoruka) {
      odgovor.setPovratnaPoruka(povratnaPoruka);
      return this;
    }
    
}
