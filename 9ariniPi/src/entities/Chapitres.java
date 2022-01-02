/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author fares
 */
public class Chapitres extends Cours {

    private int id;
    private int cours_id ;
    private String videoChapitre;
    private String chapitreNom;

    public Chapitres() {
    }

    public Chapitres(String chapitreNom) {
        this.chapitreNom = chapitreNom;
    }

   /* public Chapitres(String videoChapitre) {
        this.videoChapitre = videoChapitre;
    }*/
   

    public Chapitres(int id, int cours_id , String chapitreNom, String videoChapitre) {
        this.id = id;
        this.cours_id  = cours_id ;
        this.videoChapitre = videoChapitre;
        this.chapitreNom = chapitreNom;
    }

    public Chapitres(String chapitreNom, String videoChapitre) {
        this.chapitreNom = chapitreNom;
        this.videoChapitre = videoChapitre;
    }

    public Chapitres(int cours_id , String chapitreNom, String videoChapitre) {
        this.cours_id  = cours_id ;
        this.videoChapitre = videoChapitre;
        this.chapitreNom = chapitreNom;

    }

    public String getChapitreNom() {
        return chapitreNom;
    }

    public void setChapitreNom(String chapitreNom) {
        this.chapitreNom = chapitreNom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCours_id () {
        return cours_id ;
    }

    public void setCours_id(int cours_id ) {
        this.cours_id  = cours_id ;
    }

    public String getVideoChapitre() {
        return videoChapitre;
    }
  public void setVideoChapitre(String videoChapitre) {
        this.videoChapitre = videoChapitre;
    }
   

    @Override
    public String toString() {
        return "Chapitres{" + "id=" + id + ", cours_id =" + cours_id  + ", videoChapitre=" + videoChapitre + ", chapitreNom=" + chapitreNom + '}';
    }

 
}
