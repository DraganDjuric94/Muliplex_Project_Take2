/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter;

import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.model.dao.FilmDAO;
import org.unibl.etf.model.dao.FilmZanrDAO;
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.Film;
import org.unibl.etf.model.domain.FilmZanr;
import org.unibl.etf.model.domain.oo.FilmOO;
import org.unibl.etf.model.domain.oo.PonudaZaFilmOO;
import org.unibl.etf.model.domain.oo.ProjekcijaOO;
import org.unibl.etf.model.domain.oo.ZanrOO;

/**
 *
 * @author juhu
 */
public class FilmAdapter {
    
    private static FilmDAO filmDAO = MySQLDAOFactory.getInstance().getFilmDAO();
    private static FilmZanrDAO filmZanrDAO = MySQLDAOFactory.getInstance().getFilmZanrDAO();
    
    public static ArrayList<FilmOO> preuzmiSve(){
        List<Film> filmList = filmDAO.selectAll();
        ArrayList<FilmOO> filmOOList = new ArrayList<>();
        for (Film film : filmList) {
            filmOOList.add(konvertujUOO(film));
        }
        return filmOOList;
    }
    
    public static FilmOO preuzmiPoId(Integer filmId){
        FilmOO filmOO = null;
        List<Film> filmList = filmDAO.selectBy(new Film(filmId, null, null, null, null));
        if (1 == filmList.size()) {
            filmOO = konvertujUOO(filmList.get(0));
        }
        return filmOO;
    }
    
    public static ArrayList<FilmOO> preuzmiPoNazivu(String naziv){
        ArrayList<FilmOO> filmOOList = new ArrayList<>();
        List<Film> filmList = filmDAO.selectBy(new Film(null, naziv, null, null, null));
        for(Film film: filmList){
            filmOOList.add(konvertujUOO(film));
        }
        return filmOOList;
    }
    
    public static void unesi(FilmOO filmOO){
        Film film = konvertujUOV(filmOO);
        filmDAO.insert(film);
        filmOO.setFilmId(film.getFilmId());
        
        for(ZanrOO zanrOO: filmOO.getZanrovi()){
            filmZanrDAO.insert(new FilmZanr(filmOO.getFilmId(), zanrOO.getZanrId()));
        } 
    }
    
    public static void izmijeni(FilmOO filmOO){
   
       Film film = konvertujUOV(filmOO);
       
       List<FilmZanr> trenutniZanrovi = filmZanrDAO.selectBy(new FilmZanr(filmOO.getFilmId(), null));
       
       for(FilmZanr fz : trenutniZanrovi){
           filmZanrDAO.delete(fz.getFilmId(), fz.getZanrId());
       }
       
       for(ZanrOO z : filmOO.getZanrovi()){
           filmZanrDAO.insert(new FilmZanr(filmOO.getFilmId(), z.getZanrId()));
       }
       
       filmDAO.update(film);
       
    }
    
    public static void obrisi(Integer filmId){
   
        List<ProjekcijaOO> projekcijeZaFilm = ProjekcijaAdapter.preuzmiSve();
        
        for(ProjekcijaOO proj : projekcijeZaFilm){
            if(proj.getFilm().getFilmId() == filmId){
                ProjekcijaAdapter.obrisi(proj.getProjekcijaId());
            }
        }
        
        List<PonudaZaFilmOO> ponudaZaFilmOOList = PonudaZaFilmAdapter.preuzmiPoFilmId(filmId);
        for(PonudaZaFilmOO ponudaZaFilmOO: ponudaZaFilmOOList){
           PonudaZaFilmAdapter.obrisi(ponudaZaFilmOO.getPonudaZaFilmId());
        }
        
        List<FilmZanr> filmZanrList = filmZanrDAO.selectBy(new FilmZanr(filmId, null));
        
        for(FilmZanr filmZanr: filmZanrList){
            filmZanrDAO.delete(filmId, filmZanr.getZanrId());
        }
        
        filmDAO.delete(filmId);
    }
    
    private static FilmOO konvertujUOO(Film film){
        
        ArrayList<ZanrOO> zanrOOList = new ArrayList<>();

        List<FilmZanr> filmZanrList = filmZanrDAO.selectBy(new FilmZanr(film.getFilmId(), null));
        for(FilmZanr filmZanr: filmZanrList){
            zanrOOList.add(ZanrAdapter.preuzmiPoId(filmZanr.getZanrId()));
        }
        return new FilmOO(
                film.getFilmId(),
                film.getNaziv(),
                film.getTrajanje(),
                film.getOpis(),
                film.getSlika(),
                zanrOOList
        );
        
        
    }
    
    private static Film konvertujUOV(FilmOO filmOO){
        return new Film(
                filmOO.getFilmId(),
                filmOO.getNaziv(),
                filmOO.getTrajanje(),
                filmOO.getOpis(),
                filmOO.getSlika()
        );
    }
    
}
