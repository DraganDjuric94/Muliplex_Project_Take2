/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain.oo;

import java.util.Objects;

/**
 *
 * @author Aleksandar
 */
public class ArtikalOO {

    private Integer artikalId;
    private String naziv;
    private Integer kolicinaNaStanju;
    private String barkod;
    private String tip;
    private Double cijena;

    public ArtikalOO() {

    }

    public ArtikalOO(Integer artikalId, String naziv, Integer kolicinaNaStanju, String barkod, String tip, Double cijena) {
        this.artikalId = artikalId;
        this.naziv = naziv;
        this.kolicinaNaStanju = kolicinaNaStanju;
        this.barkod = barkod;
        this.tip = tip;
        this.cijena = cijena;
    }

    public Integer getArtikalId() {
        return artikalId;
    }

    public String getNaziv() {
        return naziv;
    }

    public Integer getKolicinaNaStanju() {
        return kolicinaNaStanju;
    }

    public String getBarkod() {
        return barkod;
    }

    public Double getCijena() {
        return cijena;
    }

    public String getTip() {
        return tip;
    }

    public void setArtikalId(Integer artikalId) {
        this.artikalId = artikalId;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setKolicinaNaStanju(Integer kolicinaNaStanju) {
        this.kolicinaNaStanju = kolicinaNaStanju;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.artikalId);
        hash = 43 * hash + Objects.hashCode(this.naziv);
        hash = 43 * hash + Objects.hashCode(this.kolicinaNaStanju);
        hash = 43 * hash + Objects.hashCode(this.barkod);
        hash = 43 * hash + Objects.hashCode(this.tip);
        hash = 43 * hash + Objects.hashCode(this.cijena);
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
        final ArtikalOO other = (ArtikalOO) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.barkod, other.barkod)) {
            return false;
        }
        if (!Objects.equals(this.tip, other.tip)) {
            return false;
        }
        if (!Objects.equals(this.artikalId, other.artikalId)) {
            return false;
        }
        if (!Objects.equals(this.kolicinaNaStanju, other.kolicinaNaStanju)) {
            return false;
        }
        if (!Objects.equals(this.cijena, other.cijena)) {
            return false;
        }
        return true;
    }

    

    

    @Override
    public String toString() {
        return '{'
                + "artikalId=" + artikalId
                + ", naziv=" + naziv
                + ", kolicinaNaStanju=" + kolicinaNaStanju
                + ", barkod=" + barkod
                + ", tip=" + tip
                + ", cijena=" + cijena
                + '}';
    }

}
