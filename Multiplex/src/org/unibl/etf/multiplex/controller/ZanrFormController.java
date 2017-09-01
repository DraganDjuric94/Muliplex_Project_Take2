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
import javafx.scene.control.Label;
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
    private Boolean validniPodaci = false;

    private ZanrOO zanr;

    public ZanrFormController(ZanrOO zanr, boolean azuriranje) {
        if (azuriranje) {
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
    @FXML
    private Label validnostPodatakaLBL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        validnostPodatakaLBL.setVisible(false);
        if (this.azuriranje) {
            this.zanrFormNazivTXT.setText(this.zanr.getNaziv());
        }
    }

    @FXML
    private void zanrFormPonistiBTNHandler(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void zanrFormOkBTNHandler(ActionEvent event) {
        provjeriPodatke();
        if (validniPodaci == true) {
            if (this.azuriranje) {
                azuriraj();
            } else {
                dodaj();
            }
            System.out.println("POdaci su validni");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            validnostPodatakaLBL.setVisible(true);
            System.out.println("Podaci nisu validni");
        }

    }

    public void azuriraj() {
        this.zanr.setNaziv(this.zanrFormNazivTXT.getText());
        ZanrAdapter.izmijeni(zanr);
    }

    public void dodaj() {
        ZanrOO noviZanr = new ZanrOO(
                null,
                this.zanrFormNazivTXT.getText()
        );
        ZanrAdapter.unesi(noviZanr);
    }

    private void provjeriPodatke() {
        if ("".equals(zanrFormNazivTXT.getText())) {

        } else {
            validniPodaci = true;
        }
    }

}
