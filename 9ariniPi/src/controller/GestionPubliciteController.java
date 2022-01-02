package controller;

import DB.DB;
import java.io.IOException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;

import java.sql.SQLDataException;
import java.sql.SQLException;

import java.time.ZoneId;
import java.util.ArrayList;

import java.util.List;
import java.util.Properties;

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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import entities.Cible;
import entities.Formateur;
import entities.Publicite;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import javafx.util.Duration;
import services.CibleServices;
import services.PubliciteServices;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author pc-asus
 */
public class GestionPubliciteController implements Initializable {

     public GestionPubliciteController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = DB.getInstance().getConnection();
    }
    Connection connexion;
    @FXML
    private ComboBox<String> agemin;
    @FXML
    private ComboBox<String> agemax;
    @FXML
    private Button valider;
    @FXML
    private TextField affichagePrix;
    @FXML
    private ComboBox<String> M_Genre;

    ComboBox<String> Genre = M_Genre;
    private TableColumn<Publicite, Integer> R_ID;
    @FXML
    private TableColumn<Publicite, String> R_Lien;

    @FXML
    private TableColumn<Publicite, String> R_Video;


    @FXML
    private Button R_accepterContrat;
    @FXML
    private Button R_creePubliciter;
    @FXML
    private Button R_ajouterPub;
    @FXML
    private Button R_modifPub;
    @FXML
    private TextField R_video_ajout;
    @FXML
    private TextField R_imageAjout;
    @FXML
    private TextField R_lienAjout;
    @FXML
    private AnchorPane espace_pub;
    @FXML
    private AnchorPane espace_creation;
    @FXML
    private AnchorPane espace_stat;
    @FXML
    private TableView<Publicite> table;
    @FXML
    private TableColumn<Publicite, String> R_image;
    @FXML
    private TableColumn<Publicite, Integer> R_Nclick;
    @FXML
    private TableColumn<Publicite, Integer> R_Naffichage;
    @FXML
    private Button supp;
    public ObservableList<Publicite> list;
    @FXML
    private TextField bar_recherche;
    @FXML
    private DatePicker date_debut_cible;
    @FXML
    private DatePicker date_fin_cible;
    @FXML
    private Button modifier_cible;
    
    public static Publicite connectedPub;
    CibleServices cc = new CibleServices();
    @FXML
    private BarChart<String, Integer> stat_nbr_click;
