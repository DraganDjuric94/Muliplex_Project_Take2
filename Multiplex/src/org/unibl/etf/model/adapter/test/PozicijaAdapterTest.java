/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter.test;

import java.util.Date;
import org.unibl.etf.model.adapter.PozicijaAdapter;
import org.unibl.etf.model.domain.oo.PozicijaOO;

/**
 *
 * @author Aleksandar
 */
public class PozicijaAdapterTest {
    
    public static void preuzmiSveTest(){
        System.out.println("PozicijaAdapter: preuzmiSve test");
        System.out.println("-------------------------------");
        TestUtil.printAll(PozicijaAdapter.preuzmiSve());
    }
    
    public static void preuzmiPoIdTest(){
        System.out.println("PozicijaAdapter: preuzmiPoId test");
        System.out.println("-------------------------------");
        System.out.println(PozicijaAdapter.preuzmiPoId(1));
    }
    
    public static void preuzmiPoNazivuTest(){
        System.out.println("PozicijaAdapter: preuzmiPoNazivu test");
        System.out.println("-------------------------------------");
        TestUtil.printAll(PozicijaAdapter.preuzmiPoNazivu("Prodavac"));
    }
    
    public static void unesiTest(){
        System.out.println("PozicijaAdapter: unesi test");
        System.out.println("-------------------------------------");
        PozicijaOO pozicijaOO = new PozicijaOO(
                null,
                "Kinooperater",
                new Date(),
                null
        );
        
        PozicijaAdapter.unesi(pozicijaOO);
        
        System.out.println(pozicijaOO);
    }
    
    public static void izmijeniTest(){
        System.out.println("PozicijaAdapter: izmijeni test");
        System.out.println("-------------------------------------");
        PozicijaOO pozicijaOO = new PozicijaOO(
                6,
                "Cistacica",
                new Date(),
                null
        );
        PozicijaAdapter.izmijeni(pozicijaOO);
    }
    
    public static void obrisiTest(){
        System.out.println("PozicijaAdapter: obrisi test");
        System.out.println("-------------------------------------");
        PozicijaAdapter.obrisi(6);
    }
    
    
}
