package org.foi.uzdiz.ilevak_zadaca_3.podaci;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ivale
 */
public class Osoba {

    private int id;
    private String imePrezime;
    private List<Vozilo> iznajmljenaVozila = new ArrayList<>();
    private int brojKvarova = 0;
    private boolean postojanjeUgovora;
    private float dugovanje = 0;
    private Date datumZadnjegNajma;
    private float uplacenIznos;
    private int brojNeplacenihRacuna=0;

    public Osoba(int id, String imePrezime, List<Vozilo> iznajmljenoVozilo) {
        this.id = id;
        this.imePrezime = imePrezime;
        this.iznajmljenaVozila = iznajmljenoVozilo;
    }

    public Osoba() {
    }

    public int getId() {
        return id;
    }

    public void setId(int idOsobe) {
        this.id = idOsobe;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public boolean postojanjeUgovora() {
        return postojanjeUgovora;
    }

    public void setPostojanjeUgovora(boolean postojanjeUgovora) {
        this.postojanjeUgovora = postojanjeUgovora;
    }

    public float getDugovanje() {
        return dugovanje;
    }

    public void setDugovanje(float dugovanje) {
        this.dugovanje =dugovanje;
    }

    public Date getDatumZadnjegNajma() {
        return datumZadnjegNajma;
    }

    public void setDatumZadnjegNajma(Date datumZadnjegNajma) {
        this.datumZadnjegNajma = datumZadnjegNajma;
    }

    public void smanjiBrojNeplacenihRacuna() {
        this.brojNeplacenihRacuna--;
    }

    public void povecajBrojNeplaenihRacuna() {
        this.brojNeplacenihRacuna++;
    }
    public int getBrojNeplacenihRacuna(){
        return this.brojNeplacenihRacuna;
    }

    public float getUplacenIznos() {
        return uplacenIznos;
    }

    public void setUplacenIznos(float uplacenIznos) {
        this.uplacenIznos = uplacenIznos;
    }

    public List<Vozilo> getIznajmljenaVozila() {
        return iznajmljenaVozila;
    }

    public void setIznajmljenoVozilo(Vozilo vozilo) {
        iznajmljenaVozila.add(vozilo);
    }

    public void removeIznajmljenoVozilo(int idVrsteVozila) {
        iznajmljenaVozila.remove(pronadiIznajmljenoVozilo(idVrsteVozila));
    }

    public int getBrojKvarova() {
        return brojKvarova;
    }

    public void povecajBrojKvarova() {
        this.brojKvarova++;
    }

    public void sortirajListuPopisVozila() {
        Collections.sort(iznajmljenaVozila, Comparator.comparing(Vozilo::getIdVrsteVozila));
    }

    public Vozilo pronadiIznajmljenoVozilo(int idVrsteVozila) {
        sortirajListuPopisVozila();
        Vozilo vozilo = new Vozilo();
        vozilo.setVrstaVozila(new VrstaVozila(idVrsteVozila));
        int indeks = Collections.binarySearch(iznajmljenaVozila, vozilo, Comparator.comparing(Vozilo::getIdVrsteVozila));
        if (indeks >= 0) {
            return iznajmljenaVozila.get(indeks);
        } else {
            return null;
        }
    }

    public Vozilo vratiVozilo(int idVrstaVozila, int brojPrijedenihKm, String opisKvara) {
        sortirajListuPopisVozila();
        for (Vozilo v : iznajmljenaVozila) {
            if (v.vrati(idVrstaVozila, brojPrijedenihKm, opisKvara)) {
                iznajmljenaVozila.remove(v);
                return v;
            }
        }
        return null;
    }

}
