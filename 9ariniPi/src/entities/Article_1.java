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
public class Article_1 {
    private int id;
    private int utilisateur_id ;
    private String utilisateurNom;
    private String object;
    private String content;
   

   





    

    

    public int getUtilisateur_id () {
        return utilisateur_id ;
    }

    public void setUtilisateur_id (int utlisateurID) {
        this.utilisateur_id  = utilisateur_id ;
    }

    public Article_1(int id,String object,  int utilisateur_id , String content) {
        this.id = id;
        this.utilisateur_id  = utilisateur_id ;
        this.object = object;
        this.content = content;
    }

 

    public Article_1(String object, String content) {
        this.object = object;
        this.content = content;
    }

    

    



    


    public Article_1() {
        
    }
    

    public int getId() {
        return id;
    }

    public String getUtilisateurNom() {
        return utilisateurNom;
    }

    public void setUtilisateurNom(String utilisateurNom) {
        this.utilisateurNom = utilisateurNom;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article_1(int id , String object,String content,int utilisateur_id , String utilisateurNom) {
        this.id = id;
        this.object = object;
        this.content = content;
        this.utilisateur_id =utilisateur_id  ; 
        this.utilisateurNom = utilisateurNom;
       
        
    }

    public Article_1(String object, String content, String utilisateurNom) {
        this.utilisateurNom = utilisateurNom;
        this.object = object;
        this.content = content;
    }
    

    public Article_1(String object,String content, int utilisateur_id , String utilisateurNom  ) {
        this.utilisateur_id  = utilisateur_id ;
        this.utilisateurNom = utilisateurNom;
        this.object = object;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", utilisateur_id =" + utilisateur_id  + ", utilisateurNom=" + utilisateurNom + ", object=" + object + ", content=" + content + '}';
    }



    
    
    
    
}
