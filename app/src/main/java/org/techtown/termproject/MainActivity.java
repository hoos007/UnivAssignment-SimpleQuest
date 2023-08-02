package org.techtown.termproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Fragment1 fragment1;
    Fragment2 fragment2;

    static FragmentManager fm;

    Button button;

    static int bottomId;

    BottomNavigationView bottomNavigation;

    static SQLiteDatabase database;
    static String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ac = getSupportActionBar();
        ac.setTitle("Today");

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        button = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Add.class);
                intent.putExtra("mod", 0);
                startActivity(intent);
            }
        });

        fm = getSupportFragmentManager();

        fm.beginTransaction().replace(R.id.container, fragment1).commit();

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomId = bottomNavigation.getSelectedItemId();

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                bottomId = item.getItemId();
                switch (bottomId)
                {
                    case R.id.tab1:
                        ac.setTitle("Today");
                        fm.beginTransaction().replace(R.id.container, fragment1).commit();
                        return true;
                    case R.id.tab3:
                        ac.setTitle("Calender");
                        fm.beginTransaction().replace(R.id.container, fragment2).commit();
                        return true;
                }
                return false;
            }
        });

        createDatabase("todoDB");

        tableName = "todoTable";
        createTable(tableName);


    }

    private void createDatabase(String name) {
        database = openOrCreateDatabase(name, MODE_PRIVATE, null);
    }

    private void createTable(String name) {
        database.execSQL("create table if not exists "+name+"(" +
                " _id integer, " +
                "title text, " +
                "content text, " +
                "date date, " +
                "important integer," +
                "PRIMARY KEY (_id, title, content, date))");
    }
}