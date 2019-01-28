package com.example.ilang_000.mycalenderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class Calendar_date_setting extends AppCompatActivity {
private CalendarView calendarView;
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_date_setting);
        calendarView=findViewById(R.id.calendar);
        textView=findViewById(R.id.textView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Toast.makeText(getApplicationContext(), ""+dayOfMonth, Toast.LENGTH_SHORT).show();// TODO Auto-generated method stub
                String date=dayOfMonth+"/"+(month+1)+"/"+year;
                Intent intent=new Intent(Calendar_date_setting.this,SaleryOrgenizer.class);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });

    }
}
