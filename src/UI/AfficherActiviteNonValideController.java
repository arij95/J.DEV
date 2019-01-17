/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entites.Activite;

import Services.AdminService;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author challakh
 */
public class AfficherActiviteNonValideController implements Initializable {

    @FXML
    private TableView<Activite> tableau;
    @FXML
    private TableColumn<Activite, Integer> col_id;
    @FXML
    private TableColumn<Activite, String> col_nom;
    @FXML
    private Button btn_valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            AdminService as=new AdminService();
            
            List<Activite> activites=as.afficherActiviteNonValide();
            ObservableList obs;
            obs = FXCollections.observableArrayList(activites);
            tableau.setItems(obs);
            col_id.setCellValueFactory(new  PropertyValueFactory<>("id"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            System.out.println(activites);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherActiviteNonValideController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Valider(ActionEvent event) {
        try {
            try {
                Activite a =new Activite();
                a=tableau.getSelectionModel().getSelectedItem();
                AdminService as=new AdminService();
                as.VerifierActivite(a.getId());
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("information Dialog");
                alert1.setHeaderText(null);
                alert1.setContentText("Activité verifié");
                alert1.show();
            } catch (SQLException ex) {
                Logger.getLogger(AfficherActiviteNonValideController.class.getName()).log(Level.SEVERE, null, ex);
            }
            AdminService as=new AdminService();
            
            List<Activite> activites=as.afficherActiviteNonValide();
            ObservableList obs;
            obs = FXCollections.observableArrayList(activites);
            tableau.setItems(obs);
            col_id.setCellValueFactory(new  PropertyValueFactory<>("id"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            System.out.println(activites);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherActiviteNonValideController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
