/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter.test;

import org.unibl.etf.model.adapter.ArtikalAdapter;
import org.unibl.etf.model.domain.oo.ArtikalOO;

/**
 *
 * @author Aleksandar
 */
public class ArtikalAdapterTest {
    
    public static void preuzmiSveTest(){
        System.out.println("ArtikalAdapter: preuzmiSveTest");
        System.out.println("------------------------------");
        TestUtil.printAll(ArtikalAdapter.preuzmiSve());
    }
    
    public static void preuzmiPoIdTest(){
        System.out.println("ArtikalAdapter: preuzmiPoIdTest");
        System.out.println("------------------------------");
        System.out.println(ArtikalAdapter.preuzmiPoId(1));
    }
    
    public static void preuzmiPoNazivuTest(){
        System.out.println("ArtikalAdapter: preuzmiPoNazivuTest");
        System.out.println("------------------------------");
        TestUtil.printAll(ArtikalAdapter.preuzmiPoNazivu("Cips"));
    }
    
    public static void preuzmiPoKolicinaNaStanjuTest(){
        System.out.println("ArtikalAdapter: preuzmiPoKolicinaNaStanjuTest");
        System.out.println("------------------------------");
        TestUtil.printAll(ArtikalAdapter.preuzmiPoKoliciniNaStanju(100));
    }
    
    public static void preuzmiPoBarkoduTest(){
        System.out.println("ArtikalAdapter: preuzmiPoBarkoduTest");
        System.out.println("------------------------------");
        TestUtil.printAll(ArtikalAdapter.preuzmiPoBarkodu("1111"));
    }
    
    public static void preuzmiPoTipuTest(){
        System.out.println("ArtikalAdapter: preuzmiPoTipuTest");
        System.out.println("------------------------------");
        TestUtil.printAll(ArtikalAdapter.preuzmiPoTipu("Pice"));
    }
    
    public static void preuzmiPoCijeniTest(){
        System.out.println("ArtikalAdapter: preuzmiPoCijeniTest");
        System.out.println("------------------------------");
        TestUtil.printAll(ArtikalAdapter.preuzmiPoCijeni(2.5));
        
    }
    
    public static void unesiTest(){
        System.out.println("ArtikalAdapter: unesi test");
        System.out.println("------------------------------");
        ArtikalOO artikalOO = new ArtikalOO(
                null,
                "Cupa cups",
                100,
                "3333",
                "Hrana",
                0.5
        );
        ArtikalAdapter.unesi(artikalOO);
        System.out.println(artikalOO);
    }
    
    public static void izmijeniTest(){
        System.out.println("ArtikalAdapter: izmijeni test");
        System.out.println("------------------------------");
        ArtikalOO artikalOO = new ArtikalOO(
                3,
                "Sladoled plazma mmm",
                100,
                "3334",
                "Hrana++",
                1.5
        );
        ArtikalAdapter.izmijeni(artikalOO);
    }
    
    public static void obrisiTest(){
        System.out.println("ArtikalAdapter: obrisi test");
        System.out.println("------------------------------");
        ArtikalAdapter.obrisi(3);
    }
}
