package com.emilygranville.videogamelist.Model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents organization of the consoles and games with in it
 */
public class ConsoleOrganizer {

    private HashMap<String, List<VideoGame>> consoleMap;


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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("ConsoleOrganizer:consoleMapKeys=" + consoleMap.keySet() + "\n");

        for (String key: consoleMap.keySet()) {
            result.append("Console: ").append(key).append("\n").append("Games:\n");
            for (VideoGame game : consoleMap.get(key)) {
                    result.append(game.getGameName()).append("\n");
            }
        }

        return result.toString();
    }
}
