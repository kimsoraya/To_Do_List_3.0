package com.example.kimschuiten.todolist30;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Singleton for the ToDoItem and ToDoList classes
 */
public class ToDoManagerSingleton {

    // Fields: properties
    private ArrayList<ToDoList> listTitles;
    private ArrayList<ToDoItem> taskList;

    // One single instance for the entire app
    private static ToDoManagerSingleton ourInstance = new ToDoManagerSingleton();

    // Constructor
    private ToDoManagerSingleton() {
        // Empty ArrayList with in it the different lists
        listTitles = new ArrayList<ToDoList>();
        taskList = new ArrayList<ToDoItem>();
    }

    // Method to get the one single instance
    public static ToDoManagerSingleton getInstance() {
        return ourInstance;
    }

    // Getter and setter
    public void setTaskList (ArrayList listArg){
        taskList = listArg;
    }

    public ArrayList getTaskList(){
        return taskList;
    }

    public void setListTitles (ArrayList listTitlesArg){
        listTitles = listTitlesArg;
    }

    public ArrayList getListTitles(){
        return listTitles;
    }

    // Add new tasks to list
    public void AddToDoItem(ToDoItem task){
        taskList.add(task);
    }

    // Add new title to list
    public void AddNewTitle(ToDoList title){
        listTitles.add(title);
    }

    // Write list titles to a file
    public void WriteListTitlesToFile (Context context){
        PrintStream outstream = null;
        try{
            outstream = new PrintStream(context.openFileOutput("titlesfile.txt", Context.MODE_PRIVATE));
            for (ToDoList listTitle : listTitles) {
                outstream.println(listTitle);
            }
            outstream.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    // Write new tasks to internal storage
    public void WriteListToFile(Context context){
        PrintStream outStream = null;
        try {
            outStream = new PrintStream(context.openFileOutput("itemsfile.txt", Context.MODE_PRIVATE));
            for (ToDoItem task : taskList) {
                outStream.println(task);
            }
            outStream.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    // Read titlesfile.txt
    public void ReadTitlesFromFile(Context context){
        try {
            FileInputStream inStream = context.openFileInput("titlesfile.txt");
            InputStreamReader inReader = new InputStreamReader(inStream);
            BufferedReader bufferedReader = new BufferedReader(inReader);
            StringBuilder sBuilder = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                sBuilder.append(line).append("\n");
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    // Read itemsfile.txt
    public void ReadItemsFromFile(Context context){
        try {
            FileInputStream inStream = context.openFileInput("itemsfile.txt");
            InputStreamReader inReader = new InputStreamReader(inStream);
            BufferedReader bufferedReader = new BufferedReader(inReader);
            StringBuilder sBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                sBuilder.append(line).append("\n");
            }
            inStream.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
