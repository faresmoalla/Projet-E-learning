/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.prism.impl.Disposer.Record;
import java.sql.Date;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import entities.Admin;
import entities.Entrepreneur;
import entities.Formateur;
import entities.Membre;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.AdminService;
import services.EntrepreneurService;
import services.FormateurService;
import services.MembreService;

/**
 * FXML Controller class
 *
 * @author lahbib
 */
public class ModifierProfilController implements Initializable {

    Connection connexion;
    @FXML
    private Button zd_Modifier;
    @FXML
    private Button zd_annulerModifer;
    @FXML
    private ImageView zd_Pdp;
    @FXML
    private Label zd_Pdppath;
    @FXML
    private Label zd_Pdpnom;
    @FXML
    private Label zd_Rolel;
    @FXML
    private Label zd_LOrg;
    @FXML
    private Label zd_LFonction;
    @FXML
    private Label zd_LSoftSkills;
    @FXML
    private Label zd_Lnomeentrprise;
    @FXML
    private Label zd_LsitewebEntreprise;
    @FXML
    private Label zd_LEntrpreneurUsage;
    @FXML
    private TextField zd_nom;
    @FXML
    private TextField zd_prenom;
    @FXML
    private TextField zd_adresse;
    @FXML
    private TextField zd_pays;
    @FXML
    private ToggleGroup zd_genre;
    @FXML
    private Label zd_Email;
    @FXML
    private TextField zd_Mdp;
    @FXML
    private TextField zd_CMdp;
    @FXML
    private Label zd_numtel;
    @FXML
    private DatePicker zd_DDN;
    @FXML
    private ComboBox<String> zd_Role;
    @FXML
    private TextField zd_Org;
    @FXML
    private TextField zd_Fonction;
    @FXML
    private TextArea zd_softskills;
    @FXML
    private TextField zd_nomeentrprise;
    @FXML
    private TextField zd_sitewebEntreprise;
    @FXML
    private TextField zd_EntrpreneurUsage;
    @FXML
    private Label zd_LCmdp;
    @FXML
    private RadioButton zd_homme;
    @FXML
    private RadioButton zd_femme;
    @FXML
    private Button zd_APdp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

         // TODO
         String Membre = new String("Membre");
        String Admin = new String("Admin");
        String Formateur = new String("Formateur");
        String Entrepreneur = new String("Entrepreneur");
        if (AuthentificationController.connectedUser.getUtilisateurRole().equals(Admin)){ObservableList<String> list_ne = FXCollections.observableArrayList("Admin","Formateur", "Membre", "Entrepreneur");
                         zd_Role.setItems(list_ne);}else{
        ObservableList<String> list_ne = FXCollections.observableArrayList("Formateur", "Membre", "Entrepreneur");
        zd_Role.setItems(list_ne);}
        java.sql.Date r;
        r = new java.sql.Date(AuthentificationController.connectedUser.getUtilisateurDDN().getTime());
        LocalDate date = r.toLocalDate();
        MembreService ms = new MembreService();
        AdminService as = new AdminService();
        FormateurService fs = new FormateurService();
        EntrepreneurService es = new EntrepreneurService();
       
        zd_nom.setText(AuthentificationController.connectedUser.getUtilisateurNom());
        zd_prenom.setText(AuthentificationController.connectedUser.getUtilisateurPrenom());
        zd_adresse.setText(AuthentificationController.connectedUser.getUtilisateurAddress());
        zd_pays.setText(AuthentificationController.connectedUser.getUtilisateurPays());
        if (AuthentificationController.connectedUser.getUtilisateurGenre().equals("homme")) {
            zd_genre.selectToggle(zd_homme);
        } else if (AuthentificationController.connectedUser.getUtilisateurGenre().equals("femme")) {
            zd_genre.selectToggle(zd_femme);
        }
        zd_Email.setText(AuthentificationController.connectedUser.getUtilisateurAddressEmail());

