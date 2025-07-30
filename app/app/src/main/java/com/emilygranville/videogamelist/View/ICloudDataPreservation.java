package com.emilygranville.videogamelist.View;

import com.emilygranville.videogamelist.Model.ConsoleOrganizer;
import com.google.firebase.Timestamp;

public interface ICloudDataPreservation {
    interface Listener {
        /**
         * Alerts the listener to successful save
         */
        void onCloudSaveSuccess(Timestamp timestamp);

        /**
         * Alerts the listener to successful load
         */
        void onCloudLoadSuccess(ConsoleOrganizer consoleOrganizer);

        /**
         * Alerts the listener to cloud failure
         */
        void onCloudFailure();
    }
}
