/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DB;
import controller.AuthentificationController;
import entities.Categorie;
import entities.Chapitres;
import entities.Cours;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import services.Chapitresservice;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.math.BigDecimal;
import java.util.Properties;
import javafx.scene.control.ProgressBar;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URI;

/**
 * FXML Controller class
 *
 * @author fares
 */
public class CoursController implements Initializable {

    @FXML
    private TableColumn<Chapitres, String> ChapitresdansCoursEtu;
    @FXML
    private TableView<Chapitres> tableChapitresdansCours;
    @FXML
    private Hyperlink profiliio;
    @FXML
    private Label testithis;
    @FXML
    private MediaView VideoCoursss;
    @FXML
    private ImageView Imgcoruusr;
    @FXML
    private Button pause44;
    @FXML
    private Button reset44;
    @FXML
    private Button play44;
    @FXML
    private ProgressBar myProgressBar;
    @FXML
    private Label myLabel;

    public CoursController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = DB.getInstance().getConnection();
    }
    Connection connexion;
    String absolutePath;
    double progress;
    @FXML
    private Label Flistechap;
    @FXML
    private AnchorPane Fenetrecoursss;
    @FXML
    private Hyperlink acceuilducours;
    @FXML
    private Hyperlink retourducours;
    @FXML
    private Button buttonpdf;
    @FXML
    private Text Descriptionsdanscours;
    @FXML
    private Label CoursnomdansCours;
    @FXML
    private TextField searchfilcouus;
    ObservableList<Chapitres> data;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    public static Chapitres connectedCHapitre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        CoursnomdansCours.setText(EspaceEtudiantController.connectedCours.getNomCours());
        Descriptionsdanscours.setText(EspaceEtudiantController.connectedCours.getDescription());
        profiliio.setText(AuthentificationController.connectedUser.getUtilisateurNom());
        try {
            /*String req2 = "select id,nomCours,chapitreNom,videoChapitre from chapitre c , cours c1 where c.nomCours=c1.nomCours order by videoChapitre ";
             */

            String req2 = "select  * from  chapitre c Join  cours  c1 ON c.cours_id=c1.id ";
                 //   + "And  c1.nomCours LIKE'" + EspaceEtudiantController.connectedCours.getNomCours() + "'";
            Statement stm2 = connexion.createStatement();
            ResultSet rst2 = stm2.executeQuery(req2);
            while (rst2.next()) {
                Chapitres c = new Chapitres(//rst2.getInt("id"),
                        //rst2.getString("nomCours"),
                        rst2.getString("chapitreNom"),
                        rst2.getString("videoChapitre"));
                String yy = rst2.getString("videoChapitre");
                connectedCHapitre = c;
                tableChapitresdansCours.setRowFactory(tv -> {
                    TableRow<Chapitres> row = new TableRow<>();
                    row.setOnMouseClicked(event -> {
                        if (!row.isEmpty()) {
                            Chapitres rowData = row.getItem();
                            absolutePath = rowData.getVideoChapitre();
                            Chapitresservice service = new Chapitresservice();
                            testithis.setText(CoursController.connectedCHapitre.getVideoChapitre());
                            file = new File(testithis.getText());
                            media = new Media(file.toURI().toString());
                            //media = new Media(file.toURI().getPath());
                            mediaPlayer = new MediaPlayer(media);
                            VideoCoursss.setMediaPlayer(mediaPlayer);
                            mediaPlayer.play();
                            if (progress < 1) {
                                progress += 0.04;
                                myProgressBar.setProgress(progress);
                                myLabel.setText(Integer.toString((int) Math.round(progress * 100)) + "%");
                            } else {
                                try {
                                    RechercheCoursController.sendMail2("fares.moalla1996@gmail.com");
                                    String message = "Vous avez terminé le cours " + CoursnomdansCours.getText();
                                    String title = "Félicitation";
                                    TrayNotification tray = new TrayNotification();
                                    AnimationType type = AnimationType.POPUP;
                                    tray.setAnimationType(type);
                                    tray.setTitle(title);
                                    tray.setMessage(message);
                                    tray.setNotificationType(NotificationType.SUCCESS);
                                    tray.showAndDismiss(Duration.millis(3000));
                                } catch (MessagingException ex) {
                                }
                            }
                        }
                    });
                    return row;
                });

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            String req = "select  * from  chapitre c Join  cours  c1 where c.cours_id=c1.id "
                    + "And  c1.nomCours LIKE'" + EspaceEtudiantController.connectedCours.getNomCours() + "'";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                Chapitres a = new Chapitres(rst.getString("chapitreNom"));
                String xx = rst.getString("chapitreNom");
                tableChapitresdansCours.getItems().add(a);
                ChapitresdansCoursEtu.setCellValueFactory(new PropertyValueFactory<>("chapitreNom"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourAcceuilducours(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("EspaceEtudiant.fxml"));
        Scene scene = new Scene(page1, 1129, 819);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Bienvenue" + " " + AuthentificationController.connectedUser.getUtilisateurNom());
        stage.setTitle("Se connecter");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Retourducours(ActionEvent event) throws IOException {

      Parent page1 = FXMLLoader.load(getClass().getResource("RechercheCours.fxml"));
        Scene scene = new Scene(page1, 848, 654);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(EspaceEtudiantController.connectedCours.getNomCours()+": Résultat de la recherche");
        stage.setTitle("Se connecter");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onbuttonpdf(ActionEvent event) throws DocumentException, IOException {
        if (event.getSource() == buttonpdf) {
            printPDF();
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("PDF");
            tray.setMessage("PDF Evenements");
            tray.setNotificationType(NotificationType.INFORMATION);//
            tray.showAndDismiss(Duration.millis(3000));
        }
    }

    private void printPDF() throws FileNotFoundException, DocumentException, IOException {
        Document d = new Document();
        PdfWriter.getInstance(d, new FileOutputStream("C:\\Users\\fares\\Desktop\\Integration web java\\9arini-DEV\\9ariniPi\\src\\Images\\test.pdf"));
        d.open();
        d.add(new Paragraph("1ér test"));
        PdfPTable pTable = new PdfPTable(2);
        pTable.addCell("ChapitreNom");
        pTable.addCell("VideoChapitre");
        tableChapitresdansCours.getItems().forEach((t) -> {
            pTable.addCell(String.valueOf(t.getChapitreNom()));
            pTable.addCell(t.getVideoChapitre());
            try {
                d.add(pTable);
            } catch (DocumentException ex) {
                System.out.println(ex);
            }
        });
        d.close();
        System.out.println();
        Desktop.getDesktop().open(new File("C:\\Users\\fares\\Desktop\\Integration web java\\9arini-DEV\\9ariniPi\\src\\Images\\" + EspaceEtudiantController.connectedCours.getNomCours() + ".pdf"));
    }

    @FXML
    private void SearchonCours(ActionEvent event) throws SQLException {
        String req = "SELECT * from cours WHERE nomCours LIKE '" + searchfilcouus.getText() + "'";
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
    private void PauseMedia44(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    private void ResetMedia44(ActionEvent event) {
        if (mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
        }
    }

    @FXML
    private void Playvideo44(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    private void logoutcoursetudd(ActionEvent event) throws IOException {

        Parent page1 = FXMLLoader.load(getClass().getResource("Bienvenue.fxml"));
        Scene scene = new Scene(page1, 1106, 819);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Inscrivez Vous");

        stage.setScene(scene);
        stage.show();
    }
}
