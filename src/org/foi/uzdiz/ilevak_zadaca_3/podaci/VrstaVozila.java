/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.podaci;

/**
 *
 * @author ivale
 */
public class VrstaVozila {

    private int id;
    private String naziv;
    private int vrijemePunjenaBaterije;
    private int domet;

    public VrstaVozila(int id, String naziv, int vrijemePunjenaBaterije, int domet) {
        this.id = id;
        this.naziv = naziv;
        this.vrijemePunjenaBaterije = vrijemePunjenaBaterije;
        this.domet = domet;
    }
    
    public VrstaVozila(int id){
        this.id=id;
    }

    public VrstaVozila() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int idVozila) {
        this.id = idVozila;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getVrijemePunjenaBaterije() {
        return vrijemePunjenaBaterije;
    }

    public void setVrijemePunjenaBaterije(int vrijemePunjenaBaterije) {
        this.vrijemePunjenaBaterije = vrijemePunjenaBaterije;
    }

    public int getDomet() {
        return domet;
    }

    public void setDomet(int domet) {
        this.domet = domet;
    }
}
