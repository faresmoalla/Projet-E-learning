package IService;

import java.sql.SQLException;
import entities.Panier;
import entities.Utilisateur;

/**
 *
 * @author Aminous
 */
public interface IServicePanier {

    public int ajouterPanier(Panier pn) throws SQLException;

    public void modifierPanier(Panier pn, Panier pn2) throws SQLException;

    public void supprimerPanier(Panier pn) throws SQLException;

    public Panier recupererPanier(Utilisateur p) throws SQLException;

}
