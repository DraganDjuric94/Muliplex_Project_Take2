/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

import java.sql.Timestamp;




/**
 *
 * @author Aleksandar
 */
public class Karta {
    private Integer kartaId;
    private Integer projekcijaId;
    private Integer sjedisteId;
    private Timestamp datumVrijeme;
    private Boolean rezervisana;

    public Karta() {
    }

    public Karta(Integer kartaId, Integer projekcijaId, Integer sjedisteId, Timestamp datumVrijeme, Boolean rezervisana) {
        this.kartaId = kartaId;
        this.projekcijaId = projekcijaId;
        this.sjedisteId = sjedisteId;
        this.datumVrijeme = datumVrijeme;
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

    public Timestamp getDatumVrijeme() {
        return datumVrijeme;
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

    public void setDatumVrijeme(Timestamp datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    public void setRezervisana(Boolean rezervisana) {
        this.rezervisana = rezervisana;
    }

    @Override
    public String toString() {
        return "Karta{" + "kartaId=" + kartaId + ", projekcijaId=" + projekcijaId + ", sjedisteId=" + sjedisteId + ", datumVrijeme=" + datumVrijeme + ", rezervisana=" + rezervisana + '}';
    }

    

    
}
