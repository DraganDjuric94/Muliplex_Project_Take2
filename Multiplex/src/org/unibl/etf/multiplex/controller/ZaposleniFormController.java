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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.unibl.etf.model.adapter.PozicijaAdapter;
import org.unibl.etf.model.adapter.ZanrAdapter;
import org.unibl.etf.model.adapter.ZaposleniAdapter;
import org.unibl.etf.model.domain.oo.PozicijaOO;
import org.unibl.etf.model.domain.oo.ZaposleniOO;

/**
 * FXML Controller class
 *
 * @author juhu
 */
public class ZaposleniFormController implements Initializable {

    @FXML
    private AnchorPane zaposleniForm;
    @FXML
    private TextField zaposleniFormImeTXT;
    @FXML
    private TextField zaposleniFormPrezimeTXT;
    @FXML
    private TextField zaposleniFormPlataTXT;
    @FXML
    private TextField zaposleniFormJmbgTXT;
    @FXML
    private CheckBox zaposleniFormAktivanCHK;
    @FXML
    private Button zaposleniFormOkBTN;
    @FXML
    private Button zaposleniFormCancelBTN;
    @FXML
    private TextField zaposleniFormKorImeTXT;
    @FXML
    private TextField zaposleniFormLozinkaTXT;
    @FXML
    private ComboBox<String> zaposleniFormPozicijaCBX;
    
    private boolean azuriranje = false;
    private ObservableList<String> pozicije = FXCollections.observableArrayList();
    private ZaposleniOO stari;
    private String ime, prezime, jmbg, plata, korIme, loz;
    private boolean aktivan;

    /**
     * Initializes the controller class.
     */
    
    
    public ZaposleniFormController(ZaposleniOO zap, boolean azuriranje){
        if(azuriranje){
            stari = zap;
            System.out.println(zap);
            this.azuriranje = azuriranje;
            ime = zap.getIme();
            prezime = zap.getPrezime();
            jmbg = zap.getJmbg();
            plata = zap.getPlata().toString();
            aktivan = zap.getAktivan();
            korIme = zap.getKorisnickoIme();
            loz = zap.getLozinka();
        }
        preuzmiPozicije(); 
    }
    
    public void azuriraj(){
        ZaposleniOO zap = new ZaposleniOO(stari.getZaposleniId(), zaposleniFormImeTXT.getText(), 
                zaposleniFormPrezimeTXT.getText(), zaposleniFormJmbgTXT.getText(), 
                zaposleniFormKorImeTXT.getText(), zaposleniFormLozinkaTXT.getText(), 
                Double.parseDouble(zaposleniFormPlataTXT.getText()), 
                zaposleniFormAktivanCHK.isSelected(), null);
        if(zaposleniFormPozicijaCBX.getSelectionModel().getSelectedItem().equals(stari.getPozicija().getNaziv())){
            zap.setPozicija(stari.getPozicija());
        }else{
            PozicijaOO poz = new PozicijaOO(null, zaposleniFormPozicijaCBX.getSelectionModel().getSelectedItem(),
                new Date(), null);
            PozicijaAdapter.unesi(poz);
            zap.setPozicija(poz);
        }
        ZaposleniAdapter.izmijeni(zap);
        stari.getPozicija().setDatumDo(new Date());
        PozicijaAdapter.izmijeni(stari.getPozicija());
    }
    
    public void dodaj(){
        ZaposleniOO zap = new ZaposleniOO(null, zaposleniFormImeTXT.getText(), 
                zaposleniFormPrezimeTXT.getText(), zaposleniFormJmbgTXT.getText(), 
                zaposleniFormKorImeTXT.getText(), zaposleniFormLozinkaTXT.getText(), 
                Double.parseDouble(zaposleniFormPlataTXT.getText()), 
                zaposleniFormAktivanCHK.isSelected(), null);
        PozicijaOO poz = new PozicijaOO(null, zaposleniFormPozicijaCBX.getSelectionModel().getSelectedItem(),
                new Date(), null);
        zap.setPozicija(poz);
        
        ZaposleniAdapter.unesi(zap);
        
    }
    
    public void preuzmiPozicije(){
        ArrayList<PozicijaOO> poz = PozicijaAdapter.preuzmiSve();
        for(PozicijaOO p : poz){
            if(!pozicije.contains(p.getNaziv())){
                pozicije.add(p.getNaziv());
            }
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        zaposleniFormPozicijaCBX.setItems(pozicije);
        if(azuriranje){
            zaposleniFormImeTXT.setText(ime);
            zaposleniFormPrezimeTXT.setText(prezime);
            zaposleniFormJmbgTXT.setText(jmbg);
            zaposleniFormPlataTXT.setText(plata);
            zaposleniFormAktivanCHK.setSelected(aktivan);
            zaposleniFormKorImeTXT.setText(korIme);
            zaposleniFormLozinkaTXT.setText(loz);
            int index = 0;
            for(String s : pozicije){
                if(s.equals(stari.getPozicija().getNaziv())){
                    break;
                }
                index++;
            }
            zaposleniFormPozicijaCBX.getSelectionModel().select(index);
        }else{
            zaposleniFormPozicijaCBX.getSelectionModel().select(0);
        }
        
        zaposleniFormOkBTN.setOnAction((event) -> {
            //Provjeriti da li su unesena polja i sl
            if(azuriranje){
                azuriraj();
            }else{
                dodaj();
            }   
        });
        
    }    
    
}
