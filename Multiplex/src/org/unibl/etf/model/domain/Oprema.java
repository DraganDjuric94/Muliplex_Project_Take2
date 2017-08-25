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
public class Oprema implements Serializable{

    private Integer opremaId;
    private Integer brojInventara;
    private String naziv;
    private Boolean ispravnost;
    
    public Oprema(){
        
    }
    
    public Oprema(Integer opremaId, Integer brojInventara, String naziv, Boolean ispravnost) {
        this.opremaId = opremaId;
        this.brojInventara = brojInventara;
        this.naziv = naziv;
        this.ispravnost = ispravnost;
    }

    public Integer getOpremaId() {
        return opremaId;
    }

    public Integer getBrojInventara() {
        return brojInventara;
    }

    public String getNaziv() {
        return naziv;
    }

    public Boolean getIspravnost() {
        return ispravnost;
    }

    public void setOpremaId(Integer opremaId) {
        this.opremaId = opremaId;
    }

    public void setBrojInventara(Integer brojInventara) {
        this.brojInventara = brojInventara;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setIspravnost(Boolean ispravnost) {
        this.ispravnost = ispravnost;
    }

    @Override
    public String toString() {
        return "Oprema{" + "opremaId=" + opremaId + ", brojInventara=" + brojInventara + ", naziv=" + naziv + ", ispravnost=" + ispravnost + '}';
    }
    
    

}
