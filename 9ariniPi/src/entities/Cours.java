/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fares
 */
public class Cours {

    private int id;
    private String utilisateurNom;
    private String nomCours;
    private int nbrChapitres;
    private String description;
         private String coursImg;
    private int categorie_id;
    List<Chapitres> ch;
    List<Ressources> res;   
    public Cours() {
    }
    public Cours(String utilisateurNom, String nomCours, int nbrChapitres,String description,int categorie_id) {
        this.utilisateurNom = utilisateurNom;
        this.nomCours = nomCours;
        this.nbrChapitres = nbrChapitres;
                this.description=description;

        this.categorie_id=categorie_id;
            ch  = new ArrayList<Chapitres>();
    res  = new ArrayList<Ressources>();

    }
    
    
    public Cours(String utilisateurNom, String nomCours, int nbrChapitres, String description, String coursImg, int categorie_id) {
        this.utilisateurNom = utilisateurNom;
        this.nomCours = nomCours;
        this.nbrChapitres = nbrChapitres;
        this.description = description;
        this.coursImg = coursImg;
        this.categorie_id = categorie_id;
    }

    public Cours(int id, String utilisateurNom, String nomCours, int nbrChapitres, String description, String coursImg, int categorie_id) {
        this.id = id;
        this.utilisateurNom = utilisateurNom;
        this.nomCours = nomCours;
        this.nbrChapitres = nbrChapitres;
        this.description = description;
        this.coursImg = coursImg;
        this.categorie_id = categorie_id;
    }

    public String getCoursImg() {
        return coursImg;
    }

    public void setCoursImg(String coursImg) {
        this.coursImg = coursImg;
    }

    public Cours(String nomCours) {
        this.nomCours = nomCours;
    }
  public Cours(int id) {
        this.id = id;
    }
    public Cours(int id, String utilisateurNom, String nomCours, int nbrChapitres,String description,int categorie_id) {
        this.id = id;
        this.utilisateurNom = utilisateurNom;
        this.nomCours = nomCours;
        this.nbrChapitres = nbrChapitres;
                this.description=description;
        this.categorie_id=categorie_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getUtilisateurNom() {
        return utilisateurNom;
    }

    public void setUtilisateurNom(String utilisateurNom) {
        this.utilisateurNom = utilisateurNom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public int getNbrChapitres() {
        return nbrChapitres;
    }

    public void setNbrChapitres(int nbrChapitres) {
        this.nbrChapitres = nbrChapitres;
    }

    @Override
    public String toString() {
        return "Cours{" + "id=" + id + ", utilisateurNom=" + utilisateurNom + ", nomCours=" + nomCours + ", nbrChapitres=" + nbrChapitres + ", description=" + description + ", categorie_id=" + categorie_id + '}';
    }

    public Cours(String utilisateurNom, String nomCours, int nbrChapitres, String description) {
        this.utilisateurNom = utilisateurNom;
        this.nomCours = nomCours;
        this.nbrChapitres = nbrChapitres;
        this.description = description;
    }

 



    

  

}
