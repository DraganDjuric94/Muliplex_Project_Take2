/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.unibl.etf.model.adapter.ArtikalAdapter;
import org.unibl.etf.model.adapter.KartaAdapter;
import org.unibl.etf.model.domain.oo.ArtikalOO;
import org.unibl.etf.model.domain.oo.KartaOO;
import org.unibl.etf.model.domain.oo.RacunOO;
import org.unibl.etf.model.domain.oo.StavkaOO;

/**
 * FXML Controller class
 *
 * @author Ognjen
 */
public class HranaController implements Initializable {

    @FXML
    private ListView<StavkaOO> listaDodatihArtikala;
    @FXML
    private ListView<ArtikalOO> listaUkupnoArtikala;
    @FXML
    private Label ukupnoLBL;
    @FXML
    private TextField pretraziTXT;
    @FXML
    private TextField kolicinaTXT;
    @FXML
    private Button pretraziBTN;
    @FXML
    private Button dodajBTN;
    @FXML
    private Button nazadBTN;
    @FXML
    private Button obrisiBTN;
    @FXML
    private ObservableList<ArtikalOO> listaUkupnoArtikalaOBS;
    ObservableList<StavkaOO> listaDodatihArtikalaOBS;

    int kolicina = 0;
    Double ukupnaCijena;
    Double ukupnoZaPlatiti = 0.0;
    String pretragaTXT;
    ArrayList<ArtikalOO> ukupnoArtikli = new ArrayList<>();
    RacunOO racun;

    public HranaController(ObservableList listaDodatihArtikalaOBS, RacunOO racun) {
        this.listaDodatihArtikalaOBS = listaDodatihArtikalaOBS;
        this.racun = racun;
    }
    
    public void update() {
        ArrayList<ArtikalOO> temp = ArtikalAdapter.preuzmiPoTipu("Hrana");
        ObservableList<ArtikalOO> kar = FXCollections.observableArrayList();
        for (ArtikalOO k : temp) {
            if (!k.getNaziv().toLowerCase().startsWith(pretraziTXT.getText().toLowerCase())) continue;
            kar.add(k);
        }
        if (!kar.equals(this.listaUkupnoArtikalaOBS)) {
            this.listaUkupnoArtikalaOBS.clear();
            this.listaUkupnoArtikalaOBS.addAll(kar);
            System.out.println("update!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Timer t = new Timer();
        
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        update();
                    }
                });
            }
        }, 0, 5000);
        
        dodajBTN.requestFocus();
        setFieldForIntegersOnly(kolicinaTXT);
        if (racun.getUkupnaCijena() == 0.0) {
            ukupnoLBL.setVisible(false);
        } else {
            ukupnoLBL.setText("Ukupno za platiti: " + racun.getUkupnaCijena() + " KM");
        }

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

        ukupnoArtikli.addAll(ArtikalAdapter.preuzmiPoTipu("Hrana"));
        listaUkupnoArtikalaOBS = FXCollections.observableArrayList(ukupnoArtikli);
        listaUkupnoArtikala.setItems(listaUkupnoArtikalaOBS);
        listaUkupnoArtikala.setCellFactory(lv -> new ListCell<ArtikalOO>() {
            @Override
            public void updateItem(ArtikalOO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    PodaciZaArtikliList podaci = new PodaciZaArtikliList(item.getNaziv(), item.getKolicinaNaStanju().toString(), item.getCijena().toString());
                    AnchorPane fxmlPrikaz = podaci.getFXMLView();
                    fxmlPrikaz.prefWidthProperty().bindBidirectional(listaUkupnoArtikala.prefWidthProperty());
                    setGraphic(fxmlPrikaz);

                }
            }
        });

        obrisiBTN.setOnAction(e -> obrisiBTN_Clicked());
        dodajBTN.setOnAction(e -> dodajBTN_Clicked());
        pretraziBTN.setOnAction(e -> pretraziBTN_Clicked());
        nazadBTN.setOnAction(e -> nazadBTN_Clicked());
        pretraziTXT.setOnKeyReleased(e -> pretraziBTN_Clicked());

    }

    private void dodajBTN_Clicked() {
        if (!kolicinaTXT.getText().isEmpty()) {
            kolicina = Integer.parseInt(kolicinaTXT.getText());
        }
        ArtikalOO artikal = listaUkupnoArtikala.getSelectionModel().getSelectedItem();
        if (kolicina == 0 || artikal == null || (artikal != null && artikal.getKolicinaNaStanju() < kolicina)) {
            neispravanOdabir("Niste odabrali stavku za dodavanje na racun ili niste unijeli odgovarajuću kolicinu!");
            return;
        }

        int pom = -1;
        for (int i = 0; i < listaDodatihArtikalaOBS.size(); i++) {
            if (listaDodatihArtikalaOBS.get(i).getArtikal().getArtikalId().equals(artikal.getArtikalId())) {
                pom = i;
            }
        }
        ukupnaCijena = kolicina * artikal.getCijena();
        StavkaOO stavka = new StavkaOO(null, kolicina, ukupnaCijena, artikal);
        if (pom != (-1)) {
            listaDodatihArtikalaOBS.remove(pom);
            racun.getStavke().remove(pom);
            listaDodatihArtikalaOBS.add(pom, stavka);
            racun.getStavke().add(pom, stavka);
        } else {
            listaDodatihArtikalaOBS.add(stavka);
            racun.getStavke().add(stavka);
        }

        for (int i = 0; i < racun.getStavke().size(); i++) {
            ukupnoZaPlatiti += racun.getStavke().get(i).getUkupnaCijena();
        }
        ukupnoLBL.setVisible(true);
        ukupnoLBL.setText("Ukupno za platiti: " + ukupnoZaPlatiti.toString() + "KM");
        racun.setUkupnaCijena(ukupnoZaPlatiti);
        kolicinaTXT.clear();
        kolicina = 0;
        ukupnoZaPlatiti = 0.0;

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
            ukupnoLBL.setText("Ukupno za platiti: " + ukupnoZaPlatiti.toString() + "KM");
        }
        racun.setUkupnaCijena(ukupnoZaPlatiti);
        ukupnoZaPlatiti = 0.0;
    }

    private void pretraziBTN_Clicked() {
       // if (!pretraziTXT.getText().isEmpty()) {
            pretragaTXT = pretraziTXT.getText();
            listaUkupnoArtikalaOBS.clear();
            ArrayList<ArtikalOO> artikli = ArtikalAdapter.preuzmiPoTipu("Hrana");
            for (ArtikalOO artikal : artikli) {
                if (artikal.getNaziv().toLowerCase().startsWith(pretragaTXT.toLowerCase())) {
                    listaUkupnoArtikalaOBS.add(artikal);
                }
            }
      /*  } else {
            listaUkupnoArtikalaOBS.clear();
            listaUkupnoArtikalaOBS.addAll(ArtikalAdapter.preuzmiPoTipu("Hrana"));
        }*/

    }

    private void nazadBTN_Clicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/multiplex/fxml/Racun.fxml"));

        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(RacunController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Racun");
        stage.setResizable(false);
        ((Stage) nazadBTN.getScene().getWindow()).close();
    }

    private void setFieldForIntegersOnly(TextField input) {
        input.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("^[1-9]+[0-9]*")) {
                if (newValue.length() != 0) {
                    input.setText(oldValue);
                }
            }
        });
    }

    private void neispravanOdabir(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informacioni dijalog");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public RacunOO preuzmiRacun() {
        return racun;
    }

}
