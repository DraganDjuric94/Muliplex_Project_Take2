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
 * @author User
 */
public class PonudaZaFilm implements Serializable{
    private Integer ponudaZaFilmId;
    private String opis;
    private Date datum;
    
    public PonudaZaFilm() {
        
    }

    public PonudaZaFilm(Integer ponudaZaFilmId, String opis, Date datum) {
        this.ponudaZaFilmId = ponudaZaFilmId;
        this.opis = opis;
        this.datum = datum;
    }

    public Integer getPonudaZaFilmId() {
        return ponudaZaFilmId;
    }

    public String getOpis() {
        return opis;
    }

    public Date getDatum() {
        return datum;
    }

    public void setPonudaZaFilmId(Integer ponudaZaFilmId) {
        this.ponudaZaFilmId = ponudaZaFilmId;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "PonudaZaFilm{" + "ponudaZaFilmId=" + ponudaZaFilmId + ", opis=" + opis + ", datum=" + datum + '}';
    }
    
    
}
