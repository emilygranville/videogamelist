package com.emilygranville.videogamelist.Controller;

import android.content.Context;

import com.emilygranville.videogamelist.Model.ConsoleOrganizer;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CloudDataPreservation implements  IDataPreservation {
    /**
     * Saves the console organizer
     *
     * @param context          context for file directory
     * @param consoleOrganizer the console organizer to save
     * @return whether it was saved properly
     */
    @Override
    public boolean saveConsoleOrganizer(Context context, ConsoleOrganizer consoleOrganizer) {
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
