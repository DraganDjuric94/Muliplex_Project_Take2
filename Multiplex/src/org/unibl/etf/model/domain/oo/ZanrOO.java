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
public class ZanrOO {

    private Integer zanrId;
    private String naziv;

    public ZanrOO() {
    }

    public ZanrOO(Integer zanrId, String naziv) {
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
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.zanrId);
        hash = 59 * hash + Objects.hashCode(this.naziv);
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
        final ZanrOO other = (ZanrOO) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.zanrId, other.zanrId)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return naziv;
    }

}
