/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Article;


import DB.DB;

/**
 *
 * @author yahia
 */
public class ArticleService {

    Connection connexion;
    Article article=new Article();
static ArticleService instance;
    private ArticleService() {
        connexion = DB.getInstance().getConnection();
    }

    public void ajouterservice(Article a) throws SQLException {
        String req = " INSERT INTO `article` "
                + "(`object`, `content`, `utilisateur_id`, `utilisateurNom`) "
                + "VALUES ('"
                
                + a.getObject() + "', '"
                + a.getContent() + "', '"
                + a.getUtilisateur_id ()+ "', '"
                + a.getUtilisateurNom()
                + "')"; 

        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    
    
    public List<Article> getAllArticle() throws SQLException {

        List<Article> articles = new ArrayList<>();
  
         //String req = "SELECT * FROM article a JOIN utilisateur u  ON a.id = u.id";
        String req = "SELECT * FROM article a JOIN utilisateur u  ON a.utilisateur_id  = u.id";

        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Article f = new Article(rst.getInt("id"),
                           rst.getString("object"),
                           rst.getString("content"), 
                           rst.getInt("utilisateur_id"),                       
                           rst.getString("utilisateurNom")
            );
            articles.add(f);
        }
        return articles;
    }
     public  void  supprimerArticlebyID(int id) throws SQLException   {

        String req = "DELETE FROM article WHERE id=?";
        try {
            PreparedStatement pss = connexion.prepareStatement(req);

            pss.setInt(1, id);
            pss.executeUpdate();
        } catch (SQLException ex) {
        }
    }
     public void updatearticle  (int idA, String newobj )  {
        try {
            String req = "UPDATE article SET object= '"+ newobj +"' WHERE id ="+ idA;
            Statement stm = connexion.createStatement();
            stm.executeUpdate(req);

        } catch (SQLException ex) {
        }
    }
      public static  ArticleService getInstance(){
        if(instance == null)
            instance = new ArticleService();
        return instance;
    }
    
    public Article getArticle(){
               
        return article;
    }
    public void setArticle(Article article){
        this.article = article;
    }
}
