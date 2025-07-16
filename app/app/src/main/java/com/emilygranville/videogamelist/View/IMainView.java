package com.emilygranville.videogamelist.View;

import android.view.View;

import androidx.fragment.app.Fragment;

public interface IMainView {
    /**
     * Gets the highest root view of the screen
     * @return highest root view of the screen
     */
    View getRootView();

    /**
     * Displays the fragment
     * @param fragment the fragment to display
     * @param allowBack whether back button can return to fragment
     * @param name the name of the fragment
     */
    void displayFragment(Fragment fragment, boolean allowBack, String name);

    /**
     * Displays a toast message
     * @param message message to make a toast
     */
    void displayToast(String message);

    /**
     * Listener to provide controller with updates
     */
    interface Listener {

    }
}
