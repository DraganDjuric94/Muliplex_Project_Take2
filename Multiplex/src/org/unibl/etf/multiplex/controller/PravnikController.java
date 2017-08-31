/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.unibl.etf.model.adapter.ZaposleniAdapter;
import org.unibl.etf.model.domain.oo.ProjekcijaOO;
import org.unibl.etf.model.domain.oo.ZaposleniOO;

/**
 *
 * @author juhu
 */
public class PravnikController implements Initializable{

    @FXML
    private Button opremaAddBTN;

    @FXML
    private TableColumn<?, ?> transakcijaIznosColumn;

    @FXML
    private TableColumn<?, ?> projekcijaIdCOL;

    @FXML
    private TableColumn<ZaposleniOO, String> zaposleniJmbgColumn;

    @FXML
    private Tab transakcijaTAB;

    @FXML
    private Button artikalEraseBTN;

    @FXML
    private TableView<?> artikalTable;

    @FXML
    private Button izmijeniProjekcijaBTN;

    @FXML
    private TableColumn<?, ?> projekcijaSalaCOL;

    @FXML
    private TableColumn<?, ?> transakcijaDatumColumn;

    @FXML
    private Button opremaEditBTN;

    @FXML
    private TabPane taboviTPN;

    @FXML
    private TableColumn<?, ?> fakturaDatumColumn;

    @FXML
    private TextField fakturaSearchTXT;

    @FXML
    private Button dodajFilmBTN;

    @FXML
    private Button opremaEraseBTN;

    @FXML
    private TextField opremaSearchTXT;

    @FXML
    private TableColumn<ZaposleniOO, String> zaposleniImeColumn;

    @FXML
    private TextField pretraziFilmTXT;

    @FXML
    private TableColumn<?, ?> opremaNazivOpremeColumn;

    @FXML
    private Button fakturaEditBTN;

    @FXML
    private TableColumn<?, ?> fakturaJedinicaMjereColumn;

    @FXML
    private TableColumn<?, ?> filmTrajanjeCOL;

    @FXML
    private Tab artikalTAB;

    @FXML
    private TextField transakcijaSearchTXT;

    @FXML
    private Button obrisiProjekcijaBTN;

    @FXML
    private TableColumn<?, ?> opremaBrojInventaraColumn;

    @FXML
    private TableColumn<?, ?> artikalNazivColumn;

    @FXML
    private Tab opremaTAB;

    @FXML
    private Button opremaSearchBTN;

    @FXML
    private TableColumn<ZaposleniOO, Double> zaposleniPlataColumn;

    @FXML
    private TableView<?> opremaTable;

    @FXML
    private TableColumn<?, ?> datVrijCOL;

    @FXML
    private TableColumn<?, ?> filmOpisCOL;

    @FXML
    private Button pretraziFilmBTN;

    @FXML
    private TableColumn<?, ?> filmIdCOL;

    @FXML
    private Button transakcijaSearchBTN;

    @FXML
    private TableColumn<ZaposleniOO, String> zaposleniPozicijaColumn;

    @FXML
    private Button transakcijaEditBTN;

    @FXML
    private Tab projekcijaTAB;

    @FXML
    private TableColumn<?, ?> projekcijaCijenaCOL;

    @FXML
    private TableColumn<?, ?> artikalBarkodColumn;

    @FXML
    private TableColumn<?, ?> filmZanroviCOL;

    @FXML
    private Button zaposleniEditBTN;

    @FXML
    private Button fakturaObrisiBTN;

    @FXML
    private TableView<?> tabelaProjekcijaTBL;

    @FXML
    private TableColumn<?, ?> transakcijaVrstaColumn;

    @FXML
    private TableColumn<?, ?> artikalKolicinaColumn;

    @FXML
    private TableColumn<?, ?> opremaIspravnostColumn;

    @FXML
    private TableColumn<?, ?> artikalTipColumn;

    @FXML
    private Tab fakturaTAB;

    @FXML
    private TableView<?> fakturaTable;

    @FXML
    private TableColumn<?, ?> filmNazivCOL;

    @FXML
    private TableColumn<?, ?> fakturaBrojRacunaColumn;

    @FXML
    private Button artikalSearchBTN;

    @FXML
    private TextField zaposleniSearchTXT;

    @FXML
    private Button transakcijaEraseBTN;

    @FXML
    private TableColumn<ZaposleniOO, String> zaposleniPrezimeColumn;

    @FXML
    private TextField artikalSearchTXT;

    @FXML
    private TableColumn<?, ?> fakturaRacunIzdaoColumn;

    @FXML
    private TableColumn<?, ?> filmSLikaCOL;

