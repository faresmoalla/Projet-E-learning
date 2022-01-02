/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import entities.Admin;
import entities.Entrepreneur;
import entities.Formateur;
import entities.Membre;
import entities.Utilisateur;
import services.AdminService;
import services.EntrepreneurService;
import services.FormateurService;
import services.MembreService;

/**
 * FXML Controller class
 *
 * @author lahbib
 */

public class ListeFormateurController implements Initializable {
public static Formateur connectedFormateur;
    @FXML
    private TableColumn<?, ?> zd_utilisateurID;
    @FXML
    private TableColumn<?, ?> zd_utilisateurPDP;
    @FXML
    private TableColumn<?, ?> zd_utilisateurPrenom;
    @FXML
    private TableColumn<?, ?> zd_utilisateurNom;
    @FXML
    private TableColumn<?, ?> zd_utilisateurGenre;
    @FXML
    private TableColumn<?, ?> zd_utilisateurDDN;
    @FXML
    private TableColumn<?, ?> zd_utilisateurAdresse;
    @FXML
    private TableColumn<?, ?> zd_utilisateurPays;
    @FXML
    private TableColumn<?, ?> zd_utilisateurphone;
    @FXML
    private TableColumn<?, ?> zd_utilisateurFonction;
    @FXML
    private TableColumn<?, ?> zd_utilisateurOrganisme;
    @FXML
    private TableColumn<?, ?> zd_utilisateurSavoirEtre;
    @FXML
    private TableColumn<?, ?> zd_utilisateurAdresseEmail;
    @FXML
    private TableColumn<?, ?> zd_utilisateurMDP;
    @FXML
    private TableColumn<?, ?> zd_utilisateurRole;
    @FXML
    private TableColumn<?, ?> zd_nomEntreprise;
    @FXML
    private TableColumn<?, ?> zd_EntrepreneurSiteWeb;
    @FXML
    private TableColumn<?, ?> zd_EntrepreneurUsage;
    
    @FXML
    private TableView<Formateur> zd_tableFormateur;
    @FXML
    private Button zd_retour;
    @FXML
    private Button zd_supp;
    private ObservableList<Formateur> data;
    FormateurService fs = new FormateurService();
    ObservableList<Membre> obl = FXCollections.observableArrayList();
    public ObservableList<Formateur> list;
    @FXML
    private TextField text_recherche;
    @FXML
    private Button zd_modfier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            //MembreService ms = new MembreService();
            //AdminService as = new AdminService();
            FormateurService fs = new FormateurService();
            //EntrepreneurService es = new EntrepreneurService();
            //ArrayList<Membre> m;
            ArrayList<Formateur> f;
            //ArrayList<Entrepreneur> e;
            //ArrayList<Admin> a;
            try {
                // m = (ArrayList<Membre>) ms.getAllMembre();
                //ObservableList obm = FXCollections.observableArrayList(m);
                f = (ArrayList<Formateur>) fs.getAllFormateur();
                ObservableList obf = FXCollections.observableArrayList(f);
                
                //e= (ArrayList<Entrepreneur>) es.getAllEntrepreneur();
                //ObservableList obe = FXCollections.observableArrayList(e);
                
                //a= (ArrayList<Admin>) as.getAllAdmin();
                //ObservableList oba = FXCollections.observableArrayList(a);
                zd_tableFormateur.setItems(obf);
                /*
                zd_tablemembre.setItems(obf);
                zd_tablemembre.setItems(obe);
                zd_tablemembre.setItems(oba);*/
                zd_utilisateurID.setCellValueFactory(new PropertyValueFactory<>("id"));
                zd_utilisateurPDP.setCellValueFactory(new PropertyValueFactory<>("utilisateurPDP"));
                zd_utilisateurPrenom.setCellValueFactory(new PropertyValueFactory<>("utilisateurPrenom"));
                zd_utilisateurNom.setCellValueFactory(new PropertyValueFactory<>("utilisateurNom"));
                zd_utilisateurGenre.setCellValueFactory(new PropertyValueFactory<>("utilisateurGenre"));
                zd_utilisateurDDN.setCellValueFactory(new PropertyValueFactory<>("utilisateurDDN"));
                zd_utilisateurAdresse.setCellValueFactory(new PropertyValueFactory<>("utilisateurAddress"));
                zd_utilisateurPays.setCellValueFactory(new PropertyValueFactory<>("utilisateurPays"));
                zd_utilisateurphone.setCellValueFactory(new PropertyValueFactory<>("utilisateurphone"));
                zd_utilisateurFonction.setCellValueFactory(new PropertyValueFactory<>("utilisateurFonction"));
                zd_utilisateurOrganisme.setCellValueFactory(new PropertyValueFactory<>("utilisateurOrganisme"));
                zd_utilisateurSavoirEtre.setCellValueFactory(new PropertyValueFactory<>("utilisateurSoftskills"));
                zd_utilisateurAdresseEmail.setCellValueFactory(new PropertyValueFactory<>("utilisateurAddressEmail"));
                zd_utilisateurMDP.setCellValueFactory(new PropertyValueFactory<>("utilisateurMDP"));
                zd_utilisateurRole.setCellValueFactory(new PropertyValueFactory<>("utilisateurRole"));
                zd_nomEntreprise.setCellValueFactory(new PropertyValueFactory<>("nomEntreprise"));
                zd_EntrepreneurSiteWeb.setCellValueFactory(new PropertyValueFactory<>("EntrepreneurSiteWeb"));
                zd_EntrepreneurUsage.setCellValueFactory(new PropertyValueFactory<>("EntrepreneurUsage"));
            } catch (SQLException ex) {
                Logger.getLogger(ListeFormateurController.class.getName()).log(Level.SEVERE, null, ex);
            }
            // TODO
            // TODO
            // TODO
            // TODO
            FormateurService fsa = new FormateurService();
            ArrayList<Formateur> a = new ArrayList<>();
            a = (ArrayList<Formateur>) fsa.getAllFormateur();
            ObservableList<Formateur> art1 = FXCollections.observableArrayList(a);
            zd_tableFormateur.setItems(art1);
            zd_utilisateurID.setCellValueFactory(new PropertyValueFactory<>("id"));
            zd_utilisateurAdresseEmail.setCellValueFactory(new PropertyValueFactory<>("utilisateurAddressEmail"));
            list = FXCollections.observableArrayList(
                    fs.getAllFormateur());
            FilteredList<Formateur> filteredData = new FilteredList<>(list, e -> true);
            text_recherche.setOnKeyReleased(e -> {
                text_recherche.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Formateur>) Formateur -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Formateur.getUtilisateurAddressEmail().toLowerCase().contains(lower)) {
                            return true;
                        }
                        
