/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.adapter.test;

import java.util.List;

/**
 *
 * @author Aleksandar
 */
public class TestUtil {
    
   public static void printAll(List<? extends Object> objList){
       objList.stream().forEach(obj -> System.out.println(obj));
   }
   
}
