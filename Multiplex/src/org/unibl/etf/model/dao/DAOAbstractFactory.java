/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

/**
 *
 * @author Aleksandar
 */
public interface DAOAbstractFactory {
    
    public ArtikalDAO getArtikalDAO();
    public FakturaDAO getFakturaDAO();
    public FilmDAO getFilmDAO();
    public FilmZanrDAO getFilmZanrDAO();
    public KartaDAO getKartaDAO();
    public OpremaDAO getOpremaDAO();
    public PonudaZaFilmDAO getPonudaZaFilmDAO();
    public PozicijaDAO getPozicijaDAO();
    public ProjekcijaDAO getProjekcijaDAO();
    public ProjekcijaSalaDAO getProjekcijaSalaDAO();
    public RacunDAO getRacunDAO();
    public SalaDAO getSalaDAO();
    public SjedisteDAO getSjedisteDAO();
    public StavkaDAO getStavkaDAO();
    public TransakcijaDAO getTransakcijaDAO();
    public ZanrDAO getZanrDAO();
    public ZaposleniDAO getZaposleniDAO();
    public ZaposleniPozicijaDAO getZaposleniPozicijaDAO();
    
}
