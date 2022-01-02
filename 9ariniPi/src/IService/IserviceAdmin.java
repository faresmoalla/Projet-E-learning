/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import entities.Admin;
import entities.Utilisateur;

/**
 *
 * @author lahbib
 */
public interface IserviceAdmin {
    public void ajouterAdmin(Admin p) throws SQLException, NoSuchAlgorithmException ;
    public void modifierAdmin(Admin p,Admin p1) throws SQLException, NoSuchAlgorithmException;
    public void supprimerAdmin(Admin p) throws SQLException;
    public List<Admin> getAllAdmin() throws SQLException;
    public String hashmdp (String mdp) throws NoSuchAlgorithmException;
    public List<Admin> rechercherAdmin(Admin a) throws SQLException;
    public void modifierAdminMembre(Admin p, Utilisateur u) throws NoSuchAlgorithmException, SQLException;
    public void modifierAdminFormateur(Admin p, Utilisateur u) throws NoSuchAlgorithmException, SQLException;
    public void modifierAdminEntrepreneur(Admin p, Utilisateur u) throws NoSuchAlgorithmException, SQLException;
    
    
    
}
