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

    public static final String DISPLAY_FRAG_NAME = "display";
    public static final String EDIT_FRAG_NAME = "edit";
    private IMainView mainView;
    private ConsoleOrganizer consoleOrganizer;
    private Fragment currentFragment;

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

        // TODO: fix this to be related to an actual key in the ConsoleOrganizer
        //showDisplayFrag("switch");
        showEditFrag();
    }

    private ConsoleOrganizer makeTestConsoleOrganizer() {
        ConsoleOrganizer testConsoleOrganizer = new ConsoleOrganizer();
        List<VideoGame> videoGameList = new ArrayList<>();

        VideoGame vg = new VideoGame("Game1", "SWITCH");
        vg.addConsole("XBOX");
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
     * Deletes the video game from the ConsoleOrganizer
     * @param videoGame that needs to be deleted
     */
    @Override
    public void deleteGame(VideoGame videoGame, String curConsole) {
        int index = this.consoleOrganizer.getGameIndex(videoGame, curConsole);
        this.consoleOrganizer.deleteGame(videoGame);
        Log.i("vgl", "delete");
        ((IDisplayVGView) this.currentFragment).updateDeletedItem(index);
    }

    /**
     * Passes the video game to the edit menu
     * @param videoGame that needs to be updated
     */
    @Override
    public void editGame(VideoGame videoGame) {
        showEditFrag(videoGame);
    }

    @Override
    public void switchConsole(String console) {
        //Log.i("vgl", "switch console to " + console);
        this.currentFragment = new DisplayVGView(this, this.consoleOrganizer.getGamesForConsole(console),
                this.consoleOrganizer.getConsoleList(), console);
        this.mainView.displayFragment(currentFragment, true, DISPLAY_FRAG_NAME);
    }

    /**
     * Alerts listener to submitting the video game
     */
    @Override
    public void submitGame(VideoGame videoGame) {
        this.consoleOrganizer.editGame(videoGame);
        Log.i("vgl", "submitted");
        showDisplayFrag(videoGame.getConsoles().get(0));
    }

    private void showDisplayFrag(String console) {
        this.currentFragment = new DisplayVGView(this, this.consoleOrganizer.getGamesForConsole(console),
                this.consoleOrganizer.getConsoleList(), console);
        this.mainView.displayFragment(currentFragment, false, DISPLAY_FRAG_NAME);
    }

    private void showEditFrag(){
        this.currentFragment = new EditVGView(this, this.consoleOrganizer.getConsoleList());
        this.mainView.displayFragment(currentFragment, false, EDIT_FRAG_NAME);
    }

    private void showEditFrag(VideoGame videoGame){
        this.currentFragment = new EditVGView(this, this.consoleOrganizer.getConsoleList(), videoGame);
        this.mainView.displayFragment(currentFragment, false, EDIT_FRAG_NAME);
    }
}