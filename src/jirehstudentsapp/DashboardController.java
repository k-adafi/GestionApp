/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jirehstudentsapp;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author KADAFI Ben
 */
public class DashboardController implements Initializable{
    
    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label NomUtilisateur;

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

    @FXML
    private AnchorPane serviceForm;

    @FXML
    private AnchorPane clientForm;

    @FXML
    private TableColumn<?, ?> clientIDCol;

    @FXML
    private TableColumn<?, ?> clientNomCol;

    @FXML
    private TableColumn<?, ?> clientPrenomCol;

    @FXML
    private TableColumn<?, ?> clientGenreCol;

    @FXML
    private TableColumn<?, ?> clientDateNCol;

    @FXML
    private TableColumn<?, ?> clientCINCol;

    @FXML
    private TableColumn<?, ?> clientEtablissementCol;

    @FXML
    private TableColumn<?, ?> clientMentionCol;

    @FXML
    private TableColumn<?, ?> clientNiveauCol;

    @FXML
    private TableColumn<?, ?> clientCodageCol;

    @FXML
    private TableColumn<?, ?> clientPassantCol;

    @FXML
    private TableColumn<?, ?> clientTelCol;

    @FXML
    private TableColumn<?, ?> clientAdressCol;

    @FXML
    private TextField clientRechercheText;

    @FXML
    private Button clientRechercheBtn;

    @FXML
    private TextField clientID;

    @FXML
    private TextField clientNom;

    @FXML
    private TextField clientPrenom;

    @FXML
    private ComboBox<?> clientGenre;

    @FXML
    private TextField clientCIN;

    @FXML
    private TextField clientMention;

    @FXML
    private TextField clientCodage;

    @FXML
    private TextField clientTel;

    @FXML
    private ComboBox<?> clientPassant;

    @FXML
    private ComboBox<?> clientNiveau;

    @FXML
    private ComboBox<?> clientEtablissement;

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
    private TextField serviceRechercheText;

    @FXML
    private Button serviceRechercheBtn;

    @FXML
    private TableView<?> serviceTableView;

    @FXML
    private TableColumn<?, ?> serviceIDCol;

    @FXML
    private TableColumn<?, ?> serviceNomCol;

    @FXML
    private TableColumn<?, ?> serviceTypeCol;

    @FXML
    private TableColumn<?, ?> servicePrixCol;

    @FXML
    private TableColumn<?, ?> serviceDureeCol;

    @FXML
    private TableColumn<?, ?> serviceDateDebutCol;

    @FXML
    private TableColumn<?, ?> serviceDateFinCol;

    @FXML
    private TextField serviceID;

    @FXML
    private TextField serviceNom;

    @FXML
    private TextField serviceType;

    @FXML
    private Button serviceAjouterBtn;

    @FXML
    private Button serviceModifierBtn;

    @FXML
    private Button serviceEffacerBtn;

    @FXML
    private Button serviceActualiserBtn;

    @FXML
    private TextField servicePrix;

    @FXML
    private TextField serviceDuree;

    @FXML
    private DatePicker serviceDateDebut;

    @FXML
    private DatePicker serviceDateFin;

    @FXML
    private AnchorPane suivieForm;

    @FXML
    private TextField serviceRechercheText1;

    @FXML
    private Button serviceRechercheBtn1;

    @FXML
    private TableView<?> serviceTableView1;

    @FXML
    private TableColumn<?, ?> serviceIDCol1;

    @FXML
    private TableColumn<?, ?> serviceNomCol1;

    @FXML
    private TableColumn<?, ?> serviceTypeCol1;

    @FXML
    private TableColumn<?, ?> servicePrixCol1;

    @FXML
    private TableColumn<?, ?> serviceDureeCol1;

    @FXML
    private TableColumn<?, ?> serviceDateDebutCol1;

    @FXML
    private TableColumn<?, ?> serviceDateFinCol1;

    @FXML
    private TextField serviceID1;

    @FXML
    private Button serviceAjouterBtn1;

    @FXML
    private Button serviceActualiserBtn1;

    @FXML
    private TextField servicePrix1;

    @FXML
    private TextField serviceDuree1;

    @FXML
    private TextField serviceDuree11;

    @FXML
    private AnchorPane factureForm;

    @FXML
    private TextField factureID;

    @FXML
    private ImageView factureImage;

    @FXML
    private Button facturePrint;

    @FXML
    private DatePicker factureDate;

    @FXML
    private ComboBox<?> servicequantite;

    @FXML
    private TextField factureMonttant;

    @FXML
    private AnchorPane historiqueForm;

    @FXML
    private TableView<?> historiqueTableview;

    @FXML
    private TableColumn<?, ?> historiqueDateCol;

    @FXML
    private TableColumn<?, ?> historiqueActiviteCol;

    @FXML
    private AnchorPane makeServiceForm;

    @FXML
    private Button makeserviceAjouterBtn;

    @FXML
    private Button makeserviceAnulerBtn;

    @FXML
    private Button makeservicePayerBtn;
    
    //Relier les fenêtres
    public void SwitchForm(ActionEvent event){
        
        if(event.getSource() == acceuilBtn){
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
            
        }else if(event.getSource() == clientBtn){
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
            
        }else if(event.getSource() == serviceBtn){
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
            
        }else if(event.getSource() == makeserviceBtn){
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
            
        }else if(event.getSource() == suivieBtn){
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
            
        }else if(event.getSource() == factureBtn){
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
            
        }else if(event.getSource() == historiqueBtn){
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
    
    //Pour la boutton de déconnexion
    private double x = 0;
    private double y = 0;
    
    public void logout(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Message de confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sur de se déconnecter");
        Optional<ButtonType> option = alert.showAndWait();
        
        try {
	   if(option.get().equals(ButtonType.OK)){
            
                signOutBtn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
        
                root.setOnMousePressed((MouseEvent event) ->{
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) ->{
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) ->{
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show(); 
            }
          
	} catch(Exception e){e.printStackTrace();}
    }

    
    //Pour la fermeture du fenetre
    public void close(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Message de confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sur de vouloir quitter le programme?");
        Optional<ButtonType> option = alert.showAndWait();
        
        if(option.get().equals(ButtonType.OK)){
             System.exit(0);
        }
    }
    
    //Pour la réduction d'une fenetre
    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }
    
}
