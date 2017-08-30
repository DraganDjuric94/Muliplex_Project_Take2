/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter;

import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.model.dao.OpremaDAO;
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.Oprema;
import org.unibl.etf.model.domain.oo.OpremaOO;

/**
 *
 * @author Aleksandar
 */
public class OpremaAdapter {

    private static OpremaDAO opremaDAO = MySQLDAOFactory.getInstance().getOpremaDAO();

    public static ArrayList<OpremaOO> preuzmiSve() {
        List<Oprema> opremaList = opremaDAO.selectAll();
        ArrayList<OpremaOO> opremaOOList = new ArrayList<>();
        for (Oprema oprema : opremaList) {
            opremaOOList.add(konvertujUOO(oprema));
        }
        return opremaOOList;
    }

    public static OpremaOO preuzmiPoId(Integer id) {
        OpremaOO opremaOO = null;
        List<Oprema> opremaList = opremaDAO.selectBy(new Oprema(id, null, null, null));
        if (1 == opremaList.size()) {
            opremaOO = konvertujUOO(opremaList.get(0));
        }
        return opremaOO;
    }

    public static ArrayList<OpremaOO> preuzmiPoBrojuInventara(String brojInventara) {
        ArrayList<OpremaOO> opremaOOList = new ArrayList<>();
        List<Oprema> opremaList = opremaDAO.selectBy(new Oprema(null, brojInventara, null, null));
        for (Oprema oprema : opremaList) {
            opremaOOList.add(konvertujUOO(oprema));
        }
        return opremaOOList;
    }

    public static ArrayList<OpremaOO> preuzmiPoNazivu(String naziv) {
        ArrayList<OpremaOO> opremaOOList = new ArrayList<>();
        List<Oprema> opremaList = opremaDAO.selectBy(new Oprema(null, null, naziv, null));
        for (Oprema oprema : opremaList) {
            opremaOOList.add(konvertujUOO(oprema));
        }
        return opremaOOList;
    }

    public static ArrayList<OpremaOO> preuzmiPoIspravnosti(Boolean ispravnost) {
        ArrayList<OpremaOO> opremaOOList = new ArrayList<>();
        List<Oprema> opremaList = opremaDAO.selectBy(new Oprema(null, null, null, ispravnost));
        for (Oprema oprema : opremaList) {
            opremaOOList.add(konvertujUOO(oprema));
        }
        return opremaOOList;
    }
    
    public static void unesi(OpremaOO opremaOO){
        Oprema oprema = konvertujUOV(opremaOO);
        opremaDAO.insert(oprema);
        opremaOO.setOpremaId(oprema.getOpremaId());
    }

    public static void izmijeni(OpremaOO opremaOO) {
        Oprema oprema = konvertujUOV(opremaOO);
        opremaDAO.update(oprema);
    }

    public static void obrisi(Integer id) {
        opremaDAO.delete(id);
    }

    private static OpremaOO konvertujUOO(Oprema oprema) {
        return new OpremaOO(
                oprema.getOpremaId(),
                oprema.getBrojInventara(),
                oprema.getNaziv(),
                oprema.getIspravnost()
        );
    }

    private static Oprema konvertujUOV(OpremaOO opremaOO) {
        return new Oprema(
                opremaOO.getOpremaId(),
                opremaOO.getBrojInventara(),
                opremaOO.getNaziv(),
                opremaOO.getIspravnost()
        );
    }
}
