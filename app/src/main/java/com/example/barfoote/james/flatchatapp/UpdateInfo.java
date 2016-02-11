package com.example.barfoote.james.flatchatapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by james on 2/11/2016.
 */
public class UpdateInfo extends AsyncTask<String,Void,String> {
    private String FLATGROUP_TABLE_NAME;
    private String FLATGROUP_COLUMN_ID;
    private String FLATGROUP_COLUMN_GROUP_ID;
    private String FLATGROUP_COLUMN_GROUP_NAME;
    private String FLATGROUP_COLUMN_SHOPPINGLIST;
    private String FLATGROUP_COLUMN_CALENDAR;
    private String FLATGROUP_COLUMN_MONEY;
    private String FLATGROUP_COLUMN_TODO_LIST;
    private String FLATGROUP_COLUMN_OWNER_ID;

    private final Context mContext;
    private ArrayList<String> groupData = new ArrayList<>();
    private int whatToDo;
    private ArrayList<String> newData = new ArrayList<>();
    private String username;

    DBHelper dbHelper= new DBHelper(getmContext());


    //newData is the data the user wants to add to the internet db
    //method is the method that the update request came from
    public UpdateInfo(Context c, ArrayList<String> newData, int method)
    {
        mContext = c;
        this.whatToDo = method;
        this.newData = newData;
    }

    public Context getmContext() {
        return mContext;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0) {
        if(whatToDo == 1)//call came from NotesActivity and we only want to get the data
        {
            //First pull down the latest version from the net
            pullDownDB();

            //process that data
            //Add that data to the local db fields
            processInternetDB();

        }
        else if(whatToDo == 2)//call came from NotesActivity and we only want to push the data
        {
            //Get the local db
            getLocalDB();

            //Push the local db to the internet
            pushDB();
        }




        return "";
    }

    @Override
    protected void onPostExecute(String result){

    }
    

    public boolean processInternetDB(String data) //Pull the string appart and put the values into their corresponding db column
    {

        return false;
    }

    private boolean getLocalDB()
    {
        if(dbHelper.getGroupData() != null)
        {
            this.groupData = dbHelper.getGroupData();
            return true;
        }
        return false;
    }

    private String pullDownDB()//get group from internet
    {
        try{
            Log.d("In get group", "");
            this.username = dbHelper.getGroup();
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new FormEncodingBuilder()
                    .add("GROUP_NAME", username)
                    .build();
            Request request = new Request.Builder()
                    .url("http://jimmyapps.16mb.com/getGroupData.php")
                    .post(formBody)
                    .build();

            Response response = client.newCall(request).execute();
            BufferedReader in = new BufferedReader(new InputStreamReader(response.body().byteStream()));

            StringBuffer sb = new StringBuffer("");
            String line="";

            while ((line = in.readLine()) != null) {

                sb.append(line);
                break;
            }
            in.close();
            Log.d("Result of getting group", sb.toString());
            response.body().close();
            return sb.toString();
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    private boolean pushDB()//update internet with local data
    {

    }
}
