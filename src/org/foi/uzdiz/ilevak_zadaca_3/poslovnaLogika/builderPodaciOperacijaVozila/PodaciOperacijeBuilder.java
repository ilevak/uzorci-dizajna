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
public interface PodaciOperacijeBuilder {
    PodaciOperacije build();
    PodaciOperacijeBuilder setBrojPrijedenihKm(final int brojPrijedenihKm);
    PodaciOperacijeBuilder setVrstaVozila(final int idVrsteVozila);
    PodaciOperacijeBuilder setLokacija(final int idLokacija);
    PodaciOperacijeBuilder setOsoba(final int idOsoba);
    PodaciOperacijeBuilder setOpisKvara(String opisproblema);
}

