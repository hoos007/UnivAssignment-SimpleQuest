package org.techtown.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatePicker extends AppCompatActivity {
    private int mYear =0, mMonth=0, mDay=0;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        Calendar calendar = new GregorianCalendar();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        android.widget.DatePicker datePicker = findViewById(R.id.vDatePicker);
        button = findViewById(R.id.vDateEnter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("mYear",mYear);
                intent.putExtra("mMonth", mMonth + 1);
                intent.putExtra("mDay", mDay);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        datePicker.init(mYear, mMonth, mDay,mOnDateChangedListener);
    }

    android.widget.DatePicker.OnDateChangedListener mOnDateChangedListener = new android.widget.DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(android.widget.DatePicker datePicker, int yy, int mm, int dd) {
            mYear = yy;
            mMonth = mm;
            mDay = dd;
        }
    };

}