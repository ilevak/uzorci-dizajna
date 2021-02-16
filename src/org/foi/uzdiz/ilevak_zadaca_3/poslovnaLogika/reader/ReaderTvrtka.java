/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.foi.uzdiz.ilevak_zadaca_3.izvodenje.Pocetak;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite.Lokacija;
import org.foi.uzdiz.ilevak_zadaca_3.podaci.Podaci;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor.OdgovorImplement;
import org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.composite.OrganizacijskaJedinica;

/**
 *
 * @author ivale
 */
public class ReaderTvrtka extends Reader {

    private final int[] lokacije = new int[Podaci.popisLokacija.size()];
    private int brojacLokacije = 0;
    private int[] organizacijskeJedinice;
    private String[] popisIdLokacija = null;
    private int brojacOJ = 0;
    private OrganizacijskaJedinica orgJed;
    private OrganizacijskaJedinica orgJedRoditelj;
    private String[] elementiLinije;
    private Lokacija lokacija;
    private boolean postojiKorijen = false;
    private final List<OrganizacijskaJedinica> popisOJ = new ArrayList<>();
    int brojElemSLok = 4;

    public ReaderTvrtka() {
        super();
    }

    @Override
    public void read(String putDatoteke) throws IOException, ProvjeraException {
        kreirajCitacDatoteke(putDatoteke);

        procitajLinijuDatoteke();
        String linijaDatoteke;
        String porukaUpozorenja = "";
        while ((linijaDatoteke = procitajLinijuDatoteke()) != null) {
            try {
                elementiLinije = linijaDatoteke.split(";");
                validacijaSadrzajaLinije(elementiLinije);
                procitajOJ();
            } catch (ProvjeraException msg) {
                porukaUpozorenja += "Linija: " + linijaDatoteke
                        + " je neispravna! " + msg;
            }

            if (!porukaUpozorenja.isBlank()) {
                odgovor = new OdgovorImplement()
                        .setStatus("ERR")
                        .setPovratnaPoruka(porukaUpozorenja)
                        .build();
                Pocetak.modelOdogovor.setOdgovor(odgovor);
            }
        }
        provjeraUpisanihLokacija();
        sortirajPopisOJDesc();
        kreirajStablo();
        postaviVrijednosti();

    }

    public void validacijaSadrzajaLinije(String[] elementi) throws ProvjeraException {
        if (elementi.length > 2 && elementi.length < 5) {
            if (!elementiLinije[2].isBlank()) {
                provjeraInt(elementi[2].trim());
            }
            provjeraInt(elementi[0].trim());
            provjeraNaziv(elementi[1]);
            if (elementi.length == 4) {
                provjeraIntLokacija(elementi[3]);
            }
        } else {
            throw new ProvjeraException("Upisali ste previse ili premalo elemenata");
        }
    }

    public void provjeraInt(String n) throws ProvjeraException {
        try {
            Integer.parseInt(n);
        } catch (NumberFormatException e) {
            throw new ProvjeraException("Ocekivao se broj, a unjeli ste:" + n);
        }
    }

    public void provjeraIntLokacija(String linija) throws ProvjeraException {
        String[] loks = linija.split(",");
        for (String lok : loks) {
            provjeraInt(lok.trim());
        }
    }

    public void provjeraNaziv(String naziv) throws ProvjeraException {
        if (naziv.isBlank()) {
            throw new ProvjeraException("Niste upisali naziv!");
        }
    }

    private void procitajOJ() throws ProvjeraException {
        String naziv = elementiLinije[1].trim();
        int idOJ = Integer.parseInt(elementiLinije[0].trim());
        int idRoditelj = pronadiIdRoditelja(elementiLinije[2]);
        if (pronadiOjUListi(idOJ) != null) {
            throw new ProvjeraException("Vec je upisana organizacijska jedinica"
                    + " s id:" + idOJ);
        }
        orgJed = new OrganizacijskaJedinica(idOJ, naziv, idRoditelj);
        dodajLokacijeOJ();
        popisOJ.add(orgJed);
    }

