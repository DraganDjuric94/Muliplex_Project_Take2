/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

import java.util.Objects;

/**
 *
 * @author Aleksandar
 */
public class FilmZanr {

    private Integer filmId;
    private Integer zanrId;

    public FilmZanr() {
    }

    public FilmZanr(Integer filmId, Integer zanrId) {
        this.filmId = filmId;
        this.zanrId = zanrId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public Integer getZanrId() {
        return zanrId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public void setZanrId(Integer zanrId) {
        this.zanrId = zanrId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.filmId);
        hash = 79 * hash + Objects.hashCode(this.zanrId);
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
        final FilmZanr other = (FilmZanr) obj;
        if (!Objects.equals(this.filmId, other.filmId)) {
            return false;
        }
        if (!Objects.equals(this.zanrId, other.zanrId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FilmZanr{" + "filmId=" + filmId + ", zanrId=" + zanrId + '}';
    }

}
