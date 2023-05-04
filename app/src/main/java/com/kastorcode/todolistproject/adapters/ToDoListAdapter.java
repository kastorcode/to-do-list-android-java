package com.kastorcode.todolistproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kastorcode.todolistproject.R;
import com.kastorcode.todolistproject.models.ToDoTaskModel;

import java.util.ArrayList;

public class ToDoListAdapter extends BaseAdapter {

    private final ArrayList <ToDoTaskModel> toDoList;
    private final LayoutInflater layoutInflater;

    public ToDoListAdapter (Context context, ArrayList <ToDoTaskModel> toDoList) {
        this.toDoList = toDoList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount () {
        return toDoList.size();
    }

    @Override
    public Object getItem (int i) {
        return toDoList.get(i);
    }

    @Override
    public long getItemId (int i) {
        return i;
    }

    @Override
    public View getView (int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.fragment_task, null);
        }
        TextView taskTitle = view.findViewById(R.id.task_title);
        taskTitle.setText(toDoList.get(i).getTitle());
        return view;
    }

}