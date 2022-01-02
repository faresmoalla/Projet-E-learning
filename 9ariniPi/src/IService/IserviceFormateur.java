/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import entities.Formateur;

/**
 *
 * @author lahbib
 */
public interface IserviceFormateur {
    public void ajouterFormateur(Formateur p) throws SQLException, NoSuchAlgorithmException;
    public void modifierFormateur(Formateur p,Formateur p1) throws SQLException, NoSuchAlgorithmException;
    public void supprimerFormateur(Formateur p) throws SQLException;
    public List<Formateur> getAllFormateur() throws SQLException;
    public String hashmdp (String mdp) throws NoSuchAlgorithmException;
    public List<Formateur> rechercherFormateur(Formateur f) throws SQLException;
    
}
