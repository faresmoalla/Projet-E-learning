/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DB;
import entities.Categorie;
import entities.Chapitres;
import entities.Cours;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.Categorieservice;
import services.Chapitresservice;

/**
 * FXML Controller class
 *
 * @author fares
 */
public class ChapitresGestionController implements Initializable {

    public ChapitresGestionController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = DB.getInstance().getConnection();
    }
    Connection connexion;
    @FXML
    private TableView<Chapitres> tablechapitres;
    @FXML
    private TableColumn<Chapitres, Integer> ChapitreIDD;

    @FXML
    private TableColumn<Chapitres, String> chapitrenomm;
    @FXML
    private TableColumn<Chapitres, String> videochapitress;
    @FXML
    private TextField FnomChapitree;
    private TextField inputRech;
    @FXML
    private Hyperlink returnacceiuilchapitre;
    @FXML
    private Hyperlink precedenttchapitre;
    @FXML
    private Button modifierChapitres;
    @FXML
    private Label idlabelChapitre;
    @FXML
    private Label imgpathchapitre;
    @FXML
    private Button browsevideochapitre;
    @FXML
    private ImageView imgajoutchapitre;
    public ObservableList<Chapitres> list;
    @FXML
    private AnchorPane FenetreChapitres;
    @FXML
    private TextField inputRechcc;

    Chapitresservice cs = new Chapitresservice();
    @FXML
    private Button supprimerch;
    @FXML
    private Button ajimg;
    @FXML
    private TableColumn<Chapitres, Integer> CoursNommm;
    @FXML
    private ComboBox<String> CoursNomchapitre;
    private ObservableList<Chapitres> data;
    @FXML
    private Button confirmermodifierChap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Chapitresservice ps = new Chapitresservice();
        ArrayList<Chapitres> a = new ArrayList<>();
        try {
            a = (ArrayList<Chapitres>) ps.afficherChapitre();
        } catch (SQLException ex) {
        }
        ObservableList<Chapitres> obsl = FXCollections.observableArrayList(a);

        tablechapitres.setItems(obsl);
        ChapitreIDD.setCellValueFactory(new PropertyValueFactory<>("id"));
        CoursNommm.setCellValueFactory(new PropertyValueFactory<>("cours_id"));
        chapitrenomm.setCellValueFactory(new PropertyValueFactory<>("chapitreNom"));
        videochapitress.setCellValueFactory(new PropertyValueFactory<>("videoChapitre"));
        try {
            list = FXCollections.observableArrayList(
                    ps.afficherChapitre());

            FilteredList<Chapitres> filteredData = new FilteredList<>(list, e -> true);
            inputRechcc.setOnKeyReleased(e -> {
                inputRechcc.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Chapitres>) Chapitress -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Chapitress.getChapitreNom().toLowerCase().contains(lower)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Chapitres> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tablechapitres.comparatorProperty());
                tablechapitres.setItems(sortedData);

            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            String req = "select * from cours";

            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {
                Cours c = new Cours(rst.getInt("id"));
                String xx = rst.getString("id");
                CoursNomchapitre.getItems().add(xx);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void Returnacceiuilchapitre(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("DashbordFormateur.fxml"));
        Scene scene = new Scene(page1, 687, 605);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Gestion");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void PrecedentGestionChapitre(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("DashbordFormateur.fxml"));
        Scene scene = new Scene(page1, 687, 605);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Gestion");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ModifierChapitres(ActionEvent event) {
        int id7 = tablechapitres.getSelectionModel().getSelectedItem().getId();
        //nomTextField.setText(user.getNom());
        FnomChapitree.setText(tablechapitres.getSelectionModel().getSelectedItem().getChapitreNom());
        
        //CoursNomchapitre.setValue(Integer.parseInt(tablechapitres.getSelectionModel().getSelectedItem().getCours_id()));
        
        imgpathchapitre.setText(tablechapitres.getSelectionModel().getSelectedItem().getVideoChapitre());
        idlabelChapitre.setText(Integer.toString(id7));
        confirmermodifierChap.setVisible(true);
    }

    @FXML
    private void ConfirmerModifierChapitre(ActionEvent event) {
        try {
            Chapitres c1 = new Chapitres(Integer.parseInt(idlabelChapitre.getText()), 
                    Integer.parseInt(CoursNomchapitre.getValue()),
                    FnomChapitree.getText(), imgpathchapitre.getText());
            Chapitresservice chapitresservice = new Chapitresservice();
            chapitresservice.editChapitres(c1);
            resetTableDataChapitres();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimerrchapitres(ActionEvent event) throws SQLException {
        if (event.getSource() == supprimerch) {
            Chapitres chapitres = new Chapitres();
            chapitres.setId(tablechapitres.getSelectionModel().getSelectedItem().getId());
            System.out.println(tablechapitres.getSelectionModel().getSelectedItem().getId());
            Chapitresservice chapitreservice = new Chapitresservice();
            chapitreservice.supprimerChapitres(chapitres);
            resetTableDataChapitres();
        }
    }

    public void resetTableDataChapitres() throws SQLDataException, SQLException {
        List<Chapitres> listChapitre = new ArrayList<>();
        listChapitre = cs.getAllChapitres();
        ObservableList<Chapitres> data = FXCollections.observableArrayList(listChapitre);
        tablechapitres.setItems(data);
    }

    @FXML
    private void Ajouterimggch(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("select your media(*.mp4)", "*.mp4");
        chooser.getExtensionFilters().add(filter);
        File file = chooser.showOpenDialog(null);
        if (file != null) {
            Media source = new Media(file.toURI().toString());
            MediaPlayer player = new MediaPlayer(source);
            MediaView view = new MediaView(player);
            player.play();
            imgpathchapitre.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void AjouterChapitres(ActionEvent event) throws SQLException {
        Chapitresservice productService = new Chapitresservice();
        if (FnomChapitree.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else {
            Chapitres c = new Chapitres(Integer.parseInt(CoursNomchapitre.getValue()), FnomChapitree.getText(), imgpathchapitre.getText());
            try {
                productService.ajouterProduitChapitres(c);
                resetTableDataChapitres();
            } catch (SQLException ex) {
                System.out.println("erreur");
            }
        }
    }

    @FXML
    private void logoutcahp(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("Bienvenue.fxml"));
        Scene scene = new Scene(page1, 1106, 819);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Inscrivez Vous");

        stage.setScene(scene);
        stage.show();
    }
}
