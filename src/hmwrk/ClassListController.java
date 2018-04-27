/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmwrk;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author dhuangal
 */
public class ClassListController extends Switchable implements Initializable {

    @FXML
    private JFXListView<String> classListView;
    
    //The list of classes that the student is enrolled in
    //These names will come from a JSON file, but are currently set to test values
    //just to see how it works.
    ObservableList<String> list = FXCollections.observableArrayList("Classes", "Go", "Here");
    @FXML
    private void addNewClass(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("New Class");
        dialog.setHeaderText("Enter your new class name");
        Optional<String> name = dialog.showAndWait();
        if(name.isPresent()){
            list.add(name.get());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Set the items in the listview to the classes recieved from the JSON
        classListView.setItems(list);
    }    
    
}
