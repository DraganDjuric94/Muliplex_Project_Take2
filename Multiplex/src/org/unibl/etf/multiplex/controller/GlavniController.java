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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.stage.WindowEvent;
import org.unibl.etf.model.adapter.ArtikalAdapter;
import org.unibl.etf.model.adapter.FakturaAdapter;
import org.unibl.etf.model.adapter.FilmAdapter;
import org.unibl.etf.model.adapter.OpremaAdapter;
import org.unibl.etf.model.adapter.PonudaZaFilmAdapter;
import org.unibl.etf.model.adapter.ProjekcijaAdapter;
import org.unibl.etf.model.adapter.SalaAdapter;
import org.unibl.etf.model.adapter.TransakcijaAdapter;
import org.unibl.etf.model.adapter.ZanrAdapter;
import org.unibl.etf.model.adapter.ZaposleniAdapter;
import org.unibl.etf.model.domain.oo.ArtikalOO;
import org.unibl.etf.model.domain.oo.FakturaOO;
import org.unibl.etf.model.domain.oo.FilmOO;
import org.unibl.etf.model.domain.oo.OpremaOO;
import org.unibl.etf.model.domain.oo.PonudaZaFilmOO;
import org.unibl.etf.model.domain.oo.ProjekcijaOO;
import org.unibl.etf.model.domain.oo.SalaOO;
import org.unibl.etf.model.domain.oo.TransakcijaOO;
import org.unibl.etf.model.domain.oo.ZanrOO;
import org.unibl.etf.model.domain.oo.ZaposleniOO;
import org.unibl.etf.multiplex.Multiplex;

/**
 *
 * @author juhu, Milos
 */


public class GlavniController implements Initializable{
    
    @FXML
    private Tab projekcijaTAB;
    
    @FXML
    private Button fakturaObrisiBTN;
    
    @FXML
    private Button fakturaEditBTN;
    
    @FXML
    private TableColumn<FakturaOO, String> fakturaNazivRobeColumn;
    
    @FXML
    private TableColumn<FakturaOO, String> fakturaJedinicaMjereColumn;
    
    @FXML
    private TableColumn<FakturaOO, Double> fakturaCijenaColumn;

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
    private TableView<TransakcijaOO> traksancijaTable;

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
    private TableColumn<OpremaOO, String> opremaBrojInventaraColumn;

    @FXML
    private TableColumn<?, ?> artikalNazivColumn;

    @FXML
    private Button opremaSearchBTN;

    @FXML
    private TableColumn<ZaposleniOO, Double> zaposleniPlataColumn;

    @FXML
    private TableView<OpremaOO> opremaTable;

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
    private TableColumn<FakturaOO, String> fakturaBrojRacunaColumn;
    @FXML
    private TableView<FakturaOO> fakturaTable;
    

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
    private TableColumn<FakturaOO, String> fakturaRacunIzdaoColumn;

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
    private TableColumn<FakturaOO, Date> fakturaDatumColumn;

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
    private TableColumn<TransakcijaOO, String> transakcijaVrstaColumn;

    @FXML
    private TableColumn<PonudaZaFilmOO, Integer> ponudaFilmIdCOL;

    @FXML
    private TableColumn<OpremaOO, Boolean> opremaIspravnostColumn;

    @FXML
    private Tab fakturaTAB;

    @FXML
    private TableView<SalaOO> salaTabelaTBL;

    @FXML
    private TableColumn<PonudaZaFilmOO, String> ponudaOpisCOL;

    @FXML
    private Button transakcijaAddBTN;

    @FXML
    private TableColumn<FakturaOO, Integer> fakturaKolicinaColumn;

    @FXML
    private TableColumn<TransakcijaOO, String> transakcijaPosiljalacColumn;
    @FXML
    private Button fakturaSearchBTN;

    @FXML
    private TableColumn<TransakcijaOO, String> transakcijaPrimalacColumn;

    @FXML
    private Button dodajPonudaBTN;

    @FXML
    private Button obrisiPonudaBTN;

    @FXML
    private TableColumn<TransakcijaOO, Double> transakcijaIznosColumn;

    @FXML
    private Tab transakcijaTAB;

    @FXML
    private TableColumn<TransakcijaOO, Date> transakcijaDatumColumn;

    @FXML
    private TextField fakturaSearchTXT;

    @FXML
    private TableColumn<SalaOO, Integer> salaBrKolCOL;

    @FXML
    private TableColumn<SalaOO, Integer> salaBrRedCOL;

