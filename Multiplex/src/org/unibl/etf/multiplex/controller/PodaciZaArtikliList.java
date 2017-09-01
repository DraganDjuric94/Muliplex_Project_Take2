/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author juhu
 */
public class PodaciZaArtikliList {
    
    @FXML
    private Label stanjeLBL;

    @FXML
    private Label nazivLBL;

    @FXML
    private Label cijenaLBL;

    @FXML
    private AnchorPane artikliANP;
    
     public PodaciZaArtikliList(String naziv, String kolicina, String cijena){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/multiplex/fxml/PodaciZaListuArtikala.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(PodaciZaRacunList.class.getName()).log(Level.SEVERE, null, ex);
        }
        nazivLBL.setText(naziv);
        stanjeLBL.setText("Na stanju: " + kolicina);
        cijenaLBL.setText("Cijena: " + cijena + " KM");
    }
    
    public AnchorPane getFXMLView(){
        return artikliANP;
    }
    
}
