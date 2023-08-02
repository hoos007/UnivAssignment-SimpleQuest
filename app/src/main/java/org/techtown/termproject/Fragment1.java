package org.techtown.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

public class Fragment1 extends Fragment {
    RecyclerView recyclerView;
    static todoAdapter adapter;
    static DBControl dbControl;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        textView = rootView.findViewById(R.id.textView11);

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

        return rootView;
    }
}