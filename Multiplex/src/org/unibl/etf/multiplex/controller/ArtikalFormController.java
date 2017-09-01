/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.unibl.etf.model.adapter.ArtikalAdapter;
import org.unibl.etf.model.domain.oo.ArtikalOO;

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
    @FXML
    private Label validnostPodatakaLBL;

    private ArtikalOO old;
    private String naziv;
    private Integer kolicinaNaStanju;
    private String barkod;
    private String tip;
    private Double cijena;

    private Boolean isUpdating = false;
    private Boolean validniPodaci = false;

    public ArtikalFormController(ArtikalOO it, Boolean updateFlag) {

        if (updateFlag) {

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

        validnostPodatakaLBL.setVisible(false);
        omoguciSamoCijeleBrojeve(artikalFormKolicinaTXT);
        omoguciSamoCijeleBrojeve(artikalFormBarkodTXT);
        omoguciSamoDecimalneBrojeve(artikalFormCijenaTXT);

        if (isUpdating) {

            artikalFormNazivTXT.setText(naziv);
            artikalFormCijenaTXT.setText(cijena.toString());
            artikalFormTipTXT.setText(tip);
            artikalFormBarkodTXT.setText(barkod);
            artikalFormKolicinaTXT.setText(kolicinaNaStanju.toString());
        }

        artikalFormOkBTN.setOnAction((event) -> {
            //TODO Provjeriti da li su unesena polja i sl

            provjeriPodatke();
            if (validniPodaci == true) {
                if (isUpdating) {

                    updateArtikal();

                } else {

                    addArtikal();
                }
                validnostPodatakaLBL.setVisible(false);
                Stage stage = (Stage) artikalFormOkBTN.getScene().getWindow();
                stage.close();
            } else {
                validnostPodatakaLBL.setVisible(true);
            }

        });

        artikalFormCancelBTN.setOnAction((event) -> {

            Stage stage = (Stage) artikalFormCancelBTN.getScene().getWindow();
            stage.close();

        });
    }

    private void provjeriPodatke() {
        if ("".equals(artikalFormNazivTXT.getText()) || "".equals(artikalFormKolicinaTXT.getText())
                || "".equals(artikalFormBarkodTXT.getText()) || "".equals(artikalFormTipTXT.getText())
                || "".equals(artikalFormCijenaTXT.getText())) {

            validniPodaci = false;
            System.out.println("Podaci nisu validni");
        } else {
            if ("Hrana".equals(artikalFormTipTXT.getText())
                    || "Pice".equals(artikalFormTipTXT.getText())) {
                System.out.println("Podaci su validni");
                validniPodaci = true;
            } else {
                System.out.println("Podaci nisu validni");
                validniPodaci = false;
            }

        }

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

}
