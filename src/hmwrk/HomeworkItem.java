/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmwrk;

/**
 *
 * @author dhuangal
 */
public class HomeworkItem {
    
    public String title;
    public String completedStatus;
    public String priority;
    
    public HomeworkItem(String title, String completedStatus, String priority) {
        
        this.title = title;
        this.completedStatus = completedStatus;
        this.priority = priority;
    }
    
}
