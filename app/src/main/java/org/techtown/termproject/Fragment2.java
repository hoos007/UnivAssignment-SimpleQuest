package org.techtown.termproject;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Fragment2 extends Fragment {
    private int mYear =0, mMonth=0, mDay=0;
    RecyclerView recyclerView;
    todoAdapter adapter;
    DBControl dbControl;
    String date;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_2, container, false);
        Calendar calendar = new GregorianCalendar();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        android.widget.CalendarView calendarView = rootView.findViewById(R.id.calendarView);

        recyclerView = rootView.findViewById(R.id.recycler2);
        textView = rootView.findViewById(R.id.textView12);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new todoAdapter();
        dbControl = new DBControl();


        dbControl.setDatabase(MainActivity.database);
        dbControl.setTableName(MainActivity.tableName);



        List<todo> todoList = dbControl.selectAllRecord("now");

        if(todoList != null)
        {
            textView.setVisibility(View.GONE);
            for (todo todo: todoList)
            {
                adapter.addItem(new todo(todo.get_Id(), todo.getTitle(), todo.getContent(), todo.getDate(), todo.getImportant()));
            }
        }
        else
        {
            textView.setVisibility(View.VISIBLE);
        }

        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new OnTodoItemClickListener() {
            @Override
            public void onItemClick(todoAdapter.ViewHolder holder, View view, int position) {
                todo item = adapter.getItem(position);

                Intent intent = new Intent(getActivity(), show.class);
                intent.putExtra("todo_id", item.get_Id());
                intent.putExtra("todo_title", item.getTitle());
                intent.putExtra("todo_content", item.getContent());
                intent.putExtra("todo_date", item.getDate());
                intent.putExtra("todo_important", item.getImportant());
                startActivity(intent);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int yy, int mm, int dd) {
                mYear = yy;
                mMonth = mm+1;
                mDay = dd;
                date = String.format("%d-%02d-%02d",mYear,mMonth,mDay);

                Log.i("day", date);

                adapter.items.clear();
                List<todo> todoList = dbControl.selectAllRecord(date);

                if(todoList != null)
                {
                    textView.setVisibility(View.GONE);
                    for (todo todo: todoList)
                    {
                        adapter.addItem(new todo(todo.get_Id(), todo.getTitle(), todo.getContent(), todo.getDate(), todo.getImportant()));
                    }
                }
                else
                {
                    textView.setVisibility(View.VISIBLE);
                }


                recyclerView.setAdapter(adapter);
            }
        });

        return rootView;
    }
}