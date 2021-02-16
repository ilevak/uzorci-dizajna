/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.MVC.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.Model.ModelOdgovor;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.Observer.ObserverPromjene;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.Odgovor;

/**
 *
 * @author ivale
 */
public class InteraktivnoView extends ObserverPromjene implements View {

    public static String naziv = "INTERAKTIVNO";
    public static BufferedReader bufferedReader;
    String pogled = "\nPogled 1 --interaktivni način rada, ispis";
    public String crvenaBoja = "\033[31m";
    public String defaultBoja = "\u001B[0m";

    public InteraktivnoView(ModelOdgovor modelOdogovor) {
        this.modelOdogovor = modelOdogovor;
    }

    @Override
    public void ispisi(Odgovor odgovor) {
        if (odgovor.getStatus().equals("ERR")) {
            System.out.println(crvenaBoja + odgovor.getPovratnaPoruka());
        } else {
            System.out.println(defaultBoja + odgovor.getPovratnaPoruka());
        }
    }

    @Override
    public void updateView() {
        ispisi(modelOdogovor.getOdgovor());
    }

    @Override
    public void konzola() {
        System.out.println(defaultBoja + vertikala + pogled + vertikala);
    }

    /**
     * Ponaša se kao event, ukoliko je napisana nova linija, event okida
     * funkciju u Controlleru(primi liniju i odradi posao)
     *
     * @return
     */
    public String citac() {

        System.out.println(defaultBoja + "\nUpisite akciju:");
        pokreniCitac();
        return procitajKonzolnuLiniju();
    }

    /**
     * U mojem command promptu je namješten Active code page na IBM-852
     */
    private void pokreniCitac() {
        bufferedReader
                = new BufferedReader(new InputStreamReader(System.in, Charset.forName("IBM852")));
    }

    private String procitajKonzolnuLiniju() {
        try {
            return bufferedReader.readLine();
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return null;
    }

    @Override
    public String getNaziv() {
        return naziv;
    }

}
