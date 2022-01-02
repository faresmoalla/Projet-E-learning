/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DB;
import entities.Categorie;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import services.Categorieservice;
import services.Coursservice;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author fares
 */
public class CoursModifierController implements Initializable {

    @FXML
    private Label labelimgpathonmodifier;
    @FXML
    private ImageView imageonmodifierrr;
    @FXML
    private Label labelpathduphotofilmodi;

    public CoursModifierController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = DB.getInstance().getConnection();
    }
    Connection connexion;

    @FXML
    private Label labelcoursid;
    @FXML
    private TextField labelutulisateurid;
    @FXML
    private TextField labelnbrchapitre;
    @FXML
    private ComboBox<String> labelcategorieid;
    @FXML
    private TextField labelcoursnom;
    @FXML
    private TextField labeldescription;
    @FXML
    private Button modifierc;
    Coursservice coursservice = new Coursservice();

    @FXML
    private Hyperlink Acceuilmodifer;
    @FXML
    private Hyperlink retourmodifier;
    @FXML
    private AnchorPane FenetreModifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Coursservice ps = new Coursservice();

        labelcoursid.setText(Integer.toString(CoursGestionController.connectedCourss.getId()));
        labelutulisateurid.setText(CoursGestionController.connectedCourss.getUtilisateurNom());
        labelnbrchapitre.setText(Integer.toString(CoursGestionController.connectedCourss.getNbrChapitres()));
     
      //  labelcategorieid.setValue.(CoursGestionController.connectedCourss.getCategorie_id());
        
        labelcoursnom.setText(CoursGestionController.connectedCourss.getNomCours());
        labeldescription.setText(CoursGestionController.connectedCourss.getDescription());
        labelimgpathonmodifier.setText(CoursGestionController.connectedCourss.getCoursImg());
        try {
            String req = "select * from categorie";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                Categorie a = new Categorie(rst.getString("id"));
                String xx = rst.getString("id");
                labelcategorieid.getItems().add(xx);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Browseimgonmodifier(ActionEvent event) {
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
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageonmodifierrr.setImage(image);
            imageonmodifierrr.setFitWidth(200);
            imageonmodifierrr.setFitHeight(200);
            imageonmodifierrr.scaleXProperty();
            imageonmodifierrr.scaleYProperty();
            imageonmodifierrr.setSmooth(true);
            imageonmodifierrr.setCache(true);
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
        labelpathduphotofilmodi.setText(file.getAbsolutePath());
    }

    @FXML
    private void modifierCours(ActionEvent event) {
        if (labelutulisateurid.getText().equals("") || labelnbrchapitre.getText().equals("")
                || labelcategorieid.getValue().equals("") || labelcoursnom.getText().equals("")
                || labeldescription.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        }
        Cours c1 = new Cours(Integer.parseInt(labelcoursid.getText()), labelutulisateurid.getText(), labelcoursnom.getText(), Integer.parseInt(labelnbrchapitre.getText()), labeldescription.getText(), labelpathduphotofilmodi.getText(),Integer.parseInt( labelcategorieid.getValue()));
        Coursservice coursservice = new Coursservice();
        coursservice.ModifierCours(c1);
        String message = labelcoursnom.getText();
        String title = "Modifié";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
    }

    @FXML
    private void Acceuilmodiferr(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("DashbordFormateur.fxml"));
        Scene scene = new Scene(page1, 687, 605);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Gestion");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void RetourModifier(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("CoursGestion.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Gestion des Cours");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logsdansmodifss(ActionEvent event) throws IOException {
          Parent page1 = FXMLLoader.load(getClass().getResource("Bienvenue.fxml"));
        Scene scene = new Scene(page1, 1106, 819);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Inscrivez Vous");

        stage.setScene(scene);
        stage.show();
    }

}
