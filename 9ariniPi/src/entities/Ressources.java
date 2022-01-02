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
public class Ressources {
   private int ressourceID;
   private int coursID;
   private String ressourceNom;

    public Ressources() {
    }

    public Ressources(int ressourceID, int chapitreID,String ressourceNom) {
        this.ressourceID = ressourceID;
        this.coursID = coursID;
        this.ressourceNom=ressourceNom;
    }

    public Ressources(int coursID, String ressourceNom) {
        this.coursID = coursID;
        this.ressourceNom = ressourceNom;
    }

    public Ressources(int coursID) {
        this.coursID = coursID;
        this.ressourceNom=ressourceNom;
    }

    public String getRessourceNom() {
        return ressourceNom;
    }

    public void setRessourceNom(String ressourceNom) {
        this.ressourceNom = ressourceNom;
    }

    public int getRessourceID() {
        return ressourceID;
    }

    public void setRessourceID(int ressourceID) {
        this.ressourceID = ressourceID;
    }

    public int getCoursID() {
        return coursID;
    }

    public void setCoursID(int coursID) {
        this.coursID = coursID;
    }

    @Override
    public String toString() {
        return "Ressources{" + "ressourceID=" + ressourceID + ", coursID=" + coursID + '}';
    }
   
   
}
