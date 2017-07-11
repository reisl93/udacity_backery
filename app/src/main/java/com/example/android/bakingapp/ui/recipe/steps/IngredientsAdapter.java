package com.example.android.bakingapp.ui.recipe.steps;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Ingredient;


public class IngredientsAdapter extends ArrayAdapter<Ingredient> {
    public IngredientsAdapter(@NonNull Context context) {
        super(context, R.layout.ingredients_item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Ingredient ingredient = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.ingredients_item, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.tv_ingredient))
                .setText(ingredient.getIngredient());
        ((TextView) convertView.findViewById(R.id.tv_quantity))
                .setText(String.valueOf(ingredient.getQuantity()));
        ((TextView) convertView.findViewById(R.id.tv_measure))
                .setText(ingredient.getMeasure());

        return convertView;
    }
}
