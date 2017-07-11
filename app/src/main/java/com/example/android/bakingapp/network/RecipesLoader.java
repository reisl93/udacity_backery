package com.example.android.bakingapp.network;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.example.android.bakingapp.data.Recipe;

import java.io.IOException;
import java.net.URL;

public abstract class RecipesLoader implements LoaderManager.LoaderCallbacks<Recipe[]> {

    public static final int LOADER_ID = 123123999;

    private final Context mContext;

    protected RecipesLoader(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Loader<Recipe[]> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Recipe[]>(mContext) {
            @Override
            public Recipe[] loadInBackground() {
                URL url;
                try {
                    url = new URL("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
                    return NetworkUtils.getObjectFromHttpUrlJsonResponse(url, Recipe[].class);
                } catch (IOException e) {
                    return null;
                }
            }
        };
    }
}
