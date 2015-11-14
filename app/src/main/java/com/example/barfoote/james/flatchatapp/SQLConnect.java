package com.example.barfoote.james.flatchatapp; /**
 * Created by James on 11/14/2015.
 */

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SQLConnect  extends AsyncTask<String,Void,String> {
    private String statusField,roleField;
    private Login_Reg context;
    private int byGetOrPost = 0;

    //flag 0 means get and 1 means post.(By default it is get.)
    public SQLConnect(Login_Reg context,String statusField,String roleField,int flag) {
        this.context = context;
        this.statusField = statusField;
        this.roleField = roleField;
        byGetOrPost = flag;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0) {
        if(byGetOrPost == 0){ //means by Post Method

            try{
                String username = (String)arg0[0];
                String password = (String)arg0[1];
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormEncodingBuilder()
                        .add("email", username)
                        .add("password", password)
                        .build();
                Request request = new Request.Builder()
                        .url("http://jamesbarfoote.16mb.com/insert-db.php")
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
                return sb.toString();
            }

            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
        else{//Get
            try{
                String username = (String)arg0[0];
                String password = (String)arg0[1];
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormEncodingBuilder()
                        .add("email", username)
                        .add("password", password)
                        .build();
                Request request = new Request.Builder()
                        .url("http://jamesbarfoote.16mb.com/getData.php")
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
                Log.d("Result of login ", sb.toString());
                return sb.toString();
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }

    @Override
    protected void onPostExecute(String result){
        context.setResult("Login Successful", true);
        this.statusField = "Login Successful";
        this.roleField = result;
    }

//    public String get()
//    {
//        return statusField;
//    }
}