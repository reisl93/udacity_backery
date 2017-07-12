package com.example.android.bakingapp.network;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.ui.utils.UiUtils;

public abstract class RecipesLoader implements LoaderManager.LoaderCallbacks<Recipe[]> {

    public static final int LOADER_ID = 12319;
    private static final String TAG = RecipesLoader.class.getSimpleName();

    private final Context mContext;

    protected RecipesLoader(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Loader<Recipe[]> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "creating recipes loader");
        return new AsyncTaskLoader<Recipe[]>(mContext) {

            Recipe[] mRecipes;

            @Override
            protected void onStartLoading() {
                if (mRecipes != null) {
                    deliverResult(mRecipes);
                } else {
                    forceLoad();
                }
            }
            @Override
            public Recipe[] loadInBackground() {
                return UiUtils.getRecipes();
            }

            public void deliverResult( Recipe[] data) {
                mRecipes = data;
                super.deliverResult(data);
            }
        };
    }

}
