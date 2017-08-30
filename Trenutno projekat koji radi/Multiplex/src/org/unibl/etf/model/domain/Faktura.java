/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

import java.sql.Date;

/**
 *
 * @author Aleksandar
 */
public class Faktura {

    private Integer fakturaId;
    private String brojRacuna;
    private String nazivRobe;
    private String jedinicaMjere;
    private Integer kolicina;
    private Double cijena;
    private Date datum;
    private String racunIzdao;

    public Faktura() {

    }

    public Faktura(Integer fakturaId, String brojRacuna, String nazivRobe, String jedinicaMjere, Integer kolicina, Double cijena, Date datum, String racunIzdao) {
        this.fakturaId = fakturaId;
        this.brojRacuna = brojRacuna;
        this.nazivRobe = nazivRobe;
        this.jedinicaMjere = jedinicaMjere;
        this.kolicina = kolicina;
        this.cijena = cijena;
        this.datum = datum;
        this.racunIzdao = racunIzdao;
    }
    
    public Integer getFakturaId() {
        return fakturaId;
    }

    public String getBrojRacuna() {
        return brojRacuna;
    }

    public String getNazivRobe() {
        return nazivRobe;
    }

    public String getJedinicaMjere() {
        return jedinicaMjere;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public Double getCijena() {
        return cijena;
    }

    public Date getDatum() {
        return datum;
    }

    public String getRacunIzdao() {
        return racunIzdao;
    }

    public void setFakturaId(Integer fakturaId) {
        this.fakturaId = fakturaId;
    }

    public void setBrojRacuna(String brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public void setNazivRobe(String nazivRobe) {
        this.nazivRobe = nazivRobe;
    }

    public void setJedinicaMjere(String jedinicaMjere) {
        this.jedinicaMjere = jedinicaMjere;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setRacunIzdao(String racunIzdao) {
        this.racunIzdao = racunIzdao;
    }

    @Override
    public String toString() {
        return "Faktura{" + "fakturaId=" + fakturaId + ", brojRacuna=" + brojRacuna + ", nazivRobe=" + nazivRobe + ", jedinicaMjere=" + jedinicaMjere + ", kolicina=" + kolicina + ", cijena=" + cijena + ", datum=" + datum + ", racunIzdao=" + racunIzdao + '}';
    }

}
