package com.emilygranville.videogamelist.Controller;

import android.content.Context;
import android.util.Log;

import com.emilygranville.videogamelist.Model.ConsoleOrganizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LocalDataPreservation implements IDataPreservation {

    public static final String CONSOLE_ORGANIZER_FILE_NAME = "console organizer file name";

    /**
     * Saves the console organizer
     *
     * @param context          context for file directory
     * @param consoleOrganizer the console organizer to save
     * @return whether it was saved properly
     */
    @Override
    public boolean saveConsoleOrganizer(Context context, ConsoleOrganizer consoleOrganizer) {
        File outFile = new File(context.getFilesDir(), CONSOLE_ORGANIZER_FILE_NAME);
        try {
            FileOutputStream fileOutStream = new FileOutputStream(outFile);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
            objectOutStream.writeObject(consoleOrganizer);
            return true;
        } catch (IOException e) {
            final String msg = String.format("I/O error writing to %s", outFile);
            Log.e(MainActivity.VGL, msg);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * loads the package from saved
     *
     * @param context context for file directory
     * @return the saved ConsoleOrganizer
     */
    @Override
    public ConsoleOrganizer loadConsoleOrganizer(Context context) {
        File inFile = new File(context.getFilesDir(), CONSOLE_ORGANIZER_FILE_NAME);
        if (inFile.isFile()) {
            try {
                FileInputStream fileInStream = new FileInputStream(inFile);
                ObjectInputStream objectInStream = new ObjectInputStream(fileInStream);
                return (ConsoleOrganizer) objectInStream.readObject();
            } catch (IOException e) {
                final String msg = String.format("I/O error reading from %s", inFile);
                Log.e(MainActivity.VGL, msg);
                e.printStackTrace();
                return new ConsoleOrganizer();
            } catch (ClassNotFoundException e) {
                final String msg = String.format("Can't find class of object from %s", inFile);
                Log.e(MainActivity.VGL, msg);
                e.printStackTrace();
                return new ConsoleOrganizer();
            }
        }
        return new ConsoleOrganizer();
    }
}
