/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.model.dao.FakturaDAO;
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.Faktura;
import org.unibl.etf.model.domain.oo.FakturaOO;

/**
 *
 * @author Aleksandar
 */
public class FakturaAdapter {

    private static FakturaDAO fakturaDAO = MySQLDAOFactory.getInstance().getFakturaDAO();

    public static ArrayList<FakturaOO> preuzmiSve() {
        List<Faktura> fakturaList = fakturaDAO.selectAll();
        ArrayList<FakturaOO> fakturaOOList = new ArrayList<>();
        for (Faktura faktura : fakturaList) {
            fakturaOOList.add(konvertujUOO(faktura));
        }
        return fakturaOOList;
    }

    public static FakturaOO preuzmiPoId(Integer fakturaId) {
        FakturaOO fakturaOO = null;
        List<Faktura> fakturaList = fakturaDAO.selectBy(new Faktura(fakturaId, null, null, null, null, null, null, null));
        Faktura faktura = fakturaList.get(0);
        if (null != faktura) {
            fakturaOO = konvertujUOO(faktura);
        }
        return fakturaOO;
    }
    
    public static ArrayList<FakturaOO> preuzmiPoBrojuRacuna(String brojRacuna) {
        ArrayList<FakturaOO> fakturaOOList = new ArrayList<>();
        List<Faktura> fakturaList = fakturaDAO.selectBy(new Faktura(null, brojRacuna, null, null, null, null, null, null));
        for(Faktura faktura: fakturaList){
            fakturaOOList.add(konvertujUOO(faktura));
        }
        return fakturaOOList;
    }
    
    public static ArrayList<FakturaOO> preuzmiPoNazivuRobe(String nazivRobe) {
        ArrayList<FakturaOO> fakturaOOList = new ArrayList<>();
        List<Faktura> fakturaList = fakturaDAO.selectBy(new Faktura(null, null, nazivRobe, null, null, null, null, null));
        for(Faktura faktura: fakturaList){
            fakturaOOList.add(konvertujUOO(faktura));
        }
        return fakturaOOList;
    }
    
    public static ArrayList<FakturaOO> preuzmiPoJediniciMjere(String jedinicaMjere) {
        ArrayList<FakturaOO> fakturaOOList = new ArrayList<>();
        List<Faktura> fakturaList = fakturaDAO.selectBy(new Faktura(null, null, null, jedinicaMjere, null, null, null, null));
        for(Faktura faktura: fakturaList){
            fakturaOOList.add(konvertujUOO(faktura));
        }
        return fakturaOOList;
    }
    
    public static ArrayList<FakturaOO> preuzmiPoKolicini(Integer kolicina) {
        ArrayList<FakturaOO> fakturaOOList = new ArrayList<>();
        List<Faktura> fakturaList = fakturaDAO.selectBy(new Faktura(null, null, null, null, kolicina, null, null, null));
        for(Faktura faktura: fakturaList){
            fakturaOOList.add(konvertujUOO(faktura));
        }
        return fakturaOOList;
    }
    
    public static ArrayList<FakturaOO> preuzmiPoCijeni(Double cijena) {
        ArrayList<FakturaOO> fakturaOOList = new ArrayList<>();
        List<Faktura> fakturaList = fakturaDAO.selectBy(new Faktura(null, null, null, null, null, cijena, null, null));
        for(Faktura faktura: fakturaList){
            fakturaOOList.add(konvertujUOO(faktura));
        }
        return fakturaOOList;
    }
    
    public static ArrayList<FakturaOO> preuzmiPoDatumu(java.util.Date datum) {
        Date datumSQL = new Date(datum.getTime()); 
        ArrayList<FakturaOO> fakturaOOList = new ArrayList<>();
        List<Faktura> fakturaList = fakturaDAO.selectBy(new Faktura(null, null, null, null, null, null, datumSQL, null));
        for(Faktura faktura: fakturaList){
            fakturaOOList.add(konvertujUOO(faktura));
        }
        return fakturaOOList;
    }
    
    public static ArrayList<FakturaOO> preuzmiPoRacunIzdao(String racunIzdao) {
        ArrayList<FakturaOO> fakturaOOList = new ArrayList<>();
        List<Faktura> fakturaList = fakturaDAO.selectBy(new Faktura(null, null, null, null, null, null, null, racunIzdao));
        for(Faktura faktura: fakturaList){
            fakturaOOList.add(konvertujUOO(faktura));
        }
        return fakturaOOList;
    }
    
    public static void unesi(FakturaOO fakturaOO){
        Faktura faktura = konvertujUOV(fakturaOO);
        fakturaDAO.insert(faktura);
    }

    public static void izmijeni(FakturaOO fakturaOO) {
        Faktura faktura = konvertujUOV(fakturaOO);
        fakturaDAO.update(faktura);
    }

    public static void obrisi(Integer fakturaId) {
        fakturaDAO.delete(fakturaId);
    }

    private static FakturaOO konvertujUOO(Faktura faktura) {
        return new FakturaOO(
                faktura.getFakturaId(),
                faktura.getBrojRacuna(),
                faktura.getNazivRobe(),
                faktura.getJedinicaMjere(),
                faktura.getKolicina(),
                faktura.getCijena(),
                faktura.getDatum(),
                faktura.getRacunIzdao()
        );
    }

    private static Faktura konvertujUOV(FakturaOO fakturaOO) {
        Date datumSQL = new Date(fakturaOO.getDatum().getTime());
        return new Faktura(
                fakturaOO.getFakturaId(),
                fakturaOO.getBrojRacuna(),
                fakturaOO.getNazivRobe(),
                fakturaOO.getJedinicaMjere(),
                fakturaOO.getKolicina(),
                fakturaOO.getCijena(),
                datumSQL,
                fakturaOO.getRacunIzdao()
        );
    }
}
