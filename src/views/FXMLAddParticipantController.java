/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import models.Contender;
import models.Participant;
import utils.MyDialog;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLAddParticipantController implements Initializable {

    private FXMLDocumentController mainController;

    @FXML
    private MenuBar mainMenuMenuBar;
    @FXML
    private ComboBox<String> partcipantComboBox;
    @FXML
    private Label partcipantLabel;
    @FXML
    private Button btn_confirm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datas.ParticipantsManager provider = new datas.ParticipantsManager();
        ObservableList<String> pseudoList = FXCollections.observableArrayList();

        for (Participant p : provider.selectAllParticipants()) {
            pseudoList.add(p.getPseudo());
           
        }

        partcipantComboBox.getItems().addAll(pseudoList);

        btn_confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if (!partcipantComboBox.getSelectionModel().isEmpty()) {
                    
                    mainController.addDataToTableView(partcipantComboBox.getItems().get(partcipantComboBox.getSelectionModel().getSelectedIndex()));
                    partcipantComboBox.getItems().remove(partcipantComboBox.getSelectionModel().getSelectedIndex());
                     
                } else {
                    MyDialog.warningDialog("Erreur", "Please select participant to add in the list below or cancel.");
                }
            }
        });
    }

    public void setMainController(FXMLDocumentController c) {
        this.mainController = c;
    }
}
