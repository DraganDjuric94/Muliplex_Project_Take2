/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain.oo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Aleksandar
 */
public class SalaOO {
   private Integer salaId;
   private Integer brojRedova;
   private Integer brojKolona;
   private List<SjedisteOO> sjedista;

    public SalaOO() {
    }

    public SalaOO(Integer salaId, Integer brojRedova, Integer brojKolona, List<SjedisteOO> sjedista) {
        this.salaId = salaId;
        this.brojRedova = brojRedova;
        this.brojKolona = brojKolona;
        this.sjedista = sjedista;
    }

    public Integer getSalaId() {
        return salaId;
    }

    public Integer getBrojRedova() {
        return brojRedova;
    }

    public Integer getBrojKolona() {
        return brojKolona;
    }

    public List<SjedisteOO> getSjedista() {
        return sjedista;
    }

    public void setSalaId(Integer salaId) {
        this.salaId = salaId;
    }

    public void setBrojRedova(Integer brojRedova) {
        this.brojRedova = brojRedova;
    }

    public void setBrojKolona(Integer brojKolona) {
        this.brojKolona = brojKolona;
    }

    public void setSjedista(List<SjedisteOO> sjedista) {
        this.sjedista = sjedista;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.salaId);
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
        final SalaOO other = (SalaOO) obj;
        if (!Objects.equals(this.salaId, other.salaId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return 
                '{' 
                + "salaId=" + salaId
                + ", brojRedova=" + brojRedova
                + ", brojKolona=" + brojKolona
                + ", sjedista=\n" 
                + sjedista.stream().map(sjediste -> sjediste.toString()).collect(Collectors.joining("\n"))
                + "\n}";
    }
   
   
   
}