    @FXML
    private TableColumn<OpremaOO, String> opremaNazivOpremeColumn;
    
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
    private ObservableList<OpremaOO> oprema = FXCollections.observableArrayList();
    private ObservableList<TransakcijaOO> transakcije = FXCollections.observableArrayList();
    private ObservableList<FakturaOO> fakture = FXCollections.observableArrayList();
    private String vrstaKorisnika;
    
    public void updateOprema() {
        ArrayList<OpremaOO> temp = OpremaAdapter.preuzmiSve();
        ObservableList<OpremaOO> kar = FXCollections.observableArrayList();
        for (OpremaOO k : temp) {
            if (k.getNaziv().toLowerCase().startsWith(opremaSearchTXT.getText().toLowerCase())){
                kar.add(k);
            }
        }
        if (!kar.equals(this.oprema)) {
            this.oprema.clear();
            this.oprema.addAll(kar);
            System.out.println("update!");
        }
    }
    
    public void updateZaposleni() {
        ArrayList<ZaposleniOO> temp = ZaposleniAdapter.preuzmiSve();
        ObservableList<ZaposleniOO> kar = FXCollections.observableArrayList();
        for (ZaposleniOO k : temp) {
            if (k.getIme().toLowerCase().startsWith(zaposleniSearchTXT.getText().toLowerCase())){
                kar.add(k);
            }
        }
        if (!kar.equals(this.zaposleni)) {
            this.zaposleni.clear();
            this.zaposleni.addAll(kar);
            System.out.println("update!");
        }
    }
    
    public void updateArtikal() {
        ArrayList<ArtikalOO> temp = ArtikalAdapter.preuzmiSve();
        ObservableList<ArtikalOO> kar = FXCollections.observableArrayList();
        for (ArtikalOO k : temp) {
            if (k.getNaziv().toLowerCase().startsWith(artikalSearchTXT.getText().toLowerCase())){
                kar.add(k);
            }
        }
        if (!kar.equals(this.artikli)) {
            this.artikli.clear();
            this.artikli.addAll(kar);
            System.out.println("update!");
        }
    }
    
    public void updateTransakcija() {
        ArrayList<TransakcijaOO> temp = TransakcijaAdapter.preuzmiSve();
        ObservableList<TransakcijaOO> kar = FXCollections.observableArrayList();
        for (TransakcijaOO k : temp) {
            if (k.getTransakcijaId().toString().startsWith(transakcijaSearchTXT.getText()) || k.getPosaljilac().toLowerCase().startsWith(transakcijaSearchTXT.getText().toLowerCase())){
                kar.add(k);
            }
        }
        if (!kar.equals(this.transakcije)) {
            this.transakcije.clear();
            this.transakcije.addAll(kar);
            System.out.println("update!");
        }
    }
    
    public void updateFaktura() {
        ArrayList<FakturaOO> temp = FakturaAdapter.preuzmiSve();
        ObservableList<FakturaOO> kar = FXCollections.observableArrayList();
        for (FakturaOO k : temp) {
            if (k.getFakturaId().toString().startsWith(fakturaSearchTXT.getText()) || k.getNazivRobe().toLowerCase().startsWith(fakturaSearchTXT.getText().toLowerCase())){
                kar.add(k);
            }
        }
        if (!kar.equals(this.fakture)) {
            this.fakture.clear();
            this.fakture.addAll(kar);
            System.out.println("update!");
        }
    }
    
    public void updateFilm() {
        ArrayList<FilmOO> temp = FilmAdapter.preuzmiSve();
        ObservableList<FilmOO> kar = FXCollections.observableArrayList();
        for (FilmOO k : temp) {
            if (k.getNaziv().toLowerCase().startsWith(pretraziFilmTXT.getText().toLowerCase()) || k.getFilmId().toString().startsWith(pretraziFilmTXT.getText())){
                kar.add(k);
            }
        }
        if (!kar.equals(this.filmovi)) {
            this.filmovi.clear();
            this.filmovi.addAll(kar);
            System.out.println("update!");
        }
    }
    
    public void updateSala() {
        ArrayList<SalaOO> temp = SalaAdapter.preuzmiSve();
        ObservableList<SalaOO> kar = FXCollections.observableArrayList();
        for (SalaOO k : temp) {
            if (k.getSalaId().toString().toLowerCase().startsWith(pretraziSalaTXT.getText().toLowerCase())){
                kar.add(k);
            }
        }
        if (!kar.equals(this.sale)) {
            this.sale.clear();
            this.sale.addAll(kar);
            System.out.println("update!");
        }
    }
    
