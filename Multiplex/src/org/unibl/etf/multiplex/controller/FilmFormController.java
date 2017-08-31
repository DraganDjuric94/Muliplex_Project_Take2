/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.unibl.etf.model.adapter.FilmAdapter;
import org.unibl.etf.model.adapter.ZanrAdapter;
import org.unibl.etf.model.domain.oo.FilmOO;
import org.unibl.etf.model.domain.oo.ZanrOO;

/**
 * FXML Controller class
 *
 * @author juhu
 */
public class FilmFormController implements Initializable {

    @FXML
    private TextField filmFormNazivTXT;
    @FXML
    private TextField filmFormTrajanjeTXT;
    @FXML
    private TextArea filmFormOpisTA;
    @FXML
    private Button filmFormSlikaBTN;
    @FXML
    private Label filmFormSlikaLBL;
    @FXML
    private ListView<ZanrOO> filmFormSviZanroviLV;
    @FXML
    private ListView<ZanrOO> filmFormUbaceniZanroviLV;
    @FXML
    private Button filmFormUbaciZanrBTN;
    @FXML
    private Button filmFormIzbaciZanrBTN;
    @FXML
    private Button filmFormOkBTN;
    @FXML
    private Button filmFormPonistiBTN;
    
    private File file;
    private boolean mijenjanaSlika;
    private boolean azuriranje = false;
    private FilmOO stari;
    private String naziv, trajanje, opis, slika;
    private ObservableList<ZanrOO> zanroviUbaceni = FXCollections.observableArrayList();
    private ObservableList<ZanrOO> zanroviSvi = FXCollections.observableArrayList();
    
    public FilmFormController(FilmOO film, boolean azur){
        azuriranje = azur;
        if(azuriranje){
            stari = film;
            naziv = film.getNaziv();
            opis = film.getOpis();
            slika = film.getSlika();
            zanroviUbaceni.addAll(film.getZanrovi());
            trajanje = film.getTrajanje().toString();
        }else{
            zanroviUbaceni.clear();
        }
        preuzmiZanrove();
    }
    
    public void preuzmiZanrove(){
        zanroviSvi.clear();
        zanroviSvi.addAll(ZanrAdapter.preuzmiSve());
    }
    
    public void dodaj(){
        String tip = (filmFormSlikaLBL.getText().contains(".jpg")) ? ".jpg" : ".png";
        String sl = ("images/" + String.valueOf(System.nanoTime()) + tip);
        try {
            Files.copy(Paths.get(file.getPath()), Paths.get(sl), StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ex) {
            Logger.getLogger(FilmFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FilmOO flm = new FilmOO(null,
                filmFormNazivTXT.getText(),
                Integer.parseInt(filmFormTrajanjeTXT.getText()),
                filmFormOpisTA.getText(),
                sl,
                zanroviUbaceni);
        FilmAdapter.unesi(flm);
    }
    
    public void azuriraj(){
        String sl = "";
        if(mijenjanaSlika){
            String tip = (filmFormSlikaLBL.getText().contains(".jpg")) ? ".jpg" : ".png";
            sl = ("images/" + String.valueOf(System.nanoTime()) + tip);
            try {
                Files.copy(Paths.get(file.getPath()), Paths.get(sl), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(FilmFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            sl = stari.getSlika();
        }
        FilmOO flm = new FilmOO(stari.getFilmId(),
                filmFormNazivTXT.getText(),
                Integer.parseInt(filmFormTrajanjeTXT.getText()),
                filmFormOpisTA.getText(),
                sl,
                zanroviUbaceni);
        FilmAdapter.izmijeni(flm);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filmFormSviZanroviLV.setItems(zanroviSvi);
        filmFormUbaceniZanroviLV.setItems(zanroviUbaceni);
        
        if(azuriranje){
            filmFormSlikaLBL.setText(slika);
            filmFormNazivTXT.setText(naziv);
            filmFormOpisTA.setText(opis);
            filmFormTrajanjeTXT.setText(trajanje);
        }
        
        filmFormPonistiBTN.setOnAction((event) -> {
            ((Stage) filmFormIzbaciZanrBTN.getScene().getWindow()).close();
        });
    }    

    @FXML
    private void filmFormSlikaBTNHandler(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File f2 = fc.showOpenDialog(filmFormIzbaciZanrBTN.getScene().getWindow());
        file = (f2 != null) ? f2 : file;
        filmFormSlikaLBL.setText(file.getName());
        if(file != null){
            mijenjanaSlika = true;
        }
    }

    @FXML
    private void filmFormUbaciZanrBTNHandler(ActionEvent event) {
        ZanrOO z = filmFormSviZanroviLV.getSelectionModel().getSelectedItem();
        if(z != null){
            if(!zanroviUbaceni.contains(z)){
                zanroviUbaceni.add(z);
            }
        }
    }

    @FXML
    private void filmFormIzbaciZanrBTNHandler(ActionEvent event) {
        ZanrOO z = filmFormUbaceniZanroviLV.getSelectionModel().getSelectedItem();
        if(z != null){
            zanroviUbaceni.remove(z);
        }
    }

    @FXML
    private void filmFormOkBTNHandler(ActionEvent event) {
        if(azuriranje){
            azuriraj();
        }else{
            dodaj();
        }
        ((Stage) filmFormIzbaciZanrBTN.getScene().getWindow()).close();
    }
    
}
