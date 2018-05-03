/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmwrk;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author dhuangal
 */
public class HomeworkViewDetailsController extends Switchable implements Initializable {

    @FXML
    private JFXButton editButton;
    @FXML
    private Label titleLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label priorityLabel;
    @FXML
    private JFXButton backButton;
    
    @FXML
    public void handleGoBackToHomeworkList() {
        //Basically refresh
        Switchable.controllers.clear();
        Switchable.switchTo("HomeworkListView");
        
    }
    
    @FXML
    public void handleEdit() {
        //Getting the title of the homework item
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Edit Homework Item");
        dialog.setHeaderText("Enter homework item name");
        Optional<String> title = dialog.showAndWait();
        dialog.getEditor().clear();
       
        // Getting the completedStatus
        dialog.setHeaderText("Enter the completed status for your homework item.");
        Optional<String> status = dialog.showAndWait();
        dialog.getEditor().clear();
        
        // Getting the priority
        dialog.setHeaderText("Enter the priority for your homework item.");
        Optional<String> priority = dialog.showAndWait();
        dialog.getEditor().clear();
        
        //Updating everything after receiving the input
        if(title.isPresent() && status.isPresent() && priority.isPresent()){
            
            titleLabel.setText(title.get());
            statusLabel.setText(status.get());
            priorityLabel.setText(priority.get());
            
            Switchable.currentHomeworkItem.title = title.get();
            Switchable.currentHomeworkItem.completedStatus = status.get();
            Switchable.currentHomeworkItem.priority = priority.get();
            
            Switchable.homeworkItems.put(title.get(), Switchable.currentHomeworkItem);
        }
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(Switchable.currentHomeworkItem != null){
            titleLabel.setText(Switchable.currentHomeworkItem.title);
            statusLabel.setText(Switchable.currentHomeworkItem.completedStatus);
            priorityLabel.setText(Switchable.currentHomeworkItem.priority);
        }
        
    }    
    
}
