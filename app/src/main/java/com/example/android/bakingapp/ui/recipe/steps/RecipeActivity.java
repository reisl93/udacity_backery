package com.example.android.bakingapp.ui.recipe.steps;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.ui.recipe.step.RecipeStepActivity;
import com.example.android.bakingapp.ui.recipe.step.RecipeStepFragment;

public class RecipeActivity extends AppCompatActivity implements OnStepClicked {

    public final static String EXTRA_RECIPE = "EXTRA_RECIPE";
    private static final String TAG = RecipeActivity.class.getSimpleName();

    private boolean isMultiView;
    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        if (getIntent().hasExtra(EXTRA_RECIPE)) {
            mRecipe = getIntent().getParcelableExtra(EXTRA_RECIPE);
        } else {
            Log.e(TAG, "Has not the " + EXTRA_RECIPE + " parcelable in the intent. abort!");
            return;
        }

        getSupportActionBar().setTitle(mRecipe.getName());

        isMultiView = findViewById(R.id.f_recipe_step_container) != null;

        ((MasterListRecipeStepsFragment) getSupportFragmentManager().findFragmentById(R.id.master_list_fragment))
                .setRecipe(mRecipe)
                .setOnStepClickedListener(this);

        if (isMultiView && savedInstanceState == null && mRecipe.getSteps().length > 0) {
            // In two-pane mode, add initial BodyPartFragments to the screen
            FragmentManager fragmentManager = getSupportFragmentManager();

            // Creating a new head fragment
            RecipeStepFragment stepFragment = new RecipeStepFragment();
            stepFragment.setStep(mRecipe.getSteps()[0]);
            // Add the fragment to its container using a transaction
            fragmentManager.beginTransaction()
                    .add(R.id.f_recipe_step_container, stepFragment)
                    .commit();
        }

    }

    @Override
    public void stepClicked(Step step) {
        if (isMultiView) {
            RecipeStepFragment stepFragment = new RecipeStepFragment();
            stepFragment.setStep(step);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.f_recipe_step_container, stepFragment)
                    .commit();
        } else {
            final Intent showRecipeActivity = new Intent(this, RecipeStepActivity.class);
            showRecipeActivity.putExtra(RecipeStepActivity.EXTRA_RECIPE, mRecipe);
            showRecipeActivity.putExtra(RecipeStepActivity.EXTRA_STEP_ID, step.getId());
            startActivity(showRecipeActivity);
        }
    }
}