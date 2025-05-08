package com.emilygranville.videogamelist.View;

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
    }
}
