package com.kastorcode.todolistproject.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.kastorcode.todolistproject.R;
import com.kastorcode.todolistproject.adapters.ToDoListAdapter;
import com.kastorcode.todolistproject.databinding.FragmentAddBinding;
import com.kastorcode.todolistproject.exceptions.ToDoTaskEmptyTitleException;
import com.kastorcode.todolistproject.helpers.AlertHelper;
import com.kastorcode.todolistproject.helpers.ToDoListHelper;
import com.kastorcode.todolistproject.models.ToDoTaskModel;

import java.io.IOException;
import java.util.ArrayList;

public class AddFragment {

    private final ArrayList <ToDoTaskModel> toDoList;
    private final ToDoListAdapter toDoListAdapter;
    private EditText addDescription;
    private EditText addTitle;

    public AddFragment (ArrayList <ToDoTaskModel> toDoList, ToDoListAdapter toDoListAdapter) {
        this.toDoList = toDoList;
        this.toDoListAdapter = toDoListAdapter;
    }

    public View getRoot (LayoutInflater inflater, ViewGroup container) {
        FragmentAddBinding fragmentAdd = FragmentAddBinding.inflate(inflater, container, false);
        addTitle = fragmentAdd.addTitle;
        addDescription = fragmentAdd.addDescription;
        fragmentAdd.addButton.setOnClickListener(view -> {
            ToDoTaskModel toDoTask = new ToDoTaskModel(addTitle.getText().toString(), addDescription.getText().toString());
            try {
                ToDoListHelper.add(view.getContext(), toDoList, toDoTask, true);
                AlertHelper.success(view.getContext(), R.string.successAdd);
                resetComponentsValue();
                toDoListAdapter.notifyDataSetChanged();
            }
            catch (ToDoTaskEmptyTitleException e) {
                AlertHelper.error(view.getContext(), R.string.errorTitleEmpty);
            }
            catch (IOException e) {
                AlertHelper.error(view.getContext(), R.string.errorSaveData);
            }
        });
        return fragmentAdd.getRoot();
    }

    private void resetComponentsValue () {
        addTitle.setText("");
        addDescription.setText("");
    }

}