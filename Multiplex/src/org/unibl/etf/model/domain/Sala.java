/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

import java.io.Serializable;

/**
 *
 * @author Aleksandar
 */
public class Sala implements Serializable{
   private Integer salaId;
   private Integer brojRedova;
   private Integer brojKolona;
   
   public Sala() {
       
   }
   
   public Sala(Integer salaId, Integer brojRedova, Integer brojKolona){
       this.salaId = salaId;
       this.brojRedova = brojRedova;
       this.brojKolona = brojKolona;
   }

    public Integer getSalaId() {
        return salaId;
    }

    public Integer getBrojRedova() {
        return brojRedova;
    }

    public Integer getBrojKolona() {
        return brojKolona;
    }

    public void setSalaId(Integer salaId) {
        this.salaId = salaId;
    }

    public void setBrojRedova(Integer brojRedova) {
        this.brojRedova = brojRedova;
    }

    public void setBrojKolona(Integer brojKolona) {
        this.brojKolona = brojKolona;
    }

    @Override
    public String toString() {
        return "Sala{" + "salaId=" + salaId + ", brojRedova=" + brojRedova + ", brojKolona=" + brojKolona + '}';
    }

    
   
    
}
