/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;
import entities.Ressources;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author fares
 */
public interface IServiceRessources {
           public void ajouterProduitRessources(Ressources r) throws SQLException;
               public List<Ressources> getAllRessources() throws SQLException ;
    public void supprimerRessources(Ressources c) throws SQLException ;
                        public  void ModifierRessources(Ressources rs ,int ressourceID) ;
}
