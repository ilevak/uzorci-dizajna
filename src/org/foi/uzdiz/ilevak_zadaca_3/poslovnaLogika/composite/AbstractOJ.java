/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite;

import java.util.Date;
import java.util.List;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Racun;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.iterator.Iterator;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.iterator.VrsteVozilaIterator;

/**
 *
 * @author ivale
 */
public interface AbstractOJ {

    public AbstractOJ pronadiJedinicu(int idJedinice);

    public int getId();

    public int getBrojNajma(int idVrste, Date datumOd, Date datumDo);

    public int getTrajanjeNajma(int idVrste, Date datumOd, Date datumDo);

    public float getZarada(int idVrste, Date datumOd, Date DatumDo);

    public int getBrojRaspMjesta(int idVrste);

    public int getBrojRaspVozila(int idVrste);

    public int getBrojStrgVozila(int idVrste);

    public List<Racun> getRacun(int idVrste, Date datumOd, Date datumDo);

    public void napuniTablicuStanja();

    public void napuniTablicuNajmaZarade(Date datumOd, Date datumDo);

    public void napuniTablicuRacuna(Date datumOd, Date datumDo);

    public static StringBuffer compositeBuilder = new StringBuffer();
    
    public static Iterator iteratorVrste=new VrsteVozilaIterator(Podaci.popisVrsteVozila);
    
}
