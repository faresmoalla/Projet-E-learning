 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;






/**
 *
 * @author pc-asus
 */
public class Publicite {
   private int id ;
   private String video_publicite ;
   
 
   private String lien_publicite  ;
 
   private int nbr_click  ;
   private int nbr_affichage;
   private String image_publicite ;
   private int cible_id;

    public int getCible_id() {
        return cible_id;
    }

    public void setCible_id(int cible_id) {
        this.cible_id = cible_id;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideo_publicite() {
        return video_publicite;
    }

    public void setVideo_publicite(String video_publicite) {
        this.video_publicite = video_publicite;
    }

    public Publicite(int id, String video_publicite, String lien_publicite, String image_publicite) {
        this.id = id;
        this.video_publicite = video_publicite;
        this.lien_publicite = lien_publicite;
        this.image_publicite = image_publicite;
    }

   

  

    

   

    public String getLien_publicite() {
        return lien_publicite;
    }

    public void setLien_publicite(String lien_publicite) {
        this.lien_publicite = lien_publicite;
    }

    public int getNbr_click() {
        return nbr_click;
    }

    public void setNbr_click(int nbr_click) {
        this.nbr_click = nbr_click;
    }

    public int getNbr_affichage() {
        return nbr_affichage;
    }

    public void setNbr_affichage(int nbr_affichage) {
        this.nbr_affichage = nbr_affichage;
    }

    public String getImage_publicite() {
        return image_publicite;
    }

    public void setImage_publicite(String imagePublicite) {
        this.image_publicite = imagePublicite;
    }

    public Publicite() {
    }

   

   
    ///////////////////////////////////

    public Publicite(String video_publicite, String lien_publicite, String image_publicite,int cible_id) {
        this.video_publicite = video_publicite;
        this.lien_publicite = lien_publicite;
        this.image_publicite = image_publicite;
        this.cible_id=cible_id;
    }
    public Publicite(String video_publicite, String lien_publicite, String image_publicite) {
       
        this.video_publicite = video_publicite;
        this.lien_publicite = lien_publicite;
        this.image_publicite = image_publicite;
        
    }
    public Publicite(String video_publicite) {
        this.video_publicite = video_publicite;
    }

    public Publicite(int id, String video_publicite,  String lien_publicite, int nbr_click, int nbr_affichage, String image_publicite) {
        this.id = id;
        this.video_publicite = video_publicite;
       
        this.lien_publicite = lien_publicite;
        this.nbr_click = nbr_click;
        this.nbr_affichage = nbr_affichage;
        this.image_publicite = image_publicite;
    }

    public Publicite(String video_publicite, String lien_publicite, int nbr_click, int nbr_affichage, String image_publicite) {
        this.video_publicite = video_publicite;
       
        this.lien_publicite = lien_publicite;
        this.nbr_click = nbr_click;
        this.nbr_affichage = nbr_affichage;
        this.image_publicite = image_publicite;
    }

    public Publicite(int id, String genre, int nbr_click, int nbr_affichage) {
        this.id = id;
       
        this.nbr_click = nbr_click;
        this.nbr_affichage = nbr_affichage;
    }

  
    

    @Override
    public String toString() {
        return "Publicite{" + "video_publicite=" + video_publicite + ", lien_publicite=" + lien_publicite + ", image_publicite=" + image_publicite + '}';
    }

   
    
    
}
