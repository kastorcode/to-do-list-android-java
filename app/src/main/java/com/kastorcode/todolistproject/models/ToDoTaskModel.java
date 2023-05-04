package com.kastorcode.todolistproject.models;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

import com.kastorcode.todolistproject.R;
import com.kastorcode.todolistproject.adapters.ToDoListAdapter;
import com.kastorcode.todolistproject.helpers.AlertHelper;
import com.kastorcode.todolistproject.helpers.ToDoListHelper;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class ToDoTaskModel implements Serializable {

    private String title;
    private String description;

    public ToDoTaskModel (String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle () {
        return title;
    }

    @SuppressWarnings("all")
    public void setTitle (String title) {
        this.title = title;
    }

    @SuppressWarnings("all")
    public String getDescription () {
        return description;
    }

    @SuppressWarnings("all")
    public void setDescription (String description) {
        this.description = description;
    }

    private void copy (Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newPlainText("ToDo Task", title + "\n\n" + description));
        Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
    }

    public void show (Context context, ArrayList <ToDoTaskModel> toDoList, int i, ToDoListAdapter toDoListAdapter) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog
                .setTitle(title)
                .setMessage(description)
                .setNeutralButton(R.string.close, (dialogInterface, j) -> dialogInterface.dismiss())
                .setNegativeButton(R.string.copy, (dialogInterface, j) -> {
                    dialogInterface.dismiss();
                    copy(context);
                })
                .setPositiveButton(R.string.delete, (dialogInterface, j) -> {
                    dialogInterface.dismiss();
                    showDeleteDialog(context, toDoList, i, toDoListAdapter);
                })
                .show();
    }

    private void showDeleteDialog (Context context, ArrayList <ToDoTaskModel> toDoList, int i, ToDoListAdapter toDoListAdapter) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog
                .setTitle(R.string.confirmation)
                .setMessage(R.string.wantDelete)
                .setNeutralButton(R.string.no, (dialogInterface, j) -> dialogInterface.dismiss())
                .setPositiveButton(R.string.yes, (dialogInterface, j) -> {
                    dialogInterface.dismiss();
                    try {
                        ToDoListHelper.remove(context, toDoList, i, true);
                        toDoListAdapter.notifyDataSetChanged();
                    }
                    catch (IOException e) {
                        AlertHelper.error(context, R.string.errorSaveData);
                    }
                })
                .show();
    }

}