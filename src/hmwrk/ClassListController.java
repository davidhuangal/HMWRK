/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmwrk;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author dhuangal
 */
public class ClassListController extends Switchable implements Initializable {

    @FXML
    private JFXListView<String> classListView;
    
    //The list of classes that the student is enrolled in
    //These names will come from a JSON file.
//    ObservableList<String> list = FXCollections.observableArrayList("Classes", "Go", "Here");
    ObservableList<String> list;

      

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Set the items in the listview to the classes recieved from the JSON
        if(Switchable.currentStudent != null) {
            for(int i = 0; i < Switchable.currentStudent.courseList.size(); i++) {
                
                String title = (String) Switchable.currentStudent.courseList.get(i).title;
                
                classListView.getItems().add(title);
            }
        }
        

    }    
    
}
