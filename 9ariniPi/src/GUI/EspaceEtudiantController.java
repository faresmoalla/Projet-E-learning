/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DB;
import controller.AuthentificationController;
import entities.Chapitres;
import entities.Cours;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.ResultSet;
import javafx.scene.control.Hyperlink;
import javafx.scene.media.MediaView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
/**
 * FXML Controller class
 *
 * @author fares
 */
public class EspaceEtudiantController implements Initializable {
     public static Cours connectedCours;
    @FXML
    private Hyperlink Profilespacetu;
    @FXML
    private MediaView VideoAcceuil1;
    @FXML
    private Button pause1;
    @FXML
    private Button reset1;
    @FXML
    private Button play1;

    public EspaceEtudiantController(TextField rchcours) {
        this.rchcours = rchcours;
    }
    
    
    public EspaceEtudiantController() throws IOException, SQLException, NoSuchAlgorithmException  {
         
   
        connexion = DB.getInstance().getConnection();
    }
    Connection connexion;  
    @FXML
    private TextField rchcours;
    @FXML
    private Button rechdecours;
 private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        file = new File("C:\\Users\\fares\\Desktop\\9arini-DEV\\9ariniPi\\src\\Images\\pub.mp4");
        media= new Media(file.toURI().toString());
        mediaPlayer= new MediaPlayer(media);
        VideoAcceuil1.setMediaPlayer(mediaPlayer); 
        mediaPlayer.play();
        Profilespacetu.setText(AuthentificationController.connectedUser.getUtilisateurNom());
                Profilespacetu.setText(AuthentificationController.connectedUser.getUtilisateurNom()+" "+AuthentificationController.connectedUser.getUtilisateurPrenom());

        // TODO
    }    
   
    @FXML
    private void RechercheCours(ActionEvent event) throws IOException, SQLException, NoSuchAlgorithmException {
                mediaPlayer.pause();

         String req ="SELECT * from cours WHERE nomCours LIKE '"+rchcours.getText()+ "'";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           
                    Cours c = new Cours(rst.getInt("id")
                    , rst.getString("utilisateurNom")
                    , rst.getString("nomCours")
                    , rst.getInt("nbrChapitres")
                    , rst.getString("description")
                            , rst.getString("coursImg")
    , rst.getInt("categorie_id"));
            EspaceEtudiantController.connectedCours=c;      
            try{
                   Parent page1=FXMLLoader.load(getClass().getResource("RechercheCours.fxml"));
Scene scene=new Scene(page1,848,654);
       Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
                              stage.setTitle(EspaceEtudiantController.connectedCours.getNomCours() + " " + ":Résultat de la recherche");
    stage.setScene(scene);
    stage.show();   
            
         } catch (IOException ex) {
        }                   
    }  
}
    @FXML
    private void PauseMedia1(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    private void ResetMedia1(ActionEvent event) {
         if(mediaPlayer.getStatus()!= MediaPlayer.Status.READY){
                    mediaPlayer.seek(Duration.seconds(0.0));
        }
    }

    @FXML
    private void Playvideo1(ActionEvent event) {
          mediaPlayer.play();
    }
    @FXML
    private void toprofil(ActionEvent event) throws IOException {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/view/ModifierProfil.fxml"));
            Scene scene = Profilespacetu.getScene();
            scene.setRoot(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }  

    @FXML
    private void logfffss(ActionEvent event) throws IOException {
          Parent page1 = FXMLLoader.load(getClass().getResource("Bienvenue.fxml"));
        Scene scene = new Scene(page1, 1106, 819);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Inscrivez Vous");

        stage.setScene(scene);
        stage.show();
        mediaPlayer.pause();
    }

    @FXML
    private void godiscuss(ActionEvent event) throws IOException {
             Parent page1 = FXMLLoader.load(getClass().getResource("/view/articleUI.fxml"));
        Scene scene = new Scene(page1, 1180, 734);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Zone de discussion");

        stage.setScene(scene);
        stage.show();  
    }
    
    
    
    
}