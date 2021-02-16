/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.izvodenje;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.Controller.Controller;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.Model.ModelOdgovor;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.View.InteraktivnoView;
import org.foi.uzdiz.ilevak_zadaca_3.MVC.View.SkupnoDatView;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.NeispravnaKonfiguracija;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.NemaKonfiguracije;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.VirtualnoVrijeme;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.Odgovor;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.OdgovorImplement;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.reader.ProvjeraException;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.reader.Reader;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.reader.ReaderFactory;

/**
 *
 * @author ivale
 */
public class Pocetak {

    public static ModelOdgovor modelOdogovor;

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length > 0) {
            try {
                modelOdogovor = new ModelOdgovor();
                modelOdogovor.setPogledPrikaza(InteraktivnoView.naziv);
                ucitajKonfig(args);
                Controller controller = new Controller(modelOdogovor);
                controller.izvrsi();
            } catch (NemaKonfiguracije | NeispravnaKonfiguracija ex) {
                Logger.getLogger(Pocetak.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("Niste upisali nikakve podatke!");
        }
    }

    private static void ucitajKonfig(String[] args) throws NemaKonfiguracije, NeispravnaKonfiguracija {
        Konfiguracija konf = new Konfiguracija();
        konf.ucitajKonfiguraciju(args[0]);
        Properties props = konf.dajSvePostavke();
        Set<String> keys = props.stringPropertyNames();
        provjeriKeyKonfig(keys);
        ucitajDatotekeKonfig(keys, props);

    }

    private static void ucitajDatotekeKonfig(Set<String> keys, Properties props) {
        String kapacitet = "";
        String cjenik = "";
        String struktura = "";
        String skupniElementi;

        for (String key : keys) {
            System.out.println(key + " " + props.getProperty(key));
            switch (key) {
                case "vrijeme":
                    ucitajVrijeme(key + " " + props.getProperty(key));
                    break;
                case "kapaciteti":
                    kapacitet = key + " " + props.getProperty(key);
                    break;
                case "cjenik":
                    cjenik = key + " " + props.getProperty(key);
                    break;
                case "aktivnosti":
                    skupniElementi = key + " " + props.getProperty(key);
                    modelOdogovor.setPutDatotekeAktivnosti(skupniElementi);
                    break;
                case "struktura":
                    struktura = key + " " + props.getProperty(key);
                    break;
                case "cijeli":
                    upisiMaxCijeli(props, key);
                    break;
                case "tekst":
                    upisiMaxTekst(props, key);
                    break;
                case "decimala":
                    upisiMaxDecim(props, key);
                    break;
                case "izlaz":
                    upisiIzlaznuDatoteku(props, key);
                    break;
                case "dugovanje":
                    upisiDugovanje(props, key);
                    break;
                default:
                    ucitajDatoteke(key + " " + props.getProperty(key));
                    break;
            }

        }
        ucitajDatoteke(kapacitet);
        ucitajDatoteke(cjenik);
        ucitajDatoteke(struktura);
    }

    private static void upisiMaxCijeli(Properties props, String key) {
        int broj;
        try {
            broj = Integer.parseInt(props.getProperty(key));
            Podaci.getInstance().setMaxCijeliTab(broj);
        } catch (NumberFormatException n) {
            System.out.println("Unutar konfiguracije ste "
                    + "umjesto broja za cijeli napisali nesto drugo: " + props.getProperty(key));
        }
    }

    private static void upisiMaxDecim(Properties props, String key) {
        int broj;
        try {
            broj = Integer.parseInt(props.getProperty(key));
            Podaci.getInstance().setMaxDecimTab(broj);
        } catch (NumberFormatException n) {
            System.out.println("Unutar konfiguracije ste "
                    + "umjesto broja za decimalu napisali nesto drugo: " + props.getProperty(key));
        }
    }

    private static void upisiMaxTekst(Properties props, String key) {
        int broj;
        try {
            broj = Integer.parseInt(props.getProperty(key));
            Podaci.getInstance().setMaxTekstTab(broj);
        } catch (NumberFormatException n) {
            System.out.println("Unutar konfiguracije ste "
                    + "umjesto broja za tekst napisali nesto drugo: " + props.getProperty(key));
        }
    }

    public static String argsToString(String[] args) {
        StringBuilder sb = new StringBuilder();

        for (String s : args) {
            sb.append(s).append(" ");
        }
        String komanda = sb.toString().trim().replace("„", "").replace("“", "");

        return komanda;
    }

    private static void provjeriKeyKonfig(Set<String> keys) throws NeispravnaKonfiguracija {
        String[] obavezneOznake = new String[]{"struktura", "vozila", "lokacije", "vrijeme", "osobe", "kapaciteti", "cjenik"};
        String sveMoguceOznake = " struktura vozila lokacije vrijeme osobe kapaciteti cjenik"
                + " aktivnosti tekst cijeli decimala dugovanje izlaz ";
        Arrays.sort(obavezneOznake);
        for (String s : obavezneOznake) {
            if (!keys.contains(s)) {
                throw new NeispravnaKonfiguracija("Vasa konfiguracija se ne sastoji od potrebne oznake " + s);
            }
        }
        for (String k : keys) {
            if (!sveMoguceOznake.contains(" " + k + " ")) {
                throw new NeispravnaKonfiguracija("Kriva oznaka u konfiguraciji: " + k);
            }
        }
    }

    private static void upisiDugovanje(Properties props, String key) {
        float broj;
        try {
            broj = Float.parseFloat(props.getProperty(key).trim().replace(",", "."));
            Podaci.getInstance().setMaxDugovanje(broj);
        } catch (NumberFormatException n) {
            System.out.println("Unutar konfiguracije ste "
                    + "umjesto broja za dugovanje napisali nesto drugo: " + props.getProperty(key));
        }
    }

    private static void upisiIzlaznuDatoteku(Properties props, String key) {
        String putIzlazneDatoteke = props.getProperty(key);
        if (!putIzlazneDatoteke.isBlank() && !putIzlazneDatoteke.isEmpty()) {
            modelOdogovor.setPogledPrikaza(SkupnoDatView.naziv);
            modelOdogovor.setPutDatotekeIspis(putIzlazneDatoteke);
        } else {
            System.out.println("Unutar konfiguracije ste za izlaznu datoteku ostavili prazno");
        }
    }
    
    public static void ucitajVrijeme(String elementi) {
        String[] vrijednosti = elementi.split(" ");
        String datum = vrijednosti[1].trim() + " " + vrijednosti[2].trim();
        VirtualnoVrijeme.getInstance().setSadasnjeVrijeme(datum);
    }

        public static void ucitajDatoteke(String elementi) {
        String[] vrijednosti = elementi.replaceAll("( )+", " ").split(" ");
        if (vrijednosti.length == 2) {
            String oznakaMjestaSpremanja = vrijednosti[0].trim();
            String putDatoteke = vrijednosti[1].trim();
            ReaderFactory readerFactory = new ReaderFactory();
            Reader reader = readerFactory.fileReader(oznakaMjestaSpremanja);
            if (reader != null) {
                try {
                    reader.read(putDatoteke);
                } catch (IOException | ProvjeraException ex) {
                    Odgovor odgovor = new OdgovorImplement()
                            .setStatus("ERR")
                            .setPovratnaPoruka(ex.getMessage())
                            .build();
                    Pocetak.modelOdogovor.setOdgovor(odgovor);
                    Controller.interaktivno = true;
                    Pocetak.modelOdogovor.setPogledPrikaza(InteraktivnoView.naziv);
                }
            }
        }

    }
}
