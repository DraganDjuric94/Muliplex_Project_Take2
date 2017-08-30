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
public class SjedisteOO {

    private Integer sjedisteId;
    private Integer vrsta;
    private Integer kolona;

    public SjedisteOO() {
    }

    public SjedisteOO(Integer sjedisteId, Integer vrsta, Integer kolona) {
        this.sjedisteId = sjedisteId;
        this.vrsta = vrsta;
        this.kolona = kolona;
    }

    public Integer getSjedisteId() {
        return sjedisteId;
    }

    public Integer getVrsta() {
        return vrsta;
    }

    public Integer getKolona() {
        return kolona;
    }

    public void setSjedisteId(Integer sjedisteId) {
        this.sjedisteId = sjedisteId;
    }

    public void setVrsta(Integer vrsta) {
        this.vrsta = vrsta;
    }

    public void setKolona(Integer kolona) {
        this.kolona = kolona;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.sjedisteId);
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
        final SjedisteOO other = (SjedisteOO) obj;
        if (!Objects.equals(this.sjedisteId, other.sjedisteId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return 
                '{' 
                + "sjedisteId=" + sjedisteId 
                + ", vrsta=" + vrsta 
                + ", kolona=" + kolona 
                + '}';
    }

}
