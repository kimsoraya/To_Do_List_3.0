package com.example.kimschuiten.todolist30;

import java.util.ArrayList;

/**
 * Creates a To Do List with several To Do Items
 */

public class ToDoList {

    // Fields: properties
    private ArrayList<ToDoItem> taskList;
    private String listTitle;

    // Constructor: defines how instance is created
    public ToDoList(String titleArg){
        taskList = new ArrayList<ToDoItem>();
        listTitle = titleArg;
    }

    // Methods
    // Getters and Setters
    public void setTaskList (ArrayList listArg){
        taskList = listArg;
    }

    public ArrayList getTaskList(){
        return taskList;
    }

    public void setListTitleTitle (String titleArg){
        listTitle = titleArg;
    }

    public String getListTitle(){
        return listTitle;
    }
}
