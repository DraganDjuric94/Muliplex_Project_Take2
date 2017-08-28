/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter;

import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.model.dao.ArtikalDAO;
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.Artikal;
import org.unibl.etf.model.domain.oo.ArtikalOO;

/**
 *
 * @author Aleksandar
 */
public class ArtikalAdapter {

    private static ArtikalDAO artikalDAO = MySQLDAOFactory.getInstance().getArtikalDAO();

    public static ArrayList<ArtikalOO> preuzmiSve() {
        List<Artikal> artikalList = artikalDAO.selectAll();
        ArrayList<ArtikalOO> artikalOOList = new ArrayList<>();
        for (Artikal artikal : artikalList) {
            artikalOOList.add(konvertujUOO(artikal));
        }
        return artikalOOList;
    }

    public static ArtikalOO preuzmiPoId(Integer artikalId) {
        ArtikalOO artikalOO = null;
        List<Artikal> artikalList = artikalDAO.selectBy(new Artikal(artikalId, null, null, null, null, null));
        Artikal artikal = artikalList.get(0);
        if (null != artikal) {
            artikalOO = konvertujUOO(artikal);
        }
        return artikalOO;
    }

    public static ArrayList<ArtikalOO> preuzmiPoNazivu(String naziv) {
        ArrayList<ArtikalOO> artikalOOList = new ArrayList<>();
        List<Artikal> artikalList = artikalDAO.selectBy(new Artikal(null, naziv, null, null, null, null));
        for (Artikal artikal : artikalList) {
            artikalOOList.add(konvertujUOO(artikal));
        }
        return artikalOOList;
    }
    
    public static ArrayList<ArtikalOO> preuzmiPoKoliciniNaStanju(Integer kolicinaNaStanju) {
        ArrayList<ArtikalOO> artikalOOList = new ArrayList<>();
        List<Artikal> artikalList = artikalDAO.selectBy(new Artikal(null, null, kolicinaNaStanju, null, null, null));
        for (Artikal artikal : artikalList) {
            artikalOOList.add(konvertujUOO(artikal));
        }
        return artikalOOList;
    }
    
    public static ArrayList<ArtikalOO> preuzmiPoBarkodu(String barkod) {
        ArrayList<ArtikalOO> artikalOOList = new ArrayList<>();
        List<Artikal> artikalList = artikalDAO.selectBy(new Artikal(null, null, null, barkod, null, null));
        for (Artikal artikal : artikalList) {
            artikalOOList.add(konvertujUOO(artikal));
        }
        return artikalOOList;
    }
    
    public static ArrayList<ArtikalOO> preuzmiPoTipu(String tip) {
        ArrayList<ArtikalOO> artikalOOList = new ArrayList<>();
        List<Artikal> artikalList = artikalDAO.selectBy(new Artikal(null, null, null, null, tip, null));
        for (Artikal artikal : artikalList) {
            artikalOOList.add(konvertujUOO(artikal));
        }
        return artikalOOList;
    }
    
    public static ArrayList<ArtikalOO> preuzmiPoCijeni(Double cijena) {
        ArrayList<ArtikalOO> artikalOOList = new ArrayList<>();
        List<Artikal> artikalList = artikalDAO.selectBy(new Artikal(null, null, null, null, null, cijena));
        for (Artikal artikal : artikalList) {
            artikalOOList.add(konvertujUOO(artikal));
        }
        return artikalOOList;
    }

    public static void unesi(ArtikalOO artikalOO){
        artikalDAO.insert(konvertujUOV(artikalOO));
    }
    
    public static void izmijeni(ArtikalOO artikalOO) {
        artikalDAO.update(konvertujUOV(artikalOO));
    }

    public static void obrisi(Integer artikalId) {
        artikalDAO.delete(artikalId);
    }

    private static ArtikalOO konvertujUOO(Artikal artikal) {
        return new ArtikalOO(
                artikal.getArtikalId(),
                artikal.getNaziv(),
                artikal.getKolicinaNaStanju(),
                artikal.getBarkod(),
                artikal.getTip(),
                artikal.getCijena()
        );
    }

    private static Artikal konvertujUOV(ArtikalOO artikalOO) {
        return new Artikal(
                artikalOO.getArtikalId(),
                artikalOO.getNaziv(),
                artikalOO.getKolicinaNaStanju(),
                artikalOO.getBarkod(),
                artikalOO.getTip(),
                artikalOO.getCijena()
        );
    }

}
