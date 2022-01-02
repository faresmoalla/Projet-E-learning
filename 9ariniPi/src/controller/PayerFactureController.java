package controller;

import java.io.IOException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import services.CommandeService;
import services.PanierService;
import services.Coursservice;
import DB.DB;
import controller.AfficherPanierController;
import controller.AjouterCommandeController;
import entities.Commande;
import entities.Panier;
import entities.Utilisateur;

/**
 * FXML Controller class
 *
 * @author Aminous
 */
public class PayerFactureController implements Initializable {

    Connection connexion;

    public static String numCarte;
    public static String prixTotal;

    PanierService panierService = new PanierService();

    CommandeService commandeService = new CommandeService();

    Coursservice coursService = new Coursservice();

    public PayerFactureController() {
        connexion = DB.getInstance().getConnection();
    }

    @FXML
    private ImageView carteBancaireFBG;
    @FXML
    private Label labNomService;
    @FXML
    private ImageView puceCarteBancaire;
    @FXML
    private ImageView iconNFC;
    @FXML
    private Label labNumCarte;
    @FXML
    private Label labDateExpiration;
    @FXML
    private Label labNomClient;
    @FXML
    private ImageView iconMastercard;
    @FXML
    private ImageView carteBancaireRBG;
    @FXML
    private Label labValabilite;
    @FXML
    private Rectangle bandeMagnetique;
    @FXML
    private ImageView bandeCodeSecret;
    @FXML
    private Label labCodeSecret;
    @FXML
    private Label labDetailCarte;
    @FXML
    private TextField numeroCarte;
    @FXML
    private TextField CVV;
    @FXML
    private TextField nomProprietaire;
    @FXML
    private ComboBox moisCarte;
    @FXML
    private ComboBox anneeCarte;
    @FXML
    private TextField prixTTC;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> listMois = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        moisCarte.setItems(listMois);
        ObservableList<Integer> listAnnee = FXCollections.observableArrayList(21, 22, 23, 24, 25, 26);
        anneeCarte.setItems(listAnnee);
        nomProprietaire.setText(AuthentificationController.connectedUser.getUtilisateurPrenom() + " " + AuthentificationController.connectedUser.getUtilisateurNom().toUpperCase());
        labNomClient.setText(nomProprietaire.getText());
        try {
            prixTTC.setText((((Integer) prixTotalPanier(AuthentificationController.connectedUser)).toString()) + " DT");
            prixTotal = prixTTC.toString();
        } catch (SQLException ex) {
            Logger.getLogger(PayerFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public int prixTotalPanier(Utilisateur p) throws SQLException {
        int prixTotal = 0;
        for (Commande cdes : commandeService.recupererCommandes(panierService.recupererPanier(p))) {
            prixTotal += coursService.rechercherCours(cdes).getNbrChapitres();
        }
        return prixTotal;
    }

    public void payerFacture(ActionEvent event) throws MessagingException {
        if (numeroCarte.getText().isEmpty() || CVV.getText().isEmpty() || nomProprietaire.getText().isEmpty() || moisCarte.getSelectionModel().isEmpty() || anneeCarte.getSelectionModel().isEmpty()) {
            JOptionPane.showMessageDialog(null, "   Veuillez remplir tous les champs");
        } else if (!numeroCarte.getText().matches("\\d*") || numeroCarte.getText().length() != 16) {
            JOptionPane.showMessageDialog(null, "   Numéro de carte invalide");
        } else if (!CVV.getText().matches("[0-9]+") || CVV.getText().length() != 3) {
            JOptionPane.showMessageDialog(null,     "Numéro CVV invalide");
        } else if (!nomProprietaire.getText().matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(null, "   Nom du propriétaire invalide");
        } else if ((Integer) moisCarte.getSelectionModel().getSelectedItem() <= LocalDate.now().getMonthValue() && (Integer) anneeCarte.getSelectionModel().getSelectedItem() <= Integer.valueOf(((Integer) LocalDate.now().getYear()).toString().substring(2))) {
            JOptionPane.showMessageDialog(null, "   Date d'expiration invalide");
        } else {
            int res = JOptionPane.showConfirmDialog(null, "Vous êtes en train de payer votre panier!\n                          Continuer?", "", JOptionPane.YES_NO_OPTION);
            switch (res) {
                case JOptionPane.YES_OPTION: {
                    try {
                        Panier pn = panierService.recupererPanier(AuthentificationController.connectedUser);
                        Panier pn2 = new Panier(Panier.getCurrentDate(), "Payé");
                        panierService.modifierPanier(pn, pn2);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficherPanierController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                JOptionPane.showMessageDialog(null, "   Votre panier a été payé avec succès!");
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                try {
                    numCarte = labNumCarte.getText();
                    sendMail("malika.sassi.77@gmail.com");
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
    }

    public static void sendMail(String recipient) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "mohamedaminebenfredj.1k99@gmail.com";
        String password = "A2m6i4n6o6u8s7";

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
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            message.setSubject("Reçu de Paiement");
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<p>"
                    + "Bonjour " + AuthentificationController.connectedUser.getUtilisateurPrenom() + ", <br/>"
                    + "Nous avons bien reçu votre facture numéro " + ((Integer) AfficherPanierController.panierOuverte.getPanierID()).toString() + ". <br/>"
                    + "Après vérification de notre compte, nous avons constaté que cette facture avait été payée le <b>" + sdf.format(AfficherPanierController.panierOuverte.getDatePanier()) + "</b> par carte bancaire numérotée <b>" + numCarte + "</b> à votre ordre, <br/>"
                    + "d'un montant de <b>" + prixTotal + " DT</b>. <br/>"
                    + "Veuillez agréer nos salutations distinguées. <br/>"
                    + "Cordialement. <br/>"
                    + "</p>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(PayerFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void retourPayerFacture(ActionEvent event) {
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

    public void numeroCarte(ActionEvent event) {
        carteBancaireFBG.setVisible(true);
        labNomService.setVisible(true);
        puceCarteBancaire.setVisible(true);
        iconNFC.setVisible(true);
        labNumCarte.setVisible(true);
        labDateExpiration.setVisible(true);
        labNomClient.setVisible(true);
        iconMastercard.setVisible(true);
        carteBancaireRBG.setVisible(false);
        labValabilite.setVisible(false);
        bandeMagnetique.setVisible(false);
        bandeCodeSecret.setVisible(false);
        labCodeSecret.setVisible(false);
        labDetailCarte.setVisible(false);
        numeroCarte.setOnKeyPressed(e -> labNumCarte.setText(numeroCarte.getText().replaceAll("(.{" + 4 + "})", "$0 ").trim()));
    }

    public void CVV(ActionEvent event) {
        carteBancaireFBG.setVisible(false);
        labNomService.setVisible(false);
        puceCarteBancaire.setVisible(false);
        iconNFC.setVisible(false);
        labNumCarte.setVisible(false);
        labDateExpiration.setVisible(false);
        labNomClient.setVisible(false);
        iconMastercard.setVisible(false);
        carteBancaireRBG.setVisible(true);
        labValabilite.setVisible(true);
        bandeMagnetique.setVisible(true);
        bandeCodeSecret.setVisible(true);
        labCodeSecret.setVisible(true);
        labDetailCarte.setVisible(true);
        CVV.setOnKeyPressed(e -> labCodeSecret.setText(CVV.getText()));
    }

    public void nomProprietaire(ActionEvent event) {
        carteBancaireFBG.setVisible(true);
        labNomService.setVisible(true);
        puceCarteBancaire.setVisible(true);
        iconNFC.setVisible(true);
        labNumCarte.setVisible(true);
        labDateExpiration.setVisible(true);
        labNomClient.setVisible(true);
        iconMastercard.setVisible(true);
        carteBancaireRBG.setVisible(false);
        labValabilite.setVisible(false);
        bandeMagnetique.setVisible(false);
        bandeCodeSecret.setVisible(false);
        labCodeSecret.setVisible(false);
        labDetailCarte.setVisible(false);
        nomProprietaire.setOnKeyPressed(e -> labNomClient.setText(nomProprietaire.getText()));
    }

    public void moisCarte(ActionEvent event) {
        carteBancaireFBG.setVisible(true);
        labNomService.setVisible(true);
        puceCarteBancaire.setVisible(true);
        iconNFC.setVisible(true);
        labNumCarte.setVisible(true);
        labDateExpiration.setVisible(true);
        labNomClient.setVisible(true);
        iconMastercard.setVisible(true);
        carteBancaireRBG.setVisible(false);
        labValabilite.setVisible(false);
        bandeMagnetique.setVisible(false);
        bandeCodeSecret.setVisible(false);
        labCodeSecret.setVisible(false);
        labDetailCarte.setVisible(false);
        if (anneeCarte.getSelectionModel().isEmpty()) {
            moisCarte.setOnKeyPressed(e -> labDateExpiration.setText(String.format("%2s", moisCarte.getSelectionModel().getSelectedItem().toString()).replace(' ', '0') + '/'));
        } else {
            moisCarte.setOnKeyPressed(e -> labDateExpiration.setText(String.format("%2s", moisCarte.getSelectionModel().getSelectedItem().toString()).replace(' ', '0') + '/' + String.format("%2s", anneeCarte.getSelectionModel().getSelectedItem().toString()).replace(' ', '0')));
        }
    }

    public void anneeCarte(ActionEvent event) {
        carteBancaireFBG.setVisible(true);
        labNomService.setVisible(true);
        puceCarteBancaire.setVisible(true);
        iconNFC.setVisible(true);
        labNumCarte.setVisible(true);
        labDateExpiration.setVisible(true);
        labNomClient.setVisible(true);
        iconMastercard.setVisible(true);
        carteBancaireRBG.setVisible(false);
        labValabilite.setVisible(false);
        bandeMagnetique.setVisible(false);
        bandeCodeSecret.setVisible(false);
        labCodeSecret.setVisible(false);
        labDetailCarte.setVisible(false);
        if (moisCarte.getSelectionModel().isEmpty()) {
            anneeCarte.setOnKeyPressed(e -> labDateExpiration.setText('/' + String.format("%2s", anneeCarte.getSelectionModel().getSelectedItem().toString()).replace(' ', '0')));
        } else {
            anneeCarte.setOnKeyPressed(e -> labDateExpiration.setText(String.format("%2s", moisCarte.getSelectionModel().getSelectedItem().toString()).replace(' ', '0') + '/' + String.format("%2s", anneeCarte.getSelectionModel().getSelectedItem().toString()).replace(' ', '0')));
        }
    }
}
