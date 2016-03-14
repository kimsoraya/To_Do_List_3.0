package com.example.kimschuiten.todolist30;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * MainActivity where user can add new lists (titles). Click on list to add items to is.
 */

public class MainActivity extends AppCompatActivity {

    ListView showSavedFilesListview;
    EditText newListEdText;

    String listTitle;
    ArrayList<String> taskList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize
        showSavedFilesListview = (ListView) findViewById(R.id.ListView1);
        newListEdText = (EditText) findViewById(R.id.newListEdText);
        listTitle = newListEdText.getText().toString();

        // Create Array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, taskList);
        // Set Array adapter
        showSavedFilesListview.setAdapter(adapter);
        ((EditText) findViewById(R.id.newListEdText)).setText(" ");

        // Call To Do title
        ToDoList newList = new ToDoList(listTitle);

        // Read titles from titlesfile.txt
        ToDoManagerSingleton tmp = ToDoManagerSingleton.getInstance();
        Context context = getApplicationContext();
        tmp.ReadTitlesFromFile(context);

        // When user clicks on one of the titles, go to the list
        showSavedFilesListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showListIntent = new Intent(MainActivity.this, Lists.class);
                startActivity(showListIntent);
            }
        });
    }

    // onClickListener for the Create List button
    public void createNewList(View view) {
        // Get text from EditTexts
        listTitle = newListEdText.getText().toString();

        // Call To Do item
        ToDoList newList = new ToDoList(listTitle);

        // Show a toast when the title EditText is empty
        if (listTitle == null || listTitle.trim().equals("")) {
            Toast.makeText(getBaseContext(), "You can't add an empty title", Toast.LENGTH_LONG).show();
        } else {
            taskList.add(listTitle);
            // Create Array adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1, taskList);
            // Set Array adapter
            showSavedFilesListview.setAdapter(adapter);
            ((EditText) findViewById(R.id.newListEdText)).setText(" ");

            // Update the list
            adapter.notifyDataSetChanged();

            // Save title to list
            ToDoManagerSingleton tmp = ToDoManagerSingleton.getInstance();
            Context context = getApplicationContext();
            tmp.WriteListTitlesToFile(context);

            Toast.makeText(getBaseContext(), "List saved succesfully", Toast.LENGTH_LONG).show();
        }
    }
 }

