/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.stateVozilo;

import java.util.Date;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.VirtualnoVrijeme;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Vozilo;

/**
 *
 * @author ivale
 */
public class PunjenjeState implements StateVozilo {

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
        if (baterijaPuna(vozilo) && idVrste == vozilo.getIdVrsteVozila()) {
            vozilo.setStatus(new SlobodnoState());
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
        return (sljedeceStanje(vozilo, idVrste)) ? vozilo.iznajmi(idVrste) : false;
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
     *
     * @param vozilo
     * @param idVrste
     * @return boolean
     */
    @Override
    public boolean raspolozivo(Vozilo vozilo, int idVrste) {
        return sljedeceStanje(vozilo, idVrste);
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

    public boolean baterijaPuna(Vozilo vozilo) {
        if (vozilo.getDatumVracanja() == null) {
            return true;
        } else {
            float brojProteklihSati = izracunajProtekleSate(vozilo.getDatumVracanja());
            float brojSatiPunjenjaBaterije = izracunajSatePunjenja(vozilo);
            if (brojProteklihSati >= brojSatiPunjenjaBaterije) {
                vozilo.setPostotakUtroseneBaterije(0);
                return true;
            } else {
                return false;
            }
        }
    }

    private float izracunajSatePunjenja(Vozilo vozilo) {
        return (vozilo.getPostotakUtroseneBaterije() / 100) * vozilo.getVrstaVozila().getVrijemePunjenaBaterije();
    }

    private float izracunajProtekleSate(Date datumVracanja) {
        float milisekunde = VirtualnoVrijeme.getInstance().getSadasnjeVrijeme().getTime() - datumVracanja.getTime();
        return (float) ((milisekunde / (1000 * 60 * 60)));
    }

}