        zd_numtel.setText(String.valueOf(AuthentificationController.connectedUser.getUtilisateurphone()));
        zd_DDN.setValue(date);
        if (AuthentificationController.connectedUser.getUtilisateurRole().equals(Membre)) {
            zd_Role.setValue(Membre);
            zd_LOrg.setVisible(true);
            zd_LFonction.setVisible(true);
            zd_Org.setVisible(true);
            zd_Fonction.setVisible(true);
            zd_LSoftSkills.setVisible(false);
            zd_softskills.setVisible(false);
            zd_Lnomeentrprise.setVisible(false);
            zd_LsitewebEntreprise.setVisible(false);
            zd_LEntrpreneurUsage.setVisible(false);
            zd_nomeentrprise.setVisible(false);
            zd_sitewebEntreprise.setVisible(false);
            zd_EntrpreneurUsage.setVisible(false);
            
        }else if (AuthentificationController.connectedUser.getUtilisateurRole().equals(Admin)) {
            zd_Role.setValue(Admin);
            zd_LOrg.setVisible(false);
            zd_LFonction.setVisible(false);
            zd_Org.setVisible(false);
            zd_Fonction.setVisible(false);
            zd_LSoftSkills.setVisible(false);
            zd_softskills.setVisible(false);
            zd_Lnomeentrprise.setVisible(false);
            zd_LsitewebEntreprise.setVisible(false);
            zd_LEntrpreneurUsage.setVisible(false);
            zd_nomeentrprise.setVisible(false);
            zd_sitewebEntreprise.setVisible(false);
            zd_EntrpreneurUsage.setVisible(false);
        } else if (AuthentificationController.connectedUser.getUtilisateurRole().equals(Formateur)) {
            zd_Role.setValue(Formateur);
            zd_LOrg.setVisible(true);
            zd_LFonction.setVisible(true);
            zd_Org.setVisible(true);
            zd_Fonction.setVisible(true);
            zd_LSoftSkills.setVisible(true);
            zd_softskills.setVisible(true);
            zd_Lnomeentrprise.setVisible(false);
            zd_LsitewebEntreprise.setVisible(false);
            zd_LEntrpreneurUsage.setVisible(false);
            zd_nomeentrprise.setVisible(false);
            zd_sitewebEntreprise.setVisible(false);
            zd_EntrpreneurUsage.setVisible(false);
        } else if (AuthentificationController.connectedUser.getUtilisateurRole().equals(Entrepreneur)) {
            zd_Role.setValue(Entrepreneur);
            zd_LOrg.setVisible(false);
            zd_LFonction.setVisible(false);
            zd_Org.setVisible(false);
            zd_Fonction.setVisible(false);
            zd_LSoftSkills.setVisible(false);
            zd_softskills.setVisible(false);
            zd_Lnomeentrprise.setVisible(true);
            zd_LsitewebEntreprise.setVisible(true);
            zd_LEntrpreneurUsage.setVisible(true);
            zd_nomeentrprise.setVisible(true);
            zd_sitewebEntreprise.setVisible(true);
            zd_EntrpreneurUsage.setVisible(true);
        }
        zd_Org.setText(AuthentificationController.connectedUser.getUtilisateurOrganisme());
        zd_Fonction.setText(AuthentificationController.connectedUser.getUtilisateurFonction());
        zd_softskills.setText(AuthentificationController.connectedUser.getUtilisateurSoftskills());
        zd_nomeentrprise.setText(AuthentificationController.connectedUser.getNomEntreprise());
        zd_sitewebEntreprise.setText(AuthentificationController.connectedUser.getEntrepreneurSiteWeb());
        zd_EntrpreneurUsage.setText(AuthentificationController.connectedUser.getEntrepreneurUsage());
        zd_Role.setOnAction(e -> {
            if (Membre.equals(zd_Role.getValue())) {

                zd_LOrg.setVisible(true);
                zd_LFonction.setVisible(true);
                zd_Org.setVisible(true);
                zd_Fonction.setVisible(true);
                zd_LSoftSkills.setVisible(false);
                zd_softskills.setVisible(false);
                zd_Lnomeentrprise.setVisible(false);
                zd_LsitewebEntreprise.setVisible(false);
                zd_LEntrpreneurUsage.setVisible(false);
                zd_nomeentrprise.setVisible(false);
                zd_sitewebEntreprise.setVisible(false);
                zd_EntrpreneurUsage.setVisible(false);
            } else if (Formateur.equals(zd_Role.getValue())) {

                zd_LOrg.setVisible(true);
                zd_LFonction.setVisible(true);
                zd_Org.setVisible(true);
                zd_Fonction.setVisible(true);
                zd_LSoftSkills.setVisible(true);
                zd_softskills.setVisible(true);
                zd_Lnomeentrprise.setVisible(false);
                zd_LsitewebEntreprise.setVisible(false);
                zd_LEntrpreneurUsage.setVisible(false);
                zd_nomeentrprise.setVisible(false);
                zd_sitewebEntreprise.setVisible(false);
                zd_EntrpreneurUsage.setVisible(false);
            } else if (Admin.equals(zd_Role.getValue())) {

                zd_LOrg.setVisible(false);
                zd_LFonction.setVisible(false);
                zd_Org.setVisible(false);
                zd_Fonction.setVisible(false);
                zd_LSoftSkills.setVisible(false);
                zd_softskills.setVisible(false);
                zd_Lnomeentrprise.setVisible(false);
                zd_LsitewebEntreprise.setVisible(false);
                zd_LEntrpreneurUsage.setVisible(false);
                zd_nomeentrprise.setVisible(false);
                zd_sitewebEntreprise.setVisible(false);
                zd_EntrpreneurUsage.setVisible(false);
            } else if (Entrepreneur.equals(zd_Role.getValue())) {
                ;
                zd_LOrg.setVisible(false);
                zd_LFonction.setVisible(false);
                zd_Org.setVisible(false);
                zd_Fonction.setVisible(false);
                zd_LSoftSkills.setVisible(false);
                zd_softskills.setVisible(false);
                zd_Lnomeentrprise.setVisible(true);
                zd_LsitewebEntreprise.setVisible(true);
                zd_LEntrpreneurUsage.setVisible(true);
                zd_nomeentrprise.setVisible(true);
                zd_sitewebEntreprise.setVisible(true);
                zd_EntrpreneurUsage.setVisible(true);
            }

        });

