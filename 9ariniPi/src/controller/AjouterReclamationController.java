package controller;

import DB.DB;
import static controller.PayerFactureController.numCarte;
import static controller.PayerFactureController.sendMail;
import entities.Panier;
import entities.Reclamation;
import java.sql.Connection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author yahia
 */
public class AjouterReclamationController implements Initializable {

    Connection connexion;

    public AjouterReclamationController() {
        connexion = DB.getInstance().getConnection();
    }

    @FXML
    private ComboBox categorie;

    @FXML
    private TextArea description;

    @FXML
    private Button btnAjouterReclamation;

    @FXML
    private Button btnRetourReclamation;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> Categories = FXCollections.observableArrayList("Formation", "Paiement");
        categorie.setItems(Categories);
    }

    @FXML
    public void ajouterReclamation(ActionEvent event) throws IOException, SQLException, MessagingException {
        int res = JOptionPane.showConfirmDialog(null, "Vous êtes en train d'envoyer une réclamation!\n                          Continuer?", "", JOptionPane.YES_NO_OPTION);
        switch (res) {
            case JOptionPane.YES_OPTION: {
                try {
                    String req = "INSERT INTO `reclamation` (`utilisateurID`, `categorieReclamation`, `messageReclamation`, `dateReclamation`, `etatReclamation`) VALUES (?, ?, ?, ?, ?) ";
                    PreparedStatement ps = connexion.prepareStatement(req);
                    ps.setInt(1, AuthentificationController.connectedUser.getId());
                    ps.setString(2, categorie.getSelectionModel().getSelectedItem().toString());
                    ps.setString(3, description.getText());
                    ps.setDate(4, Reclamation.getCurrentDate());
                    ps.setString(5, "En cours");
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherPanierController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            JOptionPane.showMessageDialog(null, "   Votre réclamation a été envoyée avec succès!");
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AjouterCommande.fxml"));
                Scene scene1 = new Scene(page1, 1059, 817);
                Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene1);
                stage1.show();
            } catch (IOException ex) {
                Logger.getLogger(PayerFactureController.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case JOptionPane.NO_OPTION:
                JOptionPane.showMessageDialog(null, "Le processus est annulé.");
                break;
        }
    }

    @FXML
    public void retourReclamation(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/view/AfficherPanier.fxml"));
            Scene scene1 = new Scene(page1, 640, 400);
            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene1);
            stage1.show();

        } catch (IOException ex) {
            Logger.getLogger(PayerFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
