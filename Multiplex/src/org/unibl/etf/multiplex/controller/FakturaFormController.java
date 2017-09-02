/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.unibl.etf.model.adapter.FakturaAdapter;
import org.unibl.etf.model.domain.oo.FakturaOO;

/**
 * FXML Controller class
 *
 * @author Aleksandar
 */
public class FakturaFormController implements Initializable {

    private boolean azuriranje;
    private Boolean validniPodaci = false;

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
    @FXML
    private Label validnostPodatakaLBL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        validnostPodatakaLBL.setVisible(false);
        fakturaFormDatumDP.setEditable(false);
        omoguciSamoCijeleBrojeve(fakturaFormBrojRacunaTXT);
        omoguciSamoCijeleBrojeve(fakturaFormKolicinaTXT);
        omoguciSamoDecimalneBrojeve(fakturaFormCijenaTXT);
        if (this.azuriranje) {
            this.fakturaFormBrojRacunaTXT.setText(faktura.getBrojRacuna());
            this.fakturaFormNazivRobeTXT.setText(faktura.getNazivRobe());
            this.fakturaFormJedinicaMjereTXT.setText(faktura.getJedinicaMjere());
            this.fakturaFormKolicinaTXT.setText(faktura.getKolicina().toString());
            this.fakturaFormCijenaTXT.setText(faktura.getCijena().toString());
            this.fakturaFormDatumDP.setValue(LocalDate.parse(faktura.getDatum().toString()));
            this.fakturaFormRacunIzdaoTXT.setText(faktura.getRacunIzdao());
        }
    }

    @FXML
    private void fakturaFormPonistiBTNHandler(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void fakturaFormOkBTNHandler(ActionEvent event) {
        provjeriPodatke();
        if (validniPodaci == true) {
            if (this.azuriranje) {
                azuriraj();
            } else {
                dodaj();
            }
            validnostPodatakaLBL.setVisible(false);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            validnostPodatakaLBL.setVisible(true);
        }

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

    private void omoguciSamoCijeleBrojeve(TextField unos) {

        Pattern validIntegerText = Pattern.compile("[0-9]*");

        TextFormatter<Integer> textFormatter = new TextFormatter<Integer>(new IntegerStringConverter(), null,
                change -> {
                    String newText = change.getControlNewText();
                    if (validIntegerText.matcher(newText).matches()) {
                        return change;
                    } else {
                        return null;
                    }
                });
        unos.setTextFormatter(textFormatter);
    }

    private void omoguciSamoDecimalneBrojeve(TextField unos) {
        Pattern validDoubleText = Pattern.compile("[0-9]*[.]?[0-9]*");

        TextFormatter<Double> textFormatter = new TextFormatter<Double>(new DoubleStringConverter(), null,
                change -> {
                    String newText = change.getControlNewText();
                    if (validDoubleText.matcher(newText).matches()) {
                        return change;
                    } else {
                        return null;
                    }
                });

        unos.setTextFormatter(textFormatter);
    }

    private void provjeriPodatke() {
        if ("".equals(fakturaFormBrojRacunaTXT.getText()) || "".equals(fakturaFormNazivRobeTXT.getText())
                || "".equals(fakturaFormJedinicaMjereTXT) || "".equals(fakturaFormKolicinaTXT.getText())
                || "".equals(fakturaFormCijenaTXT.getText()) || fakturaFormDatumDP.getValue() == null
                || "".equals(fakturaFormRacunIzdaoTXT.getText()) || Double.parseDouble(fakturaFormCijenaTXT.getText()) == 0.0
                || fakturaFormDatumDP.getValue().isAfter(LocalDate.now())) {
            validniPodaci = false;
        } else {
            validniPodaci = true;

        }
    }

}
