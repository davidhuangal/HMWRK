/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmwrk;

import java.util.List;

/**
 *
 * @author dhuangal
 */
public class Course {
    
    public String title;
    
    private List<HomeworkItem> homeworkItems;
    
    public void addHomeworkItem(HomeworkItem item) {
        
        this.homeworkItems.add(item);
    }
    
    public List<HomeworkItem> getHomeworkItemList() {
        return this.homeworkItems;
    }
    
}