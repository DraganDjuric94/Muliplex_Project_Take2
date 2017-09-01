/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    private boolean dobarUnos = false;
    
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
    @FXML
    private Label greskaLBL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(this.azuriranje){
            java.sql.Date datum = (java.sql.Date)this.ponudaZaFilm.getDatum();
            this.ponudaZaFilmFormDatumDP.setValue(datum.toLocalDate());
        }
    }    

    @FXML
    private void ponudaZaFilmFormPonistiBTNHandler(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ponudaZaFilmFormOkBTNHandler(ActionEvent event) {
        if(provjeriUnos()){
            if(this.azuriranje){
                azuriraj();
            }else{
                dodaj();
            }
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }else{
            greskaLBL.setVisible(true);
        }
    }
    
    public void azuriraj(){
        this.ponudaZaFilm.setOpis(this.ponudaZaFilmFormOpisTA.getText());
        this.ponudaZaFilm.setDatum(Date.from(this.ponudaZaFilmFormDatumDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        
        PonudaZaFilmAdapter.izmijeni(ponudaZaFilm);
    }
    
    public void dodaj(){
        PonudaZaFilmOO novaPonudaZaFilm = new PonudaZaFilmOO(
                null,
                null,
                this.ponudaZaFilmFormOpisTA.getText(),
                Date.from(this.ponudaZaFilmFormDatumDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        
        PonudaZaFilmAdapter.unesi(novaPonudaZaFilm);
    }
    
    public boolean provjeriUnos(){
        if(ponudaZaFilmFormOpisTA != null && ponudaZaFilmFormOpisTA.getText().length() > 0 && ponudaZaFilmFormDatumDP.getValue() != null){
           return true;
        }else{
            return false;
        }
    }
    
}
