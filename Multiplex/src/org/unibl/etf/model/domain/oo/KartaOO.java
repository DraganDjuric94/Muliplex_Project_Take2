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
public class KartaOO {

    private Integer kartaId;
    private ProjekcijaOO projekcija;
    private SjedisteOO sjediste;
    private Date datumIzdavanja;
    private Double cijena;
    private Boolean rezervisana;

    public KartaOO() {
    }

    public KartaOO(Integer kartaId, ProjekcijaOO projekcija, SjedisteOO sjediste, Date datumIzdavanja, Double cijena, Boolean rezervisana) {
        this.kartaId = kartaId;
        this.projekcija = projekcija;
        this.sjediste = sjediste;
        this.datumIzdavanja = datumIzdavanja;
        this.cijena = cijena;
        this.rezervisana = rezervisana;
    }

    public Integer getKartaId() {
        return kartaId;
    }

    public ProjekcijaOO getProjekcija() {
        return projekcija;
    }

    public SjedisteOO getSjediste() {
        return sjediste;
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

    public void setProjekcija(ProjekcijaOO projekcija) {
        this.projekcija = projekcija;
    }

    public void setSjediste(SjedisteOO sjediste) {
        this.sjediste = sjediste;
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
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.kartaId);
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
        final KartaOO other = (KartaOO) obj;
        if (!Objects.equals(this.kartaId, other.kartaId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KartaOO{" + "kartaId=" + kartaId + ", projekcija=" + projekcija + ", sjediste=" + sjediste + ", datumIzdavanja=" + datumIzdavanja + ", cijena=" + cijena + ", rezervisana=" + rezervisana + '}';
    }

    

}
