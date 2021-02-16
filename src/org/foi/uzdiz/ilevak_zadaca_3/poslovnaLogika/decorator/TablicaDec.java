/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.decorator;

import org.foi.uzdiz.ilevak_zadaca_3.podaci.Redak;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivale
 */
public interface TablicaDec {

    public static List<String> redci = new ArrayList<>();

    public String getZaglavlje();

    public String getFormat();

    public String getVertikala();

    /**
     * Podaci su u obliku "prviParametar, drugiParametar, treciParametar, ..."
     *
     * @param redak
     * @return
     */
    public String napraviRedak(Redak redak);
}
