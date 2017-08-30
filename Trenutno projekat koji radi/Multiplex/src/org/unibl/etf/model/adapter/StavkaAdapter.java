/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter;

import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.model.dao.StavkaDAO;
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.Stavka;
import org.unibl.etf.model.domain.oo.ArtikalOO;
import org.unibl.etf.model.domain.oo.StavkaOO;

/**
 *
 * @author Aleksandar
 */
public class StavkaAdapter {
    
    private static final StavkaDAO stavkaDAO = MySQLDAOFactory.getInstance().getStavkaDAO();
    
    public static ArrayList<StavkaOO> preuzmiSve(){
        List<Stavka> stavkaList = stavkaDAO.selectAll();
        ArrayList<StavkaOO> stavkaOOList = new ArrayList<>();
        for (Stavka stavka : stavkaList) {
            stavkaOOList.add(konvertujUOO(stavka));
        }
        return stavkaOOList;
    }
    
    public static StavkaOO preuzmiPoId(Integer stavkaId){
        StavkaOO stavkaOO = null;
        List<Stavka> stavkaList = stavkaDAO.selectBy(new Stavka(stavkaId, null, null, null, null));
        Stavka stavka = stavkaList.get(0);
        if (null != stavka) {
            stavkaOO = konvertujUOO(stavka);
        }
        return stavkaOO;
    }
    
    public static ArrayList<StavkaOO> preuzmiPoArtikalId(Integer artikalId){
        ArrayList<StavkaOO> stavkaOOList = new ArrayList<>();
        List<Stavka> stavkaList = stavkaDAO.selectBy(new Stavka(null, artikalId, null, null, null));
        for(Stavka stavka: stavkaList){
            stavkaOOList.add(konvertujUOO(stavka));
        }
        return stavkaOOList;
    }
    
    public static ArrayList<StavkaOO> preuzmiPoRacunId(Integer racunId){
        ArrayList<StavkaOO> stavkaOOList = new ArrayList<>();
        List<Stavka> stavkaList = stavkaDAO.selectBy(new Stavka(null, null, racunId, null, null));
        for(Stavka stavka: stavkaList){
            stavkaOOList.add(konvertujUOO(stavka));
        }
        return stavkaOOList;
    }
    
    public static void unesi(Integer racunId, StavkaOO stavkaOO){
        stavkaDAO.insert(konvertujUOV(racunId, stavkaOO));
    }
    
    public static void izmijeni(Integer racunId, StavkaOO stavkaOO){
        stavkaDAO.update(konvertujUOV(racunId, stavkaOO));
    }
    
    public static void obrisi(Integer stavkaId){
        stavkaDAO.delete(stavkaId);
    }
    
    private static StavkaOO konvertujUOO(Stavka stavka){
        ArtikalOO artikalOO = ArtikalAdapter.preuzmiPoId(stavka.getArtikalId());
        return new StavkaOO(
                stavka.getStavkaId(),
                stavka.getKolicina(),
                stavka.getUkupnaCijena(),
                artikalOO
        );
    }
    
    private static Stavka konvertujUOV(Integer racunId, StavkaOO stavkaOO){
        return new Stavka(
                stavkaOO.getStavkaId(),
                stavkaOO.getArtikal().getArtikalId(),
                racunId,
                stavkaOO.getKolicina(),
                stavkaOO.getUkupnaCijena()
        );
    }
    
    
}
