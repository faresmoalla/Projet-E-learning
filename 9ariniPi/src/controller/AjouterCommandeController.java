package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CommandeService;
import services.Coursservice;
import services.PanierService;
import DB.DB;
import entities.Commande;
import entities.Panier;
import entities.Utilisateur;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Aminous
 */
public class AjouterCommandeController implements Initializable {

    Connection connexion;

    public static String prixPanier;

    PanierService panierService = new PanierService();

    CommandeService commandeService = new CommandeService();

    Coursservice coursService = new Coursservice();

    ReclamationService reclamationService = new ReclamationService();

    public AjouterCommandeController() {
        connexion = DB.getInstance().getConnection();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    private Button ajouterCde1Bouton;
    @FXML
    private Button annulerCde1Bouton;
    @FXML
    private Button ajouterCde2Bouton;
    @FXML
    private Button annulerCde2Bouton;
    @FXML
    private Button cde1Bouton;
    @FXML
    private Button cde2Bouton;
    @FXML
    private Button cde3Bouton;
    @FXML
    private Button cde4Bouton;
    @FXML
    private Button ajouterCde3Bouton;
    @FXML
    private Button annulerCde3Bouton;
    @FXML
    private Button ajouterCde4Bouton;
    @FXML
    private Button annulerCde4Bouton;
    @FXML
    private Label nombreNotifications;
    @FXML
    private Label nombreCommandes;
    @FXML
    private Label prixTotalPanier;
    @FXML
    private Circle imgUtilisateur;
    @FXML
    private Label nomUtilisateur;

    public Label getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(Label nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            imgUtilisateur.setStroke(Color.DARKBLUE);
            imgUtilisateur.setFill(new ImagePattern(new Image("/Images/" + AuthentificationController.connectedUser.getUtilisateurPdp(), false)));
            nomUtilisateur.setText(AuthentificationController.connectedUser.getUtilisateurNom());
            nombreNotifications.setText(((Integer) reclamationService.recupererReclamations(AuthentificationController.connectedUser).size()).toString());
            nombreCommandes.setText(((Integer) commandeService.recupererCommandes(panierService.recupererPanier(AuthentificationController.connectedUser)).size()).toString());
            prixTotalPanier.setText(String.format("%4s", ((Integer) prixTotalPanier(AuthentificationController.connectedUser)).toString()).replace(' ', '0') + " DT");
            prixPanier = prixTotalPanier.getText();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Panier pn = new Panier(AuthentificationController.connectedUser.getId(), Panier.getCurrentDate(), "En cours");
            try {
                panierService.ajouterPanier(pn);
            } catch (SQLException ex1) {
                Logger.getLogger(AjouterCommandeController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public int prixTotalPanier(Utilisateur p) throws SQLException {
        int prixTotal = 0;
        for (Commande cdes : commandeService.recupererCommandes(panierService.recupererPanier(p))) {
            prixTotal += coursService.rechercherCours(cdes).getNbrChapitres();
        }
        return prixTotal;
    }

    @FXML
    private void ajouterCommande() throws IOException, SQLException {
        String req = "INSERT INTO `commande` (`panierID`, `coursID`) VALUES (?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        cde1Bouton.setOnAction(e -> {
            ajouterCde1Bouton.setVisible(true);
            annulerCde1Bouton.setVisible(true);
            ajouterCde2Bouton.setVisible(false);
            annulerCde2Bouton.setVisible(false);
            ajouterCde3Bouton.setVisible(false);
            annulerCde3Bouton.setVisible(false);
            ajouterCde4Bouton.setVisible(false);
            annulerCde4Bouton.setVisible(false);
            ajouterCde1Bouton.setOnAction(ej -> {
                try {
                    Boolean next = true;
                    Commande cde = new Commande(1);
                    for (Commande cdes : commandeService.recupererCommandes(panierService.recupererPanier(AuthentificationController.connectedUser))) {
                        if (cdes.getCoursID() == cde.getCoursID()) {
                            JOptionPane.showMessageDialog(null, "Ce cours a été déjà commandé!");
                            next = false;
                        }
                    }
                    if (next) {
                        ps.setInt(1, panierService.recupererPanier(AuthentificationController.connectedUser).getPanierID());
                        ps.setInt(2, cde.getCoursID());
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Ce cours a été ajouté avec succès!");
                        nombreCommandes.setText(((Integer) commandeService.nombreTotalCommandes(panierService.recupererPanier(AuthentificationController.connectedUser))).toString());
                        prixTotalPanier.setText(String.format("%4s", ((Integer) prixTotalPanier(AuthentificationController.connectedUser)).toString()).replace(' ', '0') + " DT");
                        prixPanier = prixTotalPanier.getText();
                    }
                    ajouterCde1Bouton.setVisible(false);
                    annulerCde1Bouton.setVisible(false);

                } catch (SQLException ex) {
                    Logger.getLogger(AjouterCommandeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            annulerCde1Bouton.setOnAction(en -> {
                ajouterCde1Bouton.setVisible(false);
                annulerCde1Bouton.setVisible(false);
                ajouterCde2Bouton.setVisible(false);
                annulerCde2Bouton.setVisible(false);
                ajouterCde3Bouton.setVisible(false);
                annulerCde3Bouton.setVisible(false);
                ajouterCde4Bouton.setVisible(false);
                annulerCde4Bouton.setVisible(false);
            });
        });
        cde2Bouton.setOnAction(e -> {
            ajouterCde1Bouton.setVisible(false);
            annulerCde1Bouton.setVisible(false);
            ajouterCde2Bouton.setVisible(true);
            annulerCde2Bouton.setVisible(true);
            ajouterCde3Bouton.setVisible(false);
            annulerCde3Bouton.setVisible(false);
            ajouterCde4Bouton.setVisible(false);
            annulerCde4Bouton.setVisible(false);
            ajouterCde2Bouton.setOnAction(ej -> {
                try {
                    Boolean next = true;
                    Commande cde = new Commande(2);
                    for (Commande cdes : commandeService.recupererCommandes(panierService.recupererPanier(AuthentificationController.connectedUser))) {
                        if (cdes.getCoursID() == cde.getCoursID()) {
                            JOptionPane.showMessageDialog(null, "Ce cours a été déjà commandé!");
                            next = false;
                        }
                    }
                    if (next) {
                        ps.setInt(1, panierService.recupererPanier(AuthentificationController.connectedUser).getPanierID());
                        ps.setInt(2, cde.getCoursID());
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Ce cours a été ajouté avec succès!");
                        nombreCommandes.setText(((Integer) commandeService.nombreTotalCommandes(panierService.recupererPanier(AuthentificationController.connectedUser))).toString());
                        prixTotalPanier.setText(String.format("%4s", ((Integer) prixTotalPanier(AuthentificationController.connectedUser)).toString()).replace(' ', '0') + " DT");
                        prixPanier = prixTotalPanier.getText();
                    }
                    ajouterCde2Bouton.setVisible(false);
                    annulerCde2Bouton.setVisible(false);
                } catch (SQLException ex) {
                    Logger.getLogger(AjouterCommandeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            annulerCde2Bouton.setOnAction(ej -> {
                ajouterCde1Bouton.setVisible(false);
                annulerCde1Bouton.setVisible(false);
                ajouterCde2Bouton.setVisible(false);
                annulerCde2Bouton.setVisible(false);
                ajouterCde3Bouton.setVisible(false);
                annulerCde3Bouton.setVisible(false);
                ajouterCde4Bouton.setVisible(false);
                annulerCde4Bouton.setVisible(false);
            });
        });
        cde3Bouton.setOnAction(e -> {
            ajouterCde1Bouton.setVisible(false);
            annulerCde1Bouton.setVisible(false);
            ajouterCde2Bouton.setVisible(false);
            annulerCde2Bouton.setVisible(false);
            ajouterCde3Bouton.setVisible(true);
            annulerCde3Bouton.setVisible(true);
            ajouterCde4Bouton.setVisible(false);
            annulerCde4Bouton.setVisible(false);
            ajouterCde3Bouton.setOnAction(ej -> {
                try {
                    Boolean next = true;
                    Commande cde = new Commande(3);
                    for (Commande cdes : commandeService.recupererCommandes(panierService.recupererPanier(AuthentificationController.connectedUser))) {
                        if (cdes.getCoursID() == cde.getCoursID()) {
                            JOptionPane.showMessageDialog(null, "Ce cours a été déjà commandé!");
                            next = false;
                        }
                    }
                    if (next) {
                        ps.setInt(1, panierService.recupererPanier(AuthentificationController.connectedUser).getPanierID());
                        ps.setInt(2, cde.getCoursID());
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Ce cours a été ajouté avec succès!");
                        nombreCommandes.setText(((Integer) commandeService.nombreTotalCommandes(panierService.recupererPanier(AuthentificationController.connectedUser))).toString());
                        prixTotalPanier.setText(String.format("%4s", ((Integer) prixTotalPanier(AuthentificationController.connectedUser)).toString()).replace(' ', '0') + " DT");
                        prixPanier = prixTotalPanier.getText();
                    }
                    ajouterCde3Bouton.setVisible(false);
                    annulerCde3Bouton.setVisible(false);
                } catch (SQLException ex) {
                    Logger.getLogger(AjouterCommandeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            annulerCde3Bouton.setOnAction(ej -> {
                ajouterCde1Bouton.setVisible(false);
                annulerCde1Bouton.setVisible(false);
                ajouterCde2Bouton.setVisible(false);
                annulerCde2Bouton.setVisible(false);
                ajouterCde3Bouton.setVisible(false);
                annulerCde3Bouton.setVisible(false);
                ajouterCde4Bouton.setVisible(false);
                annulerCde4Bouton.setVisible(false);
            });
        });
        cde4Bouton.setOnAction(e
                -> {
            ajouterCde1Bouton.setVisible(false);
            annulerCde1Bouton.setVisible(false);
            ajouterCde2Bouton.setVisible(false);
            annulerCde2Bouton.setVisible(false);
            ajouterCde3Bouton.setVisible(false);
            annulerCde3Bouton.setVisible(false);
            ajouterCde4Bouton.setVisible(true);
            annulerCde4Bouton.setVisible(true);
            ajouterCde4Bouton.setOnAction(en -> {
                try {
                    Boolean next = true;
                    Commande cde = new Commande(7);
                    for (Commande cdes : commandeService.recupererCommandes(panierService.recupererPanier(AuthentificationController.connectedUser))) {
                        if (cdes.getCoursID() == cde.getCoursID()) {
                            JOptionPane.showMessageDialog(null, "Ce cours a été déjà commandé!");
                            next = false;
                        }
                    }
                    if (next) {
                        ps.setInt(1, panierService.recupererPanier(AuthentificationController.connectedUser).getPanierID());
                        ps.setInt(2, cde.getCoursID());
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Ce cours a été ajouté avec succès!");
                        nombreCommandes.setText(((Integer) commandeService.nombreTotalCommandes(panierService.recupererPanier(AuthentificationController.connectedUser))).toString());
                        prixTotalPanier.setText(String.format("%4s", ((Integer) prixTotalPanier(AuthentificationController.connectedUser)).toString()).replace(' ', '0') + " DT");
                        prixPanier = prixTotalPanier.getText();
                    }
                    ajouterCde4Bouton.setVisible(false);
                    annulerCde4Bouton.setVisible(false);
                } catch (SQLException ex) {
                    Logger.getLogger(AjouterCommandeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            annulerCde4Bouton.setOnAction(ej
                    -> {
                ajouterCde1Bouton.setVisible(false);
                annulerCde1Bouton.setVisible(false);
                ajouterCde2Bouton.setVisible(false);
                annulerCde2Bouton.setVisible(false);
                ajouterCde3Bouton.setVisible(false);
                annulerCde3Bouton.setVisible(false);
                ajouterCde4Bouton.setVisible(false);
                annulerCde4Bouton.setVisible(false);
            });
        });
    }

    @FXML
    private void afficherPanier(ActionEvent event) throws IOException {
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
            Logger.getLogger(PayerFactureController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void seDeconnecter(ActionEvent event) throws IOException {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/view/Authentification.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }
}
