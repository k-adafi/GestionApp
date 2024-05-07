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
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jirehstudentsapp.database;

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
    
    
    private Integer clientID;
    
    private clientData cliData;
    
    private serviceData serveData;

   //private DashboardController cliData;

    private Image imageService;

    private SpinnerValueFactory<Integer> spin;

    private Integer serviceID;

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
    
    private Date serveDate; 

    private String serviceImage;
 
   
    //Debut du Service
    public void setData(serviceData serveData) {
        this.serveData = serveData;
        serviceID = serveData.getServiceID();

        menuServiceNom.setText(serveData.getServiceNom());
        menuServicePrix.setText(String.valueOf(serveData.getServicePrix()) + " Ar");

        String path = "File:" + serveData.getServiceImage();
        imageService = new Image(path, 200, 160, false, true);
        menuServiceImageView.setImage(imageService);
        
        serviceImage = serveData.getServiceImage();
        prix = serveData.getServicePrix();
    }


    public void SetQuantity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        menuServiceSpinner.setValueFactory(spin);
    }  
    
      
    public void ajoutMenuServiceBtn() {
        
        DashboardController dashForm =  new DashboardController();
        dashForm.suivieID();
        
        Alert alert;
        try {
            qty = menuServiceSpinner.getValue();

            if (qty == 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez ajouter au moins un quantité!");
                alert.showAndWait();
            
            }else if(getData.getMenuClientID == null|| getData.getMenuClientNom == null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez séléctionner un client!");
                alert.showAndWait();
                
            } else {
                // Récupérer la date actuelle
                java.sql.Date dateS = new java.sql.Date(System.currentTimeMillis());

                // Insérer les données dans la table suivieclient
                String insertData = "INSERT INTO suivieclient (suivieID, suivieDate, clientID, clientNom, serviceNom, factureQte, "
                        + "servicePrix, suivieServiceToaleSolde, suivieServiceDejaPayer, suivieServiceRestePayer, NomUtilisateur)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                
                connect = database.ConnectDb();

                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, String.valueOf(getData.sID));
                prepare.setDate(2, dateS);
                
                prepare.setString(3, getData.getMenuClientID);
                prepare.setString(4, getData.getMenuClientNom);
                prepare.setString(5, menuServiceNom.getText());
                prepare.setInt(6, qty);
                
                totaleP = (qty * prix);
                prepare.setDouble(7, totaleP);
                
                soldeTotale += totaleP;
                prepare.setDouble(8, soldeTotale);
                
                soldedejaPayer = ( soldeTotale - soldeRestePayer);
                prepare.setDouble(9, soldedejaPayer);
                
                soldeRestePayer = ( soldeTotale - soldedejaPayer);
                prepare.setDouble(10, soldeRestePayer);
                
                prepare.setString(11, getData.username);
                
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sur d'ajout cette service ?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if (option.get().equals(ButtonType.CANCEL)) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Echec d'ajout !");
                    alert.showAndWait();
                   
                }else{
                    prepare.executeUpdate();
                     
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Ajout avec succès !\nVous devez cliquer sur Actualiser avant d'ajouter un autre client sur un service");
                    alert.showAndWait();
                    
                    dashForm.menuGetTotale();
                }
                
     
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         
        //Comment faire pour charger deux fichier fxml dans un même controller et traitrer aficher leurs données sans provoque l'erreur "NullPointException"
  
        //Pour la quantité des services.
        SetQuantity();
  
    }

}