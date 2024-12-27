package com.emilygranville.videogamelist.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single video game and the consoles it is on
 */
public class VideoGame implements Comparable<VideoGame> {

    private String gameName;
    private List<String> consoles;

    /**
     * Constructors for VideoGame
     */

    public VideoGame() {}

    public VideoGame(String gameName) {
        this.gameName = gameName;
        createConsoleList();
    }

    public VideoGame(String gameName, List<String> consoles) {
        this.gameName = gameName;
        this.consoles = consoles;
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

    public List<String> getConsoles() {
        return consoles;
    }

    public void setConsoles(List<String> consoles) {
        this.consoles = consoles;
    }

    /**
     * Adds new console to the list
     * @param console new console to be added
     */
    public void addConsole(String console) {
        if (this.consoles == null) {
            createConsoleList();
        }
        this.consoles.add(console);
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

    @Override
    public String toString() {
        return "VideoGame{" +
                "gameName='" + this.gameName + '\'' +
                ", consoles=" + (this.consoles == null ? "NULL" : this.consoles.toString()) +
                '}';
    }
}
