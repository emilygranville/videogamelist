package com.emilygranville.videogamelist.View;

public interface IAddConsoleDialog {
    interface Listener {

        /**
         * Alerts listener to submitting the video game
         * @param consoleName name of the new console
         */
        void onSubmitNewConsole(String consoleName);
    }
}
