/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter.test;

import org.unibl.etf.model.adapter.SjedisteAdapter;
import org.unibl.etf.model.domain.oo.SjedisteOO;

/**
 *
 * @author Aleksandar
 */
public class SjedisteAdapterTest {
    public static void preuzmiSveTest(){
        System.out.println("SjedisteAdapter: preuzmiSve test");
        System.out.println("--------------------------------");
        TestUtil.printAll(SjedisteAdapter.preuzmiSve());
    }
    
    public static void preuzmiPoIdTest(){
        System.out.println("SjedisteAdapter: preuzmiPoId test");
        System.out.println("---------------------------------");
        System.out.println(SjedisteAdapter.preuzmiPoId(1));
    }
    
    public static void preuzmiPoSalaIdTest(){
        System.out.println("SjedisteAdapter: preuzmiPoSalaId test");
        System.out.println("-------------------------------------");
        TestUtil.printAll(SjedisteAdapter.preuzmiPoSalaId(1));
    }
    
    public static void unesiTest(){
        System.out.println("SjedisteAdapter: unesi test");
        System.out.println("-------------------------------------");
        SjedisteOO sjedisteOO = new SjedisteOO(
                null,
                1,
                1
        );
        
        SjedisteAdapter.unesi(2, sjedisteOO);
        System.out.println(sjedisteOO);
        
    }
    
    public static void izmijeniTest(){
        System.out.println("SjedisteAdapter: izmijeni test");
        System.out.println("-------------------------------------");
        SjedisteOO sjedisteOO = new SjedisteOO(
                4,
                1,
                3
        );
        
        SjedisteAdapter.izmijeni(2, sjedisteOO);
    }
    
    public static void obrisiTest(){
        System.out.println("SjedisteAdapter: obrisi test");
        System.out.println("-------------------------------------");
        SjedisteAdapter.obrisi(4);
    }
    
    
}
