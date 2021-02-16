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
public interface OdgovorBuilder {
    Odgovor build();
    OdgovorBuilder setStatus(String status);
    OdgovorBuilder setPovratnaPoruka(String povratnaPoruka);
}
