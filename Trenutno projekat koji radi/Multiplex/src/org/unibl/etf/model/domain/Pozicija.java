/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

import java.io.Serializable;
import java.sql.Date;


/**
 *
 * @author Aleksandar
 */
public class Pozicija{
    private Integer pozicijaId;
    private String naziv;
    private Date datumOd;
    private Date datumDo;
    
    public Pozicija(){
        
    }

    public Pozicija(Integer pozicijaId, String naziv, Date datumOd, Date datumDo) {
        this.pozicijaId = pozicijaId;
        this.naziv = naziv;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
    }

    public Integer getPozicijaId() {
        return pozicijaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setPozicijaId(Integer pozicijaId) {
        this.pozicijaId = pozicijaId;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    @Override
    public String toString() {
        return "Pozicija{" + "pozicijaId=" + pozicijaId + ", naziv=" + naziv + ", datumOd=" + datumOd + ", datumDo=" + datumDo + '}';
    }
    
    
    
}
