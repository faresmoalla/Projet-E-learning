/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Article;
import entities.Comment;
import DB.DB;

/**
 *
 * @author yahia
 */
public class CommentService {

    Connection connexion;

    public CommentService() {
        connexion = DB.getInstance().getConnection();
    }


    
    public void ajouterCommentparid(Comment c) throws SQLException {
      
        String req = " INSERT INTO `comments` "
                + "(`article`, `content`,`id`,`userName`) "
                + "VALUES ('"
                       

                + c.getArticle()+ "', '"
                + c.getContent()+"', '"

                + c.getId()+ "', '"
                + c.getUtilisateurNom()
                + "')";

        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }


    
    
    
    
    
    public List<Comment> getAllCommentForArticle(int article) throws SQLException {

        List<Comment> comments = new ArrayList<>();
        //String req = "select * from Comment where article="+article;
      // String req = "SELECT * FROM comment c JOIN utilisateur u ON c.utilisateur_id=u.id WHERE article="+article ;

      String req = "SELECT * FROM comments " ;
 
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Comment c = new Comment(rst.getInt("id"),
                    rst.getString("content"),
                   rst.getInt("utilisateur_id"),
                   rst.getString("userName") );
            comments.add(c);
        }
        return comments;
    }
    
    
}
