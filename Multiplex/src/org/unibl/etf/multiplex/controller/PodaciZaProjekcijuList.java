
package org.unibl.etf.multiplex.controller;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PodaciZaProjekcijuList {
    @FXML
    private Label trajanjeLBL;
    @FXML
    private Label cijenaLBL;
    @FXML
    private ImageView slikaIMG;
    @FXML
    private Label naslovFilmaLBL;
    @FXML
    private Label opisFilmLBL;
    @FXML
    private AnchorPane projekcijaANP;
    @FXML
    private Label salaLBL;
    @FXML
    private Label vrijemeLBL;

    public PodaciZaProjekcijuList(String slika, String naslov, String opis,String vrijeme, String sala, String trajanje, String cijena) {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/ProjekcijaIzListe.fxml"));
        fxmlLoader.setController((Object)this);
        try {
            fxmlLoader.load();
        }
        catch (IOException ex) {
            Logger.getLogger(PodaciZaProjekcijuList.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.slikaIMG.setImage(new Image("file:" + slika));
        this.naslovFilmaLBL.setText(naslov);
        this.naslovFilmaLBL.setContentDisplay(ContentDisplay.CENTER);
        this.opisFilmLBL.setText(opis);
        this.opisFilmLBL.setContentDisplay(ContentDisplay.TOP);
        this.trajanjeLBL.setText("Trajanje: " + trajanje + " min");
        this.cijenaLBL.setText("Cijena: " + cijena + " KM");
        this.vrijemeLBL.setText("Vrijeme: " + vrijeme);
        this.salaLBL.setText("Sala: " + sala);
    }

    public AnchorPane getFXMLPrikaz() {
        return this.projekcijaANP;
    }
}

