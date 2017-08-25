/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author User
 */
public class Racun implements Serializable{
   private Integer racunId;
   private Date datumIzdavanja;
   private Time vrijemeIzdavanja;
   private Double ukupnaCijena;
   
   public Racun(){
       
   }

    public Racun(Integer racunId, Date datumIzdavanja, Double ukupnaCijena) {
        this.racunId = racunId;
        this.datumIzdavanja = datumIzdavanja;
        this.ukupnaCijena = ukupnaCijena;
    }

    public Integer getRacunId() {
        return racunId;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public Time getVrijemeIzdavanja() {
        return vrijemeIzdavanja;
    }
    
    public Double getUkupnaCijena() {
        return ukupnaCijena;
    }

    public void setRacunId(Integer racunId) {
        this.racunId = racunId;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public void setVrijemeIzdavanja(Time vrijemeIzdavanja) {
        this.vrijemeIzdavanja = vrijemeIzdavanja;
    }
    
    public void setUkupnaCijena(Double ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    @Override
    public String toString() {
        return "Racun{" + "racunId=" + racunId + ", datumIzdavanja=" + datumIzdavanja + ", ukupnaCijena=" + ukupnaCijena + '}';
    }
   
   
}
