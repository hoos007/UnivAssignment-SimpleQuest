package org.techtown.termproject;

import android.view.View;

public interface OnTodoItemClickListener {
    public void onItemClick(todoAdapter.ViewHolder holder, View view, int position);
}
