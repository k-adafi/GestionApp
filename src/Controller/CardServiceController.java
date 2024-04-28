/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import jirehstudentsapp.clientData;
import jirehstudentsapp.getData;
import jirehstudentsapp.serviceData;
import Controller.DashboardController;
import javafx.event.Event;
/**
 * FXML Controller class
 *
 * @author KADAFI Ben
 */
public class CardServiceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane cardForm;

    @FXML
    private Label menuServiceNom;

    @FXML
    private Label menuServicePrix;

    @FXML
    private ImageView menuServiceImageView;

    @FXML
    private Spinner<Integer> menuServiceSpinner;

    @FXML
    private Button menuServiceAjouterBtn;
    
    private serviceData serveData;
    
    private DashboardController cliData;
    
    private Image imageService;
    
    private SpinnerValueFactory<Integer> spin;
    
    private Integer serviceID;
    
    
    private Integer clientID;
    
    
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Connection connect;
    
    private double totaleP;
    private double prix;
    private double soldeTotale;
    private double soldedejaPayer;
    private double soldeRestePayer;
    private int qty;
    
    public void setData(serviceData serveData){
        this.serveData = serveData;
        serviceID = serveData.getServiceID();
        
        menuServiceNom.setText(serveData.getServiceNom());
        menuServicePrix.setText(String.valueOf(serveData.getServicePrix())+" Ar");
        
        String path = "File:" + serveData.getServiceImage();
        imageService = new Image(path, 200, 160, false, true);
        menuServiceImageView.setImage(imageService);
        
        prix = serveData.getServicePrix();
    }
    
    private DashboardController dashboardController;

    // Constructeur qui prend DashboardController comme paramètre
    public CardServiceController() {
        this.dashboardController = dashboardController;
    }

   /* public void setDataClient(Label menuClientID, Label menuClientNom) {
        // Utilisez l'instance existante de DashboardController pour appeler menuClientSelect
        dashboardController.menuClientSelect(menuClientID, menuClientNom);
    }*/

   
    public void SetQuantity(){
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        menuServiceSpinner.setValueFactory(spin);
    }
   
    
    public void ajoutMenuService(Label menuClientID, Label menuClientNom){
        
        dashboardController.menuClientSelect(menuClientID, menuClientNom);
        
        qty = menuServiceSpinner.getValue();
        
        
        
        Alert alert;
        
        try {
            
            if (qty == 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plaît, vous devez vérifier votre réquête !");
            alert.showAndWait();
            
            } else {
                
                java.util.Date suivieDate = new java.util.Date();
                java.sql.Date dateS = new java.sql.Date(suivieDate.getTime());
                
                String InsertData = "INSERT INTO suivieclient (suivieID, suivieDate, clientID,"
                        + " clientNom, serviceNom, factureQte, servicePrix, suivieSoldeTotale,"
                        + " suivieDejaPayer, suivieRestePayer, NomUtilisateur)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                
                prepare = connect.prepareStatement(InsertData); 
                prepare.setString(1, String.valueOf(getData.sID));
                prepare.setString(2, String.valueOf(dateS));
                prepare.setString(3, menuClientID.getText());
                prepare.setString(4, menuClientNom.getText());
                prepare.setString(5, menuServiceNom.getText());
                prepare.setString(6, String.valueOf(qty));
                
                totaleP = (qty * prix);
                prepare.setString(7, String.valueOf(totaleP));
                
                soldeTotale += totaleP; 
                prepare.setString(8, String.valueOf(soldeTotale));
                
                soldedejaPayer = (soldeTotale - soldeRestePayer);
                prepare.setString(9, String.valueOf(soldedejaPayer));
                
                soldeRestePayer = (soldeTotale - soldedejaPayer);
                prepare.setString(10, String.valueOf(soldeRestePayer));
                
                prepare.setString(11, getData.username);
                
                prepare.executeUpdate();
                
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message d'information");
                alert.setHeaderText(null);
                alert.setContentText("Succès !");
                alert.showAndWait();
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Pour la quantité des services.
        SetQuantity();
        
    }    
    
}
