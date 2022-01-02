/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.Node;
import entities.Admin;
import entities.Membre;
import entities.Utilisateur;
import DB.DB;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author lahbib
 */
public class AuthentificationController implements Initializable {

    @FXML
    private Button zd_ok;
    private TextField zd_numtel;
    @FXML
    private Hyperlink zd_mdpoublier;
    @FXML
    private Label kkkk;

    public AuthentificationController(TextField AcceuilEmail, TextField AcceuilPasswd) {
        this.AcceuilEmail = AcceuilEmail;
        this.AcceuilPasswd = AcceuilPasswd;
    }

    public AuthentificationController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = DB.getInstance().getConnection();
    }
    Connection connexion;
    public static Utilisateur connectedUser;
    @FXML
    private TextField zd_codev;
    private Label zd_lcodev;
    @FXML
    private Button btn_AcceuilInscription;
    @FXML
    private Button btn_AcceuilConnexion;
    @FXML
    private TextField AcceuilEmail;
    @FXML
    private TextField AcceuilPasswd;

    private TextField zd_emailcon = AcceuilEmail;
    private TextField zd_Passwdconn = AcceuilPasswd;

    public TextField getZd_emailcon() {
        return zd_emailcon;
    }

    public TextField getZd_Passwdconn() {
        return zd_Passwdconn;
    }
    public static final String ACCOUNT_SID = "AC04fedb666177e902b410a42d0b4614b9";
    public static final String AUTH_TOKEN = "e19969ecb5f0279d8539e03f2c414f40";

    /**
     * Initializes the controller class.
     *
     * @param
     * @param
     */
    // TODO
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void goToInscription(ActionEvent event) throws IOException {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/view/Inscription.fxml"));
            Scene scene = btn_AcceuilInscription.getScene();
            scene.setRoot(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }

    @FXML
    private void login(ActionEvent event) throws IOException, SQLException, NoSuchAlgorithmException {
        int rowCount1 = 0;
        Statement stm1 = connexion.createStatement();
        String req1 = "SELECT count(*) as rowCount1 from utilisateur WHERE utilisateurAdresseEmail LIKE '" + AcceuilEmail.getText() + "' and utilisateurMDP LIKE '" + hashmdp(AcceuilPasswd.getText()) + "' ";
        ResultSet rp1 = stm1.executeQuery(req1);
        if (rp1.next()) {
            rowCount1 = rp1.getInt("rowcount1");
        }
        System.out.println(rowCount1);
        if (rowCount1 == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("warning !! ");
            alert.setHeaderText(null);
            alert.setContentText("email ou mot de passe non valide");
            alert.show();

        } else {

            String req = "SELECT * from utilisateur WHERE utilisateurAdresseEmail LIKE '" + AcceuilEmail.getText() + "' and utilisateurMDP LIKE '" + hashmdp(AcceuilPasswd.getText()) + "' ";

            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {

                Admin p = new Admin(rst.getInt("id"),
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
                        rst.getString("utilisateurSavoirEtre"),
                        rst.getString("nomEntreprise"),
                        rst.getString("EntrepreneurSiteWeb"),
                        rst.getString("EntrepreneurUsage"),
                        rst.getDate("utilisateurDDN"));
                AuthentificationController.connectedUser = p;
                String num = "+216" + p.getUtilisateurphone();
                String codev = zd_codev.getText();
                zd_codev.setVisible(true);
                kkkk.setVisible(true);
                zd_ok.setVisible(true);
                AcceuilEmail.setVisible(false);
                zd_mdpoublier.setVisible(false);
                AcceuilPasswd.setVisible(false);
                btn_AcceuilConnexion.setVisible(false);
                //twiliosend(ACCOUNT_SID, AUTH_TOKEN,num);

            }

        }
    }

    public static void twiliosend(String ACCOUNT_SID, String AUTH_TOKEN, String num) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Verification verification;
        verification = Verification.creator(
                "VA43d502871f086dd1dc62cb5fccfef0b2",
                num,
                "sms")
                .create();

        System.out.println(verification.getStatus());
    }

    public static boolean twilioverif(String ACCOUNT_SID, String AUTH_TOKEN, String codev, String num) {
        boolean verif = false;

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        VerificationCheck verificationCheck;
        verificationCheck = VerificationCheck.creator(
                "VA43d502871f086dd1dc62cb5fccfef0b2",
                codev)
                .setTo(num).create();

        System.out.println(verificationCheck.getStatus());
        if ((verificationCheck.getStatus()).equals("approved")) {
            verif = true;
        } else {
            return false;
        }
        return verif;
    }

    @FXML
    private void ok(ActionEvent event) throws IOException {

        String num = "+216" + AuthentificationController.connectedUser.getUtilisateurphone();
        String codev = zd_codev.getText();
        
        if (AuthentificationController.connectedUser.getId() == 93) {            
            Parent page1 = FXMLLoader.load(getClass().getResource("/view/AjouterCommande.fxml"));
            Scene scene = new Scene(page1, 1059, 817);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } else if (/*twilioverif(ACCOUNT_SID, AUTH_TOKEN, codev, num) &&*/AuthentificationController.connectedUser.getUtilisateurRole().equals("Membre")) {

            Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/EspaceEtudiant.fxml"));
            Scene scene = new Scene(page1, 1129, 819);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Bienvenue" + " " + AuthentificationController.connectedUser.getUtilisateurNom());

            stage.setScene(scene);
            stage.show();
        
        } else if (AuthentificationController.connectedUser.getUtilisateurRole().equals("Formateur") /*&& (twilioverif(ACCOUNT_SID, AUTH_TOKEN, codev, num))*/) {

            Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/DashbordFormateur.fxml"));
            Scene scene = new Scene(page1, 656, 605);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Bienvenue" + " " + AuthentificationController.connectedUser.getUtilisateurNom());

            stage.setScene(scene);
            stage.show();
        
        } //else if (AuthentificationController.connectedUser.getUtilisateurRole().equals("Entrepreuneur")){
        else if (AuthentificationController.connectedUser.getUtilisateurRole().equals("Admin")/*&& (twilioverif(ACCOUNT_SID, AUTH_TOKEN, codev, num))*/) {

            Parent page1 = FXMLLoader.load(getClass().getResource("/view/Acceuil.fxml"));
            Scene scene = new Scene(page1, 834, 670);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } else if (AuthentificationController.connectedUser.getUtilisateurRole().equals("Entrepreneur")/*&& (twilioverif(ACCOUNT_SID, AUTH_TOKEN, codev, num))*/) {

            Parent page1 = FXMLLoader.load(getClass().getResource("/view/GestionPublicite.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bienvenue :) ");
            alert.setHeaderText(null);
            alert.setContentText("erreur de connexion");
            alert.show();
        }
    }

    @FXML
    private void Motdepasseoublié(ActionEvent event) throws IOException {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/view/MotdePasseOublié.fxml"));
            Scene scene2 = new Scene(page1, 600, 400);
            Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage2.setScene(scene2);
            stage2.show();
        } catch (IOException ex) {
        }
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

    @FXML
    private void reoutdansauthentifi(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/Bienvenue.fxml"));
        Scene scene = new Scene(page1, 1106, 819);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Bienvenue dans 9arini");

        stage.setScene(scene);
        stage.show();

    }
}
