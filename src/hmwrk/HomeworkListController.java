/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmwrk;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author dhuangal
 */
public class HomeworkListController extends Switchable implements Initializable {

    @FXML
    private JFXListView<String> hwListView;
    @FXML
    private JFXButton viewDetailsButton;
    @FXML
    private JFXButton backButton;
    @FXML
    private Label courseLabel;
    
    
    
    @FXML
    private void addNewHomeworkItem(ActionEvent event){
        
        //Getting the title of the homework item
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("New Homework Item");
        dialog.setHeaderText("Enter your new homework item name");
        Optional<String> title = dialog.showAndWait();
        dialog.getEditor().clear();
       
        // Getting the completedStatus
        dialog.setTitle("New Homework Item");
        dialog.setHeaderText("Enter the completed status for your homework item.");
        Optional<String> status = dialog.showAndWait();
        dialog.getEditor().clear();
        
        // Getting the priority
        dialog.setTitle("New Homework Item");
        dialog.setHeaderText("Enter the priority for your homework item.");
        Optional<String> priority = dialog.showAndWait();
        dialog.getEditor().clear();
        
        if(title.isPresent() && status.isPresent() && priority.isPresent()){
            hwListView.getItems().add(title.get());
            HomeworkItem hwItem = new HomeworkItem(title.get(), status.get(), priority.get());
            Switchable.currentCourse.addHomeworkItem(hwItem);
            Switchable.homeworkItems.put(title.get(), hwItem);
        }        
        
    }
    
    
    public void handleViewDetails() {
        
        String selection = (String) hwListView.getSelectionModel().getSelectedItem();
        
        Switchable.currentHomeworkItem = Switchable.homeworkItems.get(selection);
        
        Switchable.switchTo("HomeworkViewDetails");
    }
    
    
    @FXML
    public void handleGoBackToCourses() {        
        Switchable.switchTo("ClassList");
        
        //Basically refresh the listView
        Switchable.controllers.clear();
    }
    
    public void populateList() {
        
        hwListView.getItems().clear();
                
        //Set the items in the listview to the homework items recieved from the JSON      
        if(Switchable.currentStudent != null) {
            for(int i = 0; i < Switchable.currentCourse.getHomeworkItemList().size(); i++) {
                
                String title = (String) Switchable.currentCourse.getHomeworkItemList().get(i).title;
                               
                hwListView.getItems().add(title);
            }
        }
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        courseLabel.setText(Switchable.currentCourse.title);
        
        populateList();

    }
    
    
}
