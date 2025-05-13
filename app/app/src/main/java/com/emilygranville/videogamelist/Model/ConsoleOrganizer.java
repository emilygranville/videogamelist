package com.emilygranville.videogamelist.Model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Represents organization of the consoles and games with in it
 */
public class ConsoleOrganizer implements Serializable {

    private final HashMap<String, List<VideoGame>> consoleMap;


    /**
     * Constructor
     */
    public ConsoleOrganizer() {
        this.consoleMap = new HashMap<String, List<VideoGame>>();
    }

    /**
     * Getters and setters
     */
    public HashMap<String, List<VideoGame>> getConsoleMap() {
        return consoleMap;
    }

    /**
     * From a list of VideoGame objects, sets up the map
     * @param videoGameList list of the VideoGame objects
     */
    public void setupConsoleOrganizer(List<VideoGame> videoGameList) {
        for (VideoGame videoGame : videoGameList) {
            for (String consoleKey : videoGame.getConsoles()) {
                if (!consoleMap.containsKey(consoleKey)) {
                    List<VideoGame> consoleList = new LinkedList<VideoGame>();
                    consoleList.add(videoGame);
                    consoleMap.put(consoleKey, consoleList);
                } else {
                    List<VideoGame> consoleList = consoleMap.get(consoleKey);
                    assert consoleList != null;
                    Iterator<VideoGame> it = consoleList.iterator();
                    int index = 0;

                    while(it.hasNext()) {
                        VideoGame next = it.next();
                        if(videoGame.compareTo(next) <= 0) {
                            break;
                        }
                        index++;
                    }
                    consoleList.add(index, videoGame);
                }
            }
        }
    }

    /**
     * Adds a single video game to the console map
     * @param videoGame the video game to add
     */
    public void addVideoGame(VideoGame videoGame) {
        for (String consoles : videoGame.getConsoles()) {
            if (!consoleMap.containsKey(consoles)) {
                List<VideoGame> consoleList = new LinkedList<VideoGame>();
                consoleList.add(videoGame);
                consoleMap.put(consoles, consoleList);
            } else {
                List<VideoGame> consoleList = consoleMap.get(consoles);
                assert consoleList != null;
                Iterator<VideoGame> it = consoleList.iterator();
                int index = 0;

                while(it.hasNext()) {
                    VideoGame next = it.next();
                    if(videoGame.compareTo(next) <= 0) {
                        break;
                    }
                    index++;
                }
                consoleList.add(index, videoGame);
            }
        }
    }

    /**
     * Gives the list of games for a console key
     * @param console the console for which games are needed
     * @return the list of games for the console
     */
    public List<VideoGame> getGamesForConsole(String console) {
        String consoleKey = console.toUpperCase().replaceAll("\\s", "");
        return consoleMap.get(consoleKey);
    }

    /**
     * Gives the list of the consoles
     * @return the keys for the console map
     */
    public List<String> getConsoleList() {
        return new ArrayList<String>(consoleMap.keySet());
    }

    /**
     * Gives the index of the game for the given console
     * @param videoGame the video game to get the index of
     * @param console the console where the video game is in
     * @return int
     */
    public int getGameIndex(VideoGame videoGame, String console) {
        return this.consoleMap.get(console).indexOf(videoGame);
    }

    /**
     * Deletes the given game from every console
     * @param videoGame the video game to delete
     */
    public void deleteGame(VideoGame videoGame) {
        List<String> consoles = videoGame.getConsoles();
        for (String console : consoles) {
            List<VideoGame> gameList = this.consoleMap.get(console);
            Objects.requireNonNull(gameList).remove(videoGame);
        }
    }

    /**
     * Deletes the given game from every console based on game ID
     * @param videoGameID the ID of the game to delete
     */
    public void deleteGame(int videoGameID) {
        List<String> consoles = getConsoleList();
        for (String console : consoles) {
            List<VideoGame> gameList = this.consoleMap.get(console);
            Iterator<VideoGame> it = gameList.iterator();
            while(it.hasNext()) {
                VideoGame game = (VideoGame) it.next();
                if (game.getGameId() == videoGameID) {
                    it.remove();
                }
            }
        }
    }

    /**
     * Edits a game by deleting the original version
     * (if there is one) and adding the new version
     * @param videoGame the video game to edit
     */
    public void editGame(VideoGame videoGame) {
        deleteGame(videoGame.getGameId());
        addVideoGame(videoGame);
    }

    /**
     * String representation of ConsoleOrganizer
     * @return the string representation of ConsoleOrganizer
     */
    @NonNull
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("ConsoleOrganizer:\nMap Keys: " + consoleMap.keySet() + "\n");

        for (String key: consoleMap.keySet()) {
            result.append("Console Name: ").append(key).append("\n").append("Games:\n");
            for (VideoGame game : Objects.requireNonNull(consoleMap.get(key))) {
                    result.append(game.getGameName()).append("\n");
            }
        }

        return result.toString();
    }
}
