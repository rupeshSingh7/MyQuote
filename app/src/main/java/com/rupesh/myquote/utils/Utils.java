package com.rupesh.myquote.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

public class Utils {

    public static int calculateNoOfColumns(@NonNull Context context) {

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }
}
