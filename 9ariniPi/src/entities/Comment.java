/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author yahia
 */
public class Comment {

    
    private int id,article , utilisateur_id ;
    private String content;
        private String utilisateurNom;


    public int getUtilisateur_id () {
        return utilisateur_id ;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id  = utilisateur_id;
    }

    public Comment(int article, String content) {
        this.article = article;
        this.content = content;
    }

    public Comment(int article,  String content,int utilisateur_id , String utilisateurNom) {
        this.article = article;
        this.utilisateur_id  = utilisateur_id ;
        this.content = content;
        this.utilisateurNom = utilisateurNom;
    }

 

    

   
    
    
    

    public Comment(){};

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public String getUtilisateurNom() {
        return utilisateurNom;
    }

    public void setUtilisateurNom(String utilisateurNom) {
        this.utilisateurNom = utilisateurNom;
    }

    public Comment(int id, int article, int utilisateur_id , String content) {
        this.id = id;
        this.article = article;
        this.utilisateur_id  = utilisateur_id ;
        this.content = content;
    }

   /* public Comment(int id, int article, String content, int utilisateur_id ,String utilisateurNom) {
        this.id = id;
        this.article = article;
        this.utilisateur_id = utilisateur_id;
        this.content = content;
        this.utilisateurNom = utilisateurNom;
    }*/

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", article=" + article + ", utilisateur_id =" + utilisateur_id  + ", content=" + content + ", utilisateurNom=" + utilisateurNom + '}';
    }

    

   
    
}
