package entities;

import java.sql.Date;

/**
 *
 * @author yahia
 */
public class Reclamation {

    private int reclamationID, utilisateurID, formateurid, formationid;
    private String categorieReclamation, messageReclamation, reponseReclamation, etatReclamation;
    private Date dateReclamation, dateReponse;
    private boolean etatReponse;

    
    public Reclamation() {
    }
    
    public Reclamation(int utilisateurID, String categorieReclamation, String messageReclamation, String reponseReclamation, String etatReclamation, Date dateReclamation, Date dateReponse, boolean etatReponse) {
        this.utilisateurID = utilisateurID;
        this.categorieReclamation = categorieReclamation;
        this.messageReclamation = messageReclamation;
        this.reponseReclamation = reponseReclamation;
        this.etatReclamation = etatReclamation;
        this.dateReclamation = dateReclamation;
        this.dateReponse = dateReponse;
        this.etatReponse = etatReponse;
    }

    public Reclamation(int utilisateurID, int formateurid, int formationid, String categorieReclamation, String messageReclamation, String reponseReclamation, String etatReclamation, Date dateReclamation, Date dateReponse, boolean etatReponse) {
        this.utilisateurID = utilisateurID;
        this.formateurid = formateurid;
        this.formationid = formationid;
        this.categorieReclamation = categorieReclamation;
        this.messageReclamation = messageReclamation;
        this.reponseReclamation = reponseReclamation;
        this.etatReclamation = etatReclamation;
        this.dateReclamation = dateReclamation;
        this.dateReponse = dateReponse;
        this.etatReponse = etatReponse;
    }
    
    public Reclamation(int reclamationID, int utilisateurID, int formateurid, int formationid, String categorieReclamation, String messageReclamation, String reponseReclamation, String etatReclamation, Date dateReclamation, Date dateReponse, boolean etatReponse) {
        this.reclamationID = reclamationID;
        this.utilisateurID = utilisateurID;
        this.formateurid = formateurid;
        this.formationid = formationid;
        this.categorieReclamation = categorieReclamation;
        this.messageReclamation = messageReclamation;
        this.reponseReclamation = reponseReclamation;
        this.etatReclamation = etatReclamation;
        this.dateReclamation = dateReclamation;
        this.dateReponse = dateReponse;
        this.etatReponse = etatReponse;
    }
    
    public static Date getCurrentDate() {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    public int getReclamationID() {
        return reclamationID;
    }

    public void setReclamationID(int reclamationID) {
        this.reclamationID = reclamationID;
    }

    public int getUtilisateurID() {
        return utilisateurID;
    }

    public void setUtilisateurID(int utilisateurID) {
        this.utilisateurID = utilisateurID;
    }

    public int getFormateurid() {
        return formateurid;
    }

    public void setFormateurid(int formateurid) {
        this.formateurid = formateurid;
    }

    public int getFormationid() {
        return formationid;
    }

    public void setFormationid(int formationid) {
        this.formationid = formationid;
    }

    public String getCategorieReclamation() {
        return categorieReclamation;
    }

    public void setCategorieReclamation(String categorieReclamation) {
        this.categorieReclamation = categorieReclamation;
    }

    public String getMessageReclamation() {
        return messageReclamation;
    }

    public void setMessageReclamation(String messageReclamation) {
        this.messageReclamation = messageReclamation;
    }

    public String getReponseReclamation() {
        return reponseReclamation;
    }

    public void setReponseReclamation(String reponseReclamation) {
        this.reponseReclamation = reponseReclamation;
    }

    public String getEtatReclamation() {
        return etatReclamation;
    }

    public void setEtatReclamation(String etatReclamation) {
        this.etatReclamation = etatReclamation;
    }

    public Date getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(Date dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    public Date getDateReponse() {
        return dateReponse;
    }

    public void setDateReponse(Date dateReponse) {
        this.dateReponse = dateReponse;
    }

    public boolean isEtatReponse() {
        return etatReponse;
    }

    public void setEtatReponse(boolean etatReponse) {
        this.etatReponse = etatReponse;
    }
    
    @Override
    public String toString() {
        return "Reclamation{" + "reclamationID=" + reclamationID + ", utilisateurID=" + utilisateurID + ", formateurid=" + formateurid + ", formationid=" + formationid + ", categorieReclamation=" + categorieReclamation + ", messageReclamation=" + messageReclamation + ", reponseReclamation=" + reponseReclamation + ", etatReclamation=" + etatReclamation + ", dateReclamation=" + dateReclamation + ", dateReponse=" + dateReponse + ", etatReponse=" + etatReponse + '}';
    }

    
    
}
