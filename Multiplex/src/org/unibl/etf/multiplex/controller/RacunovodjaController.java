/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import org.unibl.etf.model.adapter.FakturaAdapter;
import org.unibl.etf.model.adapter.TransakcijaAdapter;
import org.unibl.etf.model.domain.oo.FakturaOO;
import org.unibl.etf.model.domain.oo.TransakcijaOO;

/**
 *
 * @author juhu
 */
public class RacunovodjaController implements Initializable{
    
    @FXML
    private Button opremaAddBTN;

    @FXML
    private TableView<?> ponudeTabelaTBL;

    @FXML
    private TableColumn<?, ?> salaIdCOL;

    @FXML
    private TableColumn<?, ?> projekcijaIdCOL;

    @FXML
    private Button artikalEraseBTN;

    @FXML
    private TableView<?> artikalTable;

    @FXML
    private Button izmijeniProjekcijaBTN;

    @FXML
    private TableColumn<?, ?> projekcijaSalaCOL;

    @FXML
    private Button izmijeniZanrBTN;

    @FXML
    private Button opremaEditBTN;

    @FXML
    private TabPane taboviTPN;

    @FXML
    private TableColumn<FakturaOO, Date> fakturaDatumColumn;

    @FXML
    private Button dodajFilmBTN;

    @FXML
    private Button opremaEraseBTN;

    @FXML
    private TextField opremaSearchTXT;

    @FXML
    private TableColumn<?, ?> zaposleniImeColumn;

    @FXML
    private TextField pretraziFilmTXT;

    @FXML
    private TableColumn<?, ?> filmTrajanjeCOL;

    @FXML
    private Button dodajSalaBTN;

    @FXML
    private Tab artikalTAB;

    @FXML
    private TextField transakcijaSearchTXT;

    @FXML
    private Button pretraziSalaBTN;

    @FXML
    private Button obrisiProjekcijaBTN;

    @FXML
    private Tab opremaTAB;

    @FXML
    private Button opremaSearchBTN;

    @FXML
    private TableColumn<?, ?> zaposleniPlataColumn;

    @FXML
    private Button pretraziFilmBTN;

    @FXML
    private Button transakcijaSearchBTN;

    @FXML
    private TableColumn<?, ?> zaposleniPozicijaColumn;

    @FXML
    private TextField pretraziSalaTXT;

    @FXML
    private Tab projekcijaTAB;

    @FXML
    private TableColumn<?, ?> artikalBarkodColumn;

    @FXML
    private Tab salaTAB;

    @FXML
    private Button zaposleniEditBTN;

    @FXML
    private Button izmijeniSalaBTN;

    @FXML
    private TableColumn<?, ?> ponudaDatumCOL;

    @FXML
    private TableColumn<TransakcijaOO, String> transakcijaVrstaColumn;

    @FXML
    private TableColumn<?, ?> artikalKolicinaColumn;

    @FXML
    private TableColumn<?, ?> ponudaFilmIdCOL;

    @FXML
    private TableColumn<?, ?> opremaIspravnostColumn;

    @FXML
    private TableColumn<?, ?> artikalTipColumn;

    @FXML
    private Tab fakturaTAB;

    @FXML
    private TableColumn<?, ?> filmNazivCOL;

    @FXML
    private TableView<?> salaTabelaTBL;

    @FXML
    private TableColumn<FakturaOO, String> fakturaBrojRacunaColumn;

    @FXML
    private Button artikalSearchBTN;

    @FXML
    private Button transakcijaEraseBTN;

    @FXML
    private TableColumn<?, ?> zaposleniPrezimeColumn;

    @FXML
    private TextField artikalSearchTXT;

    @FXML
    private TableColumn<FakturaOO, String> fakturaRacunIzdaoColumn;

    @FXML
    private TableColumn<?, ?> ponudaOpisCOL;

    @FXML
    private Button dodajProjekcijaBTN;

