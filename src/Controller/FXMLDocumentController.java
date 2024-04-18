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
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;
    
    
    @FXML
    private Button close;
    
    
    @FXML
    private Button OpenSign;
    
    
    //Database Tools
    
    private Connection connect;
    
    private PreparedStatement prepare;
    
    private ResultSet result;
   
    private double x = 0; 
    private double y = 0;
    
    
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
