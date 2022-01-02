/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import IService.IServiceCategorie;
import entities.Categorie;
import DB.DB;
import entities.Cours;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author fares
 */
public class Categorieservice implements IServiceCategorie {
    Connection connexion;
    List<Cours> css;

    public Categorieservice() {
        connexion = DB.getInstance().getConnection();
    }
    @Override
    public void ajouterProduitCategorie(Categorie c) throws SQLException {
        String req = "INSERT INTO `categorie` (`categorieNom`,`categorieImage`) "
                + "VALUES (?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, c.getCategorieNom());
        ps.setString(2, c.getCategorieImage());

        ps.executeUpdate();
    }
    @Override
    public List<Categorie> getAllCategorie() throws SQLException {
        List<Categorie> Categories = new ArrayList<>();
        String req = "select * from categorie";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Categorie c = new Categorie(rst.getInt("id"),
                    rst.getString("categorieNom"),
                    rst.getString("categorieImage"));

            Categories.add(c);
        }
        return Categories;
    }
    
    @Override
    public List<String> getAllCategorieNom() throws SQLException {
        List<String> categories = new ArrayList<>();
        String req = "select * from categorie";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Categorie c = new Categorie(rst.getString("categorieNom"));
            categories.add(c.toString());
        }
        return categories;
    }

      @Override
    public ObservableList<Categorie> getAll() throws SQLException {
        ObservableList<Categorie> categories = FXCollections.observableArrayList();
        Statement st = null;
        try {
            String req = "select * from categorie";

            st = connexion.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setId(rs.getInt("id"));

                c.setCategorieNom(rs.getString("categorieNom"));
                                c.setCategorieNom(rs.getString("categorieImage"));

                categories.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return categories;
    }
    @Override
    public List<Categorie> afficherCategorie() throws SQLException {
        List<Categorie> myList = new ArrayList();

        String requete = "SELECT * FROM categorie ";
        PreparedStatement pst = connexion.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Categorie a = new Categorie();
            a.setId(rs.getInt(1));
            a.setCategorieNom(rs.getString(2));
            a.setCategorieImage(rs.getString(3));
            myList.add(a);
        } 
        return myList;
    } 
        @Override
    public void supprimerCategorie( Categorie c) throws SQLException {

        String req = "DELETE FROM categorie WHERE id=?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
           // css.remove(cs);

            ps.setInt(1, c.getId());
            ps.execute/*Update*/();
        } catch (SQLException ex) {
                        System.out.println(ex);

        }
    }
    
     @Override
    public void ModifierCategorie(Categorie c)  {
        try {
            String req = "UPDATE categorie SET "
   + " categorieNom='" + c.getCategorieNom()  + ", categorieImage='" + c.getCategorieImage()  + "'where id = " + c.getId()+ "";
            Statement stm = connexion.createStatement();
            stm.executeUpdate(req);

        } catch (SQLException ex) {
        }
    }
    @Override
     public void edit(Categorie c) throws SQLException {
        try {
            String req = "UPDATE  categorie  SET  categorieNom=?, categorieImage=?   WHERE id = ?  ";
            PreparedStatement ps = connexion.prepareStatement(req);
           
            ps.setString(1, c.getCategorieNom());
                        ps.setString(2, c.getCategorieImage());
 ps.setInt(3, c.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        }
    @Override
     public Categorie findCategorieById(int id) throws SQLException{
    
                Categorie c = new Categorie();
           
        String requete="select * from categorie where id="+id;
       
            Statement st = connexion.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next())
            {  
                
            c.setId(rs.getInt(1));
            c.setCategorieNom(rs.getString(2));
            c.setCategorieImage(rs.getString(3));
            } 
               return c;                 
      }
}
