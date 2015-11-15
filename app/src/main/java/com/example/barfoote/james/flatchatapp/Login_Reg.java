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
    }

    public void register(){//Register user
        String username = emailField.getText().toString();
        String password = passwordField.getText().toString();
        new SQLConnect(this,status,role, 0).execute(username,password);
    }

    public void setResult(String res, boolean finished)
    {
        this.status = res;
        this.finished = finished;
Log.d("Result", res);
        if (res.equals("  Login Successful")) {
            //Login successful
            Toast.makeText(getApplicationContext(), "Login Successful",
                    Toast.LENGTH_LONG).show();
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

}
