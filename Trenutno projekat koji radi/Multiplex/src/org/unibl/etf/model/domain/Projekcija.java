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
    private Double cijenaKarte;

    public Projekcija() {
    }

    public Projekcija(Integer projekcijaId, Integer filmId, Timestamp datumVrijeme, Double cijenaKarte) {
        this.projekcijaId = projekcijaId;
        this.filmId = filmId;
        this.datumVrijeme = datumVrijeme;
        this.cijenaKarte = cijenaKarte;
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

    public Double getCijenaKarte() {
        return cijenaKarte;
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

    public void setCijenaKarte(Double cijenaKarte) {
        this.cijenaKarte = cijenaKarte;
    }

    @Override
    public String toString() {
        return "Projekcija{" + "projekcijaId=" + projekcijaId + ", filmId=" + filmId + ", datumVrijeme=" + datumVrijeme + ", cijenaKarte=" + cijenaKarte + '}';
    }
    
    
    
    
    
    
}
