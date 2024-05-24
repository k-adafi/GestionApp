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
public class clientData {
    
    //Pour les clients
    private Integer clientID;
    private String clientNom;
    private String clientPrenom;
    private String clientGenre;
    private Date clientDateN;
    private String clientCNI;
    private String clientEtablissement;
    private String clientMention;
    private String clientNiveau;
    private String clientCodage;
    private String clientPassant;
    private String clientTel;
    private String clientAdresse;
    private String clientType;
    private String clientPhoto;
    private Date DateInscrp;
    private String NomUtilisateur;
    private Double clientSoldeDejaPayer;
    private Double clientSoldeRestePayer;
    
 
    //Pour les clients
    public clientData(Integer clientID, String clientNom,String clientPrenom, String clientGenre, Date clientDateN,
        String clientCNI, String clientEtablissement, String clientMention, String clientNiveau, String clientCodage,
        String clientPassant,String clientTel, String clientAdresse, String clientType, String clientPhoto, Date DateInscrp, String NomUtilisateur)
    {
        this.clientID = clientID;
        this.clientNom = clientNom;
        this.clientPrenom = clientPrenom;
        this.clientGenre = clientGenre;
        this.clientDateN = clientDateN;
        this.clientCNI = clientCNI;
        this.clientEtablissement = clientEtablissement;
        this.clientMention = clientMention;
        this.clientNiveau = clientNiveau;
        this.clientCodage = clientCodage;
        this.clientPassant = clientPassant;
        this.clientTel = clientTel;
        this.clientAdresse = clientAdresse;
        this.clientType = clientType;
        this.clientPhoto = clientPhoto;
        this.DateInscrp = DateInscrp;
        this.NomUtilisateur = NomUtilisateur;
    }  
    
    
    public clientData(Integer clientID, String clientNom, String clientPrenom){
        this.clientID = clientID;
        this.clientNom = clientNom;
        this.clientPrenom = clientPrenom;    
    }
    
    public clientData(Integer clientID, String clientNom){
        this.clientID = clientID;
        this.clientNom = clientNom;    
    }
    
    public clientData(Integer clientID, String clientNom, Double clientSoldeDejaPayer, Double clientSoldeRestePayer){
        this.clientID = clientID;
        this.clientNom = clientNom;
        this.clientSoldeDejaPayer = clientSoldeDejaPayer;
        this.clientSoldeRestePayer = clientSoldeRestePayer;
    }

    
    //Pour les clients
    public Integer getClientID(){
        return clientID;
    }
    
    public String getClientNom(){
        return clientNom;
    }
    
    public String getClientPrenom(){
        return clientPrenom;
    }
    
    public String getClientGenre(){
        return clientGenre;
    }
    
    public Date getClientDateN(){
        return clientDateN;
    }
    
    public String getClientCNI(){
        return clientCNI;
    }
    
    public String getClientEtablissement(){
        return clientEtablissement;
    }
    
    public String getClientMention(){
        return clientMention;
    }
    
    public String getClientNiveau(){
        return clientNiveau;
    }
    
    public String getClientCodage(){
        return clientCodage;
    }
    
    public String getClientPassant(){
        return clientPassant;
    }
    
    public String getClientTel(){
        return clientTel;
    }
    
    public String getClientAdresse(){
        return clientAdresse;
    }
    
    public String getClientType(){
        return clientType;
    }
    
    public String getClientPhoto(){
        return clientPhoto;
    }
    
    public Date getDateInscrp(){
        return DateInscrp;
    }
    
    public String getNomUtilisateur(){
        return NomUtilisateur;
    }
    
    public Double getclientSoldeDejaPayer(){
        return clientSoldeDejaPayer;
    }
    
    public Double getclientSoldeRestePayer(){
        return clientSoldeRestePayer;
    }
    
}