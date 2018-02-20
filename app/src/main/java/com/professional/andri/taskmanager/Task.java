package com.professional.andri.taskmanager;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Andri on 19/11/2017.
 */

public class Task {
    private String id;
    private String title;
    private String detail;
    private String image;

    public static ArrayList<Task> feedTask(int numOfTasks){
        String[] taskTitles = { "Task #A1", "Task #A2", "Task #A3",
                "Task #B1", "Task #B2", "Task #B3",
                "Task #C1", "Task #C2", "Task #C3",
                "Task #D1"
        };
        String[] taskDetails = { "Complete the home page", "Fix bug in filter mode", "Change the notification page",
                                "Improve scroll performance", "Implement paypal payment", "Integrate analytics to project",
                                "Apply swipe to refresh", "Give the image proper aspect ratio", "Refactor the project",
                                "Apply email feature"
                                };
        
        ArrayList<Task> tasks = new ArrayList<>(numOfTasks);
        for(int i = 0;i< numOfTasks;i++){
            Task task = new Task();
            task.id = "" + (i + 1);
            task.title = taskTitles[i];
            task.detail = taskDetails[i];
            tasks.add(task);
        }
        return tasks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