    @FXML
    private Button dodajProjekcijaBTN;

    @FXML
    private TableView<ZaposleniOO> zaposleniTable;

    @FXML
    private TextField pretraziProjekcijaTXT;

    @FXML
    private Button fakturaAddBTN;

    @FXML
    private Button obrisiFilmBTN;

    @FXML
    private Button artikalEditBTN;

    @FXML
    private TableView<?> traksancijaTable;

    @FXML
    private Button transakcijaAddBTN;

    @FXML
    private Button artikalAddBTN;

    @FXML
    private TableColumn<?, ?> fakturaKolicinaColumn;

    @FXML
    private TableColumn<?, ?> fakturaCijenaColumn;

    @FXML
    private TableColumn<?, ?> transakcijaPosiljalacColumn;

    @FXML
    private Button zaposleniAddBTN;

    @FXML
    private Tab zaposleniTAB;

    @FXML
    private Button fakturaSearchBTN;

    @FXML
    private TableColumn<?, ?> fakturaNazivRobeColumn;

    @FXML
    private TableColumn<?, ?> projekcijaNazivfCOL;

    @FXML
    private TableColumn<?, ?> transakcijaPrimalacColumn;

    @FXML
    private TableView<?> filmoviTBL;

    @FXML
    private Button pretraziProjekcijaBTN;

    @FXML
    private TableColumn<?, ?> artikalCijenaColumn;

    @FXML
    private Button zaposleniSearchBTN;

    @FXML
    private Button zaposleniEraseBTN;

    @FXML
    private Tab filmTAB;

    @FXML
    private Button izmijeniFilmBTN;
    
    private ObservableList<ZaposleniOO> zaposleni = FXCollections.observableArrayList();
    
    public void pretraziZaposlenog(String tekst){
        ArrayList<ZaposleniOO> tempZ = ZaposleniAdapter.preuzmiSve();
        zaposleni.clear();
        for(ZaposleniOO zap : tempZ){
            if(zap.getIme().toLowerCase().contains(tekst.toLowerCase()) || zap.getZaposleniId().toString().contains(tekst)){
                zaposleni.add(zap);
            }
        }
    }
    
    public void preuzmiSveZaposlene(){
        zaposleni.clear();
        zaposleni.addAll(ZaposleniAdapter.preuzmiSve());
    }
    
    private void initializeZaposleniPravnik() {
        
        preuzmiSveZaposlene();
        
        zaposleniImeColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
        zaposleniPrezimeColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        zaposleniJmbgColumn.setCellValueFactory(new PropertyValueFactory<>("jmbg"));
        zaposleniPlataColumn.setCellValueFactory(new PropertyValueFactory<>("plata"));
        zaposleniPozicijaColumn.setCellValueFactory(new PropertyValueFactory<>("nazivPozicije"));
        
        zaposleniTable.setItems(zaposleni);
        
        zaposleniSearchBTN.setOnAction((event) -> {
            pretraziZaposlenog(zaposleniSearchTXT.getText());
        });
        
        zaposleniSearchTXT.setOnKeyReleased((event) -> {
            pretraziZaposlenog(zaposleniSearchTXT.getText());
        });
        
        zaposleniEditBTN.setOnAction((event) -> {
            int index = zaposleniTable.getSelectionModel().getSelectedIndex();
            System.out.println(index);
            ZaposleniOO zap = zaposleni.get(index);
            System.out.println(zap);
            ZaposleniFormController control = new ZaposleniFormController(zap, true);
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/ZaposleniForm.fxml"));
            try {
                loader.setController((Object)control);
                Parent root = (Parent)loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("");
                stage.setResizable(false);
                stage.show(); 
            } catch (IOException ex) {
                Logger.getLogger(PravnikController.class.getName()).log(Level.SEVERE, null, ex);
            }    
        });
        
        zaposleniAddBTN.setOnAction((event) -> {
            int index = zaposleniTable.getSelectionModel().getSelectedIndex();
            ZaposleniFormController control = new ZaposleniFormController(zaposleni.get(index), false);
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/ZaposleniForm.fxml"));
            try {
                loader.setController((Object)control);
                Parent root = (Parent)loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("");
                stage.setResizable(false);
                stage.show(); 
            } catch (IOException ex) {
                Logger.getLogger(PravnikController.class.getName()).log(Level.SEVERE, null, ex);
            }      
        });
        
        zaposleniEraseBTN.setOnAction((event) -> {
            ZaposleniAdapter.obrisi(zaposleniTable.getSelectionModel().getSelectedItem().getZaposleniId());
        });
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
       initializeZaposleniPravnik();
    }
    
    
    
    
}
