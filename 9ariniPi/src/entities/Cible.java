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
public class Cible {
    private int id;
     private String Genre ;
     private   int age_min ;
     private   int age_max ;
     

     private Date date_debut;
     private Date date_fin ;
     private   int  prix  ;

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public int getAge_min() {
        return age_min;
    }

    public void setAge_min(int age_min) {
        this.age_min = age_min;
    }

    public int getAge_max() {
        return age_max;
    }

    public void setAge_max(int age_max) {
        this.age_max = age_max;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Cible() {
    }

    public Cible(String Genre, int age_min, int age_max, Date date_debut, Date date_fin) {
        this.Genre = Genre;
        this.age_min = age_min;
        this.age_max = age_max;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Cible(String Genre, int age_min, int age_max, Date date_debut, Date date_fin, int prix) {
        this.Genre = Genre;
        this.age_min = age_min;
        this.age_max = age_max;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cible(int id) {
        this.id = id;
    }

 

    public Cible(String Genre, int age_min, int age_max) {
        this.Genre = Genre;
        this.age_min = age_min;
        this.age_max = age_max;
        
    }
     
     
     

 

   
}
