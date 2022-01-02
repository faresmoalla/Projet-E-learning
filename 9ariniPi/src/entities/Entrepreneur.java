/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author lahbib
 */
public class Entrepreneur extends Utilisateur {

    public Entrepreneur(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String nomEntreprise, String EntrepreneurSiteWeb, String EntrepreneurUsage, Date utilisateurDDN) {
        super(utilisateurphone, utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurMDP, utilisateurRole, utilisateurOrganisme, utilisateurFonction, nomEntreprise, EntrepreneurSiteWeb, EntrepreneurUsage, utilisateurDDN);
    }

    public Entrepreneur(int utilisateurID, int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String nomEntreprise, String EntrepreneurSiteWeb, String EntrepreneurUsage, Date utilisateurDDN) {
        super(utilisateurID, utilisateurphone, utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurMDP, utilisateurRole, utilisateurOrganisme, utilisateurFonction, nomEntreprise, EntrepreneurSiteWeb, EntrepreneurUsage, utilisateurDDN);
    }

    public Entrepreneur(String utilisateurNom, String utilisateurPrenom) {
        super(utilisateurNom, utilisateurPrenom);
    }

    public Entrepreneur() {
    }

    public Entrepreneur(int utilisateurID, Date utilisateurDDN, String utilisateurPdp, int utilisateurphone, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String nomEntreprise, String utilisateurRole, String EntrepreneurSiteWeb, String EntrepreneurUsage) {
        super(utilisateurID, utilisateurDDN, utilisateurPdp, utilisateurphone, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, nomEntreprise, utilisateurRole, EntrepreneurSiteWeb, EntrepreneurUsage);
    }

    public Entrepreneur(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String nomEntreprise, String EntrepreneurSiteWeb, String EntrepreneurUsage, Date utilisateurDDN) {
        super(utilisateurphone, utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurRole, utilisateurOrganisme, utilisateurFonction, nomEntreprise, EntrepreneurSiteWeb, EntrepreneurUsage, utilisateurDDN);
    }

    public Entrepreneur(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
  

    

    

}
