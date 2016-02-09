package com.example.barfoote.james.flatchatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class GroupLogin extends AppCompatActivity {
    private EditText nameField,passwordField;
    private String status = "";
    private String role = "";
    DBHelper dbHelper;
    //Group_ID, Group_name, shoppinglist, calendar, money, todoList, owner_id
    int group_id;
    String groupName = "";
    String shoppingList = "";
    String calendar = "";
    String money = "";
    String todoList = "";
    String ownerID;
    boolean loginSuccess = false;
    private ArrayList<String> infoList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameField = (EditText)findViewById(R.id.groupNameInput);
        passwordField = (EditText)findViewById(R.id.groupPassInput);

        Button loginB = (Button)findViewById(R.id.groupLoginButton);
        Button registerB = (Button)findViewById(R.id.groupRegisterbutton);
        loginB.setOnClickListener(loginListener);
        registerB.setOnClickListener(registerListener);

    }

    private View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.v("Login", "Clicked");
            login();
        }
    };

    private View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.v("Register", "Clicked");
            register();
        }
    };

    public void login(){//Get group
        String name = nameField.getText().toString();
        String password = passwordField.getText().toString();
        new SQLConnect(this,status,role,2).execute(name,password);

        if(loginSuccess)
        {
            new SQLConnect(this,status,role, 4).execute(dbHelper.getEmail(), this.groupName);
        }
    }

    public void register(){//Register group
        String name = nameField.getText().toString();
        String password = passwordField.getText().toString();
        new SQLConnect(this,status,role, 3).execute(name, password);
    }

    public void setResult(String res,String info, int byGetOrPost)
    {
        //byGetOrPost = 0 means register
        this.infoList.addAll(Arrays.asList(res.split(",")));
        //Log.d("First item", "a" + this.infoList.get(0) + "b");
        this.status = res;

        if (infoList.get(0).equals("  successLG")) {
            //Login successful

            Toast.makeText(getApplicationContext(), "Login Successful ",
                    Toast.LENGTH_LONG).show();

            //Parse res to get all the user information (id, email, group, pic)
            //Add user to user database
            dbHelper = new DBHelper(this);
            if(byGetOrPost == 3)//register
            {
                this.groupName = this.nameField.getText().toString();
                dbHelper.insertGroup(0, this.groupName, "", "", "", "", this.ownerID);

                //Also add to the online version of the database

            }
            else if(byGetOrPost == 2)//login
            {
                String groupNameIs = this.nameField.getText().toString();
                this.groupName = this.nameField.getText().toString();

                parseLogin(res);
                dbHelper.insertGroup(this.group_id, groupNameIs, this.shoppingList, this.calendar, this.money, this.todoList, this.ownerID);

                //Add group to user
                loginSuccess = true;
                addGroupToUser();
                //new SQLConnect(this,status,role, 4).execute(dbHelper.getEmail(), groupNameIs);

//                Cursor cur = dbHelper.getAllGroup();
//                ArrayList temp = new ArrayList();
//                if (cur != null) {
//                    if (cur.moveToFirst()) {
//                        do {
//                            temp.add(cur.getString(cur.getColumnIndex("groupName"))); // "Title" is the field name(column) of the Table
//                        } while (cur.moveToNext());
//                    }
//                }
//                Log.v("Group name is ",temp.get(0).toString());
            }
            else if(byGetOrPost == 4)
            {
                Toast.makeText(getApplicationContext(), "updated internet table ",
                        Toast.LENGTH_LONG).show();

                Log.v("Res is for 4", res);
            }

            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
        }
        else if(res.equals("success"))
        {
            //Register successful
            Toast.makeText(getApplicationContext(), "Registration successful",
                    Toast.LENGTH_LONG).show();
            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Username/password incorrect OR user already exists",
                    Toast.LENGTH_LONG).show();
        }
    }


    public void parseLogin(String res)
    {
        Log.v("parselogin", res);
        //need to parse Group_ID, Group_name, shoppinglist, calendar, money, todoList, owner_id
        this.group_id = Integer.parseInt(this.infoList.get(1));
        Log.d("Group id","" + group_id);
        this.groupName = this.nameField.getText().toString().replaceAll("\\s", "");
        Log.d("Group name","" + groupName);
        this.shoppingList = this.infoList.get(2).replaceAll("\\s", "");
        Log.d("shopping list","" + shoppingList);
        this.calendar = this.infoList.get(3).replaceAll("\\s","");
        Log.d("calendar","" + calendar);
        this.money = this.infoList.get(6).replaceAll("\\s","");
        Log.d("money", "" + money);
        this.todoList = this.infoList.get(4).replaceAll("\\s","");
        Log.d("todo list","" + todoList);
        this.ownerID = this.infoList.get(5).replaceAll("\\s", "");
        Log.d("owner id","" + ownerID);

        //add this data to the local sql database
        if(dbHelper.groupExists(groupName))
        {
            Log.v("SQLite", "updating group");
            //Update with new info
        }
        else //Insert new group
        {
            Log.v("SQLite", "Inserting new group");
            dbHelper.insertGroup(this.group_id, this.groupName, this.shoppingList, this.calendar, this.money,this.todoList, this.ownerID);
        }

    }

    public void addGroupToUser()
    {
        boolean success = false;
        if(loginSuccess)
        {
           // Log.v("login success", "" + loginSuccess);
            Log.v("The email", ""+dbHelper.getEmail());
            Log.v("The group", ""+this.groupName);
            new SQLConnect(this,status,role, 4).execute(this.groupName, dbHelper.getEmail());
            success = true;
        }
    }



}
