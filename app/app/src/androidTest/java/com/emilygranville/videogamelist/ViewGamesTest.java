package com.emilygranville.videogamelist;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.emilygranville.videogamelist.Controller.MainActivity;

public class ViewGamesTest {

    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @org.junit.Test
    public void viewGames() {
        // is the recycler view showing anything
        onView(withId(R.id.displayRecyclerView))
                .check(matches(isDisplayed()));
        // does it have 4 children
        // based on shown items only
        onView(withId(R.id.displayRecyclerView))
                .check(matches(hasMinimumChildCount(4)));

        // does it display game1
        onView(ViewMatchers.withId(R.id.displayRecyclerView))
                .check(matches(hasDescendant(withText("Game1"))));
        // scroll to game 6
        // then does it display game6
        onView(withId(R.id.displayRecyclerView))
                .perform(scrollToPosition(5))
                .check(matches(hasDescendant(withText("Game6"))));
    }
}
