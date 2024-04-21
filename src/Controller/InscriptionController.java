/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * FXML Controller class
 *
 * @author KADAFI Ben
 */
public class InscriptionController implements Initializable {
          
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button OpenConnect;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;
      
    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    
    @FXML
    private Button addAdminBtn;

    
    @FXML
    private Button close;
    
    
    
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    private Connection connect;

    
    //Ajout nouveau administrateur (mbola tsy mandeha)
    public void addAdminAdd(){  
           
        String sql = "INSERT INTO admin"
                + "(NomUtilisateur, Email, MotDePasse)"
                + "VALUES(?,?,?)";
        
        connect = database.ConnectDb();
        
        try{     
            Alert alert;
            
            if(username.getText().isEmpty()
                    || email.getText().isEmpty()
                    || password.getText().isEmpty())
            { 
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez complètez ces formulaires!");
                alert.showAndWait();
            }
            else
            {
                String check = "SELECT NomUtilisateur FROM admin WHERE NomUtilisateur = '"
                        +username.getText()+"'";
                
                statement = connect.createStatement();
                result = statement.executeQuery(check);
                
                if(result.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Cette administrateur: " + username.getText() + " existe déjà!");
                    alert.showAndWait();
                }
                else
                {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, username.getText());
                    prepare.setString(2, email.getText());
                    prepare.setString(3, password.getText());
                    prepare.executeUpdate();
                       
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Administrateur a été ajouté avec succès!");
                    alert.showAndWait();
                    
                    username.setText("");
                    email.setText("");
                    password.setText("");
                }
            
            }
            
        }catch(Exception e){e.printStackTrace();}
    }
    
    
    
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
     
    private double x = 0; 
    private double y = 0;
    
     public void OpenConnect(){
        try {
	  OpenConnect.getScene().getWindow().hide();
          Parent root = FXMLLoader.load(getClass().getResource("/Interface/FXMLDocument.fxml"));
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
        // TODO
        connect = database.ConnectDb();
    }    
    
}
