package com.example.android.bakingapp.ui.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.bakingapp.R;

public class IngredientsUpdateService extends IntentService{

    private static final String TAG = IntentService.class.getSimpleName();

    public static final String ACTION_UPDATE_INGREDIENT_WIDGETS = "com.example.android.bakingapp.action.update_plant_widgets";

    public IngredientsUpdateService() {
        super(IngredientsUpdateService.class.getSimpleName());
    }

    public static void startActionUpdateIngredientsWidgets(Context context) {
        Intent intent = new Intent(context, IngredientsUpdateService.class);
        intent.setAction(ACTION_UPDATE_INGREDIENT_WIDGETS);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_INGREDIENT_WIDGETS.equals(action)) {
                handleActionUpdateIngredientsWidgets();
            }
        }
    }

    private void handleActionUpdateIngredientsWidgets() {
        Log.d(TAG, "update ingredients Widget");
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, IngredientsAppWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_lv_ingredients);
        IngredientsAppWidgetProvider.updateIngredientsWidgets(this, appWidgetManager, appWidgetIds);
    }
}
