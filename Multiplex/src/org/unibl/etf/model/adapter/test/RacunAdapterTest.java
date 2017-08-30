/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.unibl.etf.model.adapter.ArtikalAdapter;
import org.unibl.etf.model.adapter.RacunAdapter;
import org.unibl.etf.model.domain.oo.ArtikalOO;
import org.unibl.etf.model.domain.oo.RacunOO;
import org.unibl.etf.model.domain.oo.StavkaOO;

/**
 *
 * @author Aleksandar
 */
public class RacunAdapterTest {
    
    public static void preuzmiSveTest(){
        System.out.println("RacunAdapter: preuzmiSve test");
        System.out.println("-----------------------------");
        TestUtil.printAll(RacunAdapter.preuzmiSve());
    }
    
    public static void preuzmiPoIdTest(){
        System.out.println("RacunAdapter: preuzmiPoId test");
        System.out.println("-----------------------------");
        System.out.println(RacunAdapter.preuzmiPoId(1));
    }
    
    public static void unesiTest(){
        System.out.println("RacunAdapter: unesi test");
        System.out.println("------------------------------");
        ArtikalOO artikal1 = new ArtikalOO(
                null,
                "Pepsi",
                150,
                "123321",
                "Pice",
                1.0
        );
        
        ArtikalOO artikal2 = new ArtikalOO(
                null,
                "Kokice",
                150,
                "123341",
                "Hrana",
                2.0
        );
        
        ArtikalAdapter.unesi(artikal1);
        ArtikalAdapter.unesi(artikal2);
        
        List<StavkaOO> stavke = new ArrayList<>();
        stavke.add(new StavkaOO(
            null, 2, 4.0, artikal2
        ));
        
        stavke.add(new StavkaOO(
            null, 3, 3.0, artikal1    
        ));
        
        RacunOO racunOO = new RacunOO(
                null,
                new Date(),
                7.0,
                stavke
        );
        
        RacunAdapter.unesi(racunOO);   
    }
    
    public static void izmijeniTest(){
        System.out.println("RacunAdapter: izmijeni test");
        System.out.println("------------------------------");
        //Povucimo prvi racun iz baze
        RacunOO racunOO = RacunAdapter.preuzmiPoId(1);
        //Obrisimo jednu stavku
        racunOO.getStavke().remove(0);
        
        RacunAdapter.izmijeni(racunOO);
        
        System.out.println(racunOO);
    }
    
    public static void obrisiTest(){
        System.out.println("RacunAdapter: obrisi test");
        System.out.println("------------------------------");
        RacunAdapter.obrisi(5);
    }
    
    
}
