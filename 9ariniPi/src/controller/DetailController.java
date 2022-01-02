/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.AuthentificationController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
//import javafx.scene.control.TextFlow; 
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import entities.Article;
import entities.Comment;
import entities.Reclamation;
import services.ArticleService;
import services.CommentService;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author yahia
 */
public class DetailController implements Initializable {

    @FXML
    private TableView<Comment> table_comm;
    @FXML
    private TableColumn<?, ?> list_comm;
    @FXML
    private Button bnt_comm;
    @FXML
    private TextArea comm;
    @FXML
    private Label text_object;
    @FXML
    private Label text_contenue;
    @FXML
    private Label articleid;

    private ObservableList<Comment> dataa;
    CommentService co = new CommentService();
    @FXML
    private Label userid;
    @FXML
    private Label username;
    @FXML
    private TableColumn<?, ?> list_name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ArticleService as = ArticleService.getInstance();
        System.out.println("article : " + as.getArticle());
        Article a = as.getArticle();
     
        text_contenue.setText(a.getContent());
        text_object.setText(a.getObject());

        CommentService commentService = new CommentService();
        ArrayList<Comment> c = new ArrayList<>();

        
   ////////////////////////////////////////////
       

       try {
            c = (ArrayList<Comment>) commentService.getAllCommentForArticle(as.getArticle().getId());
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
//////////////////////////////////////
        ObservableList<Comment> com = FXCollections.observableArrayList(c);
        
    table_comm.setItems(com);
        list_comm.setCellValueFactory(new PropertyValueFactory<>("content"));
        list_name.setCellValueFactory(new PropertyValueFactory<>("userName"));

        articleid.setText(String.valueOf(as.getArticle().getId()));
        userid.setText(String.valueOf(AuthentificationController.connectedUser.getId()));
        username.setText(AuthentificationController.connectedUser.getUtilisateurNom());

    }

    private void gobackarticle(ActionEvent event) throws IOException {

        Parent page1 = FXMLLoader.load(getClass().getResource("/view/articleUI.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML

    private void ajouter_comment(ActionEvent event) {
        CommentService commentService = new CommentService();
        ArticleService as = ArticleService.getInstance();

        if (comm.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();

        } else {

            try {

                Comment co = new Comment(Integer.parseInt(articleid.getText()),
                        comm.getText(),
                        Integer.parseInt(userid.getText()),
                        username.getText());
                System.out.println(co.getArticle());

                commentService.ajouterCommentparid(co);
                resetTableData();

            } catch (SQLException ex) {
                System.out.println("comment Ajouté");
            }

        }

    }

    public void resetTableData() throws SQLDataException, SQLException {
        ArticleService as = ArticleService.getInstance();

        List<Comment> listComment = new ArrayList<>();
        listComment = co.getAllCommentForArticle(as.getArticle().getId());
        ObservableList<Comment> dataa = FXCollections.observableArrayList(listComment);
        table_comm.setItems(dataa);
    }

    @FXML
    private void bacfff(ActionEvent event) throws IOException {
   Parent page1 = FXMLLoader.load(getClass().getResource("/view/articleUI.fxml"));
        Scene scene = new Scene(page1, 1180, 734);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Gestion");
        stage.show();

    }

}
