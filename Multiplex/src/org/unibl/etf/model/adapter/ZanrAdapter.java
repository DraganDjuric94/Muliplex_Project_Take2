/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter;

import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.model.dao.ZanrDAO;
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.Zanr;
import org.unibl.etf.model.domain.oo.ZanrOO;

/**
 *
 * @author Aleksandar
 */
public class ZanrAdapter {
    
    private static ZanrDAO zanrDAO = MySQLDAOFactory.getInstance().getZanrDAO();
    
    public static ArrayList<ZanrOO> preuzmiSve(){
        List<Zanr> zanrList = zanrDAO.selectAll();
        ArrayList<ZanrOO> zanrOOList = new ArrayList<>();
        for (Zanr zanr : zanrList) {
            zanrOOList.add(konvertujUOO(zanr));
        }
        return zanrOOList;
    }
    
    public static ZanrOO preuzmiPoId(Integer zanrId){
        ZanrOO zanrOO = null;
        List<Zanr> zanrList = zanrDAO.selectBy(new Zanr(zanrId, null));
        if (1 == zanrList.size()) {
            zanrOO = konvertujUOO(zanrList.get(0));
        }
        return zanrOO;
    }
    
    public static ArrayList<ZanrOO> preuzmiPoNazivu(String naziv){
        ArrayList<ZanrOO> zanrOOList = new ArrayList<>();
        List<Zanr> zanrList = zanrDAO.selectBy(new Zanr(null, naziv));
        for (Zanr zanr: zanrList) {
            zanrOOList.add(konvertujUOO(zanr));
        }
        return zanrOOList;
    }
    
    public static void unesi(ZanrOO zanrOO){
        Zanr zanr = konvertujUOV(zanrOO);
        zanrDAO.insert(zanr);
        zanrOO.setZanrId(zanr.getZanrId());
    }
    
    public static void izmijeni(ZanrOO zanr){
        zanrDAO.update(konvertujUOV(zanr));
    }
    
    public static void obrisi(Integer zanrId){
        zanrDAO.delete(zanrId);
    }
    
    private static ZanrOO konvertujUOO(Zanr zanr){
        return new ZanrOO(
                zanr.getZanrId(),
                zanr.getNaziv()
        );
    }
    
    private static Zanr konvertujUOV(ZanrOO zanrOO){
        return new Zanr(
                zanrOO.getZanrId(),
                zanrOO.getNaziv()
        );
    }
}
