/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author User
 */
public class Transakcija implements Serializable{
    private Integer transakcijaId;
    private String vrstaTransakcije;
    private String primalac;
    private String posaljilac;
    private Date datumTransakcije;
    private Double iznos;

    public Transakcija(){
        
    }
    
    public Transakcija(Integer transakcijaId, String vrstaTransakcije, String primalac, String posaljilac, Date datumTransakcije, Double iznos) {
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
    public String toString() {
        return "Transakcija{" + "transakcijaId=" + transakcijaId + ", vrstaTransakcije=" + vrstaTransakcije + ", primalac=" + primalac + ", posaljilac=" + posaljilac + ", datumTransakcije=" + datumTransakcije + ", iznos=" + iznos + '}';
    }
    
    
    
    
}
