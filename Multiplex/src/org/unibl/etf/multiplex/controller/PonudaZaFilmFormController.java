/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.unibl.etf.model.adapter.PonudaZaFilmAdapter;
import org.unibl.etf.model.domain.oo.PonudaZaFilmOO;

/**
 * FXML Controller class
 *
 * @author Aleksandar
 */
public class PonudaZaFilmFormController implements Initializable {

    private PonudaZaFilmOO ponudaZaFilm;
    private boolean azuriranje;
    
    public PonudaZaFilmFormController(PonudaZaFilmOO ponudaZaFilm, boolean azuriranje){
        if(azuriranje){
            this.ponudaZaFilm = ponudaZaFilm;
            this.azuriranje = true;
        }
    }
    
    @FXML
    private TextArea ponudaZaFilmFormOpisTA;
    @FXML
    private DatePicker ponudaZaFilmFormDatumDP;
    @FXML
    private Button ponudaZaFilmFormPonistiBTN;
    @FXML
    private Button ponudaZaFilmFormOkBTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(this.azuriranje){
            this.ponudaZaFilmFormOpisTA.setText(this.ponudaZaFilm.getOpis());
            this.ponudaZaFilmFormDatumDP.setValue(this.ponudaZaFilm.getDatum().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
    }    

    @FXML
    private void ponudaZaFilmFormPonistiBTNHandler(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ponudaZaFilmFormOkBTNHandler(ActionEvent event) {
        if(this.azuriranje){
            azuriraj();
        }else{
            dodaj();
        }
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void dodaj(){
        this.ponudaZaFilm.setOpis(this.ponudaZaFilmFormOpisTA.getText());
        this.ponudaZaFilm.setDatum(Date.from(this.ponudaZaFilmFormDatumDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        
        PonudaZaFilmAdapter.unesi(ponudaZaFilm);
    }
    
    public void azuriraj(){
        PonudaZaFilmOO novaPonudaZaFilm = new PonudaZaFilmOO(
                null,
                null,
                this.ponudaZaFilmFormOpisTA.getText(),
                Date.from(this.ponudaZaFilmFormDatumDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())
        );
        
        PonudaZaFilmAdapter.unesi(novaPonudaZaFilm);
    }
    
}