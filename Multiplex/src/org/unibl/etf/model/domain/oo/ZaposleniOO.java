/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.domain.oo;

import java.util.Objects;
import org.unibl.etf.model.domain.Pozicija;

/**
 *
 * @author Aleksandar
 */
public class ZaposleniOO {
    private Integer zaposleniId;
    private String ime;
    private String prezime;
    private String jmbg;
    private String korisnickoIme;
    private String lozinka;
    private Double plata;
    private Boolean aktivan;
    private PozicijaOO pozicija;

    public ZaposleniOO() {
    }

    public ZaposleniOO(Integer zaposleniId, String ime, String prezime, String jmbg, String korisnickoIme, String lozinka, Double plata, Boolean aktivan, PozicijaOO pozicija) {
        this.zaposleniId = zaposleniId;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.plata = plata;
        this.aktivan = aktivan;
        this.pozicija = pozicija;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public Double getPlata() {
        return plata;
    }

    public Boolean getAktivan() {
        return aktivan;
    }

    public PozicijaOO getPozicija() {
        return pozicija;
    }
    
    public String getNazivPozicije(){
        return pozicija.getNaziv();
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

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setPlata(Double plata) {
        this.plata = plata;
    }

    public void setAktivan(Boolean aktivan) {
        this.aktivan = aktivan;
    }

    public void setPozicija(PozicijaOO pozicija) {
        this.pozicija = pozicija;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.zaposleniId);
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
        final ZaposleniOO other = (ZaposleniOO) obj;
        if (!Objects.equals(this.zaposleniId, other.zaposleniId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ZaposleniOO{" + "zaposleniId=" + zaposleniId + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", plata=" + plata + ", aktivan=" + aktivan + ", pozicija=" + pozicija + '}';
    }
    
    
    
    
}
