package com.emilygranville.videogamelist.View;

import com.emilygranville.videogamelist.Model.VideoGame;

public interface IDisplayVGView {

    /**
     * Listener so that controller classes are alerted to edits
     */
    interface Listener {

        /**
         * Deletes a video game from the ConsoleOrganizer
         * @param videoGame
         */
        public void deleteGame(VideoGame videoGame);
    }
}
