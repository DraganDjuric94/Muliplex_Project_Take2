/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter.test;

import org.unibl.etf.model.adapter.PozicijaAdapter;

/**
 *
 * @author Aleksandar
 */
public class ProjekcijaAdapterTest {
    
    public static void preuzmiSveTest(){
        System.out.println("ProjekcijaAdapter: preuzmiSve test");
        System.out.println("----------------------------------");
        TestUtil.printAll(PozicijaAdapter.preuzmiSve());
    }
    
    public static void preuzmiPoNazivuFilmaTest(){
        System.out.println("ProjekcijaAdapter: preuzmiPoNazivuFilma test");
        System.out.println("----------------------------------");
        TestUtil.printAll(PozicijaAdapter.preuzmiPoNazivu("Kum"));
    }
}
