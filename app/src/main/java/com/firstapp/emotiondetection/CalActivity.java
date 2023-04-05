package com.firstapp.emotiondetection;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
public class CalActivity extends AppCompatActivity {

    private TextView mDateTextView, mTimeTextView;
    private Button mDateButton, mTimeButton;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        mDateTextView = findViewById(R.id.date_text_view);
        mTimeTextView = findViewById(R.id.time_text_view);
        mDateButton = findViewById(R.id.date_button);
        mTimeButton = findViewById(R.id.time_button);

        // Set a click listener for the date button
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the current date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Create a new date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(CalActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mYear = year;
                                mMonth = monthOfYear;
                                mDay = dayOfMonth;
                                // Update the text view with the selected date
                                mDateTextView.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                            }
                        }, mYear, mMonth, mDay);

                // Show the date picker dialog
                datePickerDialog.show();
            }
        });

        // Set a click listener for the time button
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the current time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Create a new time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(CalActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                mHour = hourOfDay;
                                mMinute = minute;
                                // Update the text view with the selected time
                                mTimeTextView.setText(String.format("%02d:%02d", mHour, mMinute));
                            }
                        }, mHour, mMinute, DateFormat.is24HourFormat(CalActivity.this));

                // Show the time picker dialog
                timePickerDialog.show();
            }
        });

        // Set a click listener for the alert button
        Button alertButton = findViewById(R.id.alert_button);
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new alert dialog builder
                AlertDialog.Builder builder = new AlertDialog.Builder(CalActivity.this);
                builder.setTitle("Happy Birthday!");
                builder.setMessage("Date: " + mDateTextView.getText() + "\nTime: " + mTimeTextView.getText());
                builder.setPositiveButton("OK", null);
                // Show the alert dialog
                builder.create().show();
            }
        });
    }
}