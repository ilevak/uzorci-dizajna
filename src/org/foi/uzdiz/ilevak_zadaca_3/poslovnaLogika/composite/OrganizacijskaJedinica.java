/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Racun;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.VrstaVozila;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain.AbstractAktivnost;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.iterator.Iterator;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.iterator.OrganizacijaIterator;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Redak;

/**
 *
 * @author ivale
 */
public class OrganizacijskaJedinica implements AbstractOJ {

    int id;
    String naziv;
    int idRoditelj;
    List<AbstractOJ> popisJedinica = new ArrayList<>();
    int brojvozila = 0;

    public OrganizacijskaJedinica(int id, String naziv, int idRoditelj) {
        this.id = id;
        this.naziv = naziv;
        this.idRoditelj = idRoditelj;
    }

    public OrganizacijskaJedinica() {
    }

    public void add(AbstractOJ oj) {
        this.popisJedinica.add(oj);
    }

    public int getIdRoditelj() {
        return idRoditelj;
    }

    public void setIdRoditelj(int idRoditelj) {
        this.idRoditelj = idRoditelj;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<AbstractOJ> getPopisJedinica() {
        return popisJedinica;
    }

    public void setPopisJedinica(List<AbstractOJ> popisJedinica) {
        this.popisJedinica = popisJedinica;
    }

    private OrganizacijaIterator napraviIterator() {
        return new OrganizacijaIterator(popisJedinica);
    }

    /**
     * orgj ili oj-- organizacijska jedinica
     *
     * @param idJedinice
     * @return jedinicu sa odredenim id-om
     */
    @Override
    public AbstractOJ pronadiJedinicu(int idJedinice) {
        AbstractOJ orgjPronadena;
        AbstractOJ oj;
        OrganizacijaIterator orgjIterator = napraviIterator();
        if (this.id == idJedinice) {
            return this;
        }
        while (orgjIterator.hasNext()) {
            oj = orgjIterator.next();
            if (oj.getClass() == OrganizacijskaJedinica.class) {
                orgjPronadena = oj.pronadiJedinicu(idJedinice);
                if (orgjPronadena != null) {
                    return orgjPronadena;
                }
            }
        }
        return null;
    }

    @Override
    public void napuniTablicuStanja() {
        Redak redak;

        List<VrstaVozila> popisVrsteVozila = Podaci.popisVrsteVozila;
        iteratorVrste.osvjezi();
        VrstaVozila vrstaVozila;
        while (iteratorVrste.hasNext()) {
            vrstaVozila = (VrstaVozila) iteratorVrste.next();
            redak = new Redak();
            redak.setNazivLok(compositeBuilder + naziv);
            redak.setNazivVozila(vrstaVozila.getNaziv());
            redak.setBrojRaspMjesta(this.getBrojRaspMjesta(vrstaVozila.getId()));
            redak.setBrojRaspVoz(this.getBrojRaspVozila(vrstaVozila.getId()));
            redak.setBrojStrganihVozila(this.getBrojStrgVozila(vrstaVozila.getId()));
            AbstractAktivnost.tablica.addRedak(redak);
        }

        compositeBuilder.append(":");
        Iterator iterator = napraviIterator();
        AbstractOJ oj;
        while (iterator.hasNext()) {
            oj = (AbstractOJ) iterator.next();
            oj.napuniTablicuStanja();
        }
        compositeBuilder.setLength(
                compositeBuilder.length() - 1);
    }

    @Override
    public void napuniTablicuNajmaZarade(Date datumOd, Date datumDo) {
        
        Redak redak;
        iteratorVrste.osvjezi();
        VrstaVozila vrstaVozila;
        while (iteratorVrste.hasNext()) {
            vrstaVozila = (VrstaVozila) iteratorVrste.next();
            redak = new Redak();
            redak.setNazivLok(compositeBuilder + naziv);
            redak.setNazivVozila(vrstaVozila.getNaziv());
            redak.setBrojNajma(this.getBrojNajma(vrstaVozila.getId(), datumOd, datumDo));
            redak.setZarada(this.getZarada(vrstaVozila.getId(), datumOd, datumDo));
            redak.setTrajanjeNajma(this.getTrajanjeNajma(vrstaVozila.getId(), datumOd, datumDo));
            AbstractAktivnost.tablica.addRedak(redak);
        }
        compositeBuilder.append(":");
        Iterator iterator = napraviIterator();
        AbstractOJ oj;
        while (iterator.hasNext()) {
            oj = (AbstractOJ) iterator.next();
            oj.napuniTablicuNajmaZarade(datumOd, datumDo);
        }
        compositeBuilder.setLength(
                compositeBuilder.length() - 1);
    }

    @Override
    public void napuniTablicuRacuna(Date datumOd, Date datumDo) {
        Redak redak;
        iteratorVrste.osvjezi();
        VrstaVozila vrstaVozila;
        while (iteratorVrste.hasNext()) {
            vrstaVozila = (VrstaVozila) iteratorVrste.next();
            redak = new Redak();
            redak.setNazivLok(compositeBuilder + naziv);
            redak.setNazivVozila(vrstaVozila.getNaziv());
            redak.setListaRacuna(this.getRacun(vrstaVozila.getId(), datumOd, datumDo));
            AbstractAktivnost.tablica.addRedak(redak);
        }
        compositeBuilder.append(":");
        Iterator iterator = napraviIterator();
        AbstractOJ oj;
        while (iterator.hasNext()) {
            oj = (AbstractOJ) iterator.next();
            oj.napuniTablicuRacuna(datumOd, datumDo);
        }
        compositeBuilder.setLength(
                compositeBuilder.length() - 1);
    }

    @Override
    public int getBrojRaspMjesta(int idVrste) {
        int brojRaspolozivihMjesta = 0;
        Iterator iterator = napraviIterator();
        AbstractOJ oj;
        while (iterator.hasNext()) {
            oj = (AbstractOJ) iterator.next();
            brojRaspolozivihMjesta += oj.getBrojRaspMjesta(idVrste);
        }
        return brojRaspolozivihMjesta;
    }

    @Override
    public int getBrojRaspVozila(int idVrste) {
        int brojRaspolozivihVozila = 0;
        Iterator iterator = napraviIterator();
        AbstractOJ oj;
        while (iterator.hasNext()) {
            oj = (AbstractOJ) iterator.next();
            brojRaspolozivihVozila += oj.getBrojRaspVozila(idVrste);
        }
        return brojRaspolozivihVozila;
    }

    @Override
    public int getBrojStrgVozila(int idVrste) {
        int brojStrganihVozila = 0;
        Iterator iterator = napraviIterator();
        AbstractOJ oj;
        while (iterator.hasNext()) {
            oj = (AbstractOJ) iterator.next();
            brojStrganihVozila += oj.getBrojStrgVozila(idVrste);
        }
        return brojStrganihVozila;
    }

    @Override
    public int getBrojNajma(int idVrste, Date datumOd, Date datumDo) {
        int brojNajmova = 0;
        Iterator iterator = napraviIterator();
        AbstractOJ oj;
        while (iterator.hasNext()) {
            oj = (AbstractOJ) iterator.next();
            brojNajmova += oj.getBrojNajma(idVrste, datumOd, datumDo);
        }
        return brojNajmova;
    }

    @Override
    public int getTrajanjeNajma(int idVrste, Date datumOd, Date datumDo) {
        int vrijemeTrajanja = 0;
        Iterator iterator = napraviIterator();
        AbstractOJ oj;
        while (iterator.hasNext()) {
            oj = (AbstractOJ) iterator.next();
            vrijemeTrajanja += oj.getTrajanjeNajma(idVrste, datumOd, datumDo);
        }
        return vrijemeTrajanja;
    }

    @Override
    public float getZarada(int idVrste, Date datumOd, Date datumDo) {
        float zarada = 0;
        Iterator iterator = napraviIterator();
        AbstractOJ oj;
        while (iterator.hasNext()) {
            oj = (AbstractOJ) iterator.next();
            zarada += oj.getZarada(idVrste, datumOd, datumDo);;
        }
        return zarada;
    }

    @Override
    public List<Racun> getRacun(int idVrste, Date datumOd, Date datumDo) {
        List<Racun> listaRacuna = new ArrayList<>();

        Iterator iterator = napraviIterator();
        AbstractOJ oj;
        while (iterator.hasNext()) {
            oj = (AbstractOJ) iterator.next();
            listaRacuna.addAll(oj.getRacun(idVrste, datumOd, datumDo));
        }
        return listaRacuna;
    }

}
