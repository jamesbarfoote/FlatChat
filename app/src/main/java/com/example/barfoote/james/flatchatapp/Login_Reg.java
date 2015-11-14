package com.example.barfoote.james.flatchatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login_Reg extends AppCompatActivity {
    private EditText emailField,passwordField;
    private String status = "";
    private String role = "";
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
        while(finished != true) {
            if (this.status.equals("Login Successful")) {
                //Login successful
                Log.d("Login result", "" + this.status);
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
            }
        }

    }

    public void register(){//Register user
        String username = emailField.getText().toString();
        String password = passwordField.getText().toString();
        new SQLConnect(this,status,role, 0).execute(username,password);
    }

    public void setResult(String res, boolean finished)
    {
        Log.d("Result", res);
        this.status = res;
        this.finished = finished;
    }

}
