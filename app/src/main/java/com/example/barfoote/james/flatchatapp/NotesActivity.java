package com.example.barfoote.james.flatchatapp;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jam on 8/06/2015.
 */
public class NotesActivity extends ListActivity {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();

    EditText editText;

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int clickCounter=0;

    DBHelper dbHelper= new DBHelper(this);
    UpdateInfo updater;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        editText = (EditText) findViewById(R.id.notesEditText);

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    addItems(v);
                    handled = true;
                }
                return handled;
            }
        });

      //  displayNotesLocal();
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
        String text = editText.getText().toString();
        editText.setText("");
        listItems.add(text);
        adapter.notifyDataSetChanged();
        //call sql and add into the notes column of the group

//       ArrayList<String> stuff = dbHelper.getGroupData();
//        for(String n: stuff)
//        {
//            Log.v("Life", "" + n);
//        }

        //get latest version from the internet
        updater = new UpdateInfo(this, null, 1);
        displayNotesLocal();

        //update local db
        String sList = listToString(listItems);
        dbHelper.updateNotes(sList);
        Log.v("Updated list", "notes " + sList);

        //update internet table
        updater = new UpdateInfo(this, null, 2);


    }

    public void displayNotesLocal()
    {
        String list = dbHelper.getNotes();
        Log.v("List", "List " + list);

        ArrayList<String> notesList = parseNotes(list);
        for(String n: notesList) {
            listItems.add(n);
        }
    }

    public String listToString(ArrayList<String> items)
    {
        String allItems = "";
        for(String n: items)
        {
            allItems = allItems + n  + "~";
        }

        return allItems;
    }

    public ArrayList<String> parseNotes(String notes)
    {
        ArrayList<String> parsed = new ArrayList<>();
        String[] splitting = notes.split("~");
        for(String n: splitting)
        {
            parsed.add(n);
        }

        return parsed;
    }
}
