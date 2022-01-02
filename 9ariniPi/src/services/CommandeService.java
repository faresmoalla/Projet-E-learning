package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Commande;
import entities.Panier;
import IService.IServiceCommande;
import DB.DB;
import entities.Membre;

/**
 *
 * @author Aminous
 */
public class CommandeService implements IServiceCommande {

    Connection connexion;

    public CommandeService() {
        connexion = DB.getInstance().getConnection();
    }

    @Override
    public Panier ajouterCommande(Commande cde, Membre p) throws SQLException {
        String req = "INSERT INTO `commande` (`panierID`, `coursID`) VALUES (?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        PanierService panierService = new PanierService();
        Panier pn;
        if (panierService.recupererPanier(p) == null) {
            pn = new Panier(p.getId(), Panier.getCurrentDate(), "En cours");
            int panierID = panierService.ajouterPanier(pn);
            ps.setInt(1, panierID);
        } else {
            pn = panierService.recupererPanier(p);
            ps.setInt(1, pn.getPanierID());
        }
        if ((Integer) cde.getCoursID() == 0) {
            ps.setNull(2, cde.getCoursID());
        } else {
            ps.setInt(2, cde.getCoursID());
        }
        ps.executeUpdate();
        return pn;
    }

    @Override
    public void supprimerCommande(Commande cde) throws SQLException {
        String req = "DELETE FROM `commande` WHERE commandeID = " + cde.getCommandeID();
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.executeUpdate();
    }

    @Override
    public List<Commande> recupererCommandes(Panier pn) throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String req = "select * FROM commande where panierID = " + pn.getPanierID();
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Commande cde = new Commande(
                    rst.getInt("commandeID"),
                    rst.getInt("panierID"),
                    rst.getInt("coursID")
            );
            commandes.add(cde);
        }
        return commandes;
    }

    @Override
    public Commande premiereCommande(Panier pn) throws SQLException {
        Commande cde = new Commande();
        String req = "select * from commande where panierID = " + pn.getPanierID() + " limit 1";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        if (rst.next()) {
            cde = new Commande(
                    rst.getInt("commandeID"),
                    rst.getInt("coursID")
            );
        }
        return cde;
    }

    @Override
    public int nombreTotalCommandes(Panier pn) throws SQLException {
        String req = "select count(*) as total FROM commande where panierID = " + pn.getPanierID();
        int nombreTotal = 0;
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        if (rst.next()) {
            nombreTotal = rst.getInt("total");
        }
        return nombreTotal;
    }

}
