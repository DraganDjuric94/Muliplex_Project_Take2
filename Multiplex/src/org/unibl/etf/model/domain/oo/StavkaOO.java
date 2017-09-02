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
public class StavkaOO {
   private Integer stavkaId;
   private Integer kolicina;
   private Double ukupnaCijena;
   private ArtikalOO artikal;
   
   public StavkaOO(){
       
   }

    public StavkaOO(Integer stavkaId, Integer kolicina, Double ukupnaCijena, ArtikalOO artikal) {
        this.stavkaId = stavkaId;
        this.kolicina = kolicina;
        this.ukupnaCijena = ukupnaCijena;
        this.artikal = artikal;
    }

    public Integer getStavkaId() {
        return stavkaId;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public Double getUkupnaCijena() {
        return ukupnaCijena;
    }

    public ArtikalOO getArtikal() {
        return artikal;
    }

    public void setStavkaId(Integer stavkaId) {
        this.stavkaId = stavkaId;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public void setUkupnaCijena(Double ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    public void setArtikal(ArtikalOO artikal) {
        this.artikal = artikal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.stavkaId);
        hash = 89 * hash + Objects.hashCode(this.kolicina);
        hash = 89 * hash + Objects.hashCode(this.ukupnaCijena);
        hash = 89 * hash + Objects.hashCode(this.artikal);
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
        final StavkaOO other = (StavkaOO) obj;
        if (!Objects.equals(this.stavkaId, other.stavkaId)) {
            return false;
        }
        if (!Objects.equals(this.kolicina, other.kolicina)) {
            return false;
        }
        if (!Objects.equals(this.ukupnaCijena, other.ukupnaCijena)) {
            return false;
        }
        if (!Objects.equals(this.artikal, other.artikal)) {
            return false;
        }
        return true;
    }

    

    

    @Override
    public String toString() {
        return 
                '{' 
                + "stavkaId=" + stavkaId 
                + ", kolicina=" + kolicina 
                + ", ukupnaCijena=" + ukupnaCijena 
                + ", artikal= " + artikal 
                + '}';
    }
   
   
   
}
