package com.emilygranville.videogamelist.Controller;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.emilygranville.videogamelist.View.EditVGView;
import com.emilygranville.videogamelist.Model.ConsoleOrganizer;
import com.emilygranville.videogamelist.Model.VideoGame;
import com.emilygranville.videogamelist.View.DisplayVGView;
import com.emilygranville.videogamelist.View.IDisplayVGView;
import com.emilygranville.videogamelist.View.IEditVVGView;
import com.emilygranville.videogamelist.View.IMainView;
import com.emilygranville.videogamelist.View.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView.Listener,
        IDisplayVGView.Listener, IEditVVGView.Listener {

    private IMainView mainView;
    private ConsoleOrganizer consoleOrganizer;
    private Fragment currentFragment;

    /**
     * Sets up the app
     * @param savedInstanceState saved information when app reloads
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        this.mainView = new MainView(this,this);
        setContentView(this.mainView.getRootView());

        this.consoleOrganizer = makeTestConsoleOrganizer();

        showDisplayFrag(this.consoleOrganizer.getConsoleList().get(0));
        //showEditFrag();
    }

    /**
     * Creates a test console organizer so I can see
     * what stuff looks like with dummy data
     * @return sample consoleOrganizer
     */
    private ConsoleOrganizer makeTestConsoleOrganizer() {
        ConsoleOrganizer testConsoleOrganizer = new ConsoleOrganizer();
        List<VideoGame> videoGameList = new ArrayList<>();

        VideoGame vg = new VideoGame("Game1", "SWITCH");
        vg.addConsole("XBOX");
//        vg.addConsole("z");
//        vg.addConsole("z1");
//        vg.addConsole("z2");
//        vg.addConsole("z3");
//        vg.addConsole("z4");
//        vg.addConsole("z5");
        videoGameList.add(vg);
        VideoGame vg1 = new VideoGame("Game2", "SWITCH");
        vg1.addConsole("XBOX");
        videoGameList.add(vg1);
        videoGameList.add(new VideoGame("Game3", "SWITCH"));
        VideoGame vg2 = new VideoGame("Game4", "SWITCH");
        vg2.addConsole("XBOX");
        videoGameList.add(vg2);
        videoGameList.add(new VideoGame("Game5", "SWITCH"));
        videoGameList.add(new VideoGame("Game6", "SWITCH"));
        videoGameList.add(new VideoGame("Game7", "SWITCH"));
        videoGameList.add(new VideoGame("Game8", "SWITCH"));

        testConsoleOrganizer.setupConsoleOrganizer(videoGameList);

        return testConsoleOrganizer;
    }

    /**
     * Alerts listener to the video game needing deleting
     * @param videoGame that needs to be deleted
     * @param curConsole current displayed console list
     */
    @Override
    public void deleteGame(VideoGame videoGame, String curConsole) {
        int index = this.consoleOrganizer.getGameIndex(videoGame, curConsole);
        this.consoleOrganizer.deleteGame(videoGame);
        Log.i("vgl", "delete");
        ((IDisplayVGView) this.currentFragment).updateDeletedItem(index);
    }

    /**
     * Alerts listener to the video game needing editing
     * @param videoGame that needs to be updated
     */
    @Override
    public void editGame(VideoGame videoGame) {
        showEditFrag(videoGame);
    }

    /**
     * Alerts listener to wanting to switch console viewed
     * @param console new console to view
     * @param scrollLeft position in scroll of the console
     */
    @Override
    public void switchConsole(String console, int scrollLeft) {
        showDisplayFrag(console, scrollLeft);
    }

    /**
     * Alerts listener to adding a new game
     */
    @Override
    public void addNewGame() {
        showEditFrag();
    }

    /**
     * Alerts listener to submitting the video game
     * @param videoGame the video game to edit/create
     */
    @Override
    public void submitGame(VideoGame videoGame) {
        this.consoleOrganizer.editGame(videoGame);
        showDisplayFrag(videoGame.getConsoles().get(0));
    }

    /**
     * Shows the Display page
     * @param console name of the console to display
     */
    private void showDisplayFrag(String console) {
        showDisplayFrag(console, 0);
    }

    /**
     * Shows the Display page
     * @param console name of the console to display
     */
    private void showDisplayFrag(String console, int scrollLeft) {
        this.currentFragment = new DisplayVGView(this, this.consoleOrganizer.getGamesForConsole(console),
                this.consoleOrganizer.getConsoleList(), console, scrollLeft);
        this.mainView.displayFragment(currentFragment, false, DisplayVGView.FRAG_NAME);
    }

    /**
     * Shows the Edit page
     */
    private void showEditFrag(){
        this.currentFragment = new EditVGView(this, this.consoleOrganizer.getConsoleList());
        this.mainView.displayFragment(currentFragment, false, EditVGView.FRAG_NAME);
    }

    /**
     * Shows the Edit page
     * @param videoGame VideoGame to edit
     */
    private void showEditFrag(VideoGame videoGame){
        this.currentFragment = new EditVGView(this, this.consoleOrganizer.getConsoleList(), videoGame);
        this.mainView.displayFragment(currentFragment, false, EditVGView.FRAG_NAME);
    }
}