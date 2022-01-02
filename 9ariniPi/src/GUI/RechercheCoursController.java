/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DB;
import controller.AuthentificationController;
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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author fares
 */
public class RechercheCoursController implements Initializable {

    @FXML
    private Button buttonrechfilrech;
    @FXML
    private AnchorPane FentreRechCours;
    @FXML
    private Label descriptioninrecherchee;
    @FXML
    private ImageView imginsearch;
    @FXML
    private Label labelpathimg;
    @FXML
    private Hyperlink profilllll;

    public RechercheCoursController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = DB.getInstance().getConnection();
    }
    Connection connexion;
    @FXML
    private Hyperlink ReturnAcceuilcateg5;
    @FXML
    private Hyperlink retourrrrr;
    @FXML
    private Label LableCourss;
    @FXML
    private TextField searchfisearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        profilllll.setText(AuthentificationController.connectedUser.getUtilisateurNom());

        LableCourss.setText(EspaceEtudiantController.connectedCours.getNomCours());
        descriptioninrecherchee.setText(EspaceEtudiantController.connectedCours.getDescription());
        labelpathimg.setText(EspaceEtudiantController.connectedCours.getCoursImg());

        FileChooser fileChooser = new FileChooser();
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
        File file = new File(labelpathimg.getText());
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imginsearch.setImage(image);
            imginsearch.setFitWidth(200);
            imginsearch.setFitHeight(200);
            imginsearch.scaleXProperty();
            imginsearch.scaleYProperty();
            imginsearch.setSmooth(true);
            imginsearch.setCache(true);
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
    }

    @FXML
    private void ReturnAcceuilRechCours(ActionEvent event) {
        
        
        
    }

    @FXML
    private void PrecedentinterfaceRechcours(ActionEvent event) throws IOException {
            Parent page1 = FXMLLoader.load(getClass().getResource("EspaceEtudiant.fxml"));
        Scene scene = new Scene(page1, 1129, 819);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Bienvenue"+ AuthentificationController.connectedUser.getUtilisateurNom());

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void SearchfiSearch(ActionEvent event) throws SQLException {
        String req = "SELECT * from cours WHERE nomCours LIKE '" + searchfisearch.getText() + "'";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Cours c = new Cours(rst.getInt("id"),
                    rst.getString("utilisateurNom"),
                    rst.getString("nomCours"),
                    rst.getInt("nbrChapitres"),
                    rst.getString("description"),
                    rst.getString("coursImg"),
                    rst.getInt("categorie_id"));
            EspaceEtudiantController.connectedCours = c;
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("RechercheCours.fxml"));
                Scene scene = new Scene(page1, 848, 654);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle(EspaceEtudiantController.connectedCours.getNomCours() + " " + ":Résultat de la recherche");
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
            }

        }
    }
    @FXML
    private void GotoceCours(ActionEvent event) throws IOException, Exception {
        Parent page5 = FXMLLoader.load(getClass().getResource("Cours.fxml"));
        Scene scene = new Scene(page5, 1177, 899);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        sendMail("fares.moalla1996@gmail.com");
    }

    public static void sendMail(String recipient) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "9ariniphoenix@gmail.com";
        String password = "Fares5683@";
        Session session = Session.getInstance(properties, new Authenticator() {
             @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail, password);
            }
        });
            
        Message message = prepareMessage(session, myAccountEmail, recipient);

        Transport.send(message);
        System.out.println("Message sent successfully");
    }      
    public static void sendMail2(String recipient) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "9ariniphoenix@gmail.com";
        String password = "Fares5683@";
        Session session = Session.getInstance(properties, new Authenticator() {
             @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail, password);
            }
        });        
        Message message = prepareMessage2(session, myAccountEmail, recipient);

        Transport.send(message);
        System.out.println("Message sent successfully");
    }
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Vous etes inscrit au nouveau Cours");
            message.setText("Vous etes inscrit au cours"+ "  "+EspaceEtudiantController.connectedCours.getNomCours());
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(RechercheCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   
    private static Message prepareMessage2(Session session, String myAccountEmail, String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Bravo Cours complété");
            message.setText("Bravo Vous avez finit le cours"+ "  "+EspaceEtudiantController.connectedCours.getNomCours());
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(RechercheCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @FXML
    private void logfffssfff(ActionEvent event) throws IOException {
           Parent page1 = FXMLLoader.load(getClass().getResource("Bienvenue.fxml"));
        Scene scene = new Scene(page1, 1106, 819);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Inscrivez Vous");

        stage.setScene(scene);
        stage.show(); 
        
        
    }
}
