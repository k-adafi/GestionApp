/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jirehstudentsapp;


import java.util.Date;



/**
 *
 * @author KADAFI Ben
 */
public class serviceData {
    
    private Integer serviceID;
    private String serviceNom;
    private String serviceType;
    private Double servicePrix;
    private String serviceDuree;
    private Date serviceDateDebut;
    private Date serviceDateFin;
    private String serviceImage;
    private String NomUtilisateur;

        
    public serviceData(Integer serviceID, String serviceNom, String serviceType, Double servicePrix, 
            String serviceDuree, Date serviceDateDebut, Date serviceDateFin, String serviceImage, String NomUtilisateur)
    {
        this.serviceID = serviceID;
        this.serviceNom = serviceNom;
        this.serviceType = serviceType;
        this.servicePrix = servicePrix;
        this.serviceDuree = serviceDuree;
        this.serviceDateDebut = serviceDateDebut;
        this.serviceDateFin = serviceDateFin;
        this.serviceImage = serviceImage;
        this.NomUtilisateur = NomUtilisateur;
    }
    
    
    public serviceData(Integer serviceID, String serviceNom, Double servicePrix, String serviceImage)
    {
        this.serviceID = serviceID;
        this.serviceNom = serviceNom;
        this.servicePrix = servicePrix;
        this.serviceImage = serviceImage;
    }
    
    
    public serviceData(Integer serviceID, String serviceNom, Double servicePrix)
    {
        this.serviceID = serviceID;
        this.serviceNom = serviceNom;
        this.servicePrix = servicePrix;
    }
    
 
    
    public Integer getServiceID(){
        return serviceID;
    }
    
    public String getServiceNom(){
        return serviceNom;
    }
    
    public String getServiceType(){
        return serviceType;
    }
    
    public Double getServicePrix(){
        return servicePrix;
    }
    
    public String getServiceDuree(){
        return serviceDuree;
    }
    
    public Date getServiceDateDebut(){
        return serviceDateDebut;
    }
    
    public Date getServiceDateFin(){
        return serviceDateFin;
    }
    
    public String getServiceImage(){
        return serviceImage;
    }
    
    public String getNomUtilisateur(){
        return NomUtilisateur;
    }
   
}
