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
import DB.DB;import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author lahbib
 */
public class ModePasseOubliéController implements Initializable {

    @FXML
    private Button zd_ok;
    @FXML
    private TextField zd_numtel;

    

    public ModePasseOubliéController(TextField AcceuilEmail, TextField AcceuilPasswd) {
        this.AcceuilEmail = AcceuilEmail;
        this.AcceuilPasswd = AcceuilPasswd;
    }

    public ModePasseOubliéController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = DB.getInstance().getConnection();
    }
    Connection connexion;
    public static Utilisateur connectedUser;
    @FXML
    private TextField zd_codev;
    @FXML
    private Label zd_lcodev;
    private Button btn_AcceuilInscription;
    @FXML
    private Button btn_AcceuilConnexion;
    @FXML
    private TextField AcceuilEmail;
    private TextField AcceuilPasswd;

    private TextField zd_emailcon = AcceuilEmail;
    private TextField zd_Passwdconn = AcceuilPasswd;

    public TextField getZd_emailcon() {
        return zd_emailcon;
    }

    public TextField getZd_Passwdconn() {
        return zd_Passwdconn;
    }
    public static final String ACCOUNT_SID = "AC9330c0499ef2576ac9c88376fbc3ab6d";
    public static final String AUTH_TOKEN = "ffdcc44bae1d9f4abdb5330a5175c226";

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
        String req1 = "SELECT count(*) as rowCount1 from utilisateur WHERE utilisateurAdresseEmail LIKE '" + AcceuilEmail.getText() + "' and utilisateurphone LIKE '" + zd_numtel.getText() + "' ";
        ResultSet rp1 = stm1.executeQuery(req1);
        if (rp1.next()) {
            rowCount1 = rp1.getInt("rowcount1");
        }
        System.out.println(rowCount1);
        if (rowCount1 == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("warning !! ");
            alert.setHeaderText(null);
            alert.setContentText("email ou num de te non valide");
            alert.show();

        } else {
            String req = "SELECT * from utilisateur WHERE utilisateurAdresseEmail LIKE '" + AcceuilEmail.getText() + "' and utilisateurphone LIKE '" + zd_numtel.getText() + "' ";
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

                ModePasseOubliéController.connectedUser = p;
                AuthentificationController.connectedUser = ModePasseOubliéController.connectedUser;

                String num = "+216" + p.getUtilisateurphone();
                String codev = zd_codev.getText();

                twiliosend(ACCOUNT_SID, AUTH_TOKEN, num);
                zd_codev.setVisible(true);
                zd_lcodev.setVisible(true);
                zd_ok.setVisible(true);
            }
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

    /**
     *
     * @param ACCOUNT_SID
     * @param AUTH_TOKEN
     */
    public static void twiliosend(String ACCOUNT_SID,String AUTH_TOKEN,String num) {
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Verification verification;
        verification = Verification.creator(
                "VA43d502871f086dd1dc62cb5fccfef0b2",
                num,
                "sms")
                .create();

        System.out.println(verification.getStatus());
    }
    public static boolean twilioverif(String ACCOUNT_SID,String AUTH_TOKEN,String codev,String num){
       boolean verif = false;

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        VerificationCheck verificationCheck = VerificationCheck.creator(
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
    private void ok(ActionEvent event){
          try {
            String num = "+216" + connectedUser.getUtilisateurphone();
            String codev = zd_codev.getText();
            if (twilioverif(ACCOUNT_SID, AUTH_TOKEN, codev, num)) {
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/ModifierProfil.fxml"));
                Scene scene2 = btn_AcceuilConnexion.getScene();
                scene2.setRoot(page2);
                Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage2.setScene(scene2);
                stage2.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Bienvenue :) ");
                alert.setHeaderText(null);
                alert.setContentText("erreur de connexion");
                alert.show();
            }

        } catch (IOException ex) {
            Logger.getLogger(ModePasseOubliéController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