        zd_Modifier.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                java.util.Date date
                        = java.util.Date.from(zd_DDN.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                //ToggleGroup genre = new ToggleGroup();
                if ((zd_Mdp.getText().equals(zd_CMdp.getText()))) {
                    if (Membre.equals(zd_Role.getValue())) {
                        if (Membre.equals(zd_Role.getValue())) {
                            if (zd_nom.getText().equals("") || zd_prenom.getText().equals("")
                                    || zd_numtel.getText().equals("") || zd_adresse.getText().equals("")
                                    || zd_pays.getText().equals("") || zd_Email.getText().equals("") || zd_Mdp.getText().equals("") || zd_CMdp.getText().equals("")) {
                                Alert a = new Alert(Alert.AlertType.WARNING);
                                a.setContentText("svp remplir tout le formulaire ");
                                a.setHeaderText(null);
                                a.showAndWait();

                            } else {
                                try {
                                    Membre p = new Membre(AuthentificationController.connectedUser.getId(), AuthentificationController.connectedUser.getUtilisateurphone(), AuthentificationController.connectedUser.getUtilisateurPdp(), AuthentificationController.connectedUser.getUtilisateurNom(),
                                            AuthentificationController.connectedUser.getUtilisateurPrenom(), AuthentificationController.connectedUser.getUtilisateurAddress(), AuthentificationController.connectedUser.getUtilisateurPays(),
                                            AuthentificationController.connectedUser.getUtilisateurGenre(), AuthentificationController.connectedUser.getUtilisateurAddressEmail(),
                                            AuthentificationController.connectedUser.getUtilisateurMDP(), AuthentificationController.connectedUser.getUtilisateurRole(), AuthentificationController.connectedUser.getUtilisateurOrganisme(),
                                            AuthentificationController.connectedUser.getUtilisateurFonction(), AuthentificationController.connectedUser.getUtilisateurDDN());
                                    System.out.println(p.toString());
                                    Membre p1 = new Membre(Integer.parseInt(zd_numtel.getText()), zd_Pdppath.getText(), zd_nom.getText(), zd_prenom.getText(),
                                            zd_adresse.getText(), zd_pays.getText(), ((RadioButton) zd_genre.getSelectedToggle()).getText(), zd_Email.getText(),
                                            zd_Mdp.getText(), zd_Role.getValue(), zd_Org.getText(), zd_Fonction.getText(), sqlDate);
                                    System.out.println(p1.toString());

                                    ms.modifierMembre(p1, p);
                                    Alert a = new Alert(Alert.AlertType.WARNING);
                                    a.setContentText("votre donnée ont été bien modifier ");
                                    a.setHeaderText(null);
                                    a.showAndWait();
                                } catch (SQLException ex) {
                                    Logger.getLogger(ModifierProfilController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (NoSuchAlgorithmException ex) {
                                    Logger.getLogger(ModifierProfilController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        }
                    } else if (Formateur.equals(zd_Role.getValue())) {
                        if (zd_nom.getText().equals("") || zd_prenom.getText().equals("")
                                || zd_numtel.getText().equals("") || zd_adresse.getText().equals("")
                                || zd_pays.getText().equals("") || zd_Email.getText().equals("") || zd_Mdp.getText().equals("") || zd_CMdp.getText().equals("") ) {
                            Alert a = new Alert(Alert.AlertType.WARNING);
                            a.setContentText("svp remplir tout le formulaire ");
                            a.setHeaderText(null);
                            a.showAndWait();

                        } else {
                            try {
                                Formateur p = new Formateur(AuthentificationController.connectedUser.getId(), AuthentificationController.connectedUser.getUtilisateurphone(), AuthentificationController.connectedUser.getUtilisateurPdp(), AuthentificationController.connectedUser.getUtilisateurNom(),
                                        AuthentificationController.connectedUser.getUtilisateurPrenom(), AuthentificationController.connectedUser.getUtilisateurAddress(), AuthentificationController.connectedUser.getUtilisateurPays(),
                                        AuthentificationController.connectedUser.getUtilisateurGenre(), AuthentificationController.connectedUser.getUtilisateurAddressEmail(),
                                        AuthentificationController.connectedUser.getUtilisateurMDP(), AuthentificationController.connectedUser.getUtilisateurRole(), AuthentificationController.connectedUser.getUtilisateurOrganisme(),
                                        AuthentificationController.connectedUser.getUtilisateurFonction(), AuthentificationController.connectedUser.getUtilisateurSoftskills(), AuthentificationController.connectedUser.getUtilisateurDDN());
                                System.out.println(p.toString());
                                Formateur p1 = new Formateur(Integer.parseInt(zd_numtel.getText()), zd_Pdppath.getText(), zd_nom.getText(), zd_prenom.getText(),
                                        zd_adresse.getText(), zd_pays.getText(), ((RadioButton) zd_genre.getSelectedToggle()).getText(), zd_Email.getText(),
                                        zd_Mdp.getText(), zd_Role.getValue(), zd_Org.getText(), zd_Fonction.getText(), zd_softskills.getText(), sqlDate);
                                System.out.println(p1.toString());

                                fs.modifierFormateur(p1, p);
                                Alert a = new Alert(Alert.AlertType.WARNING);
                                a.setContentText("votre donnée ont été bien modifier ");
                                a.setHeaderText(null);
                                a.showAndWait();
                            } catch (SQLException ex) {
                                Logger.getLogger(ModifierProfilController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(ModifierProfilController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    } else if (Entrepreneur.equals(zd_Role.getValue())) {
                        if (zd_nom.getText().equals("") || zd_prenom.getText().equals("")
                                || zd_numtel.getText().equals("") || zd_adresse.getText().equals("")
                                || zd_pays.getText().equals("") || zd_Email.getText().equals("") || zd_Mdp.getText().equals("") || zd_CMdp.getText().equals("") ) {
                            Alert a = new Alert(Alert.AlertType.WARNING);
                            a.setContentText("svp remplir tout le formulaire ");
                            a.setHeaderText(null);
                            a.showAndWait();

                        } else {
                            try {
                                Entrepreneur p = new Entrepreneur(AuthentificationController.connectedUser.getId(), AuthentificationController.connectedUser.getUtilisateurphone(), AuthentificationController.connectedUser.getUtilisateurPdp(), AuthentificationController.connectedUser.getUtilisateurNom(),
                                        AuthentificationController.connectedUser.getUtilisateurPrenom(), AuthentificationController.connectedUser.getUtilisateurAddress(), AuthentificationController.connectedUser.getUtilisateurPays(),
                                        AuthentificationController.connectedUser.getUtilisateurGenre(), AuthentificationController.connectedUser.getUtilisateurAddressEmail(),
                                        AuthentificationController.connectedUser.getUtilisateurMDP(), AuthentificationController.connectedUser.getUtilisateurRole(), AuthentificationController.connectedUser.getUtilisateurOrganisme(),
                                        AuthentificationController.connectedUser.getUtilisateurFonction(), AuthentificationController.connectedUser.getNomEntreprise(), AuthentificationController.connectedUser.getEntrepreneurSiteWeb(),
                                        AuthentificationController.connectedUser.getEntrepreneurUsage(), AuthentificationController.connectedUser.getUtilisateurDDN());
                                System.out.println(p.toString());
                                Entrepreneur p1 = new Entrepreneur(Integer.parseInt(zd_numtel.getText()), zd_Pdppath.getText(), zd_nom.getText(), zd_prenom.getText(),
                                        zd_adresse.getText(), zd_pays.getText(), ((RadioButton) zd_genre.getSelectedToggle()).getText(), zd_Email.getText(),
                                        zd_Mdp.getText(), zd_Role.getValue(), zd_Org.getText(), zd_Fonction.getText(), zd_nomeentrprise.getText(), zd_sitewebEntreprise.getText(), zd_EntrpreneurUsage.getText(), sqlDate);
                                System.out.println(p1.toString());

                                es.modifierEntrepreneur(p1, p);
                                Alert a = new Alert(Alert.AlertType.WARNING);
                                a.setContentText("votre donnée ont été bien modifier ");
                                a.setHeaderText(null);
                                a.showAndWait();
                            } catch (SQLException ex) {
                                Logger.getLogger(ModifierProfilController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(ModifierProfilController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    } else if (Admin.equals(zd_Role.getValue())) {
                        
                        if (zd_nom.getText().equals("") || zd_prenom.getText().equals("")
                                || zd_numtel.getText().equals("") || zd_adresse.getText().equals("")
                                || zd_pays.getText().equals("") || zd_Email.getText().equals("") || zd_Mdp.getText().equals("") || zd_CMdp.getText().equals("") ) {
                            Alert a = new Alert(Alert.AlertType.WARNING);
                            a.setContentText("svp remplir tout le formulaire ");
                            a.setHeaderText(null);
                            a.showAndWait();

                        } else {

                            try {
                                Admin p = new Admin(AuthentificationController.connectedUser.getId(), AuthentificationController.connectedUser.getUtilisateurphone(), AuthentificationController.connectedUser.getUtilisateurPdp(), AuthentificationController.connectedUser.getUtilisateurNom(),
                                        AuthentificationController.connectedUser.getUtilisateurPrenom(), AuthentificationController.connectedUser.getUtilisateurAddress(), AuthentificationController.connectedUser.getUtilisateurPays(),
                                        AuthentificationController.connectedUser.getUtilisateurGenre(), AuthentificationController.connectedUser.getUtilisateurAddressEmail(),
                                        AuthentificationController.connectedUser.getUtilisateurMDP(), AuthentificationController.connectedUser.getUtilisateurRole(), AuthentificationController.connectedUser.getUtilisateurOrganisme(),
                                        AuthentificationController.connectedUser.getUtilisateurFonction(), AuthentificationController.connectedUser.getUtilisateurSoftskills(), AuthentificationController.connectedUser.getNomEntreprise(), AuthentificationController.connectedUser.getEntrepreneurSiteWeb(),
                                        AuthentificationController.connectedUser.getEntrepreneurUsage(), AuthentificationController.connectedUser.getUtilisateurDDN());
                                System.out.println(p.toString());
                                Admin p1 = new Admin(Integer.parseInt(zd_numtel.getText()), zd_Pdppath.getText(), zd_nom.getText(), zd_prenom.getText(),
                                        zd_adresse.getText(), zd_pays.getText(), ((RadioButton) zd_genre.getSelectedToggle()).getText(), zd_Email.getText(),
                                        zd_Mdp.getText(), zd_Role.getValue(), zd_Org.getText(), zd_Fonction.getText(), zd_softskills.getText(), zd_nomeentrprise.getText(), zd_sitewebEntreprise.getText(), zd_EntrpreneurUsage.getText(), sqlDate);
                                System.out.println(p1.toString());

                                as.modifierAdmin(p1, p);
                                Alert a = new Alert(Alert.AlertType.WARNING);
                                a.setContentText("votre donnée ont été bien modifier ");
                                a.setHeaderText(null);
                                a.showAndWait();
                            } catch (SQLException ex) {
                                Logger.getLogger(ModifierProfilController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(ModifierProfilController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    }

                } else {
                    zd_LCmdp.setText("les mot de passe doivent etre identique");
                }

            }

        });

    }

    private String hashmdp(String mdp) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    private Membre recmembre(AuthentificationController p) throws NoSuchAlgorithmException, SQLException {

        String req = "SELECT * from utilisateur WHERE utilisateurAdresseEmail LIKE '" + p.getZd_emailcon().getText() + "' and utilisateurMDP LIKE '" + hashmdp(p.getZd_Passwdconn().getText()) + "' ";
        Statement stm = connexion.createStatement();
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        Membre m = null;
        while (rst.next()) {
            Membre x = new Membre(
                    rst.getInt("id"),
                    rst.getInt("utilisateurphone"),
                    rst.getString("utilisateurPdp"),
                    rst.getString("utilisateurNom"),
                    rst.getString("utilisateurPrenom"),
                    rst.getString("utilisateurAdresse"),
                    rst.getString("utilisateurPays"),
                    rst.getString("utilisateurGenre"),
                    rst.getString("utilisateurAdresseEmail"),
                    rst.getString("utilisateurMDP"),
                    rst.getString("utilisateurRole"),
                    rst.getString("utilisateurOrganisme"),
                    rst.getString("utilisateurFonction"),
                    rst.getDate("utilisateurDDN"));
            m = x;
        }

        return m;

    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
    @FXML
    private void annuler(ActionEvent event) throws IOException {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/view/Acceuil.fxml"));
            Scene scene = zd_annulerModifer.getScene();
            scene.setRoot(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }
    @FXML
    private void UploadImageActionPerformed(ActionEvent event) throws FileNotFoundException {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            zd_Pdp.setImage(image);
            zd_Pdp.setFitWidth(200);
            zd_Pdp.setFitHeight(200);
            zd_Pdp.scaleXProperty();
            zd_Pdp.scaleYProperty();
            zd_Pdp.setSmooth(true);
            zd_Pdp.setCache(true);
            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            byte[] person_image = bos.toByteArray();

        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
        zd_Pdppath.setText(file.getAbsolutePath());
        zd_Pdpnom.setText(file.getName());
    }

}
