/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.unibl.etf.model.domain.oo.ZaposleniOO;
import org.unibl.etf.model.adapter.ZaposleniAdapter;

/**
 * FXML Controller class
 *
 * @author wolfskrieg
 */
public class PregledZaposlenihController implements Initializable {

    @FXML
    private TableView<ZaposleniOO> zaposleniTable;
    @FXML
    private TableColumn<ZaposleniOO, String> imeColumn;
    @FXML
    private TableColumn<ZaposleniOO, String> prezimeColumn;
    @FXML
    private TableColumn<ZaposleniOO, String> jmbgColumn;
    @FXML
    private AnchorPane dataPane;
    @FXML
    private TextField imeTextField;
    @FXML
    private TextField prezimeTextField;
    @FXML
    private TextField jmbgTextField;
    @FXML
    private TextField plataTextField;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button addButton;
    @FXML
    private CheckBox aktivanCheck;

    private ObservableList<ZaposleniOO> zaposleni = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dataPane.setVisible(true);
        setDisableInputs(true);
        setResizableAndEditable(false);
        
        preuzmiSveZaposlene();
        zaposleniTable.setItems(zaposleni);
        
        imeColumn.setCellValueFactory(new PropertyValueFactory<ZaposleniOO, String>("ime"));
        prezimeColumn.setCellValueFactory(new PropertyValueFactory<ZaposleniOO, String>("prezime"));
        jmbgColumn.setCellValueFactory(new PropertyValueFactory<ZaposleniOO, String>("jmbg"));
        
        //TODO filter based on user who logged in
        //TODO actions on buttons
    }    
    
    private void preuzmiSveZaposlene() {
        
        zaposleni.clear();
        zaposleni.addAll(ZaposleniAdapter.preuzmiSve());
    }
    
    private void pretrazi(String searchString) {
        
        zaposleni.clear();
        ArrayList<ZaposleniOO> tempZaposleni;
        tempZaposleni = CombineList(ZaposleniAdapter.preuzmiPoImenu(searchString), ZaposleniAdapter.preuzmiPoPrezimenu(searchString));
        
    }
    
    private void setDisableInputs(boolean state) {
        
        imeTextField.setDisable(state);
        prezimeTextField.setDisable(state);
        jmbgTextField.setDisable(state);
        plataTextField.setDisable(state);
        aktivanCheck.setDisable(state);
        
    }
    
    private void setResizableAndEditable(boolean state) {
    
        imeColumn.setResizable(state);
        prezimeColumn.setResizable(state);
        jmbgColumn.setResizable(state);
    
        imeColumn.setEditable(state);
        prezimeColumn.setEditable(state);
        jmbgColumn.setEditable(state);
    }
    
    ArrayList<ZaposleniOO> CombineList(ArrayList<ZaposleniOO> first, ArrayList<ZaposleniOO> second) {
        ArrayList<ZaposleniOO> tempArrayList = first;
        for (ZaposleniOO zaposleni : second) {
            
            // TODO test for comparing an actual object, or just a copy
            if(!tempArrayList.contains(zaposleni)) {
                tempArrayList.add(zaposleni);
            }
        }
        
        return tempArrayList;
    }
    
}
