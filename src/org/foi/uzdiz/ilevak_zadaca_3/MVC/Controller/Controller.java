/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.MVC.Controller;

import java.io.IOException;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.Model.ModelOdgovor;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.Observer.ObserverPromjene;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.View.InteraktivnoView;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.View.SkupnoDatView;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.View.SkupnoView;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.View.View;
import org.foi.uzdiz.ilevak_zadaca_3.izvodenje.Pocetak;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain.AbstractAktivnost;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain.Lanac;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.reader.ProvjeraException;

/**
 *
 * @author ivale
 */
public class Controller extends ObserverPromjene {

    public static boolean interaktivno = true;
    private final SkupnoView skupnoView;
    private final SkupnoDatView skupnoUpisUDatoteku;
    private final InteraktivnoView interaktivnoView;
    private View pogledPrikaza;

    public Controller(ModelOdgovor model) {
        skupnoView = new SkupnoView(model);
        skupnoUpisUDatoteku = new SkupnoDatView(model);
        interaktivnoView = new InteraktivnoView(model);
        this.modelOdogovor = model;
    }

    public void izvrsi() throws ProvjeraException {
        this.modelOdogovor.dodajController(this);
        promijeniTrenutniPogled();
    }

    @Override
    public void updateView() throws ProvjeraException {
        if (modelOdogovor.isUgasi()) {
            ugasiSustav();
        } else {
            if (!this.pogledPrikaza.getNaziv().equals(modelOdogovor.getPogledPrikaza())) {
                odvojiPogled();
                promijeniTrenutniPogled();
            }
        }
    }

    private void promijeniTrenutniPogled() throws ProvjeraException {
        if (modelOdogovor.getPogledPrikaza().equals(InteraktivnoView.naziv)) {
            this.modelOdogovor.dodajView(interaktivnoView);
            pogledPrikaza = interaktivnoView;
            pogledPrikaza.konzola();
            pokreniInteraktivno();
        } else if (modelOdogovor.getPogledPrikaza().equals(SkupnoDatView.naziv)
                || !modelOdogovor.getPutDatotekeIspis().isBlank()) {
            pogledPrikaza = skupnoUpisUDatoteku;
            interaktivno = false;
            this.modelOdogovor.dodajView(skupnoUpisUDatoteku);
            pogledPrikaza.konzola();
            pokreniSkupno();
        } else if (modelOdogovor.getPogledPrikaza().equals(SkupnoView.naziv)
                && modelOdogovor.getPutDatotekeIspis().isBlank()) {
            pogledPrikaza = skupnoView;
            interaktivno = false;
            this.modelOdogovor.dodajView(skupnoView);
            pogledPrikaza.konzola();
            pokreniSkupno();
        }
    }

    private void odvojiPogled() {
        if (!modelOdogovor.getPutDatotekeIspis().isBlank()
                && !pogledPrikaza.getNaziv().equals(InteraktivnoView.naziv)) {
            ((SkupnoDatView) pogledPrikaza).upisiUDatoteku();
        }
        if (pogledPrikaza.getNaziv().equals(InteraktivnoView.naziv)) {
            modelOdogovor.odvojiView(interaktivnoView);
        } else if (pogledPrikaza.getNaziv().equals(SkupnoDatView.naziv)) {
            modelOdogovor.odvojiView(skupnoUpisUDatoteku);
        } else if (pogledPrikaza.getNaziv().equals(SkupnoView.naziv)) {
            modelOdogovor.odvojiView(skupnoView);
        }
    }

    private void pokreniInteraktivno() throws ProvjeraException {
        String linija;
        while ((linija = ((InteraktivnoView) pogledPrikaza).citac()) != null && interaktivno) {
            izvrsiAktivnost(linija);
        }
    }

    private void pokreniSkupno() {
        Pocetak.ucitajDatoteke(modelOdogovor.getPutDatotekeAktivnosti());

    }

    private void izvrsiAktivnost(String aktivnost) throws ProvjeraException {
        aktivnost = pripremiNaredbu(aktivnost);
        String[] elementiNaredbe = aktivnost.split(";");
        if (provjeriElemente(elementiNaredbe)) {

            int idAktivnosti = Integer.parseInt(elementiNaredbe[0]);
            Lanac.getLanacAktivnosti().izvrsiAktivnost(idAktivnosti, aktivnost);

        }
    }

    private boolean provjeriElemente(String[] elementiNaredbe) {
        return elementiNaredbe.length != 0 && AbstractAktivnost.provjeraIdAktivnosti(elementiNaredbe[0]);
    }

    private String pripremiNaredbu(String aktivnost) {
        return aktivnost.replace('„', ' ')
                .replace('“', ' ')
                .replace("\"", "")
                .replace('\u201c', ' ')
                .replace('\u201d', ' ')
                .replace('\u010d', 'c')
                .replaceAll("[\u010d]", "c")
                .replaceAll("[č]", "c")
                .replace("č", "c")
                .replaceAll("( )+", " ");
    }

    /**
     * Brise sve unutar konzole! 
     */
    public final static void ocistiKonzolu() {
        try {
            String[] cls = new String[]{"cmd.exe", "/c", "cls"};
            Runtime.getRuntime().exec(cls);
            if(Pocetak.modelOdogovor.getPogledPrikaza().equals(SkupnoDatView.naziv)){
                Thread.sleep(200);
            }
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex);
        }
    }

    public void ugasiSustav() {
        if ("INTERAKTIVNO".equals(pogledPrikaza.getNaziv())) {
            try {
                InteraktivnoView.bufferedReader.close();
                SkupnoDatView.fileWriter.close();
                interaktivno = false;
                System.exit(0);
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        odvojiPogled();
    }

}
