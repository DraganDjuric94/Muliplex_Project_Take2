/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.unibl.etf.model.adapter.SalaAdapter;
import org.unibl.etf.model.domain.oo.SalaOO;
import org.unibl.etf.model.domain.oo.SjedisteOO;
import org.unibl.etf.multiplex.controller.GlavniController;
import org.unibl.etf.multiplex.controller.PravnikController;

/**
 *
 * @author juhu
 */
public class Multiplex extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Login.fxml"));
            try {
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Softver za Multipleks");
                
                stage.setOnCloseRequest((WindowEvent event1) -> {
                    System.exit(1);
                });
                
                stage.setResizable(false);
                stage.show();
            }catch (IOException ex) {
                Logger.getLogger(Multiplex.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*List<SjedisteOO> sj1 = new ArrayList<>();
        for(int i = 1; i < 16; i++){
            for(int j = 1; j < 11; j++){
                sj1.add(new SjedisteOO(null, i, j));
            }
        }
        List<SjedisteOO> sj2 = new ArrayList<>();
        for(int i = 1; i < 21; i++){
            for(int j = 1; j < 16; j++){
                sj2.add(new SjedisteOO(null, i, j));
            }
        }
        List<SjedisteOO> sj3 = new ArrayList<>();
        for(int i = 1; i < 14; i++){
            for(int j = 1; j < 17; j++){
                sj3.add(new SjedisteOO(null, i, j));
            }
        }
        SalaOO s1 = new SalaOO(null, 15, 10, sj1);
        SalaOO s2 = new SalaOO(null, 20, 15, sj2);
        SalaOO s3 = new SalaOO(null, 13, 16, sj3);
        SalaAdapter.unesi(s1);
        SalaAdapter.unesi(s2);
        SalaAdapter.unesi(s3);
        */
        launch(args);
        

    }
    
}
