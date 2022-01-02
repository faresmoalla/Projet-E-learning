/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import entities.Categorie;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import entities.Publicite;


/**
 *
 * @author pc-asus
 */
public interface IPubliciteService {
    
   public  void ajouterPublicite(Publicite p) throws SQLException;
   
   public    List<Publicite> affichertab() throws SQLException;
   
   public void modifierPublicite(Publicite p) throws SQLException, NoSuchAlgorithmException;
   
   public void supprimerPublicite(Publicite p) throws SQLException;
   
 public void Modifier2(Publicite p);
  // public List<Publicite> AfficheCategoriePub(Categorie c) throws SQLException;
  

    
}
