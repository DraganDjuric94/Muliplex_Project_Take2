/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

import java.sql.Blob;

/**
 *
 * @author User
 */
public class Film {
   private Integer filmId;
   private Integer ponudaZaFilmId;
   private String naziv;
   private Integer trajanje;
   private String opis;
   private Blob slika;
   
   public Film(){
       
   }

    public Film(Integer filmId, Integer ponudaZaFilmId, String naziv, Integer trajanje, String opis, Blob slika) {
        this.filmId = filmId;
        this.ponudaZaFilmId = ponudaZaFilmId;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.opis = opis;
        this.slika = slika;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public Integer getPonudaZaFilmId() {
        return ponudaZaFilmId;
    }

    public String getNaziv() {
        return naziv;
    }

    public Integer getTrajanje() {
        return trajanje;
    }

    public String getOpis() {
        return opis;
    }

    public Blob getSlika() {
        return slika;
    }
    

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public void setPonudaZaFilmId(Integer ponudaZaFilmId) {
        this.ponudaZaFilmId = ponudaZaFilmId;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setTrajanje(Integer trajanje) {
        this.trajanje = trajanje;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setSlika(Blob slika) {
        this.slika = slika;
    }

    @Override
    public String toString() {
        return "Film{" + "filmId=" + filmId + ", ponudaZaFilmId=" + ponudaZaFilmId + ", naziv=" + naziv + ", trajanje=" + trajanje + ", opis=" + opis + '}';
    }
   
   
}
