/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderPodaciOperacijaVozila;

/**
 *
 * @author ivale
 */
public class PodaciOperacijeDirektor {

    private final PodaciOperacijeBuilder builder;

    public PodaciOperacijeDirektor(PodaciOperacijeBuilder builder) {
        this.builder = builder;
    }

    public PodaciOperacije upisi(int idVozila, int idLokacije) {
        return builder
                .setLokacija(idLokacije)
                .setVrstaVozila(idVozila)
                .build();
    }

    public PodaciOperacije upisi(int idVozila, int idLokacije, int idOsoba) {
        return builder
                .setLokacija(idLokacije)
                .setVrstaVozila(idVozila)
                .setOsoba(idOsoba)
                .build();
    }

    public PodaciOperacije vratiVozilo(int idVozila, int idLokacije, int idOsoba, int brojPrijednihKm) {
        return builder
                .setLokacija(idLokacije)
                .setVrstaVozila(idVozila)
                .setOsoba(idOsoba)
                .setBrojPrijedenihKm(brojPrijednihKm)
                .build();
    }

    public PodaciOperacije upisi(int idVozila, int idLokacije, int idOsoba, int brojPrijednihKm, String opisKvara) {
        return builder
                .setLokacija(idLokacije)
                .setVrstaVozila(idVozila)
                .setOsoba(idOsoba)
                .setBrojPrijedenihKm(brojPrijednihKm)
                .setOpisKvara(opisKvara)
                .build();
    }

}
