/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter;

import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.model.dao.KartaDAO;
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.Karta;
import org.unibl.etf.model.domain.oo.KartaOO;
import org.unibl.etf.model.domain.oo.ProjekcijaOO;
import org.unibl.etf.model.domain.oo.SjedisteOO;

/**
 *
 * @author Aleksandar
 */
public class KartaAdapter {
    
    private static final KartaDAO kartaDAO = MySQLDAOFactory.getInstance().getKartaDAO();
    
    public static ArrayList<KartaOO> preuzmiSve(){
        List<Karta> kartaList = kartaDAO.selectAll();
        ArrayList<KartaOO> kartaOOList = new ArrayList<>();
        for (Karta karta : kartaList) {
            kartaOOList.add(konvertujUOO(karta.getProjekcijaId(), karta.getSjedisteId(), karta));
        }
        return kartaOOList;
    }
    
    public static KartaOO preuzmiPoId(Integer kartaId){
        KartaOO kartaOO = null;
        List<Karta> kartaList = kartaDAO.selectBy(new Karta(kartaId, null, null, null, null, null));
        if (1 == kartaList.size()) {
            Karta karta = kartaList.get(0);
            kartaOO = konvertujUOO(karta.getProjekcijaId(), karta.getSjedisteId(), karta);
        }
        return kartaOO;
    }
    
    public static ArrayList<KartaOO> preuzmiPoProjekcijaId(Integer projekcijaId){
        ArrayList<KartaOO> kartaOOList = new ArrayList<>();
        List<Karta> kartaList = kartaDAO.selectBy(new Karta(null, projekcijaId, null, null, null, null));
        for (Karta karta : kartaList) {
            kartaOOList.add(konvertujUOO(projekcijaId, karta.getSjedisteId(), karta));
        }
        return kartaOOList;
    }
    
    public static ArrayList<KartaOO> preuzmiPoSjedisteId(Integer sjedisteId){
        ArrayList<KartaOO> kartaOOList = new ArrayList<>();
        List<Karta> kartaList = kartaDAO.selectBy(new Karta(null, null, sjedisteId, null, null, null));
        for (Karta karta : kartaList) {
            kartaOOList.add(konvertujUOO(karta.getProjekcijaId(), sjedisteId, karta));
        }
        return kartaOOList;
    }
    
    public static void unesi(KartaOO kartaOO){
        kartaDAO.insert(konvertujUOV(kartaOO));
    }
    
    public static void izmijeni(KartaOO kartaOO){
        kartaDAO.update(konvertujUOV(kartaOO));
    }
    
    public static void obrisi(Integer kartaId){
        kartaDAO.delete(kartaId);
    }
    
    private static KartaOO konvertujUOO(Integer projekcijaId, Integer sjedisteId, Karta karta){
        ProjekcijaOO projekcijaOO = ProjekcijaAdapter.preuzmiPoId(projekcijaId);
        SjedisteOO sjedisteOO = SjedisteAdapter.preuzmiPoId(sjedisteId);
        return new KartaOO(
                karta.getKartaId(),
                projekcijaOO,
                sjedisteOO,
                karta.getDatumVrijeme(),
                karta.getCijena(),
                karta.getRezervisana()
        );
    }
    
    private static Karta konvertujUOV(KartaOO kartaOO){
        java.sql.Timestamp datumVrijeme = new java.sql.Timestamp(kartaOO.getDatumIzdavanja().getTime());
        return new Karta(
                kartaOO.getKartaId(),
                kartaOO.getProjekcija().getProjekcijaId(),
                kartaOO.getSjediste().getSjedisteId(),
                datumVrijeme,
                kartaOO.getCijena(),
                kartaOO.getRezervisana()
        );
    }
    
}
