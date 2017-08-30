/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Stavka implements Serializable{
    private Integer stavkaId;
    private Integer artikalId;
    private Integer racunId;
    private Integer kolicina;
    private Double ukupnaCijena;
    
    public Stavka() {
        
    }

    public Stavka(Integer stavkaId, Integer artikalId, Integer racunId, Integer kolicina, Double ukupnaCijena) {
        this.stavkaId = stavkaId;
        this.artikalId = artikalId;
        this.racunId = racunId;
        this.kolicina = kolicina;
        this.ukupnaCijena = ukupnaCijena;
    }

    public Integer getStavkaId() {
        return stavkaId;
    }

    public Integer getArtikalId() {
        return artikalId;
    }

    public Integer getRacunId() {
        return racunId;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public Double getUkupnaCijena() {
        return ukupnaCijena;
    }

    public void setStavkaId(Integer stavkaId) {
        this.stavkaId = stavkaId;
    }

    public void setArtikalId(Integer artikalId) {
        this.artikalId = artikalId;
    }

    public void setRacunId(Integer racunId) {
        this.racunId = racunId;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public void setUkupnaCijena(Double ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    @Override
    public String toString() {
        return "Stavka{" + "stavkaId=" + stavkaId + ", artikalId=" + artikalId + ", racunId=" + racunId + ", kolicina=" + kolicina + ", ukupnaCijena=" + ukupnaCijena + '}';
    }
    
    
}
