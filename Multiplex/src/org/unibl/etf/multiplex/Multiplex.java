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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        launch(args);
        

    }
    
}
