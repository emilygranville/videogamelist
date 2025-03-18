package com.emilygranville.videogamelist;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.core.internal.deps.dagger.internal.Preconditions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.emilygranville.videogamelist.Controller.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ViewGamesTest {

    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @org.junit.Test
    public void viewGames() {

        onView(ViewMatchers.withId(R.id.displayRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("Game1")))));
    }

    /**
     * From <a href="https://stackoverflow.com/a/34795431">https://stackoverflow.com/a/34795431</a>
     * @param position position of item in RecyclerView
     * @param itemMatcher item to match
     * @return
     */
    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        Preconditions.checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

}
