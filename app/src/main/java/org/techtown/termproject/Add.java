package org.techtown.termproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU1 = 101;
    public static final int REQUEST_CODE_MENU2 = 102;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    CheckBox checkBox;
    Button button;
    int mYear = 0;
    int mMonth = 0;
    int mDay = 0;

    int M = 0;
    int H = 0;

    int important = 0;

    String date = null;
    String time = null;

    int mod = 0;

    int todo_id;
    String todo_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ActionBar ac = getSupportActionBar();
        ac.setTitle("Add");

        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        checkBox = findViewById(R.id.checkBox2);
        button = findViewById(R.id.buttonAdd);

        Intent intent = getIntent();

        mod = intent.getIntExtra("mod", 0);

        if(mod == 1)
        {
            todo_id = intent.getIntExtra("todo_id",0);
            String todo_title = intent.getStringExtra("todo_title");
            String todo_content = intent.getStringExtra("todo_content");
            todo_date = intent.getStringExtra("todo_date");
            int todo_important = intent.getIntExtra("todo_important", 0);

            String[] date = todo_date.split(" ");

            editText5.setText(todo_title);
            editText6.setText(todo_content);
            this.date = date[0];
            time = date[1];
            editText3.setText(this.date);
            editText4.setText(time);
            if(todo_important == 1) {checkBox.setChecked(true);}
            else {checkBox.setChecked(false);}
        }

        editText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DatePicker.class);
                startActivityForResult(intent, REQUEST_CODE_MENU1);
            }
        });

        editText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TimePicker.class);
                startActivityForResult(intent, REQUEST_CODE_MENU2);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText5.getText().toString().length() == 0 || editText6.getText().toString().length() == 0 || editText3.getText().toString().length() == 0 || editText4.getText().toString().length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "값을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DBControl dbControl = new DBControl();
                    dbControl.setDatabase(MainActivity.database);
                    dbControl.setTableName(MainActivity.tableName);

                    todo data = new todo();
                    data.setTitle(editText5.getText().toString());
                    data.setContent(editText6.getText().toString());
                    data.setDate(date+" "+time);
                    data.setImportant(important);

                    if(mod == 1)
                    {
                        data.set_Id(todo_id);
                        dbControl.updateRecord(data, todo_date);
                    }
                    else
                    {
                        dbControl.insertRecord(data);
                    }

                    switch(MainActivity.bottomId)
                    {
                        case R.id.tab1:
                            MainActivity.fm.beginTransaction().replace(R.id.container,new Fragment1()).commitAllowingStateLoss();
                            break;
                        case R.id.tab3:
                            MainActivity.fm.beginTransaction().replace(R.id.container,new Fragment2()).commitAllowingStateLoss();
                            break;
                    }
                    finish();
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    important = 1;
                }
                else
                {
                    important = 0;
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_MENU1)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                mYear = data.getIntExtra("mYear",0);
                mMonth = data.getIntExtra("mMonth",0);
                mDay = data.getIntExtra("mDay",0);
                date = String.format("%d-%02d-%02d",mYear,mMonth,mDay);

                editText3.setText(date);
            }
        }

        if(requestCode == REQUEST_CODE_MENU2)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                H = data.getIntExtra("H",0);
                M = data.getIntExtra("M",0);
                time = String.format("%02d:%02d",H,M);

                editText4.setText(time);
            }
        }
    }
}