//karray.gargouri@esprit.tn
    @FXML
    private TableColumn<Publicite, Integer> R_idCible;
    @FXML
    private TableColumn<Publicite, Integer> Id_pub;
    @FXML
    private ComboBox<String> ffa;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   try {
            String req2 = "select * from cible ";
            Statement stm2 = connexion.createStatement();
            ResultSet rst2 = stm2.executeQuery(req2);
            while (rst2.next()) {
              
                        
                         String xx = rst2.getString("id");
                ffa.getItems().add(xx);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
        
        XYChart.Series<String, Integer> series1 = new XYChart.Series();
        series1.setName("Nombre de click");
        series1.getData().add(new XYChart.Data("Pub1", 20));
        series1.getData().add(new XYChart.Data("Pub2", 15));
        series1.getData().add(new XYChart.Data("Pub3", 30));
        series1.getData().add(new XYChart.Data("Pub4", 1));
        series1.getData().add(new XYChart.Data("Pub5", 50));
        XYChart.Series<String, Integer> series2 = new XYChart.Series();
        series2.setName("Nombre de affichage");
        series2.getData().add(new XYChart.Data("Pub1", 15));
        series2.getData().add(new XYChart.Data("Pub2", 1));
        series2.getData().add(new XYChart.Data("Pub3", 3));
        series2.getData().add(new XYChart.Data("Pub4", 1));
        series2.getData().add(new XYChart.Data("Pub5", 5));
        stat_nbr_click.getData().addAll(series1, series2);

        CibleServices cc = new CibleServices();
        try {
            if (cc.testCible() > 0) {
                espace_pub.setVisible(true);
                modifier_cible.setVisible(true);
                M_Genre.setVisible(false);
                agemin.setVisible(false);
                agemax.setVisible(false);
                date_debut_cible.setVisible(false);
                date_fin_cible.setVisible(false);
                valider.setVisible(false);
                affichagePrix.setVisible(false);
                R_accepterContrat.setVisible(false);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionPubliciteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<String> list_ne1 = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", " 12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", " 27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50");
        agemax.setItems(list_ne1);
        ObservableList<String> list_ne2 = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", " 12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", " 27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50");
        agemin.setItems(list_ne2);
        ObservableList<String> list_ne3 = FXCollections.observableArrayList("Homme", "Femme", "Tous");
        M_Genre.setItems(list_ne3);
        Publicite p = new Publicite();

        PubliciteServices productService = new PubliciteServices();

        ArrayList<Publicite> a = new ArrayList<>();
        try {
            a = (ArrayList<Publicite>) productService.affichertab();
        } catch (SQLException ex) {
            Logger.getLogger(GestionPubliciteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Publicite> obs = FXCollections.observableArrayList(a);
        table.setItems(obs);
        R_Lien.setCellValueFactory(new PropertyValueFactory<>("lien_publicite"));
        R_image.setCellValueFactory(new PropertyValueFactory<>("image_publicite"));
        R_Video.setCellValueFactory(new PropertyValueFactory<>("video_publicite"));
       
        R_Nclick.setCellValueFactory(new PropertyValueFactory<>("nbr_click"));
        R_Naffichage.setCellValueFactory(new PropertyValueFactory<>("nbr_affichage"));
        
        Id_pub.setCellValueFactory(new PropertyValueFactory<>("id"));

        try {
            list = FXCollections.observableArrayList(
                    productService.affichertab()
            );
            FilteredList<Publicite> filteredData = new FilteredList<>(list, e -> true);
            bar_recherche.setOnKeyReleased(e -> {
                bar_recherche.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Publicite>) Publicites -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Publicites.getLien_publicite().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Publicite> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(table.comparatorProperty());
                table.setItems(sortedData);
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        
        //PubliciteServices productService = new PubliciteServices();

    }

    public static void sendMail(String recipient) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "kacemradhwen@gmail.com";
        String password = "20923939";

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

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("My First Email from Java App");
            message.setText("Hey There, \n Look my email!");
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(GestionPubliciteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @FXML
    private void validerGenre(ActionEvent event) throws SQLException {
        java.util.Date date1
                = java.util.Date.from(date_debut_cible.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
        java.util.Date date2
                = java.util.Date.from(date_fin_cible.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
        Cible c = new Cible(this.M_Genre.getSelectionModel().getSelectedItem(), this.agemin.getSelectionModel().getSelectedIndex(), this.agemax.getSelectionModel().getSelectedIndex(), sqlDate1, sqlDate2);
        CibleServices pc = new CibleServices();

        if (pc.testCible() == 0) {
            valider.setOnAction(e -> {
                affichagePrix.setText("Le prix est " + pc.AjoutPrix(c) + " dt");
            });

        } else {
            int n = pc.AjoutPrix(c);
            int m = pc.ModifierCible(n);
            if (m < 0) {
                valider.setOnAction(e -> {
                    affichagePrix.setText("Le prix est " + 0 + " dt");
                });
            } else {

                valider.setOnAction(e -> {
                    affichagePrix.setText("Le prix est " + m + " dt");
                });
            }

        }

    }

    @FXML
    private void RA_accepterContra(ActionEvent event) throws SQLException {
        java.util.Date date1
                = java.util.Date.from(this.date_debut_cible.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
        java.util.Date date2
                = java.util.Date.from(this.date_fin_cible.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
        Cible c = new Cible(this.M_Genre.getSelectionModel().getSelectedItem(), this.agemin.getSelectionModel().getSelectedIndex(), this.agemax.getSelectionModel().getSelectedIndex(), sqlDate1, sqlDate2);
        CibleServices pc = new CibleServices();
        pc.ajouterCible(c);
        espace_pub.setVisible(true);
        modifier_cible.setVisible(true);
        M_Genre.setVisible(false);
        agemin.setVisible(false);
        agemax.setVisible(false);
        date_debut_cible.setVisible(false);
        date_fin_cible.setVisible(false);
        valider.setVisible(false);
        affichagePrix.setVisible(false);
        R_accepterContrat.setVisible(false);
        
        
        String message = "Cible ajouté";
        String title = "Ajouté";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));

    }

    @FXML
    private void RA_creePubliciter(ActionEvent event) {
        espace_creation.setVisible(true);

    }

    @FXML
    private void RA_ajouterPub(ActionEvent event) throws SQLException {

        espace_stat.setVisible(true);

        String video = R_video_ajout.getText();
  /*      java.util.Date date1
                = java.util.Date.from(date_debut_cible.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
        java.util.Date date2
                = java.util.Date.from(date_fin_cible.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
*/
        String lien = R_lienAjout.getText();
        String image = R_imageAjout.getText();

        try {

            PubliciteServices productService;
            productService = new PubliciteServices();
           // Cible c = new Cible(this.M_Genre.getSelectionModel().getSelectedItem(), this.agemin.getSelectionModel().getSelectedIndex(), this.agemax.getSelectionModel().getSelectedIndex()/*, sqlDate1, sqlDate2*/);
            Publicite p = new Publicite( Integer.parseInt(ffa.getValue()),video, lien, image);

            productService.ajouterPublicite(p);
            espace_stat.setVisible(true);
            resetTableData();

            String message = "Publicité ajouté";
            String title = "Ajouté";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

        } catch (SQLException ex) {
            System.out.println("Publicite ne lance pas" + ex.getMessage());

        }

    }

    @FXML
    private void RA_modifPub(ActionEvent event) {
        

        String v = table.getSelectionModel().getSelectedItem().getVideo_publicite();
       
        String l = table.getSelectionModel().getSelectedItem().getLien_publicite();
        String i = table.getSelectionModel().getSelectedItem().getImage_publicite();
        Publicite p1 = new Publicite(v, l, i);
        GestionPubliciteController.connectedPub = p1;

        try {
            Parent page = FXMLLoader.load(getClass().getResource("/view/modifierPublicite.fxml"));
            Scene scene = new Scene(page, 300, 500);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            String message = "Publicité Modifié ";
            String title = "Modifié avec succées";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

        } catch (IOException IOex) {
            System.out.println("Une erreur s'est produite. " + IOex.getMessage());
        }

    }

    public void resetTableData() throws SQLDataException, SQLException {
        PubliciteServices cs = new PubliciteServices();

        List<Publicite> listCategorie = new ArrayList<>();
        listCategorie = cs.affichertab();
        ObservableList<Publicite> data = FXCollections.observableArrayList(listCategorie);
        table.setItems(data);
    }

    @FXML
    private void SupprimerPub(ActionEvent event) {

        if (event.getSource() == supp) {
            Publicite pub = new Publicite();
            pub.setLien_publicite(table.getSelectionModel().getSelectedItem().getLien_publicite());
            System.out.println(table.getSelectionModel().getSelectedItem().getLien_publicite());

            PubliciteServices publiciteServices = new PubliciteServices();
            try {
                publiciteServices.supprimerPublicite(pub);
                resetTableData();
                String message = "publicité supprimé ";
                String title = "Supprimé";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));

            } catch (SQLException ex) {
            }
        }
    }

    @FXML
    private void RA_modifCible(ActionEvent event) {
        M_Genre.setVisible(true);
        agemin.setVisible(true);
        agemax.setVisible(true);
        date_debut_cible.setVisible(true);
        date_fin_cible.setVisible(true);
        valider.setVisible(true);
        affichagePrix.setVisible(true);
        R_accepterContrat.setVisible(true);
        modifier_cible.setVisible(false);
    }

}

