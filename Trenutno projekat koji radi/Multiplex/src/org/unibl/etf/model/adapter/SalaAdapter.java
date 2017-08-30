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
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.ProjekcijaSala;
import org.unibl.etf.model.domain.Sala;
import org.unibl.etf.model.domain.oo.SalaOO;
import org.unibl.etf.model.domain.oo.SjedisteOO;

/**
 *
 * @author Aleksandar
 */
public class SalaAdapter {

    private static final SalaDAO salaDAO = MySQLDAOFactory.getInstance().getSalaDAO();
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
        if (1 == salaList.size()) {
            salaOO = konvertujUOO(salaList.get(0));
        }
        return salaOO;
    }

    public static void unesi(SalaOO salaOO) {        
        //unos sale
        Sala sala = konvertujUOV(salaOO);
        salaDAO.insert(sala);
        salaOO.setSalaId(sala.getSalaId());
        
        //unos sjediste
        for (SjedisteOO sjedisteOO : salaOO.getSjedista()) {
            SjedisteAdapter.unesi(salaOO.getSalaId(), sjedisteOO);
        }
    }

    public static void izmijeni(SalaOO salaOO) {
        List<SjedisteOO> sjedisteOOList = SjedisteAdapter.preuzmiPoSalaId(salaOO.getSalaId());
        for(SjedisteOO sjedisteOO: sjedisteOOList){
            SjedisteAdapter.obrisi(sjedisteOO.getSjedisteId());
        }
        
        for(SjedisteOO sjedisteOO: salaOO.getSjedista()){
            SjedisteAdapter.unesi(salaOO.getSalaId(), sjedisteOO);
        }
        
        salaDAO.update(konvertujUOV(salaOO));
        
    }

    public static void obrisi(Integer salaId) {
        List<ProjekcijaSala> projekcijaSalaList = projekcijaSalaDAO.selectBy(new ProjekcijaSala(null, salaId));
        
        for(ProjekcijaSala projekcijaSala: projekcijaSalaList){
            projekcijaSalaDAO.delete(salaId, projekcijaSala.getProjekcijaId());
        }
        
        SalaOO salaOO = preuzmiPoId(salaId);
        for(SjedisteOO sjedisteOO: salaOO.getSjedista()){
            SjedisteAdapter.obrisi(salaId);
        }
        
        
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
