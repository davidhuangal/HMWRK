/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmwrk;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;


/**
 *
 * @author dhuangal
 */
public class Student {
    
    public String name;
    public List<Course> courseList;
    
    public void loadStudentFromJSON(JSONObject studentJO) {
        
        // Get the name from the JSON file
        this.name = (String) studentJO.get("name");
        
        // Create a JSONArray and iterator to loop through each course the student is taking
        JSONArray ja1 = (JSONArray) studentJO.get("courseList");
        
        Iterator itr1 = ja1.iterator();
        
        while(itr1.hasNext()) {
            
            JSONObject currentCourse = (JSONObject) itr1.next();
            
            Course course = new Course();
            
            course.title = (String) currentCourse.get("courseName");
            
            JSONArray ja2 = (JSONArray) currentCourse.get("homeworkItems");
            
            Iterator itr2 = ja2.iterator();
            
            while(itr2.hasNext()) {
                
                JSONObject currentHomeworkItem = (JSONObject) itr2.next();
                
                String title = (String) currentHomeworkItem.get("title");
                String completedStatus = (String) currentHomeworkItem.get("completedStatus");
                String priority = (String) currentHomeworkItem.get("priority");
                
                HomeworkItem homeworkItem = new HomeworkItem(title, completedStatus, priority);
                
                course.addHomeworkItem(homeworkItem);
                
            }
            
            courseList.add(course);        
        }        
    }
    
    
    public void writeStudentToJSON(String fileName) throws FileNotFoundException {
        
        // Create a JSON object
        
        JSONObject studentJO = new JSONObject();
        
        studentJO.put("name", name);
        
        JSONArray courseArray = new JSONArray();
        
        // Loop through each course the student is taking
        for(int i = 0; i < courseList.size(); i++) {
            
            JSONObject courseJO = new JSONObject();
                       
            Course currentCourse = courseList.get(i);
            
            courseJO.put("courseName", currentCourse.title);
            
            JSONArray homeworkArray = new JSONArray();
            
            List<HomeworkItem> currentHomeworkList = currentCourse.getHomeworkItemList();
            
            // loop through current homework list for the current course
            // Adding each item in that list to the homeworkArray
            for(int j = 0; j < currentHomeworkList.size(); j++) {
                
                String title = (String) currentHomeworkList.get(j).title;
                String completedStatus = (String) currentHomeworkList.get(j).completedStatus;
                String priority = (String) currentHomeworkList.get(j).priority;
                
                JSONObject homeworkJO = new JSONObject();
                
                homeworkJO.put("title", title);
                homeworkJO.put("completedStatus", completedStatus);
                homeworkJO.put("priority", priority);
                
                homeworkArray.add(homeworkJO);
                
            }
            
            courseJO.put("homeworkItems", homeworkArray);
            
            courseArray.add(courseJO);
        }
        
        studentJO.put("courseList", courseArray);
        
        
        //Write the JSON object to the file
        PrintWriter pw = new PrintWriter(fileName);
        pw.write(studentJO.toJSONString());       
    }
    
}
