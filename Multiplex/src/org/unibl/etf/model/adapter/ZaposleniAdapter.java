/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.unibl.etf.model.dao.ZaposleniDAO;
import org.unibl.etf.model.dao.ZaposleniPozicijaDAO;
import org.unibl.etf.model.dao.mysql.MySQLDAOFactory;
import org.unibl.etf.model.domain.Zaposleni;
import org.unibl.etf.model.domain.ZaposleniPozicija;
import org.unibl.etf.model.domain.oo.PozicijaOO;
import org.unibl.etf.model.domain.oo.ZaposleniOO;

/**
 *
 * @author Aleksandar
 */
public class ZaposleniAdapter {
    
    private static ZaposleniDAO zaposleniDAO = MySQLDAOFactory.getInstance().getZaposleniDAO();
    private static ZaposleniPozicijaDAO zaposleniPozicijaDAO = MySQLDAOFactory.getInstance().getZaposleniPozicijaDAO();
    
    public static ArrayList<ZaposleniOO> preuzmiSve(){
      List<Zaposleni> zaposleniList = zaposleniDAO.selectAll();
        ArrayList<ZaposleniOO> zaposleniOOList = new ArrayList<>();
        for (Zaposleni zaposleni : zaposleniList) {
            zaposleniOOList.add(konvertujUOO(zaposleni));
        }
        return zaposleniOOList;  
    }
    
    public static ZaposleniOO preuzmiPoId(Integer zaposleniId){
        ZaposleniOO zaposleniOO = null;
        List<Zaposleni> zaposleniList = zaposleniDAO.selectBy(new Zaposleni(zaposleniId, null, null, null, null, null, null, null));
        if (1 == zaposleniList.size()) {
            zaposleniOO = konvertujUOO(zaposleniList.get(0));
        }
        return zaposleniOO;
    }
    
    public static ArrayList<ZaposleniOO> preuzmiPoImenu(String ime){
        ArrayList<ZaposleniOO> zaposleniOOList = new ArrayList<>();
        List<Zaposleni> zaposleniList = zaposleniDAO.selectBy(new Zaposleni(null, ime, null, null, null, null, null, null));
        for (Zaposleni zaposleni : zaposleniList) {
            zaposleniOOList.add(konvertujUOO(zaposleni));
        }
        return zaposleniOOList;
    }
    
    public static ArrayList<ZaposleniOO> preuzmiPoPrezimenu(String prezime){
        ArrayList<ZaposleniOO> zaposleniOOList = new ArrayList<>();
        List<Zaposleni> zaposleniList = zaposleniDAO.selectBy(new Zaposleni(null, null, prezime, null, null, null, null, null));
        for (Zaposleni zaposleni : zaposleniList) {
            zaposleniOOList.add(konvertujUOO(zaposleni));
        }
        return zaposleniOOList;
    }
    
    public static ArrayList<ZaposleniOO> preuzmiPoJMBG(String jmbg){
        ArrayList<ZaposleniOO> zaposleniOOList = new ArrayList<>();
        List<Zaposleni> zaposleniList = zaposleniDAO.selectBy(new Zaposleni(null, null, null, jmbg, null, null, null, null));
        for (Zaposleni zaposleni : zaposleniList) {
            zaposleniOOList.add(konvertujUOO(zaposleni));
        }
        return zaposleniOOList;
    }
    
    public static ArrayList<ZaposleniOO> preuzmiPoAktivnosti(Boolean aktivan){
        ArrayList<ZaposleniOO> zaposleniOOList = new ArrayList<>();
        List<Zaposleni> zaposleniList = zaposleniDAO.selectBy(new Zaposleni(null, null, null, null, aktivan, null, null, null));
        for (Zaposleni zaposleni : zaposleniList) {
            zaposleniOOList.add(konvertujUOO(zaposleni));
        }
        return zaposleniOOList;
    }
    
    public static ArrayList<ZaposleniOO> preuzmiPoKorisnickomImenu(String korisnickoIme){
        ArrayList<ZaposleniOO> zaposleniOOList = new ArrayList<>();
        List<Zaposleni> zaposleniList = zaposleniDAO.selectBy(new Zaposleni(null, null, null, null, null, null, korisnickoIme, null));
        for (Zaposleni zaposleni : zaposleniList) {
            zaposleniOOList.add(konvertujUOO(zaposleni));
        }
        return zaposleniOOList;
    }
    
