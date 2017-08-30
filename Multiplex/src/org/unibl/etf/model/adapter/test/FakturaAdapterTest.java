/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.unibl.etf.model.adapter.FakturaAdapter;
import org.unibl.etf.model.domain.oo.FakturaOO;

/**
 *
 * @author Aleksandar
 */
public class FakturaAdapterTest {
    public static void preuzmiSveTest(){
        System.out.println("FakturaAdapter: preuzmiSve test");
        System.out.println("-------------------------------");
        TestUtil.printAll(FakturaAdapter.preuzmiSve());
    }
    
    public static void preuzmiPoIdTest(){
        System.out.println("FakturaAdapter: preuzmiPoId test");
        System.out.println("-------------------------------");
        System.out.println(FakturaAdapter.preuzmiPoId(1));
    }
    
    public static void preuzmiPoBrojuRacunaTest(){
        System.out.println("FakturaAdapter: preuzmiPoBrojuRacuna test");
        System.out.println("-------------------------------");
        TestUtil.printAll(FakturaAdapter.preuzmiPoBrojuRacuna("11112222"));
    }
    
    public static void preuzmiPoNazivuRobeTest(){
        System.out.println("FakturaAdapter: preuzmiPoNazivuRobe test");
        System.out.println("-------------------------------");
        TestUtil.printAll(FakturaAdapter.preuzmiPoNazivuRobe("Kola"));
    }
    
    public static void preuzmiPoJediniciMjereTest(){
        System.out.println("FakturaAdapter: preuzmiPoJediniciMjere test");
        System.out.println("-------------------------------");
        TestUtil.printAll(FakturaAdapter.preuzmiPoJediniciMjere("kol"));
    }
    
    public static void preuzmiPoKoliciniTest(){
        System.out.println("FakturaAdapter: preuzmiPoKolicini test");
        System.out.println("-------------------------------");
        TestUtil.printAll(FakturaAdapter.preuzmiPoKolicini(150));
    }
    
    public static void preuzmiPoCijeniTest(){
        System.out.println("FakturaAdapter: preuzmiPoCijeni test");
        System.out.println("-------------------------------");
        TestUtil.printAll(FakturaAdapter.preuzmiPoCijeni(100.0));
    }
    
    public static void preuzmiPoDatumuTest() throws ParseException{
        System.out.println("FakturaAdapter: preuzmiPoDatumu test");
        System.out.println("-------------------------------");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date datum = sdf.parse("10/5/2017");
        TestUtil.printAll(FakturaAdapter.preuzmiPoDatumu(datum));
    }
    
    public static void preuzmiPoRacunIzdaoTest(){
       System.out.println("FakturaAdapter: preuzmiPoRacunIzdao test");
       System.out.println("-------------------------------");
       TestUtil.printAll(FakturaAdapter.preuzmiPoRacunIzdao("Milos")); 
    }
    
    public static void unesiTest(){
        System.out.println("FakturaAdapter: unesi test");
        System.out.println("-------------------------------");
        FakturaOO fakturaOO = new FakturaOO(null, "333333", "Lizalo", "kol", 500, 100.0, new Date(), "Aleksandar");
        FakturaAdapter.unesi(fakturaOO);
        System.out.println(fakturaOO);
        
    }
    
    public static void izmijeniTest(){
        System.out.println("FakturaAdapter: izmijeni test");
        System.out.println("-------------------------------");
        FakturaOO fakturaOO = new FakturaOO(4, "333333", "OrbitZvake", "kol", 500, 100.0, new Date(), "Aleksandar");
        FakturaAdapter.izmijeni(fakturaOO);
        

    }
    
    public static void obrisiTest(){
        System.out.println("FakturaAdapter: obrisi test");
        System.out.println("-------------------------------");
        FakturaAdapter.obrisi(4);
    }
    
    
}
