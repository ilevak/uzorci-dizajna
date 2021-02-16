/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.stateVozilo;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Vozilo;

/**
 *
 * @author ivale
 */
public interface StateVozilo {
   
    public boolean sljedeceStanje(Vozilo vozilo, int idVrste);
    public boolean iznajmiVozilo(Vozilo vozilo, int idVrste);
    public boolean vratiVozilo(Vozilo vozilo, int idVrste, String opisKvara, int brojPrijedenihKm);
    public boolean raspolozivo(Vozilo vozilo, int idVrste);
    public boolean strgano(Vozilo vozilo, int idVrste);
}
