/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import IService.IserviceEntrpreneur;
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
import entities.Entrepreneur;
import entities.Utilisateur;
import DB.DB;
/**
 *
 * @author bhk
 */
public class EntrepreneurService implements IserviceEntrpreneur{

    Connection connexion;

    public EntrepreneurService()  {
        connexion = DB.getInstance().getConnection();
    }
    @Override
    public void ajouterEntrepreneur(Entrepreneur p) throws SQLException, NoSuchAlgorithmException {
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
     public void modifierEntrepreneur(Entrepreneur p,Entrepreneur p1) throws SQLException, NoSuchAlgorithmException {
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
     public void supprimerEntrepreneur(Entrepreneur p) throws SQLException {

        String req = "DELETE FROM utilisateur WHERE id ="+p.getId()+"";
     
         PreparedStatement ps = connexion.prepareStatement(req);
        ps.executeUpdate();
     
    }  
       @Override
    public List<Entrepreneur> getAllEntrepreneur() throws SQLException {

        List<Entrepreneur> presonnes = new ArrayList<>();
        String req = "select * from utilisateur  where  utilisateurRole='Entrepreneur'";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Entrepreneur p = new Entrepreneur(rst.getInt("id")
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
                    , rst.getString("nomEntreprise")
                    , rst.getString("EntrepreneurSiteWeb")
                    , rst.getString("EntrepreneurUsage")
                    , rst.getDate("utilisateurDDN"));
            presonnes.add(p);
        }
        return presonnes;
    }
    @Override
    public String hashmdp (String mdp) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        StringBuffer hexString = new StringBuffer();
     for (int i=0;i<byteData.length;i++) {
      String hex=Integer.toHexString(0xff & byteData[i]);
          if(hex.length()==1) hexString.append('0');
          hexString.append(hex);
     }
     
    
       return hexString.toString();
    }
    @Override
    public List<Entrepreneur> rechercherEntrepreneur(Entrepreneur e) throws SQLException {
        List<Entrepreneur> presonnes = new ArrayList<>();
        String req = "select * from utilisateur WHERE utilisateurPrenom LIKE '"+e.getUtilisateurPrenom()+"'or utilisateurNom LIKE '"+e.getUtilisateurNom()+"'";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        //Utilisateur p = null;
        while (rst.next()) {
            Entrepreneur p = new Entrepreneur(
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
                    , rst.getString("nomEntreprise")
                    , rst.getString("EntrepreneurSiteWeb")
                    , rst.getString("EntrepreneurUsage")
                    , rst.getDate("utilisateurDDN"));
            presonnes.add(p);
        }   
        return presonnes ;
    
    }
}
