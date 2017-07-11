package com.example.android.bakingapp.ui.recipe.overview;

import android.content.Context;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.network.RecipesLoader;

public class RecipesOverviewActivity extends AppCompatActivity {


    private RecipesAdapter mRecipesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_overview);

        mRecipesAdapter = new RecipesAdapter(this);
        ((GridView) findViewById(R.id.gv_recipes)).setAdapter(mRecipesAdapter);

        getSupportLoaderManager().initLoader(RecipesCallback.LOADER_ID, null, new RecipesCallback(this));
    }

    private class RecipesCallback extends RecipesLoader {
        RecipesCallback(Context mContext) {
            super(mContext);
        }

        @Override
        public void onLoadFinished(Loader<Recipe[]> loader, Recipe[] data) {
            if (data != null) {
                mRecipesAdapter.clear();
                mRecipesAdapter.addAll(data);
            }
        }

        @Override
        public void onLoaderReset(Loader<Recipe[]> loader) {
            mRecipesAdapter.clear();
        }
    }
}
