/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.unibl.etf.model.adapter.RacunAdapter;
import org.unibl.etf.model.domain.oo.RacunOO;
import org.unibl.etf.model.domain.oo.StavkaOO;

/**
 * FXML Controller class
 *
 * @author Ognjen
 */
public class RacunController implements Initializable {

    @FXML
    private Button hranaBTN;
    @FXML
    private Button piceBTN;
    @FXML
    private Button odustaniBTN;
    @FXML
    private Button stampajBTN;
    @FXML
    private Button obrisiBTN;
    @FXML
    private Label ukupnoLBL;
    @FXML
    private ListView<StavkaOO> listaDodatihArtikala;

    Double ukupnoZaPlatiti = 0.0;
    ObservableList<StavkaOO> listaDodatihArtikalaOBS = FXCollections.observableArrayList();
    ArrayList<StavkaOO> stavke = new ArrayList<>();
    RacunOO racun = new RacunOO(null, null, 0.0, stavke);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ukupnoLBL.setVisible(false);
        listaDodatihArtikala.setItems(listaDodatihArtikalaOBS);
        listaDodatihArtikala.setCellFactory(lv -> new ListCell<StavkaOO>() {
            @Override
            public void updateItem(StavkaOO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    PodaciZaRacunList podaci = new PodaciZaRacunList(item.getArtikal().getNaziv(), item.getKolicina().toString(), item.getUkupnaCijena().toString());
                    AnchorPane fxmlPrikaz = podaci.getFXMLView();
                    fxmlPrikaz.prefWidthProperty().bindBidirectional(listaDodatihArtikala.prefWidthProperty());
                    setGraphic(fxmlPrikaz);
                }
            }
        });
        hranaBTN.setOnAction(e -> hranaBTN_Clicked());
        piceBTN.setOnAction(e -> piceBTN_Clicked());
        obrisiBTN.setOnAction(e -> obrisiBTN_Clicked());
        odustaniBTN.setOnAction(e -> odustaniBTN_Clicked());
        stampajBTN.setOnAction(e -> stampajBTN_Clicked());
    }

    private void hranaBTN_Clicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/multiplex/fxml/Hrana.fxml"));
        HranaController controller = new HranaController(listaDodatihArtikalaOBS, racun);
        loader.setController(controller);
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(RacunController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Hrana");
        //stage.setResizable(false);
        stage.showAndWait();
        racun = controller.preuzmiRacun();
        if (!racun.getStavke().isEmpty()) {
            ukupnoLBL.setVisible(true);
            ukupnoLBL.setText("Ukupno za platiti: " + racun.getUkupnaCijena());
        } else {
            ukupnoLBL.setVisible(false);
        }
    }

    private void piceBTN_Clicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/multiplex/fxml/Pice.fxml"));
        PiceController controller = new PiceController(listaDodatihArtikalaOBS, racun);
        loader.setController(controller);
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(RacunController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Pice");
        //stage.setResizable(false);
        stage.showAndWait();
        racun = controller.preuzmiRacun();
        if (!racun.getStavke().isEmpty()) {
            ukupnoLBL.setVisible(true);
            ukupnoLBL.setText("Ukupno za platiti: " + racun.getUkupnaCijena());
        } else {
            ukupnoLBL.setVisible(false);
        }
    }

    private void obrisiBTN_Clicked() {
        StavkaOO stavkaSelektovana = listaDodatihArtikala.getSelectionModel().getSelectedItem();
        if (stavkaSelektovana == null) {
            neispravanOdabir("Niste odabrali stavku za brisanje!");
            return;
        }
        listaDodatihArtikalaOBS.remove(stavkaSelektovana);
        racun.getStavke().remove(stavkaSelektovana);
        if (racun.getStavke().isEmpty()) {
            ukupnoLBL.setVisible(false);
            ukupnoZaPlatiti = 0.0;
        } else {
            for (int i = 0; i < racun.getStavke().size(); i++) {
                ukupnoZaPlatiti += racun.getStavke().get(i).getUkupnaCijena();
            }
            ukupnoLBL.setText("Ukupno za platiti: " + ukupnoZaPlatiti.toString());
        }
        racun.setUkupnaCijena(ukupnoZaPlatiti);
        ukupnoZaPlatiti = 0.0;
    }

    private void odustaniBTN_Clicked() {
        ((Stage) odustaniBTN.getScene().getWindow()).close();
    }

    private void stampajBTN_Clicked() {
        if (racun.getUkupnaCijena() != 0) {
            racun.setDatumIzdavanja(new Date());
            RacunAdapter.unesi(racun);
        } else {
            neispravanOdabir("Na racunu nema stavki!");
        }

    }

    private void neispravanOdabir(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informacioni dijalog");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

}
