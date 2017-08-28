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
public class Projekcija {
    private Integer projekcijaId;
    private Integer filmId;
    private Timestamp datumVrijeme;

    public Projekcija() {
    }

    public Projekcija(Integer projekcijaId, Integer filmId, Timestamp datumVrijeme) {
        this.projekcijaId = projekcijaId;
        this.filmId = filmId;
        this.datumVrijeme = datumVrijeme;
    }

    public Integer getProjekcijaId() {
        return projekcijaId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public Timestamp getDatumVrijeme() {
        return datumVrijeme;
    }

    public void setProjekcijaId(Integer projekcijaId) {
        this.projekcijaId = projekcijaId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public void setDatumVrijeme(Timestamp datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    @Override
    public String toString() {
        return "Projekcija{" + "projekcijaId=" + projekcijaId + ", filmId=" + filmId + ", datumVrijeme=" + datumVrijeme + '}';
    }
    
    
    
    
}
