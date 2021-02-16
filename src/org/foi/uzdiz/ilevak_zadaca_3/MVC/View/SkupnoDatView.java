/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.MVC.View;

import java.io.FileWriter;
import java.io.IOException;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.Model.ModelOdgovor;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.Observer.ObserverPromjene;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.Odgovor;

/**
 *
 * @author ivale
 */
public class SkupnoDatView extends ObserverPromjene implements View {

    String ispis = "";
    public static String naziv = "SKUPNO2";
    String pogled = "\nPogled 3 --skupni način rada, datoteka";
    public static FileWriter fileWriter;
    int duzinaPrethodnog = 0;
    public String defaultBoja="\u001B[0m";

    public SkupnoDatView(ModelOdgovor modelOdogovor) {
        this.modelOdogovor = modelOdogovor;
    }

    @Override
    public void updateView() {
        ispisi(modelOdogovor.getOdgovor());
    }

    public void kreirajPisac() throws IOException {
        ispis = "";
        fileWriter = new FileWriter(modelOdogovor.getPutDatotekeIspis());
    }

    @Override
    public void konzola() {
        System.out.println(defaultBoja+vertikala + pogled + vertikala);
        try {
            kreirajPisac();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public String getNaziv() {
        return naziv;
    }

    @Override
    public void ispisi(Odgovor odgovor) {
        if (odgovor.getStatus().equals("ERR")) {
            ispis += izgradiString(duzinaPrethodnog, "X") + "\n";
        } else {
            duzinaPrethodnog = odgovor.getPovratnaPoruka().length();
            ispis += odgovor.getPovratnaPoruka() + "\n";
        }
    }

    public void upisiUDatoteku() {
        try {
            fileWriter.write(ispis);
            fileWriter.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public String izgradiString(int brojPonavljanja, String oznaka) {
        StringBuilder strB = new StringBuilder();
        for (int i = 0; i < brojPonavljanja; i++) {
            strB.append(oznaka);
        }
        return strB.toString();
    }
}
