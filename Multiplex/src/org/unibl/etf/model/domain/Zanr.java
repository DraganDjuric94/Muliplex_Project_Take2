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
public class Zanr implements Serializable{
    private Integer zanrId;
    private String naziv;
    
    public Zanr() {
        
    }

    public Zanr(Integer zanrId, String naziv) {
        this.zanrId = zanrId;
        this.naziv = naziv;
    }

    public Integer getZanrId() {
        return zanrId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setZanrId(Integer zanrId) {
        this.zanrId = zanrId;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "Zanr{" + "zanrId=" + zanrId + ", naziv=" + naziv + '}';
    }
    
    
}
