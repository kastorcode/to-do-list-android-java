package com.kastorcode.todolistproject.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kastorcode.todolistproject.adapters.ToDoListAdapter;
import com.kastorcode.todolistproject.databinding.FragmentViewBinding;
import com.kastorcode.todolistproject.models.ToDoTaskModel;

import java.util.ArrayList;

public class ViewFragment {

    private final ArrayList <ToDoTaskModel> toDoList;
    private final ToDoListAdapter toDoListAdapter;

    public ViewFragment (ArrayList <ToDoTaskModel> toDoList, ToDoListAdapter toDoListAdapter) {
        this.toDoList = toDoList;
        this.toDoListAdapter = toDoListAdapter;
    }

    public View getRoot (LayoutInflater inflater, ViewGroup container) {
        FragmentViewBinding fragmentView = FragmentViewBinding.inflate(inflater, container, false);
        fragmentView.viewList.setAdapter(toDoListAdapter);
        fragmentView.viewList.setOnItemClickListener((adapterView, view, i, l) ->
            toDoList.get(i).show(view.getContext(), toDoList, i, toDoListAdapter));
        return fragmentView.getRoot();
    }

}