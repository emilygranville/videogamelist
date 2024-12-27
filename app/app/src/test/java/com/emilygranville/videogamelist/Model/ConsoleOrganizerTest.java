package com.emilygranville.videogamelist.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class ConsoleOrganizerTest {

    VideoGame v1;
    VideoGame v2;
    VideoGame v3;

    List<VideoGame> videoGameList = new ArrayList<VideoGame>();

    ConsoleOrganizer consoleOrganizer = new ConsoleOrganizer();

    void setUp() {
        List<String> c1 = new ArrayList<String>();
        List<String> c2 = new ArrayList<String>();
        List<String> c3 = new ArrayList<String>();

        c1.add("1");
        c2.add("1");
        c3.add("1");

        c2.add("2");

        v1 = new VideoGame("B", c1);
        v2 = new VideoGame("C", c2);
        v3 = new VideoGame("F", c3);

        videoGameList.add(v1);
        videoGameList.add(v2);
        videoGameList.add(v3);
    }

    @Test
    void testSetupConsoleOrganizer() {
        setUp();

        //tests for initial set up
        consoleOrganizer.setupConsoleOrganizer(videoGameList);

        assertEquals(3, consoleOrganizer.getConsoleMap().get("1").size());
        assertEquals(1, consoleOrganizer.getConsoleMap().get("2").size());
        assertNull(consoleOrganizer.getConsoleMap().get("3"));

        List<VideoGame> expectedOrder = new LinkedList<VideoGame>();
        expectedOrder.add(v1);
        expectedOrder.add(v2);
        expectedOrder.add(v3);

        assertEquals(expectedOrder, consoleOrganizer.getConsoleMap().get("1"));

        //tests for adding an additional list to the map
        List<String> c4 = new ArrayList<String>();
        c4.add("1");
        c4.add("3");
        VideoGame newVideoGame1 = new VideoGame("A", c4);


        expectedOrder.add(0, newVideoGame1);

        List<VideoGame> newVideoGameList = new ArrayList<VideoGame>();
        newVideoGameList.add(newVideoGame1);
        consoleOrganizer.setupConsoleOrganizer(newVideoGameList);

        assertEquals(4, consoleOrganizer.getConsoleMap().get("1").size());
        assertEquals(1, consoleOrganizer.getConsoleMap().get("3").size());
        assertEquals(expectedOrder, consoleOrganizer.getConsoleMap().get("1"));

        /*
            re-test for adding another list to the map where the item should be inserted
            in the middle of the list
         */

        VideoGame newVideoGame2 = new VideoGame("D", c4);
        expectedOrder.add(3, newVideoGame2);
        newVideoGameList = new ArrayList<VideoGame>();
        newVideoGameList.add(newVideoGame2);
        consoleOrganizer.setupConsoleOrganizer(newVideoGameList);
        assertEquals(5, consoleOrganizer.getConsoleMap().get("1").size());
        assertEquals(2, consoleOrganizer.getConsoleMap().get("3").size());
        assertEquals(expectedOrder, consoleOrganizer.getConsoleMap().get("1"));
    }

    @Test
    void testToString() {
        setUp();
    }
}