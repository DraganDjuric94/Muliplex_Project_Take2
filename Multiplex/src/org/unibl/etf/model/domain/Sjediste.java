/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

/**
 *
 * @author User
 */
public class Sjediste {
    private Integer sjedisteId;
    private Integer salaId;
    private Integer kolona;
    private Integer vrsta;
    
    public Sjediste() {
        
    }

    public Sjediste(Integer sjedisteId, Integer salaId, Integer kolona, Integer vrsta) {
        this.sjedisteId = sjedisteId;
        this.salaId = salaId;
        this.kolona = kolona;
        this.vrsta = vrsta;
    }

    public Integer getSjedisteId() {
        return sjedisteId;
    }

    public Integer getSalaId() {
        return salaId;
    }

    public Integer getKolona() {
        return kolona;
    }

    public Integer getVrsta() {
        return vrsta;
    }

    public void setSjedisteId(Integer sjedisteId) {
        this.sjedisteId = sjedisteId;
    }

    public void setSalaId(Integer salaId) {
        this.salaId = salaId;
    }

    public void setKolona(Integer kolona) {
        this.kolona = kolona;
    }

    public void setVrsta(Integer vrsta) {
        this.vrsta = vrsta;
    }

    @Override
    public String toString() {
        return "Sjediste{" + "sjedisteId=" + sjedisteId + ", salaId=" + salaId + ", kolona=" + kolona + ", vrsta=" + vrsta + '}';
    }
    
    
}
