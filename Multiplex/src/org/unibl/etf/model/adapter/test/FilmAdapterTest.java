/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter.test;

import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.model.adapter.FilmAdapter;
import org.unibl.etf.model.adapter.ZanrAdapter;
import org.unibl.etf.model.domain.oo.FilmOO;
import org.unibl.etf.model.domain.oo.ZanrOO;

/**
 *
 * @author Aleksandar
 */
public class FilmAdapterTest {
    
    public static void preuzmiSveTest(){
        System.out.println("FilmAdapter: preuzmiSve test");
        System.out.println("----------------------------");
        
        TestUtil.printAll(FilmAdapter.preuzmiSve());
    }
    
    public static void preuzmiPoIdTest(){
        System.out.println("FilmAdapter: preuzmiPoId test");
        System.out.println("----------------------------");
        System.out.println(FilmAdapter.preuzmiPoId(1));
    }
    
    public static void preuzmiPoNazivuTest(){
        System.out.println("FilmAdapter: preuzmiPoNazivu test");
        System.out.println("----------------------------");
        TestUtil.printAll(FilmAdapter.preuzmiPoNazivu("Kum"));
    }
    
    public static void unesiTest(){
        System.out.println("FilmAdapter: unesi test");
        System.out.println("----------------------------");
        
        //Zanrove morate vec imati u bazi, kako ce te ih povlaciti, vasa stvar :D
        List<ZanrOO> zanrovi = new ArrayList<ZanrOO>();
        zanrovi.add(ZanrAdapter.preuzmiPoId(1));
        zanrovi.add(ZanrAdapter.preuzmiPoId(3));
        FilmOO filmOO = new FilmOO(
                null,
                "Trumanov sou",
                103,
                "Veliki Brat",
                "C:\\Slika.jpg",
                zanrovi
        );
        
        FilmAdapter.unesi(filmOO);
        
        System.out.println(filmOO);
    }
    
    public static void izmijeniTest(){
        System.out.println("FilmAdapter: unesi test");
        System.out.println("----------------------------");
        //Mijenjamo zanrove i osnovne podatke 
        List<ZanrOO> zanrovi = new ArrayList<ZanrOO>();
        zanrovi.add(ZanrAdapter.preuzmiPoId(3));
        FilmOO filmOO = new FilmOO(
                4,
                "Trumanov sou",
                103,
                "Veliki Brat Orvel",
                "C:\\Downloads\\Slika.jpg",
                zanrovi
        );
        
        FilmAdapter.izmijeni(filmOO);
    }
    
    public static void obrisiTest(){
       System.out.println("FilmAdapter: obrisi test");
       System.out.println("----------------------------");
       FilmAdapter.obrisi(4);
    }
}
