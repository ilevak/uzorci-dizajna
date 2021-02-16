/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.poslovnaLogika.builderOdgovor;

/**
 *
 * @author ivale
 */
public class Odgovor {

    String status;
    String poruka;

    public Odgovor() {
    }

    public Odgovor(String status, String poruka) {
        this.status = status;
        this.poruka = poruka;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPovratnaPoruka() {
        return poruka;
    }

    public void setPovratnaPoruka(String povratnaPoruka) {
        this.poruka = povratnaPoruka;
    }

    @Override
    public String toString() {
        return status + ";" + poruka;
    }

}
