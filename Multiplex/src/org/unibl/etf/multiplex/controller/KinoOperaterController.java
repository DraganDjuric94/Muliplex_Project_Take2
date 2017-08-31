/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.io.IOException;
import org.unibl.etf.model.adapter.OpremaAdapter;
import java.net.URL;
import java.util.ArrayList;
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
import org.unibl.etf.model.domain.oo.OpremaOO;
import org.unibl.etf.multiplex.controller.PravnikController;
import org.unibl.etf.multiplex.controller.OpremaFormController;

/**
 * FXML Controller class
 *
 * @author Miloš
 */
public class KinoOperaterController implements Initializable {

    @FXML
    private TabPane taboviTPN;
    @FXML
    private Tab opremaTAB;
    @FXML
    private Button opremaAddBTN;
    @FXML
    private Button opremaEraseBTN;
    @FXML
    private TableView<OpremaOO> opremaTable;
    @FXML
    private TableColumn<OpremaOO, String> opremaBrojInventaraColumn;
    @FXML
    private TableColumn<OpremaOO, String> opremaNazivOpremeColumn;
    @FXML
    private TableColumn<OpremaOO, Boolean> opremaIspravnostColumn;
    @FXML
    private TextField opremaSearchTXT;
    @FXML
    private Button opremaSearchBTN;
    @FXML
    private Button opremaEditBTN;
   

    private ObservableList<OpremaOO> oprema = FXCollections.observableArrayList();
    
    public void pretraziOpremu(String tekst){
        ArrayList<OpremaOO> temp = OpremaAdapter.preuzmiSve();
        oprema.clear();
        for(OpremaOO it : temp){
            if(it.getNaziv().toLowerCase().contains(tekst.toLowerCase()) || it.getBrojInventara().toLowerCase().contains(tekst.toLowerCase())){
                oprema.add(it);
            }
        }
    }
    
    public void preuzmiSvuOpremu(){
        oprema.clear();
        oprema.addAll(OpremaAdapter.preuzmiSve());
    }
    
    private void initializeOpremaTab() {
    
        preuzmiSvuOpremu();
        
        opremaBrojInventaraColumn.setCellValueFactory(new PropertyValueFactory<>("brojInventara"));
        opremaNazivOpremeColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        opremaIspravnostColumn.setCellValueFactory(new PropertyValueFactory<>("ispravnost"));
        
        opremaTable.setItems(oprema);
        
        opremaSearchBTN.setOnAction((event) -> {
            pretraziOpremu(opremaSearchTXT.getText());
        });
        
        opremaSearchTXT.setOnKeyReleased((event) -> {
            pretraziOpremu(opremaSearchTXT.getText());
        });
        
        opremaEditBTN.setOnAction((event) -> {
            int index = opremaTable.getSelectionModel().getSelectedIndex();
            // TODO should be in try catch, ArrayIndexOutOfBounds 
            OpremaOO it = oprema.get(index);
            OpremaFormController control = new OpremaFormController(it, true);

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/OpremaForm.fxml"));
            try {
                loader.setController((Object)control);
                Parent root = (Parent)loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Administracija opreme");
                stage.setResizable(false);
                stage.show(); 
            } catch (IOException ex) {
                Logger.getLogger(KinoOperaterController.class.getName()).log(Level.SEVERE, null, ex);
            }    
        });
        
        opremaAddBTN.setOnAction((event) -> {
            int index = opremaTable.getSelectionModel().getSelectedIndex();
            OpremaFormController opremaController = new OpremaFormController(new OpremaOO(), false);
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/OpremaForm.fxml"));
            try {
                loader.setController((Object)opremaController);
                Parent root = (Parent)loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Administracija opreme");
                stage.setResizable(false);
                stage.show(); 
            } catch (IOException ex) {
                Logger.getLogger(PravnikController.class.getName()).log(Level.SEVERE, null, ex);
            }      
        });
        
        opremaEraseBTN.setOnAction((event) -> {
            // TODO postaviti try-catch blokove uz svaki poziv u adapteru
            OpremaAdapter.obrisi(opremaTable.getSelectionModel().getSelectedItem().getOpremaId());
            
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initializeOpremaTab();
    }    
    
}
