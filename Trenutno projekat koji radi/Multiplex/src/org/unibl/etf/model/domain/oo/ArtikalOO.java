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
        hash = 79 * hash + Objects.hashCode(this.artikalId);
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
        if (!Objects.equals(this.artikalId, other.artikalId)) {
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
