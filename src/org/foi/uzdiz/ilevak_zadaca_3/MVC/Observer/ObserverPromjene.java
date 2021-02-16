/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ilevak_zadaca_3.MVC.Observer;

import org.foi.uzdiz.ilevak_zadaca_3.MVC.Model.ModelOdgovor;

/**
 *
 * @author ivale
 */
public abstract class ObserverPromjene {
    protected ModelOdgovor modelOdogovor;
    public abstract void updateView();
}
