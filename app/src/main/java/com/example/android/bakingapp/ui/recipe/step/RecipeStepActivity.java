package com.example.android.bakingapp.ui.recipe.step;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Recipe;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class RecipeStepActivity extends AppCompatActivity {

    public static final String EXTRA_STEP_ID = "EXTRA_STEP_ID";
    public static final String EXTRA_RECIPE = "EXTRA_RECIPE";
    private static final String TAG = RecipeStepActivity.class.getSimpleName();

    private Recipe mRecipe;
    private int mStepId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);

        if (getIntent().hasExtra(EXTRA_RECIPE) && getIntent().hasExtra(EXTRA_STEP_ID)) {
            mRecipe = getIntent().getParcelableExtra(EXTRA_RECIPE);
            mStepId = getIntent().getIntExtra(EXTRA_STEP_ID, -1);
        } else {
            Log.e(TAG, "Has not the " + EXTRA_RECIPE + " parcelable in the intent. abort!");
            return;
        }

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        ((RecipeStepFragment) getSupportFragmentManager().findFragmentById(R.id.f_recipe_step))
                .setStep(mRecipe.getSteps()[mStepId]);
    }

    public void nextStep() {
        mStepId++;
        if (mStepId > mRecipe.getSteps().length) {
            mStepId = mRecipe.getSteps().length - 1;
        }

        ((RecipeStepFragment) getSupportFragmentManager().findFragmentById(R.id.f_recipe_step))
                .setStep(mRecipe.getSteps()[mStepId]);
    }

}
