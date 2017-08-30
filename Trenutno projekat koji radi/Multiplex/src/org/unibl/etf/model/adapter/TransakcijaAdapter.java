/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.unibl.etf.model.dao.TransakcijaDAO;
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.Transakcija;
import org.unibl.etf.model.domain.oo.ProjekcijaOO;
import org.unibl.etf.model.domain.oo.TransakcijaOO;

/**
 *
 * @author Aleksandar
 */
public class TransakcijaAdapter {

    private static TransakcijaDAO transakcijaDAO = MySQLDAOFactory.getInstance().getTransakcijaDAO();

    public static ArrayList<TransakcijaOO> preuzmiSve() {
        List<Transakcija> transakcijaList = transakcijaDAO.selectAll();
        ArrayList<TransakcijaOO> transakcijaOOList = new ArrayList<>();
        for (Transakcija transakcija : transakcijaList) {
            transakcijaOOList.add(konvertujUOO(transakcija));
        }
        return transakcijaOOList;
    }

    public static TransakcijaOO preuzmiPoId(Integer transakcijaId) {
        TransakcijaOO transakcijaOO = null;
        List<Transakcija> transakcijaList = transakcijaDAO.selectBy(new Transakcija(transakcijaId, null, null, null, null, null));
        Transakcija transakcija = transakcijaList.get(0);
        if (1 == transakcijaList.size()) {
            transakcijaOO = konvertujUOO(transakcijaList.get(0));
        }
        return transakcijaOO;
    }

    public static ArrayList<TransakcijaOO> preuzmiPoVrstiTransakcije(String vrstaTransakcije) {
        ArrayList<TransakcijaOO> transakcijaOOList = new ArrayList<>();
        List<Transakcija> transakcijaList = transakcijaDAO.selectBy(new Transakcija(null, vrstaTransakcije, null, null, null, null));
        for (Transakcija transakcija : transakcijaList) {
            transakcijaOOList.add(konvertujUOO(transakcija));
        }
        return transakcijaOOList;
    }
    
    public static ArrayList<TransakcijaOO> preuzmiPoPrimaocu(String primalac) {
        ArrayList<TransakcijaOO> transakcijaOOList = new ArrayList<>();
        List<Transakcija> transakcijaList = transakcijaDAO.selectBy(new Transakcija(null, null, primalac, null, null, null));
        for (Transakcija transakcija : transakcijaList) {
            transakcijaOOList.add(konvertujUOO(transakcija));
        }
        return transakcijaOOList;
    }
    
    public static ArrayList<TransakcijaOO> preuzmiPoPosaljiocu(String posaljilac) {
        ArrayList<TransakcijaOO> transakcijaOOList = new ArrayList<>();
        List<Transakcija> transakcijaList = transakcijaDAO.selectBy(new Transakcija(null, null, null, posaljilac, null, null));
        for (Transakcija transakcija : transakcijaList) {
            transakcijaOOList.add(konvertujUOO(transakcija));
        }
        return transakcijaOOList;
    }
    
    public static ArrayList<TransakcijaOO> preuzmiPoDatumuTransakcije(Date datum) {
        java.sql.Date datumSQL = new java.sql.Date(datum.getTime());
        ArrayList<TransakcijaOO> transakcijaOOList = new ArrayList<>();
        List<Transakcija> transakcijaList = transakcijaDAO.selectBy(new Transakcija(null, null, null, null, datumSQL, null));
        for (Transakcija transakcija : transakcijaList) {
            transakcijaOOList.add(konvertujUOO(transakcija));
        }
        return transakcijaOOList;
    }
    
    public static ArrayList<TransakcijaOO> preuzmiPoIznosu(Double iznos) {
        ArrayList<TransakcijaOO> transakcijaOOList = new ArrayList<>();
        List<Transakcija> transakcijaList = transakcijaDAO.selectBy(new Transakcija(null, null, null, null, null, iznos));
        for (Transakcija transakcija : transakcijaList) {
            transakcijaOOList.add(konvertujUOO(transakcija));
        }
        return transakcijaOOList;
    }

    public static void unesi(TransakcijaOO transakcijaOO){
        Transakcija transakcija = konvertujUOV(transakcijaOO);
        transakcijaDAO.insert(transakcija);
        transakcijaOO.setTransakcijaId(transakcija.getTransakcijaId());
    }
    
    public static void izmijeni(TransakcijaOO transakcijaOO) {
        transakcijaDAO.update(konvertujUOV(transakcijaOO));
    }

    public static void obrisi(Integer transakcijaId) {
        transakcijaDAO.delete(transakcijaId);
    }

    private static TransakcijaOO konvertujUOO(Transakcija transakcija) {
        return new TransakcijaOO(
                transakcija.getTransakcijaId(),
                transakcija.getVrstaTransakcije(),
                transakcija.getPrimalac(),
                transakcija.getPosaljilac(),
                transakcija.getDatumTransakcije(),
                transakcija.getIznos()
        );
    }

    private static Transakcija konvertujUOV(TransakcijaOO transakcijaOO) {
        java.sql.Date datumSQL = new java.sql.Date(transakcijaOO.getDatumTransakcije().getTime());
        return new Transakcija(
                transakcijaOO.getTransakcijaId(),
                transakcijaOO.getVrstaTransakcije(),
                transakcijaOO.getPrimalac(),
                transakcijaOO.getPosaljilac(),
                datumSQL,
                transakcijaOO.getIznos()
        );

    }

}
