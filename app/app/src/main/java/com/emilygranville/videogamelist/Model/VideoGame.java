package com.emilygranville.videogamelist.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single video game and the consoles it is on
 */
public class VideoGame implements Comparable<VideoGame> {

    private static int NEXT_ID;
    private int gameId;
    private String gameName;
    private double price;
    private List<String> consoles;

    /**
     * Constructors for VideoGame
     */

    public VideoGame() {}

    public VideoGame(String gameName) {
        this.gameName = gameName;
        createConsoleList();
        this.gameId = ++NEXT_ID;
    }

    public VideoGame(String gameName, String console) {
        this.gameName = gameName;
        createConsoleList();
        addConsole(console);
        this.gameId = ++NEXT_ID;
    }

    public VideoGame(String gameName, double price, String console) {
        this.gameName = gameName;
        this.price = price;
        createConsoleList();
        addConsole(console);
        this.gameId = ++NEXT_ID;
    }

    public VideoGame(String gameName, List<String> consoles) {
        this.gameName = gameName;
        this.consoles = consoles;
        this.gameId = ++NEXT_ID;
    }

    public VideoGame(String gameName, double price, List<String> consoles) {
        this.gameName = gameName;
        this.price = price;
        this.consoles = consoles;
        this.gameId = ++NEXT_ID;
    }

    public VideoGame(String gameName, double price, List<String> consoles, int gameId) {
        this.gameName = gameName;
        this.price = price;
        this.consoles = consoles;
        this.gameId = gameId;
    }

    public VideoGame(String gameName, List<String> consoles, int gameId) {
        this.gameName = gameName;
        this.price = price;
        this.consoles = consoles;
        this.gameId = gameId;
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

    public void setPrice(double price) {
        this.price = price;
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

    public static void resetNextID() {
        NEXT_ID = 0;
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
    @Override
    public String toString() {
        return "VideoGame{" +
                "gameName='" + this.gameName + '\'' +
                ", price=" + this.price +
                ", consoles=" + (this.consoles == null ? "NULL" : this.consoles.toString()) +
                '}';
    }
}
