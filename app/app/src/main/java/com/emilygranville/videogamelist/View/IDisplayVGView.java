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
         * @param curConsole current displayed console list
         */
        void deleteGame(VideoGame videoGame, String curConsole);

        /**
         * Alerts listener to the video game needing editing
         * @param videoGame that needs to be updated
         */
        void editGame(VideoGame videoGame);

        void switchConsole(String console);
    }

    void updateDeletedItem(int index);

}
