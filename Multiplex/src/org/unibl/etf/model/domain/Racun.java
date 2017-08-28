/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

import java.sql.Timestamp;

/**
 *
 * @author Aleksandar
 */
public class Racun {

    private Integer racunId;
    private Timestamp datumVrijeme;
    private Double ukupnaCijena;

    public Racun() {
    }

    public Racun(Integer racunId, Timestamp datumVrijeme, Double ukupnaCijena) {
        this.racunId = racunId;
        this.datumVrijeme = datumVrijeme;
        this.ukupnaCijena = ukupnaCijena;
    }

    public Integer getRacunId() {
        return racunId;
    }

    public Timestamp getDatumVrijeme() {
        return datumVrijeme;
    }

    public Double getUkupnaCijena() {
        return ukupnaCijena;
    }

    public void setRacunId(Integer racunId) {
        this.racunId = racunId;
    }

    public void setDatumVrijeme(Timestamp datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    public void setUkupnaCijena(Double ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    @Override
    public String toString() {
        return "Racun{" + "racunId=" + racunId + ", datumVrijeme=" + datumVrijeme + ", ukupnaCijena=" + ukupnaCijena + '}';
    }

}
