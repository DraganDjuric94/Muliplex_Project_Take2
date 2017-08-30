/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.unibl.etf.model.adapter.FilmAdapter;
import org.unibl.etf.model.adapter.ProjekcijaAdapter;
import org.unibl.etf.model.domain.oo.FilmOO;
import org.unibl.etf.model.domain.oo.ProjekcijaOO;
import org.unibl.etf.model.domain.oo.ZanrOO;

/**
 *
 * @author juhu
 */


public class MenadzerController implements Initializable{
    
    @FXML
    private Tab projekcijaTAB;

    @FXML
    private TableColumn<ProjekcijaOO, Double> projekcijaCijenaCOL;

    @FXML
    private TableColumn<FilmOO, String> filmZanroviCOL;

    @FXML
    private TableView<ProjekcijaOO> tabelaProjekcijaTBL;

    @FXML
    private TableColumn<ProjekcijaOO, Integer> projekcijaIdCOL;

    @FXML
    private TableColumn<FilmOO, String> filmNazivCOL;

    @FXML
    private Button izmijeniProjekcijaBTN;

    @FXML
    private TableColumn<ProjekcijaOO, Integer> projekcijaSalaCOL;

    @FXML
    private TableColumn<FilmOO, String> filmSLikaCOL;

    @FXML
    private TabPane taboviTPN;

    @FXML
    private Button dodajProjekcijaBTN;

    @FXML
    private Button dodajFilmBTN;

    @FXML
    private TextField pretraziProjekcijaTXT;

    @FXML
    private Button obrisiFilmBTN;

    @FXML
    private TextField pretraziFilmTXT;

    @FXML
    private TableColumn<FilmOO, Integer> filmTrajanjeCOL;

    @FXML
    private Button obrisiProjekcijaBTN;

    @FXML
    private TableColumn<ProjekcijaOO, String> projekcijaNazivfCOL;

    @FXML
    private Tab opremaTAB;

    @FXML
    private TableColumn<ProjekcijaOO, Date> datVrijCOL;

    @FXML
    private TableView<FilmOO> filmoviTBL;

    @FXML
    private TableColumn<FilmOO, String> filmOpisCOL;

    @FXML
    private Button pretraziFilmBTN;

    @FXML
    private TableColumn<FilmOO, Integer> filmIdCOL;

    @FXML
    private Button pretraziProjekcijaBTN;

    @FXML
    private Tab filmTAB;

    @FXML
    private Button izmijeniFilmBTN;
    
    private ObservableList<ProjekcijaOO> projekcije = FXCollections.observableArrayList();
    private ObservableList<FilmOO> filmovi = FXCollections.observableArrayList();
    
    public void preuzmiSveProjekcije(){
        projekcije.clear();
        projekcije.addAll(ProjekcijaAdapter.preuzmiSve());
    }
    
    public void pretraziProjekciju(String tekst){
        ArrayList<ProjekcijaOO> sve = ProjekcijaAdapter.preuzmiSve();
        projekcije.clear();
        for(ProjekcijaOO pr : sve){
            if(pr.getNazivFilma().toLowerCase().startsWith(tekst.toLowerCase()) || pr.getProjekcijaId().toString().startsWith(tekst)){
                projekcije.add(pr);
            }
        } 
    }
    
    public void preuzmiSveFilmove(){
        filmovi.clear();
        filmovi.addAll(FilmAdapter.preuzmiSve());
        
    }
    
    public void pretraziFilm(String tekst){
        ArrayList<FilmOO> sve = FilmAdapter.preuzmiSve();
        filmovi.clear();
        for(FilmOO f : sve){
            if(f.getNaziv().toLowerCase().startsWith(tekst.toLowerCase()) || f.getFilmId().toString().startsWith(tekst)){
                filmovi.add(f);
            }
        } 
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        opremaTAB.setDisable(true);
        
        preuzmiSveProjekcije();
        preuzmiSveFilmove();
        
        projekcijaIdCOL.setCellValueFactory(new PropertyValueFactory<ProjekcijaOO,Integer>("projekcijaId"));
        projekcijaNazivfCOL.setCellValueFactory(new PropertyValueFactory<ProjekcijaOO,String>("nazivFilma"));
        projekcijaSalaCOL.setCellValueFactory(new PropertyValueFactory<ProjekcijaOO, Integer>("salaId"));
        datVrijCOL.setCellValueFactory(new PropertyValueFactory<ProjekcijaOO,Date>("datumVrijeme"));
        projekcijaCijenaCOL.setCellValueFactory(new PropertyValueFactory<ProjekcijaOO,Double>("cijenaKarte"));
        
        tabelaProjekcijaTBL.setItems(projekcije);
        
        filmIdCOL.setCellValueFactory(new PropertyValueFactory<FilmOO,Integer>("filmId"));
        filmNazivCOL.setCellValueFactory(new PropertyValueFactory<FilmOO,String>("naziv"));
        filmOpisCOL.setCellValueFactory(new PropertyValueFactory<FilmOO,String>("opis"));
        filmTrajanjeCOL.setCellValueFactory(new PropertyValueFactory<FilmOO,Integer>("trajanje"));
        filmSLikaCOL.setCellValueFactory(new PropertyValueFactory<FilmOO,String>("slika"));
        filmZanroviCOL.setCellValueFactory(new PropertyValueFactory<FilmOO,String>("zanroviStr"));
        
        filmoviTBL.setItems(filmovi);
        
        pretraziFilmBTN.setOnAction((event) -> {
            pretraziFilm(pretraziFilmTXT.getText());
        });
        
        pretraziProjekcijaBTN.setOnAction((event) -> {
            pretraziProjekciju(pretraziProjekcijaTXT.getText());
        });
        
        pretraziFilmTXT.setOnKeyReleased((event) -> {
            pretraziFilm(pretraziFilmTXT.getText());
        });
        
        pretraziProjekcijaTXT.setOnKeyReleased((event) -> {
            pretraziProjekciju(pretraziProjekcijaTXT.getText());
        });
        
        
    }

    
    
    
    
    
    
    
    
    
    
    
}
