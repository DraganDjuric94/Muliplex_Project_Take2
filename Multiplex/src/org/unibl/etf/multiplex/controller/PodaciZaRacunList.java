package org.unibl.etf.multiplex.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author juhu
 */
public class PodaciZaRacunList {
    @FXML
    private Label kolicinaLBL;

    @FXML
    private Label nazivLBL;

    @FXML
    private Label ukupnoLBL;
    
    @FXML
    private AnchorPane stavkaANP;
    
    public PodaciZaRacunList(String naziv, String kolicina, String ukupno){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/multiplex/fxml/PodaciZaRacun.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(PodaciZaRacunList.class.getName()).log(Level.SEVERE, null, ex);
        }
        nazivLBL.setText(naziv);
        kolicinaLBL.setText("x" + kolicina);
        ukupnoLBL.setText("ukupno: " + ukupno + " KM");
    }
    
    public AnchorPane getFXMLView(){
        return stavkaANP;
    }
}
