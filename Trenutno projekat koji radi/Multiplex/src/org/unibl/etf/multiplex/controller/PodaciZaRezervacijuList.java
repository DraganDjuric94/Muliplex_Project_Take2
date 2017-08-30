/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javafx.fxml.FXML
 *  javafx.fxml.FXMLLoader
 *  javafx.scene.control.ContentDisplay
 *  javafx.scene.control.Label
 *  javafx.scene.image.Image
 *  javafx.scene.image.ImageView
 *  javafx.scene.layout.AnchorPane
 */
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
import org.unibl.etf.multiplex.controller.PodaciZaProjekcijuList;

public class PodaciZaRezervacijuList {
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
    private Label brojRezervacijeLBL;

    public PodaciZaRezervacijuList(String slika, String naslov, String opis, String trajanje, String cijena, String idRezervacije) {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/RezervacijaIzListe.fxml"));
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
        this.brojRezervacijeLBL.setText(idRezervacije);
    }

    public AnchorPane getFXMLPrikaz() {
        return this.projekcijaANP;
    }
}

