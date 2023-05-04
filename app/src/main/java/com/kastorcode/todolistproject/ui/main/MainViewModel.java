package com.kastorcode.todolistproject.ui.main;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.kastorcode.todolistproject.R;
import com.kastorcode.todolistproject.adapters.ToDoListAdapter;
import com.kastorcode.todolistproject.helpers.AlertHelper;
import com.kastorcode.todolistproject.helpers.ToDoListHelper;
import com.kastorcode.todolistproject.models.ToDoTaskModel;

import java.io.IOException;
import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    public ArrayList <ToDoTaskModel> toDoList;
    public ToDoListAdapter toDoListAdapter;

    @SuppressWarnings({"rawtypes", "unchecked"})
    public MainViewModel (Context context) {
        try {
            toDoList = ToDoListHelper.readData(context);
        }
        catch (IOException e) {
            toDoList = new ArrayList();
            AlertHelper.error(context, R.string.errorReadTaskFile);
        }
        toDoListAdapter = new ToDoListAdapter(context, toDoList);
    }

}