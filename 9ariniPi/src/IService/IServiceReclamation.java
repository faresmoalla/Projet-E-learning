package IService;

import entities.Reclamation;
import entities.Utilisateur;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aminous
 */
public interface IServiceReclamation {
    
    public void ajouterReclamation(Reclamation r) throws SQLException;

    public List<Reclamation> getAllReclamation() throws SQLException;

    public void supprimerReclamationbyId(int reclamationid) throws SQLException;

    public List<Reclamation> getAllReclamationParId(int utilisateurId) throws SQLException;

    public void updatereclamation(int idR, String newMsg);
     
    public void supprimerReclamation(Reclamation r) throws SQLException;

    public List<Reclamation> recupererReclamations(Utilisateur p) throws SQLException;
    
}
