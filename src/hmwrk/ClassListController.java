/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmwrk;

import com.jfoenix.controls.JFXListView;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dhuangal
 */
public class ClassListController extends Switchable implements Initializable {

    private Stage stage;
    
    @FXML
    private JFXListView<String> classListView;
    
    ObservableList<String> list;
    
    @FXML
    private void addNewClass(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("New Class");
        dialog.setHeaderText("Enter your new class name");
        Optional<String> name = dialog.showAndWait();
        if(name.isPresent()){
            classListView.getItems().add(name.get());
            Course course = new Course();
            course.title = name.get();
            Switchable.currentStudent.courseList.add(course);
        }
    }
    @FXML
    private void savePerson(ActionEvent event) throws FileNotFoundException{
        FileChooser chooser = new FileChooser();
        File file = chooser.showSaveDialog(stage);
        if(file != null){
            Switchable.currentStudent.writeStudentToJSON(file.getPath());
        }
    }

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
