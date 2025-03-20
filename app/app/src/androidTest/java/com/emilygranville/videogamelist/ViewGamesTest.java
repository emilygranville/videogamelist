package com.emilygranville.videogamelist;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.emilygranville.videogamelist.Controller.MainActivity;

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

}
