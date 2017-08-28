/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

/**
 *
 * @author Aleksandar
 */
public class ProjekcijaSala {

    private Integer projekcijaId;
    private Integer salaId;

    public ProjekcijaSala() {
    }

    public ProjekcijaSala(Integer projekcijaId, Integer salaId) {
        this.projekcijaId = projekcijaId;
        this.salaId = salaId;
    }

    public Integer getProjekcijaId() {
        return projekcijaId;
    }

    public Integer getSalaId() {
        return salaId;
    }

    public void setProjekcijaId(Integer projekcijaId) {
        this.projekcijaId = projekcijaId;
    }

    public void setSalaId(Integer salaId) {
        this.salaId = salaId;
    }

    @Override
    public String toString() {
        return "ProjekcijaSala{" + "projekcijaId=" + projekcijaId + ", salaId=" + salaId + '}';
    }

}
