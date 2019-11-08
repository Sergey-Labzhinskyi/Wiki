package com.labzhynskyi.wiki.view;


import android.content.Context;
import android.widget.Toast;

import androidx.annotation.StringRes;

public class ToastUtil {

    public static void showLong(@StringRes int resourceId, Context context) {
        showToast(context.getString(resourceId), Toast.LENGTH_LONG, context);
    }

    public static void showLong(final String message, Context context) {
        showToast(message, Toast.LENGTH_LONG, context);
    }

    public static void showShort(int resourceId, Context context) {
        showToast(context.getString(resourceId), Toast.LENGTH_SHORT, context);
    }

    public static void showShort(String message, Context context) {
        showToast(message, Toast.LENGTH_SHORT, context);
    }

    private static void showToast(final String message, final Integer length, Context context) {
        Toast.makeText(context, message, length).show();
    }
}