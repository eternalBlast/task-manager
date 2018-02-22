package com.professional.andri.taskmanager;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by Andri on 19/11/2017.
 */

@Parcel
public class Task {
    String id;
    String title;
    String detail;
    String image;

    public Task(){

    }

    public static ArrayList<Task> feedTask(int numOfTasks){
        String[] taskImages = {"task_1", "task_2", "task_3", "task_4", "task_5",
                                "task_1", "task_2", "task_3", "task_4", "task_5",
                                "task_1", "task_2", "task_3", "task_4", "task_5",
                                "task_1", "task_2", "task_3", "task_4", "task_5"};

        String[] taskTitles = { "Task #A1", "Task #A2", "Task #A3",
                "Task #B1", "Task #B2", "Task #B3",
                "Task #C1", "Task #C2", "Task #C3",
                "Task #D1", "Task #D2", "Task #D3",
                "Task #D4", "Task #D5", "Task #D6",
                "Task #E1", "Task #E2", "Task #E3",
                "Task #F1", "Task #F2"/*, "Task #F3"*/

        };
        String[] taskDetails = { "Complete the home page", "Fix bug in filter mode", "Change the notification page",
                                "Improve scroll performance", "Implement paypal payment", "Integrate analytics to project",
                                "Apply swipe to refresh", "Give the image proper aspect ratio", "Refactor the project",
                                "Apply email feature", "Delete unused arrays", "Add scanner function", "Apply fingerprint feature",
                                "Refactor purchase history class", "Add google analytics", "Add fabric event to log",
                                "Fix the problem in detail page", "Implement nearby area feature", "Fix search result for list",
                                "Align text and organize", "Refactor to use dependency injection"
                                };
        
        ArrayList<Task> tasks = new ArrayList<>(numOfTasks);
        for(int i = 0;i< numOfTasks;i++){
            Task task = new Task();
            task.id = "" + (i + 1);
            task.title = taskTitles[i];
            task.detail = taskDetails[i];
            task.image = taskImages[i];
            tasks.add(task);
        }
        return tasks;
    }

}
