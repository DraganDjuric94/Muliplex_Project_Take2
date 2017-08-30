/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.model.dao.ProjekcijaDAO;
import org.unibl.etf.model.dao.ProjekcijaSalaDAO;
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.Projekcija;
import org.unibl.etf.model.domain.ProjekcijaSala;
import org.unibl.etf.model.domain.oo.FilmOO;
import org.unibl.etf.model.domain.oo.KartaOO;
import org.unibl.etf.model.domain.oo.ProjekcijaOO;
import org.unibl.etf.model.domain.oo.SalaOO;

/**
 *
 * @author juhu, Aleksandar
 */

public class ProjekcijaAdapter {
    
    private static final ProjekcijaDAO projekcijaDAO = MySQLDAOFactory.getInstance().getProjekcijaDAO();
    private static final ProjekcijaSalaDAO projekcijaSalaDAO = MySQLDAOFactory.getInstance().getProjekcijaSalaDAO();
    
    
    public static ArrayList<ProjekcijaOO> preuzmiPoNazivuFilma(String text){
        ArrayList<ProjekcijaOO> retVal = new ArrayList<>();
        
        ArrayList<ProjekcijaOO> temp = preuzmiSve();
        for(ProjekcijaOO pr : temp){
            if(pr.getFilm().getNaziv().toLowerCase().startsWith(text.toLowerCase())){
                retVal.add(pr);
            }
        }
 
        return retVal;
    }
    
    public static ArrayList<ProjekcijaOO> preuzmiSve(){
        
        ArrayList<ProjekcijaOO> projekcije = new ArrayList<>();
        
        List<Projekcija> projekcijeOv = projekcijaDAO.selectAll();
        
        System.out.println(projekcijeOv.size());
        
        for(Projekcija p : projekcijeOv){
            projekcije.add(konvertujUOO(p.getFilmId(), p));
        }
        
        return projekcije;
    }
    
    public static ProjekcijaOO preuzmiPoId(Integer projekcijaId){
        
        ProjekcijaOO retVal = null;
        
        List<Projekcija> tempLst = projekcijaDAO.selectBy(new Projekcija(projekcijaId, null, null, null));
        Projekcija tempProj = null;
        if(tempLst.size() == 1){
            tempProj = tempLst.get(0);
            retVal = konvertujUOO(tempProj.getFilmId(), tempProj);
        }
        
        return retVal;
    }
    
    
    public static String izmijeni(ProjekcijaOO projekcija, boolean OBRISI_KARTE){
        //U slucaju promijene sale, karte se brisu samo ako je setovan obrisi karte marker
        //Ukoliko: postoje karte za projekciju, mijenja se sala i marker nije setovan, 
        //izmijena nece biti izvrsena
        String message = "Uspjesno izmijenjena projekcija";
        
        ProjekcijaOO trenutna = preuzmiPoId(projekcija.getProjekcijaId());
        
        Projekcija proj = konvertujUOV(projekcija);

        if(trenutna.getSala().getSalaId() != projekcija.getSala().getSalaId()){
           ArrayList<KartaOO> karteZaProj = KartaAdapter.preuzmiPoProjekcijaId(projekcija.getProjekcijaId());
           if(karteZaProj.size() > 0 && OBRISI_KARTE){
               for(KartaOO k : karteZaProj){
                   KartaAdapter.obrisi(k.getKartaId());
               }
               ProjekcijaSala projSal = null;
               List<ProjekcijaSala> projSalLST = projekcijaSalaDAO.selectBy(new ProjekcijaSala(projekcija.getProjekcijaId(), null));
               if(projSalLST.size() == 1){
                   projSal = projSalLST.get(0);
                   projekcijaSalaDAO.delete(projSal.getProjekcijaId(), projSal.getSalaId());
                   ProjekcijaSala noviProjSal = new ProjekcijaSala(projekcija.getProjekcijaId(), projekcija.getSala().getSalaId());
                   projekcijaSalaDAO.insert(noviProjSal);
                   projekcijaDAO.update(proj);
                   return message;
               }
           }else if (karteZaProj.size() > 0){
               message = "Nije moguce izmijeniti salu za projekciju. Postoje neobrisane karte...";
           }
        }else{
            projekcijaDAO.update(proj);
        }
        
        return message;
    }
    
    public static void obrisi(Integer id){
        
        List<KartaOO> karteZaProjekciju = KartaAdapter.preuzmiPoProjekcijaId(id);
        for(KartaOO karta : karteZaProjekciju){
            KartaAdapter.obrisi(karta.getKartaId());
        }
        
        List<ProjekcijaSala> projekcijaSalaList = projekcijaSalaDAO.selectBy(new ProjekcijaSala(id, null));
        
        for(ProjekcijaSala ps : projekcijaSalaList){
            projekcijaSalaDAO.delete(id, ps.getSalaId());
        }
        
        projekcijaDAO.delete(id);
        
    }
    
    public static void dodaj(ProjekcijaOO novaProjekcija){
        
        Projekcija projOv = konvertujUOV(novaProjekcija);
        
        SalaOO sala = novaProjekcija.getSala();
        ProjekcijaSala projekcijaSala = new ProjekcijaSala(novaProjekcija.getProjekcijaId(), sala.getSalaId());
        projekcijaSalaDAO.insert(projekcijaSala);
        
        projekcijaDAO.insert(projOv);
    }
    
    private static  ProjekcijaOO konvertujUOO(Integer filmId, Projekcija projekcija){
        
        ProjekcijaOO retVal = new ProjekcijaOO();
        
        retVal.setProjekcijaId(projekcija.getProjekcijaId());
        retVal.setFilm(FilmAdapter.preuzmiPoId(filmId));
        retVal.setDatumVrijeme(projekcija.getDatumVrijeme());
        retVal.setCijenaKarte(projekcija.getCijenaKarte());

       
        ProjekcijaSala projSal = null;
        
        List<ProjekcijaSala> projSalLST = projekcijaSalaDAO.selectBy(new ProjekcijaSala(projekcija.getProjekcijaId(), null));
        
        if(projSalLST.size() == 1){
            projSal = projSalLST.get(0);
        }
        
        SalaOO sala = SalaAdapter.preuzmiPoId(projSal.getSalaId());
        retVal.setSala(sala);
        
        /*
        ArrayList<KartaOO> karteZaProjekciju = KartaAdapter.preuzmiPoProjekcijaId(projekcija.getProjekcijaId());
        ArrayList<SjedisteOO> zauzetaSjedista = new ArrayList<>();
        
        for(KartaOO karta : karteZaProjekciju){
            zauzetaSjedista.add(karta.getSjediste());
        }
        
        retVal.setZauzetaSjedista(zauzetaSjedista);
        */
        
        return retVal;
        
    }
    
    private static  Projekcija konvertujUOV(ProjekcijaOO projekcija){
       
        return new Projekcija(
                projekcija.getProjekcijaId(),
                projekcija.getFilm().getFilmId(),
                new Timestamp(projekcija.getDatumVrijeme().getTime()),
                projekcija.getCijenaKarte()
        );
        
    }
    
}