                        return false;
                    });
                });
                SortedList<Formateur> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(zd_tableFormateur.comparatorProperty());
                zd_tableFormateur.setItems(sortedData);
            });
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ListeFormateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }   
    @FXML
    private void supprimerFormateur(ActionEvent event) throws SQLException {
         if (event.getSource()==zd_supp) {
            Formateur m = new Formateur();
            m.setId(zd_tableFormateur.getSelectionModel().getSelectedItem().getId());
            System.out.println(zd_tableFormateur.getSelectionModel().getSelectedItem().getId());

        FormateurService es = new FormateurService();
        es.supprimerFormateur(m);
                     resetTableData();
        }
          }
    public void resetTableData() throws SQLDataException, SQLException{
    
        List<Formateur> listFormateur = new ArrayList<>();
        listFormateur = fs.getAllFormateur();
        ObservableList<Formateur> data = FXCollections.observableArrayList(listFormateur);
        zd_tableFormateur.setItems(data);
    }
    @FXML
    private void retour(ActionEvent event) throws IOException {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/view/Acceuil.fxml"));
            Scene scene = zd_retour.getScene();
            scene.setRoot(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }

    @FXML
    private void mofiierAdmin(ActionEvent event) {
    
            
        
       try {
             int id = zd_tableFormateur.getSelectionModel().getSelectedItem().getId();
        String Nom = zd_tableFormateur.getSelectionModel().getSelectedItem().getUtilisateurNom();
        String Prenom = zd_tableFormateur.getSelectionModel().getSelectedItem().getUtilisateurPrenom();
        String Pdp = zd_tableFormateur.getSelectionModel().getSelectedItem().getUtilisateurPdp();
        String genre = zd_tableFormateur.getSelectionModel().getSelectedItem().getUtilisateurGenre();
        String role = zd_tableFormateur.getSelectionModel().getSelectedItem().getUtilisateurRole();
        String address = zd_tableFormateur.getSelectionModel().getSelectedItem().getUtilisateurAddress();
        String pays = zd_tableFormateur.getSelectionModel().getSelectedItem().getUtilisateurPays();
        String email = zd_tableFormateur.getSelectionModel().getSelectedItem().getUtilisateurAddressEmail();
        java.util.Date ddn = zd_tableFormateur.getSelectionModel().getSelectedItem().getUtilisateurDDN();
        String fonction = zd_tableFormateur.getSelectionModel().getSelectedItem().getUtilisateurFonction();
        String org = zd_tableFormateur.getSelectionModel().getSelectedItem().getUtilisateurOrganisme();
        String softskills = zd_tableFormateur.getSelectionModel().getSelectedItem().getUtilisateurSoftskills();
        int phone = zd_tableFormateur.getSelectionModel().getSelectedItem().getUtilisateurphone();
        Formateur m1 = new Formateur(id, Pdp,phone, Nom, Prenom, address, pays, genre, email, role,fonction,org,softskills, ddn);
        ListeFormateurController.connectedFormateur = m1;
            Parent page2 = FXMLLoader.load(getClass().getResource("/view/AjouterAdmin.fxml"));
            Scene scene2 = zd_modfier.getScene();
            scene2.setRoot(page2);
            Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage2.setScene(scene2);
            stage2.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
