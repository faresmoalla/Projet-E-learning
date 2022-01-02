package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Reclamation;
import IService.IServiceReclamation;
import DB.DB;
import entities.Utilisateur;

/**
 *
 * @author Aminous
 */
public class ReclamationService implements IServiceReclamation {

    Connection connexion;

    public ReclamationService() {
        connexion = DB.getInstance().getConnection();
    }

    @Override
    public void ajouterReclamation(Reclamation r) throws SQLException {
//        String req = "INSERT INTO `reclamation`"
//                + "(`coursid`,`utilisateurid`,`formateurid`,`formationid`,`etat`, `message`,`titre`) "
//                + "VALUES ( '"
//                + r.getCoursid() + "', '"
//                + r.getUtilisateurid() + "', '"
//                + r.getFormateurid() + "', '"
//                + r.getFormationid() + "', '"
//                + r.getEtat() + "', '"
//                + r.getMessage() + "', '"
//                + r.getTitre()
//                + "') ";
//
//        Statement stm = connexion.createStatement();
//        stm.executeUpdate(req);
    }
//

    @Override
    public List<Reclamation> getAllReclamation() throws SQLException {
//
//        List<Reclamation> reclamations = new ArrayList<>();
//        String req = "select * from Reclamation";
//        Statement stm = connexion.createStatement();
//        ResultSet rst = stm.executeQuery(req);
//
//        while (rst.next()) {
//            Reclamation r = new Reclamation(rst.getInt("reclamationid"),
//                    rst.getInt("formateurid"),
//                    rst.getInt("formationid"),
//                    rst.getInt("coursid"),
//                    rst.getInt("utilisateurid"),
//                    rst.getString("etat"),
//                    rst.getString("message"),
//                    rst.getString("titre"));
//            reclamations.add(r);
//        }
        return null;
    }
//

    @Override
    public void supprimerReclamationbyId(int reclamationid) throws SQLException {
//
//        String req = "DELETE FROM reclamation WHERE reclamationid=?";
//        try {
//            PreparedStatement pss = connexion.prepareStatement(req);
//
//            pss.setInt(1, reclamationid);
//            pss.executeUpdate();
//        } catch (SQLException ex) {
//        }
    }
//

    @Override
    public List<Reclamation> getAllReclamationParId(int utilisateurId) throws SQLException {
//
//        List<Reclamation> reclamations = new ArrayList<>();
//        String req = "select * from Reclamation where utilisateurId=" + utilisateurId;
//        Statement stm = connexion.createStatement();
//        ResultSet rst = stm.executeQuery(req);
//
//        while (rst.next()) {
//            Reclamation r = new Reclamation(rst.getInt("reclamationid"),
//                    rst.getInt("formateurid"),
//                    rst.getInt("formationid"),
//                    rst.getInt("coursid"),
//                    rst.getInt("utilisateurid"),
//                    rst.getString("etat"),
//                    rst.getString("message"),
//                    rst.getString("titre"));
//            reclamations.add(r);
//        }
        return null;
    }
//

    @Override
    public void updatereclamation(int idR, String newMsg) {
//        try {
//            String req = "UPDATE reclamation SET message = '" + newMsg + "' WHERE reclamationid =" + idR;
//            Statement stm = connexion.createStatement();
//            stm.executeUpdate(req);
//
//        } catch (SQLException ex) {
//        }
    }
//

    @Override
    public void supprimerReclamation(Reclamation r) throws SQLException {
//
//        String req = "DELETE FROM reclamation WHERE reclamationid=?";
//        try {
//            PreparedStatement ps = connexion.prepareStatement(req);
//            // css.remove(cs);
//
//            ps.setInt(1, r.getReclamationid());
//            ps.execute/*Update*/();
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
    }

    @Override
    public List<Reclamation> recupererReclamations(Utilisateur p) throws SQLException {
        List<Reclamation> reclamations = new ArrayList<>();
        String req = "SELECT * FROM `reclamation` WHERE utilisateurID = " + p.getId() + " AND etatReponse = 1";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Reclamation rec = new Reclamation(
                    rst.getInt("utilisateurID"),
                    rst.getString("categorieReclamation"),
                    rst.getString("messageReclamation"),
                    rst.getString("reponseReclamation"),
                    rst.getString("etatReclamation"),
                    rst.getDate("dateReclamation"),
                    rst.getDate("dateReponse"),
                    rst.getBoolean("etatReponse")
            );
            reclamations.add(rec);
        }
        return reclamations;
    }

}
