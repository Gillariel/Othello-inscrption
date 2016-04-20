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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import utils.AppInfo;
import utils.FasterFXMLLoader;
import utils.MyDialog;
import views.models.Person;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {
    
    //Menu
    @FXML
    private MenuBar menuBar;
    
    @FXML
    private Menu participantMenu;
    @FXML
    private MenuItem newMemberMenuItem;
    @FXML
    private MenuItem deleteMemberMenuItem;
    @FXML
    private MenuItem modifiyMemberMenuItem;
    
    @FXML
    private Menu tournamentMenu;
    @FXML
    private MenuItem currentTournamentMenuItm;
    @FXML
    private MenuItem closeTournamentMenuItem;
    @FXML
    private MenuItem switchTournamentMenuItem;
    @FXML
    private MenuItem generateTournamentMenuItem;
    
    @FXML
    private MenuItem launchGameMenuItem;
    
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem rulesMenuItem;
    @FXML
    private MenuItem aboutMenuItem;
    //End Menu
    
    //TableView
    @FXML
    private TableView<Person> CurrentParticipantsView;
    @FXML
    private TableColumn<Person,String> pseudoTableColumn;
    @FXML
    private TableColumn<Person,String> firstnameTableColumn;
    @FXML
    private TableColumn<Person,String> lastnameTableColumn;
    @FXML
    private TableColumn<Person,Integer> wonGamesTableColumn;
    @FXML
    private TableColumn<Person,Integer> lostGamesTableColumn;
    private final ObservableList<Person> data = FXCollections.observableArrayList();
    
    //Buttons
    @FXML
    private Button btn_add_participant;
    @FXML
    private Button btn_delete_one;
    @FXML
    private Button btn_delete_all;
    
    @FXML
    private ImageView imgOthelloGame;
    
    @FXML
    private Pane mainMenuPane;
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CurrentParticipantsView.setEditable(true);
        
        pseudoTableColumn = new TableColumn("Pseudo");
        pseudoTableColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("pseudo"));
        
        firstnameTableColumn = new TableColumn("First Name");
        firstnameTableColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("firstname"));
        
        lastnameTableColumn = new TableColumn("Last Name");
        lastnameTableColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("lastname"));
        
        wonGamesTableColumn = new TableColumn("Won Games");
        wonGamesTableColumn.setCellValueFactory(new PropertyValueFactory<Person,Integer>("wonGames"));
        
        lostGamesTableColumn = new TableColumn("Lost Games");
        lostGamesTableColumn.setCellValueFactory(new PropertyValueFactory<Person,Integer>("lostGames"));
    
        CurrentParticipantsView.setItems(data);
        CurrentParticipantsView.getColumns().addAll(pseudoTableColumn,firstnameTableColumn, lastnameTableColumn, wonGamesTableColumn, lostGamesTableColumn);
        
    }    

    //Check La Classe utils.FasterFXMLLoader si tu as un doute, c'est juste une manière de ne pas réécrire 100x le meme code :p
    @FXML
    private void handleNewMember(ActionEvent event) { FasterFXMLLoader.load("/views/FXMLInscription.fxml", this); }
    @FXML
    // Méthode faisant le lien entre le click sur le menu et le lancement de la fenetre
    private void handleDeleteMember(ActionEvent event) {
        
    }
    @FXML
    private void handleModifyMember(ActionEvent event) { FasterFXMLLoader.load("/views/FXMLModifyMember.fxml", this, "Modify Member"); }
    
    @FXML
    private void handleCurrentTournament(ActionEvent event) {
    
    }
    @FXML
    private void handleCloseTournament(ActionEvent event) {
    
    }
    @FXML
    private void handleSwitchTournament(ActionEvent event) {
    
    }
    @FXML
    private void handleGenerateTournament(ActionEvent event) {
    
    }
    
    //Never using it while the second app is not created and bit completed yet!
    @FXML
    private void handleLaunchGame(ActionEvent event) {
        String cmd = "c:\\windows\\Othello_Game.exe";
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(cmd);
            p.waitFor();//si l'application doit attendre a ce que ce process fini
        }catch(Exception e) {
            MyDialog.warningDialog("Erreur", "Le programme 'Othello - Game' est-il bien installé? (Veuillez ne jamais le changer de place)");
        } 
    }

    @FXML
    private void handleRules(ActionEvent event) { AppInfo.showRules(); }
    @FXML
    private void handleAbout(ActionEvent event) { AppInfo.showLicence(); }
    
    @FXML
    private void handleBtnAddParticipant(ActionEvent event) { FasterFXMLLoader.load("/views/FXMLAddParticipant.fxml", this, "Add Participant"); }
    @FXML
    private void handleBtnDeleteParticipant(ActionEvent event) {
        if(!CurrentParticipantsView.getSelectionModel().isEmpty())
            CurrentParticipantsView.getItems().remove(CurrentParticipantsView.getSelectionModel().getSelectedIndex());
        else
            MyDialog.warningDialog("Warning", "no participants has been selected.\nPlease choose one before deleting.");
    }
    @FXML
    private void handleBtnDeleteAllParticipants(ActionEvent event) {
        if(MyDialog.confirmationDialog("All Delete", "Delete Participants not impact the Database","Are you sure you want to delete all the participants in the list?"))
            CurrentParticipantsView.getItems().clear();
    }
}
