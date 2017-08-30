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
public class Film {
   private Integer filmId;
   private String naziv;
   private Integer trajanje;
   private String opis;
   private String slika;
   
   public Film(){
       
   }

    public Film(Integer filmId, String naziv, Integer trajanje, String opis, String slika) {
        this.filmId = filmId;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.opis = opis;
        this.slika = slika;
    }

    public Integer getFilmId() {
        return filmId;
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

    public String getSlika() {
        return slika;
    }
    

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
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

    public void setSlika(String slika) {
        this.slika = slika;
    }

    @Override
    public String toString() {
        return "Film{" + "filmId=" + filmId + ", naziv=" + naziv + ", trajanje=" + trajanje + ", opis=" + opis + '}';
    }
   
   
}
