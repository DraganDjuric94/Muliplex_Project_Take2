/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter;

import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.model.dao.PonudaZaFilmDAO;
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.PonudaZaFilm;
import org.unibl.etf.model.domain.oo.PonudaZaFilmOO;

/**
 *
 * @author juhu
 */
public class PonudaZaFilmAdapter {
    
    private static PonudaZaFilmDAO ponudaZaFilmDAO = MySQLDAOFactory.getInstance().getPonudaZaFilmDAO();
    
    public static ArrayList<PonudaZaFilmOO> preuzmiSve(){
        List<PonudaZaFilm> ponudaZaFilmList = ponudaZaFilmDAO.selectAll();
        ArrayList<PonudaZaFilmOO> ponudaZaFilmOOList = new ArrayList<>();
        for (PonudaZaFilm ponudaZaFilm : ponudaZaFilmList) {
            ponudaZaFilmOOList.add(konvertujUOO(ponudaZaFilm));
        }
        return ponudaZaFilmOOList;
    }
    
    public static PonudaZaFilmOO preuzmiPoId(Integer ponudaZaFilmId){
        PonudaZaFilmOO ponudaZaFilmOO = null;
        List<PonudaZaFilm> ponudaZaFilmList = ponudaZaFilmDAO.selectBy(new PonudaZaFilm(ponudaZaFilmId, null, null, null));
        PonudaZaFilm ponudaZaFilm = ponudaZaFilmList.get(0);
        if (null != ponudaZaFilm) {
            ponudaZaFilmOO = konvertujUOO(ponudaZaFilm);
        }
        return ponudaZaFilmOO;
    }
    
    public static PonudaZaFilmOO preuzmiPoFilmId(Integer filmId){
        PonudaZaFilmOO ponudaZaFilmOO = null;
        List<PonudaZaFilm> ponudaZaFilmList = ponudaZaFilmDAO.selectBy(new PonudaZaFilm(null, filmId, null, null));
        PonudaZaFilm ponudaZaFilm = ponudaZaFilmList.get(0);
        if (null != ponudaZaFilm) {
            ponudaZaFilmOO = konvertujUOO(ponudaZaFilm);
        }
        return ponudaZaFilmOO;
    }
    
    public static void unesi(PonudaZaFilmOO ponudaZaFilmOO){
        ponudaZaFilmDAO.insert(konvertujUOV(ponudaZaFilmOO));
    }
    
    public static void izmijeni(PonudaZaFilmOO ponudaZaFilmOO){
        ponudaZaFilmDAO.update(konvertujUOV(ponudaZaFilmOO));
    }
    
    public static void obrisi(Integer ponudaZaFilmId){
        ponudaZaFilmDAO.delete(ponudaZaFilmId);
    }
    
    private static PonudaZaFilmOO konvertujUOO(PonudaZaFilm ponudaZaFilm){
        return new PonudaZaFilmOO(
                ponudaZaFilm.getPonudaZaFilmId(),
                ponudaZaFilm.getPonudaZaFilmId(),
                ponudaZaFilm.getOpis(),
                ponudaZaFilm.getDatum()
        );
    }
    
    private static PonudaZaFilm konvertujUOV(PonudaZaFilmOO ponudaZaFilmOO){
        java.sql.Date datumSQL = new java.sql.Date(ponudaZaFilmOO.getDatum().getTime());
        return new PonudaZaFilm(
                ponudaZaFilmOO.getPonudaZaFilmId(),
                ponudaZaFilmOO.getFilmId(),
                ponudaZaFilmOO.getOpis(),
                datumSQL
        );
    }
    
}
