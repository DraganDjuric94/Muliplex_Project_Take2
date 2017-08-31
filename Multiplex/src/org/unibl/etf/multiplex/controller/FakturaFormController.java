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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.unibl.etf.model.adapter.FakturaAdapter;
import org.unibl.etf.model.domain.oo.FakturaOO;

/**
 * FXML Controller class
 *
 * @author Aleksandar
 */
public class FakturaFormController implements Initializable {

    private boolean azuriranje;

    private FakturaOO faktura;

    public FakturaFormController(FakturaOO faktura, boolean azuriranje) {
        if (azuriranje) {
            this.faktura = faktura;
            this.azuriranje = true;
        }
    }

    @FXML
    private TextField fakturaFormBrojRacunaTXT;
    @FXML
    private Button fakturaFormPonistiBTN;
    @FXML
    private Button fakturaFormOkBTN;
    @FXML
    private TextField fakturaFormNazivRobeTXT;
    @FXML
    private TextField fakturaFormJedinicaMjereTXT;
    @FXML
    private TextField fakturaFormKolicinaTXT;
    @FXML
    private TextField fakturaFormCijenaTXT;
    @FXML
    private TextField fakturaFormRacunIzdaoTXT;
    @FXML
    private DatePicker fakturaFormDatumDP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (this.azuriranje) {
            this.fakturaFormBrojRacunaTXT.setText(faktura.getBrojRacuna());
            this.fakturaFormNazivRobeTXT.setText(faktura.getNazivRobe());
            this.fakturaFormJedinicaMjereTXT.setText(faktura.getJedinicaMjere());
            this.fakturaFormKolicinaTXT.setText(faktura.getKolicina().toString());
            this.fakturaFormCijenaTXT.setText(faktura.getCijena().toString());
            this.fakturaFormDatumDP.setValue(faktura.getDatum().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            this.fakturaFormRacunIzdaoTXT.setText(faktura.getRacunIzdao());
        }
    }

    @FXML
    private void fakturaFormPonistiBTNHandler(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void fakturaFormOkBTNHandler(ActionEvent event) {
        if(this.azuriranje){
            azuriraj();
        }else{
            dodaj();
        }
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void azuriraj() {
        this.faktura.setBrojRacuna(this.fakturaFormBrojRacunaTXT.getText());
        this.faktura.setNazivRobe(this.fakturaFormNazivRobeTXT.getText());
        this.faktura.setJedinicaMjere(this.fakturaFormJedinicaMjereTXT.getText());
        this.faktura.setKolicina(Integer.parseInt(this.fakturaFormKolicinaTXT.getText()));
        this.faktura.setCijena(Double.parseDouble(fakturaFormCijenaTXT.getText()));
        this.faktura.setDatum(Date.from(this.fakturaFormDatumDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        this.faktura.setRacunIzdao(this.fakturaFormRacunIzdaoTXT.getText());
        
        FakturaAdapter.izmijeni(faktura);
    }

    public void dodaj() {
        FakturaOO novaFaktura = new FakturaOO(
                null,
                this.fakturaFormBrojRacunaTXT.getText(),
                this.fakturaFormNazivRobeTXT.getText(),
                this.fakturaFormJedinicaMjereTXT.getText(),
                Integer.parseInt(this.fakturaFormKolicinaTXT.getText()),
                Double.parseDouble(this.fakturaFormCijenaTXT.getText()),
                Date.from(this.fakturaFormDatumDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                this.fakturaFormRacunIzdaoTXT.getText()                       
        );
        
        FakturaAdapter.unesi(novaFaktura);
    }

}
