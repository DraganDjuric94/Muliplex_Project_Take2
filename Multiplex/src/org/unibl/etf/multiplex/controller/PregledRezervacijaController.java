/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.unibl.etf.model.adapter.KartaAdapter;
import org.unibl.etf.model.domain.oo.KartaOO;
import org.unibl.etf.model.domain.oo.ProjekcijaOO;

/**
 * FXML Controller class
 *
 * @author juhu
 */
public class PregledRezervacijaController implements Initializable {

    @FXML
    private Button pretraziBTN;

    @FXML
    private ListView<KartaOO> rezervacijeLST;

    @FXML
    private Button potvrdiBTN;

    @FXML
    private TextField pretraziTXT;

    @FXML
    private Button otkaziBTN;
    
    private ObservableList<KartaOO> rezervacije = FXCollections.observableArrayList();
    
    public void preuzmiRezervacije(){
        ArrayList<KartaOO> temp = KartaAdapter.preuzmiSve();
        for(KartaOO k : temp){
            if(k.getRezervisana() == true){
                rezervacije.add(k);
            }
        }
    }
    
    public void pretrazi(String text){
        ArrayList<KartaOO> temp = KartaAdapter.preuzmiSve();
        rezervacije.clear();
        for(KartaOO k : temp){
            if(k.getRezervisana() && (k.getKartaId().toString().startsWith(text) || k.getProjekcija().getFilm().getNaziv().toLowerCase().startsWith(text.toLowerCase()))){
                rezervacije.add(k);
            }
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        preuzmiRezervacije();
        
        rezervacijeLST.setItems(rezervacije);
        
        pretraziBTN.setOnAction((event) -> {
            pretrazi(pretraziTXT.getText());
        });
        
        pretraziTXT.setOnKeyReleased((event) -> {
            pretrazi(pretraziTXT.getText());
        });
        
        
        rezervacijeLST.setCellFactory(listView -> new ListCell<KartaOO>(){
            @Override
            public void updateItem(KartaOO karta, boolean empty){
                super.updateItem(karta, empty);		
                if(empty){
                    setText(null);
                    setGraphic(null);
                }else{
                    ProjekcijaOO proj = karta.getProjekcija();
                    proj.getFilm().setSlika("images/testSlika.jpg");
                    PodaciZaRezervacijuList podaci = new PodaciZaRezervacijuList(proj.getFilm().getSlika(), proj.getFilm().getNaziv(),proj.getFilm().getOpis(), proj.getFilm().getTrajanje().toString(), "5.35", "Broj rezervacije: " + karta.getKartaId().toString());
                    AnchorPane fxmlPrikaz = podaci.getFXMLPrikaz();
                    fxmlPrikaz.prefWidthProperty().bindBidirectional(rezervacijeLST.prefWidthProperty());
                    setGraphic(fxmlPrikaz);
                   }
            }		
        });
        
        
        
    }    
    
}
