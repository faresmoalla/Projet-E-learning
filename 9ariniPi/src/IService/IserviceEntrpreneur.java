/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import entities.Entrepreneur;

/**
 *
 * @author lahbib
 */
public interface IserviceEntrpreneur {
    public void ajouterEntrepreneur(Entrepreneur p) throws SQLException, NoSuchAlgorithmException;
    public void modifierEntrepreneur(Entrepreneur p,Entrepreneur p1) throws SQLException, NoSuchAlgorithmException;
     public void supprimerEntrepreneur(Entrepreneur p) throws SQLException;
     public List<Entrepreneur> getAllEntrepreneur() throws SQLException;
     public String hashmdp (String mdp) throws NoSuchAlgorithmException;
     public List<Entrepreneur> rechercherEntrepreneur(Entrepreneur e) throws SQLException;
    
}
