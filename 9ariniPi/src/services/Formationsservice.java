/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Formations;
import DB.DB;
import IService.IServiceFormations;
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
public class Formationsservice implements IServiceFormations {

    Connection connexion;

    public Formationsservice() {
        connexion = DB.getInstance().getConnection();
    }
    @Override
    public void ajouterProduitFormation(Formations f) throws SQLException {
        String req = "INSERT INTO `formation` (`id` , `nomFormation`) "
                + "VALUES ( ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, f.getUtilisateurID());
        ps.setString(2, f.getNomFormation());
        ps.executeUpdate();
    }
    @Override
    public List<Formations> getAllFormations() throws SQLException {
        List<Formations> Formationss = new ArrayList<>();
        String req = "select * from formation";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Formations f = new Formations(rst.getInt("formationID"),
                     rst.getInt("utilisateurID"),
                     rst.getString("nomFormation"));
            Formationss.add(f);
        }
        return Formationss;
    }
    @Override
    public void supprimerFormations(Formations f) throws SQLException {
        String req = "DELETE FROM formation WHERE nomFormation =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, f.getNomFormation());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
       @Override
    public void ModifierFormations(Formations f, int formationID) {
        try {
            String req = "UPDATE formation SET "
                    + " utilisateurID='" + f.getUtilisateurID() + "'"
                    + ", nomFormation='" + f.getNomFormation() + "'where formationID = " + formationID + "";
            Statement stm = connexion.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
        }
    }

  }

