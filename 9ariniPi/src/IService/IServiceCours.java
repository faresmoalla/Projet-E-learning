/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

/**
 *
 * @author fares
 */
import entities.Commande;
import entities.Cours;
import java.sql.SQLException;
import java.util.List;
public interface IServiceCours {
    public void ajouterProduitCours(Cours c) throws SQLException ;
    public List<Cours> getAllCours() throws SQLException ;
     public  void ModifierCours(Cours c ) ;      
    public void supprimerCours(Cours c) throws SQLException;
        public List<Cours> afficherCours() throws SQLException ;
public void editCours(Cours c) throws SQLException;
    public Cours rechercherCours(Commande cde) throws SQLException;

}
