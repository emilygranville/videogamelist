package com.emilygranville.videogamelist.View;

import androidx.fragment.app.Fragment;

import com.emilygranville.videogamelist.Model.VideoGame;

public interface IEditVVGView {
    /**
     * Listener so that controller classes are alerted to edits
     */
    interface Listener {

        /**
         * Alerts listener to submitting the video game
         * @param videoGame the video game to edit/create
         */
        void submitGame(VideoGame videoGame);

        /**
         * Restores the fragment in MainActivity
         * @param curFragment fragment to restore
         */
        void restoreEditFragment(Fragment curFragment);
    }

    /**
     * Shows the new console in the list of consoles
     * @param consoleName name of console to show
     */
    void showNewConsole(String consoleName);
}
