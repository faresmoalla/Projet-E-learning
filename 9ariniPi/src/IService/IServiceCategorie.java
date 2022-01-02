/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;
import entities.Categorie;
import entities.Cours;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;
/**
 *
 * @author fares
 */
public interface IServiceCategorie {
    public void ajouterProduitCategorie(Categorie c) throws SQLException ;
    public List<Categorie> getAllCategorie() throws SQLException;
    public void supprimerCategorie(Categorie c) throws SQLException ;
    public void ModifierCategorie(Categorie c);
     public List<Categorie> afficherCategorie() throws SQLException;
//public  void  supprimerCategoriebyId(int id) throws SQLException;
    public ObservableList<Categorie> getAll() throws SQLException;
     public Categorie findCategorieById(int id) throws SQLException;
      public void edit(Categorie c)throws SQLException;
      public List<String> getAllCategorieNom() throws SQLException;
      
}
