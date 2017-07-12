package com.example.android.bakingapp.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.provider.IngredientsColumns;
import com.example.android.bakingapp.provider.RecipeProvider;


public class ListWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this.getApplicationContext());
    }
}

class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = ListRemoteViewsFactory.class.getSimpleName();
    private final Context mContext;
    private Cursor mIngredientsCursor;

    ListRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;

    }

    @Override
    public void onCreate() {}

    @Override
    public void onDataSetChanged() {
        Log.d(TAG, "Widget loaded recipes...");

        if (mIngredientsCursor != null) mIngredientsCursor.close();
        mIngredientsCursor = mContext.getContentResolver().query(
                RecipeProvider.LAST_USED_RECIPE_INGREDIENTS.INGREDIENTS, null, null, null, null
        );
        Log.d(TAG, "Widget loaded ingredients: " + getCount());
    }

    @Override
    public void onDestroy() {
        mIngredientsCursor.close();
    }


    @Override
    public int getCount() {
        if (mIngredientsCursor == null) return 0;
        return mIngredientsCursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.d(TAG, "updating view at " + position + " cursor here?");
        if (mIngredientsCursor == null || mIngredientsCursor.getCount() == 0) return null;

        Log.d(TAG, "creating position " + position);

        mIngredientsCursor.moveToPosition(position);
        final String ingredient = mIngredientsCursor.getString(
                mIngredientsCursor.getColumnIndex(IngredientsColumns.INGREDIENT));
        final String measure = mIngredientsCursor.getString(
                mIngredientsCursor.getColumnIndex(IngredientsColumns.MEASURE));
        final String quantity = String.valueOf(mIngredientsCursor.getDouble(
                mIngredientsCursor.getColumnIndex(IngredientsColumns.QUANTITY)))
                .replaceAll("\\.[0]+", "");

        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.ingredient_app_widget_item);

        views.setTextViewText(R.id.widget_tv_ingredient, ingredient);
        views.setTextViewText(R.id.widget_tv_measure, measure);
        views.setTextViewText(R.id.widget_tv_quantity, quantity);

        return views;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}