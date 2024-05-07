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
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import jirehstudentsapp.clientData;
import jirehstudentsapp.database;
import jirehstudentsapp.getData;

/**
 *
 * @author KADAFI Ben
 */
public class CardClientController implements Initializable{
    
    @FXML
    private AnchorPane cardClientForm;

    @FXML
    private TableView<clientData> menu_tableViewClient;

    @FXML
    private TableColumn<clientData, String> menuColClientID;

    @FXML
    private TableColumn<clientData, String> menuColClientNom;

    @FXML
    private TableColumn<clientData, String> menuColClientPrenom;

    @FXML
    private Button menuClientActualiserBtn;

    @FXML
    private Label menuClientNom;

    @FXML
    private Label menuClientPrenom;

    @FXML
    private TextField menuClientRechercheText1;

    @FXML
    private Label menuClientID;

    @FXML
    private Button menuClientConfirmerBtn;

    
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
    
    
    // Déclaration des variables membres
    private String selectedClientID;
    private String selectedClientNom;
    
    
    
    //Observation des données faire service client sur la tableView.
    public ObservableList<clientData> menuServiceClientListData() {

        ObservableList<clientData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM  client";

        connect = database.ConnectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            clientData clientD;

            while (result.next()) {
                clientD = new clientData(
                        result.getInt("clientID"),
                        result.getString("clientNom"),
                        result.getString("clientPrenom")
                );
                listData.add(clientD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<clientData> menuServiceClientList;

    //Afficher les listes des clients sur Tables view
    public void menuServiceClientShowListData() {
        menuServiceClientList = menuServiceClientListData();

        menuColClientID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        menuColClientNom.setCellValueFactory(new PropertyValueFactory<>("clientNom"));
        menuColClientPrenom.setCellValueFactory(new PropertyValueFactory<>("clientPrenom"));

        menu_tableViewClient.setItems(menuServiceClientList);
    }
    
    
    //Selection client via Table View 
    public void menuServiceClientSelect() {
        clientData clientD = menu_tableViewClient.getSelectionModel().getSelectedItem();
        int num = menu_tableViewClient.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        menuClientID.setText(String.valueOf(clientD.getClientID())); // Assurez-vous que clientID est un TextField ou un contrôle similaire
        menuClientNom.setText(clientD.getClientNom());
        menuClientPrenom.setText(clientD.getClientPrenom());
    }

    //Pour l'actualisation de l'affichage du faire service service (Efa mandeha) 
    public void menuServiceClientReset() {
        menuClientRechercheText1.setText("");
        menuClientID.setText("");
        menuClientNom.setText("");
        menuClientPrenom.setText("");
        getData.getMenuClientID = null;
        getData.getMenuClientNom = null;
    }
    
    
    //Pour la barre de recherche sur menu service dans client (efa mandeha)
    public void menuClientSearch() {

        FilteredList<clientData> filter = new FilteredList<>(menuServiceClientList, e -> true);

        menuClientRechercheText1.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateClientData -> {

                if (newValue == null || newValue.isEmpty()) {

                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateClientData.getClientID().toString().contains(searchKey)) {

                    return true;

                } else if (predicateClientData.getClientNom().toLowerCase().contains(searchKey)) {

                    return true;

                } else if (predicateClientData.getClientPrenom().toLowerCase().contains(searchKey)) {

                    return true;
                } else {
                    return false;
                }
            });

        });

        SortedList<clientData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(menu_tableViewClient.comparatorProperty());
        menu_tableViewClient.setItems(sortList);
    } 
    
    
    public void menuclientSelectBtn(){

        String sqlselectClient = "SELECT * FROM client WHERE clientID = '" 
                + menuClientID.getText() + "' AND clientNom = '" 
                + menuClientNom.getText() + "' AND clientPrenom = '" 
                + menuClientPrenom.getText() + "'";

        connect = database.ConnectDb();
       
        try {
            Alert alert;
            
            if (menuClientID.getText().isEmpty()
                    || menuClientNom.getText().isEmpty()
                    || menuClientPrenom.getText().isEmpty()){
                
                
            
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez sélectionner un client!");
                alert.showAndWait();
            
            }else{
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sur " + menuClientNom.getText() + " ?");
                Optional<ButtonType> option = alert.showAndWait();
            
                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sqlselectClient);
                    result = prepare.executeQuery();

                    getData.getMenuClientID = menuClientID.getText();
                    getData.getMenuClientNom = menuClientNom.getText();

                    menuServiceClientShowListData();
                    menuServiceClientSelect();
          
                }
            
            }
       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        menuServiceClientShowListData();
        
    }
    
    
}
