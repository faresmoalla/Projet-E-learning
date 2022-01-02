/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import IService.IserviceAdmin;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import entities.Admin;
import entities.Utilisateur;
import DB.DB;
import entities.Membre;
/**
 *
 * @author bhk
 */
public class AdminService implements IserviceAdmin{

    Connection connexion;
    private MembreService ms;

    public void ajouterMembre(Membre p) throws SQLException, NoSuchAlgorithmException {
        ms.ajouterMembre(p);
    }

    public void modifierMembre(Membre p, Membre p1) throws SQLException, NoSuchAlgorithmException {
        ms.modifierMembre(p, p1);
    }
    

    public AdminService()  {
        connexion = DB.getInstance().getConnection();
    }
    @Override
    public void ajouterAdmin(Admin p) throws SQLException, NoSuchAlgorithmException {
        String req = "INSERT INTO `utilisateur` (`utilisateurPdp`,`utilisateurphone`, `utilisateurNom`,`utilisateurPrenom`,`utilisateurAdresse`,"
                + "`utilisateurPays`,`utilisateurDDN`,`utilisateurGenre`,`utilisateurAdresseEmail`,"
                + "`utilisateurMDP`,`utilisateurRole`,`utilisateurOrganisme`,`utilisateurFonction`,"
                + "`utilisateurSavoirEtre`,`nomEntreprise`,`EntrepreneurSiteWeb`,`EntrepreneurUsage`) VALUES ( '"
                + p.getUtilisateurPdp() + "', '" + p.getUtilisateurphone() + "', '" + p.getUtilisateurNom() + "','"+p.getUtilisateurPrenom()+"','"+p.getUtilisateurAddress()+"'"
                + ",'"+p.getUtilisateurPays()+"','"+p.getUtilisateurDDN()+"','"+p.getUtilisateurGenre()+"',"
                + "'"+p.getUtilisateurAddressEmail()+"','"+hashmdp(p.getUtilisateurMDP())+"','"+p.getUtilisateurRole()+"'"
                + ",'"+p.getUtilisateurOrganisme()+"','"+p.getUtilisateurFonction()+"','"+p.getUtilisateurSoftskills()+"','"+p.getNomEntreprise()+"',"
                + "'"+p.getEntrepreneurSiteWeb()+"','"+p.getEntrepreneurUsage()+"') ";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    @Override
     public void modifierAdmin(Admin p,Admin p1) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE Utilisateur SET "
                + " utilisateurPdp='"+p.getUtilisateurPdp()+"'"
                + ", utilisateurphone='"+p.getUtilisateurphone()+"'"
                + ", utilisateurNom='"+p.getUtilisateurNom()+"'"
                + ", utilisateurPrenom='"+p.getUtilisateurPrenom()+"'" 
                + ", utilisateurAdresse='"+p.getUtilisateurAddress()+"'"
                + ", utilisateurPays='"+p.getUtilisateurPays()+"'"
                + ", utilisateurDDN='"+p.getUtilisateurDDN()+"'"
                + ", utilisateurGenre='"+p.getUtilisateurGenre()+"'"
                + ", utilisateurAdresseEmail='"+p.getUtilisateurAddressEmail()+"'"
                + ", utilisateurMDP='"+hashmdp(p.getUtilisateurMDP())+"'"
                + ", utilisateurRole='"+p.getUtilisateurRole()+"'"
                + ", utilisateurOrganisme='"+p.getUtilisateurOrganisme()+"'"
                + ", utilisateurFonction='"+p.getUtilisateurFonction()+"'"
                + ", utilisateurSavoirEtre='"+p.getUtilisateurSoftskills()+"'"
                + ", nomEntreprise='"+p.getNomEntreprise()+"'"
                + ", EntrepreneurSiteWeb='"+p.getEntrepreneurSiteWeb()+"'"
                + ", EntrepreneurUsage='"+p.getEntrepreneurUsage()+"' where id = "+p1.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
     @Override
     public void supprimerAdmin(Admin p) throws SQLException {

        String req = "DELETE FROM utilisateur WHERE id ="+p.getId()+"";
     
         PreparedStatement ps = connexion.prepareStatement(req);
        ps.executeUpdate();
     
    }  
    /*
     public void modifierUtilisateur(Utilisateur p,int id) throws SQLException {
        String req = "\"UPDATE  `utilsateur` SET (`utilisateurPdp`, `utilisateurNom`,`utilisateurPrenom`,`utilisateurAddress`,"
                + "`utilisateurPays`,`utilisateurDDN`,`utilisateurGenre`,`utilisateurAddressEmail`,\"\n" +
"                + \"`utilisateurMDP`,`utilisateurRole`,`utilisateurOrganisme`,`utilisateurFonction`,"
                + "`utilisateurSoftskills`,`nomEntreprise`,`EntrepreneurSiteWeb`,`EntrepreneurUsage`)"
                + "VALUES ( ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) where `id`=id ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, p.getUtilisateurPdp());
        ps.setString(2, p.getUtilisateurNom());
        ps.setString(3, p.getUtilisateurPrenom());
        ps.setString(4, p.getUtilisateurAddress());
        ps.setString(5, p.getUtilisateurPays());
        ps.setString(6, p.getUtilisateurGenre());
        ps.setString(7, p.getUtilisateurAddressEmail());
        ps.setString(8, p.getUtilisateurMDP());
        ps.setString(9, p.getUtilisateurRole());
        ps.setString(10, p.getUtilisateurOrganisme());
        ps.setString(11, p.getUtilisateurFonction());
        ps.setString(12, p.getUtilisateurSoftskills());
        ps.setString(13, p.getNomEntreprise());
        ps.setString(14, p.getEntrepreneurSiteWeb());
        ps.setString(15, p.getEntrepreneurUsage());
        ps.setDate(16, (Date) p.getUtilisateurDDN());
        ps.executeUpdate();
    }
    */
     @Override
    public List<Admin> getAllAdmin() throws SQLException {

        List<Admin> presonnes = new ArrayList<>();
        String req = "select * from utilisateur  where  utilisateurRole='Admin'";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Admin p = new Admin(rst.getInt("id")
                    , rst.getInt("utilisateurphone")
                    , rst.getString("utilisateurPdp")
                    , rst.getString("utilisateurNom")
                    , rst.getString("utilisateurPrenom")
                    , rst.getString("utilisateurAdresse")
                    , rst.getString("utilisateurPays")
                    , rst.getString("utilisateurGenre")
                    , rst.getString("utilisateurAdresseEmail")
                    , rst.getString("utilisateurMDP")
                    , rst.getString("utilisateurRole")
                    , rst.getString("utilisateurOrganisme")
                    , rst.getString("utilisateurFonction")
                    , rst.getString("utilisateurSavoirEtre")
                    , rst.getString("nomEntreprise")
                    , rst.getString("EntrepreneurSiteWeb")
                    , rst.getString("EntrepreneurUsage")
                    , rst.getDate("utilisateurDDN"));
            presonnes.add(p);
        }
        return presonnes;
    }
    
