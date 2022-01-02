/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import controller.AuthentificationController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fares
 */
public class DashbordFormateurController implements Initializable {

    @FXML
    private Button gotocat;
    @FXML
    private Button gotocour;
    @FXML
    private Button gotorecl;
    @FXML
    private AnchorPane fenetredash;
    @FXML
    private Button gotoChapitre;
    @FXML
    private Hyperlink profilondashs;
    @FXML
    private Hyperlink profilondashs1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void gotocategorie(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("CategorieGestion.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Gestion des Categories");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotocours(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("CoursGestion.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Gestion des Cours");

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void gotoreclamations(ActionEvent event) throws IOException {
     Parent page1 = FXMLLoader.load(getClass().getResource("/view/Reclamation.fxml"));
        Scene scene = new Scene(page1, 1249, 784);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Reclamation");

        stage.setScene(scene);
        stage.show();
    }

    private void Returnnn(ActionEvent event) throws IOException {
        AnchorPane panes = FXMLLoader.load(getClass().getResource("FormateurEtudiant.fxml"));
        fenetredash.getChildren().setAll(panes);
    }
    @FXML
    private void GotoChapitres(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("ChapitresGestion.fxml"));
        Scene scene = new Scene(page1, 1249, 784);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Gestion des Chapitres");

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void logout(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("Bienvenue.fxml"));
        Scene scene = new Scene(page1, 1106, 819);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Inscrivez Vous");

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotodiscussss(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/view/articleUI.fxml"));
        Scene scene = new Scene(page1, 1180, 734);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Zone de discussion");

        stage.setScene(scene);
        stage.show();  
        
    }

}
