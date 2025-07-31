package com.emilygranville.videogamelist.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class ConsoleOrganizerTest {

    VideoGame v1;
    VideoGame v2;
    VideoGame v3;

    List<VideoGame> videoGameList = new ArrayList<VideoGame>();

    ConsoleOrganizer consoleOrganizer = new ConsoleOrganizer();

    void setUp() {

        VideoGame.resetNextID();

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

        consoleOrganizer.setupConsoleOrganizer(videoGameList);
    }

    @Test
    void testSetupConsoleOrganizer() {
        setUp();

        //tests for initial set up
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

        //adds a video game to the end of the list
        VideoGame newVideoGame3 = new VideoGame("Z", c4);
        expectedOrder.add(newVideoGame3);
        newVideoGameList = new ArrayList<VideoGame>();
        newVideoGameList.add(newVideoGame3);
        consoleOrganizer.setupConsoleOrganizer(newVideoGameList);
        assertEquals(6, consoleOrganizer.getConsoleMap().get("1").size());
        assertEquals(3, consoleOrganizer.getConsoleMap().get("3").size());
        assertEquals(expectedOrder, consoleOrganizer.getConsoleMap().get("1"));
    }

    @Test
    void testAddVideoGame() {
        setUp();

        assertEquals(3, consoleOrganizer.getConsoleMap().get("1").size());
        assertEquals(1, consoleOrganizer.getConsoleMap().get("2").size());
        assertNull(consoleOrganizer.getConsoleMap().get("3"));
        List<VideoGame> expectedOrder = new LinkedList<VideoGame>();
        expectedOrder.add(v1);
        expectedOrder.add(v2);
        expectedOrder.add(v3);
        assertEquals(expectedOrder, consoleOrganizer.getConsoleMap().get("1"));

        List<String> c4 = new ArrayList<String>();
        c4.add("1");
        c4.add("3");

        //adds one video game to beginning of list
        VideoGame newVideoGame1 = new VideoGame("A", c4);
        expectedOrder.add(0, newVideoGame1);
        consoleOrganizer.addVideoGame(newVideoGame1);

        assertEquals(4, consoleOrganizer.getConsoleMap().get("1").size());
        assertEquals(1, consoleOrganizer.getConsoleMap().get("3").size());
        assertEquals(expectedOrder, consoleOrganizer.getConsoleMap().get("1"));

        //adds video game to middle of the list
        VideoGame newVideoGame2 = new VideoGame("D", c4);
        expectedOrder.add(3, newVideoGame2);
        consoleOrganizer.addVideoGame(newVideoGame2);

        assertEquals(5, consoleOrganizer.getConsoleMap().get("1").size());
        assertEquals(2, consoleOrganizer.getConsoleMap().get("3").size());
        assertEquals(expectedOrder, consoleOrganizer.getConsoleMap().get("1"));

        //adds video game to middle of the list
        VideoGame newVideoGame3 = new VideoGame("D", c4);
        expectedOrder.add(3, newVideoGame3);
        consoleOrganizer.addVideoGame(newVideoGame3);

        assertEquals(6, consoleOrganizer.getConsoleMap().get("1").size());
        assertEquals(3, consoleOrganizer.getConsoleMap().get("3").size());
        assertEquals(expectedOrder, consoleOrganizer.getConsoleMap().get("1"));
    }


    @Test
    void testGetGamesForConsole() {
        setUp();

        assertEquals(3, consoleOrganizer.getGamesForConsole("1").size());
        assertEquals(1, consoleOrganizer.getGamesForConsole("2").size());
        assertNull(consoleOrganizer.getGamesForConsole("3"));

        List<VideoGame> expectedOrder = new LinkedList<VideoGame>();
        expectedOrder.add(v1);
        expectedOrder.add(v2);
        expectedOrder.add(v3);
        assertEquals(expectedOrder, consoleOrganizer.getGamesForConsole("1"));

        List<String> c4 = new ArrayList<String>();
        c4.add("1");
        c4.add("3");

        //beginning
        VideoGame newVideoGame1 = new VideoGame("A", c4);
        expectedOrder.add(0, newVideoGame1);
        consoleOrganizer.addVideoGame(newVideoGame1);
        assertEquals(4, consoleOrganizer.getGamesForConsole("1").size());
        assertEquals(1, consoleOrganizer.getGamesForConsole("3").size());
        assertEquals(expectedOrder, consoleOrganizer.getGamesForConsole("1"));

        //middle
        VideoGame newVideoGame2 = new VideoGame("D", c4);
        expectedOrder.add(3, newVideoGame2);
        consoleOrganizer.addVideoGame(newVideoGame2);
        assertEquals(5, consoleOrganizer.getGamesForConsole("1").size());
        assertEquals(2, consoleOrganizer.getGamesForConsole("3").size());
        assertEquals(expectedOrder, consoleOrganizer.getGamesForConsole("1"));

        //end
        VideoGame newVideoGame3 = new VideoGame("Z", c4);
        expectedOrder.add(newVideoGame3);
        consoleOrganizer.addVideoGame(newVideoGame3);
        assertEquals(6, consoleOrganizer.getGamesForConsole("1").size());
        assertEquals(3, consoleOrganizer.getGamesForConsole("3").size());
        assertEquals(expectedOrder, consoleOrganizer.getGamesForConsole("1"));
    }

    @Test
    void testGetConsoleList() {
        setUp();

        List<String> consoleList = this.consoleOrganizer.getConsoleList();

        assertEquals(2, consoleList.size());
        assertEquals(Arrays.asList("1", "2"), consoleList);

        this.consoleOrganizer.addVideoGame(new VideoGame("H", "3"));
        consoleList = this.consoleOrganizer.getConsoleList();

        assertEquals(3, consoleList.size());
        assertEquals(Arrays.asList("1", "2", "3"), consoleList);
    }

    @Test
    void testGetGameIndex() {
        setUp();
        assertEquals(0, consoleOrganizer.getGameIndex(v1, "1"));
        assertEquals(1, consoleOrganizer.getGameIndex(v2, "1"));
        assertEquals(2, consoleOrganizer.getGameIndex(v3, "1"));

        assertEquals(0, consoleOrganizer.getGameIndex(v2, "2"));
    }

    @Test
    void testDeleteGame1() {
        setUp();
        this.consoleOrganizer.deleteGame(v2);

        assertTrue(consoleOrganizer.getConsoleMap().get("1").contains(v1));
        assertFalse(consoleOrganizer.getConsoleMap().get("1").contains(v2));
        assertTrue(consoleOrganizer.getConsoleMap().get("1").contains(v3));
        assertNull(consoleOrganizer.getConsoleMap().get("2"));

        this.consoleOrganizer.deleteGame(v3);
        assertFalse(consoleOrganizer.getConsoleMap().get("1").contains(v3));
    }

    @Test
    void testDeleteGame2() {
        setUp();
        this.consoleOrganizer.deleteGame(v2.getGameId());

        assertTrue(consoleOrganizer.getConsoleMap().get("1").contains(v1));
        assertFalse(consoleOrganizer.getConsoleMap().get("1").contains(v2));
        assertFalse(consoleOrganizer.getConsoleMap().get("2").contains(v2));
        assertTrue(consoleOrganizer.getConsoleMap().get("1").contains(v3));

        this.consoleOrganizer.deleteGame(v3.getGameId());
        assertFalse(consoleOrganizer.getConsoleMap().get("1").contains(v3));
    }

    @Test
    void testEditGame() {
        setUp();

        List<String> v1CloneConsoleList = new ArrayList<>();
        v1CloneConsoleList.add("1");
        VideoGame v1Clone = new VideoGame("B", v1CloneConsoleList, 1);

        assertTrue(v1.equals(v1Clone));

        v1Clone.setConsoles(new ArrayList<>());

        this.consoleOrganizer.editGame(v1Clone);
        this.consoleOrganizer.getConsoleList();

        assertFalse(this.consoleOrganizer.getConsoleMap().get("1").contains(v1Clone));
    }

    @Test
    void testAddToFavorites() {
        setUp();
        this.consoleOrganizer.addToFavorites(v2);
        assertEquals(0, this.consoleOrganizer.getFavorites().size());
        this.consoleOrganizer.addToFavorites(v1);
        assertEquals(0, this.consoleOrganizer.getFavorites().size());

        v1.setFavorite(true);
        v2.setFavorite(true);
        this.consoleOrganizer.addToFavorites(v2);
        assertEquals("C", this.consoleOrganizer.getFavorites().get(0).getGameName());
        assertEquals(1, this.consoleOrganizer.getFavorites().size());

        this.consoleOrganizer.addToFavorites(v1);
        assertEquals("B", this.consoleOrganizer.getFavorites().get(0).getGameName());
        assertEquals("C", this.consoleOrganizer.getFavorites().get(1).getGameName());
        assertEquals(2, this.consoleOrganizer.getFavorites().size());


        List<String> c1 = new ArrayList<String>();
        c1.add("1");
        VideoGame videoGame = new VideoGame("A", c1, 1, true);
        this.consoleOrganizer.addToFavorites(videoGame);
        assertEquals("A", this.consoleOrganizer.getFavorites().get(0).getGameName());
        assertEquals("C", this.consoleOrganizer.getFavorites().get(1).getGameName());
        assertEquals(2, this.consoleOrganizer.getFavorites().size());
    }

    @Test
    void testCompileGames() {
        setUp();

        Set<VideoGame> set = this.consoleOrganizer.compileGames();
        assertEquals(3, set.size());

        VideoGame game = new VideoGame("newTestGame", Arrays.asList("1", "2", "3"));
        this.consoleOrganizer.addVideoGame(game);
        set = this.consoleOrganizer.compileGames();
        assertEquals(4, set.size());
    }

    @Test
    void testToString() {
        setUp();

        //testing simple version
        String result = """
                ConsoleOrganizer:
                Map Keys: [1, 2]
                Console Name: 1
                Games:
                B
                C
                F
                Console Name: 2
                Games:
                C
                """;
        assertEquals(result, consoleOrganizer.toString());

        //testing more complicated version
        List<String> c4 = new ArrayList<String>();
        c4.add("1");
        c4.add("3");
        VideoGame newVideoGame1 = new VideoGame("A", c4);
        consoleOrganizer.addVideoGame(newVideoGame1);
        result = "ConsoleOrganizer:\n" +
                "Map Keys: [1, 2, 3]\n" +
                "Console Name: 1\n" +
                "Games:\n" +
                "A\n" +
                "B\n" +
                "C\n" +
                "F\n" +
                "Console Name: 2\n" +
                "Games:\n" +
                "C\n" +
                "Console Name: 3\n" +
                "Games:\n" +
                "A\n";
        assertEquals(result, consoleOrganizer.toString());
    }
}