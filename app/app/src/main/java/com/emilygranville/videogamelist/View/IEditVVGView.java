package com.emilygranville.videogamelist.View;

import com.emilygranville.videogamelist.Model.VideoGame;

public interface IEditVVGView {
    /**
     * Listener so that controller classes are alerted to edits
     */
    interface Listener {

        /**
         * Alerts listener to submitting the video game
         */
        public void submitGame(VideoGame videoGame);
    }
}
