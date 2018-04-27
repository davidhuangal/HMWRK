/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmwrk;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author dbhuangal1204
 */
public abstract class Switchable {
    public static Scene scene;
    public static final HashMap<String, Switchable> controllers = new HashMap<>();
    public static Student currentStudent = null;
    
    private Parent root;  
    
    public static Switchable add(String name) {
        Switchable controller;
        
        controller = controllers.get(name);
        
        if(controller == null) {
            try {
                //New instance of the view
                FXMLLoader loader = new FXMLLoader(Switchable.class.getResource(name + ".fxml"));
                
                //Load the view
                Parent root = loader.load();
                
                controller = loader.getController();
                
                controller.setRoot(root);
                
                controllers.put(name, controller);
                
                
            } catch (IOException ex) { //If the .fxml file is not there
                Logger.getLogger(Switchable.class.getName()).log(Level.SEVERE, null, ex);
                controller = null;
            } catch (Exception ex) { //If any other exception happens
                System.out.println("Error loading " + name + ".fxml \n\n" + ex);
                controller = null;
            }
        }
        
        return controller;
    }
    
    public static void switchTo(String name) {
        Switchable controller = controllers.get(name);
        
        if(controller == null) {
            controller = add(name);
        }
        
        if(controller != null) {
            if(scene != null) {
                scene.setRoot(controller.getRoot());
            }
        }
        
        
    }
    
    public void setRoot(Parent root) {
        this.root = root;
    }
    
    public Parent getRoot() {
        return root;
    }
    
    public static Switchable getControllerByName(String name) {
        return controllers.get(name);
    }
}