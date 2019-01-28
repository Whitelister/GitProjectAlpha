package com.example.ilang_000.mycalenderapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaleryOrgenizer extends AppCompatActivity implements textcallback {
    Button Mainstartshift;
    Button Mainendshift;
    Button overall;
    int starthour;
    int endhour;
    EditText Salery;
    double ActualOvertime;
    int endminute;
    int startminute;
    TextView DecimalCal;
    TextView calculate;
    double actualminute;
    TextView calculateovertime;
    TextView dateofday;
    CalendarView cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salery_orgenizer);
        Mainstartshift = findViewById(R.id.start_shift);
        dateofday=findViewById(R.id.date);
        calculate = findViewById(R.id.calculate);
        Salery=findViewById(R.id.Salery);
        DecimalCal=findViewById(R.id.decimalCal);
        calculateovertime=findViewById(R.id.calculateovertime);
        Mainendshift = findViewById(R.id.end_shift);
        Intent intent=getIntent();
        String getdate=intent.getStringExtra("date");
        dateofday.setText(getdate);
        overall = findViewById(R.id.overall);
        overall.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                CalculateHours();
            }
        });
    }

    public void showTimePickerDialogstart(View v) {
        DialogFragment newFragment = new MyTimePickerStart();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void showTimePickerDialogend(View v) {
        DialogFragment newFragment = new MyTimePickerEnd();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    @Override
    public void updatestartshift(String myStart) {
        Mainstartshift.setText(myStart);

    }

    @Override
    public void updateendshift(String myEnd) {
        Mainendshift.setText(myEnd);
    }

    @Override
    public void timestart(int hourofstart, int minuteofstart) {
        starthour = hourofstart;
        startminute = minuteofstart;
    }

    @Override
    public void timeend(int hourofend, int minuteofend) {
        endhour = hourofend;
        endminute = minuteofend;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void CalculateHours() {
        SimpleDateFormat format = new SimpleDateFormat("H:mm");
        Date d1;
        Date d2;
        try {
            d1 = format.parse(String.valueOf(starthour + ":" + startminute));
            d2 = format.parse(String.valueOf(endhour + ":" + endminute));

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;
            double DiffMinutesDecimal = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            if (diffHours < 0) {
                diffHours = diffHours * (-1);
            } if (diffMinutes < 0) {
                diffMinutes = diffMinutes * (-1);
            }
            Log.e("DIFFHOURS", "" + diffHours);
            if (diffHours < 10) {
                calculate.setText("0" + diffHours + ":" + diffMinutes);
            } else if (diffMinutes < 10) {
                calculate.setText("" + diffHours + ":" + "0" + diffMinutes);
            } else if (diffHours < 10 && diffMinutes < 10) {
                calculate.setText("0" + diffHours + ":" + "0" + diffMinutes);
            } else if (diffHours == 0) {
                calculate.setText("0" + diffHours + "0" + ":" + diffMinutes);
            } else if (diffMinutes == 0) {
                calculate.setText("" + diffHours + ":" + "00" + diffMinutes);
            } else if (diffHours == 0 && diffMinutes == 0) {
                calculate.setText("0" + diffHours + "0" + ":" + "00" + diffMinutes);
            } else {
                calculate.setText("" + diffHours + ":" + diffMinutes);
            }
            actualminute=(DiffMinutesDecimal / 60);
            double decimal_overall=diffHours+actualminute;
            double Final_minute_decimal=(DiffMinutesDecimal / 60);
            Final_minute_decimal=Math.floor(Final_minute_decimal*1000) /1000;
            if(decimal_overall>8){
             ActualOvertime=decimal_overall-8;
            ActualOvertime = Math.floor(ActualOvertime * 1000) / 1000;

            }
            try {
                // Same
                String S = Salery.getText().toString(); // Same
                double salerydecimal = Double.parseDouble(S); // Make use of autoboxing.  It's also easier to read.
                double CalculateSaleryovertime=(salerydecimal*8)+(ActualOvertime*salerydecimal);
                double CalculateSaleryRegular=(salerydecimal*diffHours)+(Final_minute_decimal*salerydecimal);
                CalculateSaleryovertime=Math.floor(CalculateSaleryovertime*1000)/1000;
                calculateovertime.setText(Double.toString(ActualOvertime));

                if(diffHours>8) {
                    DecimalCal.setText(Double.toString(CalculateSaleryovertime));
                }else{
                    DecimalCal.setText(Double.toString(CalculateSaleryRegular));
                }
            } catch (NumberFormatException e) {
                // p did not contain a valid double
            }
            Log.e("CALCULATE:", "" + diffHours + ":" + diffMinutes);
        } catch (ParseException e) {

            e.printStackTrace();
        }
    }

//    @SuppressLint("ResourceType")
//    public void ShowCalendar(View v) {
//        Dialog dialog = new Dialog(getBaseContext());
//        dialog.setContentView(R.id.calendarViewSetting);
//        cal = (CalendarView) dialog.findViewById(R.id.calendarView);
//        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month,
//                                            int dayOfMonth) {
//                // TODO Auto-generated method stub
//
//                Toast.makeText(getBaseContext(), "Selected Date is\n\n"
//                                + dayOfMonth + " : " + month + " : " + year,
//                        Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }
}
