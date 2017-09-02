/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain.oo;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Aleksandar
 */
public class ProjekcijaOO {

    private Integer projekcijaId;
    private Date datumVrijeme;
    private FilmOO film;
    private SalaOO sala;
    private Double cijenaKarte;
    private List<SjedisteOO> zauzetaSjedista;

    public ProjekcijaOO() {
    }

    public ProjekcijaOO(Integer projekcijaId, Date datumVrijeme, FilmOO film, SalaOO sala, Double cijenaKarte, List<SjedisteOO> zauzetaSjedista) {
        this.projekcijaId = projekcijaId;
        this.datumVrijeme = datumVrijeme;
        this.film = film;
        this.sala = sala;
        this.cijenaKarte = cijenaKarte;
        this.zauzetaSjedista = zauzetaSjedista;
    }

    public Integer getProjekcijaId() {
        return projekcijaId;
    }

    public Date getDatumVrijeme() {
        return datumVrijeme;
    }

    public FilmOO getFilm() {
        return film;
    }

    public SalaOO getSala() {
        return sala;
    }

    public Double getCijenaKarte() {
        return cijenaKarte;
    }

    public List<SjedisteOO> getZauzetaSjedista() {
        return zauzetaSjedista;
    }

    public void setProjekcijaId(Integer projekcijaId) {
        this.projekcijaId = projekcijaId;
    }

    public void setDatumVrijeme(Date datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    public void setFilm(FilmOO film) {
        this.film = film;
    }

    public void setSala(SalaOO sala) {
        this.sala = sala;
    }

    public void setCijenaKarte(Double cijenaKarte) {
        this.cijenaKarte = cijenaKarte;
    }

    public void setZauzetaSjedista(List<SjedisteOO> zauzetaSjedista) {
        this.zauzetaSjedista = zauzetaSjedista;
    }
    
    public Integer getSalaId(){
        return sala.getSalaId();
    }
    
    public String getNazivFilma(){
        return film.getNaziv();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.projekcijaId);
        hash = 29 * hash + Objects.hashCode(this.datumVrijeme);
        hash = 29 * hash + Objects.hashCode(this.film);
        hash = 29 * hash + Objects.hashCode(this.sala);
        hash = 29 * hash + Objects.hashCode(this.cijenaKarte);
        hash = 29 * hash + Objects.hashCode(this.zauzetaSjedista);
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
        final ProjekcijaOO other = (ProjekcijaOO) obj;
        if (!Objects.equals(this.projekcijaId, other.projekcijaId)) {
            return false;
        }
        if (!Objects.equals(this.datumVrijeme, other.datumVrijeme)) {
            return false;
        }
        if (!Objects.equals(this.film, other.film)) {
            return false;
        }
        if (!Objects.equals(this.sala, other.sala)) {
            return false;
        }
        if (!Objects.equals(this.cijenaKarte, other.cijenaKarte)) {
            return false;
        }
        if (!Objects.equals(this.zauzetaSjedista, other.zauzetaSjedista)) {
            return false;
        }
        return true;
    }

    
    

    @Override
    public String toString() {
        return "ProjekcijaOO{" + "projekcijaId=" + projekcijaId + ", datumVrijeme=" + datumVrijeme + ", film=" + film + ", sala=" + sala + ", cijenaKarte=" + cijenaKarte + ", zauzetaSjedista=" + zauzetaSjedista + '}';
    }

    

}
