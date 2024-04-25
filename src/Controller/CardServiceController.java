/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import jirehstudentsapp.serviceData;

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
    private Spinner<?> menuServiceSpinner;

    @FXML
    private Button menuServiceAjouterBtn;
    
    private serviceData serveData;
    
    private Image imageService;
    
    public void setData(serviceData serveData){
        this.serveData = serveData;
        
        menuServiceNom.setText(serveData.getServiceNom());
        menuServicePrix.setText(String.valueOf(serveData.getServicePrix()));
        
        String path = "File:" + serveData.getServiceImage();
        imageService = new Image(path, 200, 160, false, true);
        menuServiceImageView.setImage(imageService);
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
