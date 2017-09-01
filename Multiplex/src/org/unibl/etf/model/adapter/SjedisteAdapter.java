/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter;

import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.model.dao.SjedisteDAO;
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.Sjediste;
import org.unibl.etf.model.domain.oo.KartaOO;
import org.unibl.etf.model.domain.oo.SjedisteOO;

/**
 *
 * @author Aleksandar
 */
public class SjedisteAdapter {
    
    private static SjedisteDAO sjedisteDAO = MySQLDAOFactory.getInstance().getSjedisteDAO();
    
    public static ArrayList<SjedisteOO> preuzmiSve(){
        List<Sjediste> sjedisteList = sjedisteDAO.selectAll();
        ArrayList<SjedisteOO> sjedisteOOList = new ArrayList<>();
        for(Sjediste sjediste: sjedisteList){
            sjedisteOOList.add(konvertujUOO(sjediste));
        }
        return sjedisteOOList;
    }
    
    public static SjedisteOO preuzmiPoId(Integer sjedisteId){
        SjedisteOO sjedisteOO = null;
        List<Sjediste> sjedisteList = sjedisteDAO.selectBy(new Sjediste(sjedisteId, null, null, null));
        if (1 == sjedisteList.size()) {
            sjedisteOO = konvertujUOO(sjedisteList.get(0));
        }
        return sjedisteOO;
    }
    
    public static ArrayList<SjedisteOO> preuzmiPoSalaId(Integer salaId){
        ArrayList<SjedisteOO> sjedisteOOList = new ArrayList<>();
        List<Sjediste> sjedisteList = sjedisteDAO.selectBy(new Sjediste(null, salaId, null, null));
        for (Sjediste sjediste: sjedisteList) {
            sjedisteOOList.add(konvertujUOO(sjediste));
        }
        return sjedisteOOList;
    }
    
    
    public static void unesi(Integer salaId, SjedisteOO sjedisteOO){
        Sjediste sjediste = konvertujUOV(salaId, sjedisteOO);
        sjedisteDAO.insert(sjediste);
        sjedisteOO.setSjedisteId(sjediste.getSjedisteId());
    }
    
    public static void izmijeni(Integer salaId, SjedisteOO sjedisteOO){
        sjedisteDAO.update(konvertujUOV(salaId, sjedisteOO));
    }
    
    public static void obrisi(Integer sjedisteId){
        List<KartaOO> kartaOOList = KartaAdapter.preuzmiPoSjedisteId(sjedisteId);
        for(KartaOO kartaOO: kartaOOList){
            KartaAdapter.obrisi(kartaOO.getKartaId());
        }
        sjedisteDAO.delete(sjedisteId);
    }
    
    private static SjedisteOO konvertujUOO(Sjediste sjediste){
        return new SjedisteOO(
                sjediste.getSjedisteId(),
                sjediste.getKolona(),
                sjediste.getVrsta()
        );
    }
    
    private static Sjediste konvertujUOV(Integer salaId, SjedisteOO sjedisteOO){
        return new Sjediste(
                sjedisteOO.getSjedisteId(),
                salaId,
                sjedisteOO.getKolona(),
                sjedisteOO.getVrsta()
        );
    }
}
