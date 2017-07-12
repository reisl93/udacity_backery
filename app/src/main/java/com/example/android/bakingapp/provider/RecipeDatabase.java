package com.example.android.bakingapp.provider;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

@SuppressWarnings("WeakerAccess") // since generated
@Database(version = RecipeDatabase.VERSION)
public final class RecipeDatabase {

    public static final int VERSION = 1;

    @Table(IngredientsColumns.class)
    public static final String LAST_USED_RECIPE_INGREDIENTS = "ingredients";

}
