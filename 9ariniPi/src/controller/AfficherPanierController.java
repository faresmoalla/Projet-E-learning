package controller;

import controller.PayerFactureController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CommandeService;
import services.PanierService;
import services.Coursservice;
import DB.DB;
import entities.Commande;
import entities.Panier;

/**
 * FXML Controller class
 *
 * @author Aminous
 */
public class AfficherPanierController implements Initializable {

    Connection connexion;

    public static Panier panierOuverte;

    PanierService panierService = new PanierService();

    CommandeService commandeService = new CommandeService();

    Coursservice coursService = new Coursservice();

    public AfficherPanierController() {
        connexion = DB.getInstance().getConnection();
    }

    @FXML
    private ListView<HBoxCell> listCommandes;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            int i = 0;
            List<HBoxCell> list = new ArrayList<>();
            for (Commande cdes : commandeService.recupererCommandes(panierService.recupererPanier(AuthentificationController.connectedUser))) {
                list.add(new HBoxCell(coursService.rechercherCours(cdes).getNomCours(), ((Integer) coursService.rechercherCours(cdes).getNbrChapitres()).toString() + " DT"));
            }
            ObservableList<HBoxCell> myObservableList = FXCollections.observableList(list);
            listCommandes.setItems(myObservableList);
            panierOuverte = panierService.recupererPanier(AuthentificationController.connectedUser);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherPanierController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Panier pn = new Panier(AuthentificationController.connectedUser.getId(), Panier.getCurrentDate(), "En cours");
            try {
                panierService.ajouterPanier(pn);
            } catch (SQLException ex1) {
                Logger.getLogger(AjouterCommandeController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public class HBoxCell extends HBox {

        Label label1 = new Label();
        Label label2 = new Label();
        Button button = new Button();
        Image img = new Image("/Images/deleteIcon.png");
        ImageView view = new ImageView(img);

        HBoxCell(String labelText1, String labelText2) {
            super();
            label1.setText(labelText1);
            label1.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label1, Priority.ALWAYS);

            label2.setText(labelText2);
            label2.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label2, Priority.ALWAYS);

            view.setFitHeight(20);
            view.setPreserveRatio(true);

            button.setGraphic(view);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int res = JOptionPane.showConfirmDialog(null, "Vous êtes en train de supprimer une commande!\n                              Continuer?", "", JOptionPane.YES_NO_OPTION);
                    switch (res) {
                        case JOptionPane.YES_OPTION: {
                            try {
                                Commande cde = new Commande();
                                cde.setCommandeID(commandeService.premiereCommande(panierService.recupererPanier(AuthentificationController.connectedUser)).getCommandeID() + listCommandes.getSelectionModel().getSelectedIndex());
                                commandeService.supprimerCommande(cde);
                                actualiserListeCommandes(panierService.recupererPanier(AuthentificationController.connectedUser));
                            } catch (SQLException ex) {
                                Logger.getLogger(AfficherPanierController.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Votre commande a été supprimée avec succès!");
                        break;
                        case JOptionPane.NO_OPTION:
                            JOptionPane.showMessageDialog(null, "Le processus est annulé.");
                            break;
                    }
                }
            });

            this.getChildren().addAll(label1, label2, button);
        }
    }

    @FXML
    private void ajouterCommande(ActionEvent event) throws IOException {
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
            Logger.getLogger(AfficherPanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualiserListeCommandes(Panier pn) throws SQLException {
        List<HBoxCell> list = new ArrayList<>();
        for (Commande cdes : commandeService.recupererCommandes(panierService.recupererPanier(AuthentificationController.connectedUser))) {
            list.add(new HBoxCell(coursService.rechercherCours(cdes).getNomCours(), ((Integer) coursService.rechercherCours(cdes).getNbrChapitres()).toString() + " DT"));
        }
        ObservableList<HBoxCell> myObservableList = FXCollections.observableList(list);
        listCommandes.setItems(myObservableList);
    }

    @FXML
    private void payerPanier(ActionEvent event) throws SQLException {
        if (commandeService.nombreTotalCommandes(panierService.recupererPanier(AuthentificationController.connectedUser)) == 0) {
        } else {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/PayerFacture.fxml"));
                Scene scene1 = new Scene(page1, 630, 670);
                Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene1);
                stage1.show();
            } catch (IOException ex) {
                Logger.getLogger(PayerFactureController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void reclamerPanier(ActionEvent event) throws SQLException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/view/AjouterReclamation.fxml"));
            Scene scene1 = new Scene(page1, 682, 498);
            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene1);
            stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
