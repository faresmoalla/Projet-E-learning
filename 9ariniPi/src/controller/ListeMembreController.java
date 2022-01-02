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

public class ListeMembreController implements Initializable {
 public static Membre connectedMembre;
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
    private TableView<Membre> zd_tablemembre;
    
    @FXML
    private Button zd_suppMembre;
    @FXML
    private Button zd_retour;
    
    private ObservableList<Membre> data;
    MembreService ms = new MembreService();
    ObservableList<Membre> obl = FXCollections.observableArrayList();
    public ObservableList<Membre> list;
    
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
            MembreService ms = new MembreService();
            //EntrepreneurService es = new EntrepreneurService();
            //ArrayList<Membre> m;
            ArrayList<Membre> m;
            //ArrayList<Entrepreneur> e;
            //ArrayList<Admin> a;
            try {
                // m = (ArrayList<Membre>) ms.getAllMembre();
                //ObservableList obm = FXCollections.observableArrayList(m);
                m = (ArrayList<Membre>) ms.getAllMembre();
                ObservableList obm = FXCollections.observableArrayList(m);

                //e= (ArrayList<Entrepreneur>) es.getAllEntrepreneur();
                //ObservableList obe = FXCollections.observableArrayList(e);
                //a= (ArrayList<Admin>) as.getAllAdmin();
                //ObservableList oba = FXCollections.observableArrayList(a);
                zd_tablemembre.setItems(obm);
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
            MembreService msa = new MembreService();
            ArrayList<Membre> a = new ArrayList<>();
            a = (ArrayList<Membre>) msa.getAllMembre();
            ObservableList<Membre> art1 = FXCollections.observableArrayList(a);
            zd_tablemembre.setItems(art1);
            zd_utilisateurID.setCellValueFactory(new PropertyValueFactory<>("id"));
            zd_utilisateurAdresseEmail.setCellValueFactory(new PropertyValueFactory<>("utilisateurAddressEmail"));
            list = FXCollections.observableArrayList(
                    ms.getAllMembre());
            FilteredList<Membre> filteredData = new FilteredList<>(list, e -> true);
            text_recherche.setOnKeyReleased(e -> {
                text_recherche.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Membre>) Membre -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Membre.getUtilisateurAddressEmail().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Membre> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(zd_tablemembre.comparatorProperty());
                zd_tablemembre.setItems(sortedData);
            });
            
        } catch (SQLException ex) {
            Logger.getLogger(ListeMembreController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }   
    @FXML
    private void supprimerMembre(ActionEvent event) throws SQLException {
         if (event.getSource()==zd_suppMembre) {
            Membre m = new Membre();
            m.setId(zd_tablemembre.getSelectionModel().getSelectedItem().getId());
            System.out.println(zd_tablemembre.getSelectionModel().getSelectedItem().getId());
;
        MembreService ms = new MembreService();
        ms.supprimerMembre(m);
                     resetTableData();
        }
          }
    public void resetTableData() throws SQLDataException, SQLException{
    
        List<Membre> listMembre = new ArrayList<>();
        listMembre = ms.getAllMembre();
        ObservableList<Membre> data = FXCollections.observableArrayList(listMembre);
        zd_tablemembre.setItems(data);
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
            int id = zd_tablemembre.getSelectionModel().getSelectedItem().getId();
        String Nom = zd_tablemembre.getSelectionModel().getSelectedItem().getUtilisateurNom();
        String Prenom = zd_tablemembre.getSelectionModel().getSelectedItem().getUtilisateurPrenom();
        String Pdp = zd_tablemembre.getSelectionModel().getSelectedItem().getUtilisateurPdp();
        String genre = zd_tablemembre.getSelectionModel().getSelectedItem().getUtilisateurGenre();
        String role = zd_tablemembre.getSelectionModel().getSelectedItem().getUtilisateurRole();
        String address = zd_tablemembre.getSelectionModel().getSelectedItem().getUtilisateurAddress();
        String pays = zd_tablemembre.getSelectionModel().getSelectedItem().getUtilisateurPays();
        String email = zd_tablemembre.getSelectionModel().getSelectedItem().getUtilisateurAddressEmail();
        java.util.Date ddn = zd_tablemembre.getSelectionModel().getSelectedItem().getUtilisateurDDN();
        String fonction = zd_tablemembre.getSelectionModel().getSelectedItem().getUtilisateurFonction();
        String org = zd_tablemembre.getSelectionModel().getSelectedItem().getUtilisateurOrganisme();
        int phone = zd_tablemembre.getSelectionModel().getSelectedItem().getUtilisateurphone();
        Membre m1 = new Membre(id, phone, Pdp, Nom, Prenom, address, pays, genre, email, role, org, fonction, ddn);
        ListeMembreController.connectedMembre = m1;
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
