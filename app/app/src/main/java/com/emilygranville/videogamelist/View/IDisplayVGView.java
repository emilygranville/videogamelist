package com.emilygranville.videogamelist.View;

import com.emilygranville.videogamelist.Model.VideoGame;

public interface IDisplayVGView {

    /**
     * Listener so that controller classes are alerted to edits
     */
    interface Listener {

        /**
         * Alerts listener to the video game needing deleting
         * @param videoGame that needs to be deleted
         */
        public void deleteGame(VideoGame videoGame);

        /**
         * Alerts listener to the video game needing editing
         * @param videoGame that needs to be updated
         */
        public void editGame(VideoGame videoGame);
    }
}
