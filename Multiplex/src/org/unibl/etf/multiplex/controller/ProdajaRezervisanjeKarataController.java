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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.unibl.etf.model.adapter.ProjekcijaAdapter;
import org.unibl.etf.model.adapter.ZanrAdapter;
import org.unibl.etf.model.domain.Projekcija;
import org.unibl.etf.model.domain.oo.ProjekcijaOO;
import org.unibl.etf.model.domain.oo.ZanrOO;

/**
 * FXML Controller class
 *
 * @author juhu
 */
public class ProdajaRezervisanjeKarataController implements Initializable {
    
    @FXML
    private ListView<ProjekcijaOO> projekcijeLST;

    @FXML
    private Button pretraziBTN;

    @FXML
    private Button izaberiBTN;

    @FXML
    private TextField pretraziTXT;

    @FXML
    private ChoiceBox<String> zanrCBX;
    
    @FXML
    private Button pregledajRezervacijeBTN;
    
    private ObservableList<ProjekcijaOO> projekcije;
    private ObservableList<String> zanrovi;
    
    public void preuzmiSveProjekcije(){
        projekcije.clear();
        projekcije.addAll(ProjekcijaAdapter.preuzmiSve());
        for(ProjekcijaOO p : projekcije){
            p.getFilm().setSlika("images/testSlika.jpg");
        }
    }
    
    public void preuzmiSveZanrove(){
        zanrovi.clear();
        ArrayList<ZanrOO> tempList = ZanrAdapter.preuzmiSve();
        zanrovi.add("Svi");
        for(ZanrOO z : tempList){
            zanrovi.add(z.getNaziv());
        }
    }
    
    public void pretrazi(String tekst){
        projekcije.clear();
        projekcije.addAll(ProjekcijaAdapter.preuzmiPoNazivuFilma(tekst));
        
        if(zanrCBX.getSelectionModel().getSelectedItem().equals("Svi")){
            //nista
        }else{
            String zanr = zanrCBX.getSelectionModel().getSelectedItem();
            Iterator<ProjekcijaOO> it = projekcije.iterator();
            while(it.hasNext()){
                boolean imaZanr = false;
                for(ZanrOO z : it.next().getFilm().getZanrovi()){
                    if(zanr.toLowerCase().equals(z.getNaziv().toLowerCase())){
                        imaZanr = true;
                        break;
                    }
                }
                if(!imaZanr){
                    it.remove();
                }
            }
        }
        for(ProjekcijaOO p : projekcije){
            p.getFilm().setSlika("images/testSlika.jpg");
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
        preuzmiSveZanrove();
        zanrCBX.getSelectionModel().select(0);
        
        projekcijeLST.setCellFactory(listView -> new ListCell<ProjekcijaOO>(){
            @Override
            public void updateItem(ProjekcijaOO proj, boolean empty){
                super.updateItem(proj, empty);		
                if(empty){
                    setText(null);
                    setGraphic(null);
                }else{
                    PodaciZaProjekcijuList podaci = new PodaciZaProjekcijuList(proj.getFilm().getSlika(), proj.getFilm().getNaziv(),proj.getFilm().getOpis(), proj.getFilm().getTrajanje().toString(), "5.35");
                    AnchorPane fxmlPrikaz = podaci.getFXMLPrikaz();
                    fxmlPrikaz.prefWidthProperty().bindBidirectional(projekcijeLST.prefWidthProperty());
                    setGraphic(fxmlPrikaz);
                   }
            }		
        });       
        
        pretraziBTN.setOnAction((event) -> {
            pretrazi(pretraziTXT.getText());
        });
        
        pretraziTXT.setOnKeyReleased((event) -> {
            pretrazi(pretraziTXT.getText());
        });
        
        pregledajRezervacijeBTN.setOnAction((event) -> {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/multiplex/fxml/PregledRezervacija.fxml"));
            try {
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("");
                
                stage.setOnCloseRequest((WindowEvent event1) -> {
                    //
                });
                
                stage.show();
            }catch (IOException ex) {
                Logger.getLogger(ProdajaRezervisanjeKarataController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        });
        
        izaberiBTN.setOnAction((ActionEvent event) -> {
            IzborSjedistaController control = new IzborSjedistaController(projekcijeLST.getSelectionModel().getSelectedItem().getProjekcijaId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/multiplex/fxml/IzborSjedista.fxml"));
            try {
                loader.setController(control);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("");
                stage.setMaximized(true);
                
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
