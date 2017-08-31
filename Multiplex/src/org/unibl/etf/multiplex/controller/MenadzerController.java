/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import org.unibl.etf.model.adapter.ArtikalAdapter;
import org.unibl.etf.model.adapter.FilmAdapter;
import org.unibl.etf.model.adapter.PonudaZaFilmAdapter;
import org.unibl.etf.model.adapter.ProjekcijaAdapter;
import org.unibl.etf.model.adapter.SalaAdapter;
import org.unibl.etf.model.adapter.ZanrAdapter;
import org.unibl.etf.model.adapter.ZaposleniAdapter;
import org.unibl.etf.model.domain.oo.ArtikalOO;
import org.unibl.etf.model.domain.oo.FilmOO;
import org.unibl.etf.model.domain.oo.PonudaZaFilmOO;
import org.unibl.etf.model.domain.oo.ProjekcijaOO;
import org.unibl.etf.model.domain.oo.SalaOO;
import org.unibl.etf.model.domain.oo.ZanrOO;
import org.unibl.etf.model.domain.oo.ZaposleniOO;

/**
 *
 * @author juhu, Milos
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

    @FXML
    private TableColumn<ZaposleniOO, String> zaposleniJmbgColumn;

    @FXML
    private Button artikalEraseBTN;

    @FXML
    private TableView<ArtikalOO> artikalTable;
    
    @FXML
    private TableColumn<ZaposleniOO, String> zaposleniImeColumn;
    
    @FXML
    private Tab artikalTAB;

    @FXML
    private TableColumn<?, ?> opremaBrojInventaraColumn;

    @FXML
    private TableColumn<?, ?> artikalNazivColumn;

    @FXML
    private Button opremaSearchBTN;

    @FXML
    private TableColumn<ZaposleniOO, Double> zaposleniPlataColumn;

    @FXML
    private TableView<?> opremaTable;

    @FXML
    private Button transakcijaSearchBTN;

    @FXML
    private TableColumn<ZaposleniOO, String> zaposleniPozicijaColumn;

    @FXML
    private Button transakcijaEditBTN;

    @FXML
    private TableColumn<?, ?> artikalBarkodColumn;

    @FXML
    private Button zaposleniEditBTN;

    @FXML
    private TableColumn<?, ?> artikalKolicinaColumn;

    @FXML
    private TableColumn<?, ?> artikalTipColumn;

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
    private TableView<ZaposleniOO> zaposleniTable;


    @FXML
    private Button fakturaAddBTN;

    @FXML
    private Button artikalEditBTN;

    @FXML
    private Button artikalAddBTN;

    @FXML
    private Button zaposleniAddBTN;

    @FXML
    private Tab zaposleniTAB;

    @FXML
    private TableColumn<?, ?> artikalCijenaColumn;

    @FXML
    private Button zaposleniSearchBTN;

    @FXML
    private Button zaposleniEraseBTN;
    
    @FXML
    private Button dodajZanrBTN;
    
    @FXML
    private Button izmijeniZanrBTN;
    
    @FXML
    private TextField pretraziZanrTXT;
    
    @FXML
    private TableView<ZanrOO> zanrTabelaTBL;
    
    @FXML
    private TableColumn<ZanrOO, Integer> zanrIdCOL;
    
    @FXML
    private TableColumn<ZanrOO, String> zanrNazivCOL;
    
    @FXML
    private Button obrisiZanrBTN;
    
    @FXML
    private Button pretraziZanrBTN;
    
    @FXML
    private Tab zanrTAB;
    
    @FXML
    private Button opremaAddBTN;

    @FXML
    private TableView<PonudaZaFilmOO> ponudeTabelaTBL;

    @FXML
    private TableColumn<SalaOO, Integer> salaIdCOL;

    @FXML
    private Button opremaEditBTN;

    @FXML
    private TableColumn<?, ?> fakturaDatumColumn;

    @FXML
    private Button opremaEraseBTN;

    @FXML
    private TextField opremaSearchTXT;
    @FXML
    private Button dodajSalaBTN;

    @FXML
    private TextField transakcijaSearchTXT;

    @FXML
    private Button pretraziSalaBTN;
    
    @FXML
    private TextField pretraziSalaTXT;

    @FXML
    private Tab salaTAB;

    @FXML
    private Button izmijeniSalaBTN;

    @FXML
    private TableColumn<PonudaZaFilmOO, Date> ponudaDatumCOL;

    @FXML
    private TableColumn<?, ?> transakcijaVrstaColumn;

    @FXML
    private TableColumn<PonudaZaFilmOO, Integer> ponudaFilmIdCOL;

    @FXML
    private TableColumn<?, ?> opremaIspravnostColumn;

    @FXML
    private Tab fakturaTAB;

    @FXML
    private TableView<SalaOO> salaTabelaTBL;

    @FXML
    private TableColumn<PonudaZaFilmOO, String> ponudaOpisCOL;

    @FXML
    private Button transakcijaAddBTN;

    @FXML
    private TableColumn<?, ?> fakturaKolicinaColumn;

    @FXML
    private TableColumn<?, ?> transakcijaPosiljalacColumn;
    @FXML
    private Button fakturaSearchBTN;

    @FXML
    private TableColumn<?, ?> transakcijaPrimalacColumn;

    @FXML
    private Button dodajPonudaBTN;

    @FXML
    private Button obrisiPonudaBTN;

    @FXML
    private TableColumn<?, ?> transakcijaIznosColumn;

    @FXML
    private Tab transakcijaTAB;

    @FXML
    private TableColumn<?, ?> transakcijaDatumColumn;

    @FXML
    private TextField fakturaSearchTXT;

    @FXML
    private TableColumn<SalaOO, Integer> salaBrKolCOL;

    @FXML
    private TableColumn<SalaOO, Integer> salaBrRedCOL;

    @FXML
    private TableColumn<?, ?> opremaNazivOpremeColumn;
    
    @FXML
    private Button pretraziPonudaBTN;


    @FXML
    private TextField pretraziPonudaTXT;

    @FXML
    private Button izmijeniPonudaBTN;
    
    @FXML
    private Tab ponudaZaFilmTAB;

    @FXML
    private Button obrisiSalaBTN;

    @FXML
    private TableColumn<PonudaZaFilmOO, Integer> ponudaIdCOL;
    
    

    
    private ObservableList<ZaposleniOO> zaposleni = FXCollections.observableArrayList();
    private ObservableList<ProjekcijaOO> projekcije = FXCollections.observableArrayList();
    private ObservableList<FilmOO> filmovi = FXCollections.observableArrayList();
    private ObservableList<ArtikalOO> artikli = FXCollections.observableArrayList();
    private ObservableList<ZanrOO> zanrovi = FXCollections.observableArrayList();
    private ObservableList<SalaOO> sale = FXCollections.observableArrayList();
    private ObservableList<PonudaZaFilmOO> ponude = FXCollections.observableArrayList();
    
    public void preuzmiSveProjekcije(){
        projekcije.clear();
        projekcije.addAll(ProjekcijaAdapter.preuzmiSve());
    }
    
    public void pretraziProjekciju(String tekst){
        ArrayList<ProjekcijaOO> sve = ProjekcijaAdapter.preuzmiSve();
        projekcije.clear();
        for(ProjekcijaOO pr : sve){
            if(pr.getFilm().getNaziv().toLowerCase().startsWith(tekst.toLowerCase()) || pr.getProjekcijaId().toString().startsWith(tekst)){
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
    
    private void preuzmiSveArtikle() {
        
        artikli.clear();
        artikli.addAll(ArtikalAdapter.preuzmiSve());
    }
    
    private void pretraziArtikal(String tekst) {
    
        ArrayList<ArtikalOO> all = ArtikalAdapter.preuzmiSve();
        artikli.clear();
        
        for(ArtikalOO it : all) {
            
            if(it.getNaziv().toLowerCase().contains(tekst.toLowerCase()) 
            || it.getBarkod().toLowerCase().contains(tekst.toLowerCase())) {
            
                artikli.add(it);
            }
        }
    }

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
    
    public void preuzmiSveZanrove(){
        zanrovi.clear();
        zanrovi.addAll(ZanrAdapter.preuzmiSve());
    }
    
    public void pretraziZanr(String tekst){
        ArrayList<ZanrOO> sve = ZanrAdapter.preuzmiSve();
        zanrovi.clear();
        for(ZanrOO z : sve){
            if(z.getNaziv().toLowerCase().startsWith(tekst.toLowerCase()) || z.getZanrId().toString().startsWith(tekst)){
                zanrovi.add(z);
            }
        } 
    }
    
    private void preuzmiSveSale(){
        sale.clear();
        sale.addAll(SalaAdapter.preuzmiSve());
    }
    
    private void preuzmiSvePonude(){
        ponude.clear();
        ponude.addAll(PonudaZaFilmAdapter.preuzmiSve());
    }
    
    private void pretraziSale(String tekst){
        ArrayList<SalaOO> temp = SalaAdapter.preuzmiSve();
        sale.clear();
        for(SalaOO t : sale){
            if(t.getSalaId().toString().startsWith(tekst)){
                sale.add(t);
            }
        }
    }
    
    private void pretraziPonude(String tekst){
        ArrayList<PonudaZaFilmOO> temp = PonudaZaFilmAdapter.preuzmiSve();
        ponude.clear();
        for(PonudaZaFilmOO t : ponude){
            if(t.getPonudaZaFilmId().toString().startsWith(tekst) || t.getFilmId().toString().startsWith(tekst)){
                ponude.add(t);
            }
        }
    }
    
    private void initializeMenadzerPonude(){
        preuzmiSvePonude();
        
        ponudaIdCOL.setCellValueFactory(new PropertyValueFactory<>("ponudaZaFilmId"));
        ponudaFilmIdCOL.setCellValueFactory(new PropertyValueFactory<>("filmId"));
        ponudaOpisCOL.setCellValueFactory(new PropertyValueFactory<>("opis"));
        ponudaDatumCOL.setCellValueFactory(new PropertyValueFactory<>("datum"));
        
        ponudeTabelaTBL.setItems(ponude);
        
        pretraziPonudaBTN.setOnAction((event) -> {
            pretraziPonude(pretraziPonudaTXT.getText());
        });
        
        pretraziPonudaTXT.setOnKeyReleased((event) -> {
            pretraziPonude(pretraziPonudaTXT.getText());
        });
    
    }
    
    private void initializeMenadzerSala(){
        preuzmiSveSale();
        
        salaIdCOL.setCellValueFactory(new PropertyValueFactory<>("salaId"));
        salaBrRedCOL.setCellValueFactory(new PropertyValueFactory<>("brojRedova"));
        salaBrKolCOL.setCellValueFactory(new PropertyValueFactory<>("brojKolona"));
        
        salaTabelaTBL.setItems(sale);
        
        pretraziSalaBTN.setOnAction((event) -> {
            pretraziSale(pretraziSalaTXT.getText());
        });
        
        pretraziSalaTXT.setOnKeyReleased((event) -> {
            pretraziSale(pretraziSalaTXT.getText());
        });
    }
    
    private void initializeMenadzerZanr(){
        preuzmiSveZanrove();
        
        zanrIdCOL.setCellValueFactory(new PropertyValueFactory<>("zanrId"));
        zanrNazivCOL.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        
        zanrTabelaTBL.setItems(zanrovi);
        
        pretraziZanrBTN.setOnAction((event) -> {
            pretraziZanr(pretraziZanrTXT.getText());
        });
        
        pretraziZanrTXT.setOnKeyReleased((event) -> {
            pretraziZanr(pretraziZanrTXT.getText());
        });
        
    }
    
    private void initializeMenadzerProjekcija() {

        preuzmiSveProjekcije();
        
        projekcijaIdCOL.setCellValueFactory(new PropertyValueFactory<>("projekcijaId"));
        projekcijaNazivfCOL.setCellValueFactory(new PropertyValueFactory<>("nazivFilma"));
        projekcijaSalaCOL.setCellValueFactory(new PropertyValueFactory<>("salaId"));
        datVrijCOL.setCellValueFactory(new PropertyValueFactory<>("datumVrijeme"));
        projekcijaCijenaCOL.setCellValueFactory(new PropertyValueFactory<>("cijenaKarte"));
        
        tabelaProjekcijaTBL.setItems(projekcije);
        
        pretraziProjekcijaBTN.setOnAction((event) -> {
            pretraziProjekciju(pretraziProjekcijaTXT.getText());
        });
        
        pretraziProjekcijaTXT.setOnKeyReleased((event) -> {
            pretraziProjekciju(pretraziProjekcijaTXT.getText());
        });
    }
    
    private void initializeMenadzerFilmovi() {
        
        preuzmiSveFilmove();
          
        filmIdCOL.setCellValueFactory(new PropertyValueFactory<>("filmId"));
        filmNazivCOL.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        filmOpisCOL.setCellValueFactory(new PropertyValueFactory<>("opis"));
        filmTrajanjeCOL.setCellValueFactory(new PropertyValueFactory<>("trajanje"));
        filmSLikaCOL.setCellValueFactory(new PropertyValueFactory<>("slika"));
        filmZanroviCOL.setCellValueFactory(new PropertyValueFactory<>("zanroviStr"));
        
        filmoviTBL.setItems(filmovi);
        
        pretraziFilmBTN.setOnAction((event) -> {
            pretraziFilm(pretraziFilmTXT.getText());
        });
        
        pretraziFilmTXT.setOnKeyReleased((event) -> {
            pretraziFilm(pretraziFilmTXT.getText());
        });
    }
    
    private void initializeMenadzerArtikal() {
        preuzmiSveArtikle();
        
        artikalNazivColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        artikalTipColumn.setCellValueFactory(new PropertyValueFactory<>("tip"));
        artikalBarkodColumn.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        artikalKolicinaColumn.setCellValueFactory(new PropertyValueFactory<>("kolicinaNaStanju"));
        artikalCijenaColumn.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        
        artikalTable.setItems(artikli);
        
        artikalSearchBTN.setOnAction((event) -> {
            pretraziProjekciju(artikalSearchTXT.getText());
        });
        
        artikalSearchTXT.setOnKeyReleased((event) -> {
            pretraziProjekciju(artikalSearchTXT.getText());
        });
    }
    
    
    private void initializeMenadzerZaposleni() {
        
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
    
        opremaTAB.setDisable(true);
        initializeMenadzerSala();
        initializeMenadzerPonude();
        initializeMenadzerZanr();
        initializeMenadzerProjekcija();
        initializeMenadzerFilmovi();
        initializeMenadzerArtikal();
        initializeMenadzerZaposleni();
    }
}
