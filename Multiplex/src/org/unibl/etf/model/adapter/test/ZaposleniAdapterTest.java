/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter.test;

import java.util.Date;
import org.unibl.etf.model.adapter.PozicijaAdapter;
import org.unibl.etf.model.adapter.ZaposleniAdapter;
import org.unibl.etf.model.domain.oo.PozicijaOO;
import org.unibl.etf.model.domain.oo.ZaposleniOO;

/**
 *
 * @author Aleksandar
 */
public class ZaposleniAdapterTest {
    public static void preuzmiSveTest(){
        System.out.println("ZaposleniAdapterTest: preuzmiSve test");
        System.out.println("-------------------------------------");
        TestUtil.printAll(ZaposleniAdapter.preuzmiSve());        
    }
    
    public static void preuzmiPoIdTest(){
        System.out.println("ZaposleniAdapterTest: preuzmiPoId test");
        System.out.println("-------------------------------------");
        System.out.println(ZaposleniAdapter.preuzmiPoId(1));
               
    }
    
    public static void preuzmiPoImenu(){
        System.out.println("ZaposleniAdapterTest: preuzmiPoImenu test");
        System.out.println("-------------------------------------");
        TestUtil.printAll(ZaposleniAdapter.preuzmiPoImenu("Dragan"));
    }
    
    public static void preuzmiPoPrezimenu(){
        System.out.println("ZaposleniAdapterTest: preuzmiPoPrezimenu test");
        System.out.println("-------------------------------------");
        TestUtil.printAll(ZaposleniAdapter.preuzmiPoPrezimenu("Vujinovic"));
    }
    
    public static void preuzmiPoJMBGTest(){
        System.out.println("ZaposleniAdapterTest: preuzmiPoJMBG test");
        System.out.println("-------------------------------------");
        TestUtil.printAll(ZaposleniAdapter.preuzmiPoJMBG("1111"));
    }
    
    public static void preuzmiPoAktivnostiTest(){
        System.out.println("ZaposleniAdapterTest: preuzmiPoAktivnosti test");
        System.out.println("-------------------------------------");
        TestUtil.printAll(ZaposleniAdapter.preuzmiPoAktivnosti(true));
    }
    
    public static void preuzmiPoKorisnickomImenuTest(){
        System.out.println("ZaposleniAdapterTest: preuzmiPoKorisnickomImenu test");
        System.out.println("----------------------------------------------------");
        TestUtil.printAll(ZaposleniAdapter.preuzmiPoKorisnickomImenu("1"));
    }
    
    public static void preuzmiPoLozinkiTest(){
        System.out.println("ZaposleniAdapterTest: preuzmiPoLozinki test");
        System.out.println("----------------------------------------------------");
        TestUtil.printAll(ZaposleniAdapter.preuzmiPoLozinki("2"));
    }
    
    public static void unesiTest(){
        System.out.println("ZaposleniAdapterTest: unesi test");
        System.out.println("----------------------------------------------------");
        PozicijaOO pozicijaOO = new PozicijaOO(
                null,
                "WOW",
                new Date(),
                null
        );
        ZaposleniOO zaposleniOO = new ZaposleniOO(
                null,
                "Marko",
                "Beric",
                "Nekad u Septembru",
                "domacica",
                "domacica1",
                2000.0,
                false,
                pozicijaOO
        );
        ZaposleniAdapter.unesi(zaposleniOO);
        System.out.println(zaposleniOO);
    }
    
    public static void izmijeniTest(){
        //KADA SE RADI IZMJENA, PODRAZUMIJEVA SE DA VEC SVE POSTOJI U BAZI
        //NAPOMENA: POZICIJA MORA BITI UNESENA U BAZU PRIJE NEGO STO ZELITE DA IZMJENITE ZAPOSLENOG - AKO ZELITE MIJEJNATI POZICIJU
        //AKO UBACUJETE NOVU POZICIJU, DATUMDO SAMI MORATE AZURIRATI
        
        ZaposleniOO zaposleniOO = ZaposleniAdapter.preuzmiPoId(1);
        System.out.println(zaposleniOO);
        
        PozicijaOO pozicijaTrenutnaOO = zaposleniOO.getPozicija();
        PozicijaOO pozicijaNovaOO = new PozicijaOO(null, "Sanjar", new Date(), null);
        PozicijaAdapter.unesi(pozicijaNovaOO);
        
        //Promjene na zaposlenom
        zaposleniOO.setPrezime("Djuric<3");
        zaposleniOO.setPozicija(pozicijaNovaOO);
        
        //izmjena
        ZaposleniAdapter.izmijeni(zaposleniOO);
        
        //Podesavanje vremenskog perioda stare pozicije na zeljenu vrijednost
        pozicijaTrenutnaOO.setDatumDo(new Date());
        PozicijaAdapter.izmijeni(pozicijaTrenutnaOO); 
        
        //ZASTO SVE OVAKO NAKARADNO - LOSE PROJEKTOVANA BAZA JBG :)
    }
    
    public static void obrisiTest(){
        System.out.println("ZaposleniAdapterTest: obrisi test");
        System.out.println("----------------------------------------------------");
        ZaposleniAdapter.obrisi(10);
    }
    
}
