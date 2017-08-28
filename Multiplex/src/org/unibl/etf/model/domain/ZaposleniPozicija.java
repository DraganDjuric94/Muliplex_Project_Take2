/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

/**
 *
 * @author Aleksandar
 */
public class ZaposleniPozicija {

    private Integer zaposleniId;
    private Integer pozicijaId;

    public ZaposleniPozicija() {
    }

    public ZaposleniPozicija(Integer zaposleniId, Integer pozicijaId) {
        this.zaposleniId = zaposleniId;
        this.pozicijaId = pozicijaId;
    }

    public Integer getZaposleniId() {
        return zaposleniId;
    }

    public Integer getPozicijaId() {
        return pozicijaId;
    }

    public void setZaposleniId(Integer zaposleniId) {
        this.zaposleniId = zaposleniId;
    }

    public void setPozicijaId(Integer pozicijaId) {
        this.pozicijaId = pozicijaId;
    }

    @Override
    public String toString() {
        return "ZaposleniPozicija{" + "zaposleniId=" + zaposleniId + ", pozicijaId=" + pozicijaId + '}';
    }

}
