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
    int ownerID;
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
        for(String s: infoList)
        {
            Log.v("infolist", s);

        }
        //Log.d("First item", "a" + this.infoList.get(0) + "b");
        this.status = res;
        Log.d("Res", "a"+infoList.get(0)+"b");
        if (infoList.get(0).equals("  successLG")) {
            //Login successful
            Log.d("name is", this.groupName);

            Toast.makeText(getApplicationContext(), "Login Successful ",
                    Toast.LENGTH_LONG).show();

            Toast.makeText(getApplicationContext(), byGetOrPost + "is the res",
                    Toast.LENGTH_LONG).show();

            //Parse res to get all the user information (id, email, group, pic)
            //Add user to user database
            dbHelper = new DBHelper(this);
            if(byGetOrPost == 0)//register
            {
                this.groupName = this.nameField.getText().toString();
                dbHelper.insertGroup(0, this.groupName, "", "", "", "", this.ownerID);

                //Also add to the online version of the database

            }
            else if(byGetOrPost == 1)//login
            {
                Log.v("login", res);

                parseLogin(res);
                Log.v("gn", this.groupName);
                dbHelper.insertGroup(this.group_id, this.nameField.getText().toString(), this.shoppingList, this.calendar, this.money, this.todoList, this.ownerID);
                //Add group to user
                Toast.makeText(getApplicationContext(), dbHelper.getGroup(),
                        Toast.LENGTH_LONG).show();
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
        this.shoppingList = this.infoList.get(2).replaceAll("\\s", "");
        this.calendar = this.infoList.get(3).replaceAll("\\s","");
        this.money = this.infoList.get(4).replaceAll("\\s","");
        this.todoList = this.infoList.get(5).replaceAll("\\s","");
        this.ownerID = Integer.parseInt(this.infoList.get(6));

    }



}
