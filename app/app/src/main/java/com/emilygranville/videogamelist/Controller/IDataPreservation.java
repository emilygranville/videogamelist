package com.emilygranville.videogamelist.Controller;

import android.content.Context;

import com.emilygranville.videogamelist.Model.ConsoleOrganizer;

public interface IDataPreservation {
    /**
     * Saves the console organizer
     * @param context context for file directory
     * @param consoleOrganizer the console organizer to save
     * @return whether it was saved properly
     */
    boolean saveConsoleOrganizer(Context context, ConsoleOrganizer consoleOrganizer);

    /**
     * loads the package from saved
     * @param context context for file directory
     * @return the saved ConsoleOrganizer
     */
    ConsoleOrganizer loadConsoleOrganizer(Context context);
}
