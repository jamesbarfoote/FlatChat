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

public class Login_Reg extends AppCompatActivity {
    private EditText emailField,passwordField;
    private String status = "";
    private String role = "";
    DBHelper dbHelper;
    private String email = "";
    private int userID;
    private String pic;
    private String group;
    private ArrayList<String> infoList = new ArrayList<String>();


    private boolean finished = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__reg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emailField = (EditText)findViewById(R.id.emailField);
        passwordField = (EditText)findViewById(R.id.passwordField);

        Button loginB = (Button)findViewById(R.id.loginButton);
        Button registerB = (Button)findViewById(R.id.registerButton);
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

    public void login(){//Get user
        String username = emailField.getText().toString();
        String password = passwordField.getText().toString();
        new SQLConnect(this,status,role,1).execute(username,password);
    }

    public void register(){//Register user
        String username = emailField.getText().toString();
        String password = passwordField.getText().toString();
        new SQLConnect(this,status,role, 0).execute(username, password);
    }

    public void setResult(String res,String info, int byGetOrPost)
    {
        //byGetOrPost = 0 means register

        this.infoList.addAll(Arrays.asList(res.split(",")));

        this.status = res;
        Log.d("Res", res);
        if (infoList.get(0).equals("  successL")) {
            //Login successful
            Toast.makeText(getApplicationContext(), "Login Successful",
                    Toast.LENGTH_LONG).show();

            //Parse res to get all the user information (id, email, group, pic)
            //Add user to user database
            dbHelper = new DBHelper(this);
            if(byGetOrPost == 0)//register
            {
                this.email = info;
                dbHelper.insertUser(0, email, null, "");
            }
            else if(byGetOrPost == 1)//login
            {
                parseLogin(res);
                dbHelper.insertUser(this.userID, this.email, this.pic, this.group);
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
        //need to parse ID, email, group, pic
        this.userID = Integer.parseInt(this.infoList.get(1));
        this.email = this.infoList.get(2).replaceAll("\\s", "");
        this.pic = this.infoList.get(3).replaceAll("\\s", "");
        this.group = this.infoList.get(4).replaceAll("\\s","");

    }


}
