package com.emilygranville.videogamelist.Controller;

import android.content.Context;

import com.emilygranville.videogamelist.Model.ConsoleOrganizer;
import com.emilygranville.videogamelist.Model.VideoGame;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CloudDataPreservation {
    /**
     * Saves the console organizer
     *
     * @param context context for file directory
     * @param consoleOrganizer the console organizer to save
     * @return whether it was saved properly
     */
    public boolean saveConsoleOrganizer(Context context, ConsoleOrganizer consoleOrganizer, String uid) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        CollectionReference collection = database.collection(uid);
        Set<VideoGame> videoGameSet = consoleOrganizer.compileGames();
        for (VideoGame videoGame : videoGameSet) {
            int gameId = videoGame.getGameId();
            HashMap<String, Object> gameMap = videoGame.convertToMap();
            DocumentReference document = collection.document(String.valueOf(gameId));
            document.set(gameMap);
        }
        return false;
    }

    /**
     * loads the package from saved
     *
     * @param context context for file directory
     * @return the saved ConsoleOrganizer
     */
    public ConsoleOrganizer loadConsoleOrganizer(Context context) {
        return null;
    }

    /**
     * Creates a doc to correspond with the new user
     * @param uid user's id
     */
    public void createUserDoc(String uid) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        CollectionReference collection = database.collection("users");
        DocumentReference document = collection.document(uid);
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("user id", uid);
        document.set(userMap);
    }
}
