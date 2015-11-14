package com.example.barfoote.james.flatchatapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    public ListView msgView;
    public ArrayAdapter<String> msgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent loginAct = new Intent(this, Login_Reg.class);
        startActivity(loginAct);
        Log.v("", "Created");
        Button sendB = (Button)findViewById(R.id.btn_Send);

        msgView = (ListView) findViewById(R.id.listView);

        msgList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        msgView.setAdapter(msgList);
        msgView.smoothScrollToPosition(msgList.getCount() - 1);

        getMsg();//Get any messages from the server and print it
//
        sendB.setOnClickListener(sendListener);
    }

    private View.OnClickListener sendListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.v("Click", "Clicked");
            final EditText txtEdit = (EditText) findViewById(R.id.txt_inputText);
            if(!txtEdit.getText().toString().equals("") || !txtEdit.getText().toString().equals(" ")) {
                String msg = txtEdit.getText().toString();
                // msgList.add(txtEdit.getText().toString());
                Log.v("", "Clicked");
                Log.v("", msg);

                msgList.add(msg);
                sendMsg(msg);
                txtEdit.setText(null);
            }

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        if (id == R.id.action_calendar) {
            startActivity(new Intent(this, CalendarActivity.class));
            return true;
        }

        if (id == R.id.action_notes) {
            startActivity(new Intent(this, NotesActivity.class));
            return true;
        }

        if (id == R.id.action_login) {
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMsg(String msg)
    {
        final String message = msg;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String host = "192.168.20.112";
                int port = 6677;

                PrintWriter out;
                try
                {
                    Socket s = new Socket(host, port);//Create socket
                    out = new PrintWriter(s.getOutputStream());
                    out.println(message);//Send message
                    out.flush(); //Close the print writer

                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getMsg()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run() {
                final String host = "192.168.20.112";
                int port = 6677;
                Socket s = null;//Initilise
                BufferedReader input = null;
Log.v("", "Started getmsg");
                try//Try to create the socket
                {
                    Log.v("", "Trying to create socket");
                    s = new Socket(host, port);
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                    return;
                }

                try //Try to get the input from the stream
                {
                    Log.v("", "Trying to get the input stream");
                    if(s.getInputStream() != null && s!= null){
                        Log.v("", "Getting input stream");
                    input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    }//Get the input stream from the socket
                    else{
                        return;
                    }
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }

                while(true)//While the program is running
                {
                    String msg = null;
                    try
                    {
                        msg = input.readLine();
                        Log.v(msg, "Message");
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                    if(msg == null)//Didnt recieve anything so break out
                    {
                        break;
                    }
                    else
                    {
                        displayMsg(msg);
                    }
                }

            }
        }).start();



    }

    public void displayMsg(String message)
    {
        final String msg = message;
        handler.post(new Runnable() {
            @Override
            public void run() {
                msgList.add(msg);
                msgView.setAdapter(msgList);
                msgView.smoothScrollToPosition(msgList.getCount() - 1);
                Log.v(msg, "Printed message!");

            }
        });
    }
}
