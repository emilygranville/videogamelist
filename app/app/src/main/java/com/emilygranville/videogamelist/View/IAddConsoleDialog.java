package com.emilygranville.videogamelist.View;

import com.emilygranville.videogamelist.Model.VideoGame;

public interface IAddConsoleDialog {
    interface Listener {

        /**
         * Alerts listener to submitting the video game
         */
        void submitNewConsole(String consoleName);
    }
}
