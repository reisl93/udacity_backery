package com.example.android.bakingapp.ui.recipe.overview;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.provider.IngredientsColumns;
import com.example.android.bakingapp.provider.RecipeProvider;
import com.example.android.bakingapp.ui.recipe.steps.RecipeActivity;
import com.squareup.picasso.Picasso;

import static com.example.android.bakingapp.ui.utils.UiUtils.backgroundTarget;
import static com.example.android.bakingapp.ui.widget.IngredientsUpdateService.startActionUpdateIngredientsWidgets;

class RecipesAdapter extends ArrayAdapter<Recipe> {
    private static final String TAG = RecipesAdapter.class.getSimpleName();

    RecipesAdapter(Context context) {
        super(context, R.layout.recipes_overview_item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Recipe recipe = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.recipes_overview_item, parent, false);
        }


        final TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        final TextView servings = (TextView) convertView.findViewById(R.id.tv_servings);
        final View recipe_container = convertView.findViewById(R.id.ll_recipe_item);

        title.setText(recipe.getName());
        servings.setText(String.format(getContext().getString(R.string.serving), String.valueOf(recipe.getServings())));

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

                getContext().getContentResolver().delete(RecipeProvider.LAST_USED_RECIPE_INGREDIENTS.INGREDIENTS, null, null);
                final ContentValues[] contentValues = new ContentValues[recipe.getIngredients().length];
                for (int i = 0; i < recipe.getIngredients().length; i++){
                    Ingredient[] ingredients = recipe.getIngredients();
                    final Ingredient ingredient = ingredients[i];
                    contentValues[i] = new ContentValues();
                    contentValues[i].put(IngredientsColumns.INGREDIENT, ingredient.getIngredient());
                    contentValues[i].put(IngredientsColumns.QUANTITY, ingredient.getQuantity());
                    contentValues[i].put(IngredientsColumns.MEASURE, ingredient.getMeasure());
                }
                Log.d(TAG, "inserting " + contentValues.length + " items to ingredients");
                getContext().getContentResolver().bulkInsert(RecipeProvider.LAST_USED_RECIPE_INGREDIENTS.INGREDIENTS, contentValues);

                startActionUpdateIngredientsWidgets(getContext());
            }
        });

        return convertView;
    }
}
