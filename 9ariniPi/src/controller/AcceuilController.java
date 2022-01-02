/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import entities.Membre;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author lahbib
 */
 
public class AcceuilController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @FXML
    private Hyperlink zd_urlprofil;
    @FXML
    private Button zd_listeMembre;
    @FXML
    private Button zd_listeFormateur;
    @FXML
    private Button zd_listeAdmin;
    @FXML
    private Button zd_listeEntrepreneur;
    @FXML
    private Hyperlink zd_deconn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String Admin = new String("Admin");
        zd_urlprofil.setText(AuthentificationController.connectedUser.getUtilisateurNom());
        if(Admin.equals(AuthentificationController.connectedUser.getUtilisateurRole())){
        zd_listeAdmin.setVisible(true);
        zd_listeEntrepreneur.setVisible(true);
        zd_listeFormateur.setVisible(true);
        zd_listeMembre.setVisible(true);}
        
        // TODO
    }   
    @FXML
    private void Toprofil(ActionEvent event)throws IOException, SQLException, NoSuchAlgorithmException{
            
        
            Parent page2 = FXMLLoader.load(getClass().getResource("/view/ModifierProfil.fxml"));
            Scene scene2 = new Scene(page2, 1106, 819);
            Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage2.setScene(scene2);
            stage2.show();
        
    }
    @FXML
    private void toListem(ActionEvent event)throws IOException, SQLException, NoSuchAlgorithmException{
            
        
            Parent page2 = FXMLLoader.load(getClass().getResource("/view/ListeMembre.fxml"));
            Scene scene2 = zd_urlprofil.getScene();
            scene2.setRoot(page2);
            Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage2.setScene(scene2);
            stage2.show();
        
    }
    @FXML
    private void toListef(ActionEvent event)throws IOException, SQLException, NoSuchAlgorithmException{
            
        
            Parent page2 = FXMLLoader.load(getClass().getResource("/view/ListeFormateur.fxml"));
            Scene scene2 = zd_urlprofil.getScene();
            scene2.setRoot(page2);
            Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage2.setScene(scene2);
            stage2.show();
        
    }
    @FXML
    private void toListea(ActionEvent event)throws IOException, SQLException, NoSuchAlgorithmException{
            
        
            Parent page2 = FXMLLoader.load(getClass().getResource("/view/ListeAdmin.fxml"));
            Scene scene2 = zd_urlprofil.getScene();
            scene2.setRoot(page2);
            Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage2.setScene(scene2);
            stage2.show();
        
    }
    @FXML
    private void toListee(ActionEvent event)throws IOException, SQLException, NoSuchAlgorithmException{
            
        
            Parent page2 = FXMLLoader.load(getClass().getResource("/view/ListeEnrepreneur.fxml"));
            Scene scene2 = zd_urlprofil.getScene();
            scene2.setRoot(page2);
            Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage2.setScene(scene2);
            stage2.show();
        
    }
    @FXML
    private void deconnecte(ActionEvent event) throws IOException {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/view/Authentification.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
        }
    }
    
}
