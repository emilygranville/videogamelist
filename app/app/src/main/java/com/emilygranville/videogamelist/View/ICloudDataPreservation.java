package com.emilygranville.videogamelist.View;

import com.emilygranville.videogamelist.Model.ConsoleOrganizer;

public interface ICloudDataPreservation {
    interface Listener {
        /**
         * Alerts the listener to successful save
         */
        void onCloudSaveSuccess();

        /**
         * Alerts the listener to successful load
         */
        void onCloudLoadSuccess(ConsoleOrganizer consoleOrganizer);
    }
}
