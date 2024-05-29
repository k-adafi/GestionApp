/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jirehstudentsapp;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author KADAFI Ben
 */
public class historiqueData {
    
    private Integer idH;
    private Timestamp dateHeur;
    private String nomHistirique;
    private String NomUtilisateur;
    
    public historiqueData(Integer idH, Timestamp dateHeur, String nomHistirique, String NomUtilisateur){
        this.idH = idH;
        this.dateHeur = dateHeur;
        this.nomHistirique = nomHistirique;
        this.NomUtilisateur = NomUtilisateur;
    }
    
    public Integer getIdh(){
        return idH;
    }
    
    public Timestamp getDateHeur(){
        return dateHeur;
    }

    public Integer getIdH() {
        return idH;
    }

    public void setIdH(Integer idH) {
        this.idH = idH;
    }

    public String getNomHistirique() {
        return nomHistirique;
    }

    public void setNomHistirique(String nomHistirique) {
        this.nomHistirique = nomHistirique;
    }
    
    public String getNomHistorique(){
        return nomHistirique;
    }
    
    public String getNomUtilisateur(){
        return NomUtilisateur;
    }
      
}
