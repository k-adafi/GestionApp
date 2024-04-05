/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jirehstudentsapp;

//import java.sql.Connection;
import  java.sql.*;



/**
 *
 * @author KADAFI Ben
 */
public class database {    
    public Connection cn = null;
    
    public static Connection ConnectDb(){
         try{
             Class.forName("com.mysql.jdbc.Driver");
             Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jirehstudentsbdd?characterEncoding=UTF-8", "root", "");
             System.out.println("Connexion réussi");
             return cn;
        
        }catch( ClassNotFoundException | SQLException e){
             System.out.println("Connexion echoué");
             e.printStackTrace();
             return null;
        } 
         
    } 

}
    
