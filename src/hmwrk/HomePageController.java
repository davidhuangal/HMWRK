/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmwrk;

import com.jfoenix.controls.JFXButton;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author dhuangal
 */
public class HomePageController extends Switchable implements Initializable {

    @FXML
    private JFXButton loadButton;
    @FXML
    private JFXButton newButton;

    private Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleLoadUserList(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null){
            try{
                FileReader fileReader = new FileReader(file.getPath());
                
                try {
                    Object obj = new JSONParser().parse(fileReader);
                    JSONObject jo = (JSONObject) obj;
                    
                    String name = (String) jo.get("name");
                    
                    Student student = new Student();
                    
                    student.loadStudentFromJSON(jo);
                    
                    Switchable.currentStudent = student;
                                                            
                } catch (ParseException ex) {
                    Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
                }

                fileReader.close();
                Switchable.switchTo("ClassList");
            }
            catch(IOException ioex){
                String message = "Exception occurred while opening " + file.getPath();
                System.out.println(message);
            }
        }
    }
    @FXML
    private void handleNewUserList(ActionEvent event) {
        Switchable.switchTo("ClassList");
    }
}
