package com.example.android.bakingapp.ui.recipe.steps;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Recipe;

public class RecipeActivity extends AppCompatActivity {

    public final static String EXTRA_RECIPE = "RecipeObjectParcelable";
    private static final String TAG = RecipeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        final Recipe recipe;
        if (getIntent().hasExtra(EXTRA_RECIPE)) {
            recipe = getIntent().getParcelableExtra(EXTRA_RECIPE);
        } else {
            Log.e(TAG, "Has not the " + EXTRA_RECIPE + " parcelable in the intent. abort!");
            return;
        }



    }
}