    @FXML
    private TextField pretraziProjekcijaTXT;

    @FXML
    private Button fakturaAddBTN;

    @FXML
    private Button transakcijaAddBTN;

    @FXML
    private TableColumn<FakturaOO, Integer> fakturaKolicinaColumn;

    @FXML
    private TableColumn<?, ?> zanrIdCOL;

    @FXML
    private TableColumn<TransakcijaOO, String> transakcijaPosiljalacColumn;

    @FXML
    private Tab zaposleniTAB;

    @FXML
    private Button fakturaSearchBTN;

    @FXML
    private Button obrisiZanrBTN;

    @FXML
    private TableColumn<TransakcijaOO, String> transakcijaPrimalacColumn;

    @FXML
    private Button pretraziProjekcijaBTN;

    @FXML
    private TableColumn<?, ?> artikalCijenaColumn;

    @FXML
    private Button dodajPonudaBTN;

    @FXML
    private Button obrisiPonudaBTN;

    @FXML
    private Button zaposleniEraseBTN;

    @FXML
    private Tab filmTAB;

    @FXML
    private Button izmijeniFilmBTN;

    @FXML
    private TableColumn<TransakcijaOO, Double> transakcijaIznosColumn;

    @FXML
    private TableColumn<?, ?> zaposleniJmbgColumn;

    @FXML
    private Tab transakcijaTAB;

    @FXML
    private Button dodajZanrBTN;

    @FXML
    private TableColumn<TransakcijaOO, Date> transakcijaDatumColumn;

    @FXML
    private TextField fakturaSearchTXT;

    @FXML
    private TableColumn<?, ?> salaBrKolCOL;

    @FXML
    private TableColumn<?, ?> salaBrRedCOL;

    @FXML
    private TableColumn<?, ?> opremaNazivOpremeColumn;

    @FXML
    private Button fakturaEditBTN;

    @FXML
    private TableColumn<FakturaOO, String> fakturaJedinicaMjereColumn;

    @FXML
    private TextField pretraziZanrTXT;

    @FXML
    private Button pretraziPonudaBTN;

    @FXML
    private TableColumn<?, ?> opremaBrojInventaraColumn;

    @FXML
    private TableColumn<?, ?> artikalNazivColumn;

    @FXML
    private Button pretraziZanrBTN;

    @FXML
    private TableView<?> opremaTable;

    @FXML
    private TableColumn<?, ?> datVrijCOL;

    @FXML
    private TableColumn<?, ?> filmOpisCOL;

    @FXML
    private TableColumn<?, ?> filmIdCOL;

    @FXML
    private TableView<?> zanrTabelaTBL;

    @FXML
    private Button transakcijaEditBTN;

    @FXML
    private TextField pretraziPonudaTXT;

    @FXML
    private Button izmijeniPonudaBTN;

    @FXML
    private TableColumn<?, ?> projekcijaCijenaCOL;

    @FXML
    private TableColumn<?, ?> filmZanroviCOL;

    @FXML
    private Button fakturaObrisiBTN;

    @FXML
    private TableView<?> tabelaProjekcijaTBL;

    @FXML
    private TableView<FakturaOO> fakturaTable;

    @FXML
    private TableColumn<?, ?> zanrNazivCOL;

    @FXML
    private TextField zaposleniSearchTXT;

    @FXML
    private TableColumn<?, ?> filmSLikaCOL;

    @FXML
    private TableView<?> zaposleniTable;

    @FXML
    private Button obrisiFilmBTN;

    @FXML
    private Button artikalEditBTN;

    @FXML
    private TableView<TransakcijaOO> traksancijaTable;

    @FXML
    private Button artikalAddBTN;

    @FXML
    private TableColumn<FakturaOO, Double> fakturaCijenaColumn;

    @FXML
    private Button zaposleniAddBTN;

    @FXML
    private Tab zanrTAB;

