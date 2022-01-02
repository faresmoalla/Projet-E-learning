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
public class Formateur extends Utilisateur {

    public Formateur(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String utilisateurSoftskills, Date utilisateurDDN) {
        super(utilisateurphone, utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurMDP, utilisateurRole, utilisateurOrganisme, utilisateurFonction, utilisateurSoftskills, utilisateurDDN);
    }

    public Formateur(int utilisateurID, int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String utilisateurSoftskills, Date utilisateurDDN) {
        super(utilisateurID, utilisateurphone, utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurMDP, utilisateurRole, utilisateurOrganisme, utilisateurFonction, utilisateurSoftskills, utilisateurDDN);
    }

    public Formateur(String utilisateurNom, String utilisateurPrenom) {
        super(utilisateurNom, utilisateurPrenom);
    }

    public Formateur(int utilisateurID, int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String utilisateurSoftskills) {
        super(utilisateurID, utilisateurphone, utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurRole, utilisateurOrganisme, utilisateurFonction, utilisateurSoftskills);
    }

    public Formateur(int utilisateurID, String utilisateurPdp, int utilisateurphone, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String utilisateurSoftskills, Date utilisateurDDN) {
        super(utilisateurID, utilisateurPdp, utilisateurphone, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurRole, utilisateurOrganisme, utilisateurFonction, utilisateurSoftskills, utilisateurDDN);
    }

    public Formateur(String utilisateurPdp, int utilisateurphone, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurSoftskills, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, Date utilisateurDDN) {
        super(utilisateurPdp, utilisateurphone, utilisateurNom, utilisateurPrenom, utilisateurAddress, utilisateurPays, utilisateurGenre, utilisateurAddressEmail, utilisateurSoftskills, utilisateurRole, utilisateurOrganisme, utilisateurFonction, utilisateurDDN);
    }

    public Formateur() {
    }

    public Formateur(String utilisateurNom) {
        super(utilisateurNom);
    }

    

    

    

   
    

  
    
    
}
