/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter.test;

import java.sql.Array;
import java.util.ArrayList;
import org.unibl.etf.model.adapter.SalaAdapter;
import org.unibl.etf.model.domain.oo.SalaOO;
import org.unibl.etf.model.domain.oo.SjedisteOO;

/**
 *
 * @author Aleksandar
 */
public class SalaAdapterTest {
    
    public static void preuzmiSveTest(){
        System.out.println("SalaAdapter: preuzmiSve test");
        System.out.println("----------------------------");
        TestUtil.printAll(SalaAdapter.preuzmiSve());
    }
    
    public static void preuzmiPoIdTest(){
        System.out.println("SalaAdapter: preuzmiSve test");
        System.out.println("----------------------------");
        System.out.println(SalaAdapter.preuzmiPoId(1));
    }
    
    public static void unesiTest(){
        System.out.println("SalaAdapter: unesi test");
        System.out.println("----------------------------");
        ArrayList<SjedisteOO> sjedista = new ArrayList<>();
        sjedista.add(
                new SjedisteOO(
                        null, 1, 1
                )
        );
        
        sjedista.add(
                new SjedisteOO(
                        null, 1, 2
                )
        );
        
        SalaOO salaOO = new SalaOO(
            null, 1, 2, sjedista
        );
        
        SalaAdapter.unesi(salaOO);
        
        System.out.println(salaOO);
    }
    
    public static void izmijeniTest(){
        System.out.println("SalaAdapter: izmijeni test");
        System.out.println("----------------------------");
        
        SalaOO salaOO = SalaAdapter.preuzmiPoId(4);
        salaOO.setBrojKolona(3);
        salaOO.getSjedista().add(new SjedisteOO(null, 1, 3));
        
        SalaAdapter.izmijeni(salaOO);
        
        
    }
    
    public static void obrisiTest(){
        System.out.println("SalaAdapter: izmijeni test");
        System.out.println("----------------------------");
        SalaAdapter.obrisi(4);
    }
    
    
    
}
