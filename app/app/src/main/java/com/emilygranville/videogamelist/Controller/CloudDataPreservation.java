package com.emilygranville.videogamelist.Controller;

import android.util.Log;

import com.emilygranville.videogamelist.Model.ConsoleOrganizer;
import com.emilygranville.videogamelist.Model.VideoGame;
import com.emilygranville.videogamelist.View.ICloudDataPreservation;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CloudDataPreservation implements ICloudDataPreservation {

    public static final String NEXT_GAME_ID_KEY = "nextGameID";
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
        Timestamp timestamp = new Timestamp(new Date());
        Log.i(MainActivity.VGL, String.valueOf(timestamp));

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        CollectionReference collection = database.collection(uid);
        Set<VideoGame> videoGameSet = consoleOrganizer.compileGames();
        for (VideoGame videoGame : videoGameSet) {
            int gameId = videoGame.getGameId();
            HashMap<String, Object> gameMap = videoGame.convertToMap();
            gameMap.put("timestamp", timestamp);
            DocumentReference document = collection.document(String.valueOf(gameId));
            document.set(gameMap).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    deleteUserCollection(uid, timestamp);
                    CloudDataPreservation.this.listener.onCloudSaveSuccess(timestamp);
                } else {
                    Log.d(MainActivity.VGL, "Error saving documents: ", task.getException());
                    CloudDataPreservation.this.listener.onCloudFailure();
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

        //checkForUser(uid);

        CollectionReference collection = database.collection(uid);
        collection.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String docId = document.getId();
                            Log.d(MainActivity.VGL, "document: " + document.getId() +
                                    ", info: " + document.getData());
                            if (!docId.equals(NEXT_GAME_ID_KEY)) {
                                try {
                                    VideoGame videoGame = new VideoGame((HashMap<String, Object>)
                                            document.getData());
                                    consoleOrganizer.addVideoGame(videoGame);
                                } catch (NullPointerException e) {
                                    Log.i(MainActivity.VGL, "Could not load this video game");
                                }
                            } else {
                                VideoGame.setNextId((int) (long) document.getData().get(NEXT_GAME_ID_KEY));
                            }
                        }
                        CloudDataPreservation.this.listener.onCloudLoadSuccess(consoleOrganizer);
                    } else {
                        Log.d(MainActivity.VGL, "Error getting documents: ",
                                task.getException());
                        CloudDataPreservation.this.listener.onCloudFailure();
                    }
                });
    }

    /**
     * Ensures there is a user saved in the user section
     * @param uid user id to check for
     */
    private void checkForUser(String uid) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference doc = database.collection("users").document(uid);
        doc.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.d(MainActivity.VGL, "DocumentSnapshot data: " + document.getData());
                } else {
                    Log.d(MainActivity.VGL, "No such document");
                    createUserDoc(uid);
                }
            } else {
                Log.d(MainActivity.VGL, "get failed with ", task.getException());
            }
        });
    }

    /**
     * Creates a doc to correspond with the new user
     * @param uid user's id
     */
    public void createUserDoc(String uid) {
        Log.i(MainActivity.VGL, "creating user doc");
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        CollectionReference userCollection = database.collection("users");
        DocumentReference userDocument = userCollection.document(uid);
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("user id", uid);
        userDocument.set(userMap);

        CollectionReference collection = database.collection(uid);
        DocumentReference document = collection.document(NEXT_GAME_ID_KEY);
        HashMap<String, Object> gameIDMap = new HashMap<String, Object>();
        gameIDMap.put(NEXT_GAME_ID_KEY, VideoGame.getNextId());
        document.set(gameIDMap);
    }

    /**
     * Deletes a user from the list of users in the database
     * @param uid user id
     */
    public void deleteUserDoc(String uid) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        CollectionReference collection = database.collection(uid);
        collection.document(NEXT_GAME_ID_KEY).delete();
        CollectionReference usersCollection = database.collection("users");
        usersCollection.document(uid).delete();
    }

    public void deleteUserCollection(String uid, Timestamp timestamp) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        Query query = database.collection(uid).whereLessThan("timestamp", timestamp);
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    document.getReference().delete();
                }
            } else {
                Log.d(MainActivity.VGL, "Error getting documents: ",
                        task.getException());
                CloudDataPreservation.this.listener.onCloudFailure();
            }
        });
    }
}
