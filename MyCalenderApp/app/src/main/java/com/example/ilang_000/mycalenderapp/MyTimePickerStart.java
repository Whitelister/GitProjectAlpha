package com.example.ilang_000.mycalenderapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MyTimePickerStart extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
private Context contextmain;
private textcallback mytextcallback;

private String startshift;

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        contextmain=getActivity().getBaseContext();
        mytextcallback=(textcallback)getContext();
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(getContext(), "time was set to: "+hourOfDay+minute, Toast.LENGTH_SHORT).show();
        Log.e("time picker=",""+view.toString());
        if(hourOfDay<10) {
            startshift = "0"+hourOfDay+":"+minute;
        }else if(hourOfDay<10&&minute<10){
            startshift = "0"+hourOfDay+":"+"0"+minute;
        }else if(minute<10){
            startshift = ""+hourOfDay+":"+"0"+minute;
        }else{
            startshift=""+hourOfDay+":"+minute;
        }
        mytextcallback.updatestartshift(startshift);
        mytextcallback.timestart(hourOfDay,minute);
    }
}
