package com.example.jamesb.flattingplus;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class CalendarActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setContentView(R.layout.activity_calendar);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //MaterialCalendarView.getSelectedDates();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        CalendarActivity fragment = new CalendarActivity();
//        fragmentTransaction.add(R.id.fragment_container, fragment);
//        fragmentTransaction.commit();
    }

}

//public class Cal extends MainActivity {
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calendar); //layout for 2nd activity
//    }
//}
