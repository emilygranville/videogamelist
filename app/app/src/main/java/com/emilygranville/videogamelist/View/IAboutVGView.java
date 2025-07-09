package com.emilygranville.videogamelist.View;

import androidx.fragment.app.Fragment;

/**
 * Interface for the about page
 */
public interface IAboutVGView {
    /**
     * Add a listener for potential future use
     */
    interface Listener {
        /**
         * Alerts listener to return to display fragment
         */
        void onReturnToDisplay();

        /**
         * Restores the fragment in MainActivity
         * @param curFragment fragment to restore
         */
        void restoreAboutFragment(Fragment curFragment);
    }
}
