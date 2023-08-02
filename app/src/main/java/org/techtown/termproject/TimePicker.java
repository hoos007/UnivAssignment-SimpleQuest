package org.techtown.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimePicker extends AppCompatActivity implements android.widget.TimePicker.OnTimeChangedListener {
    private int H = 0;
    private int M = 0;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        android.widget.TimePicker timePicker = findViewById(R.id.vTimePicker);

        Calendar calendar = new GregorianCalendar();
        H = calendar.get(Calendar.HOUR_OF_DAY);
        M = calendar.get(Calendar.MINUTE);

        button = findViewById(R.id.vTimeEnter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("H",H);
                intent.putExtra("M",M);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        timePicker.setOnTimeChangedListener(this);
    }

    @Override
    public void onTimeChanged(android.widget.TimePicker timePicker, int i, int i1) {
        H = i;
        M = i1;
    }
}