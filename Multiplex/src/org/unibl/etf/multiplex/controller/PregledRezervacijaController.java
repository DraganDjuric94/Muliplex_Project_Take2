package org.unibl.etf.multiplex.controller;

import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.unibl.etf.model.adapter.KartaAdapter;
import org.unibl.etf.model.domain.oo.FilmOO;
import org.unibl.etf.model.domain.oo.KartaOO;
import org.unibl.etf.model.domain.oo.ProjekcijaOO;
import org.unibl.etf.multiplex.controller.PodaciZaRezervacijuList;

public class PregledRezervacijaController
implements Initializable {
    @FXML
    private Button pretraziBTN;
    @FXML
    private ListView<KartaOO> rezervacijeLST;
    @FXML
    private Button potvrdiBTN;
    @FXML
    private TextField pretraziTXT;
    @FXML
    private Button otkaziBTN;
    private ObservableList<KartaOO> rezervacije = FXCollections.observableArrayList();

    public void preuzmiRezervacije() {
        ArrayList<KartaOO> temp = KartaAdapter.preuzmiSve();
        for (KartaOO k : temp) {
            if (!k.getRezervisana().booleanValue()) continue;
            this.rezervacije.add(k);
        }
    }

    public void pretrazi(String text) {
        ArrayList<KartaOO> temp = KartaAdapter.preuzmiSve();
        this.rezervacije.clear();
        for (KartaOO k : temp) {
            if (!k.getRezervisana().booleanValue() || !k.getKartaId().toString().startsWith(text) && !k.getProjekcija().getFilm().getNaziv().toLowerCase().startsWith(text.toLowerCase())) continue;
            this.rezervacije.add(k);
        }
    }

    public void update() {
        ArrayList<KartaOO> temp = KartaAdapter.preuzmiSve();
        ObservableList kar = FXCollections.observableArrayList();
        for (KartaOO k : temp) {
            if (!k.getRezervisana().booleanValue() || !k.getKartaId().toString().startsWith(this.pretraziTXT.getText()) && !k.getProjekcija().getFilm().getNaziv().toLowerCase().startsWith(this.pretraziTXT.getText().toLowerCase())) continue;
            kar.add(k);
        }
        if (!kar.equals(this.rezervacije)) {
            this.rezervacije.clear();
            this.rezervacije.addAll(kar);
            System.out.println("update!");
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.preuzmiRezervacije();
        this.rezervacijeLST.setItems(this.rezervacije);
        this.pretraziBTN.setOnAction(event -> {
            this.pretrazi(this.pretraziTXT.getText());
        }
        );
        this.pretraziTXT.setOnKeyReleased(event -> {
            this.pretrazi(this.pretraziTXT.getText());
        }
        );
        Timer t = new Timer();
        t.schedule(new TimerTask(){

            @Override
            public void run() {
                Platform.runLater((Runnable)new Runnable(){

                    @Override
                    public void run() {
                        PregledRezervacijaController.this.update();
                    }
                });
            }

        }, 0, 5000);
        this.rezervacijeLST.setCellFactory(listView -> new ListCell<KartaOO>(){

            public void updateItem(KartaOO karta, boolean empty) {
                super.updateItem(karta, empty);
                if (empty) {
                    this.setText(null);
                    this.setGraphic(null);
                } else {
                    ProjekcijaOO proj = karta.getProjekcija();
                    //proj.getFilm().setSlika("images/testSlika.jpg");
                    PodaciZaRezervacijuList podaci = new PodaciZaRezervacijuList(proj.getFilm().getSlika(), proj.getFilm().getNaziv(), proj.getFilm().getOpis(),proj.getDatumVrijeme().toString(),proj.getSala().getSalaId().toString(), proj.getFilm().getTrajanje().toString(), proj.getCijenaKarte().toString(), "Broj rezervacije: " + karta.getKartaId().toString());
                    AnchorPane fxmlPrikaz = podaci.getFXMLPrikaz();
                    fxmlPrikaz.prefWidthProperty().bindBidirectional((Property)PregledRezervacijaController.this.rezervacijeLST.prefWidthProperty());
                    this.setGraphic((Node)fxmlPrikaz);
                }
            }
        });
        this.otkaziBTN.setOnAction(event -> {
            KartaOO karta = (KartaOO)this.rezervacijeLST.getSelectionModel().getSelectedItem();
            if(karta != null){
                KartaAdapter.obrisi(karta.getKartaId());
                this.update();
            }
        }
        );
        this.potvrdiBTN.setOnAction(event -> {
            KartaOO karta = (KartaOO)this.rezervacijeLST.getSelectionModel().getSelectedItem();
            if(karta != null){
                karta.setDatumIzdavanja(new Date());
                karta.setRezervisana(false);
                KartaAdapter.izmijeni(karta);
                this.update();
            }
        }
        );
    }

}

