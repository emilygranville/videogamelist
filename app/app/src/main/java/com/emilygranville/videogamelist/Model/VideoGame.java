package com.emilygranville.videogamelist.Model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a single video game and the consoles it is on
 * @noinspection Convert2Diamond
 */
public class VideoGame implements Comparable<VideoGame>, Serializable {

    private static int NEXT_ID;
    private int gameId;
    private String gameName;
    private double price;
    private List<String> consoles;
    private boolean isFavorite;

    /**
     * Constructors for VideoGame
     */

    public VideoGame() {}

    public VideoGame(String gameName) {
        this.gameName = gameName;
        createConsoleList();
        this.gameId = ++NEXT_ID;
        this.isFavorite = false;
    }

    public VideoGame(String gameName, String console) {
        this.gameName = gameName;
        createConsoleList();
        addConsole(console);
        this.gameId = ++NEXT_ID;
        this.isFavorite = false;
    }

    public VideoGame(String gameName, double price, String console) {
        this.gameName = gameName;
        this.price = price;
        createConsoleList();
        addConsole(console);
        this.gameId = ++NEXT_ID;
        this.isFavorite = false;
    }

    public VideoGame(String gameName, List<String> consoles) {
        this.gameName = gameName;
        this.consoles = consoles;
        this.gameId = ++NEXT_ID;
        this.isFavorite = false;
    }

    public VideoGame(String gameName, double price, List<String> consoles) {
        this.gameName = gameName;
        this.price = price;
        this.consoles = consoles;
        this.gameId = ++NEXT_ID;
        this.isFavorite = false;
    }

    public VideoGame(String gameName, double price, List<String> consoles, int gameId) {
        this.gameName = gameName;
        this.price = price;
        this.consoles = consoles;
        this.gameId = gameId;
        this.isFavorite = false;
    }

    public VideoGame(String gameName, List<String> consoles, int gameId) {
        this.gameName = gameName;
        this.consoles = consoles;
        this.gameId = gameId;
        this.isFavorite = false;
    }

    public VideoGame(String gameName, List<String> consoles, boolean isFavorite) {
        this.gameName = gameName;
        this.consoles = consoles;
        this.gameId = ++NEXT_ID;
        this.isFavorite = isFavorite;
    }

    public VideoGame(String gameName, List<String> consoles, int gameId, boolean isFavorite) {
        this.gameName = gameName;
        this.consoles = consoles;
        this.gameId = gameId;
        this.isFavorite = isFavorite;
    }

    public VideoGame(String gameName, double price, List<String> consoles, boolean isFavorite) {
        this.gameName = gameName;
        this.price = price;
        this.consoles = consoles;
        this.gameId = ++NEXT_ID;
        this.isFavorite = isFavorite;
    }

    public VideoGame(String gameName, double price, List<String> consoles, boolean isFavorite, int gameId) {
        this.gameName = gameName;
        this.price = price;
        this.consoles = consoles;
        this.gameId = gameId;
        this.isFavorite = isFavorite;
    }

    public VideoGame(HashMap<String, Object> videoGameMap) throws NullPointerException {
        // for some reason it needs to be cast as a long before it can be cast as an int
        this.gameId = (int) (long) videoGameMap.get("gameID");
        this.gameName = (String) videoGameMap.get("gameName");
        this.price = (double) videoGameMap.get("price");
        this.consoles = (List) videoGameMap.get("consoles");
        this.isFavorite = (boolean) videoGameMap.get("isFavorite");
    }

    /**
     * Creates a separate function to set up from a null list
     * so that if it changes to not an array list
     * it updates in only one place
     */
    private void createConsoleList() {
        this.consoles = new ArrayList<String>();
    }

    /**
     * Getters and setter for gameName and consoles
     */

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getConsoles() {
        return consoles;
    }

    public void setConsoles(List<String> consoles) {
        this.consoles = consoles;
    }

    public int getGameId() {
        return gameId;
    }

    public static int getNextId() {
        return NEXT_ID;
    }

    public static void setNextId(int id) {
        NEXT_ID = id;
    }

    public static void resetNextID() {
        NEXT_ID = 0;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void switchFavorite() {
        isFavorite = !isFavorite;
    }

    /**
     * Adds new console to the list
     * @param console new console to be added
     */
    public void addConsole(String console) {
        if (this.consoles == null) {
            createConsoleList();
        }
        //makes the consoles lower case and removes whitespace
        this.consoles.add(console.toUpperCase().replaceAll("\\s", ""));
    }

    /**
     * Converts the VideoGame object into a HashMap
     * object you can save in firestore database
     * @return HashMap of the VideoGame
     */
    public HashMap<String, Object> convertToMap() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("gameID", this.gameId);
        map.put("gameName", this.gameName);
        map.put("price", this.price);
        map.put("consoles", this.consoles);
        map.put("isFavorite", this.isFavorite);
        return map;
    }


    /**
     * Compares two VideoGames using the name of the video game
     * @param o the object to be compared.
     * @return the result of the comparison of the names
     */
    @Override
    public int compareTo(VideoGame o) {
        return this.gameName.compareTo(o.getGameName());
    }

    /**
     * Checks if two games are equal based on ID
     * @param o second VideoGame to compare to first
     * @return whether or not they're the same
     */
    public boolean equals(VideoGame o) {
        return this.gameId == o.getGameId();
    }

    /**
     * String representation of VideoGame
     * @return the string representation of VideoGame
     */
    @NonNull
    @Override
    public String toString() {
        return "VideoGame{" +
                "gameName='" + this.gameName + '\'' +
                ", price=" + this.price +
                ", consoles=" + (this.consoles == null ? "NULL" : this.consoles.toString()) +
                '}';
    }
}
