/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.podaci;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Racun;

/**
 *
 * @author ivale
 */
public class Redak {
    List<Racun> listaRacuna;
    String nazivLok;
    String nazivVozila;
    int brojNajma;
    int trajanjeNajma;
    int brojRaspMjesta;
    int brojRaspVoz;
    float zarada;
    int brojStrganihVozila;
    int idOsoba=0;

    public String getNazivLok() {
        return nazivLok;
    }

    public void setNazivLok(String nazivLok) {
        this.nazivLok = nazivLok;
    }

    public String getNazivVozila() {
        return nazivVozila;
    }

    public void setNazivVozila(String nazivVozila) {
        this.nazivVozila = nazivVozila;
    }

    public int getBrojNajma() {
        return brojNajma;
    }

    public void setBrojNajma(int brojNajma) {
        this.brojNajma = brojNajma;
    }

    public int getTrajanjeNajma() {
        return trajanjeNajma;
    }

    public void setTrajanjeNajma(int trajanjeNajma) {
        this.trajanjeNajma = trajanjeNajma;
    }

    public int getBrojRaspMjesta() {
        return brojRaspMjesta;
    }

    public void setBrojRaspMjesta(int brojRaspMjesta) {
        this.brojRaspMjesta = brojRaspMjesta;
    }

    public int getBrojRaspVoz() {
        return brojRaspVoz;
    }

    public void setBrojRaspVoz(int brojRaspVoz) {
        this.brojRaspVoz = brojRaspVoz;
    }

    public float getZarada() {
        return zarada;
    }

    public void setZarada(float zarada) {
        this.zarada = zarada;
    }

    public int getBrojStrganihVozila() {
        return brojStrganihVozila;
    }

    public void setBrojStrganihVozila(int brojStrganihVozila) {
        this.brojStrganihVozila = brojStrganihVozila;
    }

    public List<Racun> getListaRacuna() {
        return listaRacuna;
    }

    public void setListaRacuna(List<Racun> listaRacuna) {
        this.listaRacuna=new ArrayList<>();
        this.listaRacuna = listaRacuna;
    }

    public int getIdOsoba() {
        return idOsoba;
    }

    public void setIdOsoba(int idOsoba) {
        this.idOsoba = idOsoba;
    }

   
    
            
}
