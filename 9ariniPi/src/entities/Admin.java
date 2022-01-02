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
public class Admin extends Utilisateur {

    public Admin(String utilisateurPdp, int utilisateurphone, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String utilisateurSoftskills, String nomEntreprise, String EntrepreneurSiteWeb, String EntrepreneurUsage, Date utilisateurDDN) {
        super(utilisateurPdp, utilisateurphone, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurRole, utilisateurOrganisme, utilisateurFonction, utilisateurSoftskills, nomEntreprise, EntrepreneurSiteWeb, EntrepreneurUsage, utilisateurDDN);
    }

    public Admin(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String utilisateurSoftskills, String nomEntreprise, String EntrepreneurSiteWeb, String EntrepreneurUsage, Date utilisateurDDN) {
        super(utilisateurphone, utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurMDP, utilisateurRole, utilisateurOrganisme, utilisateurFonction, utilisateurSoftskills, nomEntreprise, EntrepreneurSiteWeb, EntrepreneurUsage, utilisateurDDN);
    }

    public Admin(int id, int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurRole, Date utilisateurDDN) {
        super(id, utilisateurphone, utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurRole, utilisateurDDN);
    }

    public Admin(int id, int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurRole) {
        super(id, utilisateurphone, utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurRole);
    }

    public Admin(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String nomEntreprise, String EntrepreneurSiteWeb, String EntrepreneurUsage, Date utilisateurDDN) {
        super(utilisateurphone, utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurMDP, utilisateurRole, utilisateurOrganisme, utilisateurFonction, nomEntreprise, EntrepreneurSiteWeb, EntrepreneurUsage, utilisateurDDN);
    }

    public Admin(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String utilisateurSoftskills, Date utilisateurDDN) {
        super(utilisateurphone, utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurMDP, utilisateurRole, utilisateurOrganisme, utilisateurFonction, utilisateurSoftskills, utilisateurDDN);
    }

    public Admin(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, Date utilisateurDDN) {
        super(utilisateurphone, utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurMDP, utilisateurRole, utilisateurOrganisme, utilisateurFonction, utilisateurDDN);
    }

    public Admin(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole) {
        super(utilisateurphone, utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurMDP, utilisateurRole);
    }

    public Admin(int id, int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String utilisateurSoftskills, String nomEntreprise, String EntrepreneurSiteWeb, String EntrepreneurUsage, Date utilisateurDDN) {
        super(id, utilisateurphone, utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurMDP, utilisateurRole, utilisateurOrganisme, utilisateurFonction, utilisateurSoftskills, nomEntreprise, EntrepreneurSiteWeb, EntrepreneurUsage, utilisateurDDN);
    }

    public Admin(String utilisateurNom, String utilisateurPrenom) {
        super(utilisateurNom, utilisateurPrenom);
    }

    public Admin(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, Date utilisateurDDN) {
        super(utilisateurphone, utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurMDP, utilisateurRole, utilisateurDDN);
    }

    public Admin() {
    }

  
    
}
