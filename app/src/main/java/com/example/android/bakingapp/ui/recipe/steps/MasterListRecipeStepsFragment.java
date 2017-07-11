package com.example.android.bakingapp.ui.recipe.steps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Recipe;

public class MasterListRecipeStepsFragment extends Fragment {

    private IngredientsAdapter ingredientsAdapter;
    private StepsAdapter stepsAdapter;

    public MasterListRecipeStepsFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_master_recipe_details, container, false);

        ingredientsAdapter = new IngredientsAdapter(getContext());
        ((ListView) rootView.findViewById(R.id.lv_ingredients)).setAdapter(ingredientsAdapter);
        stepsAdapter = new StepsAdapter(getContext());
        ((ListView) rootView.findViewById(R.id.lv_steps)).setAdapter(stepsAdapter);

        return rootView;
    }

    public MasterListRecipeStepsFragment setRecipe(Recipe mRecipe) {
        ingredientsAdapter.addAll(mRecipe.getIngredients());
        stepsAdapter.addAll(mRecipe.getSteps());
        return this;
    }

    public MasterListRecipeStepsFragment setOnStepClickedListener(OnStepClicked onStepClickedListener){
        stepsAdapter.setOnStepClickedListener(onStepClickedListener);
        return this;
    }
}
