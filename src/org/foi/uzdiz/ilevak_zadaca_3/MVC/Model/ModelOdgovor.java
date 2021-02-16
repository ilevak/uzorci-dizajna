/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.MVC.Model;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.Observer.ObserverPromjene;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.View.InteraktivnoView;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.View.SkupnoDatView;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.View.SkupnoView;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.Odgovor;


/**
 *
 * @author ivale
 */

public class ModelOdgovor {

    private Odgovor odgovor;

    private String pogledPrikaza = InteraktivnoView.class.toString();
    private final List<ObserverPromjene> observerController = new ArrayList<>();
    private final List<ObserverPromjene> observersView = new ArrayList<>();
    private String putDatotekeAktivnosti;
    private String putDatotekeIspis="";
    private boolean ugasi=false;

    public ModelOdgovor() {
    }

    public Odgovor getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Odgovor odgovor) {
        this.odgovor = odgovor;
        notifyAllView();
    }

    public String getPogledPrikaza() {
        return pogledPrikaza;
    }

    public void setPogledPrikaza(String pogledPrikaza) {
        this.pogledPrikaza = pogledPrikaza;
        notifyAllControlers();
    }

    public String getPutDatotekeAktivnosti() {
        return putDatotekeAktivnosti;
    }

    public void setPutDatotekeAktivnosti(String putDatotekeAktivnosti) {
        this.putDatotekeAktivnosti = putDatotekeAktivnosti;
        this.pogledPrikaza=SkupnoView.naziv;
        notifyAllControlers();
    }

    public String getPutDatotekeIspis() {
        return putDatotekeIspis;
    }

    public void setPutDatotekeIspis(String putDatotekeIspis) {
        this.putDatotekeIspis = putDatotekeIspis;
        this.pogledPrikaza=SkupnoDatView.naziv;
        notifyAllControlers();
    }

    public boolean isUgasi() {
        return ugasi;
    }

    public void setUgasi(boolean ugasi) {
        this.ugasi = ugasi;
        notifyAllControlers();
    }
    
    

    public void dodajController(ObserverPromjene observer) {
        observerController.add(observer);
    }

    public void odvojiController(ObserverPromjene observer) {
        observerController.remove(observer);
    }
    public void dodajView(ObserverPromjene observer) {
        observersView.add(observer);
    }

    public void odvojiView(ObserverPromjene observer) {
        observersView.remove(observer);
    }


    public void notifyAllControlers() {
        observerController.forEach(observer -> {
            observer.updateView();
        });
    }
    public void notifyAllView() {
        observersView.forEach(observer -> {
            observer.updateView();
        });
    }

}
