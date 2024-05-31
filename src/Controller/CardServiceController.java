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
import java.sql.SQLException;
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
import java.util.Date;
import java.util.Optional;
import javafx.scene.control.ButtonType;
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
    private Double prix;

    private double soldeTotale;
    private double soldeTotaleTotale;

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

    public double getTolaleSolde() {

        String totalSolde = "SELECT MAX(suivieServiceToaleSolde) FROM suivieclient WHERE clientID =" + getData.getMenuClientID;

        connect = database.ConnectDb();

        try {

            prepare = connect.prepareStatement(totalSolde);
            result = prepare.executeQuery();

            if (result.next()) {
                soldeTotale = result.getDouble("MAX(suivieServiceToaleSolde)");

            }

            getData.soldeTotaleTotale = soldeTotale;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return soldeTotale;
    }

    

    public void ajoutMenuServiceBtn() {
        DashboardController dashForm = new DashboardController(); // Utiliser la même instance de DashboardController

        getTolaleSolde();

        Alert alert;
        try {
            qty = menuServiceSpinner.getValue();

            if (qty == 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez ajouter au moins une quantité !");
                alert.showAndWait();

            } else if (getData.getMenuClientID == null || getData.getMenuClientNom == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez sélectionner un client !");
                alert.showAndWait();

            } else {
                // Récupérer la date actuelle
                java.sql.Timestamp currentDate = new java.sql.Timestamp(System.currentTimeMillis());

                // Insérer les données dans la table suivieclient
                String insertDataQuery = "INSERT INTO suivieclient (suivieID, suivieDate, clientID, clientNom, serviceNom, factureQte, "
                        + "servicePrix, suivieServiceToaleSolde, suivieServiceDejaPayer, suivieServiceRestePayer, NomUtilisateur)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

                connect = database.ConnectDb();

                prepare = connect.prepareStatement(insertDataQuery);
                
                if (getData.clickAdd) {
                    prepare.setString(1, String.valueOf(dashForm.getSid()));
                } else {
                    prepare.setString(1, String.valueOf(dashForm.getSid()+ 1));
                    getData.sID = dashForm.getSid()+1;
                }

                prepare.setTimestamp(2, currentDate);
                prepare.setString(3, String.valueOf(getData.getMenuClientID));
                prepare.setString(4, getData.getMenuClientNom);
                prepare.setString(5, menuServiceNom.getText());
                prepare.setInt(6, qty);

                totaleP = (qty * prix);
                prepare.setDouble(7, totaleP);

                soldeTotaleTotale = (totaleP + soldeTotale);
                prepare.setDouble(8, soldeTotaleTotale);

                prepare.setDouble(9, 0.0);

                prepare.setDouble(10, 0.0);

                prepare.setString(11, getData.username);

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr d'ajouter ce service ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.CANCEL)) {
                    
                    dashForm.historique("Le faire service avec le client: " + getData.getMenuClientID + " est anulé!", getData.username);
                    
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Ajout anuler!");
                    alert.showAndWait();

                } else {
                    prepare.executeUpdate();
                    getData.clickAdd = true;

                    dashForm.historique("Un nouveau service est fait avec le client: " + getData.getMenuClientID, getData.username);
                    
                    getData.soldeTotaleTotale = soldeTotaleTotale;
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Ajout réussi !\nVous devez cliquer sur Actualiser avant d'ajouter un autre client sur un service");
                    alert.showAndWait();

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Pour la quantité des services.
        SetQuantity();

    }

}
