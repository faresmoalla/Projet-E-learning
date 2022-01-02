package entities;

/**
 *
 * @author Aminous
 */
public class Commande {

    private int commandeID, panierID;
    private int coursID;

    public Commande() {
    }

    public Commande(int coursID) {
        this.coursID = coursID;
    }

    public Commande(int commandeID, int coursID) {
        this.commandeID = commandeID;
        this.coursID = coursID;
    }

    public Commande(int commandeID, int panierID, int coursID) {
        this.commandeID = commandeID;
        this.panierID = panierID;
        this.coursID = coursID;
    }

    public int getCommandeID() {
        return commandeID;
    }

    public void setCommandeID(int commandeID) {
        this.commandeID = commandeID;
    }

    public int getPanierID() {
        return panierID;
    }

    public void setPanierID(int panierID) {
        this.panierID = panierID;
    }

    public int getCoursID() {
        return coursID;
    }

    public void setCoursID(int coursID) {
        this.coursID = coursID;
    }

    @Override
    public String toString() {
        return "Commande{" + "commandeID=" + commandeID + ", panierID=" + panierID + ", coursID=" + coursID + '}';
    }
}
