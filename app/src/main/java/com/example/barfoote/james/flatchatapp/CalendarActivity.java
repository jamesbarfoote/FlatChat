package com.example.barfoote.james.flatchatapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;
import android.app.Activity;


/**
 * Created by Jam on 8/06/2015.
 */
public class CalendarActivity extends Activity {
    CalendarView calendar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initializeCalendar();

        Log.v("", "Created");
    }

    public void initializeCalendar() {
        calendar = (CalendarView) findViewById(R.id.calendar);

    }



    }