    public void updateProjekcija() {
        ArrayList<ProjekcijaOO> temp = ProjekcijaAdapter.preuzmiSve();
        ObservableList<ProjekcijaOO> kar = FXCollections.observableArrayList();
        for (ProjekcijaOO k : temp) {
            if (k.getNazivFilma().toString().toLowerCase().startsWith(pretraziProjekcijaTXT.getText().toLowerCase()) || k.getProjekcijaId().toString().startsWith(pretraziProjekcijaTXT.getText())){
                kar.add(k);
            }
        }
        if (!kar.equals(this.projekcije)) {
            this.projekcije.clear();
            this.projekcije.addAll(kar);
            System.out.println("update!");
        }
    }
    
    public void updateZanr() {
        ArrayList<ZanrOO> temp = ZanrAdapter.preuzmiSve();
        ObservableList<ZanrOO> kar = FXCollections.observableArrayList();
        for (ZanrOO k : temp) {
            if (k.getNaziv().toString().toLowerCase().startsWith(pretraziZanrTXT.getText().toLowerCase())){
                kar.add(k);
            }
        }
        if (!kar.equals(this.zanrovi)) {
            this.zanrovi.clear();
            this.zanrovi.addAll(kar);
            System.out.println("update!");
        }
    }
    
    public void updatePonuda() {
        ArrayList<PonudaZaFilmOO> temp = PonudaZaFilmAdapter.preuzmiSve();
        ObservableList<PonudaZaFilmOO> kar = FXCollections.observableArrayList();
        for (PonudaZaFilmOO k : temp) {
            if (k.getPonudaZaFilmId().toString().startsWith(pretraziPonudaTXT.getText()) || k.getFilmId().toString().startsWith(pretraziPonudaTXT.getText())){
                kar.add(k);
            }
            
        }
        if (!kar.equals(this.ponude)) {
            this.ponude.clear();
            this.ponude.addAll(kar);
            System.out.println("update!");
        }
    }
    
    public GlavniController(String vrstaKorisnika){
        this.vrstaKorisnika = vrstaKorisnika;  
    }
    
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
    
    private void pretraziOpremu(String tekst){
        ArrayList<OpremaOO> temp = OpremaAdapter.preuzmiSve();
        oprema.clear();
        for(OpremaOO it : temp){
            if(it.getNaziv().toLowerCase().startsWith(tekst.toLowerCase())){
                oprema.add(it);
            }
        }
    }
    
    private void preuzmiSvuOpremu(){
        oprema.clear();
        oprema.addAll(OpremaAdapter.preuzmiSve());
    }
    
    private void preuzmiSveProjekcije(){
        projekcije.clear();
        projekcije.addAll(ProjekcijaAdapter.preuzmiSve());
    }
    
    private void pretraziProjekciju(String tekst){
        ArrayList<ProjekcijaOO> sve = ProjekcijaAdapter.preuzmiSve();
        projekcije.clear();
        for(ProjekcijaOO pr : sve){
            if(pr.getFilm().getNaziv().toLowerCase().startsWith(tekst.toLowerCase()) || pr.getProjekcijaId().toString().startsWith(tekst)){
                projekcije.add(pr);
            }
        } 
    }
    
    private void preuzmiSveFilmove(){
        
        filmovi.clear();
        filmovi.addAll(FilmAdapter.preuzmiSve());
    }
    
