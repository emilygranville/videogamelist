package com.emilygranville.videogamelist.Controller;

import android.content.Context;
import android.util.Log;


import androidx.annotation.NonNull;

import com.emilygranville.videogamelist.Model.ConsoleOrganizer;
import com.emilygranville.videogamelist.Model.VideoGame;
import com.emilygranville.videogamelist.View.ICloudDataPreservation;
import com.emilygranville.videogamelist.View.IDisplayVGView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CloudDataPreservation implements ICloudDataPreservation {

    private final ICloudDataPreservation.Listener listener;

    public CloudDataPreservation(Listener listener) {
        this.listener = listener;
    }

    /**
     * Saves the console organizer
     * @param consoleOrganizer the console organizer to save
     * @param uid the user id for the person saving
     */
    public void saveConsoleOrganizer(ConsoleOrganizer consoleOrganizer, String uid) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        CollectionReference collection = database.collection(uid);
        Set<VideoGame> videoGameSet = consoleOrganizer.compileGames();
        for (VideoGame videoGame : videoGameSet) {
            int gameId = videoGame.getGameId();
            HashMap<String, Object> gameMap = videoGame.convertToMap();
            DocumentReference document = collection.document(String.valueOf(gameId));
            document.set(gameMap).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    CloudDataPreservation.this.listener.onCloudSaveSuccess();
                } else {
                    Log.d(MainActivity.VGL, "Error saving documents: ", task.getException());
                }
            });
        }
    }

    /**
     * Loads the console organizer from cloud save
     * @param uid the user id to load from
     */
    public void loadConsoleOrganizer(String uid) {
        ConsoleOrganizer consoleOrganizer = new ConsoleOrganizer();
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        CollectionReference collection = database.collection(uid);
        collection.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(MainActivity.VGL, "document: " + document.getId() + ", info: " + document.getData());
                            try {
                                VideoGame videoGame = new VideoGame((HashMap<String, Object>) document.getData());
                                consoleOrganizer.addVideoGame(videoGame);
                            } catch (NullPointerException e) {
                                Log.i(MainActivity.VGL, "Could not load this video game");
                            }
                        }
                        CloudDataPreservation.this.listener.onCloudLoadSuccess(consoleOrganizer);
                    } else {
                        Log.d(MainActivity.VGL, "Error getting documents: ", task.getException());
                    }
                });
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
