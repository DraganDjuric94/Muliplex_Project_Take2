/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

import java.sql.Date;


/**
 *
 * @author User
 */
public class Karta {
    public Integer kartaId;
    public Integer projekcijaId;
    public Integer sjedisteId;
    public Date datumIzdavanja;
    public Double cijena;
    public Boolean rezervisana;
    
    public Karta() {
        
    }

    public Karta(Integer kartaId, Integer projekcijaId, Integer sjedisteId, Date datumIzdavanja, Double cijena, Boolean rezervisana) {
        this.kartaId = kartaId;
        this.projekcijaId = projekcijaId;
        this.sjedisteId = sjedisteId;
        this.datumIzdavanja = datumIzdavanja;
        this.cijena = cijena;
        this.rezervisana = rezervisana;
    }

    public Integer getKartaId() {
        return kartaId;
    }

    public Integer getProjekcijaId() {
        return projekcijaId;
    }

    public Integer getSjedisteId() {
        return sjedisteId;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public Double getCijena() {
        return cijena;
    }

    public Boolean getRezervisana() {
        return rezervisana;
    }

    public void setKartaId(Integer kartaId) {
        this.kartaId = kartaId;
    }

    public void setProjekcijaId(Integer projekcijaId) {
        this.projekcijaId = projekcijaId;
    }

    public void setSjedisteId(Integer sjedisteId) {
        this.sjedisteId = sjedisteId;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public void setRezervisana(Boolean rezervisana) {
        this.rezervisana = rezervisana;
    }

    @Override
    public String toString() {
        return "Karta{" + "kartaId=" + kartaId + ", projekcijaId=" + projekcijaId + ", sjedisteId=" + sjedisteId + ", datumIzdavanja=" + datumIzdavanja + ", cijena=" + cijena + ", rezervisana=" + rezervisana + '}';
    }
    
    
}
