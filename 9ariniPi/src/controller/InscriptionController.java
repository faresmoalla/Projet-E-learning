/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mysql.jdbc.log.Log;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import static controller.AuthentificationController.ACCOUNT_SID;
import static controller.AuthentificationController.AUTH_TOKEN;
import static controller.AuthentificationController.connectedUser;
import static controller.AuthentificationController.twiliosend;
import static controller.AuthentificationController.twilioverif;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import sun.misc.IOUtils;
import sun.net.www.http.HttpClient;
import static sun.security.krb5.Confounder.bytes;
import entities.Admin;
import entities.Entrepreneur;
import entities.Formateur;
import entities.Membre;
import java.net.HttpURLConnection;
import javafx.scene.control.Hyperlink;
import services.AdminService;
import services.EntrepreneurService;
import services.FormateurService;
import services.MembreService;

/**
 * FXML Controller class
 *
 * @author lahbib
 */
public class InscriptionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection connexion;
    private Button zd_upload;
    @FXML
    private Button zd_CInscription;
    @FXML
    private Button zd_annulerInscription;
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
    private TextField zd_Email;
    @FXML
    private TextField zd_Mdp;
    @FXML
    private TextField zd_CMdp;
    @FXML
    private TextField zd_numtel;
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
    @FXML
    private TextField zd_codev;
    @FXML
    private Label zd_lcodev;
    @FXML
    private Button zd_ok;
    public static final String ACCOUNT_SID = "AC04fedb666177e902b410a42d0b4614b9";
    public static final String AUTH_TOKEN = "e19969ecb5f0279d8539e03f2c414f40";
    @FXML
    private Hyperlink ffee;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ActionEvent event = null;

        ObservableList<String> list_ne = FXCollections.observableArrayList("Formateur", "Membre", "Entrepreneur");
        zd_Role.setItems(list_ne);
        zd_DDN.setValue(LocalDate.now());
        zd_Role.setValue("");
        MembreService ms = new MembreService();
        AdminService as = new AdminService();
        FormateurService fs = new FormateurService();
        EntrepreneurService es = new EntrepreneurService();
        String Membre = new String("Membre");
        String Admin = new String("Admin");
        String Formateur = new String("Formateur");
        String Entrepreneur = new String("Entrepreneur");

        zd_Role.setOnAction(e -> {
            if (Membre.equals(zd_Role.getValue())) {
                zd_Rolel.setText("completer le formulaire pour terminer l'inscription en tant que membre :) ");
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
                zd_Rolel.setText("completer le formulaire pour terminer l'inscription en tant que Formateur :) ");
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
                zd_EntrpreneurUsage.setVisible(false);/*
            } else if (Admin.equals(zd_Role.getValue())) {
                zd_Rolel.setText("completer le formulaire pour terminer l'inscription en tant que Admin :) ");
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
                zd_EntrpreneurUsage.setVisible(false);*/
            } else if (Entrepreneur.equals(zd_Role.getValue())) {
                zd_Rolel.setText("completer le formulaire pour terminer l'inscription en tant que Enrepreneur :) ");
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

        zd_CInscription.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {

                    Boolean verifMembre = verifMembre();
                    Boolean verifFormateur = verifFormateur();
                    Boolean verifEntrepreneur = verifEntrepreneur();
                    Boolean verifAdmin = verifAdmin();

                    //ToggleGroup genre = new ToggleGroup();
                    if ((zd_DDN.getValue()).equals(LocalDate.now())) {
                        Alert a1 = new Alert(Alert.AlertType.WARNING);
                        a1.setContentText("svp entrer la date de naissance ");
                        a1.setHeaderText(null);
                        a1.showAndWait();
                    } else {
                        if (zd_Email.getText().endsWith("@gmail.com") || zd_Email.getText().endsWith("@esprit.tn")) {
                            if ((zd_Mdp.getText().equals(zd_CMdp.getText()))) {
                                if (verifAdmin == false || verifEntrepreneur == false || verifFormateur == false || verifMembre == false) {
                                    Alert alert = new Alert(AlertType.CONFIRMATION);
                                    alert.setTitle("Bienvenue :) ");
                                    alert.setHeaderText(null);
                                    alert.setContentText("ce email existe");
                                    alert.show();
                                } else {
                                    if (zd_nom.getText().equals("") || zd_prenom.getText().equals("")
                                            || zd_numtel.getText().equals("") || zd_adresse.getText().equals("")
                                            || zd_pays.getText().equals("") || zd_Email.getText().equals("") || zd_Mdp.getText().equals("") || zd_CMdp.getText().equals("")
                                            || !isNotselected(zd_homme, zd_femme) || (zd_Role.getValue()).equals("")) {
                                        Alert a = new Alert(Alert.AlertType.WARNING);
                                        a.setContentText("svp remplir tout le formulaire ");
                                        a.setHeaderText(null);
                                        a.showAndWait();

                                    } else {
                                        if (isInt(zd_numtel)) {
                                            zd_codev.setVisible(true);
                                            zd_lcodev.setVisible(true);
                                            zd_ok.setVisible(true);
                                            String num = "+216" + zd_numtel.getText();
                                            String codev = zd_codev.getText();
                                            twiliosend(ACCOUNT_SID, AUTH_TOKEN, num);
                                            

                                            // }
                                        } else {
                                            System.out.println("put a valid number");
                                            Alert alert = new Alert(AlertType.CONFIRMATION);
                                            alert.setTitle("warning !! ");
                                            alert.setHeaderText(null);
                                            alert.setContentText("entrer un numero de telephone valide");
                                            alert.show();
                                        }

                                    }

                                }
                            } else {
                                zd_LCmdp.setText("les mot de passe doivent etre identique");
                            }
                        } else {
                            Alert a = new Alert(Alert.AlertType.WARNING);
                            a.setContentText("l'email doit etre sous la forme de ******@gmail.com ou ****@esprit.tn ");
                            a.setHeaderText(null);
                            a.showAndWait();
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

   
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

    @FXML
    private void annulerInscription(ActionEvent event) throws IOException {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/view/Authentification.fxml"));
            Scene scene = zd_annulerInscription.getScene();
            scene.setRoot(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }

    private boolean verifMembre() throws IOException, SQLException, NoSuchAlgorithmException {
        MembreService ms = new MembreService();
        String role = zd_Email.getText();
        boolean verifMail = true;
        for (int i = 0; i < ms.getAllMembre().size(); i++) {
            if (role.equals(ms.getAllMembre().get(i).getUtilisateurAddressEmail())) {
                verifMail = false;
            }

        }
        return verifMail;

    }

    private boolean verifAdmin() throws IOException, SQLException, NoSuchAlgorithmException {
        AdminService ms = new AdminService();
        String role = zd_Email.getText();
        boolean verifMail = true;
        for (int i = 0; i < ms.getAllAdmin().size(); i++) {
            if (role.equals(ms.getAllAdmin().get(i).getUtilisateurAddressEmail())) {
                verifMail = false;
            }

        }
        return verifMail;

    }

    private boolean verifEntrepreneur() throws IOException, SQLException, NoSuchAlgorithmException {
        EntrepreneurService ms = new EntrepreneurService();
        String role = zd_Email.getText();
        boolean verifMail = true;
        for (int i = 0; i < ms.getAllEntrepreneur().size(); i++) {
            if (role.equals(ms.getAllEntrepreneur().get(i).getUtilisateurAddressEmail())) {
                verifMail = false;
            }

        }
        return verifMail;

    }

    private boolean verifFormateur() throws IOException, SQLException, NoSuchAlgorithmException {
        FormateurService ms = new FormateurService();
        String role = zd_Email.getText();
        boolean verifMail = true;
        for (int i = 0; i < ms.getAllFormateur().size(); i++) {
            if (role.equals(ms.getAllFormateur().get(i).getUtilisateurAddressEmail())) {
                verifMail = false;
            }

        }
        return verifMail;

    }

    private boolean isInt(TextField f) {
        try {
            Integer.parseInt(f.getText());
            return true;
        } catch (NumberFormatException e) {

            return false;
        }
    }

    private boolean isNotselected(RadioButton a, RadioButton b) {
        boolean verif = false;
        if (a.isSelected() || b.isSelected()) {
            verif = true;
        }
        return verif;

    }

    private boolean isNotPressed(DatePicker a, java.sql.Date sqlDate) {
        boolean verif = true;
        if (a.getValue().equals(sqlDate)) {
            verif = false;
        }
        return verif;
    }

    public void uploadFile(String sourceFileUri) throws MalformedURLException, IOException {

        FileInputStream fileInputStream = null;
        try {
            String fileName = sourceFileUri;
            String uploadPath;
            uploadPath = "http://localhost/";
            HttpURLConnection conn = null;
            DataOutputStream dos = null;
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1 * 1024 * 1024;
            File sourceFile = new File(sourceFileUri);
            // open a URL connection to the Servlet
            fileInputStream = new FileInputStream(sourceFile);
            URL url = new URL(uploadPath);
            // Open a HTTP  connection to  the URL
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // Allow Inputs
            conn.setDoOutput(true); // Allow Outputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("uploaded_file", fileName);
            dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            // create a buffer of  maximum size
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];
            // read file and write it into form...
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {

                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            }
            // send multipart form data necesssary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            // Responses from the server (code and message)
            int serverResponseCode = conn.getResponseCode();
            String serverResponseMessage = conn.getResponseMessage();
            //close the streams //
            fileInputStream.close();
            dos.flush();
            dos.close();
        } // End else block
        catch (FileNotFoundException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param ACCOUNT_SID
     * @param AUTH_TOKEN
     */
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
    private void ok(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        String num = "+216" + zd_numtel.getText();
        String codev = zd_codev.getText();
        if (twilioverif(ACCOUNT_SID, AUTH_TOKEN, codev, num)) {

            java.util.Date date
                    = java.util.Date.from(zd_DDN.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            MembreService ms = new MembreService();
            AdminService as = new AdminService();
            FormateurService fs = new FormateurService();
            EntrepreneurService es = new EntrepreneurService();
            String Membre = new String("Membre");
            String Admin = new String("Admin");
            String Formateur = new String("Formateur");
            String Entrepreneur = new String("Entrepreneur");

            if (Membre.equals(zd_Role.getValue())) {
                ms.ajouterMembre(new Membre(Integer.parseInt(zd_numtel.getText()), zd_Pdppath.getText(), zd_nom.getText(), zd_prenom.getText(),
                        zd_adresse.getText(), zd_pays.getText(), ((RadioButton) zd_genre.getSelectedToggle()).getText(), zd_Email.getText(),
                        zd_Mdp.getText(), zd_Role.getValue(), zd_Org.getText(), zd_Fonction.getText(), sqlDate));
                /*
                        } else if (Admin.equals(zd_Role.getValue())) {
                        try {
                        as.ajouterAdmin(new Admin(Integer.parseInt(zd_numtel.getText()), zd_Pdppath.getText(), zd_nom.getText(), zd_prenom.getText(),
                        zd_adresse.getText(), zd_pays.getText(), ((RadioButton) zd_genre.getSelectedToggle()).getText(), zd_Email.getText(),
                        zd_Mdp.getText(), zd_Role.getValue(), sqlDate));
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Bienvenue :) ");
                        alert.setHeaderText(null);
                        alert.setContentText("Bienvenue dans notre platform digitale 9arini");
                        alert.show();
                        } catch (SQLException ex) {
                        Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                        }*/
                //System.out.println(zd_DDN.getValue().equals(date1));
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Bienvenue :) ");
                alert.setHeaderText(null);
                alert.setContentText("Bienvenue dans notre platform digitale 9arini");
                alert.show();
            } else if (Formateur.equals(zd_Role.getValue())) {
                fs.ajouterFormateur(new Formateur(Integer.parseInt(zd_numtel.getText()), zd_Pdppath.getText(), zd_nom.getText(), zd_prenom.getText(),
                        zd_adresse.getText(), zd_pays.getText(), ((RadioButton) zd_genre.getSelectedToggle()).getText(), zd_Email.getText(),
                        zd_Mdp.getText(), zd_Role.getValue(), zd_Org.getText(), zd_Fonction.getText(), zd_softskills.getText(), sqlDate));
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Bienvenue :) ");
                alert.setHeaderText(null);
                alert.setContentText("Bienvenue dans notre platform digitale 9arini");
                alert.show();
            } else if (Entrepreneur.equals(zd_Role.getValue())) {
                es.ajouterEntrepreneur(new Entrepreneur(Integer.parseInt(zd_numtel.getText()), zd_Pdppath.getText(), zd_nom.getText(), zd_prenom.getText(),
                        zd_adresse.getText(), zd_pays.getText(), ((RadioButton) zd_genre.getSelectedToggle()).getText(), zd_Email.getText(),
                        zd_Mdp.getText(), zd_Role.getValue(), zd_Org.getText(), zd_Fonction.getText(), zd_nomeentrprise.getText(), zd_sitewebEntreprise.getText(), zd_EntrpreneurUsage.getText(), sqlDate));
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Bienvenue :) ");
                alert.setHeaderText(null);
                alert.setContentText("Bienvenue dans notre platform digitale 9arini");
                alert.show();
            }

        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Bienvenue :) ");
            alert.setHeaderText(null);
            alert.setContentText("code invalid");
            alert.show();
        }
    }

    @FXML
    private void reotourdansut(ActionEvent event) throws IOException {
            Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/Bienvenue.fxml"));
        Scene scene = new Scene(page1, 1106, 819);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Bienvenue dans 9arini");

        stage.setScene(scene);
        stage.show();
    }

}
