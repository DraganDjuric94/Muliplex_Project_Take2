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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author juhu
 */
public class PodaciZaRezervacijuList {
    
   @FXML
    private Label trajanjeLBL;

    @FXML
    private Label cijenaLBL;

    @FXML
    private ImageView slikaIMG;

    @FXML
    private Label naslovFilmaLBL;

    @FXML
    private Label opisFilmLBL;
    
    @FXML
    private AnchorPane projekcijaANP;
    
    @FXML
    private Label brojRezervacijeLBL;
    
    public PodaciZaRezervacijuList(String slika, String naslov, String opis, String trajanje, String cijena, String idRezervacije){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/unibl/etf/multiplex/fxml/RezervacijaIzListe.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        } catch (IOException ex) {
           Logger.getLogger(PodaciZaProjekcijuList.class.getName()).log(Level.SEVERE, null, ex);
       }
       
        slikaIMG.setImage(new Image("file:" + slika));
        naslovFilmaLBL.setText(naslov);
        naslovFilmaLBL.setContentDisplay(ContentDisplay.CENTER);
        opisFilmLBL.setText(opis);
        opisFilmLBL.setContentDisplay(ContentDisplay.TOP);
        trajanjeLBL.setText("Trajanje: " + trajanje + " min");
        cijenaLBL.setText("Cijena: " + cijena + " KM");
        brojRezervacijeLBL.setText(idRezervacije);
    }
    
    public AnchorPane getFXMLPrikaz(){
        return projekcijaANP;
    }
    
}
    
