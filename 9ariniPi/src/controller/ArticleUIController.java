/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.AuthentificationController;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import entities.Article;
import entities.Reclamation;
import entities.Utilisateur;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.ArticleService;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author yahia
 */
public class ArticleUIController implements Initializable {

    Connection connexion;

    @FXML
    private TableView<Article> table;
    @FXML
    private TableColumn<?, ?> object;
    @FXML
    private TableColumn<?, ?> user_name;
    @FXML
    private AnchorPane article;
    @FXML
    private TextField text_recherche;
    public ObservableList<Article> list;
    @FXML
    private Button bnt_aj;
    @FXML
    private TextField aj_obj;
    @FXML
    private TextArea aj_con;
    ArticleService ps = ArticleService.getInstance();
    private ObservableList<Article> data;
    ArticleService ca = ArticleService.getInstance();

    @FXML
    private Label username;

    @FXML
    private Label userid;
    @FXML
    private Label heyy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        heyy.setText(AuthentificationController.connectedUser.getUtilisateurRole());

        ArticleService articleService = ArticleService.getInstance();
        ArrayList<Article> a = new ArrayList<>();

        try {
            a = (ArrayList<Article>) articleService.getAllArticle();

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Article> art1 = FXCollections.observableArrayList(a);
        table.setItems(art1);
        // id.setCellValueFactory(new PropertyValueFactory<>("id"));
        object.setCellValueFactory(new PropertyValueFactory<>("object"));
        user_name.setCellValueFactory(new PropertyValueFactory<>("utilisateurNom"));
        userid.setText(String.valueOf(AuthentificationController.connectedUser.getId()));
        username.setText(AuthentificationController.connectedUser.getUtilisateurNom());

        try {
            list = FXCollections.observableArrayList(
                    articleService.getAllArticle());

            FilteredList<Article> filteredData = new FilteredList<>(list, e -> true);
            text_recherche.setOnKeyReleased(e -> {
                text_recherche.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Article>) Article -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Article.getObject().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Article> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(table.comparatorProperty());
                table.setItems(sortedData);
            });

        } catch (SQLException ex) {
            Logger.getLogger(ArticleUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setItems(list);
        table.setRowFactory(tv -> {
            TableRow<Article> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Article rowData = row.getItem();
                    System.out.println(rowData);
                    AnchorPane pane;
                    try {
                        ArticleService as = ArticleService.getInstance();
                        as.setArticle(rowData);
                        pane = FXMLLoader.load(getClass().getResource("/view/detail.fxml"));
                        article.getChildren().setAll(pane);
                    } catch (IOException ex) {
                        Logger.getLogger(ArticleUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        }
        );

    }

    ;
    @FXML
    private void ajouter_article(ActionEvent event) {

        ArticleService articleService = ArticleService.getInstance();
        if (aj_obj.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();

        } else {

            try {

                Article ar = new Article(aj_obj.getText(),
                        aj_con.getText(),
                        Integer.parseInt(userid.getText()),
                        username.getText()
                );

                articleService.ajouterservice(ar);
                System.out.println(ar.getId());
                resetTableData();

            } catch (SQLException ex) {
                System.out.println("article Ajouté");
            }

        }

    }

    public void resetTableData() throws SQLDataException, SQLException {

        List<Article> listArticles = new ArrayList<>();
        listArticles = ca.getAllArticle();
        ObservableList<Article> data = FXCollections.observableArrayList(listArticles);
        table.setItems(data);
    }

    @FXML
    private void gobackplz(ActionEvent event) throws IOException {
        if (heyy.getText().equals("Membre")) {
            Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/EspaceEtudiant.fxml"));
            Scene scene = new Scene(page1, 1129, 819);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Bienvenue" + " " + AuthentificationController.connectedUser.getUtilisateurNom());

            stage.setScene(scene);
            stage.show();
        } else {
            Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/DashbordFormateur.fxml"));
            Scene scene = new Scene(page1, 656, 605);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Bienvenue" + " " + AuthentificationController.connectedUser.getUtilisateurNom());

            stage.setScene(scene);
            stage.show();

        }

    }

}
