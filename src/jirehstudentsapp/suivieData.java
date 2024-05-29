
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
public class suivieData {
    
    //Pour les suivie
    private Integer ID;
    private Integer suivieID;
    private Timestamp suivieDate;
    private Integer clientID;
    private String clientNom;
    private String serviceNom;
    private Integer factureQte;
    private Double servicePrix;
    private Double suivieServiceToaleSolde;
    private Double suivieServiceDejaPayer;
    private Double suivieServiceRestePayer;
    private String NomUtilisateur;
  
    
    //Pour les clients
    public suivieData(Integer ID,Integer suivieID, Timestamp suivieDate, Integer clientID, String clientNom,
        String serviceNom, Integer factureQte, Double servicePrix, Double suivieServiceToaleSolde, 
        Double suivieServiceDejaPayer, Double suivieServiceRestePayer, String NomUtilisateur)
    {
        this.ID = ID;
        this.suivieID = suivieID;
        this.suivieDate = suivieDate;
        this.clientID = clientID;
        this.clientNom = clientNom;
        this.serviceNom = serviceNom;
        this.factureQte = factureQte;
        this.servicePrix = servicePrix;
        this.suivieServiceToaleSolde = suivieServiceToaleSolde;
        this.suivieServiceDejaPayer = suivieServiceDejaPayer;
        this.suivieServiceRestePayer = suivieServiceRestePayer;
        this.NomUtilisateur = NomUtilisateur;
      
    }

    public Double getSuivieServiceToaleSolde() {
        return suivieServiceToaleSolde;
    }

    public void setSuivieServiceToaleSolde(Double suivieServiceToaleSolde) {
        this.suivieServiceToaleSolde = suivieServiceToaleSolde;
    }

    public Double getSuivieServiceDejaPayer() {
        return suivieServiceDejaPayer;
    }

    public void setSuivieServiceDejaPayer(Double suivieServiceDejaPayer) {
        this.suivieServiceDejaPayer = suivieServiceDejaPayer;
    }

    public Double getSuivieServiceRestePayer() {
        return suivieServiceRestePayer;
    }

    public void setSuivieServiceRestePayer(Double suivieServiceRestePayer) {
        this.suivieServiceRestePayer = suivieServiceRestePayer;
    }
    
    //Pour les clients
////    public suivieData(Integer ID,Integer suivieID,
////        String serviceNom, Integer factureQte, Double servicePrix)
////    {
////        this.ID = ID;
////        this.suivieID = suivieID;
////        this.serviceNom = serviceNom;
////        this.factureQte = factureQte;
////        this.servicePrix = servicePrix;
////      
////    }
    
    public Integer getID(){
        return ID;
    }
    
    public Integer getSuivieID(){
        return suivieID;
    }
    
    public Date getSuivieDate(){
        return suivieDate;
    }
    
    public Integer getClientID(){
        return clientID;
    }
    
    public String getClientNom(){
        return clientNom;
    }
    
    public String getServiceNom(){
        return serviceNom;
    }
   
    public Integer getFactureQte(){
        return factureQte;
    }
    
    public Double getServicePrix(){
        return servicePrix;
    }
    
    public Double getServiceTotaleSolde(){
        return suivieServiceToaleSolde;
    }
    
    public Double getServiceDejaPayer(){
        return suivieServiceDejaPayer;
    }
    
    public Double getServiceRestePayer(){
        return suivieServiceRestePayer;
    }
    
    public String getNomUtilisateur(){
        return NomUtilisateur;
    }
}
