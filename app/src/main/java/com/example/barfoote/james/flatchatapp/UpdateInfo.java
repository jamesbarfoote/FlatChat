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
import java.util.Arrays;

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

    private String group_name;
    private String shoppingList;
    private String calendar;
    private String money;
    private String todoList;
    private String ownerID;
    private int group_id;
    private String result;

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
            this.group_name = (String)arg0[0];

            //First pull down the latest version from the net
            String r = pullDownDB();
            this.result = r;
            return r;

            //process that data
            //Add that data to the local db fields
           // processInternetDB(this.result);

        }
        else if(whatToDo == 2)//call came from NotesActivity and we only want to push the data
        {
            //Get the local db
            //getLocalDB();
            this.groupData = this.newData;

            //Push the local db to the internet
            this.result = pushDB();

            return this.result;
        }
        return "";
    }

    @Override
    protected void onPostExecute(String res){
        NotesActivity n = (NotesActivity)mContext;
        Boolean success = false;

        if(whatToDo == 1)
        {
            DBHelper dbhelper = new DBHelper(mContext);
            success = processInternetDB(res, dbhelper);
        }

        n.onPost(res + " " + success);


    }
    

    public boolean processInternetDB(String data, DBHelper dbHelper) //Pull the string appart and put the values into their corresponding db column
    {
        //Split the data and put it into an array
        ArrayList<String> seperatedD = new ArrayList<>();
        seperatedD.addAll(Arrays.asList(data.split(",")));

        if (seperatedD.get(0).equals("successGG")) {
            parseLogin(seperatedD);
            //dbHelper.updateNotes(this.todoList);
          //  compareLists();
            dbHelper.updateGroup(this.group_id, this.group_name, this.shoppingList, this.calendar, this.money, this.todoList, this.ownerID);


            ArrayList<String> stuff = dbHelper.getGroupData();
            for(String n: stuff)
            {
                Log.v("Life", "" + n);
            }

            return true;
        }

        return false;
    }

//    private boolean getLocalDB()
//    {
//        if(dbHelper.getGroupData() != null)
//        {
//            this.groupData = dbHelper.getGroupData();
//            return true;
//        }
//        return false;
//    }

    private String pullDownDB()//get group from internet
    {
        try{
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new FormEncodingBuilder()
                    .add("GROUP_NAME", this.group_name)
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
            Log.d("Line", "Line " + line);
                sb.append(line);
                break;
            }
            in.close();
            Log.d("Result of get group1", sb.toString());
            response.body().close();

            return sb.toString();
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    private String pushDB()//update internet with local data
    {
        try{
            Log.d("In update group", "updating internet group");
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new FormEncodingBuilder()
                    .add("GROUP_NAME", this.groupData.get(2))
                    .add("SHOPPING_LIST", this.groupData.get(3))
                    .add("CALENDAR", this.groupData.get(4))
                    .add("MONEY", this.groupData.get(5))
                    .add("TODO", this.groupData.get(6))
                    .add("OWNER_ID", this.groupData.get(7))
                    .build();
            Request request = new Request.Builder()
                    .url("http://jimmyapps.16mb.com/groupUpdate.php")
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
            Log.d("Result updating group", sb.toString());
            response.body().close();

            return sb.toString();
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    public void compareLists()
    {
        if(this.todoList.equals(this.newData.get(6)))//Internet list and local list are the same
        {

        }
    }

    public void parseLogin(ArrayList<String> infoList)
    {
        //need to parse Group_ID, Group_name, shoppinglist, calendar, money, todoList, owner_id
        this.group_id = Integer.parseInt(infoList.get(1));
        Log.d("Group id","" + group_id);
        this.shoppingList = infoList.get(2).replaceAll("\\s", "");
        Log.d("shopping list","" + shoppingList);
        this.calendar = infoList.get(3).replaceAll("\\s","");
        Log.d("calendar","" + calendar);
        this.money = infoList.get(6).replaceAll("\\s","");
        Log.d("money", "" + money);
        this.todoList = infoList.get(4).replaceAll("\\s","");
        Log.d("todo list","" + todoList);
        this.ownerID = infoList.get(5).replaceAll("\\s", "");
        Log.d("owner id","" + ownerID);

        //add this data to the local sql database


    }
}