    @FXML
    private TableColumn<FakturaOO, String> fakturaNazivRobeColumn;

    @FXML
    private TableColumn<?, ?> projekcijaNazivfCOL;

    @FXML
    private Tab ponudaZaFilmTAB;

    @FXML
    private Button obrisiSalaBTN;

    @FXML
    private TableView<?> filmoviTBL;

    @FXML
    private Button zaposleniSearchBTN;

    @FXML
    private TableColumn<?, ?> ponudaIdCOL;
    
    
    
    private ObservableList<TransakcijaOO> transakcije = FXCollections.observableArrayList();
    private ObservableList<FakturaOO> fakture = FXCollections.observableArrayList();
    
    private void preuzmiSveTransakcije(){
        transakcije.clear();
        transakcije.addAll(TransakcijaAdapter.preuzmiSve());
    }
    
    private void preuzmiSveFakture(){
        fakture.clear();
        fakture.addAll(FakturaAdapter.preuzmiSve());
    }
    
    private void pretraziTransakcije(String tekst){
        ArrayList<TransakcijaOO> temp = TransakcijaAdapter.preuzmiSve();
        transakcije.clear();
        for(TransakcijaOO tr : temp){
            if(tr.getTransakcijaId().toString().startsWith(tekst) || tr.getPosaljilac().toLowerCase().startsWith(tekst.toLowerCase())){
                transakcije.add(tr);
            }
        }
    }
    
    private void pretraziFakture(String tekst){
        ArrayList<FakturaOO> temp = FakturaAdapter.preuzmiSve();
        fakture.clear();
        for(FakturaOO tr : temp){
            if(tr.getFakturaId().toString().startsWith(tekst) || tr.getNazivRobe().toLowerCase().startsWith(tekst.toLowerCase())){
                fakture.add(tr);
            }
        }
    }
    
    private void initializeRacunovodjaTransakcije(){
        preuzmiSveTransakcije();
        
        transakcijaVrstaColumn.setCellValueFactory(new PropertyValueFactory<>("vrstaTransakcije"));
        transakcijaPrimalacColumn.setCellValueFactory(new PropertyValueFactory<>("primalac"));
        transakcijaPosiljalacColumn.setCellValueFactory(new PropertyValueFactory<>("posaljilac"));
        transakcijaDatumColumn.setCellValueFactory(new PropertyValueFactory<>("datumTransakcije"));
        transakcijaIznosColumn.setCellValueFactory(new PropertyValueFactory<>("iznos"));
        
        traksancijaTable.setItems(transakcije);
        
        transakcijaSearchBTN.setOnAction((event) -> {
            pretraziTransakcije(transakcijaSearchTXT.getText());
        });
        
        transakcijaSearchTXT.setOnKeyReleased((event) -> {
            pretraziTransakcije(transakcijaSearchTXT.getText());
        });
    }
    
    private void initializeRacunovodjaFakture(){
        preuzmiSveFakture();
        
        fakturaBrojRacunaColumn.setCellValueFactory(new PropertyValueFactory<>("brojRacuna"));
        fakturaNazivRobeColumn.setCellValueFactory(new PropertyValueFactory<>("nazivRobe"));
        fakturaJedinicaMjereColumn.setCellValueFactory(new PropertyValueFactory<>("jedinicaMjere"));
        fakturaKolicinaColumn.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
        fakturaCijenaColumn.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        fakturaDatumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        fakturaRacunIzdaoColumn.setCellValueFactory(new PropertyValueFactory<>("racunIzdao"));
        
        fakturaTable.setItems(fakture);
        
        fakturaSearchBTN.setOnAction((event) -> {
            pretraziFakture(fakturaSearchTXT.getText());
        });
        
        fakturaSearchTXT.setOnKeyReleased((event) -> {
            pretraziFakture(fakturaSearchTXT.getText());
        });
    
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeRacunovodjaFakture();
        initializeRacunovodjaTransakcije();
    }
    
}
