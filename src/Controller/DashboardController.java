/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import jirehstudentsapp.clientData;
import jirehstudentsapp.database;
import jirehstudentsapp.getData;
import jirehstudentsapp.historiqueData;
import jirehstudentsapp.serviceData;
import jirehstudentsapp.suivieData;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

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
    private Label acceuilTotaleFaireService;

    @FXML
    private Label acceuilTotaleService;

    @FXML
    private Label acceuilTotaleSolde;

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
    private TableColumn<clientData, String> clientTypeCol;

    @FXML
    private TableColumn<clientData, String> clientDateCol;

    @FXML
    private TableColumn<clientData, String> clientNomUtilisateurCol;

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
    private ComboBox<String> clientType;

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
    private TableColumn<serviceData, String> serviceNomUtlisateurCol;

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

    //Suivie client
    @FXML
    private TextField suivieRechercheText1;

    @FXML
    private TableView<suivieData> suivieTableView1;

    @FXML
    private TableColumn<suivieData, String> suivieClientDateCol;

    @FXML
    private TableColumn<suivieData, String> suivieClientIDCol;

    @FXML
    private TableColumn<suivieData, String> suivieClientNomCol;

    @FXML
    private TableColumn<suivieData, String> suivieServiceNomCol;

    @FXML
    private TableColumn<suivieData, String> suivieServiceQteCol;

    @FXML
    private TableColumn<suivieData, String> suivieServicePrixUnitCol;

    @FXML
    private TableColumn<suivieData, String> suivieServiceToaleSoldeCol;

    @FXML
    private TableColumn<suivieData, String> suivieServiceDejaPayerCol;

    @FXML
    private TableColumn<suivieData, String> suivieServiceRestePayerCol;

    @FXML
    private TableColumn<suivieData, String> suivieServiceNomUtlisateurCol;

    @FXML
    private Button suivieActualiserBtn1;

    @FXML
    private Label suivieClientNom;

    @FXML
    private Label suivieServiceTotaleSolde;

    @FXML
    private Label suivieServiceDejaPayer;

    @FXML
    private Label suivieClientID;

    @FXML
    private Label suivieServiceRestePayer;

    @FXML
    private Label suivieServiceNom;

    @FXML
    private Label suivieServiceQte;

    @FXML
    private Label suivieServicePrix;

    @FXML
    private Button suivieEffacerBtn;

    //Suivie payement
    @FXML
    private TextField soldePayerText;

    @FXML
    private Button suiviePayerCompteBtn;

    @FXML
    private TextField clientSuivieRechercheTextCol;

    @FXML
    private TableView<clientData> clientSuivieTableView;

    @FXML
    private TableColumn<clientData, Integer> clientSuivieIDCol;

    @FXML
    private TableColumn<clientData, String> clientSuivieNomCol;

    @FXML
    private TableColumn<clientData, Double> clientSuivieDejaPayerCol;

    @FXML
    private TableColumn<clientData, Double> clientSuivieRestePayerCol;

    @FXML
    private TextField clientSuivieRechercheTxt;

    @FXML
    private Label clientSuivieNomTxt;

    @FXML
    private Label clientSuivieDejaPayerTxt;

    @FXML
    private Label clientSuivieRestePayerTxt;

    @FXML
    private Label clientSuivieIDTxt;

    @FXML
    private TextField clientsoldePayerText;

    @FXML
    private Label clientsuivieReste;

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
    private TableView<suivieData> menu_tableView;

    @FXML
    private TableColumn<suivieData, String> menuColServiceNom;

    @FXML
    private TableColumn<suivieData, String> menuColServiceQuantite;

    @FXML
    private TableColumn<suivieData, String> menuColServicePrix;

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
    private AnchorPane menu_paneClient;

    //menu client
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

    @FXML
    private TextField menuMonttantPayerTxt;

    @FXML
    private Label menuReste;

    //Historique
    @FXML
    private AnchorPane historiqueForm;

    @FXML
    private TableView<historiqueData> historiqueTableview;

    @FXML
    private TableColumn<historiqueData, String> historiqueDateCol;

    @FXML
    private TableColumn<historiqueData, String> historiqueActiviteCol;

    @FXML
    private TableColumn<historiqueData, String> historiqueNomUtilisateurCol;

    @FXML
    private TextField historiqueTextFieldRecherche;

    @FXML
    private Button historiqueEffacerBtn;

    @FXML
    private Button historiqueEffacerToutBtn;

    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Connection connect;

    private Image imageClient;

    private Image imageService;

    
    
    //Bloc Acceuil
    private int totaleClient;
    
    private static boolean clickFservice = false;

    public int totaleClient() {

        String selectTotaleCli = "SELECT COUNT(id) as totalecli FROM client";

        connect = database.ConnectDb();

        try {

            prepare = connect.prepareStatement(selectTotaleCli);
            result = prepare.executeQuery();

            while (result.next()) {

                totaleClient = result.getInt("totalecli");
                acceuilTotaleClient.setText(String.valueOf(totaleClient));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totaleClient;
    }

    private int totaleService;

    public int totaleService() {

        String selectTotaleCli = "SELECT COUNT(ID) as totaleserv FROM service";

        connect = database.ConnectDb();

        try {

            prepare = connect.prepareStatement(selectTotaleCli);
            result = prepare.executeQuery();

            while (result.next()) {

                totaleService = result.getInt("totaleserv");
                acceuilTotaleService.setText(String.valueOf(totaleService));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totaleService;
    }

    private int totaleFaireService;

    public int totaleFaireService() {

        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        String selectTotaleCli = "SELECT COUNT(ID) as totalesuivie FROM suivieclient WHERE DATE(suivieDate) = '" + sqlDate + "'";

        connect = database.ConnectDb();

        try {

            prepare = connect.prepareStatement(selectTotaleCli);
            result = prepare.executeQuery();

            while (result.next()) {

                totaleFaireService = result.getInt("totalesuivie");
                acceuilTotaleFaireService.setText(String.valueOf(totaleFaireService));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totaleFaireService;
    }

    public double totaleSoldeAujour() {

        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        String selectTotaleCli = "SELECT SUM(suivieServiceDejaPayer) as totaleSolde FROM suivieclient WHERE DATE(suivieDate) = '" + sqlDate + "'";

        connect = database.ConnectDb();
        double totaleSolde = 0;
        try {

            prepare = connect.prepareStatement(selectTotaleCli);
            result = prepare.executeQuery();

            while (result.next()) {

                totaleSolde = result.getDouble("totaleSolde");
                acceuilTotaleSolde.setText(String.valueOf(totaleSolde));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totaleSolde;
    }

     /**
     * ********************************************************************************
     * ********************************************************************************
     * *********************************SEPARATION*************************************
     * ********************************************************************************
     * ********************************************************************************
     * ********************************************************************************
     */
    
    //Bloc client
    public void addClientAdd() throws SQLException {
        Alert alert;
        try {
            // Vérifier si tous les champs sont remplis
            if (clientID.getText().isEmpty()
                    || clientNom.getText().isEmpty()
                    || clientPrenom.getText().isEmpty()
                    || clientGenre.getSelectionModel().isEmpty()
                    || clientDateN.getValue() == null
                    || clientCNI.getText().isEmpty()
                    || clientEtablissement.getSelectionModel().isEmpty()
                    || clientMention.getText().isEmpty()
                    || clientNiveau.getSelectionModel().isEmpty()
                    || clientCodage.getText().isEmpty()
                    || clientPassant.getSelectionModel().isEmpty()
                    || clientTel.getText().isEmpty()
                    || clientAdress.getText().isEmpty()
                    || clientType.getSelectionModel().isEmpty()
                    || getData.path == null || getData.path.isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plait, veuillez remplir tous les champs!");
                alert.showAndWait();
                return; // Sortir de la méthode si un champ est vide
            }

            // Vérifier si le clientID existe déjà
            String check = "SELECT clientID FROM client WHERE clientID = ?";
            connect = database.ConnectDb();
            prepare = connect.prepareStatement(check);
            prepare.setString(1, clientID.getText());
            result = prepare.executeQuery();
            if (result.next()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Ce clientID existe déjà!");
                alert.showAndWait();
                return; // Sortir de la méthode si le clientID existe déjà

            } else {
                // Insertion des données dans la base de données
                String sql = "INSERT INTO client (clientID, clientNom, clientPrenom, clientGenre, clientDateN, clientCNI, clientEtablissement, "
                        + "clientMention, clientNiveau, clientCodage, clientPassant, clientTel, clientAdresse, clientType, clientPhoto, DateInscrp, NomUtilisateur, "
                        + "clientSoldeDejaPayer, clientSoldeRestePayer) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, clientID.getText());
                prepare.setString(2, clientNom.getText());
                prepare.setString(3, clientPrenom.getText());
                prepare.setString(4, clientGenre.getSelectionModel().getSelectedItem());
                prepare.setDate(5, java.sql.Date.valueOf(clientDateN.getValue()));
                prepare.setString(6, clientCNI.getText());
                prepare.setString(7, clientEtablissement.getSelectionModel().getSelectedItem());
                prepare.setString(8, clientMention.getText());
                prepare.setString(9, clientNiveau.getSelectionModel().getSelectedItem());
                prepare.setString(10, clientCodage.getText());
                prepare.setString(11, clientPassant.getSelectionModel().getSelectedItem());
                prepare.setString(12, clientTel.getText());
                prepare.setString(13, clientAdress.getText());
                prepare.setString(14, clientType.getSelectionModel().getSelectedItem());
                prepare.setString(15, getData.path);
                prepare.setDate(16, new java.sql.Date(System.currentTimeMillis()));
                prepare.setString(17, getData.username);
                prepare.setDouble(18, 0.0);
                prepare.setDouble(19, 0.0);

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sur de vouloir ajouter cette client?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    int rowsAffected = prepare.executeUpdate();
                    if (rowsAffected > 0) {

                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Message d'information");
                        alert.setHeaderText(null);
                        alert.setContentText("Client ajouté avec succès!");
                        alert.showAndWait();

                    } else {
                        alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Message d'erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Ajout du client anuler!");
                        alert.showAndWait();
                    }
                } else {
                    alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Ajout du client anuler!");
                    alert.showAndWait();
                }

            }

            // Effacer les champs après l'ajout
            addClientReset();
            // Rafraîchir la liste des clients
            addClientShowListData();

        } catch (SQLException e) {
            e.printStackTrace();
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'ajout du client!");
            alert.showAndWait();
        }

    }

    //Mise à jour de client (EFA MANDEHA)
    public void addClientUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        Date DateInscrp = new Date();
        java.sql.Date sqlDate = new java.sql.Date(DateInscrp.getTime());

        try {
            Alert alert;
            if (clientID.getText().isEmpty()
                    || clientNom.getText().isEmpty()
                    || clientPrenom.getText().isEmpty()
                    || clientGenre.getSelectionModel().getSelectedItem() == null
                    || clientDateN.getValue() == null
                    || clientCNI.getText().isEmpty()
                    || clientEtablissement.getSelectionModel().getSelectedItem() == null
                    || clientMention.getText().isEmpty()
                    || clientNiveau.getSelectionModel().getSelectedItem() == null
                    || clientCodage.getText().isEmpty()
                    || clientPassant.getSelectionModel().getSelectedItem() == null
                    || clientTel.getText().isEmpty()
                    || clientAdress.getText().isEmpty()
                    || clientType.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path.isEmpty()
                    || sqlDate == null) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez sélectionner un client et compléter ces formulaires!");
                alert.showAndWait();

            } else {

                LocalDate dateNaissance = clientDateN.getValue();
                String dateN = dateNaissance != null ? dateNaissance.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;

                String sql = "UPDATE client SET clientNom = ?, clientPrenom = ?, clientGenre = ?, clientDateN = ?, clientCNI = ?, clientEtablissement = ?, clientMention = ?, clientNiveau = ?, clientCodage = ?, clientPassant = ?, clientTel = ?, clientAdresse = ?, clientPhoto = ?, DateInscrp = ?, NomUtilisateur = ? WHERE clientID = ?";
                connect = database.ConnectDb();

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr de vouloir modifier ce client " + clientID.getText() + " ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    PreparedStatement preparedStatement = connect.prepareStatement(sql);
                    preparedStatement.setString(1, clientNom.getText());
                    preparedStatement.setString(2, clientPrenom.getText());
                    preparedStatement.setString(3, clientGenre.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(4, dateN);
                    preparedStatement.setString(5, clientCNI.getText());
                    preparedStatement.setString(6, clientEtablissement.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(7, clientMention.getText());
                    preparedStatement.setString(8, clientNiveau.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(9, clientCodage.getText());
                    preparedStatement.setString(10, clientPassant.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(11, clientTel.getText());
                    preparedStatement.setString(12, clientAdress.getText());
                    preparedStatement.setString(13, uri);
                    preparedStatement.setDate(14, sqlDate);
                    preparedStatement.setString(15, getData.username);
                    preparedStatement.setString(16, clientID.getText());
                    preparedStatement.executeUpdate();

                    historique("Mise à jour du client: " + clientID.getText() + " réussie avec succès.", getData.username);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Le client a été modifié avec succès!");
                    alert.showAndWait();
                } else {
                    historique("Échec! Mise à jour du client: " + clientID.getText() + " annulée.", getData.username);

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Modification du client annulée!");
                    alert.showAndWait();
                }

                addClientShowListData();
                addClientReset();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    
    //Supprimer un client (efa mandeha) 
    public void addClientDelete() {
        Alert alert;
        if (getData.username != null && getData.username.equals("Administrateur")) {

            try {
                String uri = getData.path != null ? getData.path.replace("\\", "\\\\") : null;

                Date DateInscrp = new Date();
                java.sql.Date sqlDate = new java.sql.Date(DateInscrp.getTime());

                LocalDate dateNaissance = clientDateN.getValue();
                String dateN = dateNaissance != null ? dateNaissance.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;

                if (clientID.getText().isEmpty()
                        || clientNom.getText().isEmpty()
                        || clientPrenom.getText().isEmpty()
                        || clientCNI.getText().isEmpty()
                        || clientMention.getText().isEmpty()
                        || clientCodage.getText().isEmpty()
                        || clientTel.getText().isEmpty()
                        || clientAdress.getText().isEmpty()
                        || uri == null || uri.isEmpty()
                        || sqlDate == null) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("S'il vous plaît, vous devez sélectioner un client!");
                    alert.showAndWait();
                    return;
                }

                String sql = "DELETE FROM client WHERE clientID = '" + clientID.getText() + "'";

                connect = database.ConnectDb();
                if (connect == null) {
                    // Gérer l'échec de la connexion à la base de données
                    return;
                }

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr de vouloir supprimer ce client " + clientID.getText() + " ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get() == ButtonType.OK) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    historique("Suppression du client: " + clientID.getText() + " réussie avec succès.", getData.username);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Client supprimé avec succès!");
                    alert.showAndWait();

                } else {
                    historique("Échec! suppression du client: " + clientID.getText() + " annulée.", getData.username);

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Suppression annulée!");
                    alert.showAndWait();
                }

                addClientShowListData();
                addClientReset();

            } catch (Exception e) {
                e.printStackTrace();
                // Gérer l'exception
            }

        } else {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas d'accès à cette opération!");
            alert.showAndWait();
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
        clientType.getSelectionModel().clearSelection();
        clientImageView.setImage(null);
        getData.path = "";
        clientRechercheText.setText("");
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

    //Pour combox Passant ou redoublant de client
    private String[] typeList = {"Membre simple", "Déléguer", "Oportuniste"};

    public void addClientTypeList() {
        List<String> listP = new ArrayList<>();

        for (String data : typeList) {
            listP.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listP);
        clientType.setItems(listData);
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
                } else if (predicateClientData.getClientType().toLowerCase().contains(searchKey)) {

                    return true;

                } else if (predicateClientData.getDateInscrp().toString().contains(searchKey)) {

                    return true;
                } else if (predicateClientData.getNomUtilisateur().toString().contains(searchKey)) {

                    return true;
                } else {
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
                        result.getString("clientType"),
                        result.getString("clientPhoto"),
                        result.getDate("DateInscrp"),
                        result.getString("NomUtilisateur")
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
        clientTypeCol.setCellValueFactory(new PropertyValueFactory<>("clientType"));
        clientDateCol.setCellValueFactory(new PropertyValueFactory<>("DateInscrp"));
        clientNomUtilisateurCol.setCellValueFactory(new PropertyValueFactory<>("NomUtilisateur"));

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
     * ********************************************************************************
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

        try {
            Alert alert;
            if (serviceID.getText().isEmpty()
                    || serviceNom.getText().isEmpty()
                    || serviceType.getText().isEmpty()
                    || servicePrix.getText().isEmpty()
                    || serviceDuree.getText().isEmpty()
                    || serviceDateDebut.getValue() == null
                    || serviceDateFin.getValue() == null
                    || getData.pathService == null || getData.pathService == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez complètez tous les champs!");
                alert.showAndWait();
            } else {

                String check = "SELECT serviceID FROM service WHERE serviceID = '"
                        + serviceID.getText() + "'";

                connect = database.ConnectDb();
                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Cette serviceID: " + serviceID.getText() + " existe déjà!");
                    alert.showAndWait();

                } else {
                    java.util.Date javaDate1 = java.util.Date.from(serviceDateDebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    String dateD = sdf1.format(javaDate1);

                    java.util.Date javaDate2 = java.util.Date.from(serviceDateFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                    String dateF = sdf2.format(javaDate2);

                    String sql = "INSERT INTO service"
                            + "(serviceID, serviceNom, serviceType, servicePrix, serviceDuree, serviceDateDebut, serviceDateFin, serviceImage, NomUtilisateur)"
                            + "VALUES(?,?,?,?,?,?,?,?,?)";

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
                    prepare.setString(9, getData.username);

                    alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Message de confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("Êtes-vous sur de vouloir ajouter cette service?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.CANCEL)) {

                        historique("Echec! ajout de nouveau service: " + serviceID.getText() + " anulé", getData.username);

                        alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Message d'erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Ajout du service anuler!");
                        alert.showAndWait();

                    } else {
                        prepare.executeUpdate();

                        historique("Ajout de nouveau service: " + serviceID.getText() + " réussi", getData.username);

                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Message d'information");
                        alert.setHeaderText(null);
                        alert.setContentText("Ajout de service réussi avec succès!");
                        alert.showAndWait();

                        addServiceShowListData();
                        addServiceReset();

                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Pour la mise à jour des services (Efa mandeha)
    public void addServiceUpdate() {

        try {
            Alert alert;

            String uri = getData.pathService;
            uri = uri.replace("\\", "\\\\");

            if (serviceID.getText().isEmpty()
                    || serviceNom.getText().isEmpty()
                    || serviceType.getText().isEmpty()
                    || servicePrix.getText().isEmpty()
                    || serviceDuree.getText().isEmpty()
                    || serviceDateDebut.getValue() == null
                    || serviceDateFin.getValue() == null
                    || uri == null || uri.isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez sélectionner un client et remplir ces champs!");
                alert.showAndWait();

            } else {

                LocalDate debut = serviceDateDebut.getValue();
                LocalDate fin = serviceDateFin.getValue();

                String dateD = debut != null ? debut.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
                String dateF = fin != null ? fin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;

                String sql = "UPDATE service SET serviceNom = ?, serviceType = ?, servicePrix = ?, serviceDuree = ?, serviceDateDebut = ?, serviceDateFin = ?, serviceImage = ?, NomUtilisateur = ? WHERE serviceID = ?";
                connect = database.ConnectDb();

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr de vouloir modifier ce service: " + serviceID.getText() + " ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {

                    PreparedStatement preparedStatement = connect.prepareStatement(sql);
                    preparedStatement.setString(1, serviceNom.getText());
                    preparedStatement.setString(2, serviceType.getText());
                    preparedStatement.setString(3, servicePrix.getText());
                    preparedStatement.setString(4, serviceDuree.getText());
                    preparedStatement.setString(5, dateD);
                    preparedStatement.setString(6, dateF);
                    preparedStatement.setString(7, uri);
                    preparedStatement.setString(8, getData.username);
                    preparedStatement.setString(9, serviceID.getText());
                    preparedStatement.executeUpdate();

                    historique("Modification du service: " + serviceID.getText() + " réussie avec succès.", getData.username);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Le service a été modifié avec succès!");
                    alert.showAndWait();
                } else {
                    historique("Échec de la modification du service: " + serviceID.getText() + " annulée.", getData.username);

                    alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Modification du service annulée!");
                    alert.showAndWait();
                }

                addServiceShowListData();
                addServiceReset();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Supprimer un client (efa mandeha) 
    public void addServiceDelete() {
        
        Alert alert;
        if (getData.username != null && getData.username.equals("Administrateur")) {

            try {

                if (serviceID.getText().isEmpty() || serviceNom.getText().isEmpty()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez sélectionner un service!");
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
                confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cette service " + serviceID.getText() + " ?");
                Optional<ButtonType> option = confirmationAlert.showAndWait();

                if (option.isPresent() && option.get() == ButtonType.OK) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    historique("Suppression du service: " + serviceID.getText() + " réussi avec succès", getData.username);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Service a été supprimé avec succès!");
                    alert.showAndWait();

                } else {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Suppression du service anuler!");
                    alert.showAndWait();

                    historique("Echec! suppression du service: " + serviceID.getText() + " anuler", getData.username);
                }

                addServiceShowListData();
                addServiceReset();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas d'accès à cette opération!");
            alert.showAndWait();
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
                } else if (predicateServiceData.getNomUtilisateur().toString().contains(searchKey)) {

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
                        result.getString("serviceImage"),
                        result.getString("NomUtilisateur")
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
        serviceNomUtlisateurCol.setCellValueFactory(new PropertyValueFactory<>("NomUtilisateur"));

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
     * ********************************************************************************
     */
    
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

        menu_gridPane.getChildren().clear();
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();

        for (int q = 0; q < cardListData.size(); q++) {

            try {

                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/Interface/cardService.fxml"));
                AnchorPane pane = load.load();
                CardServiceController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                if (column == 2) {
                    column = 0;
                    row += 1;
                }

                menu_gridPane.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(8));

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private Integer sID;

    public int getSid() {
        String sql = "SELECT Max(suivieID) as idS FROM `suivieclient` WHERE 1";
        int nb = 0;
        connect = database.ConnectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                nb = result.getInt("idS");
            }
            System.err.println("SUIVIE ID : " + nb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nb;
    }

    public void suivieID() {

        String sql = "SELECT MAX(suivieID) AS suivieID FROM suivieclient";

        String checkSIDSql = "SELECT MAX(suivieID) AS suivieID FROM facture";

        String checkSIDSqlCompte = "SELECT MAX(suivieID) AS suivieID FROM compte";

        connect = database.ConnectDb();

        try {
            // Obtenez la valeur maximale de suivieID dans la table suivieclient
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                sID = result.getInt("suivieID");
            }

            prepare = connect.prepareStatement(checkSIDSql);
            // Obtenez la valeur maximale de suivieID dans la table facture
            result = prepare.executeQuery();

            int checkID = 0;
            if (result.next()) {
                checkID = result.getInt("suivieID");
            }

            // Obtenez la valeur maximale de suivieID dans la table facture
            prepare = connect.prepareStatement(checkSIDSqlCompte);
            result = prepare.executeQuery();

            int checkSIDSqlCompt = 0;
            if (result.next()) {
                checkSIDSqlCompt = result.getInt("suivieID");
            }

            // Vérifiez si sID est égal à 0 ou égal à checkID, ou à checkCompt puis incrémentez-le
            while (sID == 0 || sID == checkID || sID == checkSIDSqlCompt) {
                sID++;
                System.out.println("suivie ID" + sID);
            }

            getData.sID = sID;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private ObservableList<suivieData> menuOrderListData;

    //Afficher les listes des clients sur Tables view
    public void menuShowOrderListData() {
        menuOrderListData = suivieListeData("aaa");

        menuColServiceNom.setCellValueFactory(new PropertyValueFactory<>("serviceNom"));
        menuColServiceQuantite.setCellValueFactory(new PropertyValueFactory<>("factureQte"));
        menuColServicePrix.setCellValueFactory(new PropertyValueFactory<>("servicePrix"));
        if (getData.clickAdd) {
            menu_tableView.setItems(menuOrderListData);
        } else {
            menu_tableView.setItems(null);
            menuTotale.setText("0.0 Ar");
            totalP = 0;
        }
    }

    private Integer getID;

    public void menuSelectOrder() {

        suivieData suivieD = menu_tableView.getSelectionModel().getSelectedItem();
        int num = menu_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        getID = suivieD.getID();
    }

    private static double totalP;

    public double menuGetTotale(int id) {

        suivieID();

        String total = "SELECT SUM(servicePrix) FROM suivieclient WHERE suivieID =" + id;

        connect = database.ConnectDb();

        try {

            prepare = connect.prepareStatement(total);
            result = prepare.executeQuery();

            if (result.next()) {
                totalP = result.getDouble("SUM(servicePrix)");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalP;
    }

    public double getReste(int id) {

        suivieID();

        String total = "SELECT clientSoldeRestePayer FROM client WHERE clientID =" + id;

        connect = database.ConnectDb();

        try {

            prepare = connect.prepareStatement(total);
            result = prepare.executeQuery();

            if (result.next()) {
                totalP = result.getDouble("clientSoldeRestePayer");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalP;
    }

    private double soldeRestePayer;

    private double soldePayer;

    public double getDejaPayerSolde() {

        String checkData = "SELECT SUM(suivieServiceDejaPayer) as reste FROM suivieclient WHERE clientID = '" + getData.getMenuClientID + "'";

        connect = database.ConnectDb();

        try {

            prepare = connect.prepareStatement(checkData);
            result = prepare.executeQuery();

            while (result.next()) {

                soldePayer = result.getDouble("reste");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return soldePayer;
    }

    public double getRestePayerSolde() {

        String checkData = "SELECT SUM(suivieServiceRestePayer) as reste FROM suivieclient WHERE clientID = '" + getData.getMenuClientID + "'";

        connect = database.ConnectDb();

        try {

            prepare = connect.prepareStatement(checkData);
            result = prepare.executeQuery();

            while (result.next()) {

                soldeRestePayer = result.getDouble("reste");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return soldeRestePayer;

    }

    public double getDernierRestePayerSolde() {

        String checkData = "SELECT suivieServiceRestePayer as reste FROM suivieclient WHERE ID=(SELECT MAX(ID) FROM suivieclient WHERE 1) AND clientID = '" + getData.getMenuClientID + "'";

        connect = database.ConnectDb();

        try {

            prepare = connect.prepareStatement(checkData);
            result = prepare.executeQuery();

            while (result.next()) {

                soldeRestePayer = result.getDouble("reste");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return soldeRestePayer;

    }

    public double getDejaPayerSolde2() {

        String checkData = "SELECT clientSoldeRestePayer FROM client WHERE clientID = '" + getData.getMenuClientID + "'";

        connect = database.ConnectDb();

        try {

            prepare = connect.prepareStatement(checkData);
            result = prepare.executeQuery();

            while (result.next()) {

                soldeRestePayer = result.getDouble("clientSoldeRestePayer");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return soldeRestePayer;

    }

    public void menuDisplayTotale() {
        if (clickFservice) {
            menuGetTotale(getSid());
            clickFservice = true;
        } else {
            totalP = 0;
            clickFservice = true;
        }
        menuTotale.setText(totalP + " Ar");

    }

    private double mttpayer;
    private double reste;
//    Test Nombre

    public boolean isDouble(String s) {
        try {
            if (s.isEmpty()) {
                return true;
            } else {
                Double.parseDouble(s);
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void saisieMontantR() {

//        menuGetTotale(getSid());
        if (!isDouble(clientsoldePayerText.getText())) {

            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'information");
            alert.setHeaderText(null);
            alert.setContentText("Le type de donnée doit etre numerique!");
            alert.showAndWait();
        } else {
            mttpayer = Double.parseDouble(clientsoldePayerText.getText());

            double soldeRestePayer1 = Double.parseDouble(clientSuivieRestePayerTxt.getText()) - Double.parseDouble(clientsoldePayerText.getText());
            clientsuivieReste.setText(String.valueOf(soldeRestePayer1));
        }

    }

    public void saisieMontant() {

//        menuGetTotale(getSid());
        if (!isDouble(menuMonttantPayerTxt.getText())) {

            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'information");
            alert.setHeaderText(null);
            alert.setContentText("Le type de donnée doit etre numerique!");
            alert.showAndWait();
        } else {
            mttpayer = Double.parseDouble(menuMonttantPayerTxt.getText());

            reste = totalP - mttpayer;
            menuReste.setText(String.valueOf(reste));
        }

    }

    CardServiceController cSc = new CardServiceController();

    //Pour le bouton de payement
    public void menuPayerBtn() {

        Alert alert;

        getDejaPayerSolde();

        if (totalP == 0) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Echec! \n S'il vous plaît, vous devez d'abord ajouter un service!");
            alert.showAndWait();

        } else if (mttpayer == 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Echec! \n S'il vous plaît, vous devez d'abord remplir ce que vous voulez payer sur seule à payer!");
            alert.showAndWait();

        } else {
            Date factureDate = new Date();
            java.sql.Date dateFac = new java.sql.Date(factureDate.getTime());


            String inserPay = "INSERT INTO facture (suivieID, factureDate, factureTotale, NomUtilisateur)"
                    + "VALUES(?,?,?,?)";

            connect = database.ConnectDb();

            try {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr de vouloir faire le payement?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {


                    prepare = connect.prepareStatement(inserPay);


                    prepare.setString(1, String.valueOf(getData.sID));

                    prepare.setString(2, String.valueOf(dateFac));

                    double total = this.totalP - this.reste;
                    prepare.setString(3, String.valueOf(total));
                    prepare.setString(4, getData.username);
                    prepare.executeUpdate();

                    String soldeUpdate = "UPDATE suivieclient SET suivieServicerestePayer = '" + this.reste + "' , suivieServiceDejaPayer = (servicePrix- '" + this.reste + "') WHERE suivieID = '" + getSid() + "'";

                    statement = connect.createStatement();
                    statement.executeUpdate(soldeUpdate);

                    String updateSolde = "UPDATE client SET clientSoldeRestePayer ='" + getRestePayerSolde() + "', clientSoldeDejaPayer ='" + getDejaPayerSolde() + "' WHERE clientID = '" + getData.getMenuClientID + "'";
                    statement.executeUpdate(updateSolde);

                    historique("Payement du service avec client: " + getData.getMenuClientID + " réussi avec succès", getData.username);

                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Message de confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("Payement réussi!\n Voulez-vous faire l'impression?");
                    Optional<ButtonType> optionC = alert.showAndWait();

                    if (optionC.get().equals(ButtonType.OK)) {

                        imprimer2();

                    } else {
                       
                        menu_tableView.setItems(null);
                        getData.clickAdd = false;
                        clickFservice = false;
                    }

                } else {

                    historique("Payement du service avec client: " + getData.getMenuClientID + " anuler", getData.username);

                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Payement annuler!");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void menuCompteBtn() {

        Alert alert;

        suivieID();
        if (totalP == 0) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Echec! \n S'il vous plaît, vous devez d'abord ajouter un service!");
            alert.showAndWait();

        } else {

            Date factureDate = new Date();
            java.sql.Date dateCompte = new java.sql.Date(factureDate.getTime());


            String inserPay = "INSERT INTO compte (suivieID, compteDate, compteTotale, NomUtilisateur)"
                    + "VALUES(?,?,?,?)";

            connect = database.ConnectDb();

            try {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr de vouloir l'ajouter dans votre compte?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    suivieID();

                    prepare = connect.prepareStatement(inserPay);

                    prepare.setString(1, String.valueOf(sID));
                    prepare.setString(2, String.valueOf(dateCompte));
                    prepare.setString(3, String.valueOf(totalP - mttpayer));
                    prepare.setString(4, getData.username);
                    prepare.executeUpdate();

                    String soldeUpdate = "UPDATE suivieclient SET suivieServiceRestePayer = servicePrix WHERE suivieID = '" + getSid() + "'";

                    statement = connect.createStatement();
                    statement.executeUpdate(soldeUpdate);

                    String updateSolde = "UPDATE client SET clientSoldeRestePayer = '" + (getRestePayerSolde() + totalP) + "'";
                    statement.executeUpdate(updateSolde);

                    historique("Ajout au compte par un service avec client: " + getData.getMenuClientID + " réussi avec succès", getData.username);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Ajout au compte réussi avec succès!");
                    alert.showAndWait();

                    menuShowOrderListData();
                    menuRestart();

                    menu_tableView.setItems(null);
                    getData.clickAdd = false;
                } else {

                    historique("Ajout au compte par un service avec client: " + getData.getMenuClientID + " anuler", getData.username);

                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Ajout au compte annuler!");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //Misy zavatra tokony amboarina
    public void menuRemoveBtn() {

        Alert alert;
        menuSelectOrder();

        if (getID == 0) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText(" Vous devez séléctionner ce que vous voulez supprimer");
            alert.showAndWait();

        } else {

            String deleteData = "DELETE FROM suivieclient WHERE ID = " + getID;
            connect = database.ConnectDb();

            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sur de vouloir le supprimer?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.CANCEL)) {

                    historique("Suppression de faire un service avec client: " + getData.getMenuClientID + " anuler", getData.username);

                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Suppression anuler!");
                    alert.showAndWait();

                } else {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.executeUpdate();

                    historique("Supression de faire un service avec client: " + getData.getMenuClientID + " réussi avec succès", getData.username);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Supression réussi avec succès!");
                    alert.showAndWait();

                    menuShowOrderListData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void menuRestart() {

        menuShowOrderListData();

        totalP = 0;
        menuTotale.setText("0.0 Ar");

        mttpayer = 0;
        menuMonttantPayerTxt.setText("");

        reste = 0;
        menuReste.setText("0.0 Ar");

    }

    /**
     * ********************************************************************************
     * ********************************************************************************
     * *********************************SEPARATION*************************************
     * ********************************************************************************
     * ********************************************************************************
     * ********************************************************************************
     */
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

    public void menuclientSelectBtn() {

        String sqlselectClient = "SELECT * FROM client WHERE clientID = ? AND clientNom = ? AND clientPrenom = ?";
        connect = database.ConnectDb();

        try {
            Alert alert;

            if (menuClientID.getText().isEmpty()
                    || menuClientNom.getText().isEmpty()
                    || menuClientPrenom.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez sélectionner un client!");
                alert.showAndWait();

            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr " + menuClientNom.getText() + " ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.CANCEL)) {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Annuler!");
                    alert.showAndWait();

                } else {
                    prepare = connect.prepareStatement(sqlselectClient);
                    prepare.setString(1, menuClientID.getText());
                    prepare.setString(2, menuClientNom.getText());
                    prepare.setString(3, menuClientPrenom.getText());
                    result = prepare.executeQuery();

                    getData.getMenuClientID = Integer.parseInt(menuClientID.getText());
                    getData.getMenuClientNom = menuClientNom.getText();

                    menuServiceClientShowListData();
                    menuServiceClientSelect();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ********************************************************************************
     * ********************************************************************************
     * *********************************SEPARATION*************************************
     * ********************************************************************************
     * ********************************************************************************
     * ********************************************************************************
     */
    
    //Section suivie
    public ObservableList<suivieData> suivieListeData(String s) {

        ObservableList<suivieData> listData = FXCollections.observableArrayList();
        String sql = "";
        if (s.equals("")) {
            sql = "SELECT * FROM suivieclient ORDER BY suivieDate DESC";
        } else {
            sql = "SELECT * FROM suivieclient WHERE suivieID = '" + getSid() + "' ORDER BY suivieDate DESC";
        }

        connect = database.ConnectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            suivieData suivieD;

            while (result.next()) {
                suivieD = new suivieData(
                        result.getInt("ID"),
                        result.getInt("suivieID"),
                        result.getTimestamp("suivieDate"),
                        result.getInt("clientID"),
                        result.getString("clientNom"),
                        result.getString("serviceNom"),
                        result.getInt("factureQte"),
                        result.getDouble("servicePrix"),
                        result.getDouble("suivieServiceToaleSolde"),
                        result.getDouble("suivieServiceDejaPayer"),
                        result.getDouble("suivieServiceRestePayer"),
                        result.getString("NomUtilisateur")
                );
                listData.add(suivieD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<suivieData> suivieListData;

    //Afficher les listes des clients sur Tables view
    public ObservableList<suivieData> suivieClientShowListData() {

        suivieListData = suivieListeData("");

        suivieClientDateCol.setCellValueFactory(new PropertyValueFactory<>("suivieDate"));
        suivieClientIDCol.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        suivieClientNomCol.setCellValueFactory(new PropertyValueFactory<>("clientNom"));
        suivieServiceNomCol.setCellValueFactory(new PropertyValueFactory<>("serviceNom"));
        suivieServiceQteCol.setCellValueFactory(new PropertyValueFactory<>("factureQte"));
        suivieServicePrixUnitCol.setCellValueFactory(new PropertyValueFactory<>("servicePrix"));
        suivieServiceToaleSoldeCol.setCellValueFactory(new PropertyValueFactory<>("suivieServiceToaleSolde"));
        suivieServiceDejaPayerCol.setCellValueFactory(new PropertyValueFactory<>("suivieServiceDejaPayer"));
        suivieServiceRestePayerCol.setCellValueFactory(new PropertyValueFactory<>("suivieServiceRestePayer"));
        suivieServiceNomUtlisateurCol.setCellValueFactory(new PropertyValueFactory<>("NomUtilisateur"));

        suivieTableView1.setItems(suivieListData);
        return suivieListData;
    }

    
    //Selection service via Table View
    public void suivieClientSelect() {
        suivieData suivieD = suivieTableView1.getSelectionModel().getSelectedItem();
        int num = suivieTableView1.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        suivieClientID.setText(String.valueOf(suivieD.getClientID()));
        suivieClientNom.setText(suivieD.getClientNom());
        suivieServiceNom.setText(suivieD.getServiceNom());
        suivieServiceQte.setText(String.valueOf(suivieD.getFactureQte()));
        suivieServicePrix.setText(String.valueOf(suivieD.getServicePrix()));
        suivieServiceTotaleSolde.setText(String.valueOf(suivieD.getServiceTotaleSolde()));
        suivieServiceDejaPayer.setText(String.valueOf(suivieD.getServiceDejaPayer()));
        suivieServiceRestePayer.setText(String.valueOf(suivieD.getServiceRestePayer()));
    }

    //Pour l'actualisation de l'affichage du faire service service (Efa mandeha) 
    public void SuivieClientReset() {
        suivieRechercheText1.setText("");
        suivieClientID.setText("");
        suivieClientNom.setText("");
        suivieServiceNom.setText("");
        suivieServiceQte.setText("");
        suivieServicePrix.setText("");
        suivieServiceTotaleSolde.setText("");
        suivieServiceDejaPayer.setText("");
        suivieServiceRestePayer.setText("");
    }

    //Pour la barre de recherche d'un service (efa mandeha)
    public void suivieClientSearch() {

        FilteredList<suivieData> filter = new FilteredList<>(suivieListData, e -> true);

        suivieRechercheText1.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateSuivieData -> {

                if (newValue == null || newValue.isEmpty()) {

                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateSuivieData.getClientID().toString().contains(searchKey)) {

                    return true;

                } else if (predicateSuivieData.getClientNom().toLowerCase().contains(searchKey)) {

                    return true;

                } else if (predicateSuivieData.getServiceNom().toLowerCase().contains(searchKey)) {

                    return true;
                } else if (predicateSuivieData.getFactureQte().toString().contains(searchKey)) {

                    return true;
                } else if (predicateSuivieData.getServicePrix().toString().contains(searchKey)) {

                    return true;
                } else if (predicateSuivieData.getServiceTotaleSolde().toString().contains(searchKey)) {

                    return true;
                } else if (predicateSuivieData.getServiceDejaPayer().toString().contains(searchKey)) {

                    return true;
                } else if (predicateSuivieData.getServiceRestePayer().toString().contains(searchKey)) {

                    return true;
                } else if (predicateSuivieData.getNomUtilisateur().toLowerCase().contains(searchKey)) {

                    return true;
                } else {
                    return false;
                }
            });

        });

        SortedList<suivieData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(suivieTableView1.comparatorProperty());
        suivieTableView1.setItems(sortList);
    }

    
    public void suivieClientRemove() {  
        
        Alert alert;
        if (getData.username != null && getData.username.equals("Administrateur")) {
            
            try {
                
                if (suivieClientID.getText().isEmpty() || suivieClientNom.getText().isEmpty()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("S'il vous plaît, vous devez complètez ces cases!");
                    alert.showAndWait();
                    return;
                }

                String sql = "DELETE FROM suivieclient WHERE ID = " + getID;;

                connect = database.ConnectDb();
                if (connect == null) {
                    // Gérer l'échec de la connexion à la base de données
                    return;
                }

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr de vouloir supprimer la serviice de ce client " + suivieClientNom.getText() + " ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get() == ButtonType.OK) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Service supprimé avec succès!");
                    alert.showAndWait();

                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Echec de suppression!");
                    alert.showAndWait();
                }

                suivieClientShowListData();
                SuivieClientReset();

            } catch (Exception e) {
                e.printStackTrace();
                // Gérer l'exception
            }
           
        } else {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas d'accès à cette opération!");
            alert.showAndWait();
        }

    }
    

    public ObservableList<clientData> clientSuivieListeData() {

        ObservableList<clientData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM client";

        connect = database.ConnectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            clientData cliD;

            while (result.next()) {
                cliD = new clientData(
                        result.getInt("clientID"),
                        result.getString("clientNom"),
                        result.getDouble("clientSoldeDejaPayer"),
                        result.getDouble("clientSoldeRestePayer")
                );
                listData.add(cliD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }
    

    private ObservableList<clientData> clientsuivieListData;

    //Afficher les listes des clients sur Tables view
    public void clientSuivieShowListData() {

        clientsuivieListData = clientSuivieListeData();

        clientSuivieIDCol.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        clientSuivieNomCol.setCellValueFactory(new PropertyValueFactory<>("clientNom"));
        clientSuivieDejaPayerCol.setCellValueFactory(new PropertyValueFactory<>("clientSoldeDejaPayer"));
        clientSuivieRestePayerCol.setCellValueFactory(new PropertyValueFactory<>("clientSoldeRestePayer"));

        clientSuivieTableView.setItems(clientsuivieListData);
    }
    

    public void clientsuivieSelect() {
        clientData clientD = clientSuivieTableView.getSelectionModel().getSelectedItem();
        int num = clientSuivieTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        clientSuivieIDTxt.setText(String.valueOf(clientD.getClientID())); // Assurez-vous que clientID est un TextField ou un contrôle similaire
        clientSuivieNomTxt.setText(clientD.getClientNom());
        clientSuivieDejaPayerTxt.setText(String.valueOf(clientD.getclientSoldeDejaPayer()));
        clientSuivieRestePayerTxt.setText(String.valueOf(clientD.getclientSoldeRestePayer()));
        getData.getMenuClientID = clientD.getClientID();
        System.out.println(getData.getMenuClientID);
    }
    

    public void clientsuivieReset() {

        clientSuivieIDTxt.setText("");
        clientSuivieNomTxt.setText("");
        clientSuivieDejaPayerTxt.setText("");
        clientSuivieRestePayerTxt.setText("");
        clientSuivieRechercheTxt.setText("");
        clientsoldePayerText.setText("");
        clientsuivieReste.setText("");
    }
    

    public void clientsuivieSearch() {

        FilteredList<clientData> filter = new FilteredList<>(clientsuivieListData, e -> true);

        clientSuivieRechercheTxt.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateSuivieData -> {

                if (newValue == null || newValue.isEmpty()) {

                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateSuivieData.getClientID().toString().contains(searchKey)) {

                    return true;

                } else if (predicateSuivieData.getClientNom().toLowerCase().contains(searchKey)) {

                    return true;

                } else if (predicateSuivieData.getclientSoldeDejaPayer().toString().contains(searchKey)) {

                    return true;
                } else if (predicateSuivieData.getclientSoldeRestePayer().toString().contains(searchKey)) {

                    return true;
                } else {
                    return false;
                }
            });

        });

        SortedList<clientData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(clientSuivieTableView.comparatorProperty());
        clientSuivieTableView.setItems(sortList);
    }

    
    //Pour le bouton de confirmation sur séléction au suivie client 
    public void suivieClienttConfirmerBtn() {

        String sqlselectClient = "SELECT * FROM client WHERE clientID = ? AND clientNom = ? AND clientSoldeDejaPayer = ? AND clientSoldeRestePayer = ?";
        connect = database.ConnectDb();

        try {
            Alert alert;

            if (clientSuivieIDTxt.getText().isEmpty()
                    || clientSuivieNomTxt.getText().isEmpty()
                    || clientSuivieDejaPayerTxt.getText().isEmpty()
                    || clientSuivieRestePayerTxt.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez sélectionner un client!");
                alert.showAndWait();

            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr " + clientSuivieNomTxt.getText() + " ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.CANCEL)) {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Annuler!");
                    alert.showAndWait();

                } else {
                    prepare = connect.prepareStatement(sqlselectClient);
                    prepare.setString(1, clientSuivieIDTxt.getText());
                    prepare.setString(2, clientSuivieNomTxt.getText());
                    prepare.setString(3, clientSuivieDejaPayerTxt.getText());
                    prepare.setString(4, clientSuivieRestePayerTxt.getText());
                    result = prepare.executeQuery();

                    getData.getMenuClientID = Integer.parseInt(clientSuivieIDTxt.getText());
                    getData.getMenuClientNom = clientSuivieNomTxt.getText();

                    menuServiceClientShowListData();
                    menuServiceClientSelect();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    //Pour le bouton de validation du client    
    public void validerCompte() throws FileNotFoundException {

        DashboardController dashForm = new DashboardController(); // Utiliser la même instance de DashboardController


        double totaleP = (0 * 0);
        double soldeTotaleTotale = (totaleP + getTolaleSolde());
        double soldedejaPayer = Double.parseDouble(clientsoldePayerText.getText());
        double soldeRestePayer1 = Double.parseDouble(clientSuivieRestePayerTxt.getText()) - Double.parseDouble(clientsoldePayerText.getText());


        double totalSolde = getTolaleSolde();
        Alert alert;
        try {
            if (getData.getMenuClientID == null || getData.getMenuClientNom == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez sélectionner un client !");
                alert.showAndWait();

            } else {
                // Récupérer la date actuelle
                java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

                // Insérer les données dans la table suivieclient
                String insertDataQuery = "INSERT INTO suivieclient (suivieID, suivieDate, clientID, clientNom, serviceNom, factureQte, "
                        + "servicePrix, suivieServiceToaleSolde, suivieServiceDejaPayer, suivieServiceRestePayer, NomUtilisateur)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

                connect = database.ConnectDb();

                prepare = connect.prepareStatement(insertDataQuery);

                if (getData.clickAdd) {
                    prepare.setString(1, String.valueOf(dashForm.getSid()));
                } else {
                    prepare.setString(1, String.valueOf(dashForm.getSid() + 1));
                    getData.sID = dashForm.getSid() + 1;
                }

                prepare.setDate(2, currentDate);
                prepare.setString(3, String.valueOf(getData.getMenuClientID));
                prepare.setString(4, getData.getMenuClientNom);
                prepare.setString(5, "Reglement ");
                prepare.setInt(6, 0);

                prepare.setDouble(7, totaleP);

                prepare.setDouble(8, totalSolde);

                prepare.setDouble(9, soldedejaPayer);

                prepare.setDouble(10, soldeRestePayer1);

                prepare.setString(11, getData.username);

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr d'ajouter ce service ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.CANCEL)) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Échec de l'ajout !");
                    alert.showAndWait();

                } else {
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Message de confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("Règlement réussi!\n Voulez-vous faire l'impression?");
                    Optional<ButtonType> optionC = alert.showAndWait();

                    if (optionC.get().equals(ButtonType.OK)) {

                        try {
                            imprimer3();
                        } catch (JRException ex) {
                            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {

                    }

                    statement = connect.createStatement();
                    String updateSolde = "UPDATE client SET clientSoldeRestePayer ='" + getDernierRestePayerSolde() + "', clientSoldeDejaPayer ='" + getDejaPayerSolde() + "' WHERE clientID = '" + getData.getMenuClientID + "'";
                    statement.executeUpdate(updateSolde);

                    getData.clickAdd = true;

                    getData.soldeTotaleTotale = soldeTotaleTotale;

                    clientsuivieReset();
                    menuShowOrderListData();
                    menuRestart();

                    menu_tableView.setItems(null);
                    getData.clickAdd = false;
                    

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    

    public double getTolaleSolde() {

        String totalSolde = "SELECT MAX(suivieServiceToaleSolde) FROM suivieclient WHERE clientID =" + getData.getMenuClientID;
        double soldeTotale = 0;
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

    public void suivieSeulePayer() {

        if (menuMonttantPayerTxt.getText().isEmpty() || totalP == 0) {

            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'information");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez complètez le monttant que vous voulez payer!");
            alert.showAndWait();

        } else {
            mttpayer = Double.parseDouble(menuMonttantPayerTxt.getText());

            reste = totalP - mttpayer;
            menuReste.setText(reste + " Ar");
        }

    }

    public void imprimer2() throws JRException, FileNotFoundException {

        int idSuivie = getSid();
        
        // Chargement du fichier jrxml avec un chemin relatif
        // Assurez-vous que le fichier jire.jrxml est dans le répertoire resources/Interface
        InputStream jrxmlStream = getClass().getResourceAsStream("/Interface/jire.jrxml");
        if (jrxmlStream == null) {
            throw new FileNotFoundException("Fichier jire.jrxml introuvable dans le répertoire resources/Interface");
        }

        JasperDesign jasdi = JRXmlLoader.load(jrxmlStream);
        
        
        String sql = "SELECT serviceNom,factureQte,servicePrix,clientNom FROM suivieclient WHERE clientID = '" + getData.getMenuClientID + "' AND suivieID = '" + idSuivie + "'";
        JRDesignQuery newQuery = new JRDesignQuery();
        newQuery.setText(sql);

        jasdi.setQuery(newQuery);

        HashMap<String, Object> para = new HashMap<>();

        para.put("idClient", getData.getMenuClientID);
        para.put("idSuivie", idSuivie);
        para.put("mttTotal", menuGetTotale(idSuivie));
        para.put("dejaPayer", Double.parseDouble(menuMonttantPayerTxt.getText()));
        System.err.println("=====" + Double.parseDouble(menuMonttantPayerTxt.getText()));
        para.put("reste", Double.parseDouble(menuReste.getText()));
        para.put("nomResponsable", getData.username);
        
        // Ajouter le chemin de l'image en tant que paramètre si nécessaire
        para.put("imagePath", getClass().getResource("/Image/1704805960156.jpg").toString());

        JasperReport js = JasperCompileManager.compileReport(jasdi);
        JasperPrint jp = JasperFillManager.fillReport(js, para, connect);

        JasperViewer.viewReport(jp, false);

        menuShowOrderListData();
        menuRestart();

        menu_tableView.setItems(null);
        getData.clickAdd = false;
    }
    
    
    
    public void imprimer3() throws JRException, FileNotFoundException {

        int idSuivie = getSid();
        int idClient = getData.getMenuClientID;

        // Chargement du fichier jrxml avec un chemin relatif
        // Assurez-vous que le fichier jire.jrxml est dans le répertoire resources/Interface
        InputStream jrxmlStream = getClass().getResourceAsStream("/Interface/jire.jrxml");
        if (jrxmlStream == null) {
            throw new FileNotFoundException("Fichier jire.jrxml introuvable dans le répertoire resources/Interface");
        }

        JasperDesign jasdi = JRXmlLoader.load(jrxmlStream);

        String sql = "SELECT serviceNom, factureQte, servicePrix, clientNom FROM suivieclient WHERE clientID = '" + idClient + "' AND suivieID = '" + idSuivie + "'";
        JRDesignQuery newQuery = new JRDesignQuery();
        newQuery.setText(sql);

        jasdi.setQuery(newQuery);

        HashMap<String, Object> para = new HashMap<>();

        para.put("idClient", idClient);
        para.put("idSuivie", idSuivie);
        para.put("mttTotal", getReste(idClient));
        para.put("dejaPayer", Double.parseDouble(clientsoldePayerText.getText()));
        para.put("reste", Double.parseDouble(clientsuivieReste.getText()));
        para.put("nomResponsable", getData.username);
        
         // Ajouter le chemin de l'image en tant que paramètre si nécessaire
        para.put("imagePath", getClass().getResource("/Image/1704805960156.jpg").toString());

        JasperReport js = JasperCompileManager.compileReport(jasdi);
        JasperPrint jp = JasperFillManager.fillReport(js, para, connect);

        JasperViewer.viewReport(jp, false);

        menuShowOrderListData();
        menuRestart();

        menu_tableView.setItems(null);
        getData.clickAdd = false;
    }
    
    

//    public void imprimer3() throws JRException {
//
//        int idSuivie = getSid();
//        int idClient = getData.getMenuClientID;
//
//        
//        JasperDesign jasdi = JRXmlLoader.load("C:\\Users\\KADAFI Ben\\Documents\\NetBeansProjects\\JIREHSTUDENTSapp\\src\\Interface\\jire.jrxml");
//        
//        String sql = "SELECT serviceNom,factureQte,servicePrix,clientNom FROM suivieclient WHERE clientID = '" + idClient + "' AND suivieID = '" + idSuivie + "'";
//        JRDesignQuery newQuery = new JRDesignQuery();
//        newQuery.setText(sql);
//
//        jasdi.setQuery(newQuery);
//
//        HashMap<String, Object> para = new HashMap<>();
//
//        para.put("idClient", idClient);
//        para.put("idSuivie", idSuivie);
//        para.put("mttTotal", getReste(idClient));
//        para.put("dejaPayer", Double.parseDouble(clientsoldePayerText.getText()));
//        para.put("reste", Double.parseDouble(clientsuivieReste.getText()));
//        para.put("nomResponsable", getData.username);
//
//        JasperReport js = JasperCompileManager.compileReport(jasdi);
//        JasperPrint jp = JasperFillManager.fillReport(js, para, connect);
//
//        JasperViewer.viewReport(jp, false);
//
//        menuShowOrderListData();
//        menuRestart();
//
//        menu_tableView.setItems(null);
//        getData.clickAdd = false;
//    }
    
    

    public void imprimer() throws FileNotFoundException {

        Alert alert;
        getDejaPayerSolde();
//        saisieMontant();
        try {
            if (totalP == 0) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Echec! \n S'il vous plaît, vous devez d'abord ajouter un service!");
                alert.showAndWait();

            } else if (reste == 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Echec! \n S'il vous plaît, vous devez d'abord remplir ce que vous voulez payer sur seule à payer!");
                alert.showAndWait();

//                JasperDesign jasdi = JRXmlLoader.load("C:\\Users\\KADAFI Ben\\Documents\\NetBeansProjects\\JIREHSTUDENTSapp\\src\\Interface\\jire.jrxml");
                
                 // Chargement du fichier jrxml avec un chemin relatif
                // Assurez-vous que le fichier jire.jrxml est dans le répertoire resources/Interface
                InputStream jrxmlStream = getClass().getResourceAsStream("/Interface/jire.jrxml");
                if (jrxmlStream == null) {
                    
                    throw new FileNotFoundException("Fichier jire.jrxml introuvable dans le répertoire resources/Interface");
                }

                JasperDesign jasdi = JRXmlLoader.load(jrxmlStream);
        
                String sql = "SELECT serviceNom,factureQte,servicePrix,clientNom FROM suivieclient WHERE clientID = '" + getData.getMenuClientID + "' AND suivieID = '" + getSid() + "'";
                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(sql);

                jasdi.setQuery(newQuery);

                HashMap<String, Object> para = new HashMap<>();
                para.put("idClient", getData.getMenuClientID);
                para.put("idSuivie", getSid());
                para.put("mttTotal", menuGetTotale(getSid()));
                para.put("reste", Double.parseDouble(menuReste.getText()));
                
                 // Ajouter le chemin de l'image en tant que paramètre si nécessaire
                para.put("imagePath", getClass().getResource("/Image/1704805960156.jpg").toString());
                JasperReport js = JasperCompileManager.compileReport(jasdi);
                JasperPrint jp = JasperFillManager.fillReport(js, para, connect);

                JasperViewer.viewReport(jp, false);

                menuRestart();
                menu_tableView.setItems(null);
            }

        } catch (JRException ex) {
            System.out.println("Error");
            ex.printStackTrace();
        }
    }

    /**
     * ********************************************************************************
     * ********************************************************************************
     * *********************************SEPARATION*************************************
     * ********************************************************************************
     * ********************************************************************************
     * ********************************************************************************
     */
    
    //Section historique
    public void historique(String nomHistirique, String nomUtilisateur) throws SQLException {

        String sql = "INSERT INTO historique (dateHeur, nomHistirique, NomUtilisateur) VALUES (?, ?, ?)";

        // Obtenez la connexion à la base de données
        Connection connect = database.ConnectDb();

        // Utilisez try-with-resources pour garantir la fermeture des ressources
        try (PreparedStatement statement = connect.prepareStatement(sql)) {

            // Définissez les paramètres
            statement.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis())); // Date et heure actuelles
            statement.setString(2, nomHistirique);
            statement.setString(3, nomUtilisateur);

            // Exécutez la mise à jour
            int rowsInserted = statement.executeUpdate(); // Utilisez executeUpdate sans paramètre
            if (rowsInserted > 0) {
                System.out.println("Un nouveau record a été inséré avec succès !");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Relance l'exception pour que l'appelant puisse la gérer
        } finally {
            // Fermez la connexion si elle n'est pas fermée par try-with-resources
            if (connect != null) {
                try {
                    connect.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public ObservableList<historiqueData> historiqueSuivieListeData() {

        ObservableList<historiqueData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM historique ORDER BY dateHeur DESC";

        connect = database.ConnectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            historiqueData hiD;

            while (result.next()) {
                hiD = new historiqueData(
                        result.getInt("idH"),
                        result.getTimestamp("dateHeur"),
                        result.getString("nomHistirique"),
                        result.getString("NomUtilisateur")
                );
                listData.add(hiD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<historiqueData> historiquesuivieListData;

    //Afficher les listes de l'historique sur Tables view
    public void historiqueShowListData() {

        historiquesuivieListData = historiqueSuivieListeData();

        historiqueDateCol.setCellValueFactory(new PropertyValueFactory<>("dateHeur"));
        historiqueActiviteCol.setCellValueFactory(new PropertyValueFactory<>("nomHistirique"));
        historiqueNomUtilisateurCol.setCellValueFactory(new PropertyValueFactory<>("NomUtilisateur"));

        historiqueTableview.setItems(historiquesuivieListData);
    }

    public void historiqueSearch() {

        FilteredList<historiqueData> filter = new FilteredList<>(historiquesuivieListData, e -> true);

        historiqueTextFieldRecherche.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateSuivieData -> {

                if (newValue == null || newValue.isEmpty()) {

                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateSuivieData.getDateHeur().toString().contains(searchKey)) {

                    return true;

                } else if (predicateSuivieData.getNomHistirique().toLowerCase().contains(searchKey)) {

                    return true;

                } else if (predicateSuivieData.getNomUtilisateur().toLowerCase().contains(searchKey)) {

                    return true;

                } else {
                    return false;
                }
            });

        });

        SortedList<historiqueData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(historiqueTableview.comparatorProperty());
        historiqueTableview.setItems(sortList);
    }

    private Integer getIDH;

    private String getNomH;

    public void menuSelectOrderHistorique() {

        historiqueData historiqueD = historiqueTableview.getSelectionModel().getSelectedItem();
        int num = historiqueTableview.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        getIDH = historiqueD.getIdH(); // Assurez-vous que clientID est un TextField ou un contrôle similaire
        getNomH = historiqueD.getNomHistirique();

    }
    
 
    public void effacerHistorique() {

        Alert alert;
        
        if (getData.username != null && getData.username.equals("Administrateur")) {
            
                menuSelectOrderHistorique();

            if (getIDH == 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez sélectionner ce que vous voulez supprimer");
                alert.showAndWait();
            } else {

                String deleteData = "DELETE FROM historique WHERE idH = ?";
                connect = database.ConnectDb();

                try {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Message de confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("Êtes-vous sur de vouloir le supprimer?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.CANCEL)) {
                        alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Message d'information");
                        alert.setHeaderText(null);
                        alert.setContentText("Suppression annulée!");
                        alert.showAndWait();
                    } else {
                        prepare = connect.prepareStatement(deleteData);
                        prepare.setInt(1, getIDH);
                        prepare.executeUpdate();

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Message d'information");
                        alert.setHeaderText(null);
                        alert.setContentText("Suppression réussie avec succès!");
                        alert.showAndWait();

                        historiqueShowListData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        } else {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas d'accès à cette opération!");
            alert.showAndWait();
        }
        
        
    }
    

    public void effacerHistoriqueTout() {
        
         Alert alert;
        
         if (getData.username != null && getData.username.equals("Administrateur")) {
            
           String deleteData = "DELETE FROM historique";
           connect = database.ConnectDb();

           try {
               alert = new Alert(Alert.AlertType.CONFIRMATION);
               alert.setTitle("Message de confirmation");
               alert.setHeaderText(null);
               alert.setContentText("Êtes-vous sur de vouloir tout suprimer?");
               Optional<ButtonType> option = alert.showAndWait();

               if (option.get().equals(ButtonType.CANCEL)) {
                   alert = new Alert(Alert.AlertType.WARNING);
                   alert.setTitle("Message d'information");
                   alert.setHeaderText(null);
                   alert.setContentText("Suppression annulée!");
                   alert.showAndWait();
               } else {
                   prepare = connect.prepareStatement(deleteData);
                   prepare.executeUpdate();

                   alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Message d'information");
                   alert.setHeaderText(null);
                   alert.setContentText("Suppression réussie avec succès!");
                   alert.showAndWait();

                   historiqueShowListData();
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
            
        } else {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas d'accès à cette opération!");
            alert.showAndWait();
        }
        
    }

    
    //Relier les fenêtres
    public void SwitchForm(ActionEvent event) {

        if (event.getSource() == acceuilBtn) {
            acceuilForm.setVisible(true);
            clientForm.setVisible(false);
            serviceForm.setVisible(false);
            suivieForm.setVisible(false);
            factureForm.setVisible(false);
            historiqueForm.setVisible(false);

            acceuilBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #f1f1f1, #f1f1f1);");
            clientBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            serviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            makeserviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            suivieBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            historiqueBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");

            totaleClient();
            totaleService();
            totaleFaireService();
            totaleSoldeAujour();
            clickFservice = false;

        } else if (event.getSource() == clientBtn) {
            acceuilForm.setVisible(false);
            clientForm.setVisible(true);
            serviceForm.setVisible(false);
            suivieForm.setVisible(false);
            factureForm.setVisible(false);
            historiqueForm.setVisible(false);

            clientBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #f1f1f1, #f1f1f1);");
            acceuilBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            serviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            makeserviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            suivieBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            historiqueBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");

            //Pour initializez les combox dans clients
            addClientgenreList();
            addClientetablissementList();
            addClientniveauList();
            addClientpassantList();
            addClientTypeList();

            //Barre de recherche des clients
            addClientSearch();
            clickFservice = false;

        } else if (event.getSource() == serviceBtn) {
            acceuilForm.setVisible(false);
            clientForm.setVisible(false);
            serviceForm.setVisible(true);
            suivieForm.setVisible(false);
            factureForm.setVisible(false);
            historiqueForm.setVisible(false);

            serviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #f1f1f1, #f1f1f1);");
            clientBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            acceuilBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            makeserviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            suivieBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            historiqueBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");

            //Pour la liste des clients dans la base de donnée
            addServiceShowListData();

            //Pour la barre de recherche des services
            addServiceSearch();
            clickFservice = false;

        } else if (event.getSource() == makeserviceBtn) {
            acceuilForm.setVisible(false);
            clientForm.setVisible(false);
            serviceForm.setVisible(false);
            suivieForm.setVisible(false);
            factureForm.setVisible(true);
            historiqueForm.setVisible(false);

            makeserviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #f1f1f1, #f1f1f1);");
            clientBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            serviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            suivieBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            acceuilBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            historiqueBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");

            //Pour menu affiche produit
            menuDisplayCard();

            menuShowOrderListData();
            menuRestart();

            suivieListeData("");
            menuDisplayTotale();
            clickFservice = true;
            menuSelectOrder();

            menuServiceClientShowListData();
            menuServiceClientListData();

        } else if (event.getSource() == suivieBtn) {
            acceuilForm.setVisible(false);
            clientForm.setVisible(false);
            serviceForm.setVisible(false);
            suivieForm.setVisible(true);
            factureForm.setVisible(false);
            historiqueForm.setVisible(false);

            //Pour la suivie des clients
            suivieClientShowListData();
            suivieClientSelect();
            SuivieClientReset();
            suivieClientSearch();

            clientSuivieShowListData();
            clickFservice = false;

            suivieBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #f1f1f1, #f1f1f1);");
            clientBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            serviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            makeserviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            acceuilBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
            historiqueBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");

        } else if (event.getSource() == historiqueBtn) {
            if (getData.username.equals("Administrateur")) {
                acceuilForm.setVisible(false);
                clientForm.setVisible(false);
                serviceForm.setVisible(false);
                suivieForm.setVisible(false);
                factureForm.setVisible(false);
                historiqueForm.setVisible(true);

                historiqueBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #f1f1f1, #f1f1f1);");
                clientBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
                serviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
                makeserviceBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
                suivieBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");
                acceuilBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #d0d0d0, #d0d0d0);");

                historiqueShowListData();
                clickFservice = false;
            } else {
                Alert alert;

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Vous n'avez pas l'accès à cette opération!");
                alert.showAndWait();
            }
        }
    }

    private double x = 0;
    private double y = 0;

    //Pour la boutton de déconnexion
    public void logout() {
        clickFservice = false;
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
                
                Image image = new Image("/Image/1704805960156.jpg");
        
                stage.getIcons().add(image);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Pour la fermeture du fenetre
    public void close() {

        // quitte = logout();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Message de confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Vous n'êtes pas encore déconnecter! \n Etes-vous quand même sur vouloir quitter le programme?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get().equals(ButtonType.OK)) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Echec! \n S'il vous plaît, vous devez d'abord se déconnecter!");
            alert.showAndWait();
        }
    }

    //Pour la réduction d'une fenetre
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Pour la suivie des clients
        menuShowOrderListData();
        suivieClientShowListData();
        suivieListeData("");
//        menuDisplayTotale();
        menuSelectOrder();
        menuRestart();

        menuServiceClientShowListData();
        menuServiceClientListData();

        // Initialiser sID lors de l'initialisation du contrôleur
        suivieID();

        //Pour nom d'utilisateur dans dashboard
        displayUsername();

        //Pour voir listes client dans TableView
        addClientShowListData();

        //Pour voir liste service dans TableView
        addServiceShowListData();

        //Pour menu affiche service
        menuDisplayCard();

        //Pour les combox dans clients  
        addClientgenreList();
        addClientetablissementList();
        addClientniveauList();
        addClientpassantList();
        addClientTypeList();

        clientSuivieShowListData();

        historiqueShowListData();

        totaleClient();
        totaleService();
        totaleFaireService();
        totaleSoldeAujour();

    }

}
