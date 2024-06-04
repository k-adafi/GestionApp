/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jirehstudentsapp;

//import java.sql.Connection;
import  java.sql.*;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;



/**
 *
 * @author KADAFI Ben
 */


public class database {
    
    public static Connection ConnectDb() {
        
        Properties props = new Properties();
        try (InputStream input = database.class.getResourceAsStream("/dbconfig.properties")) {
            
            // Load the properties file
            if (input == null) {
                System.out.println("Sorry, unable to find dbconfig.properties");
                return null;
            }
            props.load(input);

            // Get the property values
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie");
            return cn;
            
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier de configuration");
            e.printStackTrace();
            return null;
            
        } catch (ClassNotFoundException e) {
            System.out.println("Échec de chargement du driver JDBC");
            e.printStackTrace();
            return null;
            
        } catch (SQLException e) {
            System.out.println("Échec de connexion à la base de données");
            e.printStackTrace();
            return null;
        }
    }
}




//public class database { 
//    
//    public static Connection ConnectDb(){
//        
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jirehstudentsbdd2", "root", "lala");
//            System.out.println("Connexion réussie");
//            return cn;
//            
//        } catch (ClassNotFoundException e) {
//            System.out.println("Échec de chargement du driver JDBC");
//            e.printStackTrace();
//            return null;
//            
//        } catch (SQLException e) {
//            System.out.println("Échec de connexion à la base de données");
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
    
