package com.example.android.bakingapp.ui.recipe.steps;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Ingredient;

import butterknife.BindView;
import butterknife.ButterKnife;


class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {
    private Ingredient[] mIngredients;

    @Override
    public IngredientsViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new IngredientsViewHolder(inflater.inflate(R.layout.ingredients_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mIngredients == null ? 0 : mIngredients.length;
    }

    public void setIngredients(Ingredient[] mIngredients) {
        this.mIngredients = mIngredients;
        notifyDataSetChanged();
    }

    class IngredientsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ingredient)
        TextView ingredientView;
        @BindView(R.id.tv_quantity)
        TextView quantityView;
        @BindView(R.id.tv_measure)
        TextView measureView;

        IngredientsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final int itemIndex) {
            Ingredient ingredient = mIngredients[itemIndex];
            ingredientView.setText(ingredient.getIngredient());
            quantityView.setText(String.valueOf(ingredient.getQuantity()));
            measureView.setText(ingredient.getMeasure());
        }
    }
}
