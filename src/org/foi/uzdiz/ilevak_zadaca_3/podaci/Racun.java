/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.podaci;

import java.util.Date;

/**
 *
 * @author ivale
 */
public class Racun {

    private float cijenaNajma;
    private int vrijemeNajma;
    private int prijedeniBrKm;
    private float cijenaPoSatu;
    private float cijenaPoKm;
    private Vozilo vozilo;
    private Cjenik cjenik;
    private Date datumNajma;
    private Date datumVracanja;
    private int idLokacijaNajma;
    private int idLokacijaVracanja = 0;
    private float ukupniIznos;
    private int id;
    private int idOsobe;
    double iznosNajmaPoSatu;

    private boolean placen = false;
    double iznosNajmaPoKm;

    @Override
    public String toString() {
        cjenik = Podaci.getInstance().pronadiCjenik(vozilo.getVrstaVozila().getId());
        cijenaNajma = cjenik.getCijenaNajma();
        cijenaPoSatu = cjenik.getCijenaPoSatu();
        cijenaPoKm = cjenik.getCijenaPoKm();
        iznosNajmaPoSatu = vrijemeNajma * cijenaPoSatu;
        iznosNajmaPoKm = prijedeniBrKm * cijenaPoKm;
        ukupniIznos = (float) (cijenaNajma + iznosNajmaPoKm + iznosNajmaPoSatu);
        return "Stavke racuna: "
                + "\n najam " + vozilo.getVrstaVozila().getNaziv() + " : " + cijenaNajma + "kn "
                + "\n najam je bio " + vrijemeNajma + " sata : " + vrijemeNajma + "*" + cijenaPoSatu + "=" + iznosNajmaPoSatu + "kn "
                + "\n vozilo je proslo " + prijedeniBrKm + "km : " + prijedeniBrKm + "*" + cijenaPoKm + "=" + iznosNajmaPoKm + "kn"
                + "\n Racun ukupno iznosi ; " + ukupniIznos + "kn";
    }

    public int getVrijemeNajma() {
        return vrijemeNajma;
    }

    public void setVrijemeNajma(int vrijemeNajma) {
        this.vrijemeNajma = vrijemeNajma;
    }

    public int getPrijedeniBrKm() {
        return prijedeniBrKm;
    }

    public void setPrijedeniBrKm(int prijedeniBrKm) {
        this.prijedeniBrKm = prijedeniBrKm;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {

        cjenik = Podaci.getInstance().pronadiCjenik(vozilo.getVrstaVozila().getId());
        this.vozilo = vozilo;
    }

    public Date getDatumNajma() {
        return datumNajma;
    }

    public void setDatumNajma(Date datumNajma) {
        this.datumNajma = datumNajma;
    }

    public Date getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(Date datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public int getIdLokacijaNajma() {
        return idLokacijaNajma;
    }

    public void setIdLokacijaNajma(int idLokacijaNajma) {
        this.idLokacijaNajma = idLokacijaNajma;
    }

    public int getIdLokacijaVracanja() {
        return idLokacijaVracanja;
    }

    public void setIdLokacijaVracanja(int idLokacijaVracanja) {
        this.idLokacijaVracanja = idLokacijaVracanja;
    }

    public float getUkupniIznos() {
        cijenaNajma = cjenik.getCijenaNajma();
        cijenaPoSatu = cjenik.getCijenaPoSatu();
        cijenaPoKm = cjenik.getCijenaPoKm();
        iznosNajmaPoSatu = vrijemeNajma * cijenaPoSatu;
        iznosNajmaPoKm = prijedeniBrKm * cijenaPoKm;
        ukupniIznos = (float) (cijenaNajma + iznosNajmaPoKm + iznosNajmaPoSatu);
        return ukupniIznos;
    }

    public void setUkupniIznos(float ukupniIznos) {
        this.ukupniIznos = ukupniIznos;
    }

    public int getId() {
        return id;
    }

    public void setId(int redniBroj) {
        this.id = redniBroj;
    }

    public int getIdOsobe() {
        return idOsobe;
    }

    public void setIdOsobe(int idOsobe) {
        this.idOsobe = idOsobe;
    }

    public Cjenik getCjenik() {
        return cjenik;
    }

    public void setCjenik(Cjenik cjenik) {
        this.cjenik = cjenik;
    }

    public double getIznosNajmaPoSatu() {
        return iznosNajmaPoSatu;
    }

    public void setIznosNajmaPoSatu(double iznosNajmaPoSatu) {
        this.iznosNajmaPoSatu = iznosNajmaPoSatu;
    }

    public double getIznosNajmaPoKm() {
        return iznosNajmaPoKm;
    }

    public void setIznosNajmaPoKm(double iznosNajmaPoKm) {
        this.iznosNajmaPoKm = iznosNajmaPoKm;
    }

    public boolean isPlacen() {
        return placen;
    }

    public void setPlacen(boolean placen) {
        this.placen = placen;
    }

}
