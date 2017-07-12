package com.example.android.bakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.android.bakingapp.ui.recipe.overview.RecipesOverviewActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class OverviewActivityScreenTest {

    private static final String RECIPE_NAME = "Nutella Pie";
    @Rule
    public ActivityTestRule<RecipesOverviewActivity> mActivityTestRule = new ActivityTestRule<>(RecipesOverviewActivity.class);

    @Test
    public void clickGridViewItem_OpensRecipesActivity() {

        // Uses {@link Espresso#onData(org.hamcrest.Matcher)} to get a reference to a specific
        // gridview item and clicks it.
        onData(anything()).inAdapterView(withId(R.id.gv_recipes)).atPosition(0).perform(click());

        //actionbar has right name=
        onView(allOf(
                isAssignableFrom(TextView.class),
                withParent(isAssignableFrom(Toolbar.class)))).check(matches(withText(RECIPE_NAME)));
    }

}
