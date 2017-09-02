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
    private Integer kolona;
    private Integer vrsta;

    public SjedisteOO() {
    }

    public SjedisteOO(Integer sjedisteId, Integer kolona, Integer vrsta) {
        this.sjedisteId = sjedisteId;
        this.kolona = kolona;
        this.vrsta = vrsta;
    }

    public Integer getSjedisteId() {
        return sjedisteId;
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

    public void setKolona(Integer kolona) {
        this.kolona = kolona;
    }

    public void setVrsta(Integer vrsta) {
        this.vrsta = vrsta;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.sjedisteId);
        hash = 37 * hash + Objects.hashCode(this.kolona);
        hash = 37 * hash + Objects.hashCode(this.vrsta);
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
        if (!Objects.equals(this.kolona, other.kolona)) {
            return false;
        }
        if (!Objects.equals(this.vrsta, other.vrsta)) {
            return false;
        }
        return true;
    }

    

    
    
    
    @Override
    public String toString() {
        return "SjedisteOO{" + "sjedisteId=" + sjedisteId + ", kolona=" + kolona + ", vrsta=" + vrsta + '}';
    }

    

}
