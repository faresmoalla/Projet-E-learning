/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;
import entities.Categorie;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import services.Categorieservice;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author fares
 */
public class CategorieGestionController implements Initializable {

    Connection cnx;
    @FXML
    private TableView<Categorie> table;
    @FXML
    private Button Rech;
    @FXML
    private TextField inputRech;
    @FXML
    private TableColumn<Categorie, Integer> categorieIDD;
    @FXML
    private TableColumn<Categorie, String> categorieNomm;
    @FXML
    private TableColumn<Categorie, String> categorieImagee;
    private AnchorPane fenetreafficher;
    @FXML
    private Button browse;
    @FXML
    private TextField FnomCategorie;
    @FXML
    private Button ajcat;
    @FXML
    private Label imgpath;
    @FXML
    private ImageView ImgAjout;
    Categorieservice ps = new Categorieservice();
    private ObservableList<Categorie> data;
    Categorieservice cs = new Categorieservice();
    ObservableList<Categorie> obl = FXCollections.observableArrayList();
    @FXML
    private Button aaa;
    @FXML
    private Hyperlink ReturnAcceuilcateg4;
    @FXML
    private AnchorPane fenetrexxx;
    @FXML
    private Hyperlink precedentt;
    @FXML
    private Button modif;
    @FXML
    private Label idlabel;
    @FXML
    private Button confirmermodifier;
    public ObservableList<Categorie> list;
    @FXML
    private Label AncienNom;
    @FXML
    private TextField FnomCategorieTest;
    @FXML
    private Button pdf2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Categorieservice ps = new Categorieservice();
        ArrayList<Categorie> a = new ArrayList<>();
        try {
            a = (ArrayList<Categorie>) ps.afficherCategorie();
        } catch (SQLException ex) {
        }
        ObservableList<Categorie> obsl = FXCollections.observableArrayList(a);
        table.setItems(obsl);
        categorieIDD.setCellValueFactory(new PropertyValueFactory<>("id"));
        categorieNomm.setCellValueFactory(new PropertyValueFactory<>("categorieNom"));
        categorieImagee.setCellValueFactory(new PropertyValueFactory<>("categorieImage"));
        try {
            list = FXCollections.observableArrayList(
                    ps.afficherCategorie()
            );
            FilteredList<Categorie> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Categorie>) Categories -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Categories.getCategorieNom().toLowerCase().contains(lower)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Categorie> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(table.comparatorProperty());
                table.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void RechercherAffich(ActionEvent event) throws IOException {
    }

    private void Ajouterfilaffic(ActionEvent event) throws IOException {

    }

    @FXML
    private void Ajouterimg(ActionEvent event) {
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
            ImgAjout.setImage(image);
            ImgAjout.setFitWidth(200);
            ImgAjout.setFitHeight(200);
            ImgAjout.scaleXProperty();
            ImgAjout.scaleYProperty();
            ImgAjout.setSmooth(true);
            ImgAjout.setCache(true);
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
        imgpath.setText(file.getAbsolutePath());
    }

    @FXML
    private void AjouterCateg(ActionEvent event) {
        Categorieservice productService = new Categorieservice();
        if (FnomCategorie.getText().equals("")) { //x|| Fdescription.getText().equals("")
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();

        } else {
            Categorie c = new Categorie(FnomCategorie.getText(), imgpath.getText());
            try {
                productService.ajouterProduitCategorie(c);
                resetTableData();
                String message = FnomCategorie.getText();
                String title = "Ajouté";
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

    public void resetTableData() throws SQLDataException, SQLException {
        List<Categorie> listCategorie = new ArrayList<>();
        listCategorie = cs.getAllCategorie();
        ObservableList<Categorie> data = FXCollections.observableArrayList(listCategorie);
        table.setItems(data);
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        if (event.getSource() == aaa) {
            Categorie categorie = new Categorie();
            categorie.setId(table.getSelectionModel().getSelectedItem().getId());
            System.out.println(table.getSelectionModel().getSelectedItem().getId());
            Categorieservice categorieService = new Categorieservice();
            categorieService.supprimerCategorie(categorie);
            resetTableData();
            String message = "Catégorie";
            String title = "Supprimé";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        }
    }

    @FXML
    private void ReturnAcceuilAjoutcategorie4(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("DashbordFormateur.fxml"));
        Scene scene = new Scene(page1, 656, 605);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Gestion");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Precedent(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("DashbordFormateur.fxml"));
        Scene scene = new Scene(page1, 656, 605);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Gestion");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        int id = table.getSelectionModel().getSelectedItem().getId();
        FnomCategorieTest.setText(table.getSelectionModel().getSelectedItem().getCategorieNom());
        FnomCategorie.setText(table.getSelectionModel().getSelectedItem().getCategorieNom());
        imgpath.setText(table.getSelectionModel().getSelectedItem().getCategorieImage());
        idlabel.setText(Integer.toString(id));
        confirmermodifier.setVisible(true);
    }

    @FXML
    private void Confirmermodifier(ActionEvent event) {
        try {
            Categorie c1 = new Categorie(Integer.parseInt(idlabel.getText()), FnomCategorie.getText(), imgpath.getText());
            Categorieservice categorieService = new Categorieservice();
            categorieService.edit(c1);
            resetTableData();
            AncienNom.setText(FnomCategorieTest.getText());
            String message2 = AncienNom.getText();
            String message = FnomCategorie.getText();
            String title = "Modifié";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage("Catégorie" + " " + message2 + "  " + "en Catégorie" + " " + message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void printPDF() throws FileNotFoundException, DocumentException, IOException {
        ;
        Document d = new Document();
        PdfWriter.getInstance(d, new FileOutputStream("C:\\Users\\fares\\Desktop\\xx\\hello.pdf"));
        d.open();
        d.add(new Paragraph("1ér test"));
        PdfPTable pTable = new PdfPTable(3);
        pTable.addCell("id");
        pTable.addCell("categorieNom");
        pTable.addCell("categorieImage");
        table.getItems().forEach((t) -> {
            pTable.addCell(String.valueOf(t.getId()));
            pTable.addCell(t.getCategorieNom());
            pTable.addCell(t.getCategorieImage());
            try {
                d.add(pTable);
            } catch (DocumentException ex) {
                System.out.println(ex);
            }
        });
        d.close();
        Desktop.getDesktop().open(new File("C:\\Users\\fares\\Desktop\\xx\\hello.pdf"));

    }

    @FXML
    private void pdffff(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
        if (event.getSource() == pdf2) {
            printPDF();
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("PDF");
            tray.setMessage("PDF COURS");
            tray.setNotificationType(NotificationType.INFORMATION);//
            tray.showAndDismiss(Duration.millis(3000));
        }
    }

    @FXML
    private void logoutcateg(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("Bienvenue.fxml"));
        Scene scene = new Scene(page1, 1106, 819);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Inscrivez Vous");

        stage.setScene(scene);
        stage.show();
    }
}
