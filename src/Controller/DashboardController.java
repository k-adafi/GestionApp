/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import jirehstudentsapp.clientData;
import jirehstudentsapp.database;
import jirehstudentsapp.getData;
import static jirehstudentsapp.getData.username;
import jirehstudentsapp.serviceData;

/**
 *
 * @author KADAFI Ben
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label NomUtilisateur;

    //Bouton de navigation fenetre
    @FXML
    private Button acceuilBtn;

    @FXML
    private Button clientBtn;

    @FXML
    private Button serviceBtn;

    @FXML
    private Button factureBtn;

    @FXML
    private Button signOutBtn;

    @FXML
    private Button historiqueBtn;

    @FXML
    private Button suivieBtn;

    @FXML
    private Button makeserviceBtn;

    //Acceuil
    @FXML
    private AnchorPane acceuilForm;

    @FXML
    private Label acceuilTotaleClient;

    @FXML
    private Label acceuilTotaleService;

    @FXML
    private Button acceuilListeClientBtn;

    @FXML
    private Button acceuilListeServiceBtn;

    //Client
    @FXML
    private AnchorPane clientForm;

    @FXML
    private TableView<clientData> clientTableView;

    @FXML
    private TableColumn<clientData, String> clientIDCol;

    @FXML
    private TableColumn<clientData, String> clientNomCol;

    @FXML
    private TableColumn<clientData, String> clientPrenomCol;

    @FXML
    private TableColumn<clientData, String> clientGenreCol;

    @FXML
    private TableColumn<clientData, String> clientDateNCol;

    @FXML
    private TableColumn<clientData, String> clientCINCol;

    @FXML
    private TableColumn<clientData, String> clientEtablissementCol;

    @FXML
    private TableColumn<clientData, String> clientMentionCol;

    @FXML
    private TableColumn<clientData, String> clientNiveauCol;

    @FXML
    private TableColumn<clientData, String> clientCodageCol;

    @FXML
    private TableColumn<clientData, String> clientPassantCol;

    @FXML
    private TableColumn<clientData, String> clientTelCol;

    @FXML
    private TableColumn<clientData, String> clientAdressCol;
    
    @FXML
    private TableColumn<clientData, String> clientDateCol;

    @FXML
    private TextField clientRechercheText;

    @FXML
    private TextField clientID;

    @FXML
    private TextField clientNom;

    @FXML
    private TextField clientPrenom;

    @FXML
    private ComboBox<String> clientGenre;

    @FXML
    private TextField clientCNI;

    @FXML
    private TextField clientMention;

    @FXML
    private TextField clientCodage;

    @FXML
    private TextField clientTel;

    @FXML
    private ComboBox<String> clientPassant;

    @FXML
    private ComboBox<String> clientNiveau;

    @FXML
    private ComboBox<String> clientEtablissement;

    @FXML
    private TextField clientAdress;

    @FXML
    private ImageView clientImageView;

    @FXML
    private Button clientPhotoBtn;

    @FXML
    private Button clientAjouterBtn;

    @FXML
    private Button clientModifierBtn;

    @FXML
    private Button clientEffacerBtn;

    @FXML
    private Button clientActualiserBtn;

    @FXML
    private DatePicker clientDateN;

    @FXML
    private AnchorPane suivieForm;

    //Service
    @FXML
    private AnchorPane serviceForm;

    @FXML
    private TableView<serviceData> serviceTableView;

    @FXML
    private TableColumn<serviceData, String> serviceIDCol;

    @FXML
    private TableColumn<serviceData, String> serviceNomCol;

    @FXML
    private TableColumn<serviceData, String> serviceTypeCol;

    @FXML
    private TableColumn<serviceData, String> servicePrixCol;

    @FXML
    private TableColumn<serviceData, String> serviceDureeCol;

    @FXML
    private TableColumn<serviceData, String> serviceDateDebutCol;

    @FXML
    private TableColumn<serviceData, String> serviceDateFinCol;

    @FXML
    private TextField serviceTextFieldRecherche;

    @FXML
    private TextField serviceID;

    @FXML
    private TextField serviceNom;

    @FXML
    private TextField serviceType;

    @FXML
    private TextField servicePrix;

    @FXML
    private TextField serviceDuree;

    @FXML
    private DatePicker serviceDateDebut;

    @FXML
    private DatePicker serviceDateFin;

    @FXML
    private Button serviceImportImage;

    @FXML
    private ImageView serviceImageView;

    @FXML
    private Button serviceAjouterBtn;

    @FXML
    private Button serviceEffacerBtn;

    @FXML
    private Button serviceModifierBtn;

    @FXML
    private Button serviceActualiserBtn;

    //Faire un service 
    @FXML
    private AnchorPane makeServiceForm;

    @FXML
    private TextField faireServiceClientRechercheText;

    @FXML
    private TextField faireServiceServiceRechercheText;

    @FXML
    private TableView<clientData> faireServiceClientTableView;

    @FXML
    private TableColumn<clientData, String> faireServiceIDClientCol;

    @FXML
    private TableColumn<clientData, String> faireServiceNomClientCol;

    @FXML
    private TableColumn<clientData, String> faireServicePrenomClientCol;

    @FXML
    private TableView<serviceData> faireServiceServiceTableView;

    @FXML
    private TableColumn<serviceData, String> faireServiceIDServiceCol;

    @FXML
    private TableColumn<serviceData, String> faireServiceNomServiceCol;

    @FXML
    private TableColumn<serviceData, String> faireServicePrixServiceCol;

    @FXML
    private TextField faireServiceClientID;

    @FXML
    private TextField faireServiceServiceID;

    @FXML
    private Button makeserviceServiceSearchBtn;

    @FXML
    private Button makeserviceAjouterBtn;

    @FXML
    private Button makeserviceClientSearchBtn;

    @FXML
    private Label faireServiceClientNom;

    @FXML
    private Label faireServiceClientPrenom;

    @FXML
    private Label faireServiceServiceNom;

    @FXML
    private Button makeserviceAnulerBtn;

    @FXML
    private Button makeservicePayerBtn;

    @FXML
    private Label faireServiceServicePrix;

    //Facture
    @FXML
    private AnchorPane factureForm;

    @FXML
    private TextField factureID;

    @FXML
    private TextField clientNomF;

    @FXML
    private TextField servicePrixF;

    @FXML
    private ImageView factureImage;

    @FXML
    private Button facturePrint;

    @FXML
    private DatePicker factureDate;

    @FXML
    private ComboBox<?> factureservicequantite;

    @FXML
    private TextField srviceNom;

    @FXML
    private TextField factureMonttant;
    
    
    //Menu faire service affichage
     @FXML
    private TableView<?> menu_tableView;

    @FXML
    private TableColumn<?, ?> menuColServiceNom;

    @FXML
    private TableColumn<?, ?> menuColServiceQuantite;

    @FXML
    private TableColumn<?, ?> menuColServicePrix;

    @FXML
    private Label menuTotale;

    @FXML
    private Button menuEffacerBtn;

    @FXML
    private Button menuPayerBtn;

    @FXML
    private Button menuImprimerBtn;
    
    @FXML
    private Button menuAjouterDannsCompteBtn;

    @FXML
    private ScrollPane menu_scrolPane;

    @FXML
    private GridPane menu_gridPane;
    
    
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


    //Historique
    @FXML
    private AnchorPane historiqueForm;

    @FXML
    private TableView<?> historiqueTableview;

    @FXML
    private TableColumn<?, ?> historiqueDateCol;

    @FXML
    private TableColumn<?, ?> historiqueActiviteCol;

    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Connection connect;

    private Image imageClient;

    private Image imageService;

    //Ajout nouveau client (efa mandeha)
    public void addClientAdd() {
        
        Date DateInscrp = new Date(); 
        java.sql.Date sqlDate = new java.sql.Date(DateInscrp.getTime());

        java.util.Date javaDate = java.util.Date.from(clientDateN.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateN = sdf.format(javaDate);

        String sql = "INSERT INTO client"
                + "(clientID, clientNom, clientPrenom, clientGenre, clientDateN, clientCNI, clientEtablissement,"
                + "clientMention, clientNiveau, clientCodage, clientPassant, clientTel, clientAdresse, clientPhoto, DateInscrp)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        connect = database.ConnectDb();

        try {
            Alert alert;

            if (clientID.getText().isEmpty()
                    || clientNom.getText().isEmpty()
                    || clientPrenom.getText().isEmpty()
                    || clientGenre.getSelectionModel().getSelectedItem() == null
                    || dateN == null
                    || clientCNI.getText().isEmpty()
                    || clientEtablissement.getSelectionModel().getSelectedItem() == null
                    || clientMention.getText().isEmpty()
                    || clientNiveau.getSelectionModel().getSelectedItem() == null
                    || clientCodage.getText().isEmpty()
                    || clientPassant.getSelectionModel().getSelectedItem() == null
                    || clientTel.getText().isEmpty()
                    || clientAdress.getText().isEmpty()
                    || sqlDate == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez complètez ces cases!");
                alert.showAndWait();
            } else {
                String check = "SELECT clientID FROM client WHERE clientID = '"
                        + clientID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Cette ClientID:" + clientID.getText() + "existe déjà!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, clientID.getText());
                    prepare.setString(2, clientNom.getText());
                    prepare.setString(3, clientPrenom.getText());
                    prepare.setString(4, (String) clientGenre.getSelectionModel().getSelectedItem());
                    prepare.setString(5, String.valueOf(dateN));
                    prepare.setString(6, clientCNI.getText());
                    prepare.setString(7, (String) clientEtablissement.getSelectionModel().getSelectedItem());
                    prepare.setString(8, clientMention.getText());
                    prepare.setString(9, (String) clientNiveau.getSelectionModel().getSelectedItem());
                    prepare.setString(10, clientCodage.getText());
                    prepare.setString(11, (String) clientPassant.getSelectionModel().getSelectedItem());
                    prepare.setString(12, clientTel.getText());
                    prepare.setString(13, clientAdress.getText());

                    //Pour la photo du client
                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(14, uri);
                    prepare.setString(15, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    //Pour la base de donner du faireServiceClient
                    /*  String insertFaireServiceClient = "INSERT INTO  client "
                            + "(clientID, clientNom, clientPrenom)"
                            + "VALUES(?,?,?)";
                    
                    prepare = connect.prepareStatement(insertFaireServiceClient);
                    prepare.setString(1, clientID.getText());
                    prepare.setString(2, clientNom.getText());
                    prepare.setString(3, clientPrenom.getText());
                    prepare.executeUpdate();  */
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Ajout de client réussi avec succès!");
                    alert.showAndWait();

                    addClientShowListData();
                    addClientReset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Mise à jour de client (EFA MANDEHA)
    public void addClientUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        Date DateInscrp = new Date(); 
        java.sql.Date sqlDate = new java.sql.Date(DateInscrp.getTime());
        
        java.util.Date javaDate = java.util.Date.from(clientDateN.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateN = sdf.format(javaDate);

        String sql = "UPDATE client SET clientNom = '"
                + clientNom.getText() + "', clientPrenom = '"
                + clientPrenom.getText() + "', clientGenre = '"
                + clientGenre.getSelectionModel().getSelectedItem() + "', clientDateN = '"
                + dateN + "', clientCNI = '"
                + clientCNI.getText() + "',  clientEtablissement = '"
                + clientEtablissement.getSelectionModel().getSelectedItem() + "', clientMention = '"
                + clientMention.getText() + "', clientNiveau ='"
                + clientNiveau.getSelectionModel().getSelectedItem() + "', clientCodage = '"
                + clientCodage.getText() + "', clientPassant = '"
                + clientPassant.getSelectionModel().getSelectedItem() + "', clientTel = '"
                + clientTel.getText() + "', clientAdresse = '"
                + clientAdress.getText() + "', clientPhoto = '"
                + uri + "', DateInscrp = '"
                + sqlDate + "' WHERE clientID = '"
                + clientID.getText() + "'";

        connect = database.ConnectDb();

        try {
            Alert alert;
            if (clientID.getText().isEmpty()
                    || clientNom.getText().isEmpty()
                    || clientPrenom.getText().isEmpty()
                    || clientGenre.getSelectionModel().getSelectedItem() == null
                    || dateN == null
                    || clientCNI.getText().isEmpty()
                    || clientEtablissement.getSelectionModel().getSelectedItem() == null
                    || clientMention.getText().isEmpty()
                    || clientNiveau.getSelectionModel().getSelectedItem() == null
                    || clientCodage.getText().isEmpty()
                    || clientPassant.getSelectionModel().getSelectedItem() == null
                    || clientTel.getText().isEmpty()
                    || clientAdress.getText().isEmpty()
                    || getData.path == null || getData.path == ""
                    || sqlDate == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez complètez ces cases!");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sur de vouloir modifier cette client " + clientID.getText() + " ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    //Pour la base de donner du faireServiceClient
                    /* String updateFaireserviceClient = "UPDATE client SET clientNom = '"
                                    +clientNom.getText()+"', clientPrenom = '"
                                    +clientPrenom.getText()+"'";
                    
                    prepare = connect.prepareStatement(updateFaireserviceClient);
                    prepare.executeUpdate(); */
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Client a été modifier avec succès!");
                    alert.showAndWait();

                    addClientShowListData();
                    addClientReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Supprimer un client (efa mandeha) 
    public void addClientDelete() {
        try {
            if (clientID.getText().isEmpty() || clientNom.getText().isEmpty() || getData.path == null || getData.path.equals("")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Tous les champs doivent être remplis!");
                alert.showAndWait();
                return;
            }
            
            Date DateInscrp = new Date(); 
            java.sql.Date sqlDate = new java.sql.Date(DateInscrp.getTime());

            String uri = getData.path.replace("\\", "\\\\");
            
            java.util.Date javaDate = java.util.Date.from(clientDateN.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateN = sdf.format(javaDate);

            String sql = "DELETE FROM client WHERE clientID = '" + clientID.getText() + "'";

            connect = database.ConnectDb();
            if (connect == null) {
                // Gérer l'échec de la connexion à la base de données
                return;
            }

            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Message de confirmation");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer ce client " + clientID.getText() + " ?");
            Optional<ButtonType> option = confirmationAlert.showAndWait();

            if (option.isPresent() && option.get() == ButtonType.OK) {
                statement = connect.createStatement();
                statement.executeUpdate(sql);

                //Pour la base de donner du faireServiceClient
                /*   String deleteFaireserviceClient = "DELETE FROM client WHERE clientID = '" + clientID.getText() + "'";
                
                prepare = connect.prepareStatement(deleteFaireserviceClient);
                prepare.executeUpdate();  */
                Alert successAlert = new Alert(AlertType.INFORMATION);
                successAlert.setTitle("Message d'information");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Client supprimé avec succès!");
                successAlert.showAndWait();

                addClientShowListData();
                addClientReset();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'exception
        }
    }

    //Pour l'actualisation sur affiche client
    public void addClientReset() {
        clientID.setText("");
        clientNom.setText("");
        clientPrenom.setText("");
        clientGenre.getSelectionModel().clearSelection();
        clientDateN.setValue(null);
        clientCNI.setText("");
        clientEtablissement.getSelectionModel().clearSelection();
        clientMention.setText("");
        clientNiveau.getSelectionModel().clearSelection();
        clientCodage.setText("");
        clientPassant.getSelectionModel().clearSelection();
        clientTel.setText("");
        clientAdress.setText("");
        clientImageView.setImage(null);
        getData.path = "";
    }

    //Insertion image client
    public void addClientInsertImage() {

        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            getData.path = file.getAbsolutePath();

            imageClient = new Image(file.toURI().toString(), 186, 150, false, true);
            clientImageView.setImage(imageClient);
        }
    }

    //Pour combox genre client
    private String[] genreList = {"Feminin", "Masculin", "Autres"};

    public void addClientgenreList() {
        List<String> listG = new ArrayList<>();

        for (String data : genreList) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        clientGenre.setItems(listData);
    }

    //Pour combox etablissement du client
    private String[] etablissementList = {"FAC DEG", "FST", "FLSH", "FAC MEDCINE", "ISSED", "ENS", "ISPG", "CONFICIUS"};

    public void addClientetablissementList() {
        List<String> listE = new ArrayList<>();

        for (String data : etablissementList) {
            listE.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listE);
        clientEtablissement.setItems(listData);
    }

    //Pour combox niveau du client
    private String[] niveauList = {"L1", "L2", "L3", "M1", "M2", "D1", "D2", "D3"};

    public void addClientniveauList() {
        List<String> listN = new ArrayList<>();

        for (String data : niveauList) {
            listN.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listN);
        clientNiveau.setItems(listData);
    }

    //Pour combox Passant ou redoublant de client
    private String[] passantList = {"Passant(e)", "Rédoublant(e)", "Triplant(e)", "Quadriplant(e)"};

    public void addClientpassantList() {
        List<String> listP = new ArrayList<>();

        for (String data : passantList) {
            listP.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listP);
        clientPassant.setItems(listData);
    }

    //Pour la barre de recherche d'un client (efa mandeha)
    public void addClientSearch() {

        FilteredList<clientData> filter = new FilteredList<>(addClientList, e -> true);

        clientRechercheText.textProperty().addListener((observable, oldValue, newValue) -> {

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
                } else if (predicateClientData.getClientGenre().toLowerCase().contains(searchKey)) {

                    return true;
                } else if (predicateClientData.getClientDateN().toString().contains(searchKey)) {

                    return true;
                } else if (predicateClientData.getClientCNI().toString().contains(searchKey)) {

                    return true;
                } else if (predicateClientData.getClientEtablissement().toLowerCase().contains(searchKey)) {

                    return true;
                } else if (predicateClientData.getClientMention().toLowerCase().contains(searchKey)) {

                    return true;
                } else if (predicateClientData.getClientNiveau().toLowerCase().contains(searchKey)) {

                    return true;
                } else if (predicateClientData.getClientCodage().toLowerCase().contains(searchKey)) {

                    return true;
                } else if (predicateClientData.getClientPassant().toLowerCase().contains(searchKey)) {

                    return true;
                } else if (predicateClientData.getClientTel().toLowerCase().contains(searchKey)) {

                    return true;
                } else if (predicateClientData.getClientAdresse().toLowerCase().contains(searchKey)) {

                    return true;
                    
                }else if (predicateClientData.getDateInscrp().toString().contains(searchKey)) {

                   return true; 
                }else {
                    return false;
                }
            });

        });

        SortedList<clientData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(clientTableView.comparatorProperty());
        clientTableView.setItems(sortList);
    }

    //Observation des données client sur la tableView.
    public ObservableList<clientData> addClientListData() {

        ObservableList<clientData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM client";

        connect = database.ConnectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            clientData clientD;

            while (result.next()) {
                clientD = new clientData(
                        result.getInt("clientID"),
                        result.getString("clientNom"),
                        result.getString("clientPrenom"),
                        result.getString("clientGenre"),
                        result.getDate("clientDateN"),
                        result.getString("clientCNI"),
                        result.getString("clientEtablissement"),
                        result.getString("clientMention"),
                        result.getString("clientNiveau"),
                        result.getString("clientCodage"),
                        result.getString("clientPassant"),
                        result.getString("clientTel"),
                        result.getString("clientAdresse"),
                        result.getString("clientPhoto"),
                        result.getDate("DateInscrp")
                );
                listData.add(clientD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<clientData> addClientList;

    //Afficher les listes des clients sur Tables view
    public void addClientShowListData() {
        addClientList = addClientListData();

        clientIDCol.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        clientNomCol.setCellValueFactory(new PropertyValueFactory<>("clientNom"));
        clientPrenomCol.setCellValueFactory(new PropertyValueFactory<>("clientPrenom"));
        clientGenreCol.setCellValueFactory(new PropertyValueFactory<>("clientGenre"));
        clientDateNCol.setCellValueFactory(new PropertyValueFactory<>("clientDateN"));
        clientCINCol.setCellValueFactory(new PropertyValueFactory<>("clientCNI"));
        clientEtablissementCol.setCellValueFactory(new PropertyValueFactory<>("clientEtablissement"));
        clientMentionCol.setCellValueFactory(new PropertyValueFactory<>("clientMention"));
        clientNiveauCol.setCellValueFactory(new PropertyValueFactory<>("clientNiveau"));
        clientCodageCol.setCellValueFactory(new PropertyValueFactory<>("clientCodage"));
        clientPassantCol.setCellValueFactory(new PropertyValueFactory<>("clientPassant"));
        clientTelCol.setCellValueFactory(new PropertyValueFactory<>("clientTel"));
        clientAdressCol.setCellValueFactory(new PropertyValueFactory<>("clientAdresse"));
        clientDateCol.setCellValueFactory(new PropertyValueFactory<>("DateInscrp"));

        clientTableView.setItems(addClientList);
    }

    //Selection client via Table View (Mbola misy erreur) 
    public void addClientSelect() {
        clientData clientD = clientTableView.getSelectionModel().getSelectedItem();
        int num = clientTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        clientID.setText(String.valueOf(clientD.getClientID())); // Assurez-vous que clientID est un TextField ou un contrôle similaire
        clientNom.setText(clientD.getClientNom());
        clientPrenom.setText(clientD.getClientPrenom());

        clientCNI.setText(clientD.getClientCNI());
        clientMention.setText(clientD.getClientMention());
        clientCodage.setText(clientD.getClientCodage());
        clientTel.setText(clientD.getClientTel());
        clientAdress.setText(clientD.getClientAdresse());

        //Pour image
        getData.path = clientD.getClientPhoto();
        String uri = "file:" + clientD.getClientPhoto();
        imageClient = new Image(uri, 186, 150, false, true);
        clientImageView.setImage(imageClient);
    }

    //Sur le nom d'utilisateur
    public void displayUsername() {
        NomUtilisateur.setText(getData.username);
    }

    /**
     * ********************************************************************************
     * ********************************************************************************
     * *********************************SEPARATION*************************************
     * ********************************************************************************
     * ********************************************************************************
     *********************************************************************************
     */
    //Section pour le service  
    //Insertion image service
    public void addServiceInsertImage() {

        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            getData.pathService = file.getAbsolutePath();

            imageService = new Image(file.toURI().toString(), 200, 160, false, true);
            serviceImageView.setImage(imageService);
        }
    }

    //Ajout nouveau service
    public void addServiceAdd() {

        java.util.Date javaDate1 = java.util.Date.from(serviceDateDebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateD = sdf1.format(javaDate1);

        java.util.Date javaDate2 = java.util.Date.from(serviceDateFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String dateF = sdf2.format(javaDate2);

        String sql = "INSERT INTO service"
                + "(serviceID, serviceNom, serviceType, servicePrix, serviceDuree, serviceDateDebut, serviceDateFin, serviceImage)"
                + "VALUES(?,?,?,?,?,?,?,?)";

        connect = database.ConnectDb();

        try {
            Alert alert;

            if (serviceID.getText().isEmpty()
                    || serviceNom.getText().isEmpty()
                    || serviceType.getText().isEmpty()
                    || servicePrix.getText().isEmpty()
                    || serviceDuree.getText().isEmpty()
                    || dateD == null
                    || dateF == null
                    || getData.pathService == null || getData.pathService == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez complètez ces formulaires!");
                alert.showAndWait();
            } else {
                String check = "SELECT serviceID FROM service WHERE serviceID = '"
                        + serviceID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Cette serviceID: " + serviceID.getText() + " existe déjà!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, serviceID.getText());
                    prepare.setString(2, serviceNom.getText());
                    prepare.setString(3, serviceType.getText());
                    prepare.setString(4, servicePrix.getText());
                    prepare.setString(5, serviceDuree.getText());
                    prepare.setString(6, String.valueOf(dateD));
                    prepare.setString(7, String.valueOf(dateF));

                    //Pour la photo du client
                    String uri = getData.pathService;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(8, uri);
                    prepare.executeUpdate();

                    //Pour la base de donner du faireServiceClient
                    /*    String insertFaireServiceService = "INSERT INTO  service "
                            + "(serviceID, serviceNom, servicePrix)"
                            + "VALUES(?,?,?)";
                    
                    prepare = connect.prepareStatement(insertFaireServiceService);
                    prepare.setString(1, serviceID.getText());
                    prepare.setString(2, serviceNom.getText());
                    prepare.setString(4, servicePrix.getText());
                    prepare.executeUpdate();  */
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Ajout de service réussi avec succès!");
                    alert.showAndWait();

                    addServiceShowListData();
                    addServiceReset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Pour la mise à jour des services (Efa mandeha)
    public void addServiceUpdate() {

        String uri = getData.pathService;
        uri = uri.replace("\\", "\\\\");

        java.util.Date javaDate1 = java.util.Date.from(serviceDateDebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateD = sdf1.format(javaDate1);

        java.util.Date javaDate2 = java.util.Date.from(serviceDateFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String dateF = sdf2.format(javaDate2);

        String sql = "UPDATE service SET serviceNom = '"
                + serviceNom.getText() + "', serviceType = '"
                + serviceType.getText() + "', servicePrix = '"
                + servicePrix.getText() + "', serviceDuree = '"
                + serviceDuree.getText() + "', serviceDateDebut = '"
                + dateD + "', serviceDateFin = '"
                + dateF + "',  serviceImage = '"
                + uri + "' WHERE serviceID = '"
                + serviceID.getText() + "'";

        connect = database.ConnectDb();

        try {
            Alert alert;
            if (serviceID.getText().isEmpty()
                    || serviceNom.getText().isEmpty()
                    || serviceType.getText().isEmpty()
                    || servicePrix.getText().isEmpty()
                    || serviceDuree.getText().isEmpty()
                    || dateD == null
                    || dateF == null
                    || getData.pathService == null || getData.pathService == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez complètez ces formulaires!");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sur de vouloir modifier cette client " + serviceID.getText() + " ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    //Pour la base de donner du faireServiceClient
                    /*   String updateFaireserviceService = "UPDATE service SET serviceNom = '"
                                    +serviceNom.getText()+"', servicePrix = '"
                                    +servicePrix.getText()+"'";
                    
                    prepare = connect.prepareStatement(updateFaireserviceService);
                    prepare.executeUpdate(); */
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Service a été modifier avec succès!");
                    alert.showAndWait();

                    addServiceShowListData();
                    addServiceReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Supprimer un client (efa mandeha) 
    public void addServiceDelete() {
        try {
            if (serviceID.getText().isEmpty() || serviceNom.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Tous les champs doivent être remplis!");
                alert.showAndWait();
                return;
            }

            String uri = getData.pathService.replace("\\", "\\\\");

            String sql = "DELETE FROM service WHERE serviceID = '" + serviceID.getText() + "'";

            connect = database.ConnectDb();
            if (connect == null) {
                // Gérer l'échec de la connexion à la base de données
                return;
            }

            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Message de confirmation");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer ce client " + serviceID.getText() + " ?");
            Optional<ButtonType> option = confirmationAlert.showAndWait();

            if (option.isPresent() && option.get() == ButtonType.OK) {
                statement = connect.createStatement();
                statement.executeUpdate(sql);

                //Pour la base de donner du faireServiceClient
                /*   String deleteFaireserviceService = "DELETE FROM service WHERE serviceID = '" + serviceID.getText() + "'";
                
                prepare = connect.prepareStatement(deleteFaireserviceService);
                prepare.executeUpdate();  */
                Alert successAlert = new Alert(AlertType.INFORMATION);
                successAlert.setTitle("Message d'information");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Service a été supprimé avec succès!");
                successAlert.showAndWait();

                addServiceShowListData();
                addServiceReset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Pour la barre de recherche d'un service (efa mandeha)
    public void addServiceSearch() {

        FilteredList<serviceData> filter = new FilteredList<>(addServiceList, e -> true);

        serviceTextFieldRecherche.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateServiceData -> {

                if (newValue == null || newValue.isEmpty()) {

                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateServiceData.getServiceID().toString().contains(searchKey)) {

                    return true;

                } else if (predicateServiceData.getServiceNom().toLowerCase().contains(searchKey)) {

                    return true;

                } else if (predicateServiceData.getServiceType().toLowerCase().contains(searchKey)) {

                    return true;
                } else if (predicateServiceData.getServicePrix().toString().contains(searchKey)) {

                    return true;
                } else if (predicateServiceData.getServiceDuree().toLowerCase().contains(searchKey)) {

                    return true;
                } else if (predicateServiceData.getServiceDateDebut().toString().contains(searchKey)) {

                    return true;
                } else if (predicateServiceData.getServiceDateFin().toString().contains(searchKey)) {

                    return true;
                } else {
                    return false;
                }
            });

        });

        SortedList<serviceData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(serviceTableView.comparatorProperty());
        serviceTableView.setItems(sortList);
    }

    //Pour l'actualisation de l'affichage du service (Efa mandeha) 
    public void addServiceReset() {
        serviceID.setText("");
        serviceNom.setText("");
        serviceType.setText("");
        servicePrix.setText("");
        serviceDuree.setText("");
        serviceDateDebut.setValue(null);
        serviceDateFin.setValue(null);
        serviceImageView.setImage(null);
        getData.pathService = "";
    }

    //Observation des données service sur la tableView. (efa mandeha)
    public ObservableList<serviceData> addServiceListData() {

        ObservableList<serviceData> listDataS = FXCollections.observableArrayList();
        String sqlS = "SELECT * FROM  service";

        connect = database.ConnectDb();

        try {
            prepare = connect.prepareStatement(sqlS);
            result = prepare.executeQuery();
            serviceData serviceD;

            while (result.next()) {
                serviceD = new serviceData(
                        result.getInt("serviceID"),
                        result.getString("serviceNom"),
                        result.getString("serviceType"),
                        result.getDouble("servicePrix"),
                        result.getString("serviceDuree"),
                        result.getDate("serviceDateDebut"),
                        result.getDate("serviceDateFin"),
                        result.getString("serviceImage")
                );
                listDataS.add(serviceD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDataS;
    }

    private ObservableList<serviceData> addServiceList;

    //Afficher les listes des services sur Tables view
    public void addServiceShowListData() {

        addServiceList = addServiceListData();

        serviceIDCol.setCellValueFactory(new PropertyValueFactory<>("serviceID"));
        serviceNomCol.setCellValueFactory(new PropertyValueFactory<>("serviceNom"));
        serviceTypeCol.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
        servicePrixCol.setCellValueFactory(new PropertyValueFactory<>("servicePrix"));
        serviceDureeCol.setCellValueFactory(new PropertyValueFactory<>("serviceDuree"));
        serviceDateDebutCol.setCellValueFactory(new PropertyValueFactory<>("serviceDateDebut"));
        serviceDateFinCol.setCellValueFactory(new PropertyValueFactory<>("serviceDateFin"));

        serviceTableView.setItems(addServiceList);
    }

    //Selection service via Table View
    public void addServiceSelect() {
        serviceData serviceD = serviceTableView.getSelectionModel().getSelectedItem();
        int num = serviceTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        serviceID.setText(String.valueOf(serviceD.getServiceID()));
        serviceNom.setText(serviceD.getServiceNom());
        serviceType.setText(serviceD.getServiceType());
        servicePrix.setText(String.valueOf(serviceD.getServicePrix()));
        serviceDuree.setText(serviceD.getServiceDuree());

        //Pour image service
        getData.pathService = serviceD.getServiceImage();
        String uri = "file:" + serviceD.getServiceImage();
        imageService = new Image(uri, 186, 150, false, true);
        serviceImageView.setImage(imageService);
    }

    /**
     * ********************************************************************************
     * ********************************************************************************
     * *********************************SEPARATION*************************************
     * ********************************************************************************
     * ********************************************************************************
     *********************************************************************************
     */
    //Section faire un service
    //Observation des données faire service client sur la tableView.
    public ObservableList<clientData> faireServiceClientListData() {

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

    private ObservableList<clientData> faireServiceClientList;

    //Afficher les listes des clients sur Tables view
    public void faireServiceClientShowListData() {
        faireServiceClientList = faireServiceClientListData();

        faireServiceIDClientCol.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        faireServiceNomClientCol.setCellValueFactory(new PropertyValueFactory<>("clientNom"));
        faireServicePrenomClientCol.setCellValueFactory(new PropertyValueFactory<>("clientPrenom"));

        faireServiceClientTableView.setItems(faireServiceClientList);
    }

    //Selection client via Table View 
    public void faireServiceClientSelect() {
        clientData clientD = faireServiceClientTableView.getSelectionModel().getSelectedItem();
        int num = faireServiceClientTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        faireServiceClientID.setText(String.valueOf(clientD.getClientID())); // Assurez-vous que clientID est un TextField ou un contrôle similaire
        faireServiceClientNom.setText(clientD.getClientNom());
        faireServiceClientPrenom.setText(clientD.getClientPrenom());
    }

    //Pour l'actualisation de l'affichage du faire service service (Efa mandeha) 
    public void faireServiceClientReset() {
        faireServiceClientID.setText("");
        faireServiceClientNom.setText("");
        faireServiceClientPrenom.setText("");
    }

    //Observation des données faire service client sur la tableView.
    public ObservableList<serviceData> faireServiceServiceListData() {

        ObservableList<serviceData> listDataS = FXCollections.observableArrayList();
        String sql = "SELECT * FROM  service";

        connect = database.ConnectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            serviceData serviceD;

            while (result.next()) {
                serviceD = new serviceData(
                        result.getInt("serviceID"),
                        result.getString("serviceNom"),
                        result.getDouble("servicePrix")
                );
                listDataS.add(serviceD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDataS;
    }

    private ObservableList<serviceData> faireServiceServiceList;

    //Afficher les listes des services sur Tables view faireservice
    public void faireServiceServiceShowListData() {
        faireServiceServiceList = faireServiceServiceListData();

        faireServiceIDServiceCol.setCellValueFactory(new PropertyValueFactory<>("serviceID"));
        faireServiceNomServiceCol.setCellValueFactory(new PropertyValueFactory<>("serviceNom"));
        faireServicePrixServiceCol.setCellValueFactory(new PropertyValueFactory<>("servicePrix"));

        faireServiceServiceTableView.setItems(faireServiceServiceList);
    }

    //Selection service via Table View
    public void faireServiceServiceSelect() {
        serviceData serviceD = faireServiceServiceTableView.getSelectionModel().getSelectedItem();
        int num = faireServiceServiceTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        faireServiceServiceID.setText(String.valueOf(serviceD.getServiceID()));
        faireServiceServiceNom.setText(serviceD.getServiceNom());
        faireServiceServicePrix.setText(String.valueOf(serviceD.getServicePrix()));
    }

    //Pour l'actualisation de l'affichage du faire service service (Efa mandeha) 
    public void faireServiceServiceReset() {
        faireServiceServiceID.setText("");
        faireServiceServiceNom.setText("");
        faireServiceServicePrix.setText("");
    }

    //Pour la barre de recherche d'un faire service client (efa mandeha)
    public void faireServiceClientSearch() {

        FilteredList<clientData> filter = new FilteredList<>(faireServiceClientList, e -> true);

        faireServiceClientRechercheText.textProperty().addListener((observable, oldValue, newValue) -> {

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

        sortList.comparatorProperty().bind(faireServiceClientTableView.comparatorProperty());
        faireServiceClientTableView.setItems(sortList);
    }

    //Pour la barre de recherche d'un faire service service (efa mandeha)
    public void faireServiceServiceSearch() {

        FilteredList<serviceData> filter = new FilteredList<>(faireServiceServiceList, e -> true);

        faireServiceServiceRechercheText.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateServiceData -> {

                if (newValue == null || newValue.isEmpty()) {

                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateServiceData.getServiceID().toString().contains(searchKey)) {

                    return true;

                } else if (predicateServiceData.getServiceNom().toLowerCase().contains(searchKey)) {

                    return true;

                } else if (predicateServiceData.getServicePrix().toString().contains(searchKey)) {

                    return true;
                } else {
                    return false;
                }
            });

        });
        SortedList<serviceData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(faireServiceServiceTableView.comparatorProperty());
        faireServiceServiceTableView.setItems(sortList);
    }
    
    
    //section Menu Service

    //Liste service sur la menu card
    private ObservableList<serviceData> cardListData = FXCollections.observableArrayList();

    public ObservableList<serviceData> menuGetData() {

        ObservableList<serviceData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM  service";

        connect = database.ConnectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            serviceData serveData;

            while (result.next()) {
                serveData = new serviceData(
                        result.getInt("serviceID"),
                        result.getString("serviceNom"),
                        result.getDouble("servicePrix"),
                        result.getString("serviceImage")
                );
                listData.add(serveData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    public void menuDisplayCard() {
        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;
        
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();

        for (int q = 0; q < cardListData.size(); q++) {

            try {

                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/Interface/cardService.fxml"));
                AnchorPane pane = load.load();
                CardServiceController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                if (column == 3) {
                    column = 0;
                    row += 1;
                }
                
                menu_gridPane.add(pane, column++, row);
                
                GridPane.setMargin(pane, new Insets(10));

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    
    
    //Observation de la liste des clients dans menu tableview client
    public ObservableList<clientData> menuClientListData() {

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
    
    
    private ObservableList<clientData> menuClientList;

    //Afficher les listes des clients sur menu Tables view
    public void menuClientShowListData() {
        menuClientList = faireServiceClientListData();

        menuColClientID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        menuColClientNom.setCellValueFactory(new PropertyValueFactory<>("clientNom"));
        menuColClientPrenom.setCellValueFactory(new PropertyValueFactory<>("clientPrenom"));

        menu_tableViewClient.setItems(menuClientList);
    }
    
    //Selection client via Table View 
    public void menuClientSelect() {
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
    public void menuClientReset() {
        menuClientID.setText("");
        menuClientNom.setText("");
        menuClientPrenom.setText("");
    }
    
    //Pour la barre de recherche sur menu service dans client (efa mandeha)
    public void menuClientSearch() {

        FilteredList<clientData> filter = new FilteredList<>(faireServiceClientList, e -> true);

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
    
    
    
    //Relier les fenêtres
    public void SwitchForm(ActionEvent event) {

        if (event.getSource() == acceuilBtn) {
            acceuilForm.setVisible(true);
            clientForm.setVisible(false);
            serviceForm.setVisible(false);
            makeServiceForm.setVisible(false);
            suivieForm.setVisible(false);
            factureForm.setVisible(false);
            historiqueForm.setVisible(false);

            acceuilBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #f1f1f1, #f1f1f1);");
            clientBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            serviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            makeserviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            suivieBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            factureBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            historiqueBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");

        } else if (event.getSource() == clientBtn) {
            acceuilForm.setVisible(false);
            clientForm.setVisible(true);
            serviceForm.setVisible(false);
            makeServiceForm.setVisible(false);
            suivieForm.setVisible(false);
            factureForm.setVisible(false);
            historiqueForm.setVisible(false);

            clientBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #f1f1f1, #f1f1f1);");
            acceuilBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            serviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            makeserviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            suivieBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            factureBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            historiqueBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");

            //Pour les combox dans clients
            addClientgenreList();
            addClientetablissementList();
            addClientniveauList();
            addClientpassantList();

            //Barre de recherche des clients
            addClientSearch();

        } else if (event.getSource() == serviceBtn) {
            acceuilForm.setVisible(false);
            clientForm.setVisible(false);
            serviceForm.setVisible(true);
            makeServiceForm.setVisible(false);
            suivieForm.setVisible(false);
            factureForm.setVisible(false);
            historiqueForm.setVisible(false);

            serviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #f1f1f1, #f1f1f1);");
            clientBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            acceuilBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            makeserviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            suivieBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            factureBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            historiqueBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");

            //Pour la liste des clients dans la base de donnée
            addServiceShowListData();

            //Pour la barre de recherche des services
            addServiceSearch();

        } else if (event.getSource() == makeserviceBtn) {
            acceuilForm.setVisible(false);
            clientForm.setVisible(false);
            serviceForm.setVisible(false);
            makeServiceForm.setVisible(true);
            suivieForm.setVisible(false);
            factureForm.setVisible(false);
            historiqueForm.setVisible(false);

            makeserviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #f1f1f1, #f1f1f1);");
            clientBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            serviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            acceuilBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            suivieBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            factureBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            historiqueBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");

            //Pour la liste des client au nivea de faire un service
            faireServiceClientShowListData();
            faireServiceClientSearch();
            faireServiceClientSelect();

            //Pour la liste des services au niveau de faire un service
            faireServiceServiceShowListData();
            faireServiceServiceSearch();
            faireServiceServiceSelect();

        } else if (event.getSource() == suivieBtn) {
            acceuilForm.setVisible(false);
            clientForm.setVisible(false);
            serviceForm.setVisible(false);
            makeServiceForm.setVisible(false);
            suivieForm.setVisible(true);
            factureForm.setVisible(false);
            historiqueForm.setVisible(false);

            suivieBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #f1f1f1, #f1f1f1);");
            clientBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            serviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            makeserviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            acceuilBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            factureBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            historiqueBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");

        } else if (event.getSource() == factureBtn) {
            acceuilForm.setVisible(false);
            clientForm.setVisible(false);
            serviceForm.setVisible(false);
            makeServiceForm.setVisible(false);
            suivieForm.setVisible(false);
            factureForm.setVisible(true);
            historiqueForm.setVisible(false);

            factureBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #f1f1f1, #f1f1f1);");
            clientBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            serviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            makeserviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            suivieBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            acceuilBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            historiqueBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            
            //Pour menu affiche produit
            menuDisplayCard();
            
            //Sur les méthodes concernant les clients dans facture menu service
            menuClientShowListData();
            menuClientSearch();
            menuClientSelect();

        } else if (event.getSource() == historiqueBtn) {
            acceuilForm.setVisible(false);
            clientForm.setVisible(false);
            serviceForm.setVisible(false);
            makeServiceForm.setVisible(false);
            suivieForm.setVisible(false);
            factureForm.setVisible(false);
            historiqueForm.setVisible(true);

            historiqueBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #f1f1f1, #f1f1f1);");
            clientBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            serviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            makeserviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            suivieBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            factureBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            acceuilBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
        }
    }

    private double x = 0;
    private double y = 0;

    //Pour la boutton de déconnexion
    public void logout() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Message de confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sur de se déconnecter");
        Optional<ButtonType> option = alert.showAndWait();

        try {
            if (option.get().equals(ButtonType.OK)) {

                signOutBtn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/Interface/FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Pour la fermeture du fenetre
    public void close() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Message de confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sur de vouloir quitter le programme? \n Vous n'êtes pas encore déconnecter");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get().equals(ButtonType.OK)) {
            System.exit(0);
        }
    }

    //Pour la réduction d'une fenetre
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Pour nom d'utilisateur dans dashboard
        displayUsername();

        //Pour voir listes client dans TableView
        addClientShowListData();

        //Pour voir liste service dans TableView
        addServiceShowListData();
        
        //Pour menu affiche produit
        menuDisplayCard();
        
        //Pour menu des clients
        menuClientShowListData();

        //Pour les combox dans clients
        addClientgenreList();
        addClientetablissementList();
        addClientniveauList();
        addClientpassantList();

        //Pour la liste des client au nivea de faire un service
        faireServiceClientShowListData();

        //Pour la liste des services au niveau de faire un service
        faireServiceServiceShowListData();
        
        
    }

}
