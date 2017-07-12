package com.example.android.bakingapp.ui.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;

import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.network.NetworkUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.net.URL;

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

    @Nullable
    public static Recipe[] getRecipes() {
        URL url;
        try {
            //url = new URL("http://go.udacity.com/android-baking-app-json");
            url = new URL("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
            return NetworkUtils.getObjectFromHttpUrlJsonResponse(url, Recipe[].class);
        } catch (IOException e) {
            Log.d(TAG, "failed to load baking.json");
            e.printStackTrace();
            return null;
        }
    }
}
