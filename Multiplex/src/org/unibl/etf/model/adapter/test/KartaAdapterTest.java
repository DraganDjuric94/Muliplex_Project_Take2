/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter.test;

import java.util.Date;
import org.unibl.etf.model.adapter.KartaAdapter;
import org.unibl.etf.model.adapter.ProjekcijaAdapter;
import org.unibl.etf.model.adapter.SjedisteAdapter;
import org.unibl.etf.model.domain.oo.KartaOO;

/**
 *
 * @author Aleksandar
 */
public class KartaAdapterTest {
    public static void preuzmiSveTest(){
        System.out.println("KartaAdapter: preuzmiSveTest");
        System.out.println("----------------------------");
        TestUtil.printAll(KartaAdapter.preuzmiSve());
    }
    
    public static void preuzmiPoIdTest(){
        System.out.println("KartaAdapter: preuzmiPoId test");
        System.out.println("----------------------------");
        System.out.println(KartaAdapter.preuzmiPoId(1));
    }
    
    public static void preuzmiPoProjekcijaIdTest(){
        System.out.println("KartaAdapter: preuzmiPoProjekcijaId test");
        System.out.println("----------------------------------------");
        System.out.println(KartaAdapter.preuzmiPoProjekcijaId(1));
    }
    
    public static void preuzmiPoSjedisteIdTest(){
        System.out.println("KartaAdapter: preuzmiPoSjedisteId test");
        System.out.println("----------------------------------------");
        System.out.println(KartaAdapter.preuzmiPoSjedisteId(1));
    }
    
    public static void unesiTest(){
        System.out.println("KartaAdapter: unesi test");
        System.out.println("----------------------------------------");
        KartaOO kartaOO = new KartaOO(
               null,
                ProjekcijaAdapter.preuzmiPoId(2),
                SjedisteAdapter.preuzmiPoId(1),
                new Date(),
                null,
                true
        );
        
        KartaAdapter.unesi(kartaOO);
        
        System.out.println(kartaOO);
    }
    
    public static void izmijeniTest(){
        System.out.println("KartaAdapter: izmijeni test");
        System.out.println("----------------------------------------");
        KartaOO kartaOO = new KartaOO(
                3,
                ProjekcijaAdapter.preuzmiPoId(1),
                SjedisteAdapter.preuzmiPoId(1),
                new Date(),
                null,
                false
        );
        
        KartaAdapter.izmijeni(kartaOO);
    }
    
    public static void obrisiTest(){
        System.out.println("KartaAdapter: obrisi test");
        System.out.println("----------------------------------------");
        KartaAdapter.obrisi(3);
    }
    
    
}
