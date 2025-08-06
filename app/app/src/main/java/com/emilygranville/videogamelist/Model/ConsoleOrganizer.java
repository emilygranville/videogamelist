package com.emilygranville.videogamelist.Model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Represents organization of the consoles and games with in it
 * @noinspection Convert2Diamond
 */
public class ConsoleOrganizer implements Serializable {

    private final HashMap<String, List<VideoGame>> consoleMap;
    private final List<VideoGame> favorites;

    /**
     * Constructor
     */
    public ConsoleOrganizer() {
        this.consoleMap = new HashMap<String, List<VideoGame>>();
        this.favorites = new LinkedList<VideoGame>();
    }

    /**
     * Getters and setters
     */
    public HashMap<String, List<VideoGame>> getConsoleMap() {
        return consoleMap;
    }

    public List<VideoGame> getFavorites() {
        return this.favorites;
    }

    /**
     * Adds a game to the given list in alphabetical order
     * @param videoGame game to add to the list
     * @param list the list to add the game to
     */
    private static void addGameToAlphaList(VideoGame videoGame, List<VideoGame> list) {
        assert list != null;
        Iterator<VideoGame> it = list.iterator();
        int index = 0;

        while(it.hasNext()) {
            VideoGame next = it.next();
            if(videoGame.compareTo(next) <= 0) {
                break;
            }
            index++;
        }
        list.add(index, videoGame);
    }

    /**
     * From a list of VideoGame objects, sets up the map
     * @param videoGameList list of the VideoGame objects
     */
    public void setupConsoleOrganizer(List<VideoGame> videoGameList) {
        for (VideoGame videoGame : videoGameList) {
            addVideoGame(videoGame);
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
                addGameToAlphaList(videoGame, Objects.requireNonNull(
                        consoleMap.get(consoles)));
            }
        }
        addToFavorites(videoGame);
    }

    /**
     * Gives the list of games for a console key
     * @param console the console for which games are needed
     * @return the list of games for the console
     */
    public List<VideoGame> getGamesForConsole(String console) {
        String consoleKey = console.toUpperCase();
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
        return Objects.requireNonNull(
                this.consoleMap.get(console)).indexOf(videoGame);
    }

    /**
     * Deletes the given game from every console
     * @param videoGame the video game to delete
     */
    public void deleteGame(VideoGame videoGame) {
        List<String> consoles = videoGame.getConsoles();
        for (int i = 0; i < consoles.size(); i++) {
            String console = consoles.get(i);
            List<VideoGame> gameList = this.consoleMap.get(console);
            if (gameList != null) {
                gameList.remove(videoGame);
                if (Objects.requireNonNull(this.consoleMap.get(console)).isEmpty()) {
                    this.consoleMap.remove(console);
                    i--;
                }
            }
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
            assert gameList != null;
            gameList.removeIf(game -> game.getGameId() == videoGameID);
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
     * Adds the video game to favorite list
     * @param videoGame video game to add to favorites
     */
    protected void addToFavorites(VideoGame videoGame) {
        if (videoGame.getIsFavorite()) {
            removeFromFavorites(videoGame);
            addGameToAlphaList(videoGame, this.favorites);
        }
    }

    /**
     * Removes a potential duplicate game from the list of favorites
     * @param videoGame game to remove
     */
    private void removeFromFavorites(VideoGame videoGame) {
        int index = -1;
        for (int i = 0; i < this.favorites.size(); i++) {
            if (videoGame.getGameId() == this.favorites.get(i).getGameId()) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            this.favorites.remove(index);
        }
        //this.favorites.removeIf(game -> game.getGameId() == videoGame.getGameId());
    }

    /**
     * Creates a set of all the games (each game only appears once)
     * @return set of all games
     */
    public LinkedHashSet<VideoGame> compileGames() {
        LinkedHashSet<VideoGame> allGames = new LinkedHashSet<VideoGame>();
        for (String console : getConsoleList()) {
            for (VideoGame game : this.consoleMap.get(console)) {
                allGames.add(game);
            }
        }
        return allGames;
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
