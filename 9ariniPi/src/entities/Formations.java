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
public class Formations {
   private int formationID ;
   private int utilisateurID;
   private String nomFormation;

    public Formations() {
    }

    public Formations(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public Formations(int utilisateurID, String nomFormation) {
        this.utilisateurID = utilisateurID;
        this.nomFormation = nomFormation;
    }

    public int getFormationID() {
        return formationID;
    }

    public void setFormationID(int formationID) {
        this.formationID = formationID;
    }

    public int getUtilisateurID() {
        return utilisateurID;
    }

    public void setUtilisateurID(int utilisateurID) {
        this.utilisateurID = utilisateurID;
    }

    public String getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public Formations(int formationID, int utilisateurID, String nomFormation) {
        this.formationID = formationID;
        this.utilisateurID = utilisateurID;
        this.nomFormation = nomFormation;
    }

    @Override
    public String toString() {
        return "Formations{" + "formationID=" + formationID + ", utilisateurID=" + utilisateurID + ", nomFormation=" + nomFormation + '}';
    }
   
   
   
}
