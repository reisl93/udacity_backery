package com.example.android.bakingapp.ui.recipe.steps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MasterListRecipeStepsFragment extends Fragment {

    private IngredientsAdapter ingredientsAdapter;
    private StepsAdapter stepsAdapter;

    @BindView(R.id.rv_ingredients)
    public RecyclerView mIngredientsRecyclerView;
    @BindView(R.id.rv_steps)
    public RecyclerView mStepsRecyclerView;
    private Unbinder unbinder;

    public MasterListRecipeStepsFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_master_recipe_details, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        ingredientsAdapter = new IngredientsAdapter();
        mIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mIngredientsRecyclerView.setAdapter(ingredientsAdapter);

        stepsAdapter = new StepsAdapter();
        mStepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mStepsRecyclerView.setAdapter(stepsAdapter);

        return rootView;
    }

    public MasterListRecipeStepsFragment setRecipe(Recipe mRecipe) {
        ingredientsAdapter.setIngredients(mRecipe.getIngredients());
        stepsAdapter.setSteps(mRecipe.getSteps());
        return this;
    }

    public MasterListRecipeStepsFragment setOnStepClickedListener(OnStepClicked onStepClickedListener){
        stepsAdapter.setOnStepClickedListener(onStepClickedListener);
        return this;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
