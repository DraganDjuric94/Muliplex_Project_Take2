
package org.unibl.etf.multiplex.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.unibl.etf.model.adapter.KartaAdapter;
import org.unibl.etf.model.adapter.ProjekcijaAdapter;
import org.unibl.etf.model.domain.oo.KartaOO;
import org.unibl.etf.model.domain.oo.ProjekcijaOO;
import org.unibl.etf.model.domain.oo.SalaOO;
import org.unibl.etf.model.domain.oo.SjedisteOO;

public class IzborSjedistaController
implements Initializable {
    @FXML
    private Button stampajBTN;
    @FXML
    private Button rezervisiBTN;
    @FXML
    private AnchorPane gridSjedistaANP;
    private ProjekcijaOO projekcija;
    private Integer brKolona;
    private Integer brVrsta;
    private ArrayList<PomocnoSjediste> svaSjedista = new ArrayList();
    private GridPane sjedistaGRD = new GridPane();

    public IzborSjedistaController(Integer ProjekcijaId) {
        this.projekcija = ProjekcijaAdapter.preuzmiPoId(ProjekcijaId);
    }

    public void preuzmiSjedista() {
        this.brKolona = this.projekcija.getSala().getBrojKolona();
        this.brVrsta = this.projekcija.getSala().getBrojRedova();
        ArrayList<SjedisteOO> zauzetaSjedista = new ArrayList<SjedisteOO>();
        ArrayList<KartaOO> karteZaProjekciju = KartaAdapter.preuzmiPoProjekcijaId(this.projekcija.getProjekcijaId());
        for (KartaOO k : karteZaProjekciju) {
            zauzetaSjedista.add(k.getSjediste());
        }
        for (int i = 1; i < this.brVrsta + 1; ++i) {
            for (int j = 1; j < this.brKolona + 1; ++j) {
                Boolean zauzeto = false;
                for (SjedisteOO sj : zauzetaSjedista) {
                    if (sj.getVrsta() != i || sj.getKolona() != j) continue;
                    zauzeto = true;
                    break;
                }
                this.svaSjedista.add(new PomocnoSjediste(i, j, zauzeto));
            }
        }
    }

    public void napraviKartu(boolean rezervacija) {
        ObservableList<Node> projSjed = this.sjedistaGRD.getChildren();
        ArrayList<String> izabranaSjedista = new ArrayList<String>();
        for (Node n : projSjed) {
            String boja = "#" + n.getStyle().split("#")[1];
            if ("#abe476".equals(boja)){
                System.out.println(new Integer(GridPane.getRowIndex((Node)n) + 1).toString() + "||" + new Integer(GridPane.getColumnIndex((Node)n) + 1).toString());
                izabranaSjedista.add(new Integer(GridPane.getRowIndex((Node)n) + 1).toString() + "||" + new Integer(GridPane.getColumnIndex((Node)n) + 1).toString());
                
            }
        }
        ArrayList izabraneKarte = new ArrayList();
        for (String s : izabranaSjedista) {
            String[] sjedisteRK = s.split("\\|\\|");
            KartaOO karta = new KartaOO();
            karta.setCijena(5.35);
            karta.setDatumIzdavanja(new Date());
            karta.setKartaId(null);
            karta.setProjekcija(this.projekcija);
            karta.setRezervisana(rezervacija);
            SjedisteOO sjediste = new SjedisteOO();
            /*sjediste.setVrsta(Integer.parseInt(sjedisteRK[0]));
            sjediste.setKolona(Integer.parseInt(sjedisteRK[1]));*/
            System.out.println(this.projekcija.getProjekcijaId());
            System.out.println(this.projekcija.getSala().getSalaId());
            for (SjedisteOO sj : this.projekcija.getSala().getSjedista()) {
                if (sj.getVrsta() == Integer.parseInt(sjedisteRK[0]) && sj.getKolona() == Integer.parseInt(sjedisteRK[1])){
                    sjediste = sj;
                    System.out.println("Brejno za: " + sjediste);
                    break;
                }
            }
            karta.setSjediste(sjediste);
            KartaAdapter.unesi(karta);
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        int i;
        this.preuzmiSjedista();
        this.sjedistaGRD.setHgap(10.0);
        this.sjedistaGRD.setVgap(10.0);
        this.sjedistaGRD.setGridLinesVisible(false);
        int numCols = this.brKolona;
        int numRows = this.brVrsta;
        for (i = 0; i < this.brVrsta; ++i) {
            for (int j = 0; j < this.brKolona; ++j) {
                StackPane square = new StackPane();
                String color = this.svaSjedista.get((int)(i * this.brKolona.intValue() + j)).zauzeto != false ? "#e48e76" : "#a3b3bf";
                square.setStyle("-fx-background-color: " + color);
                if (!"#e48e76".equals(color)) {
                    square.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        String stil = square.getStyle();
                        String[] splitovanStil = stil.split("#");
                        String boja = "#" + splitovanStil[1];
                        boja = !"#a3b3bf".equals(boja) ? "#a3b3bf" : "#abe476";
                        square.setStyle("-fx-background-color: " + boja);
                    }
                    );
                }
                this.sjedistaGRD.add((Node)square, j, i);
            }
        }
        for (i = 0; i < numCols; ++i) {
            this.sjedistaGRD.getColumnConstraints().add(new ColumnConstraints(5.0, -1.0, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
        }
        for (i = 0; i < numRows; ++i) {
            this.sjedistaGRD.getRowConstraints().add(new RowConstraints(5.0, -1.0, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
        AnchorPane.setBottomAnchor((Node)this.sjedistaGRD, (Double)10.0);
        AnchorPane.setTopAnchor((Node)this.sjedistaGRD, (Double)10.0);
        AnchorPane.setLeftAnchor((Node)this.sjedistaGRD, (Double)10.0);
        AnchorPane.setRightAnchor((Node)this.sjedistaGRD, (Double)10.0);
        this.gridSjedistaANP.getChildren().add(this.sjedistaGRD);
        this.stampajBTN.setOnAction(event -> {
            this.napraviKartu(false);
            Stage ja = (Stage)this.stampajBTN.getScene().getWindow();
            ja.close();
        }
        );
        this.rezervisiBTN.setOnAction(event -> {
            this.napraviKartu(true);
            Stage ja = (Stage)this.stampajBTN.getScene().getWindow();
            ja.close();
        }
        );
    }

    private class PomocnoSjediste {
        public Integer vrsta;
        public Integer kolona;
        public Boolean zauzeto;

        public PomocnoSjediste(Integer vrsta, Integer kolona, Boolean zauzeto) {
            this.vrsta = vrsta;
            this.kolona = kolona;
            this.zauzeto = zauzeto;
        }
    }

}

