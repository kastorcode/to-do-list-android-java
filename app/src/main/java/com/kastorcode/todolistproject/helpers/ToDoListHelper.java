package com.kastorcode.todolistproject.helpers;

import android.content.Context;

import com.kastorcode.todolistproject.exceptions.ToDoTaskEmptyTitleException;
import com.kastorcode.todolistproject.models.ToDoTaskModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ToDoListHelper {

    private static final String FILE_NAME = "toDoList.dat";

    public static void add (Context context, ArrayList <ToDoTaskModel> toDoList, ToDoTaskModel toDoTask, Boolean isWriteData) throws ToDoTaskEmptyTitleException, IOException {
        if (toDoTask.getTitle().isEmpty()) {
            throw new ToDoTaskEmptyTitleException();
        }
        toDoList.add(toDoTask);
        if (isWriteData) {
            writeData(context, toDoList);
        }
    }

    @SuppressWarnings("all")
    public static void remove (Context context, ArrayList <ToDoTaskModel> toDoList, int i, Boolean isWriteData) throws IOException {
        if (toDoList.get(i) == null) {
            return;
        }
        toDoList.remove(i);
        if (isWriteData) {
            writeData(context, toDoList);
        }
    }

    public static void writeData (Context context, ArrayList <ToDoTaskModel> toDoList) throws IOException {
        FileOutputStream fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(toDoList);
        objectOutputStream.close();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static ArrayList <ToDoTaskModel> readData (Context context) throws IOException {
        FileInputStream fileInputStream;
        try {
            fileInputStream = context.openFileInput(FILE_NAME);
        }
        catch (FileNotFoundException e) {
            return new ArrayList();
        }
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList <ToDoTaskModel> toDoList;
        try {
            toDoList = (ArrayList<ToDoTaskModel>) objectInputStream.readObject();
        }
        catch (ClassNotFoundException e) {
            toDoList = new ArrayList();
        }
        objectInputStream.close();
        return toDoList;
    }

}