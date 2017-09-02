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
public class TransakcijaOO {
    private Integer transakcijaId;
    private String vrstaTransakcije;
    private String primalac;
    private String posaljilac;
    private Date datumTransakcije;
    private Double iznos;

    public TransakcijaOO() {
    }

    public TransakcijaOO(Integer transakcijaId, String vrstaTransakcije, String primalac, String posaljilac, Date datumTransakcije, Double iznos) {
        this.transakcijaId = transakcijaId;
        this.vrstaTransakcije = vrstaTransakcije;
        this.primalac = primalac;
        this.posaljilac = posaljilac;
        this.datumTransakcije = datumTransakcije;
        this.iznos = iznos;
    }

    public Integer getTransakcijaId() {
        return transakcijaId;
    }

    public String getVrstaTransakcije() {
        return vrstaTransakcije;
    }

    public String getPrimalac() {
        return primalac;
    }

    public String getPosaljilac() {
        return posaljilac;
    }

    public Date getDatumTransakcije() {
        return datumTransakcije;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setTransakcijaId(Integer transakcijaId) {
        this.transakcijaId = transakcijaId;
    }

    public void setVrstaTransakcije(String vrstaTransakcije) {
        this.vrstaTransakcije = vrstaTransakcije;
    }

    public void setPrimalac(String primalac) {
        this.primalac = primalac;
    }

    public void setPosaljilac(String posaljilac) {
        this.posaljilac = posaljilac;
    }

    public void setDatumTransakcije(Date datumTransakcije) {
        this.datumTransakcije = datumTransakcije;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.transakcijaId);
        hash = 67 * hash + Objects.hashCode(this.vrstaTransakcije);
        hash = 67 * hash + Objects.hashCode(this.primalac);
        hash = 67 * hash + Objects.hashCode(this.posaljilac);
        hash = 67 * hash + Objects.hashCode(this.datumTransakcije);
        hash = 67 * hash + Objects.hashCode(this.iznos);
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
        final TransakcijaOO other = (TransakcijaOO) obj;
        if (!Objects.equals(this.vrstaTransakcije, other.vrstaTransakcije)) {
            return false;
        }
        if (!Objects.equals(this.primalac, other.primalac)) {
            return false;
        }
        if (!Objects.equals(this.posaljilac, other.posaljilac)) {
            return false;
        }
        if (!Objects.equals(this.transakcijaId, other.transakcijaId)) {
            return false;
        }
        if (!Objects.equals(this.datumTransakcije, other.datumTransakcije)) {
            return false;
        }
        if (!Objects.equals(this.iznos, other.iznos)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "TransakcijaOO{" + "transakcijaId=" + transakcijaId + ", vrstaTransakcije=" + vrstaTransakcije + ", primalac=" + primalac + ", posaljilac=" + posaljilac + ", datumTransakcije=" + datumTransakcije + ", iznos=" + iznos + '}';
    }
    
    
    
    
    
}
