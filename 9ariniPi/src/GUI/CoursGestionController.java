/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DB;
import com.mysql.jdbc.Connection;
import entities.Categorie;
import entities.Cours;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import services.Categorieservice;
import services.Coursservice;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author fares
 */
public class CoursGestionController implements Initializable {

    public static Cours connectedCourss;
    public static Categorie connectedCategorie;
    private TableColumn<Cours, String> categorieimgggg;
    @FXML
    private TableColumn<?, ?> coursImgggg;    
    public CoursGestionController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = DB.getInstance().getConnection();
    }
    java.sql.Connection connexion;

    @FXML
    private Hyperlink ReturnAcceuilcateg5;
    @FXML
    private Hyperlink precedentCours;
    @FXML
    private TableView<Cours> tableCours;
    @FXML
    private TableColumn<Cours, Integer> coursIDD;
    @FXML
    private TableColumn<Cours, Integer> utilisateurIDD;
    @FXML
    private TableColumn<Cours, String> nomCourss;
    @FXML
    private TableColumn<Cours, Integer> nbrChapitress;
    @FXML
    private Button Reche;
    @FXML
    private TextField inputRech;
    @FXML
    private Button supp;
    @FXML
    private Button modiff;
    @FXML
    private Button ajcours;
    Coursservice ps = new Coursservice();
    private ObservableList<Cours> data;
    Coursservice cs = new Coursservice();
    ObservableList<Cours> obl = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Cours, String> descriptionss;
    @FXML
    private AnchorPane FenetreCoursGestion;
    @FXML
    private TableColumn<Cours, Integer> CategorieIDD;
    private Label labelcoursid;
    private Label labelutulisateurid;
    private Label labelcoursnom;
    private Label labelnbrchapitre;
    private Label labeldescription;
    private Label labelcategorieid;
    public ObservableList<Cours> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Coursservice pss = new Coursservice();
        ArrayList<Cours> c = new ArrayList<>();
        try {
            c = (ArrayList<Cours>) pss.afficherCours();
        } catch (SQLException ex) {
            System.out.println("erreurrrrrrrrrrrrr");
        }
        ObservableList<Cours> obs2 = FXCollections.observableArrayList(c);
        tableCours.setItems(obs2);
        coursIDD.setCellValueFactory(new PropertyValueFactory<>("id"));
        utilisateurIDD.setCellValueFactory(new PropertyValueFactory<>("utilisateurNom"));
        nomCourss.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
        nbrChapitress.setCellValueFactory(new PropertyValueFactory<>("nbrChapitres"));
        descriptionss.setCellValueFactory(new PropertyValueFactory<>("description"));
        CategorieIDD.setCellValueFactory(new PropertyValueFactory<>("categorie_id"));
                coursImgggg.setCellValueFactory(new PropertyValueFactory<>("coursImg"));
        try {
            list = FXCollections.observableArrayList(
                    pss.afficherCours()
            );      
            FilteredList<Cours> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Cours>) Courss -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Courss.getNomCours().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Cours> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableCours.comparatorProperty());
                tableCours.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    @FXML
    private void ReturnAcceuilAjoutcategorie5(ActionEvent event) throws IOException {

        Parent page1 = FXMLLoader.load(getClass().getResource("DashbordFormateur.fxml"));
        Scene scene = new Scene(page1, 687, 605);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                       stage.setTitle("Gestion");

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void PrecedentinterfaceCours(ActionEvent event) throws IOException {
               Parent page1 = FXMLLoader.load(getClass().getResource("DashbordFormateur.fxml"));
        Scene scene = new Scene(page1, 687, 605);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                               stage.setTitle("Gestion");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Rechercher(ActionEvent event) throws IOException {
    }

    @FXML
    private void supprimerCours(ActionEvent event) throws SQLException {
        if (event.getSource() == supp) {
            Cours cours = new Cours();
            cours.setId(tableCours.getSelectionModel().getSelectedItem().getId());  
            Coursservice cs = new Coursservice();
            cs.supprimerCours(cours);
            resetTableData();
        }

    }
    @FXML
    private void modifierCours(ActionEvent event) throws IOException {
        Coursservice ps = new Coursservice();
        Cours c = new Cours(tableCours.getSelectionModel().getSelectedItem().getId(),
                tableCours.getSelectionModel().getSelectedItem().getUtilisateurNom(),
                tableCours.getSelectionModel().getSelectedItem().getNomCours(),
                tableCours.getSelectionModel().getSelectedItem().getNbrChapitres(),
                tableCours.getSelectionModel().getSelectedItem().getDescription(),
 tableCours.getSelectionModel().getSelectedItem().getCoursImg(),

                tableCours.getSelectionModel().getSelectedItem().getCategorie_id());
        CoursGestionController.connectedCourss = c;
             Parent page1 = FXMLLoader.load(getClass().getResource("CoursModifier.fxml"));
        Scene scene = new Scene(page1, 1144, 741);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AjouterCours(ActionEvent event) throws IOException, SQLException {           
             Parent page1 = FXMLLoader.load(getClass().getResource("ajoutCours.fxml"));
            Scene scene = new Scene(page1, 1299, 884);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
     }
    
    public void resetTableData() throws SQLDataException, SQLException {

        List<Cours> listCours = new ArrayList<>();
        listCours = cs.getAllCours();
        ObservableList<Cours> data = FXCollections.observableArrayList(listCours);
        tableCours.setItems(data);
    }

    @FXML
    private void logoutcourscours(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("Bienvenue.fxml"));
        Scene scene = new Scene(page1, 1106, 819);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Inscrivez Vous");

        stage.setScene(scene);
        stage.show();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
