/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain.oo;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Aleksandar
 */
public class RacunOO {
   private Integer racunId;
   private Date datumIzdavanja;
   private Double ukupnaCijena;
   private List<StavkaOO> stavke;

    public RacunOO() {
    }

    public RacunOO(Integer racunId, Date datumIzdavanja, Double ukupnaCijena, List<StavkaOO> stavke) {
        this.racunId = racunId;
        this.datumIzdavanja = datumIzdavanja;
        this.ukupnaCijena = ukupnaCijena;
        this.stavke = stavke;
    }

    public Integer getRacunId() {
        return racunId;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public Double getUkupnaCijena() {
        return ukupnaCijena;
    }

    public List<StavkaOO> getStavke() {
        return stavke;
    }

    public void setRacunId(Integer racunId) {
        this.racunId = racunId;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public void setUkupnaCijena(Double ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    public void setStavke(List<StavkaOO> stavke) {
        this.stavke = stavke;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.racunId);
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
        final RacunOO other = (RacunOO) obj;
        if (!Objects.equals(this.racunId, other.racunId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return 
                '{' + "racunId=" + racunId 
                + ", datumIzdavanja=" + datumIzdavanja 
                + ", ukupnaCijena=" + ukupnaCijena
                + ", stavke=\n" 
                + stavke.stream().map(stavka -> stavka.toString()).collect(Collectors.joining("\n")) 
                + "\n}";
    }

    
    
   
   
   
}
