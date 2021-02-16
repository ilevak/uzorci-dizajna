package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Koordinata;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Racun;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Vozilo;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.VrstaVozila;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chain.AbstractAktivnost;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Redak;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ivale
 */
public class Lokacija implements AbstractOJ {

    private int id;
    private String naziv;
    private String adresa;
    private Koordinata koordinata;
    public List<Vozilo> popisVozila;

    public Lokacija(int id, String naziv, String adresa, Koordinata koordinata) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.koordinata = koordinata;
        popisVozila = new ArrayList<>();
    }

    public Lokacija() {
        popisVozila = new ArrayList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int idLokacije) {
        this.id = idLokacije;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Koordinata getKoordinata() {
        return koordinata;
    }

    public void setKoordinata(Koordinata koordinata) {
        this.koordinata = koordinata;
    }

    public void sortirajListuPopisVozila() {
        Collections.sort(popisVozila, Comparator.comparing(Vozilo::getBrojNajma)
                .thenComparing(Vozilo::getUkupanBrKm)
                .thenComparing(Vozilo::getId));
    }

    public int pronadiBrojRaspolozivihVozila(int idVrsteVozila) {
        int brojRaspolozivihVozila = 0;
        brojRaspolozivihVozila = popisVozila.stream().filter(v
                -> (v.jeRaspolozivo(idVrsteVozila))).map(_item -> 1)
                .reduce(brojRaspolozivihVozila, Integer::sum);
        return brojRaspolozivihVozila;
    }

    /**
     * Unutar popisa vozila lokacije se nalaze samo vozila koja su strgana, na
     * punjenju i slobodna
     *
     * @param idVrstaVozila
     * @return
     */
    //ovo se mozda treba maknut  kako bi bila model
    public Vozilo iznajmiVozilo(int idVrstaVozila) {
        sortirajListuPopisVozila();
        for (Vozilo v : popisVozila) {
            if (v.iznajmi(idVrstaVozila)) {
                popisVozila.remove(v);
                v.setIdLokacijeNajma(id);
                return v;
            }
        }
        return null;
    }

    public int pronadiBrojRaspolozivihMjesta(int idVrsteVozila) {
        int ukupniBrojMjesta
                = Podaci.getInstance().pronadiUkupniBrojMjesta(idVrsteVozila, this.id);
        int brojRaspolozivihMjesta = 0;
        int brojVozilaNaLokaciji = pronadiUkupanBrojVozila(idVrsteVozila);
        if (ukupniBrojMjesta != 0) {
            brojRaspolozivihMjesta = ukupniBrojMjesta - brojVozilaNaLokaciji;
        }
        return brojRaspolozivihMjesta;
    }

    private int pronadiUkupanBrojVozila(int idVrsteVozila) {
        int brojacVozila = 0;
        brojacVozila = popisVozila.stream().filter(v -> (v.getVrstaVozila().getId() == idVrsteVozila)).map(_item -> 1).reduce(brojacVozila, Integer::sum);
        return brojacVozila;
    }

    private int pronadiBrStrganihVozila(int idVrsteVozila) {
        int brojacVozila = 0;
        brojacVozila = popisVozila.stream().filter(v -> (v.jeStrgano(idVrsteVozila))).map(_item -> 1).reduce(brojacVozila, Integer::sum);
        return brojacVozila;
    }

    @Override
    public void napuniTablicuStanja() {
        Redak redak;        
        iteratorVrste.osvjezi();
        VrstaVozila vrstaVozila;
        while(iteratorVrste.hasNext()){
            vrstaVozila=(VrstaVozila) iteratorVrste.next();
            redak = new Redak();
            redak.setNazivLok(compositeBuilder + naziv);
            redak.setNazivVozila(vrstaVozila.getNaziv());
            redak.setBrojRaspMjesta(pronadiBrojRaspolozivihMjesta(vrstaVozila.getId()));
            redak.setBrojRaspVoz(pronadiBrojRaspolozivihVozila(vrstaVozila.getId()));
            redak.setBrojStrganihVozila(pronadiBrStrganihVozila(vrstaVozila.getId()));
            AbstractAktivnost.tablica.addRedak(redak);
        }
    }

    @Override
    public void napuniTablicuNajmaZarade(Date datumOd, Date datumDo) {
        Redak redak;        
        iteratorVrste.osvjezi();
        VrstaVozila vrstaVozila;
        while(iteratorVrste.hasNext()){
            vrstaVozila=(VrstaVozila) iteratorVrste.next();
            redak = new Redak();
            redak.setNazivLok(compositeBuilder + naziv);
            redak.setNazivVozila(vrstaVozila.getNaziv());
            redak.setBrojNajma(this.getBrojNajma(vrstaVozila.getId(), datumOd, datumDo));
            redak.setTrajanjeNajma(this.getTrajanjeNajma(vrstaVozila.getId(), datumOd, datumDo));
            redak.setZarada(this.getZarada(vrstaVozila.getId(), datumOd, datumDo));
            AbstractAktivnost.tablica.addRedak(redak);
        }
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
    }

    @Override
    public int getBrojRaspMjesta(int idVrsteVozila) {
        return pronadiBrojRaspolozivihMjesta(idVrsteVozila);
    }

    @Override
    public int getBrojRaspVozila(int idVrste) {
        return pronadiBrojRaspolozivihVozila(idVrste);
    }

    @Override
    public int getBrojStrgVozila(int idVrste) {
        return pronadiBrStrganihVozila(idVrste);
    }

    @Override
    public AbstractOJ pronadiJedinicu(int idOJ) {
        return null;
    }

    @Override
    public int getBrojNajma(int idVrste, Date datumOd, Date datumDo) {
        int brojacNajma = 0;
        brojacNajma = Podaci.popisRacuna.stream().filter(racun -> (datumOd.before(racun.getDatumNajma())
                && datumDo.after(racun.getDatumVracanja())
                && racun.getIdLokacijaNajma() == id
                && idVrste == racun.getVozilo().getVrstaVozila().getId())).map(_item -> 1).reduce(brojacNajma, Integer::sum);
        return brojacNajma;
    }

    @Override
    public int getTrajanjeNajma(int idVrste, Date datumOd, Date datumDo) {
        int brojSati = 0;
        brojSati = Podaci.popisRacuna.stream().filter(racun -> (datumOd.before(racun.getDatumNajma())
                && datumDo.after(racun.getDatumVracanja())
                && racun.getIdLokacijaNajma() == this.id
                && idVrste == racun.getVozilo().getVrstaVozila().getId())).map(racun -> racun.getVrijemeNajma()).reduce(brojSati, Integer::sum);
        return brojSati;
    }

    @Override
    public float getZarada(int idVrste, Date datumOd, Date datumDo) {
        float zarada = 0;
        zarada = Podaci.popisRacuna.stream().filter(racun -> (datumOd.before(racun.getDatumNajma())
                && datumDo.after(racun.getDatumVracanja())
                && racun.getIdLokacijaNajma() == this.id
                && idVrste == racun.getVozilo().getVrstaVozila().getId())).map(racun -> racun.getUkupniIznos()).reduce(zarada, (accumulator, _item) -> accumulator + _item);
        return zarada;
    }

    @Override
    public List<Racun> getRacun(int idVrste, Date datumOd, Date datumDo) {
        return Podaci.getInstance().pronadiRacune(id, idVrste, datumOd, datumDo);
    }

}
