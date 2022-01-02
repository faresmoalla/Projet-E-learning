/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Ressources;
import DB.DB;
import IService.IServiceRessources;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fares
 */
public class Ressourcesservice implements IServiceRessources {

    Connection connexion;
    //Utilisateur loggeduser;

    public Ressourcesservice() {
        connexion = DB.getInstance().getConnection();
    }
    @Override
    public void ajouterProduitRessources(Ressources r) throws SQLException {
        String req = "INSERT INTO `ressource` ( `coursID`,`ressourceNom`) "
                + "VALUES ( ?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, r.getCoursID());
        ps.setString(2, r.getRessourceNom());
        ps.executeUpdate();
    }
    @Override
    public List<Ressources> getAllRessources() throws SQLException {

        List<Ressources> Ressourcess = new ArrayList<>();
        String req = "select * from ressource";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Ressources r = new Ressources(rst.getInt("ressourceID"),
                    rst.getInt("coursID"),
                    rst.getString("ressourceNom"));
            Ressourcess.add(r);
        }
        return Ressourcess;
    }

    @Override
    public void supprimerRessources(Ressources c) throws SQLException {

        String req = "DELETE FROM ressource WHERE ressourceNom =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);

            ps.setString(1, c.getRessourceNom());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void ModifierRessources(Ressources rs, int ressourceID) {
        String req = "UPDATE ressource SET "
                + " ressourceID='" + rs.getRessourceID() + "'"
                + ", description='" + rs.getCoursID() + "'"
                + ", categorieID='" + rs.getRessourceNom() + "'where ressourceID = " + ressourceID + "";
    }
}
