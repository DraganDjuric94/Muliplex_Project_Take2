/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.unibl.etf.model.adapter.ZaposleniAdapter;
import org.unibl.etf.model.domain.oo.ZaposleniOO;
import org.unibl.etf.multiplex.Multiplex;

/**
 * FXML Controller class
 *
 * @author juhu
 */
public class LoginController implements Initializable {

    @FXML
    private Label greskaLBL;

    @FXML
    private PasswordField lozinkaPSS;

    @FXML
    private TextField korImeTXT;

    @FXML
    private Button prijavaBTN;
    
    private ZaposleniOO z;
    
    private boolean provjeriPrijavu(String korIme, String lozinka){
        ArrayList<ZaposleniOO> zap = ZaposleniAdapter.preuzmiPoKorisnickomImenu(korIme);
        if(zap.size() == 1){
            z = zap.get(0);
            if(z.getLozinka().equals(lozinka)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    private void otvoriProzor(){
        if(z.getNazivPozicije().equals("Prodavac karata")){
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
                ((Stage)prijavaBTN.getScene().getWindow()).close();
            }catch (IOException ex) {
                Logger.getLogger(Multiplex.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(z.getNazivPozicije().equals("Prodavac hrane i pića")){
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
                ((Stage)prijavaBTN.getScene().getWindow()).close();
            }catch (IOException ex) {
                Logger.getLogger(Multiplex.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/multiplex/fxml/PrikazPodataka.fxml"));
                try {
                    loader.setController(new GlavniController(z.getNazivPozicije()));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle(z.getNazivPozicije());

                    stage.setOnCloseRequest((WindowEvent event1) -> {
                        System.exit(1);
                    });

                    stage.show();

                    ((Stage)prijavaBTN.getScene().getWindow()).close();

                }catch (IOException ex) {
                    Logger.getLogger(Multiplex.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        prijavaBTN.setOnAction((event) -> {
            MessageDigest digest;
            String pass = "";
            try {
                digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(lozinkaPSS.getText().getBytes(StandardCharsets.UTF_8));
                pass = Base64.getEncoder().encodeToString(hash);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ZaposleniFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(provjeriPrijavu(korImeTXT.getText(), pass)){
                otvoriProzor();
            }else{
                greskaLBL.setVisible(true);
            }
        });
        
        lozinkaPSS.setOnKeyPressed((KeyEvent event) -> {
           if (event.getCode().equals(KeyCode.ENTER)){
               MessageDigest digest;
                String pass = "";
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                    byte[] hash = digest.digest(lozinkaPSS.getText().getBytes(StandardCharsets.UTF_8));
                    pass = Base64.getEncoder().encodeToString(hash);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(ZaposleniFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
               if(provjeriPrijavu(korImeTXT.getText(), pass)){
                otvoriProzor();
            }else{
                greskaLBL.setVisible(true);
            }
           }
        });
        
        korImeTXT.setOnKeyPressed((KeyEvent event) -> {
           if (event.getCode().equals(KeyCode.ENTER)){
               MessageDigest digest;
                String pass = "";
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                    byte[] hash = digest.digest(lozinkaPSS.getText().getBytes(StandardCharsets.UTF_8));
                    pass = Base64.getEncoder().encodeToString(hash);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(ZaposleniFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
               if(provjeriPrijavu(korImeTXT.getText(), pass)){
                otvoriProzor();
            }else{
                greskaLBL.setVisible(true);
            }
           }
        });
              
    }    
    
}
