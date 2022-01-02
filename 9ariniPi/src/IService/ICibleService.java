/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import java.sql.SQLException;
import java.util.Date;
import entities.Cible;

/**
 *
 * @author pc-asus
 */
public interface ICibleService {
    public void ajouterCible(Cible c) throws SQLException;
    public int ModifierCible(int c) throws SQLException;
    public int testCible() throws SQLException;
    public Cible rechercheCible() throws SQLException;

    /**
     *
     * @param c
     * @return
     */
    public int  AjoutPrix(Cible c);
  
    
}
