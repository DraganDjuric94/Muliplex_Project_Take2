/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unibl.etf.model.adapter.TransakcijaAdapter;
import org.unibl.etf.model.domain.oo.TransakcijaOO;

/**
 *
 * @author Aleksandar
 */
public class TransakcijaAdapterTest {
    
    public static void preuzmiSveTest(){
        System.out.println("TransakcijaAdapter: preuzmiSve test");
        System.out.println("-------------------------------");
        TestUtil.printAll(TransakcijaAdapter.preuzmiSve());
    }
    
    public static void preuzmiPoIdTest(){
        System.out.println("TransakcijaAdapter: preuzmiPoId test");
        System.out.println("-------------------------------");
        System.out.println(TransakcijaAdapter.preuzmiPoId(1));
    }
    
    public static void preuzmiPoVrstiTransakcijeTest(){
        System.out.println("TransakcijaAdapter: preuzmiPoVrstiTransakcije test");
        System.out.println("-------------------------------");
        TestUtil.printAll(TransakcijaAdapter.preuzmiPoVrstiTransakcije("Vrsta1"));
    }
    
    public static void preuzmiPoPrimaocuTest(){
        System.out.println("TransakcijaAdapter: preuzmiPoPrimaocu test");
        System.out.println("-------------------------------");
        TestUtil.printAll(TransakcijaAdapter.preuzmiPoPrimaocu("Warner Bros"));
    }
    
    public static void preuzmiPoPosaljiocuTest(){
        System.out.println("TransakcijaAdapter: preuzmiPoPosaljiocu test");
        System.out.println("-------------------------------");
        TestUtil.printAll(TransakcijaAdapter.preuzmiPoPosaljiocu("Multiplex"));
    }
    
    public static void preuzmiPoDatumuTransakcijeTest(){
        try {
            System.out.println("TransakcijaAdapter: preuzmiPoDatumuTransakcije test");
            System.out.println("-------------------------------");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date datum = sdf.parse("3/10/2013");
            TestUtil.printAll(TransakcijaAdapter.preuzmiPoDatumuTransakcije(datum));
        } catch (ParseException ex) {
            Logger.getLogger(TransakcijaAdapterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void preuzmiPoIznosuTest(){
        System.out.println("TransakcijaAdapter: preuzmiPoIznosu test");
        System.out.println("----------------------------------------");
        TestUtil.printAll(TransakcijaAdapter.preuzmiPoIznosu(1500.99));
    }
    
    public static void unesiTest(){
       System.out.println("TransakcijaAdapter: unesi test");
       System.out.println("----------------------------------------");
       TransakcijaOO transakcijaOO = new TransakcijaOO(
               null,
               "Vrsta3",
               "Paramount",
               "Multiplex",
               new Date(),
               1300.99
       );
       
       TransakcijaAdapter.unesi(transakcijaOO);
       System.out.print(transakcijaOO);
    }
    
    public static void izmijeniTest(){
       System.out.println("TransakcijaAdapter: izmijeni test");
       System.out.println("----------------------------------------");
       TransakcijaOO transakcijaOO = new TransakcijaOO(
               4,
               "Vrsta3",
               "Tristar",
               "Multiplex",
               new Date(),
               1400.99
       );
       
       TransakcijaAdapter.izmijeni(transakcijaOO);
    }
    
    public static void obrisiTest(){
       System.out.println("TransakcijaAdapter: obrisi test");
       System.out.println("----------------------------------------");
       TransakcijaAdapter.obrisi(4);
    }
    
    
}
