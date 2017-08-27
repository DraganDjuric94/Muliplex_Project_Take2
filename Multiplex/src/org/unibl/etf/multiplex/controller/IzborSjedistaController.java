/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author juhu
 */
public class IzborSjedistaController implements Initializable {
    
    @FXML
    private Button stampajBTN;

    @FXML
    private Button rezervisiBTN;
    
    @FXML
    private AnchorPane gridSjedistaANP;

    private Integer ProjekcijaId;
    private Integer brKolona;
    private Integer brVrsta;
    private ArrayList<PomocnoSjediste> svaSjedista = new ArrayList<>();
    
    private class PomocnoSjediste{
        public Integer vrsta;
        public Integer kolona;
        public Boolean zauzeto;

        public PomocnoSjediste(Integer vrsta, Integer kolona, Boolean zauzeto) {
            this.vrsta = vrsta;
            this.kolona = kolona;
            this.zauzeto = zauzeto;
        }
    }

    public IzborSjedistaController(Integer ProjekcijaId) {
        this.ProjekcijaId = ProjekcijaId;
    }
    
    public void preuzmiSjedista(){
        //pronaci sve karte za ovu projekciju iz baze
        //oznaciti kakva su sva sjedista i napraviti niz istih
        
        brKolona = 9;
        brVrsta = 5;
        
        for(int i = 1; i < 6; i++){
            for(int j = 1; j < 10; j++){
                svaSjedista.add(new PomocnoSjediste(i, j, (i%3 + j%2 == 0) ? Boolean.TRUE : Boolean.FALSE));
            }
        }
        
        
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        preuzmiSjedista();
        
        GridPane sjedistaGRD = new GridPane();
        sjedistaGRD.setHgap(25);
        sjedistaGRD.setVgap(25);
        sjedistaGRD.setGridLinesVisible(false);
        
        final int numCols = brKolona ;
        final int numRows = brVrsta ;
        
        for(int i = 0; i < brVrsta; i++){
            for (int j = 0; j < brKolona; j++){
                StackPane square = new StackPane();
                CheckBox cb = new CheckBox("Red: " + (i+1) + "\nBroj: " + (j+1));
                String color ;
                cb.disableProperty().setValue(svaSjedista.get(i*brKolona + j).zauzeto);
                if (svaSjedista.get(i*brKolona + j).zauzeto) {
                    color = "#ffa293";
                } else {
                    color = "#dbeda6";
                }
                cb.setStyle("-fx-background-color: " + color+ ";");
                square.getChildren().add(cb);
                sjedistaGRD.add(square, j, i);
            }
        }
        
        for (int i = 0; i < numCols; i++) {
             sjedistaGRD.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
        }
        for (int i = 0; i < numRows; i++) {
            sjedistaGRD.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));         
        }
        
        AnchorPane.setBottomAnchor(sjedistaGRD, 0.0);
        AnchorPane.setTopAnchor(sjedistaGRD, 0.0);
        AnchorPane.setLeftAnchor(sjedistaGRD, 0.0);
        AnchorPane.setRightAnchor(sjedistaGRD, 0.0);
       
        gridSjedistaANP.getChildren().add(sjedistaGRD);
        
    }    
    
}
