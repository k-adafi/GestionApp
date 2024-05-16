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
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jirehstudentsapp.database;
import jirehstudentsapp.getData;

/**
 *
 * @author KADAFI Ben
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane main_form;

    @FXML
    private Button OpenSign;
    
    @FXML
    private Button SeconnectPasseBtn;

    @FXML
    private AnchorPane seconnecterForm;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private Button close;

    @FXML
    private Button MotdePasseBtn;

    @FXML
    private AnchorPane motdepasseForm;

    @FXML
    private TextField username1;
    
    @FXML
    private TextField username11;
    
    @FXML
    private PasswordField password11;
    

    @FXML
    private Button loginBtn1;

    @FXML
    private Button close1;

   
    
    //Database Tools
    
    private Connection connect;
    
    private PreparedStatement prepare;
    
    private Statement statement;
    
    private ResultSet result;
   
    private double x = 0; 
    private double y = 0;
    
    
    
    public void ModifPassord() {

        String sql = "UPDATE admin SET MotDePasse = '"
                + password11.getText() + "' WHERE NomUtilisateur = '"
                + username1.getText() + "' AND Email = '"
                + username11.getText() + "'";

        connect = database.ConnectDb();

        try {
            Alert alert;
            if (username1.getText().isEmpty()
                    || username11.getText().isEmpty()
                    || password11.getText().isEmpty())
                     {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez complètez ces formulaires!");
                alert.showAndWait();
            } else {
 
                String check = "SELECT NomUtilisateur FROM admin WHERE NomUtilisateur = '"
                        +username1.getText()+"'";
                
                statement = connect.createStatement();
                result = statement.executeQuery(check);
                
                if(!result.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Cette administrateur: " + username1.getText() + " n'existe pas!");
                    alert.showAndWait();
                           
                }else {
                
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Message de confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("Êtes-vous sur de vouloir modifier votre mot de passe?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {
                        statement = connect.createStatement();
                        statement.executeUpdate(sql);

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Message d'information");
                        alert.setHeaderText(null);
                        alert.setContentText("Mot de passe a été modifier avec succès!");
                        alert.showAndWait();

                    }else{
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Message d'erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Echec de modification du mot de passe!");
                        alert.showAndWait();
                    }

                    username1.setText("");
                    username11.setText("");
                    password11.setText("");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void switchFormConnect(ActionEvent event){
        
        if (event.getSource() == MotdePasseBtn) {
            motdepasseForm.setVisible(true);
            seconnecterForm.setVisible(false);

        } else if (event.getSource() == SeconnectPasseBtn) {
            motdepasseForm.setVisible(false);
            seconnecterForm.setVisible(true);
        }
        
    }
    
    
    public void loginAdmin(){
        
        String sql = "SELECT * FROM Admin WHERE NomUtilisateur = ? and MotDePasse = ? ";
        
        connect = database.ConnectDb();

        try{
           prepare = connect.prepareStatement(sql);
           prepare.setString(1, username.getText());
           prepare.setString(2, password.getText());
           
           result = prepare.executeQuery();
           Alert alert;
           
           if(username.getText().isEmpty() || password.getText().isEmpty()){
               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR MESSAGE");
               alert.setHeaderText(null);
               alert.setContentText("S'il vous plaît, vous devez d'abord complétez ces formulaires.");
               alert.showAndWait();
               
           }else{
               if(result.next()){
                   getData.username = username.getText();
                   
                   alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("MESSAGE D'INFORMATION");
                   alert.setHeaderText(null);
                   alert.setContentText("Connection avec succès!");
                   alert.showAndWait();
                   
                   loginBtn.getScene().getWindow().hide();
                   Parent root = FXMLLoader.load(getClass().getResource("/Interface/Dashboard.fxml"));
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
                    
                    username.setText("");
                    password.setText("");
                }else{
                   alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("ERROR MESSAGE");
                   alert.setHeaderText(null);
                   alert.setContentText("Une de ces informations est incorecte");
                   alert.showAndWait();
                }
               
               
           }
           
        }catch(Exception e){e.printStackTrace();}
    }

    //Pour la fermeture du fenetre
    public void close(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Message de confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sur de vouloir quitter le programme?");
        Optional<ButtonType> option = alert.showAndWait();
        
        if(option.get().equals(ButtonType.OK)){
             System.exit(0);
        }
    }
    
    //Pour l'ouverture du fenetre d'inscription
    public void OpenSign(){
        try {
	  OpenSign.getScene().getWindow().hide();
          Parent root = FXMLLoader.load(getClass().getResource("/Interface/Inscription.fxml"));
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
          
          
	} catch(Exception e){e.printStackTrace();}
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = database.ConnectDb();
        // TODO connection BBD
    }    
    
}
