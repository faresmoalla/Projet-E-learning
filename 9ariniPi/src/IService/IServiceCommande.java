package IService;

import java.sql.SQLException;
import java.util.List;
import entities.Commande;
import entities.Panier;
import entities.Membre;

/**
 *
 * @author Aminous
 */
public interface IServiceCommande {

    public Panier ajouterCommande(Commande cde, Membre p) throws SQLException;

    public void supprimerCommande(Commande cde) throws SQLException;

    public List<Commande> recupererCommandes(Panier pn) throws SQLException;
    
   public Commande premiereCommande(Panier pn) throws SQLException;
    
    public int nombreTotalCommandes(Panier pn) throws SQLException;
}
