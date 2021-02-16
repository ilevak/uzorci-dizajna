/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.chainRacun;

/**
 *
 * @author ivale
 */
public class LanacRacuna {
    private static LanacRacuna instancaLanac = null;
    
    public static int brojacRacuna = 1;
    public LanacRacuna() {

    }

    public static LanacRacuna getInstance() {
        if (instancaLanac == null) {
            instancaLanac = new LanacRacuna();
        }

        return instancaLanac;
    }

   public static AbstractRacun getLanacAktivnosti() {

        AbstractRacun evidentiraj = new RacunEvindentiraj(AbstractRacun.EVIDENCIJA);
        AbstractRacun obradi = new RacunObradi(AbstractRacun.OBRADA);
        AbstractRacun pretrazi = new RacunPretrazi(AbstractRacun.PRETRAGA);
   
        evidentiraj.postaviSljdecuAkciju(pretrazi);
        pretrazi.postaviSljdecuAkciju(obradi);

        return evidentiraj;

    }
}

