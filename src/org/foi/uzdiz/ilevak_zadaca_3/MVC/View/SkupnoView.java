/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.MVC.View;

import org.foi.uzdiz.ilevak_zadaca_3.MVC.Model.ModelOdgovor;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.Observer.ObserverPromjene;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.Odgovor;

/**
 *
 * @author ivale
 */
public class SkupnoView extends ObserverPromjene implements View {

    public static String naziv = "SKUPNO1";
    String pogled = "\nPogled 2 --skupni način rada, ispis";
    public String crvenaBoja = "\033[31m";
    public String defaultBoja="\u001B[0m";
    public SkupnoView(ModelOdgovor modelOdogovor) {
        this.modelOdogovor = modelOdogovor;
    }

    @Override
    public void ispisi(Odgovor odgovor) {
        if (odgovor.getStatus().equals("ERR")) {
            System.out.println(crvenaBoja+odgovor.getPovratnaPoruka());
        } else {
            System.out.println(defaultBoja+odgovor.getPovratnaPoruka());
        }
    }

    @Override
    public void updateView() {
        ispisi(modelOdogovor.getOdgovor());
    }

    @Override
    public void konzola() {
        System.out.println(defaultBoja+vertikala+pogled+vertikala);
    }

    @Override
    public String getNaziv() {
        return naziv;
    }

}
