/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jirehstudentsapp;


/**
 *
 * @author KADAFI Ben
 */
public class adminData {
    
    private String NomUtilisateur;
    private String Email;
    private String MotDePasse;  
    
    public adminData(String NomUtilisateur,  String Email, String MotDePasse) {
        
        this.NomUtilisateur = NomUtilisateur;
        this.Email = Email;
        this.MotDePasse = MotDePasse;
    
    }
    
    
    
    public String getNomUtilisateur(){
        return NomUtilisateur;
    }
    
    public String getEmail(){
        return Email;
    }
    
    public String getClientGenre(){
        return MotDePasse;
    }
}