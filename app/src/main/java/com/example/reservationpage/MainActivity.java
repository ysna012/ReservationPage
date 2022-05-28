package com.example.reservationpage;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    RadioGroup rg;
    FrameLayout frameLayout;
    DatePicker datePicker;
    TimePicker timePick;
    Chronometer chrono;
    TextView textResult;
    int selectedyear, selectedmonth, selectedday;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = findViewById(R.id.rg);
        datePicker = findViewById(R.id.datepicker);
        timePick = findViewById(R.id.time_pick);
        chrono = findViewById(R.id.chrono);
        textResult = findViewById(R.id.text_result);
        frameLayout = findViewById(R.id.framelayout);

        datePicker.setVisibility(View.INVISIBLE);

        rg.setOnCheckedChangeListener(rgListener);
        datePicker.setOnDateChangedListener(dateListener);
        chrono.setOnClickListener(chronoListener);
        textResult.setOnLongClickListener(textListener);
        rg.setVisibility(View.INVISIBLE);
        frameLayout.setVisibility(View.INVISIBLE);
    }

    View.OnLongClickListener textListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            chrono.setTextColor(Color.parseColor("#A583E1"));
            chrono.stop();
            textResult.setText(selectedyear + "년 " + selectedmonth + "월 " +selectedday + "일");
            textResult.append(timePick.getCurrentHour() + "시 " + timePick.getCurrentMinute() + "분 예약됨");
            rg.setVisibility(View.INVISIBLE);
            frameLayout.setVisibility(View.INVISIBLE);
            return true;
        }
    };

    View.OnClickListener chronoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            rg.setVisibility(View.VISIBLE);

            chrono.setTextColor(Color.parseColor("#ED6392"));
            chrono.setBase(SystemClock.elapsedRealtime());
            chrono.start();
        }
    };

    DatePicker.OnDateChangedListener dateListener = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
            selectedyear = year;
            selectedmonth = month + 1;
            selectedday = day;
        }
    };

    CalendarView.OnDateChangeListener calendarListener = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
            selectedyear = year;
            selectedmonth = month + 1;
            selectedday = day;
        }
    };

    RadioGroup.OnCheckedChangeListener rgListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {
            frameLayout.setVisibility(View.VISIBLE);
            datePicker.setVisibility(View.INVISIBLE);
            timePick.setVisibility(View.INVISIBLE);
            switch(id) {
                case R.id.radio_date:
                    datePicker.setVisibility(View.VISIBLE);
                    break;
                case R.id.radio_time:
                    timePick.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
}