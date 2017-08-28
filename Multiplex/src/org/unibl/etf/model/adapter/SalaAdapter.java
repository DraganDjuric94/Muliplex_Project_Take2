/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter;

import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.model.dao.ProjekcijaSalaDAO;
import org.unibl.etf.model.dao.SalaDAO;
import org.unibl.etf.model.dao.SjedisteDAO;
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.ProjekcijaSala;
import org.unibl.etf.model.domain.Sala;
import org.unibl.etf.model.domain.Sjediste;
import org.unibl.etf.model.domain.oo.SalaOO;
import org.unibl.etf.model.domain.oo.SjedisteOO;

/**
 *
 * @author Aleksandar
 */
public class SalaAdapter {

    private static final SalaDAO salaDAO = MySQLDAOFactory.getInstance().getSalaDAO();
    private static final SjedisteDAO sjedisteDAO = MySQLDAOFactory.getInstance().getSjedisteDAO();
    private static final ProjekcijaSalaDAO projekcijaSalaDAO = MySQLDAOFactory.getInstance().getProjekcijaSalaDAO();

    public static ArrayList<SalaOO> preuzmiSve() {
        List<Sala> salaList = salaDAO.selectAll();
        ArrayList<SalaOO> salaOOList = new ArrayList<>();
        for (Sala sala : salaList) {
            salaOOList.add(konvertujUOO(sala));
        }
        return salaOOList;
    }

    public static SalaOO preuzmiPoId(Integer salaId) {
        SalaOO salaOO = null;
        List<Sala> salaList = salaDAO.selectBy(new Sala(salaId, null, null));
        Sala sala = salaList.get(0);
        if (null != sala) {
            salaOO = konvertujUOO(sala);
        }
        return salaOO;
    }

    public static void unesi(SalaOO salaOO) {
        //unos sjediste
        for (SjedisteOO sjedisteOO : salaOO.getSjedista()) {
            SjedisteAdapter.unesi(salaOO.getSalaId(), sjedisteOO);
        }
        
        //unos sale
        salaDAO.insert(konvertujUOV(salaOO));
    }

    public static void izmijeni(SalaOO salaOO) {
        //brisanje sjedista
        List<Sjediste> sjedisteList = sjedisteDAO.selectBy(new Sjediste(null, salaOO.getSalaId(), null, null));
        for (Sjediste sjediste : sjedisteList) {
            sjedisteDAO.delete(sjediste.getSjedisteId());
        }
        
        //brisanje sale
        salaDAO.delete(salaOO.getSalaId());
    }

    public static void obrisi(Integer salaId) {
        //brisanje sjediste
        List<Sjediste> sjedisteList = sjedisteDAO.selectBy(new Sjediste(null, salaId, null, null));
        for (Sjediste sjediste : sjedisteList) {
            sjedisteDAO.delete(sjediste.getSjedisteId());
        }
        
        //brisanje projekcija_baza
        List<ProjekcijaSala> projekcijaSalaList = projekcijaSalaDAO.selectBy(new ProjekcijaSala(salaId, null));
        for(ProjekcijaSala projekcijaSala:projekcijaSalaList){
            projekcijaSalaDAO.delete(projekcijaSala.getProjekcijaId(), projekcijaSala.getSalaId());
        }
        
        salaDAO.delete(salaId);
    }

    private static SalaOO konvertujUOO(Sala sala) {
        ArrayList<SjedisteOO> sjedisteOOList = SjedisteAdapter.preuzmiPoSalaId(sala.getSalaId());
        return new SalaOO(
                sala.getSalaId(),
                sala.getBrojRedova(),
                sala.getBrojKolona(),
                sjedisteOOList
        );
    }

    private static Sala konvertujUOV(SalaOO salaOO) {
        return new Sala(
                salaOO.getSalaId(),
                salaOO.getBrojRedova(),
                salaOO.getBrojKolona()
        );
    }

}
