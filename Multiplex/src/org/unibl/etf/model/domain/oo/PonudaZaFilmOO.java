/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain.oo;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Aleksandar
 */
public class PonudaZaFilmOO {

    private Integer ponudaZaFilmId;
    private Integer filmId;
    private String opis;
    private Date datum;

    public PonudaZaFilmOO() {
    }

    public PonudaZaFilmOO(Integer ponudaZaFilmId, Integer filmId, String opis, Date datum) {
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
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.ponudaZaFilmId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PonudaZaFilmOO other = (PonudaZaFilmOO) obj;
        if (!Objects.equals(this.ponudaZaFilmId, other.ponudaZaFilmId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return '{'
                + "ponudaZaFilmId=" + ponudaZaFilmId
                + ", filmId=" + filmId
                + ", opis=" + opis
                + ", datum=" + datum
                + '}';
    }

}