    private void pretraziFilm(String tekst){
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

    private void pretraziZaposlenog(String tekst){
        ArrayList<ZaposleniOO> tempZ = ZaposleniAdapter.preuzmiSve();
        zaposleni.clear();
        for(ZaposleniOO zap : tempZ){
            if(zap.getIme().toLowerCase().contains(tekst.toLowerCase()) || zap.getZaposleniId().toString().contains(tekst)){
                zaposleni.add(zap);
            }
        }
    }
    
    private void preuzmiSveZaposlene(){
        zaposleni.clear();
        zaposleni.addAll(ZaposleniAdapter.preuzmiSve());
    }
    
    private void preuzmiSveZanrove(){
        zanrovi.clear();
        zanrovi.addAll(ZanrAdapter.preuzmiSve());
    }
    
    private void pretraziZanr(String tekst){
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
        for(SalaOO t : temp){
            if(t.getSalaId().toString().startsWith(tekst)){
                sale.add(t);
            }
        }
    }
    
    private void pretraziPonude(String tekst){
        ArrayList<PonudaZaFilmOO> temp = PonudaZaFilmAdapter.preuzmiSve();
        ponude.clear();
        for(PonudaZaFilmOO t : temp){
            if(t.getPonudaZaFilmId().toString().startsWith(tekst) || t.getFilmId().toString().startsWith(tekst)){
                ponude.add(t);
            }
        }
    }
    
    private void initializePonude(boolean samoCitanje){
        preuzmiSvePonude();
        
        Timer t = new Timer();
        
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        updatePonuda();
                    }
                });
            }
        }, 0, 3000);
        
        ponudaIdCOL.prefWidthProperty().bind(ponudeTabelaTBL.widthProperty().multiply(0.25));
        ponudaFilmIdCOL.prefWidthProperty().bind(ponudeTabelaTBL.widthProperty().multiply(0.25));
        ponudaOpisCOL.prefWidthProperty().bind(ponudeTabelaTBL.widthProperty().multiply(0.25));
        ponudaDatumCOL.prefWidthProperty().bind(ponudeTabelaTBL.widthProperty().multiply(0.25));
        
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
    
        
        dodajPonudaBTN.setOnAction((event) -> {
            PonudaZaFilmFormController control = new PonudaZaFilmFormController(null, false);
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/PonudaZaFilmForm.fxml"));
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
        
        
        izmijeniPonudaBTN.setOnAction((event) -> {
            PonudaZaFilmOO pon = ponudeTabelaTBL.getSelectionModel().getSelectedItem();
            if(pon != null){
                PonudaZaFilmFormController control = new PonudaZaFilmFormController(pon, true);
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/PonudaZaFilmForm.fxml"));
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
            }
        });
        
        obrisiPonudaBTN.setOnAction((event) -> {
            PonudaZaFilmOO pon = ponudeTabelaTBL.getSelectionModel().getSelectedItem();
            if(pon != null){
                PonudaZaFilmAdapter.obrisi(pon.getPonudaZaFilmId());
            }
        });
        
        if(samoCitanje){
            obrisiPonudaBTN.disableProperty().set(true);
            izmijeniPonudaBTN.disableProperty().set(true);
            dodajPonudaBTN.disableProperty().set(true);
        }
    }
    
    private void initializeSala(boolean samoCitanje){
        preuzmiSveSale();
        
        Timer t = new Timer();
        
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        updateSala();
                    }
                });
            }
        }, 0, 3000);
        
        salaIdCOL.prefWidthProperty().bind(salaTabelaTBL.widthProperty().multiply(0.33));
        salaBrKolCOL.prefWidthProperty().bind(salaTabelaTBL.widthProperty().multiply(0.34));
        salaBrRedCOL.prefWidthProperty().bind(salaTabelaTBL.widthProperty().multiply(0.33));
        
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
        
        dodajSalaBTN.setOnAction((event) -> {
        
                SalaFormController control = new SalaFormController(null, false);
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/SalaForm.fxml"));
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
        
        izmijeniSalaBTN.setOnAction((event) -> {
            SalaOO sala = salaTabelaTBL.getSelectionModel().getSelectedItem();
            if(sala != null){
                SalaFormController control = new SalaFormController(sala, true);
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/SalaForm.fxml"));
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
            }
        });
        
        obrisiSalaBTN.setOnAction((event) -> {
            SalaOO sala = salaTabelaTBL.getSelectionModel().getSelectedItem();
            if(sala != null){
                SalaAdapter.obrisi(sala.getSalaId());
            }
        });
        
        if(samoCitanje){
            obrisiSalaBTN.disableProperty().set(true);
            dodajSalaBTN.disableProperty().set(true);
            izmijeniSalaBTN.disableProperty().set(true);
        }
        
    }
    
    private void initializeZanr(boolean samoCitanje){
        preuzmiSveZanrove();
        
        Timer t = new Timer();
        
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        updateZanr();
                    }
                });
            }
        }, 0, 3000);
        
        zanrIdCOL.prefWidthProperty().bind(zanrTabelaTBL.widthProperty().multiply(0.5));
        zanrNazivCOL.prefWidthProperty().bind(zanrTabelaTBL.widthProperty().multiply(0.5));
        
        zanrIdCOL.setCellValueFactory(new PropertyValueFactory<>("zanrId"));
        zanrNazivCOL.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        
        zanrTabelaTBL.setItems(zanrovi);
        
        pretraziZanrBTN.setOnAction((event) -> {
            pretraziZanr(pretraziZanrTXT.getText());
        });
        
        pretraziZanrTXT.setOnKeyReleased((event) -> {
            pretraziZanr(pretraziZanrTXT.getText());
        });
        
        izmijeniZanrBTN.setOnAction((event) -> {
            ZanrOO zanr = zanrTabelaTBL.getSelectionModel().getSelectedItem();
            if(zanr != null){
                ZanrFormController control = new ZanrFormController(zanr, true);
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/ZanrForm.fxml"));
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
            }
        });
        
        dodajZanrBTN.setOnAction((event) -> {
            ZanrFormController control = new ZanrFormController(null, false);
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/ZanrForm.fxml"));
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
        
        obrisiZanrBTN.setOnAction((event) -> {
            ZanrOO zanr = zanrTabelaTBL.getSelectionModel().getSelectedItem();
            if(zanr != null){
                ZanrAdapter.obrisi(zanr.getZanrId());
            }
        });
        
        if(samoCitanje){
            obrisiZanrBTN.disableProperty().set(true);
            izmijeniZanrBTN.disableProperty().set(true);
            dodajZanrBTN.disableProperty().set(true);
        }
        
    }
    
    private void initializeProjekcija(boolean samoCitanje) {

        preuzmiSveProjekcije();
        
        Timer t = new Timer();
        
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        updateProjekcija();
                    }
                });
            }
        }, 0, 3000);
        
        projekcijaIdCOL.prefWidthProperty().bind(tabelaProjekcijaTBL.widthProperty().multiply(0.2));
        projekcijaNazivfCOL.prefWidthProperty().bind(tabelaProjekcijaTBL.widthProperty().multiply(0.2));
        projekcijaSalaCOL.prefWidthProperty().bind(tabelaProjekcijaTBL.widthProperty().multiply(0.2));
        datVrijCOL.prefWidthProperty().bind(tabelaProjekcijaTBL.widthProperty().multiply(0.2));
        projekcijaCijenaCOL.prefWidthProperty().bind(tabelaProjekcijaTBL.widthProperty().multiply(0.2));
        
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
        
        dodajProjekcijaBTN.setOnAction((event) -> {
            ProjekcijaFormController control = new ProjekcijaFormController(null, false);
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/ProjekcijaForm.fxml"));
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
        
        izmijeniProjekcijaBTN.setOnAction((event) -> {
            ProjekcijaOO pr = tabelaProjekcijaTBL.getSelectionModel().getSelectedItem();
            if(pr != null){
                ProjekcijaFormController control = new ProjekcijaFormController(pr, true);
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/ProjekcijaForm.fxml"));
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
            }
        });
        
        obrisiProjekcijaBTN.setOnAction((event) -> {
           ProjekcijaOO pr = tabelaProjekcijaTBL.getSelectionModel().getSelectedItem();
           if(pr != null){
                ProjekcijaAdapter.obrisi(pr.getProjekcijaId());
           }
        });
        
        if(samoCitanje){
            izmijeniProjekcijaBTN.disableProperty().set(true);
            obrisiProjekcijaBTN.disableProperty().set(true);
            dodajProjekcijaBTN.disableProperty().set(true);
        }
    }
    
    private void initializeFilmovi(boolean samoCitanje) {
        
        preuzmiSveFilmove();
        
        Timer t = new Timer();
        
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        updateFilm();
                    }
                });
            }
        }, 0, 3000);
          
        filmIdCOL.prefWidthProperty().bind(filmoviTBL.widthProperty().multiply(0.15));
        filmNazivCOL.prefWidthProperty().bind(filmoviTBL.widthProperty().multiply(0.15));
        filmOpisCOL.prefWidthProperty().bind(filmoviTBL.widthProperty().multiply(0.25));
        filmTrajanjeCOL.prefWidthProperty().bind(filmoviTBL.widthProperty().multiply(0.15));
        filmSLikaCOL.prefWidthProperty().bind(filmoviTBL.widthProperty().multiply(0.15));
        filmZanroviCOL.prefWidthProperty().bind(filmoviTBL.widthProperty().multiply(0.15));
        
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
        
        
        izmijeniFilmBTN.setOnAction((event) -> {
            FilmOO flm = filmoviTBL.getSelectionModel().getSelectedItem();
            if(flm != null){
                FilmFormController control = new FilmFormController(flm, true);
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/FilmForm.fxml"));
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
            }
        });
        
        dodajFilmBTN.setOnAction((event) -> {
            FilmFormController control = new FilmFormController(null, false);
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/FilmForm.fxml"));
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
        
        obrisiFilmBTN.setOnAction((event) -> {
            FilmOO flm = filmoviTBL.getSelectionModel().getSelectedItem();
            if(flm != null){
                FilmAdapter.obrisi(flm.getFilmId());
            }
        });
        
        if(samoCitanje){
            obrisiFilmBTN.disableProperty().set(true);
            izmijeniFilmBTN.disableProperty().set(true);
            dodajFilmBTN.disableProperty().set(true);
        }
    }
    
    private void initializeArtikal(boolean samoCitanje) {
        preuzmiSveArtikle();
        
        Timer t = new Timer();
        
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        updateArtikal();
                    }
                });
            }
        }, 0, 3000);
        
        artikalNazivColumn.prefWidthProperty().bind(artikalTable.widthProperty().multiply(0.2));
        artikalTipColumn.prefWidthProperty().bind(artikalTable.widthProperty().multiply(0.2));
        artikalBarkodColumn.prefWidthProperty().bind(artikalTable.widthProperty().multiply(0.2));
        artikalKolicinaColumn.prefWidthProperty().bind(artikalTable.widthProperty().multiply(0.2));
        artikalCijenaColumn.prefWidthProperty().bind(artikalTable.widthProperty().multiply(0.2));
        
        artikalNazivColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        artikalTipColumn.setCellValueFactory(new PropertyValueFactory<>("tip"));
        artikalBarkodColumn.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        artikalKolicinaColumn.setCellValueFactory(new PropertyValueFactory<>("kolicinaNaStanju"));
        artikalCijenaColumn.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        
        artikalTable.setItems(artikli);
        
        artikalSearchBTN.setOnAction((event) -> {
            pretraziArtikal(artikalSearchTXT.getText());
        });
        
        artikalSearchTXT.setOnKeyReleased((event) -> {
           pretraziArtikal(artikalSearchTXT.getText());
        });
        
        
        artikalAddBTN.setOnAction((event) -> {
            ArtikalFormController control = new ArtikalFormController(null, false);
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/ArtikalForm.fxml"));
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
        
        artikalEditBTN.setOnAction((event) -> {
            ArtikalOO art = artikalTable.getSelectionModel().getSelectedItem();
            if (art != null){
                ArtikalFormController control = new ArtikalFormController(art, true);
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/ArtikalForm.fxml"));
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
            }
        });
        
        artikalEraseBTN.setOnAction((event) -> { 
            ArtikalOO art = artikalTable.getSelectionModel().getSelectedItem();
            if(art != null){
                ArtikalAdapter.obrisi(art.getArtikalId());
            }
        });
        
        if(samoCitanje){
            artikalEraseBTN.disableProperty().set(true);
            artikalEditBTN.disableProperty().set(true);
            artikalAddBTN.disableProperty().set(true);
        }
    }
    
    
    private void initializeZaposleni(boolean samoCitanje) {
        
        preuzmiSveZaposlene();
        
        Timer t = new Timer();
        
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        updateZaposleni();
                    }
                });
            }
        }, 0, 3000);
        
        zaposleniImeColumn.prefWidthProperty().bind(zaposleniTable.widthProperty().multiply(0.2));
        zaposleniPrezimeColumn.prefWidthProperty().bind(zaposleniTable.widthProperty().multiply(0.2));
        zaposleniJmbgColumn.prefWidthProperty().bind(zaposleniTable.widthProperty().multiply(0.2));
        zaposleniPlataColumn.prefWidthProperty().bind(zaposleniTable.widthProperty().multiply(0.2));
        zaposleniPozicijaColumn.prefWidthProperty().bind(zaposleniTable.widthProperty().multiply(0.2));
        
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
            ZaposleniOO zap = zaposleniTable.getSelectionModel().getSelectedItem();
            if(zap != null){
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
            }
        });
        
        zaposleniAddBTN.setOnAction((event) -> {
            ZaposleniFormController control = new ZaposleniFormController(null, false);
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
            ZaposleniOO zap = zaposleniTable.getSelectionModel().getSelectedItem();
            if(zap != null){
                ZaposleniAdapter.obrisi(zap.getZaposleniId());
            }
        });
        
        if(samoCitanje){
            zaposleniEraseBTN.disableProperty().set(true);
            zaposleniEditBTN.disableProperty().set(true);
            zaposleniAddBTN.disableProperty().set(true);
        }
    }
    
    private void initializeOprema(boolean samoCitanje) {
    
        preuzmiSvuOpremu();
        
        Timer t = new Timer();
        
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        updateOprema();
                    }
                });
            }
        }, 0, 3000);
        
        opremaBrojInventaraColumn.prefWidthProperty().bind(opremaTable.widthProperty().multiply(0.33));
        opremaNazivOpremeColumn.prefWidthProperty().bind(opremaTable.widthProperty().multiply(0.34));
        opremaIspravnostColumn.prefWidthProperty().bind(opremaTable.widthProperty().multiply(0.33));
        
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
            OpremaOO it = opremaTable.getSelectionModel().getSelectedItem();
            
            if(it != null){
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
            }
        });
        
        opremaAddBTN.setOnAction((event) -> {
            OpremaFormController opremaController = new OpremaFormController(null, false);
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
            OpremaOO op = opremaTable.getSelectionModel().getSelectedItem();
            if(op != null){
                OpremaAdapter.obrisi(op.getOpremaId());
            }
            
        });
        
        if(samoCitanje){
            opremaEditBTN.disableProperty().set(true);
            opremaEraseBTN.disableProperty().set(true);
            opremaAddBTN.disableProperty().set(true);
        }
    }
    
    private void initializeTransakcije(boolean samoCitanje){
        preuzmiSveTransakcije();
        
        Timer t = new Timer();
        
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        updateTransakcija();
                    }
                });
            }
        }, 0, 3000);
        
        transakcijaVrstaColumn.prefWidthProperty().bind(traksancijaTable.widthProperty().multiply(0.2));
        transakcijaPrimalacColumn.prefWidthProperty().bind(traksancijaTable.widthProperty().multiply(0.2));
        transakcijaPosiljalacColumn.prefWidthProperty().bind(traksancijaTable.widthProperty().multiply(0.2));
        transakcijaDatumColumn.prefWidthProperty().bind(traksancijaTable.widthProperty().multiply(0.2));
        transakcijaIznosColumn.prefWidthProperty().bind(traksancijaTable.widthProperty().multiply(0.2));
        
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
        
        transakcijaAddBTN.setOnAction((event) -> {
                TransakcijaFormController control = new TransakcijaFormController(null, false);
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/TransakcijaForm.fxml"));
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
        
        transakcijaEditBTN.setOnAction((event) -> {
            TransakcijaOO tr = traksancijaTable.getSelectionModel().getSelectedItem();
            if(tr != null){
                TransakcijaFormController control = new TransakcijaFormController(tr, true);
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/TransakcijaForm.fxml"));
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
            }
        });
        
        transakcijaEraseBTN.setOnAction((event) -> {
            TransakcijaOO tr = traksancijaTable.getSelectionModel().getSelectedItem();
            if(tr != null){
                TransakcijaAdapter.obrisi(tr.getTransakcijaId());
            } 
       });
        
        if(samoCitanje){
            transakcijaEditBTN.disableProperty().set(true);
            transakcijaEraseBTN.disableProperty().set(true);
            transakcijaAddBTN.disableProperty().set(true);
        }
    }
    
    private void initializeFakture(boolean samoCitanje){
        preuzmiSveFakture();
        
        Timer t = new Timer();
        
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        updateFaktura();
                    }
                });
            }
        }, 0, 3000);
        
        fakturaBrojRacunaColumn.prefWidthProperty().bind(fakturaTable.widthProperty().multiply(0.17));
        fakturaNazivRobeColumn.prefWidthProperty().bind(fakturaTable.widthProperty().multiply(0.17));
        fakturaJedinicaMjereColumn.prefWidthProperty().bind(fakturaTable.widthProperty().multiply(0.17));
        fakturaKolicinaColumn.prefWidthProperty().bind(fakturaTable.widthProperty().multiply(0.17));
        fakturaCijenaColumn.prefWidthProperty().bind(fakturaTable.widthProperty().multiply(0.16));
        fakturaDatumColumn.prefWidthProperty().bind(fakturaTable.widthProperty().multiply(0.16));
        fakturaRacunIzdaoColumn.prefWidthProperty().bind(fakturaTable.widthProperty().multiply(0.16));
        
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
        
        fakturaAddBTN.setOnAction((event) -> {
                FakturaFormController control = new FakturaFormController(null, false);
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/FakturaForm.fxml"));
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
        
      fakturaEditBTN.setOnAction((event) -> {
          FakturaOO fk = fakturaTable.getSelectionModel().getSelectedItem();
            if(fk != null){
                FakturaFormController control = new FakturaFormController(fk, true);
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/unibl/etf/multiplex/fxml/FakturaForm.fxml"));
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
            }
      });
      
      fakturaObrisiBTN.setOnAction((event) -> {
          FakturaOO fk = fakturaTable.getSelectionModel().getSelectedItem();
          if(fk != null){
              FakturaAdapter.obrisi(fk.getFakturaId());
          }
      });
      
      if(samoCitanje){
          fakturaEditBTN.disableProperty().set(true);
          fakturaObrisiBTN.disableProperty().set(true);
          fakturaAddBTN.disableProperty().set(true);
      }
    
    }
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        if("Direktor".equals(vrstaKorisnika)){
            initializeZaposleni(true);
            
            taboviTPN.getTabs().remove(opremaTAB);
            taboviTPN.getTabs().remove(artikalTAB);
            taboviTPN.getTabs().remove(fakturaTAB);
            taboviTPN.getTabs().remove(transakcijaTAB);
            taboviTPN.getTabs().remove(filmTAB);
            taboviTPN.getTabs().remove(salaTAB);
            taboviTPN.getTabs().remove(projekcijaTAB);
            taboviTPN.getTabs().remove(zanrTAB);
            taboviTPN.getTabs().remove(ponudaZaFilmTAB);
            
        }else if("Pravnik".equals(vrstaKorisnika)){
            initializeZaposleni(false);
            
            taboviTPN.getTabs().remove(opremaTAB);
            taboviTPN.getTabs().remove(artikalTAB);
            taboviTPN.getTabs().remove(fakturaTAB);
            taboviTPN.getTabs().remove(transakcijaTAB);
            taboviTPN.getTabs().remove(filmTAB);
            taboviTPN.getTabs().remove(salaTAB);
            taboviTPN.getTabs().remove(projekcijaTAB);
            taboviTPN.getTabs().remove(zanrTAB);
            taboviTPN.getTabs().remove(ponudaZaFilmTAB);
        }else if("Kinooperater".equals(vrstaKorisnika)){
            initializeFilmovi(true);
            initializeOprema(false);
            initializeProjekcija(true);
            
            taboviTPN.getTabs().remove(artikalTAB);
            taboviTPN.getTabs().remove(fakturaTAB);
            taboviTPN.getTabs().remove(transakcijaTAB);
            taboviTPN.getTabs().remove(salaTAB);
            taboviTPN.getTabs().remove(zanrTAB);
            taboviTPN.getTabs().remove(ponudaZaFilmTAB);
            taboviTPN.getTabs().remove(zaposleniTAB);
        }else if("Prodavac karata".equals(vrstaKorisnika)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/multiplex/fxml/ProdajaRezervisanjeKarata.fxml"));
            try {
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Prodavac karata"); 
                stage.setOnCloseRequest((WindowEvent event1) -> {
                    System.exit(1);
                });
                stage.show();
                ((Stage) taboviTPN.getScene().getWindow()).close();
            }catch (IOException ex) {
                Logger.getLogger(Multiplex.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }else if("Prodavac hrane i pića".equals(vrstaKorisnika)){//   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/multiplex/fxml/Racun.fxml"));
            try {
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Prodavac hrane i pića"); 
                stage.setOnCloseRequest((WindowEvent event1) -> {
                    System.exit(1);
                });
                stage.show();
                ((Stage) taboviTPN.getScene().getWindow()).close();
            }catch (IOException ex) {
                Logger.getLogger(Multiplex.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if("Skladištar".equals(vrstaKorisnika)){
            initializeArtikal(false);
            
            taboviTPN.getTabs().remove(opremaTAB);
            taboviTPN.getTabs().remove(zaposleniTAB);
            taboviTPN.getTabs().remove(fakturaTAB);
            taboviTPN.getTabs().remove(transakcijaTAB);
            taboviTPN.getTabs().remove(filmTAB);
            taboviTPN.getTabs().remove(salaTAB);
            taboviTPN.getTabs().remove(projekcijaTAB);
            taboviTPN.getTabs().remove(zanrTAB);
            taboviTPN.getTabs().remove(ponudaZaFilmTAB);
        }else if("Menadžer".equals(vrstaKorisnika)){
            initializePonude(false);
            initializeFilmovi(false);
            initializeSala(false);
            initializeProjekcija(false);
            initializeZaposleni(true);
            initializeZanr(false);
            
            taboviTPN.getTabs().remove(opremaTAB);
            taboviTPN.getTabs().remove(transakcijaTAB);
            taboviTPN.getTabs().remove(fakturaTAB);
            taboviTPN.getTabs().remove(artikalTAB);
        
        }else if("Računovođa".equals(vrstaKorisnika)){
            initializeFakture(false);
            initializeTransakcije(false);
            initializeZaposleni(true);
            
            taboviTPN.getTabs().remove(opremaTAB);
            taboviTPN.getTabs().remove(artikalTAB);
            taboviTPN.getTabs().remove(filmTAB);
            taboviTPN.getTabs().remove(salaTAB);
            taboviTPN.getTabs().remove(projekcijaTAB);
            taboviTPN.getTabs().remove(zanrTAB);
            taboviTPN.getTabs().remove(ponudaZaFilmTAB);
            
        }else{}
        
    }
}
