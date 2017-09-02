/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain.oo;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Aleksandar
 */
public class PozicijaOO {
    private Integer pozicijaId;
    private String naziv;
    private Date datumOd;
    private Date datumDo;

    public PozicijaOO() {
    }

    public PozicijaOO(Integer pozicijaId, String naziv, Date datumOd, Date datumDo) {
        this.pozicijaId = pozicijaId;
        this.naziv = naziv;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
    }

    public Integer getPozicijaId() {
        return pozicijaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setPozicijaId(Integer pozicijaId) {
        this.pozicijaId = pozicijaId;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.pozicijaId);
        hash = 67 * hash + Objects.hashCode(this.naziv);
        hash = 67 * hash + Objects.hashCode(this.datumOd);
        hash = 67 * hash + Objects.hashCode(this.datumDo);
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
        final PozicijaOO other = (PozicijaOO) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.pozicijaId, other.pozicijaId)) {
            return false;
        }
        if (!Objects.equals(this.datumOd, other.datumOd)) {
            return false;
        }
        if (!Objects.equals(this.datumDo, other.datumDo)) {
            return false;
        }
        return true;
    }

    

    

    @Override
    public String toString() {
        return "PozicijaOO{" + "pozicijaId=" + pozicijaId + ", naziv=" + naziv + ", datumOd=" + datumOd + ", datumDo=" + datumDo + '}';
    }
    
    
}
