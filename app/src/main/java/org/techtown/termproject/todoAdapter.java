package org.techtown.termproject;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.ViewHolder> implements OnTodoItemClickListener{
    ArrayList<todo> items = new ArrayList<todo>();
    OnTodoItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.todo_item, viewGroup, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        todo item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null)
        {
            listener.onItemClick(holder, view, position);
        }
    }

    public void setOnItemClickListener(OnTodoItemClickListener listener)
    {
        this.listener = listener;
    }



    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;

        TextView textView;
        TextView textView2;

        public ViewHolder(View itemView, final  OnTodoItemClickListener listener) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.important_back);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(todo item){
            if(item.getImportant() == 1) {
                linearLayout.setBackgroundColor(Color.parseColor("#FFFF6C"));
            }
            textView.setText(item.getTitle());
            textView2.setText(item.getDate());
        }

    }

    public void addItem(todo item)
    {
        items.add(item);
    }

    public void setItems(ArrayList<todo> items)
    {
        this.items = items;
    }

    public todo getItem(int position)
    {
        return items.get(position);
    }

    public void setItem(int position, todo item)
    {
        items.set(position, item);
    }


}
