package com.example.android.bakingapp.provider;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = RecipeProvider.AUTHORITY, database = RecipeDatabase.class)
public class RecipeProvider {

    public static final String AUTHORITY = "com.example.android.bakingapp.RecipesProvider";

    @TableEndpoint(table = RecipeDatabase.LAST_USED_RECIPE_INGREDIENTS)
    public static class LAST_USED_RECIPE_INGREDIENTS {
        @ContentUri(
                path = "ingredients",
                type = "vnd.android.cursor.dir/list",
                defaultSort = IngredientsColumns._ID + " ASC")
        public static final Uri INGREDIENTS = Uri.parse("content://" + AUTHORITY + "/ingredients");
    }
}
