/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter.test;

import java.util.Date;
import org.unibl.etf.model.adapter.PonudaZaFilmAdapter;
import org.unibl.etf.model.domain.oo.PonudaZaFilmOO;

/**
 *
 * @author Aleksandar
 */
public class PonudaZaFilmAdapterTest {
    
    public static void preuzmiSveTest(){
        System.out.println("PonudaZaFilmAdapter: preuzmiSve test");
        System.out.println("------------------------------------");
        TestUtil.printAll(PonudaZaFilmAdapter.preuzmiSve());
    }
    
    public static void preuzmiPoIdTest(){
        System.out.println("PonudaZaFilmAdapter: preuzmiPoId test");
        System.out.println("------------------------------------");
        System.out.println(PonudaZaFilmAdapter.preuzmiPoFilmId(1));
    }
    
    public static void preuzmiPoFilmIdTest(){
        System.out.println("PonudaZaFilmAdapter: preuzmiPoFilmId test");
        System.out.println("-----------------------------------------");
        TestUtil.printAll(PonudaZaFilmAdapter.preuzmiPoFilmId(1));
    }
    
    public static void unesiTest(){
        System.out.println("PonudaZaFilmAdapter: unesi test");
        System.out.println("-----------------------------------------");
        PonudaZaFilmOO ponudaZaFilmOO = new PonudaZaFilmOO(
                null,
                null,
                "Bollywood Films",
                new Date()  
        );
        
        PonudaZaFilmAdapter.unesi(ponudaZaFilmOO);
        
        System.out.println(ponudaZaFilmOO);               
    }
    
    public static void izmijeniTest(){
        System.out.println("PonudaZaFilmAdapter: izmijeni test");
        System.out.println("-----------------------------------------");
        PonudaZaFilmOO ponudaZaFilmOO = new PonudaZaFilmOO(
                3,
                3,
                "Bollywood Films",
                new Date()  
        );
        
        PonudaZaFilmAdapter.izmijeni(ponudaZaFilmOO);
    }
    
    public static void obrisiTest(){
        System.out.println("PonudaZaFilmAdapter: obrisi test");
        System.out.println("-----------------------------------------");
        PonudaZaFilmAdapter.obrisi(3);
    }
}
