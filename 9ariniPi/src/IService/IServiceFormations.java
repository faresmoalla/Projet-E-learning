/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;
import entities.Formations;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author fares
 */
public interface IServiceFormations {
 
    public void ajouterProduitFormation(Formations f) throws SQLException ;
    public List<Formations> getAllFormations() throws SQLException ;
         public  void ModifierFormations(Formations f ,int formationID) ;    
              public void supprimerFormations(Formations f) throws SQLException;
                      //  public void supprimerFormationsbyId(int formationID  ) throws SQLException;

}
