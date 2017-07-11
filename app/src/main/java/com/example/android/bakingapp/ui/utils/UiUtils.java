package com.example.android.bakingapp.ui.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class UiUtils {


    private static final String TAG = UiUtils.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static Target backgroundTarget(final Context context, final View view){
        return new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                view.setBackground(new BitmapDrawable(context.getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.e(TAG, "failed to load background");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.e(TAG, "failed to prepare background");
            }
        };
    }

}