    private void dodajLokacijeOJ() throws ProvjeraException, NumberFormatException {
        if (elementiLinije.length == brojElemSLok) {
            popisIdLokacija = elementiLinije[3].split(",");

            if (popisIdLokacija != null && popisIdLokacija.length > 0) {
                for (String idLokacije : popisIdLokacija) {
                    lokacija = Podaci.getInstance().pronadiLokacijuPutemId(Integer.parseInt(idLokacije.trim()));
                    if (lokacija != null && Arrays.binarySearch(lokacije, lokacija.getId()) < 0) {
                        lokacije[Arrays.binarySearch(lokacije, 0)] = lokacija.getId();
                        brojacLokacije++;
                        Arrays.sort(lokacije);
                        orgJed.add(lokacija);
                    } else {
                        throw new ProvjeraException(" Lokacija " + lokacija.getNaziv()
                                + " je vec zapisana u drugu Organizacijsku jedinicu");
                    }
                }
            }
        }
    }

    public void sortirajPopisOJDesc() {
        Collections.sort(popisOJ, Comparator.comparing(OrganizacijskaJedinica::getIdRoditelj).reversed());
    }

    public OrganizacijskaJedinica pronadiOjUListi(int idOj) {
        for (OrganizacijskaJedinica orgj : popisOJ) {
            if (orgj.getId() == idOj) {
                return orgj;
            }
        }
        return null;
    }

    private int pronadiIdRoditelja(String n) throws ProvjeraException {
        if (!n.isBlank()) {
            return Integer.parseInt(n.trim());
        } else if (!postojiKorijen) {
            postojiKorijen = true;
            return 0;
        }
        throw new ProvjeraException("Vec imate korijensku organizacijsku jedinicu a zeite dodati novu!");
    }

    private void provjeraUpisanihLokacija() throws ProvjeraException {
        if (brojacLokacije < lokacije.length) {
            throw new ProvjeraException("Nisu upisane sve lokacije!!");
        }
    }

    private void kreirajStablo() throws ProvjeraException {
        organizacijskeJedinice = new int[popisOJ.size()];
        popisOJ.stream().map(orgj -> {
            orgJedRoditelj = pronadiOjUListi(orgj.getIdRoditelj());
            return orgj;
        }).filter(orgj -> (orgJedRoditelj != null)).forEachOrdered(orgj -> {
            if (Arrays.binarySearch(organizacijskeJedinice, orgj.getId()) < 0) {
                organizacijskeJedinice[Arrays.binarySearch(organizacijskeJedinice, 0)] = orgj.getId();
                brojacOJ++;
                Arrays.sort(organizacijskeJedinice);
                orgJedRoditelj.add(orgj);
            } else {
                throw new ProvjeraException("Organizacijska jedinica " + orgj.getNaziv() + " je vec zapisana u drugu Organizacijsku jedinicu");
            }
        });
        OrganizacijskaJedinica orgj = new OrganizacijskaJedinica();
        orgj.setIdRoditelj(0);
        Collections.sort(popisOJ, Comparator.comparing(OrganizacijskaJedinica::getIdRoditelj));
        int indeks = Collections.binarySearch(popisOJ, orgj, Comparator.comparing(OrganizacijskaJedinica::getIdRoditelj));
        if (indeks >= 0) {
            Podaci.korijen = popisOJ.get(indeks);
        }
        provjeraUpisanihOJ();
    }

    private void provjeraUpisanihOJ() throws ProvjeraException {
        if (brojacOJ < organizacijskeJedinice.length - 1) {

            throw new ProvjeraException("Nisu upisane sve OJ!!");
        }
    }

    private void postaviVrijednosti() {
        Podaci.popisVrsteVozila.stream().map(vrstaVozila -> {
            Podaci.korijen.getBrojRaspMjesta(vrstaVozila.getId());
            return vrstaVozila;
        }).map(vrstaVozila -> {
            Podaci.korijen.getBrojRaspVozila(vrstaVozila.getId());
            return vrstaVozila;
        }).forEachOrdered(vrstaVozila -> {
            Podaci.korijen.getBrojStrgVozila(vrstaVozila.getId());
        });
    }

}
