/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author fares
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
       try {
         Parent root = FXMLLoader.load(getClass().getResource("Bienvenue.fxml"));
            Scene scene = new Scene(root);
           // 
          
             primaryStage.setTitle("Bienvenue dans 9arini");
        primaryStage.setScene(scene);
            /* Image im= new Image("C:\\Users\\fares\\Desktop\\9arini-DEV\\9ariniPi\\src\\Images\\php.jpg");
            primaryStage.getIcons().add(im);*/
        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        System.out.println("edddd");
    }
    
}
