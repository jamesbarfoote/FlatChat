package com.example.barfoote.james.flatchat;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Activity {
    private Handler handler = new Handler();
    public ListView msgView;
    public ArrayAdapter<String> msgList;
//
//    public MainActivityFragment() {
//    }



    //@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendB = (Button)findViewById(R.id.btn_Send);
        msgView = (ListView) findViewById(R.id.listView);

        msgList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        msgView.setAdapter(msgList);
        sendB.setOnClickListener(sendListener);
        Log.v("", "Create");

        //return inflater.inflate(R.layout.fragment_main, container, false);
    }

    private OnClickListener sendListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.v("Click", "Clicked");
            final EditText txtEdit = (EditText) findViewById(R.id.txt_inputText);
            msgList.add(txtEdit.getText().toString());

        }
    };
}
