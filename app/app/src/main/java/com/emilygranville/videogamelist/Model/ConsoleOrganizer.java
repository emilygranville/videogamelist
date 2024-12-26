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
    public void setupConsoleOraganizer(List<VideoGame> videoGameList) {
        for (VideoGame videoGame : videoGameList) {
            for (String console : videoGame.getConsoles()) {
                if (!consoleMap.containsKey(console)) {
                    List<VideoGame> consoleList = new LinkedList<VideoGame>();
                    consoleList.add(videoGame);
                    consoleMap.put(console, consoleList);
                } else {
                    List<VideoGame> consoleList = consoleMap.get(console);
                    Iterator<VideoGame> it = consoleList.iterator();
                    int index = 0;

                    while(it.hasNext()) {
                        VideoGame next = it.next();
                        if(videoGame.compareTo(next) > 0) {
                            break;
                        }
                        index++;
                    }
                    consoleList.add(index, videoGame);
                }
            }
        }
    }
}
