/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import IService.IPubliciteService;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Categorie;
import entities.Publicite;

import DB.DB;

/**
 *
 * @author pc-asus
 */
public class PubliciteServices implements IPubliciteService{

    static Connection connexion;

    public PubliciteServices() {
        connexion = DB.getInstance().getConnection();
    }

    @Override
    public void ajouterPublicite(Publicite p) throws SQLException {
        String req = "INSERT INTO `publicite`( `video_publicite`,`lien_publicite`, `nbr_click`, `nbr_affichage`, `image_publicite`, `cible_id`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, p.getVideo_publicite());
                       ps.setString(2, p.getLien_publicite());
            ps.setInt(3, p.getNbr_click());
            ps.setInt(4, p.getNbr_affichage());
            ps.setString(5, p.getImage_publicite());
            ps.setInt(6, p.getCible_id());
            ps.executeUpdate();
            System.out.println("publicite ajouté");
        } catch (SQLException ex) {
            System.out.println("Erreur d'ajout" + ex.getMessage());
        }
    }

    @Override
    public List<Publicite> affichertab() throws SQLException {
        List<Publicite> publicites = new ArrayList<>();
        String req = "SELECT * FROM publicite";
        try {

            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {
                Publicite P = new Publicite(
                       rst.getInt("id"),
                       rst.getString("video_publicite"),
                                           rst.getString("lien_publicite"),
                        rst.getInt("nbr_click"),
                        rst.getInt("nbr_affichage"),
                       rst.getString("image_publicite"));
                publicites.add(P);
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(Publicite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return publicites;
    }

    @Override
    public void modifierPublicite(Publicite p) throws SQLException, NoSuchAlgorithmException {
    
        
       String req = "UPDATE `publicite` SET "
                + " `video_publicite`='" + p.getVideo_publicite()+ "'"
                + ", `lien_publicite`='" + p.getLien_publicite()+ "'"
                + ", `image_publicite`='" + p.getImage_publicite()+ "'where lien_publicite LIKE'"+ p.getLien_publicite()+"'";
        try {
            Statement stm = connexion.createStatement();
            stm.executeUpdate(req);
            System.out.println("publicite Modifier");
        } catch (SQLException ex) {
            System.out.println("Erreur d'ajout" + ex.getMessage());

        }}
  @Override
    public void Modifier2(Publicite p) {
  try {
            String req = "UPDATE  publicite SET video_publicite=?,lien_publicite=?, image_publicite=?   WHERE id=?  ";
            PreparedStatement ps = connexion.prepareStatement(req);

            ps.setString(1, p.getVideo_publicite());
                     /*    ps.setInt(3, p.getNbr_click());
                                                  ps.setInt(4, p.getNbr_affichage());
*/ ps.setString(2, p.getLien_publicite());
                                                ps.setString(3, p.getImage_publicite());
                                   ps.setInt(4, p.getId());

            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        }
      
    
    
    
    
    
    
    
    
    
    
   @Override
    public void supprimerPublicite(Publicite p) throws SQLException {

        try {
            String req = "DELETE FROM `publicite` where lien_publicite  = ?";
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, p.getLien_publicite());
            ps.execute/*Update*/();
            System.out.println(" Publicite supprimé");
        } catch (SQLException ex) {
            System.out.println("Erreur de suppression" + ex.getMessage());
        }
    }

  





    }

   
 
   
   
	 
	
		



	
    
    


