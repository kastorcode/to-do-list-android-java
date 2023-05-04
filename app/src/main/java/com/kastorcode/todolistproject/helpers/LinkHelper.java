package com.kastorcode.todolistproject.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class LinkHelper {

    public static void open (Context context, String uri) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
    }

}