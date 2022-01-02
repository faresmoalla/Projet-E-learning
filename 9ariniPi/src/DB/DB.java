/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bhk
 */
public class DB {

    final String url = "jdbc:mysql://localhost/9arini";
    final String login = "root";
    final String password = "";
    Connection connexion;
    static DB instance;

    private DB() {
        try {
            connexion
                    = DriverManager.getConnection(url, login, password);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion à la base de données");
        }
    }
    
    public static  DB getInstance(){
        if(instance == null)
            instance = new DB();
        return instance;
    }
    
    public Connection getConnection(){
        return connexion;
    }
}
