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
public class Artikal {

    private Integer artikalId;
    private String naziv;
    private Integer kolicinaNaStanju;
    private String barkod;
    private String tip;
    private Double cijena;

    public Artikal() {

    }

    public Artikal(Integer artikalId, String naziv, Integer kolicinaNaStanju, String barkod, String tip, Double cijena) {
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

    public String getTip() {
        return tip;
    }

    public Double getCijena() {
        return cijena;
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

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    @Override
    public String toString() {
        return "Artikal{" + "artikalId=" + artikalId + ", naziv=" + naziv + ", kolicinaNaStanju=" + kolicinaNaStanju + ", barkod=" + barkod + ", tip=" + tip + ", cijena=" + cijena + '}';
    }

}
