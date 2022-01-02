package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import entities.Panier;
import IService.IServicePanier;
import DB.DB;
import entities.Utilisateur;

/**
 *
 * @author Aminous
 */
public class PanierService implements IServicePanier {

    Connection connexion;

    public PanierService() {
        connexion = DB.getInstance().getConnection();
    }

    @Override
    public int ajouterPanier(Panier pn) throws SQLException {
        String req = "INSERT INTO `panier` (`utilisateurID`, `datePanier`, `etatPanier` ) VALUES (?, ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, pn.getUtilisateurID());
        ps.setDate(2, Panier.getCurrentDate());
        ps.setString(3, pn.getEtatPanier()); 
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    @Override
    public void modifierPanier(Panier pn, Panier pn2) throws SQLException {
        String req = "UPDATE `panier` SET "
                + " datePanier='" + pn2.getDatePanier() + "'"
                + ", etatPanier='" + pn2.getEtatPanier() + "'"
                + " where panierID = " + pn.getPanierID() + " and etatPanier= 'En cours'";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void supprimerPanier(Panier pn) throws SQLException {
        String reqPn = "DELETE FROM `panier` WHERE panierID =" + pn.getPanierID() + "";
        String reqCde = "DELETE FROM `commande` WHERE panierID =" + pn.getPanierID() + "";
        PreparedStatement psPn = connexion.prepareStatement(reqPn);
        PreparedStatement psCde = connexion.prepareStatement(reqCde);
        psPn.executeUpdate();
        psCde.executeUpdate();
    }

    @Override
    public Panier recupererPanier(Utilisateur p) throws SQLException {
        String req = "SELECT * FROM `panier` WHERE utilisateurID = " + p.getId() + " AND etatPanier = 'En cours'";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        if (rst.next()) {
            Panier pn = new Panier(
                    rst.getInt("panierID"),
                    rst.getInt("utilisateurID"),
                    rst.getDate("datePanier"),
                    rst.getString("etatPanier")
            );
            return pn;
        }
        return null;
    }

}
