/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.image.Image;
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
    
    DashboardController dashForm = new DashboardController();
    
    //Pour modification de mot de passe
    public void ModifPassord() {
        
        String updateSql = "UPDATE admin SET MotDePasse = ? WHERE NomUtilisateur = ? AND Email = ?";
        
        String checkSql = "SELECT NomUtilisateur, Email FROM admin WHERE NomUtilisateur = ? AND Email= ?";

        try (Connection connect = database.ConnectDb();
//             PreparedStatement updateStatement = connect.prepareStatement(updateSql);
             PreparedStatement checkStatement = connect.prepareStatement(checkSql)) {

            Alert alert;

            // Vérifiez si l'utilisateur existe
            checkStatement.setString(1, username1.getText());
            checkStatement.setString(2, username11.getText());
            ResultSet result = checkStatement.executeQuery();
            
            if (username1.getText().isEmpty() || username11.getText().isEmpty() || password11.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, vous devez compléter ces formulaires!");
                alert.showAndWait();
                return; 
                
             }else if (!result.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Cet administrateur: '" + username1.getText() + "'ou '"+username11.getText()+"' n'existe pas!");
                alert.showAndWait();
                return;
          
            }else if(password11.getText().length() < 8){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plaît, le mot de passe doit être minimum à 8 caractères!");
                alert.showAndWait();
                
            }else{
              // Demandez la confirmation avant de mettre à jour le mot de passe
             alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Message de confirmation");
             alert.setHeaderText(null);
             alert.setContentText("Êtes-vous sûr de vouloir modifier votre mot de passe?");
             Optional<ButtonType> option = alert.showAndWait();

             if (option.isPresent() && option.get() == ButtonType.OK) {
                           
                 // Mettez à jour le mot de passe
                 PreparedStatement preparedStatement = connect.prepareStatement(updateSql);
                 preparedStatement.setString(1, password11.getText());
                 preparedStatement.setString(2, username1.getText());
                 preparedStatement.setString(3, username11.getText());
                 preparedStatement.executeUpdate();
                 System.err.println("++++++++"+password11.getText());

//                 updateStatement.setString(1, password11.getText());
//                 updateStatement.setString(2, username1.getText());
//                 updateStatement.setString(3, username11.getText());
//                 updateStatement.executeUpdate();
                
                     dashForm.historique("Modification de mot de passe de l'utilisateur réussi.", username1.getText());

                     alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Message d'information");
                     alert.setHeaderText(null);
                     alert.setContentText("Le mot de passe a été modifié avec succès!");
                     alert.showAndWait();
                 
            } else {
                    
               dashForm.historique("Echec de la modification de mot de passe de l'utilisateur", username1.getText());
                    
               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Message d'erreur");
               alert.setHeaderText(null);
               alert.setContentText("Échec de la modification du mot de passe!");
               alert.showAndWait();
            }
           

            // Réinitialisez les champs de saisie
            username1.setText("");
            username11.setText("");
            password11.setText("");

        }
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    //Pour switch forme dans le menu demmarer
    public void switchFormConnect(ActionEvent event){
        
        if (event.getSource() == MotdePasseBtn) {
            motdepasseForm.setVisible(true);
            seconnecterForm.setVisible(false);

        } else if (event.getSource() == SeconnectPasseBtn) {
            motdepasseForm.setVisible(false);
            seconnecterForm.setVisible(true);
        }
        
    }
    
    //Pour le boutton se connecter
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
                   
                   Toolkit tk = Toolkit.getDefaultToolkit();
                   Dimension dim = tk.getScreenSize();
                   int width = dim.width;
                   int height = dim.height;
                   Scene scene = new Scene(root,width,height);
        
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
                    
                    Image image = new Image("/Image/1704805960156.jpg");
        
                    stage.getIcons().add(image);

                    stage.setScene(scene);
                    stage.show(); 
                    
                    username.setText("");
                    password.setText("");
                    
                    dashForm.historique("Connexion de l'utilisateur: ", getData.username);
                    
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
    public void close() throws SQLException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Message de confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sur de vouloir quitter le programme?");
        Optional<ButtonType> option = alert.showAndWait();
        
        if(option.get().equals(ButtonType.OK)){
            dashForm.historique("Fermeture du programme", "Le dernier utilisateur l' a fermé");
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
          
          Image image = new Image("/Image/1704805960156.jpg");
        
          stage.getIcons().add(image);

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
