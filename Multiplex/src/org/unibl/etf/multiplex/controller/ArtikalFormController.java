/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.unibl.etf.model.adapter.ArtikalAdapter;
import org.unibl.etf.model.adapter.OpremaAdapter;
import org.unibl.etf.model.domain.oo.ArtikalOO;
import org.unibl.etf.model.domain.oo.OpremaOO;

/**
 * FXML Controller class
 *
 * @author Miloš
 */
public class ArtikalFormController implements Initializable {

    @FXML
    private AnchorPane artikalForm;
    @FXML
    private TextField artikalFormNazivTXT;
    @FXML
    private TextField artikalFormKolicinaTXT;
    @FXML
    private Button artikalFormOkBTN;
    @FXML
    private Button artikalFormCancelBTN;
    @FXML
    private TextField artikalFormBarkodTXT;
    @FXML
    private TextField artikalFormTipTXT;
    @FXML
    private TextField artikalFormCijenaTXT;

    
    private ArtikalOO old;
    private String naziv;
    private Integer kolicinaNaStanju;
    private String barkod;
    private String tip;
    private Double cijena;
    
    private Boolean isUpdating = false;
    
    public ArtikalFormController(ArtikalOO it, Boolean updateFlag) {
        
        if(updateFlag) {
            
            isUpdating = updateFlag;
            old = it;
            naziv = it.getNaziv();
            kolicinaNaStanju = it.getKolicinaNaStanju();
            barkod = it.getBarkod();
            tip = it.getTip();
            cijena = it.getCijena();
        }
    }
    
    public void updateArtikal() {
        
        ArtikalOO it = new ArtikalOO(
                old.getArtikalId(), 
                artikalFormNazivTXT.getText(), 
                Integer.parseInt(artikalFormKolicinaTXT.getText()), 
                artikalFormBarkodTXT.getText(), 
                artikalFormTipTXT.getText(), 
                Double.parseDouble(artikalFormCijenaTXT.getText())
        );
                                
        ArtikalAdapter.izmijeni(it);
    }
    
    public void addArtikal() {
    
        ArtikalOO it = new ArtikalOO(
                null, 
                artikalFormNazivTXT.getText(), 
                Integer.parseInt(artikalFormKolicinaTXT.getText()), 
                artikalFormBarkodTXT.getText(), 
                artikalFormTipTXT.getText(), 
                Double.parseDouble(artikalFormCijenaTXT.getText())
        );
        ArtikalAdapter.unesi(it);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(isUpdating){
            
            artikalFormNazivTXT.setText(naziv);
            artikalFormCijenaTXT.setText(cijena.toString());
            artikalFormTipTXT.setText(tip);
            artikalFormBarkodTXT.setText(barkod);
            artikalFormKolicinaTXT.setText(kolicinaNaStanju.toString());
        }
        
        artikalFormOkBTN.setOnAction((event) -> {
            //TODO Provjeriti da li su unesena polja i sl
            if(isUpdating) {
                
                updateArtikal();
                
            } else {
                
                addArtikal();
            } 
            Stage stage = (Stage) artikalFormOkBTN.getScene().getWindow();
            stage.close();
        });
        
        artikalFormCancelBTN.setOnAction((event) -> {
        
            Stage stage = (Stage) artikalFormCancelBTN.getScene().getWindow();
            stage.close();
        
        });
    }    
    
}
