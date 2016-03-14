package com.example.kimschuiten.todolist30;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * In this class the user can create the items of a new to do list. The list is saved to the
 * internal storage.
 */

public class Lists extends Activity{

    Button addToArrayBtn;
    EditText addToDoEdText;
    ListView toDoListView;
    ArrayList<String> addArray = new ArrayList<String>();
    Button BtnSave;

    String getInput;
    String getTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lists_layout);

        // Initialize EditText, Button and ListView
        addToDoEdText = (EditText) findViewById(R.id.listAddEditText);
        addToArrayBtn = (Button) findViewById(R.id.addButton);
        toDoListView = (ListView) findViewById(R.id.ListView2);
        BtnSave = (Button) findViewById(R.id.saveListButton);


        // Set Button OnClickListener to add items to the list
        addToArrayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get text from EditTexts
                getInput = addToDoEdText.getText().toString();

                // Call To Do item
                ToDoItem newTask = new ToDoItem(getInput, getTitle);

               // If getInput exists, don't add
                if (addArray.contains(getInput)) {
                    Toast.makeText(getBaseContext(), "You already have this on your TODO list",
                            Toast.LENGTH_LONG).show();
                }
                // If getInput is empty, don't add but prompt for input
                else if (getInput == null || getInput.trim().equals("")) {
                    Toast.makeText(getBaseContext(), "You can't add an empty task", Toast.LENGTH_LONG).show();
                }

                // Add getInput to array
                else {
                    addArray.add(getInput);

                    // Create Array adapter
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Lists.this,
                            android.R.layout.simple_list_item_1, addArray);
                    // Set Array adapter
                    toDoListView.setAdapter(adapter);
                    ((EditText) findViewById(R.id.listAddEditText)).setText(" ");

                    // Update the list
                    adapter.notifyDataSetChanged();
                }

                // Save items to list
                ToDoManagerSingleton tmp = ToDoManagerSingleton.getInstance();
                Context context = getApplicationContext();
                tmp.WriteListToFile(context);
            }
        });

        // Set long click to remove tasks when done
        toDoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Delete rows when on clicked
                addArray.remove(position);

                // Create Array adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Lists.this,
                        android.R.layout.simple_list_item_1, addArray);

                // Set Array adapter
                toDoListView.setAdapter(adapter);

                // We notify the data model is changed
                adapter.notifyDataSetChanged();

                // Confirm completion
                String taskCompleted = "You completed this task";
                Toast.makeText(Lists.this, taskCompleted, Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    /**
     *  When button is clicked, go back to MainActivity
    **/
    public void saveListButtonClick(View view) {

        // Intent back to MainScreen
        Intent getNewListIntent = new Intent(this, MainActivity.class);
        final int result = 1;
        startActivity(getNewListIntent);
    }
}