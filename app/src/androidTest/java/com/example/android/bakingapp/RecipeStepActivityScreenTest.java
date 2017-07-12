package com.example.android.bakingapp;

import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.ui.recipe.step.RecipeStepActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * also tests the fragment itself. Therefore the fragment for the multiview has not to be tested explicitly
 */
@RunWith(AndroidJUnit4.class)
public class RecipeStepActivityScreenTest {

    @Rule
    public ActivityTestRule<RecipeStepActivity> mActivityTestRule = new ActivityTestRule<>(RecipeStepActivity.class, true, false);

    private String descriptionText1 = "AAAA";
    private String descriptionText2 = "XXXXXXXX";

    @Before
    public void launchActivityWithIntent() {
        Intent intent = new Intent();
        Recipe recipe = new Recipe();
        recipe.setName("asdf");
        Step step0 = new Step(0, "step0", descriptionText1);
        step0.setVideoURL("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");
        recipe.setSteps(new Step[]{step0, new Step(1, "step1", descriptionText2)});
        recipe.setIngredients(new Ingredient[]{new Ingredient(1, "G", "AASDF"), new Ingredient(5, "G", "BBBBB")});
        intent.putExtra(RecipeStepActivity.EXTRA_RECIPE, recipe);
        intent.putExtra(RecipeStepActivity.EXTRA_STEP_ID, 0);
        mActivityTestRule.launchActivity(intent);
    }

    /**
     * if a video is played is easily checked, since it is either visible or not (if existing)
     */
    @Test
    public void clickButton_videoPlayed() {
        //test is only for devices which are no tablets!!
        if (!TestUtils.isTablet(getContext())) {
            //step 0 has video
            onView(withId(R.id.pfl_exoPlayerContainer)).check(matches(isDisplayed()));
            onView(allOf(withId(R.id.b_nextStep), withText(R.string.next_step)))
                    .perform(scrollTo(), click());
            onView(withId(R.id.pfl_exoPlayerContainer)).check(matches(not(isDisplayed())));
        }
    }

    @Test
    public void clickButton_nextDescriptionText() {
        //test is only for devices which are no tablets!!
        if (!TestUtils.isTablet(getContext())) {
            onView(withId(R.id.tv_description)).check(matches(withText(descriptionText1)));
            onView(allOf(withId(R.id.b_nextStep), withText(R.string.next_step)))
                    .perform(scrollTo(), click());
            onView(withId(R.id.tv_description)).check(matches(withText(descriptionText2)));
        }
    }

    public RecipeStepActivity getContext() {
        return mActivityTestRule.getActivity();
    }
}

