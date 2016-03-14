package com.example.kimschuiten.todolist30;

/**
 * Creates a To Do List item
 */

public class ToDoItem {

    // Fields: properties of class object
    private String task;
    private boolean completed;
    private String title;

    // Constructor: defines how instance is created
    public ToDoItem (String taskArg, String titleArg){
        task = taskArg;
        completed = false;
        title = titleArg;
    }

    // Methods
    public void finish(){
        completed = true;
    }

    // Getters and setters
    public void setTask(String taskArg){
        task = taskArg;
    }

    public String getTask(){
        return task;
    }

    public void setCompleted(String taskCompleted){
        completed = false;
    }

    public boolean getCompleted(){
        return completed;
    }

    public void setTitle(String titleArg){
        title = titleArg;
    }

    public String getTitle(){
        return title;
    }
}
