/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain.oo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Aleksandar
 */
public class FilmOO {

    private Integer filmId;
    private String naziv;
    private Integer trajanje;
    private String opis;
    private String slika;
    private List<ZanrOO> zanrovi;

    public FilmOO() {
    }

    public FilmOO(Integer filmId, String naziv, Integer trajanje, String opis, String slika, List<ZanrOO> zanrovi) {
        this.filmId = filmId;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.opis = opis;
        this.slika = slika;
        this.zanrovi = zanrovi;
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

    public List<ZanrOO> getZanrovi() {
        return zanrovi;
    }

    public String getZanroviStr(){
        if(zanrovi.size() == 0) return "Nema";
        String ret = "";
        for(ZanrOO z : zanrovi){
            ret += (z.getNaziv() + ", ");
        }
        return ret.substring(0, ret.length() - 2);
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

    public void setZanrovi(List<ZanrOO> zanrovi) {
        this.zanrovi = zanrovi;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.filmId);
        hash = 89 * hash + Objects.hashCode(this.naziv);
        hash = 89 * hash + Objects.hashCode(this.trajanje);
        hash = 89 * hash + Objects.hashCode(this.opis);
        hash = 89 * hash + Objects.hashCode(this.slika);
        hash = 89 * hash + Objects.hashCode(this.zanrovi);
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
        final FilmOO other = (FilmOO) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.opis, other.opis)) {
            return false;
        }
        if (!Objects.equals(this.slika, other.slika)) {
            return false;
        }
        if (!Objects.equals(this.filmId, other.filmId)) {
            return false;
        }
        if (!Objects.equals(this.trajanje, other.trajanje)) {
            return false;
        }
        if (!Objects.equals(this.zanrovi, other.zanrovi)) {
            return false;
        }
        return true;
    }

   

    

    @Override
    public String toString() {
        return '{'
                + "filmId=" + filmId 
                + ", naziv=" + naziv 
                + ", trajanje=" + trajanje 
                + ", opis=" + opis 
                + ", slika=" + slika 
                + ", zanrovi={\n" + zanrovi.stream().map(zanr -> zanr.toString()).collect(Collectors.joining("\n"))
                + '}';
    }

   

}
