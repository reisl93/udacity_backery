package com.example.android.bakingapp.ui.recipe.step;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Recipe;

import butterknife.ButterKnife;
import butterknife.OnClick;

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

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            getSupportActionBar().hide();
        } else {

        }

        setContentView(R.layout.activity_recipe_step);
        ButterKnife.bind(this);

        if (getIntent().hasExtra(EXTRA_RECIPE) && getIntent().hasExtra(EXTRA_STEP_ID)) {
            mRecipe = getIntent().getParcelableExtra(EXTRA_RECIPE);
            mStepId = getIntent().getIntExtra(EXTRA_STEP_ID, -1);
        } else {
            Log.e(TAG, "Has not the " + EXTRA_RECIPE + " parcelable in the intent. abort!");
            return;
        }
        if (savedInstanceState != null){
            mStepId = savedInstanceState.getInt(EXTRA_STEP_ID);
        }

        showStep();
    }

    @OnClick(value = R.id.b_nextStep)
    public void nextStepClick(){
        mStepId++;
        showStep();
    }

    public void showStep() {
        RecipeStepFragment stepFragment = (RecipeStepFragment) getSupportFragmentManager().findFragmentById(R.id.f_recipe_step);
        if (mStepId < mRecipe.getSteps().length) {
            stepFragment.setStep(mRecipe.getSteps()[mStepId]);
        }
        if (mStepId == mRecipe.getSteps().length - 1){
            findViewById(R.id.b_nextStep).setVisibility(View.GONE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(EXTRA_STEP_ID, mStepId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
