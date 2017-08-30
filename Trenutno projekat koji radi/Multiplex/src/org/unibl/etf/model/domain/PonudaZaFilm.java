/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

import java.sql.Date;


/**
 *
 * @author Aleksandar
 */
public class PonudaZaFilm {

    private Integer ponudaZaFilmId;
    private Integer filmId;
    private String opis;
    private Date datum;

    public PonudaZaFilm() {
    }

    public PonudaZaFilm(Integer ponudaZaFilmId, Integer filmId, String opis, Date datum) {
        this.ponudaZaFilmId = ponudaZaFilmId;
        this.filmId = filmId;
        this.opis = opis;
        this.datum = datum;
    }

    public Integer getPonudaZaFilmId() {
        return ponudaZaFilmId;
    }

    public Integer getFilmId() {
        return filmId;
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

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "PonudaZaFilm{" + "ponudaZaFilmId=" + ponudaZaFilmId + ", filmId=" + filmId + ", opis=" + opis + ", datum=" + datum + '}';
    }

}
