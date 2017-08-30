/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex;

import java.io.IOException;
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
import org.unibl.etf.multiplex.controller.MenadzerController;
import org.unibl.etf.multiplex.controller.PravnikControlelr;

/**
 *
 * @author juhu
 */
public class Multiplex extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/PrikazPodataka.fxml"));
            try {
                loader.setController(new PravnikControlelr());
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("");
                
                stage.setOnCloseRequest((WindowEvent event1) -> {
                    System.exit(1);
                });
                
                stage.show();
            }catch (IOException ex) {
                Logger.getLogger(Multiplex.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
