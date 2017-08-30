/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter.test;

import org.unibl.etf.model.adapter.OpremaAdapter;
import org.unibl.etf.model.domain.oo.OpremaOO;

/**
 *
 * @author Aleksandar
 */
public class OpremaAdapterTest {
    
    public static void preuzmiSveTest(){
        System.out.println("OpremaAdapter: preuzmiSve test");
        System.out.println("------------------------------");
        TestUtil.printAll(OpremaAdapter.preuzmiSve());
    }
    
    public static void preuzmiPoIdTest(){
        System.out.println("OpremaAdapter: preuzmiPoId test");
        System.out.println("------------------------------");
        System.out.println(OpremaAdapter.preuzmiPoId(1));
    }
    
    public static void preuzmiPoBrojuInventaraTest(){
        System.out.println("OpremaAdapter: preuzmiPoBrojuInventara test");
        System.out.println("------------------------------");
        TestUtil.printAll(OpremaAdapter.preuzmiPoBrojuInventara("1ABC"));
    }
    
    public static void preuzmiPoNazivuTest(){
        System.out.println("OpremaAdapter: preuzmiPonazivu test");
        System.out.println("------------------------------");
        TestUtil.printAll(OpremaAdapter.preuzmiPoNazivu("Cannon XDE"));
    }
    
    public static void preuzmiPoIspravnosti(){
        System.out.println("OpremaAdapter: preuzmiPoIspravnosti test");
        System.out.println("------------------------------");
        TestUtil.printAll(OpremaAdapter.preuzmiPoIspravnosti(true));
    }
    
    public static void unesiTest(){
        System.out.println("OpremaAdapter: unesi test");
        System.out.println("------------------------------");
        OpremaOO opremaOO = new OpremaOO(
                null,
                "1122",
                "LG Ultra",
                true
        );
        
        OpremaAdapter.unesi(opremaOO);
        System.out.println(opremaOO);
    }
    
    public static void izmijeniTest(){
        System.out.println("OpremaAdapter: izmijeni test");
        System.out.println("------------------------------");
        OpremaOO opremaOO = new OpremaOO(
                3,
                "1122",
                "LG Ultra Plus",
                false
        );
        OpremaAdapter.izmijeni(opremaOO);   
    }
    
    public static void obrisiTest(){
        System.out.println("OpremaAdapter: obrisi test");
        OpremaAdapter.obrisi(3);
    }
  
}
