/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.unibl.etf.model.adapter.ZanrAdapter;
import org.unibl.etf.model.domain.oo.ZanrOO;

/**
 * FXML Controller class
 *
 * @author Aleksandar
 */
public class ZanrFormController implements Initializable {

    private boolean azuriranje;
    
    private ZanrOO zanr;
    
    public ZanrFormController(ZanrOO zanr, boolean azuriranje){
        if(azuriranje){
            this.zanr = zanr;
            this.azuriranje = true;
        }
    }
    
    @FXML
    private TextField zanrFormNazivTXT;
    @FXML
    private Button zanrFormPonistiBTN;
    @FXML
    private Button zanrFormOkBTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(this.azuriranje){
           this.zanrFormNazivTXT.setText(this.zanr.getNaziv());
        }
    }    

    @FXML
    private void zanrFormPonistiBTNHandler(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void zanrFormOkBTNHandler(ActionEvent event) {
        if(this.azuriranje){
            azuriraj();
        }else{
            dodaj();
        }
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void azuriraj(){
        this.zanr.setNaziv(this.zanrFormNazivTXT.getText());
        ZanrAdapter.izmijeni(zanr);
    }
    
    public void dodaj(){
        ZanrOO noviZanr = new ZanrOO(
                null,
                this.zanrFormNazivTXT.getText()
        );
        ZanrAdapter.unesi(noviZanr);
    }
    
}
