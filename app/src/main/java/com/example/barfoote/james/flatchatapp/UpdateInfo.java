package com.example.barfoote.james.flatchatapp;

import android.content.Context;
import android.os.AsyncTask;

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
//        if(whatToDo == 1)//pull down the latest version of the internet db
//        {
//
//        }
//        else if(whatToDo == 2) //Push up the updated db
//        {
//
//        }




        return "";
    }

    @Override
    protected void onPostExecute(String result){

    }

//    public boolean putGroup()
//    {
//        getLocalDB();
//        return true;
//    }
//
//    public boolean getGroup()
//    {
//        return true;
//    }

    public boolean processInternetDB(String data)
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
}
