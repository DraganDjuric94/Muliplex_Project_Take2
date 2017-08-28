/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Zaposleni implements Serializable{
    private Integer zaposleniId;
    private String ime;
    private String prezime;
    private String jmbg;
    private Boolean aktivan;
    private Double plata;
    private String korisnickoIme;
    private String lozinka;

    public Zaposleni() {
    }

    public Zaposleni(Integer zaposleniId, String ime, String prezime, String jmbg, Boolean aktivan, Double plata, String korisnickoIme, String lozinka) {
        this.zaposleniId = zaposleniId;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.aktivan = aktivan;
        this.plata = plata;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public Integer getZaposleniId() {
        return zaposleniId;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public Boolean getAktivan() {
        return aktivan;
    }

    public Double getPlata() {
        return plata;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setZaposleniId(Integer zaposleniId) {
        this.zaposleniId = zaposleniId;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public void setAktivan(Boolean aktivan) {
        this.aktivan = aktivan;
    }

    public void setPlata(Double plata) {
        this.plata = plata;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public String toString() {
        return "Zaposleni{" + "zaposleniId=" + zaposleniId + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", aktivan=" + aktivan + ", plata=" + plata + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + '}';
    }
    
    
    
    
}
