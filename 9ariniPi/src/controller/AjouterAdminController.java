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
import entities.Utilisateur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.AdminService;
import services.EntrepreneurService;
import services.FormateurService;
import services.MembreService;

/**
 * FXML Controller class
 *
 * @author lahbib
 */
public class AjouterAdminController implements Initializable {

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
    MembreService ms = new MembreService();
    AdminService as = new AdminService();
    FormateurService fs = new FormateurService();
    EntrepreneurService es = new EntrepreneurService();
    
    public Membre m = new Membre();
    public Admin a = new Admin();
    public Formateur f = new Formateur();
    public Entrepreneur e = new Entrepreneur();
    public static Utilisateur u;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ObservableList<String> list_ne = FXCollections.observableArrayList("Formateur", "Admin", "Membre", "Entrepreneur");
        zd_Role.setItems(list_ne);

        /*
        MembreService ms = new MembreService();
        AdminService as = new AdminService();
        FormateurService fs = new FormateurService();
        EntrepreneurService es = new EntrepreneurService();
        public static Membre m=new Membre();
        public static Admin a =new Admin();
        public static Formateur f =new Formateur();
        public static Entrepreneur e = new Entrepreneur();*/
        String Membre = new String("Membre");
        String Admin = new String("Admin");
        String Formateur = new String("Formateur");
        String Entrepreneur = new String("Entrepreneur");
        while (ListeMembreController.connectedMembre != null) {
            java.sql.Date r;
            r = new java.sql.Date(ListeMembreController.connectedMembre.getUtilisateurDDN().getTime());
            LocalDate date = r.toLocalDate();
            zd_nom.setText(ListeMembreController.connectedMembre.getUtilisateurNom());
            zd_prenom.setText(ListeMembreController.connectedMembre.getUtilisateurPrenom());
            zd_adresse.setText(ListeMembreController.connectedMembre.getUtilisateurAddress());
            zd_pays.setText(ListeMembreController.connectedMembre.getUtilisateurPays());
            if (ListeMembreController.connectedMembre.getUtilisateurGenre().equals("homme")) {
                zd_genre.selectToggle(zd_homme);
            } else if (ListeMembreController.connectedMembre.getUtilisateurGenre().equals("femme")) {
                zd_genre.selectToggle(zd_femme);
            }
            zd_Email.setText(ListeMembreController.connectedMembre.getUtilisateurAddressEmail());

            zd_numtel.setText(String.valueOf(ListeMembreController.connectedMembre.getUtilisateurphone()));
            zd_DDN.setValue(date);
            zd_Org.setText(ListeMembreController.connectedMembre.getUtilisateurOrganisme());
            zd_Fonction.setText(ListeMembreController.connectedMembre.getUtilisateurFonction());
            zd_Role.setValue(ListeMembreController.connectedMembre.getUtilisateurRole());

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
            u = ListeMembreController.connectedMembre;
            System.out.println("controller.AjouterAdminController.m" + u.toString());
            ListeMembreController.connectedMembre = null;
        }
        while (ListeAdminController.connectedAdmin != null) {
            java.sql.Date r;
            r = new java.sql.Date(ListeAdminController.connectedAdmin.getUtilisateurDDN().getTime());
            LocalDate date = r.toLocalDate();
            zd_nom.setText(ListeAdminController.connectedAdmin.getUtilisateurNom());
            zd_prenom.setText(ListeAdminController.connectedAdmin.getUtilisateurPrenom());
            zd_adresse.setText(ListeAdminController.connectedAdmin.getUtilisateurAddress());
            zd_pays.setText(ListeAdminController.connectedAdmin.getUtilisateurPays());
            if (ListeAdminController.connectedAdmin.getUtilisateurGenre().equals("homme")) {
                zd_genre.selectToggle(zd_homme);
            } else if (ListeAdminController.connectedAdmin.getUtilisateurGenre().equals("femme")) {
                zd_genre.selectToggle(zd_femme);
            }
            zd_Email.setText(ListeAdminController.connectedAdmin.getUtilisateurAddressEmail());

            zd_numtel.setText(String.valueOf(ListeAdminController.connectedAdmin.getUtilisateurphone()));
            zd_DDN.setValue(date);
            zd_Role.setValue(ListeAdminController.connectedAdmin.getUtilisateurRole());
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
            u = ListeAdminController.connectedAdmin;
            ListeAdminController.connectedAdmin = null;
        }
        while (ListeFormateurController.connectedFormateur != null) {
            java.sql.Date r;
            r = new java.sql.Date(ListeFormateurController.connectedFormateur.getUtilisateurDDN().getTime());
            LocalDate date = r.toLocalDate();
            zd_nom.setText(ListeFormateurController.connectedFormateur.getUtilisateurNom());
            zd_prenom.setText(ListeFormateurController.connectedFormateur.getUtilisateurPrenom());
            zd_adresse.setText(ListeFormateurController.connectedFormateur.getUtilisateurAddress());
            zd_pays.setText(ListeFormateurController.connectedFormateur.getUtilisateurPays());
            if (ListeFormateurController.connectedFormateur.getUtilisateurGenre().equals("homme")) {
                zd_genre.selectToggle(zd_homme);
            } else if (ListeFormateurController.connectedFormateur.getUtilisateurGenre().equals("femme")) {
                zd_genre.selectToggle(zd_femme);
            }
            zd_Email.setText(ListeFormateurController.connectedFormateur.getUtilisateurAddressEmail());

            zd_numtel.setText(String.valueOf(ListeFormateurController.connectedFormateur.getUtilisateurphone()));
            zd_DDN.setValue(date);
            zd_Org.setText(ListeFormateurController.connectedFormateur.getUtilisateurOrganisme());
            zd_Fonction.setText(ListeFormateurController.connectedFormateur.getUtilisateurFonction());
            zd_softskills.setText(ListeFormateurController.connectedFormateur.getUtilisateurSoftskills());
            zd_Role.setValue(ListeFormateurController.connectedFormateur.getUtilisateurRole());
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
            u = ListeFormateurController.connectedFormateur;
            ListeFormateurController.connectedFormateur = null;
        }
        while (ListeEntrepreneurController.connectedEntrepreneur != null) {
            java.sql.Date r;
            r = new java.sql.Date(ListeEntrepreneurController.connectedEntrepreneur.getUtilisateurDDN().getTime());
            LocalDate date = r.toLocalDate();
            zd_nom.setText(ListeEntrepreneurController.connectedEntrepreneur.getUtilisateurNom());
            zd_prenom.setText(ListeEntrepreneurController.connectedEntrepreneur.getUtilisateurPrenom());
            zd_adresse.setText(ListeEntrepreneurController.connectedEntrepreneur.getUtilisateurAddress());
            zd_pays.setText(ListeEntrepreneurController.connectedEntrepreneur.getUtilisateurPays());
            if (ListeEntrepreneurController.connectedEntrepreneur.getUtilisateurGenre().equals("homme")) {
                zd_genre.selectToggle(zd_homme);
            } else if (ListeEntrepreneurController.connectedEntrepreneur.getUtilisateurGenre().equals("femme")) {
                zd_genre.selectToggle(zd_femme);
            }
            zd_Email.setText(ListeEntrepreneurController.connectedEntrepreneur.getUtilisateurAddressEmail());

            zd_numtel.setText(String.valueOf(ListeEntrepreneurController.connectedEntrepreneur.getUtilisateurphone()));
            zd_DDN.setValue(date);
            zd_nomeentrprise.setText(ListeEntrepreneurController.connectedEntrepreneur.getNomEntreprise());
            zd_sitewebEntreprise.setText(ListeEntrepreneurController.connectedEntrepreneur.getEntrepreneurSiteWeb());
            zd_EntrpreneurUsage.setText(ListeEntrepreneurController.connectedEntrepreneur.getEntrepreneurUsage());
            zd_Role.setValue(ListeEntrepreneurController.connectedEntrepreneur.getUtilisateurRole());
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
            u = ListeEntrepreneurController.connectedEntrepreneur;
            ListeEntrepreneurController.connectedEntrepreneur = null;
        }
        zd_Role.setOnAction(e -> {
            if (Membre.equals(zd_Role.getValue())) {
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
            } else if (Formateur.equals(zd_Role.getValue())) {
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
            } else if (Admin.equals(zd_Role.getValue())) {
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
            } else if (Entrepreneur.equals(zd_Role.getValue())) {
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

        });

        zd_Modifier.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                java.util.Date date
                        = java.util.Date.from(zd_DDN.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                //ToggleGroup genre = new ToggleGroup();
               
                    if (zd_nom.getText().equals("") || zd_prenom.getText().equals("")
                            || zd_numtel.getText().equals("") || zd_adresse.getText().equals("")
                            || zd_pays.getText().equals("") || zd_Email.getText().equals("")) {
                        Alert a = new Alert(Alert.AlertType.WARNING);
                        a.setContentText("svp remplir tous le formulaire  ");
                        a.setHeaderText(null);
                        a.showAndWait();

                    } else {
                        if (zd_Role.getValue().equals(Admin)) {
                            
                           // if (zd_Mdp.getText().equals(null)) {
                           
                                try {
                                    Admin p1 = new Admin(zd_Pdppath.getText(),Integer.parseInt(zd_numtel.getText()), zd_nom.getText(), zd_prenom.getText(),
                                            zd_adresse.getText(), zd_pays.getText(), ((RadioButton) zd_genre.getSelectedToggle()).getText(), zd_Email.getText(),
                                            zd_Role.getValue(), zd_Org.getText(), zd_Fonction.getText(), zd_softskills.getText(), zd_nomeentrprise.getText(), zd_sitewebEntreprise.getText(), zd_EntrpreneurUsage.getText(), sqlDate);
                                    System.out.println(p1.toString());
                                    System.out.println(".handle()"+u.toString());
                                    System.out.println(".handle()"+m.toString());
                                        if(u.getUtilisateurRole().equals(Membre))
                                    as.modifierAdminFormateur(p1, u);
                                        if(u.getUtilisateurRole().equals(Formateur))
                                    as.modifierAdminEntrepreneur(p1, u);
                                        if(u.getUtilisateurRole().equals(Entrepreneur))
                                    as.modifierAdminMembre(p1, u);
                                        
                                    System.out.println(".handle()"+m.toString());
                                    
                                   
                                    Alert a = new Alert(Alert.AlertType.WARNING);
                                    a.setContentText("votre donnée ont été bien modifier ");
                                    a.setHeaderText(null);
                                    a.showAndWait();
                                } catch (SQLException ex) {
                                    Logger.getLogger(AjouterAdminController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (NoSuchAlgorithmException ex) {
                                    Logger.getLogger(AjouterAdminController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                        }
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
    private void retour(ActionEvent event) throws IOException {
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

}
