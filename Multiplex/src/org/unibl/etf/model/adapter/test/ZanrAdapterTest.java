/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter.test;

import java.util.ArrayList;
import org.unibl.etf.model.adapter.ZanrAdapter;
import org.unibl.etf.model.domain.oo.ZanrOO;

/**
 *
 * @author Aleksandar
 */
public class ZanrAdapterTest {
    
    
    
    public static void preuzmiSveTest(){
        System.out.println("ZanrAdapter:preuzmiSve test");
        System.out.println("---------------------------");
        ArrayList<ZanrOO> zanrOOList = ZanrAdapter.preuzmiSve();
        TestUtil.printAll(zanrOOList);
    }
    
    public static void preuzmiPoIdTest(){
        System.out.println("ZanrAdapter:preuzmiPoId test");
        System.out.println("----------------------------");
        ZanrOO zanrOO = ZanrAdapter.preuzmiPoId(1);
        System.out.println(zanrOO);
    }
    
    public static void preuzmiPoNazivuTest(){
        System.out.println("ZanrAdapter:preuzmiPoNazivu test");
        System.out.println("--------------------------------");
        ArrayList<ZanrOO> zanrOOList = ZanrAdapter.preuzmiPoNazivu("Krimi");
        TestUtil.printAll(zanrOOList);
    }
    
    public static void unesiTest(){
        System.out.println("ZanrAdapter:unesi test");
        ZanrOO zanrOO = new ZanrOO(null, "Naucna fantastika");
        ZanrAdapter.unesi(zanrOO);
        System.out.println(zanrOO);
    }
    
    public static void izmijeniTest(){
        System.out.println("ZanrAdapter:izmijeni test");
        ZanrOO zanrOO = new ZanrOO(4, "Epska fantastika");
        ZanrAdapter.izmijeni(zanrOO);
        System.out.println(zanrOO);
    }
    
    public static void obrisiTest(){
        System.out.println("ZanrAdapter:obrisi test");
        ZanrAdapter.obrisi(4);
        TestUtil.printAll(ZanrAdapter.preuzmiSve());
    }
    
    
    
    
}
