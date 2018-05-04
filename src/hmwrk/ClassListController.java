/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmwrk;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
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
    private JFXButton viewCourseButton;
    
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
            Switchable.courses.put(name.get(), course);
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
    
    @FXML
    public void handleViewCourse() {
            
            String selection = classListView.getSelectionModel().getSelectedItem();
            
            if(selection != null){
        
                Switchable.currentCourse = Switchable.courses.get(selection);
        
        
                Switchable.switchTo("HomeworkListView");
                
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pick a class");
            alert.setHeaderText("Pick a class");
            alert.setContentText("Please pick a class to be viewed.");
            alert.showAndWait();
        }
    }
    @FXML
    public void deleteCourse() {
        String selection = classListView.getSelectionModel().getSelectedItem();

        Switchable.currentCourse = Switchable.courses.get(selection);
        
        Switchable.currentStudent.removeClass(currentCourse);
        
        classListView.getItems().clear();
        
        if(Switchable.currentStudent != null) {
            for(int i = 0; i < Switchable.currentStudent.getCourseList().size(); i++) {
                
                String title = (String) Switchable.currentStudent.getCourseList().get(i).title;
                               
                classListView.getItems().add(title);
            }
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
                
                // Add this course to the hashmap of courses
                Switchable.courses.put(title, Switchable.currentStudent.courseList.get(i));
                
                for(int j = 0; j < Switchable.currentStudent.courseList.get(i).getHomeworkItemList().size(); j++) {
                    String hwTitle = (String) Switchable.currentStudent.courseList.get(i).getHomeworkItemList().get(j).title;
                    
                    Switchable.homeworkItems.put(hwTitle, Switchable.currentStudent.courseList.get(i).getHomeworkItemList().get(j));
                }
                
                classListView.getItems().add(title);
                
                
            }
        }
        

    }

    
}