    @Override
    public List<Admin> rechercherAdmin(Admin a) throws SQLException {
        List<Admin> presonnes = new ArrayList<>();
        String req = "select * from utilisateur WHERE utilisateurPrenom LIKE '"+a.getUtilisateurPrenom()+"'or utilisateurNom LIKE '"+a.getUtilisateurNom()+"'";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        //Utilisateur p = null;
        while (rst.next()) {
            Admin p = new Admin(
                      rst.getInt("id")
                    , rst.getInt("utilisateurphone")
                    , rst.getString("utilisateurPdp")
                    , rst.getString("utilisateurNom")
                    , rst.getString("utilisateurPrenom")
                    , rst.getString("utilisateurAdresse")
                    , rst.getString("utilisateurPays")
                    , rst.getString("utilisateurGenre")
                    , rst.getString("utilisateurAdresseEmail")
                    , rst.getString("utilisateurMDP")
                    , rst.getString("utilisateurRole")
                    , rst.getString("utilisateurOrganisme")
                    , rst.getString("utilisateurFonction")
                    , rst.getString("utilisateurSavoirEtre")
                    , rst.getString("nomEntreprise")
                    , rst.getString("EntrepreneurSiteWeb")
                    , rst.getString("EntrepreneurUsage")
                    , rst.getDate("utilisateurDDN"));
            presonnes.add(p);
        }
        return presonnes ;
    
    }
    @Override
    public void modifierAdminMembre(Admin p, Utilisateur u) throws NoSuchAlgorithmException, SQLException {
        String req = "UPDATE Utilisateur SET "
                + " utilisateurPdp='"+p.getUtilisateurPdp()+"'"
                + ", utilisateurphone='"+p.getUtilisateurphone()+"'"
                + ", utilisateurNom='"+p.getUtilisateurNom()+"'"
                + ", utilisateurPrenom='"+p.getUtilisateurPrenom()+"'" 
                + ", utilisateurAdresse='"+p.getUtilisateurAddress()+"'"
                + ", utilisateurPays='"+p.getUtilisateurPays()+"'"
                + ", utilisateurDDN='"+p.getUtilisateurDDN()+"'"
                + ", utilisateurGenre='"+p.getUtilisateurGenre()+"'"
                + ", utilisateurAdresseEmail='"+p.getUtilisateurAddressEmail()+"'"
                
                + ", utilisateurRole='"+p.getUtilisateurRole()+"'"
                + ", utilisateurOrganisme='"+p.getUtilisateurOrganisme()+"'"
                + ", utilisateurFonction='"+p.getUtilisateurFonction()+"'"
                + ", utilisateurSavoirEtre='"+p.getUtilisateurSoftskills()+"'"
                + ", nomEntreprise='"+p.getNomEntreprise()+"'"
                + ", EntrepreneurSiteWeb='"+p.getEntrepreneurSiteWeb()+"'"
                + ", EntrepreneurUsage='"+p.getEntrepreneurUsage()+"' where id = "+u.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    @Override
    public void modifierAdminFormateur(Admin p, Utilisateur u) throws NoSuchAlgorithmException, SQLException{String req = "UPDATE Utilisateur SET "
                + " utilisateurPdp='"+p.getUtilisateurPdp()+"'"
                + ", utilisateurphone='"+p.getUtilisateurphone()+"'"
                + ", utilisateurNom='"+p.getUtilisateurNom()+"'"
                + ", utilisateurPrenom='"+p.getUtilisateurPrenom()+"'" 
                + ", utilisateurAdresse='"+p.getUtilisateurAddress()+"'"
                + ", utilisateurPays='"+p.getUtilisateurPays()+"'"
                + ", utilisateurDDN='"+p.getUtilisateurDDN()+"'"
                + ", utilisateurGenre='"+p.getUtilisateurGenre()+"'"
                + ", utilisateurAdresseEmail='"+p.getUtilisateurAddressEmail()+"'"
              
                + ", utilisateurRole='"+p.getUtilisateurRole()+"'"
                + ", utilisateurOrganisme='"+p.getUtilisateurOrganisme()+"'"
                + ", utilisateurFonction='"+p.getUtilisateurFonction()+"'"
                + ", utilisateurSavoirEtre='"+p.getUtilisateurSoftskills()+"'"
                + ", nomEntreprise='"+p.getNomEntreprise()+"'"
                + ", EntrepreneurSiteWeb='"+p.getEntrepreneurSiteWeb()+"'"
                + ", EntrepreneurUsage='"+p.getEntrepreneurUsage()+"' where id = "+u.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);}
    @Override
    public void modifierAdminEntrepreneur(Admin p, Utilisateur u) throws NoSuchAlgorithmException, SQLException{String req = "UPDATE Utilisateur SET "
                + " utilisateurPdp='"+p.getUtilisateurPdp()+"'"
                + ", utilisateurphone='"+p.getUtilisateurphone()+"'"
                + ", utilisateurNom='"+p.getUtilisateurNom()+"'"
                + ", utilisateurPrenom='"+p.getUtilisateurPrenom()+"'" 
                + ", utilisateurAdresse='"+p.getUtilisateurAddress()+"'"
                + ", utilisateurPays='"+p.getUtilisateurPays()+"'"
                + ", utilisateurDDN='"+p.getUtilisateurDDN()+"'"
                + ", utilisateurGenre='"+p.getUtilisateurGenre()+"'"
                + ", utilisateurAdresseEmail='"+p.getUtilisateurAddressEmail()+"'"
              
                + ", utilisateurRole='"+p.getUtilisateurRole()+"'"
                + ", utilisateurOrganisme='"+p.getUtilisateurOrganisme()+"'"
                + ", utilisateurFonction='"+p.getUtilisateurFonction()+"'"
                + ", utilisateurSavoirEtre='"+p.getUtilisateurSoftskills()+"'"
                + ", nomEntreprise='"+p.getNomEntreprise()+"'"
                + ", EntrepreneurSiteWeb='"+p.getEntrepreneurSiteWeb()+"'"
                + ", EntrepreneurUsage='"+p.getEntrepreneurUsage()+"' where id = "+u.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);}

 @Override
    public String hashmdp (String mdp) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
     for (int i=0;i<byteData.length;i++) {
      String hex=Integer.toHexString(0xff & byteData[i]);
          if(hex.length()==1) hexString.append('0');
          hexString.append(hex);
     }
     
    
       return hexString.toString();
    }
}
