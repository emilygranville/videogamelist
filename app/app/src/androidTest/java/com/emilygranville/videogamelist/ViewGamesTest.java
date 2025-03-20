package com.emilygranville.videogamelist;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.emilygranville.videogamelist.Controller.MainActivity;

import org.hamcrest.Matcher;

public class ViewGamesTest {

    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @org.junit.Test
    public void viewGames() {
        ViewInteraction rv = onView(withId(R.id.displayRecyclerView));

        // is the recycler view showing anything
        rv.check(matches(isDisplayed()));
        // does it have 4 children
        // based on shown items only
        rv.check(matches(hasMinimumChildCount(4)));

        // does it display game1
        rv.check(matches(hasDescendant(withText("Game1"))));
        // scroll to game 6
        // then does it display game6
        rv.perform(scrollToPosition(5))
                .check(matches(hasDescendant(withText("Game6"))));
    }

    @org.junit.Test
    public void deleteGame() {
        ViewInteraction rv = onView(withId(R.id.displayRecyclerView));

        // is the recycler view showing anything
        rv.check(matches(isDisplayed()));
        // does it have 4 children
        // based on shown items only
        rv.check(matches(hasMinimumChildCount(4)));
        // checks that Game1 is currently displayed
        rv.check(matches(hasDescendant(withText("Game1"))));

        // clicks delete button for game 1
        rv.perform(actionOnItemAtPosition(0,
                CustomActions.clickChildViewWithId(R.id.vg_delete_btn)));

        // checks that there are still 4 children (Game5 should be visible)
        rv.check(matches(hasMinimumChildCount(4)));
        // checks that Game5 is currently displayed
        rv.check(matches(hasDescendant(withText("Game5"))));
        // checks that Game1 is gone
        onView(withText("Game1")).check(doesNotExist());

        // clicks delete button for game 6 (position 4)
        rv.perform(actionOnItemAtPosition(4,
                CustomActions.clickChildViewWithId(R.id.vg_delete_btn)));

        // checks that there are still 4 children (Game5 should be visible)
        rv.check(matches(hasMinimumChildCount(4)));
        // checks that Game5 is currently displayed
        rv.check(matches(hasDescendant(withText("Game5"))));
        // checks that Game6 is gone
        onView(withText("Game6")).check(doesNotExist());
    }

    /**
     * Allows Espresso to click the button
     */
    public static class CustomActions {
        public static ViewAction clickChildViewWithId(final int id) {
            return new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return null;
                }

                @Override
                public String getDescription() {
                    return "Click on a child view with the specified ID.";
                }

                /**
                 * Performs this action on the given view.
                 *
                 * @param uiController the controller to use to interact with the UI.
                 * @param view         the view to act upon. never null.
                 */
                @Override
                public void perform(UiController uiController, View view) {
                    View v = view.findViewById(id);
                    if (v != null) {
                        v.performClick();
                    }
                }
            };
        }
    }

}
