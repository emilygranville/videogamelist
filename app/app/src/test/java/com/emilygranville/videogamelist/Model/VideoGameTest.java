package com.emilygranville.videogamelist.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class VideoGameTest {

    List<String> c1 = new ArrayList<String>();
    List<String> c2 = new ArrayList<String>();
    List<String> c3 = new ArrayList<String>();

    VideoGame v1;
    VideoGame v2;
    VideoGame v3;

    /**
     * Sets up the above variables for the tests
     */
    void setUp() {
        v1 = new VideoGame();

        v2 = new VideoGame("B");

        c3.add("Switch");
        c3.add("XBox");
        v3 = new VideoGame("C", c3);

        assertNull(v1.getGameName());
        assertEquals("B", v2.getGameName());
        assertEquals("C", v3.getGameName());

        v1.setGameName("A");

        assertEquals("A", v1.getGameName());

    }

    @Test
    void addConsole() {
        setUp();

        List<String> c1Copy = new ArrayList<String>();
        List<String> c2Copy = new ArrayList<String>();
        List<String> c3Copy = new ArrayList<String>();

        assertNull(v1.getConsoles());

        c3Copy.add("Switch");
        c3Copy.add("XBox");

        assertEquals(c2Copy, v2.getConsoles());
        assertEquals(c3Copy, v3.getConsoles());

        c1Copy.add("GameCube");
        c2Copy.add("GameCube");
        c3Copy.add("GameCube");

        v1.addConsole("GameCube");
        v2.addConsole("GameCube");
        v3.addConsole("GameCube");

        assertEquals(c1Copy, v1.getConsoles());
        assertEquals(c2Copy, v2.getConsoles());
        assertEquals(c3Copy, v3.getConsoles());
    }

    @Test
    void compareTo() {
        setUp();

        assertTrue(0 > v1.compareTo(v2));
        assertTrue(0 > v1.compareTo(v3));
        assertTrue(0 > v2.compareTo(v3));

        assertTrue(0 < v2.compareTo(v1));
        assertTrue(0 < v3.compareTo(v2));
        assertTrue(0 < v3.compareTo(v1));

        assertEquals(0, v1.compareTo(v1));
        assertEquals(0, v2.compareTo(v2));
        assertEquals(0, v3.compareTo(v3));

        VideoGame v1Copy = new VideoGame("A");
        VideoGame v2Copy = new VideoGame("B");
        VideoGame v3Copy = new VideoGame("C");

        assertEquals(0, v1.compareTo(v1Copy));
        assertEquals(0, v2.compareTo(v2Copy));
        assertEquals(0, v3.compareTo(v3Copy));
    }

    @Test
    void testToString() {
        setUp();

        assertEquals("VideoGame{gameName='A', consoles=NULL}", v1.toString());
        assertEquals("VideoGame{gameName='B', consoles=[]}", v2.toString());
        assertEquals("VideoGame{gameName='C', consoles=[Switch, XBox]}", v3.toString());


    }
}