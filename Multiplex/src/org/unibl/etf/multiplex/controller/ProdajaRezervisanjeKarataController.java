package org.unibl.etf.multiplex.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import org.unibl.etf.model.adapter.ProjekcijaAdapter;
import org.unibl.etf.model.adapter.ZanrAdapter;
import org.unibl.etf.model.domain.oo.FilmOO;
import org.unibl.etf.model.domain.oo.ProjekcijaOO;
import org.unibl.etf.model.domain.oo.SalaOO;
import org.unibl.etf.model.domain.oo.ZanrOO;
import org.unibl.etf.multiplex.controller.IzborSjedistaController;
import org.unibl.etf.multiplex.controller.PodaciZaProjekcijuList;

public class ProdajaRezervisanjeKarataController
implements Initializable {
    @FXML
    private ListView<ProjekcijaOO> projekcijeLST;
    @FXML
    private Button pretraziBTN;
    @FXML
    private Button izaberiBTN;
    @FXML
    private TextField pretraziTXT;
    @FXML
    private ChoiceBox<String> zanrCBX;
    @FXML
    private Button pregledajRezervacijeBTN;
    private ObservableList<ProjekcijaOO> projekcije;
    private ObservableList<String> zanrovi;

    public void preuzmiSveProjekcije() {
        this.projekcije.clear();
        this.projekcije.addAll(ProjekcijaAdapter.preuzmiSve());
        /*for (ProjekcijaOO p : this.projekcije) {
            p.getFilm().setSlika("images/testSlika.jpg");
        }*/
    }

    public void preuzmiSveZanrove() {
        this.zanrovi.clear();
        ArrayList<ZanrOO> tempList = ZanrAdapter.preuzmiSve();
        this.zanrovi.add("Svi");
        for (ZanrOO z : tempList) {
            this.zanrovi.add(z.getNaziv());
        }
    }

    public void pretrazi(String tekst) {
        this.projekcije.clear();
        this.projekcije.addAll(ProjekcijaAdapter.preuzmiPoNazivuFilma(tekst));
        if (!((String)this.zanrCBX.getSelectionModel().getSelectedItem()).equals("Svi")) {
            String zanr = (String)this.zanrCBX.getSelectionModel().getSelectedItem();
            Iterator it = this.projekcije.iterator();
            while (it.hasNext()) {
                boolean imaZanr = false;
                for (ZanrOO z : ((ProjekcijaOO)it.next()).getFilm().getZanrovi()) {
                    if (!zanr.toLowerCase().equals(z.getNaziv().toLowerCase())) continue;
                    imaZanr = true;
                    break;
                }
                if (imaZanr) continue;
                it.remove();
            }
        }
        /*for (ProjekcijaOO p : this.projekcije) {
            p.getFilm().setSlika("images/testSlika.jpg");
        }*/
    }

    public void update() {
        ObservableList<ProjekcijaOO> proj = FXCollections.observableArrayList();
        proj.addAll(ProjekcijaAdapter.preuzmiPoNazivuFilma(this.pretraziTXT.getText()));
        if (!((String)this.zanrCBX.getSelectionModel().getSelectedItem()).equals("Svi")) {
            String zanr = (String)this.zanrCBX.getSelectionModel().getSelectedItem();
            Iterator it = proj.iterator();
            while (it.hasNext()) {
                boolean imaZanr = false;
                for (ZanrOO z : ((ProjekcijaOO)it.next()).getFilm().getZanrovi()) {
                    if (!zanr.toLowerCase().equals(z.getNaziv().toLowerCase())) continue;
                    imaZanr = true;
                    break;
                }
                if (imaZanr) continue;
                it.remove();
            }
        }
        /*for (ProjekcijaOO p : proj) {
            p.getFilm().setSlika("images/testSlika.jpg");
        }*/
        if (!proj.equals(this.projekcije)) {
            this.projekcije.clear();
            this.projekcije.addAll((Collection)proj);
            System.out.println("update!");
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.projekcije = FXCollections.observableArrayList();
        this.zanrovi = FXCollections.observableArrayList();
        this.projekcijeLST.setItems(this.projekcije);
        this.zanrCBX.setItems(this.zanrovi);
        this.preuzmiSveProjekcije();
        this.preuzmiSveZanrove();
        this.zanrCBX.getSelectionModel().select(0);
        Timer t = new Timer();
        t.schedule(new TimerTask(){

            @Override
            public void run() {
                Platform.runLater((Runnable)new Runnable(){

                    @Override
                    public void run() {
                        ProdajaRezervisanjeKarataController.this.update();
                    }
                });
            }

        }, 0, 5000);
        this.projekcijeLST.setCellFactory(listView -> new ListCell<ProjekcijaOO>(){

            public void updateItem(ProjekcijaOO proj, boolean empty) {
                super.updateItem(proj, empty);
                if (empty) {
                    this.setText(null);
                    this.setGraphic(null);
                } else {
                    PodaciZaProjekcijuList podaci = new PodaciZaProjekcijuList(proj.getFilm().getSlika(), proj.getFilm().getNaziv(), proj.getFilm().getOpis(),proj.getDatumVrijeme().toString(),proj.getSala().getSalaId().toString(), proj.getFilm().getTrajanje().toString(), proj.getCijenaKarte().toString());
                    AnchorPane fxmlPrikaz = podaci.getFXMLPrikaz();
                    fxmlPrikaz.prefWidthProperty().bindBidirectional((Property)ProdajaRezervisanjeKarataController.this.projekcijeLST.prefWidthProperty());
                    this.setGraphic((Node)fxmlPrikaz);
                }
            }
        });
        this.pretraziBTN.setOnAction(event -> {
            this.pretrazi(this.pretraziTXT.getText());
        }
        );
        this.pretraziTXT.setOnKeyReleased(event -> {
            this.pretrazi(this.pretraziTXT.getText());
        }
        );
        this.pregledajRezervacijeBTN.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/PregledRezervacija.fxml"));
            try {
                Parent root = (Parent)loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("");
                Stage ja = (Stage)this.pregledajRezervacijeBTN.getScene().getWindow();
                ja.close();
                stage.setOnCloseRequest(event1 -> {
                    ja.show();
                }
                );
                stage.show();
            }
            catch (IOException ex) {
                Logger.getLogger(ProdajaRezervisanjeKarataController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
        this.izaberiBTN.setOnAction(event -> {
            IzborSjedistaController control = new IzborSjedistaController(((ProjekcijaOO)this.projekcijeLST.getSelectionModel().getSelectedItem()).getProjekcijaId());
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/IzborSjedista.fxml"));
            try {
                loader.setController((Object)control);
                Parent root = (Parent)loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("");
                int brVrsta = ((ProjekcijaOO)this.projekcijeLST.getSelectionModel().getSelectedItem()).getSala().getBrojRedova();
                int brKolona = ((ProjekcijaOO)this.projekcijeLST.getSelectionModel().getSelectedItem()).getSala().getBrojKolona();
                double sirina = 650.0 * ((double)brKolona / (double)brVrsta);
                stage.setWidth(sirina);
                stage.setHeight(650.0);
                stage.setResizable(false);
                stage.setOnCloseRequest(event1 -> {
                }
                );
                stage.show();
            }
            catch (IOException ex) {
                Logger.getLogger(ProdajaRezervisanjeKarataController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
    }

}

