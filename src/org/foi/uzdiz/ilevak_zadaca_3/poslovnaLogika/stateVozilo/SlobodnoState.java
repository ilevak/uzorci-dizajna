/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.stateVozilo;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.VirtualnoVrijeme;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Vozilo;

/**
 *
 * @author ivale
 */
public class SlobodnoState implements StateVozilo {

    /**
     * aktivira uvijek sljedece stanje ako je moguce ovisno o tome je li idvrste
     * dobar
     *
     * @param vozilo
     * @param idVrste
     * @return
     */
    @Override
    public boolean sljedeceStanje(Vozilo vozilo, int idVrste) {
        if (idVrste == vozilo.getIdVrsteVozila()) {
            vozilo.setStatus(new IznajmljenState());
            vozilo.setDatumNajma(VirtualnoVrijeme.getInstance().getSadasnjeVrijeme());
            vozilo.brojNajma++;
            return true;
        }
        return false;
    }

    /**
     * Unajmljuje vozilo ako je moguće
     *
     * @param vozilo
     * @param idVrste
     * @return
     */
    @Override
    public boolean iznajmiVozilo(Vozilo vozilo, int idVrste) {

        return this.sljedeceStanje(vozilo, idVrste);

    }

    /**
     * Provjerava je li vozilo moguce vratiti i vraca
     *
     * @param vozilo
     * @param idVrste
     * @param opisKvara
     * @param brojPrijedenihKm
     * @return uspjesnost vracanja
     */
    @Override
    public boolean vratiVozilo(Vozilo vozilo, int idVrste, String opisKvara, int brojPrijedenihKm) {
        return false;
    }

 /**
     * Provjerava je li vozilo raspolozivo za najam
     * i je li unesene vrste
     *
     * @param vozilo
     * @param idVrste
     * @return
     */
    @Override
    public boolean raspolozivo(Vozilo vozilo, int idVrste) {
        return vozilo.getIdVrsteVozila() == idVrste;
    }

    /**
     *
     * Provjerava je li vozilo strgano i je li unesene vrste
     *
     * @param vozilo
     * @param idVrste
     * @return
     */
    @Override
    public boolean strgano(Vozilo vozilo, int idVrste) {
        return false;
    }

}
