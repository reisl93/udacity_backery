package com.example.android.bakingapp;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.ui.recipe.steps.RecipeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecipeActivityScreenTest {

    @Rule
    public final ActivityTestRule<RecipeActivity> mActivityTestRule = new ActivityTestRule<>(RecipeActivity.class, false, false);

    @Before
    public void launchActivityWithIntent(){
        Intent intent = new Intent();
        Recipe recipe = new Recipe();
        recipe.setName("asdf");
        recipe.setSteps(new Step[]{new Step(0, "asdf", "ddddddddd"), new Step(1, "AAAA", "BBBBBB")});
        recipe.setIngredients(new Ingredient[]{new Ingredient(1, "G", "AAAA"), new Ingredient(5, "G", "BBBBB")});
        intent.putExtra(RecipeActivity.EXTRA_RECIPE, recipe);
        mActivityTestRule.launchActivity(intent);
    }

    @Test
    public void steps_clickRecycleViewItem_OpensRecipesStepActivity() {
        onView(withId(R.id.rv_steps)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_steps)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        if (TestUtils.isTablet(getContext())){
            // if tablet => fragment view
            onView(withId(R.id.rv_steps)).check(matches(isDisplayed()));
        } else {
            //if phone activity view -> steps are now hidden
            onView(withId(R.id.rv_steps)).check(doesNotExist());
        }
    }

    @Test
    public void ingredients_clickRecycleViewItem_nothingHappens() {
        onView(withId(R.id.rv_ingredients)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_ingredients)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.rv_ingredients)).check(matches(isDisplayed()));
    }

    public RecipeActivity getContext() {
        return mActivityTestRule.getActivity();
    }

}
