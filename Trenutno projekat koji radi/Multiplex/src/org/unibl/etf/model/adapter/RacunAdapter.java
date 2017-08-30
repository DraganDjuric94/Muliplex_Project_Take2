/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter;

import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.model.dao.RacunDAO;
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.Racun;
import org.unibl.etf.model.domain.oo.RacunOO;
import org.unibl.etf.model.domain.oo.StavkaOO;

/**
 *
 * @author Aleksandar
 */
public class RacunAdapter {
    
    private static final RacunDAO racunDAO = MySQLDAOFactory.getInstance().getRacunDAO();
    
    public static ArrayList<RacunOO> preuzmiSve(){
        List<Racun> racunList = racunDAO.selectAll();
        ArrayList<RacunOO> racunOOList = new ArrayList<>();
        for (Racun racun : racunList) {
            racunOOList.add(konvertujUOO(racun));
        }
        return racunOOList;
    }
    
    public static RacunOO preuzmiPoId(Integer racunId){
        RacunOO racunOO = null;
        List<Racun> racunList = racunDAO.selectBy(new Racun(racunId, null, null));
        if (1 == racunList.size()) {
            racunOO = konvertujUOO(racunList.get(0));
        }
        return racunOO;
    }
    
    public static void unesi(RacunOO racunOO){
        
        Racun racun = konvertujUOV(racunOO);
        racunDAO.insert(racun);
        racunOO.setRacunId(racun.getRacunId());
         
        for(StavkaOO stavkaOO: racunOO.getStavke()){
            StavkaAdapter.unesi(racunOO.getRacunId(), stavkaOO);
        }
    }
    
    public static void izmijeni(RacunOO racunOO){
        
        
        //Izbrisi stare stavke
        List<StavkaOO> stavkaOOList = StavkaAdapter.preuzmiPoRacunId(racunOO.getRacunId());
        for(StavkaOO stavkaOO: stavkaOOList){
            StavkaAdapter.obrisi(stavkaOO.getStavkaId());
        }
        
        //Dodaj nove stavke
        for(StavkaOO stavkaOO: racunOO.getStavke()){
            StavkaAdapter.unesi(racunOO.getRacunId(), stavkaOO);
        }
        
        //Azuriraj ostala polja racuna
        racunDAO.update(konvertujUOV(racunOO));
    }
    
    public static void obrisi(Integer racunId){
        //Izbrisi stavke
        List<StavkaOO> stavkaOOList = StavkaAdapter.preuzmiPoRacunId(racunId);
        for(StavkaOO stavkaOO: stavkaOOList){
            StavkaAdapter.obrisi(stavkaOO.getStavkaId());
        }
        
        //Izbrisi racun
        racunDAO.delete(racunId);
    }
    
    private static RacunOO konvertujUOO(Racun racun){
       List<StavkaOO> stavkaOOList = StavkaAdapter.preuzmiPoRacunId(racun.getRacunId());
       return new RacunOO(
               racun.getRacunId(),
               racun.getDatumVrijeme(),
               racun.getUkupnaCijena(),
               stavkaOOList
       );
        
        
        
    }
    
    private static Racun konvertujUOV(RacunOO racunOO){
        java.sql.Timestamp datumVrijemeSQL = new java.sql.Timestamp(racunOO.getDatumIzdavanja().getTime());
        return new Racun(
                racunOO.getRacunId(),
                datumVrijemeSQL,
                racunOO.getUkupnaCijena()
        );
    }
}
