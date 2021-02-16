package org.foi.uzdiz.ilevak_zadaca_3.podaci;

import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite.Lokacija;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite.OrganizacijskaJedinica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ivale
 */
public class Podaci {

    private int maxTekstTab;
    private int maxCijeliTab;
    private int maxDecimTab;
    private  float  maxDugovanje=0;
    

    private static Podaci instancaPodaci = null;
    public static OrganizacijskaJedinica korijen;
    public static List<Osoba> popisOsoba = new ArrayList<>();
    public static List<VrstaVozila> popisVrsteVozila = new ArrayList<>();
    public static List<Cjenik> popisCijena = new ArrayList<>();
    public static List<Lokacija> popisLokacija = new ArrayList<>();
    public static List<KapacitetLokacije> popisLokacijaKapaciteti = new ArrayList<>();
    public static List<Racun> popisRacuna = new ArrayList<>();
    
    private Podaci() {
        //inicijalni podaci
        maxCijeliTab = 5;
        maxDecimTab = 2;
        maxTekstTab = 30;
    }

    public static Podaci getInstance() {
        if (instancaPodaci == null) {
            instancaPodaci = new Podaci();

        }

        return instancaPodaci;
    }

    public VrstaVozila pronadiVrstuVozilaPutemId(int id) {
        for (VrstaVozila v : popisVrsteVozila) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }
    
    public List<Racun> pronadiRacune(int idOsobe, Date datumOd, Date datumDo) {
        List<Racun> listaRacuna = new ArrayList<>();
        Podaci.popisRacuna.stream().filter(racun -> (datumOd.before(racun.getDatumNajma())
                && datumDo.after(racun.getDatumVracanja())
                && idOsobe==racun.getIdOsobe())).forEachOrdered(racun -> {
                    listaRacuna.add(racun);
        });
        return listaRacuna;
    }
    
    public List<Racun> pronadiRacune(int idLokacija, int idVrste, Date datumOd, Date datumDo){
        List<Racun> listaRacuna = new ArrayList<>();
        Podaci.popisRacuna.stream().filter(racun -> (datumOd.before(racun.getDatumNajma())
                && datumDo.after(racun.getDatumVracanja())
                && racun.getIdLokacijaNajma() == idLokacija
                && idVrste == racun.getVozilo().getVrstaVozila().getId())).forEachOrdered(racun -> {
                    listaRacuna.add(racun);
        });
        return listaRacuna;
    }
    public Lokacija pronadiLokacijuPutemId(int id) {
        for (Lokacija l : popisLokacija) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;

    }

    public Osoba pronadiOsobuPutemId(int id) {
        for (Osoba o : popisOsoba) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;

    }
      public Racun pronadiRacunPutemId(int id) {
        for (Racun r : popisRacuna) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;

    }

    public int pronadiUkupniBrojMjesta(int idVozila, int idLokacija) {
        int ukupniBrojMjesta = 0;
        for (KapacitetLokacije k : popisLokacijaKapaciteti) {
            if (k.getVrstaVozila().getId() == idVozila
                    && k.getLokacija().getId() == idLokacija) {
                ukupniBrojMjesta = k.getBrojMjestaZaVrstuVozila();
            }
        }
        return ukupniBrojMjesta;
    }

    public Cjenik pronadiCjenik(int idVozila) {
        for (Cjenik cjenik : popisCijena) {
            if (cjenik.getVrstaVozila().getId() == idVozila) {
                return cjenik;
            }
        }
        return null;
    }

    public int getMaxTekstTab() {
        return maxTekstTab;
    }

    public void setMaxTekstTab(int maxTekstTab) {
        this.maxTekstTab = maxTekstTab;
    }

    public int getMaxCijeliTab() {
        return maxCijeliTab;
    }

    public void setMaxCijeliTab(int maxCijeliTab) {
        this.maxCijeliTab = maxCijeliTab;
    }

    public int getMaxDecimTab() {
        return maxDecimTab;
    }

    public void setMaxDecimTab(int maxDecimTab) {
        this.maxDecimTab = maxDecimTab;
    }

    public float getMaxDugovanje() {
        if(maxDugovanje==0){
            return Float.MAX_VALUE;
        }
        return maxDugovanje;
    }

    public void setMaxDugovanje(float maxDugovanje) {
        this.maxDugovanje = maxDugovanje;
    }


    
    

}
