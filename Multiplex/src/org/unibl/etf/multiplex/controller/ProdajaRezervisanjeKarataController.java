/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.unibl.etf.model.domain.Projekcija;

/**
 * FXML Controller class
 *
 * @author juhu
 */
public class ProdajaRezervisanjeKarataController implements Initializable {
    
    @FXML
    private ListView<Projekcija> projekcijeLST;

    @FXML
    private Button pretraziBTN;

    @FXML
    private Button izaberiBTN;

    @FXML
    private TextField pretraziTXT;

    @FXML
    private ChoiceBox<String> zanrCBX;
    
    private ObservableList<Projekcija> projekcije;
    private ObservableList<String> zanrovi;
    
    public void preuzmiSveProjekcije(){
        //projekcije = daj mi iz baze
        
    }
    
    public void pretrazi(String tekst){
        //projekcije je daj iz baze po tekstu
        
        if(zanrCBX.getSelectionModel().getSelectedItem().equals("Svi")){
            //nista
        }else{
            String zanr = zanrCBX.getSelectionModel().getSelectedItem();
            Iterator it = projekcije.iterator();
            while(it.hasNext()){
                if(true/*it.next().film.znarovi not contains zanr*/){
                    it.remove();
                }
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        projekcije = FXCollections.observableArrayList();
        zanrovi = FXCollections.observableArrayList();
        projekcijeLST.setItems(projekcije);
        zanrCBX.setItems(zanrovi);
        preuzmiSveProjekcije();
        
        pretraziBTN.setOnAction((event) -> {
            pretrazi(pretraziTXT.getText());
        });
        
        pretraziTXT.setOnKeyReleased((event) -> {
            pretrazi(pretraziTXT.getText());
        });
        
        izaberiBTN.setOnAction((ActionEvent event) -> {
            IzborSjedistaController control = new IzborSjedistaController(null/*projekcijeLST.getSelectionModel().getSelectedItem().getProjekcijaId()*/);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/multiplex/fxml/IzborSjedista.fxml"));
            try {
                loader.setController(control);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("");
                //stage.setResizable(false);
                
                stage.setOnCloseRequest((WindowEvent event1) -> {
                    //
                });
                
                stage.show();
            }catch (IOException ex) {
                Logger.getLogger(ProdajaRezervisanjeKarataController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        
        
        
    }    
    
}
