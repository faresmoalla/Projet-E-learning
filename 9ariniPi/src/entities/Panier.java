package entities;

import java.sql.Date;

/**
 *
 * @author Aminous
 */
public class Panier {

    private int panierID, utilisateurID;
    private Date datePanier;
    private String etatPanier;
    
    public Panier() {
    }

    public Panier(Date datePanier, String etatPanier) {
        this.datePanier = datePanier;
        this.etatPanier = etatPanier;
    }

    public Panier(int utilisateurID, Date datePanier, String etatPanier) {
        this.utilisateurID = utilisateurID;
        this.datePanier = datePanier;
        this.etatPanier = etatPanier;
    }

    public Panier(int panierID, int utilisateurID, Date datePanier, String etatPanier) {
        this.panierID = panierID;
        this.utilisateurID = utilisateurID;
        this.datePanier = datePanier;
        this.etatPanier = etatPanier;
    }

    public static Date getCurrentDate() {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    public int getPanierID() {
        return panierID;
    }

    public void setPanierID(int panierID) {
        this.panierID = panierID;
    }

    public int getUtilisateurID() {
        return utilisateurID;
    }

    public void setUtilisateurID(int utilisateurID) {
        this.utilisateurID = utilisateurID;
    }

    public Date getDatePanier() {
        return datePanier;
    }

    public void setDatePanier(Date datePanier) {
        this.datePanier = datePanier;
    }

    public String getEtatPanier() {
        return etatPanier;
    }

    public void setEtatPanier(String etatPanier) {
        this.etatPanier = etatPanier;
    }

    @Override
    public String toString() {
        return "Panier{" + "panierID=" + panierID + ", utilisateurID=" + utilisateurID + ", datePanier=" + datePanier + ", etatPanier=" + etatPanier + '}';
    }
}
