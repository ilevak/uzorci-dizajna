package org.foi.uzdiz.ilevak_zadaca_3.podaci;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ivale
 */
public class Cjenik {

    private VrstaVozila vrstaVozila;
    private float cijenaNajma;
    private float cijenaPoSatu;
    private float cijenaPoKm;

    public Cjenik() {
    }

    public Cjenik(VrstaVozila vrstaVozila, float cijenaNajma, float cijenaPoSatu, float cijenaPoKm) {
        this.vrstaVozila = vrstaVozila;
        this.cijenaNajma = cijenaNajma;
        this.cijenaPoSatu = cijenaPoSatu;
        this.cijenaPoKm = cijenaPoKm;
    }

    public VrstaVozila getVrstaVozila() {
        return vrstaVozila;
    }

    public void setVrstaVozila(VrstaVozila vrstaVozila) {
        this.vrstaVozila = vrstaVozila;
    }

    public float getCijenaNajma() {
        return cijenaNajma;
    }

    public void setCijenaNajma(float cijenaNajma) {
        this.cijenaNajma = cijenaNajma;
    }

    public float getCijenaPoSatu() {
        return cijenaPoSatu;
    }

    public void setCijenaPoSatu(float cijenaPoSatu) {
        this.cijenaPoSatu = cijenaPoSatu;
    }

    public float getCijenaPoKm() {
        return cijenaPoKm;
    }

    public void setCijenaPoKm(float cijenaPoKm) {
        this.cijenaPoKm = cijenaPoKm;
    }

}
