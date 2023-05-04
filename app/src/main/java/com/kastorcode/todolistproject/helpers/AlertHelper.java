package com.kastorcode.todolistproject.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

import com.kastorcode.todolistproject.R;
import com.kastorcode.todolistproject.models.SettingsModel;

public class AlertHelper {

    public static void error (Context context, int message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(R.string.error);
        alertDialog.setMessage(message);
        alertDialog.setNeutralButton(R.string.close, (dialogInterface, i) -> dialogInterface.dismiss());
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public static void success (Context context, int message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(R.string.success);
        alertDialog.setMessage(message);
        alertDialog.setNeutralButton(R.string.close, (dialogInterface, i) -> dialogInterface.dismiss());
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public static void about (Activity activity, SettingsModel settingsModel) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle(R.string.about);
        alertDialog.setMessage(activity.getString(R.string.poweredBy) + "\ngithub.com/kastorcode");
        alertDialog.setNeutralButton(R.string.close, (dialogInterface, i) -> dialogInterface.dismiss());
        alertDialog.setNegativeButton("Github", (dialogInterface, i) -> LinkHelper.open(activity, "https://github.com/kastorcode"));
        alertDialog.setPositiveButton(R.string.theme, (dialogInterface, i) -> {
            dialogInterface.dismiss();
            SettingsHelper.showThemesDialog(activity, settingsModel);
        });
        alertDialog.show();
    }

}