    public static ArrayList<ZaposleniOO> preuzmiPoLozinki(String lozinka){
        ArrayList<ZaposleniOO> zaposleniOOList = new ArrayList<>();
        List<Zaposleni> zaposleniList = zaposleniDAO.selectBy(new Zaposleni(null, null, null, null, null, null, null, lozinka));
        for (Zaposleni zaposleni : zaposleniList) {
            zaposleniOOList.add(konvertujUOO(zaposleni));
        }
        return zaposleniOOList;
    }
    
    public static void unesi(ZaposleniOO zaposleniOO){
        PozicijaOO pozicijaOO = zaposleniOO.getPozicija();
        
        if((null == pozicijaOO.getPozicijaId()) || (null == PozicijaAdapter.preuzmiPoId(pozicijaOO.getPozicijaId()))){
            PozicijaAdapter.unesi(pozicijaOO);
        }
        
        Zaposleni zaposleni = konvertujUOV(zaposleniOO);
        zaposleniDAO.insert(zaposleni);
        zaposleniOO.setZaposleniId(zaposleni.getZaposleniId());
        
        zaposleniPozicijaDAO.insert(new ZaposleniPozicija(zaposleniOO.getZaposleniId(), zaposleniOO.getPozicija().getPozicijaId()));
    }
    
    public static void izmijeni(ZaposleniOO zaposleniOO){
        PozicijaOO zadnjaPozicijaZaposlenogOO = vratiTrenutnuPozicijuZaposlenog(zaposleniOO.getZaposleniId());
        PozicijaOO trenutnaPozicijaZaposlenogOO = zaposleniOO.getPozicija();
        if(!zadnjaPozicijaZaposlenogOO.getPozicijaId().equals(trenutnaPozicijaZaposlenogOO.getPozicijaId())){
            zaposleniPozicijaDAO.insert(new ZaposleniPozicija(zaposleniOO.getZaposleniId(), trenutnaPozicijaZaposlenogOO.getPozicijaId()));
        }
        zaposleniDAO.update(konvertujUOV(zaposleniOO));
        
    }
    
    public static void obrisi(Integer zaposleniId){
        List<ZaposleniPozicija> zaposleniPozicijaList = zaposleniPozicijaDAO.selectBy(new ZaposleniPozicija(zaposleniId, null));     
        
        for(ZaposleniPozicija zaposleniPozicija: zaposleniPozicijaList){
            zaposleniPozicijaDAO.delete(zaposleniId, zaposleniPozicija.getPozicijaId());
        }
        
        for(ZaposleniPozicija zaposleniPozicija: zaposleniPozicijaList){
            PozicijaAdapter.obrisi(zaposleniPozicija.getPozicijaId());
        }
        
        zaposleniDAO.delete(zaposleniId);
    }   
    
    
    private static ZaposleniOO konvertujUOO(Zaposleni zaposleni){
        PozicijaOO trenutnaPozicijaOO = vratiTrenutnuPozicijuZaposlenog(zaposleni.getZaposleniId());
        return new ZaposleniOO(
                zaposleni.getZaposleniId(),
                zaposleni.getIme(),
                zaposleni.getPrezime(),
                zaposleni.getJmbg(),
                zaposleni.getKorisnickoIme(),
                zaposleni.getLozinka(),
                zaposleni.getPlata(),
                zaposleni.getAktivan(),
                trenutnaPozicijaOO              
        );
    }
    
    private static Zaposleni konvertujUOV(ZaposleniOO zaposleniOO){
        return new Zaposleni(
                zaposleniOO.getZaposleniId(),
                zaposleniOO.getIme(),
                zaposleniOO.getPrezime(),
                zaposleniOO.getJmbg(),
                zaposleniOO.getAktivan(),
                zaposleniOO.getPlata(),
                zaposleniOO.getKorisnickoIme(),
                zaposleniOO.getLozinka()                      
        );
    }
    
    private static PozicijaOO vratiTrenutnuPozicijuZaposlenog(Integer zaposleniId){
        PozicijaOO trenutnaPozicija = null;
        ArrayList<PozicijaOO> pozicijaOOList = new ArrayList<>();
        List<ZaposleniPozicija> zaposleniPozicijaList = zaposleniPozicijaDAO.selectBy(new ZaposleniPozicija(zaposleniId, null));
        
        for(ZaposleniPozicija zaposleniPozicija: zaposleniPozicijaList){
            pozicijaOOList.add(PozicijaAdapter.preuzmiPoId(zaposleniPozicija.getPozicijaId()));
        }
        
        for(PozicijaOO pozicijaOO: pozicijaOOList){
            if(null == pozicijaOO.getDatumDo()){
                trenutnaPozicija = pozicijaOO;
                break;
            }
        }
        return trenutnaPozicija;
    }
    
}
