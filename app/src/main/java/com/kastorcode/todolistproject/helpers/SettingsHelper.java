package com.kastorcode.todolistproject.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import com.kastorcode.todolistproject.R;
import com.kastorcode.todolistproject.exceptions.ThemeNotFoundException;
import com.kastorcode.todolistproject.models.SettingsModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SettingsHelper {

    private static final String FILE_NAME = "settings.dat";
    private static final int[] THEMES_NAMES = { R.string.orange, R.string.purple };
    private static final int[] THEMES = { R.style.OrangeTheme, R.style.PurpleTheme };

    @SuppressWarnings("all")
    public static void applySettings (Activity activity, SettingsModel settingsModel) {
        try {
            setTheme(activity, settingsModel.getTheme(), settingsModel);
        }
        catch (IOException e) {}
        catch (ThemeNotFoundException e) {}
    }

    private static void setTheme (Activity activity, int i, SettingsModel settingsModel) throws ThemeNotFoundException, IOException {
        if (i < 0 || i > THEMES.length - 1) {
            throw new ThemeNotFoundException();
        }
        activity.setTheme(THEMES[i]);
        if (i != settingsModel.getTheme()) {
            settingsModel.setTheme(i);
            writeData(activity, settingsModel);
            activity.finish();
            activity.startActivity(new Intent(activity, activity.getClass()));
        }
    }

    public static void showThemesDialog (Activity activity, SettingsModel settingsModel) {
        String[] themes = new String[THEMES_NAMES.length];
        for (int i = 0; i < THEMES_NAMES.length; i++) {
            themes[i] = activity.getString(THEMES_NAMES[i]);
        }
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle(R.string.theme);
        alertDialog.setItems(themes, (dialogInterface, i) -> {
            dialogInterface.dismiss();
            try {
                setTheme(activity, i, settingsModel);
            }
            catch (ThemeNotFoundException e) {
                throw new RuntimeException(e);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        alertDialog.show();
    }

    private static void writeData (Context context, SettingsModel settingsModel) throws IOException {
        FileOutputStream fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(settingsModel);
        objectOutputStream.close();
    }

    @SuppressWarnings("all")
    public static SettingsModel readData (Context context) {
        SettingsModel settingsModel = new SettingsModel(0);
        try {
            FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            settingsModel = (SettingsModel) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (ClassNotFoundException e) {}
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
        return settingsModel;
    }

}