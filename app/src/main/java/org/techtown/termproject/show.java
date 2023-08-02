package org.techtown.termproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class show extends AppCompatActivity {

    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    Button button;
    Button button2;

    DBControl dbControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        ActionBar ac = getSupportActionBar();
        ac.setTitle("Todo");

        textView = findViewById(R.id.textView3);
        textView2 = findViewById(R.id.textView8);
        textView3 = findViewById(R.id.textView9);
        textView4 = findViewById(R.id.textView10);

        button = findViewById(R.id.button4);
        button2 = findViewById(R.id.button5);

        dbControl = new DBControl();
        dbControl.setDatabase(MainActivity.database);
        dbControl.setTableName(MainActivity.tableName);

        Intent intent = getIntent();

        int todo_id = intent.getIntExtra("todo_id",0);
        String todo_title = intent.getStringExtra("todo_title");
        String todo_content = intent.getStringExtra("todo_content");
        String todo_date = intent.getStringExtra("todo_date");
        int todo_important = intent.getIntExtra("todo_important", 0);

        textView.setText(todo_title);
        textView2.setText(todo_content);
        textView3.setText(todo_date);
        if(todo_important == 1)
        {
            textView4.setText("중요!");
        }
        else
        {
            textView4.setVisibility(View.GONE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Add.class);
                intent.putExtra("todo_id", todo_id);
                intent.putExtra("todo_title", todo_title);
                intent.putExtra("todo_content", todo_content);
                intent.putExtra("todo_date", todo_date);
                intent.putExtra("todo_important", todo_important);
                intent.putExtra("mod", 1);
                startActivity(intent);
                finish();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbControl.deleteRecord(todo_id, todo_date);
                Log.i("bottom",String.valueOf(MainActivity.bottomId));
                if(MainActivity.bottomId == R.id.tab1)
                {
                    MainActivity.fm.beginTransaction().replace(R.id.container,new Fragment1()).commitAllowingStateLoss();
                }
                else
                {
                    MainActivity.fm.beginTransaction().replace(R.id.container,new Fragment2()).commitAllowingStateLoss();
                }
                finish();
            }
        });
    }
}