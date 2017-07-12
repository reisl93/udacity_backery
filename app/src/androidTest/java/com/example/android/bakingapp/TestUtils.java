package com.example.android.bakingapp;

import android.content.Context;
import android.content.res.Configuration;

public final class TestUtils {
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
