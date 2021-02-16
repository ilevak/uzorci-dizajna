/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.uzdiz.ilevak_zadaca_3.izvodenje.Pocetak;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.VirtualnoVrijeme;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.Odgovor;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.OdgovorImplement;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderPodaciOperacijaVozila.PodaciOperacijeBuilder;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderPodaciOperacijaVozila.PodaciOperacijeDirektor;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderPodaciOperacijaVozila.PodaciOperacijeImplement;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Tablica;

/**
 *
 * @author ivale
 */
public abstract class AbstractAktivnost {

    public static int RASPOLOZIVA_VOZILA = 1;
    public static int NAJAM = 2;
    public static int RASPOLOZIVA_MJESTA = 3;
    public static int VRACANJE = 4;
    public static int SKUPNI = 5;
    public static int STANJE = 6;
    public static int NAJAM_ZARADA = 7;
    public static int RACUNI = 8;
    public static int FINANCIJE_OSOBA = 9;
    public static int RACUNI_OSOBA = 10;
    public static int PLATI = 11;
    public static int UGASI = 0;

    public static Tablica tablica;
    protected Date datumIzvodenja = null;
    public static boolean radi = true;
    public static String error = "";
    public static String potvrda = "";
    Odgovor odgovor;

    protected int level;

    protected AbstractAktivnost nextAktivnost;

    public void setNextAktivnost(AbstractAktivnost nextAktivnost) {
        this.nextAktivnost = nextAktivnost;
    }

    public void izvrsiAktivnost(int level, String naredba) {
        if (this.level == level) {
            if (provjeraIntegera(naredba.split(";")[0])) {
                izvrsi(naredba);
                ispisiPovratnuPoruku();
            }
        } else if (nextAktivnost != null) {
            nextAktivnost.izvrsiAktivnost(level, naredba);
        }

    }

    abstract protected void izvrsi(String naredba);

    protected boolean provjeriNaredbu(String naredba) {
        String[] elementiNaredbe = naredba.split(";");
        return provjeraBrojaElemenata(elementiNaredbe);
    }

    private boolean provjeraBrojaElemenata(String[] elementiNaredbe) {
        if (elementiNaredbe.length == 1) {
            error = "Niste upisali dovoljno argumenata";
            return false;
        }
        return true;
    }

    public static boolean provjeraIdAktivnosti(String idAktivnosti) {
        try {
            if (Integer.parseInt(idAktivnosti.trim()) > 11) {
                error = "Ne postoji aktivnost sa brojem " + idAktivnosti;
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            error = "Niste unjeli broj za idAktivnosti!";
            return false;
        }
    }

    protected boolean postaviNovoVirtualnoVrijeme(String unesenoVrijeme) {
        unesenoVrijeme = unesenoVrijeme.trim()
                .replace("„", "")
                .replace("“", "")
                .replace("\"", "");
        if (VirtualnoVrijeme.getInstance().setSadasnjeVrijeme(unesenoVrijeme)) {
            datumIzvodenja = VirtualnoVrijeme.getInstance().getSadasnjeVrijeme();
            return true;
        } else {
            return false;
        }
    }

    protected boolean provjeriBrojArg(String[] elementiNaredbe, int potrebanBrojArg) {
        if (elementiNaredbe.length == potrebanBrojArg) {
            return true;
        } else {
            error = "Unjeli ste previse ili premalo argumenata";

            return false;
        }
    }

    protected boolean provjeriBrojArg(String[] elementiNaredbe, int potrebanBrojArg, int mogucBrojArg) {
        if (elementiNaredbe.length == potrebanBrojArg || elementiNaredbe.length == mogucBrojArg) {
            return true;
        } else {
            error = "Unjeli ste previse ili premalo argumenata";
            return false;
        }
    }

    protected int stringUInteger(String brojString) {

        try {
            return Integer.parseInt(brojString.trim());
        } catch (NumberFormatException e) {
            error = "Niste unjeli dobre podatke(slovo umjesto broja)!";
        }
        return -1;
    }

    protected boolean provjeraIntegera(String brojString) {

        try {
            Integer.parseInt(brojString.trim());
            return true;
        } catch (NumberFormatException e) {
            error = "Niste unjeli dobre podatke(slovo umjesto broja)!";
            return false;
        }
    }

    protected Date pronadiVrijeme(String[] elementi, int index) {
        String datumS = elementi[index];
        Date datum = stringUDatum(datumS);
        if (datum == null) {
            error = "Niste upisali datum!"
                    + " Datum pocetka treba biti na 3. mjestu od kraja"
                    + " a datum kraja na 2. mjestu od kraja naredbe!";
        }
        return datum;
    }

    protected PodaciOperacijeDirektor kreirajDirektor() {
        final PodaciOperacijeBuilder podaciOperacijeBuilder
                = new PodaciOperacijeImplement();
        final PodaciOperacijeDirektor podaciOperacijeDirektor
                = new PodaciOperacijeDirektor(podaciOperacijeBuilder);
        return podaciOperacijeDirektor;
    }

    protected Date stringUDatum(String datumString) {
        Date datum;
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        try {
            datum = formater.parse(datumString);
        } catch (ParseException ex) {
            error = "Unjeli ste krivi zapis datuma! Potrebno: "
                    + "dd.MM.yyyy  napisano: " + datumString;
            return null;
        }
        return datum;
    }

    /**
     * Metoda patternMatch usporeduje sintaksu sa dobivenim parametrima i ako
     * ona odgovara vraca Matcher kako bi se doslo do grupa podataka, a ako ne
     * odgovara baca iznimku
     *
     * @param sintaksa
     * @param naredba
     * @return m Matcher ako je ispravan unos
     */
    public static boolean patternMatch(String sintaksa, String naredba) {
        Pattern pattern = Pattern.compile(sintaksa);
        naredba = naredba.trim();
        Matcher m = pattern.matcher(naredba);
        return m.matches();
    }

    private void ispisiPovratnuPoruku() {
        if (!potvrda.isBlank()) {
            odgovor = new OdgovorImplement()
                    .setStatus("OK")
                    .setPovratnaPoruka(potvrda)
                    .build();
            Pocetak.modelOdogovor.setOdgovor(odgovor);
        } else if (!error.isBlank()) {
            odgovor = new OdgovorImplement()
                    .setStatus("ERR")
                    .setPovratnaPoruka(error)
                    .build();
            Pocetak.modelOdogovor.setOdgovor(odgovor);
        }

    }

}
