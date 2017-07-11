package com.example.android.bakingapp.ui.recipe.overview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.ui.recipe.steps.RecipeActivity;
import com.squareup.picasso.Picasso;

import static com.example.android.bakingapp.ui.utils.UiUtils.backgroundTarget;

class RecipesAdapter extends ArrayAdapter<Recipe> {
    public RecipesAdapter(Context context) {
        super(context, R.layout.recipes_overview_item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Recipe recipe = getItem(position);

        final TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        final TextView servings = (TextView) convertView.findViewById(R.id.tv_servings);
        final View recipe_container = convertView.findViewById(R.id.ll_recipe_item);

        title.setText(recipe.getName());
        servings.setText(String.format(String.valueOf(R.string.serving), recipe.getServings()));

        if (!"".equals(recipe.getImage())){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                Picasso.with(getContext()).load(Uri.parse(recipe.getImage())).into(backgroundTarget(getContext(), recipe_container));
            }
        }

        recipe_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent showRecipeActivity = new Intent(getContext(), RecipeActivity.class);
                showRecipeActivity.putExtra(RecipeActivity.EXTRA_RECIPE, recipe);
                getContext().startActivity(showRecipeActivity);
            }
        });

        return convertView;
    }
}
