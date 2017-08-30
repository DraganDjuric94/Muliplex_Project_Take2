/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter;

import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.model.dao.PozicijaDAO;
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.Pozicija;
import org.unibl.etf.model.domain.oo.PozicijaOO;

/**
 *
 * @author Aleksandar
 */
public class PozicijaAdapter {

    private static PozicijaDAO pozicijaDAO = MySQLDAOFactory.getInstance().getPozicijaDAO();

    public static ArrayList<PozicijaOO> preuzmiSve() {
        List<Pozicija> pozicijaList = pozicijaDAO.selectAll();
        ArrayList<PozicijaOO> pozicijaOOList = new ArrayList<>();
        for (Pozicija pozicija : pozicijaList) {
            pozicijaOOList.add(konvertujUOO(pozicija));
        }
        return pozicijaOOList;
    }

    public static PozicijaOO preuzmiPoId(Integer pozicijaId) {
        PozicijaOO pozicijaOO = null;
        List<Pozicija> pozicijaList = pozicijaDAO.selectBy(new Pozicija(pozicijaId, null, null, null));
        if (1 == pozicijaList.size()) {
            pozicijaOO = konvertujUOO(pozicijaList.get(0));
        }
        return pozicijaOO;
    }

    public static ArrayList<PozicijaOO> preuzmiPoNazivu(String naziv) {
        ArrayList<PozicijaOO> pozicijaOOList = new ArrayList<>();
        List<Pozicija> pozicijaList = pozicijaDAO.selectBy(new Pozicija(null, naziv, null, null));
        for (Pozicija pozicija : pozicijaList) {
            pozicijaOOList.add(konvertujUOO(pozicija));
        }
        return pozicijaOOList;
    }

    public static void unesi(PozicijaOO pozicijaOO){
        Pozicija pozicija = konvertujUOV(pozicijaOO);
        pozicijaDAO.insert(pozicija);
        pozicijaOO.setPozicijaId(pozicija.getPozicijaId());
    }
    
    public static void izmijeni(PozicijaOO pozicija) {
        pozicijaDAO.update(konvertujUOV(pozicija));
    }

    public static void obrisi(Integer pozicijaId) {
        pozicijaDAO.delete(pozicijaId);
    }

    private static PozicijaOO konvertujUOO(Pozicija pozicija) {
        return new PozicijaOO(
                pozicija.getPozicijaId(),
                pozicija.getNaziv(),
                pozicija.getDatumOd(),
                pozicija.getDatumDo()
        );
    }

    private static Pozicija konvertujUOV(PozicijaOO pozicijaOO) {
        java.sql.Date datumOdSQL = new java.sql.Date(pozicijaOO.getDatumOd().getTime());
        java.sql.Date datumDoSQL = null;
        if(null != pozicijaOO.getDatumDo()){
            datumDoSQL = new java.sql.Date(pozicijaOO.getDatumDo().getTime());
        }
        return new Pozicija(
                pozicijaOO.getPozicijaId(),
                pozicijaOO.getNaziv(),
                datumOdSQL,
                datumDoSQL
        );
    }

}
