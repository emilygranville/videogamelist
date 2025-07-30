package com.emilygranville.videogamelist.View;

import androidx.fragment.app.Fragment;

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
        void onDeleteGame(VideoGame videoGame, String curConsole);

        /**
         * Alerts listener to the video game needing editing
         * @param videoGame that needs to be updated
         */
        void onEditGame(VideoGame videoGame);

        /**
         * Alerts listener to wanting to switch console viewed
         * @param console new console to view
         * @param scrollLeft position in scroll of the console
         */
        void onSwitchConsole(String console, int scrollLeft);

        /**
         * Restores the fragment in MainActivity
         * @param curFragment fragment to restore
         */
        void restoreDisplayFragment(Fragment curFragment);

        /**
         * Alerts listener to toggling favorite
         * @param videoGame game to change favorite
         */
        void onFavorite(VideoGame videoGame);

        /*
         * MENUS METHODS
         */

        /**
         * Alerts listener to adding a new game
         */
        void onAddNewGame();

        /**
         * Alerts listener to saving on device
         */
        boolean onDeviceSave();

        /**
         * Alerts listener to loading from device
         */
        void onDeviceLoad();

        /**
         * Alerts listener to saving to cloud
         */
        boolean onCloudSave();

        /**
         * Alerts listener to loading from cloud
         */
        boolean onCloudLoad();

        /**
         * Alerts listener to show the account management page
         */
        void onDisplayAMPage();

        /**
         * Alerts listener to show the about page
         */
        void onDisplayAboutPage();
    }

    /**
     * Updates the view for the deleted item
     * @param index index the item had been
     */
    void updateDeletedItem(int index);
}
