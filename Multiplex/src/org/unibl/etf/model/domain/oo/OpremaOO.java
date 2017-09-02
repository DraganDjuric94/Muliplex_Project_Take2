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
public class OpremaOO {

    private Integer opremaId;
    private String brojInventara;
    private String naziv;
    private Boolean ispravnost;

    public OpremaOO() {

    }

    public OpremaOO(Integer opremaId, String brojInventara, String naziv, Boolean ispravnost) {
        this.opremaId = opremaId;
        this.brojInventara = brojInventara;
        this.naziv = naziv;
        this.ispravnost = ispravnost;
    }

    public Integer getOpremaId() {
        return opremaId;
    }

    public String getBrojInventara() {
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

    public void setBrojInventara(String brojInventara) {
        this.brojInventara = brojInventara;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setIspravnost(Boolean ispravnost) {
        this.ispravnost = ispravnost;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.opremaId);
        hash = 29 * hash + Objects.hashCode(this.brojInventara);
        hash = 29 * hash + Objects.hashCode(this.naziv);
        hash = 29 * hash + Objects.hashCode(this.ispravnost);
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
        final OpremaOO other = (OpremaOO) obj;
        if (!Objects.equals(this.brojInventara, other.brojInventara)) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.opremaId, other.opremaId)) {
            return false;
        }
        if (!Objects.equals(this.ispravnost, other.ispravnost)) {
            return false;
        }
        return true;
    }

    

    

    @Override
    public String toString() {
        return 
                '{' 
                + "opremaId=" + opremaId 
                + ", brojInventara=" + brojInventara 
                + ", naziv=" + naziv 
                + ", ispravnost=" + ispravnost 
                + '}';
    }

}
