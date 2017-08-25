/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

import java.sql.Date;
import java.sql.Time;


/**
 *
 * @author User
 */
public class Projekcija {
    private Integer projekcijaId;
    private Integer filmId;
    private Date datum;
    private Time vrijeme;
    
    public Projekcija(){
        
    }

    public Projekcija(Integer projekcijaId, Integer filmId, Date datum, Time vrijeme) {
        this.projekcijaId = projekcijaId;
        this.filmId = filmId;
        this.datum = datum;
        this.vrijeme = vrijeme;
    }

    public Integer getProjekcijaId() {
        return projekcijaId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public Date getDatum() {
        return datum;
    }

    public Time getVrijeme() {
        return vrijeme;
    }

    public void setProjekcijaId(Integer projekcijaId) {
        this.projekcijaId = projekcijaId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setVrijeme(Time vrijeme) {
        this.vrijeme = vrijeme;
    }

    @Override
    public String toString() {
        return "Projekcija{" + "projekcijaId=" + projekcijaId + ", filmId=" + filmId + ", datum=" + datum + ", vrijeme=" + vrijeme + '}';
    }
    
    
}
