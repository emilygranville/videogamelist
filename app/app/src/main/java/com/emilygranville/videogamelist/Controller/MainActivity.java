package com.emilygranville.videogamelist.Controller;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.emilygranville.videogamelist.View.AboutVGView;
import com.emilygranville.videogamelist.View.EditVGView;
import com.emilygranville.videogamelist.Model.ConsoleOrganizer;
import com.emilygranville.videogamelist.Model.VideoGame;
import com.emilygranville.videogamelist.View.DisplayVGView;
import com.emilygranville.videogamelist.View.IAboutVGView;
import com.emilygranville.videogamelist.View.IAddConsoleDialog;
import com.emilygranville.videogamelist.View.IDisplayVGView;
import com.emilygranville.videogamelist.View.IEditVVGView;
import com.emilygranville.videogamelist.View.IMainView;
import com.emilygranville.videogamelist.View.MainView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView.Listener,
        IDisplayVGView.Listener, IEditVVGView.Listener, IAddConsoleDialog.Listener,
        IAboutVGView.Listener {

    private static final String CONSOLE_ORGANIZER_KEY = "console organizer";
    private static final String IN_PROGRESS_KEY = "in progress";
    public static final String VIDEO_GAME_KEY = "video game";
    public static final String CONSOLE_LIST_KEY = "console list";
    public static final String GAMES_FOR_CONSOLE = "games for console";
    public static final String CONSOLE_NAME_KEY = "console name";
    public static final String SCROLL_LEFT_KEY = "scroll left";
    public static final String IS_EDITED_KEY = "is edited";

    public static final String VGL = "vgl";

    public static final String FAVORITES_KEY = "Favorites";

    private IMainView mainView;
    private ConsoleOrganizer consoleOrganizer;
    private Fragment currentFragment;


    /*
     * ANDROID METHODS
     */

    /**
     * Sets up the app
     * @param savedInstanceState saved information when app reloads
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportFragmentManager().
                setFragmentFactory(new VGLFragmentFactory(this));

        super.onCreate(savedInstanceState);

        this.mainView = new MainView(this,this);
        setContentView(this.mainView.getRootView());

        if (savedInstanceState != null) {
            this.consoleOrganizer = (ConsoleOrganizer) savedInstanceState.getSerializable(CONSOLE_ORGANIZER_KEY);
        } else {

            loadLocally();

            if(!this.consoleOrganizer.getConsoleList().isEmpty()) {
                showDisplayFrag(this.consoleOrganizer.getConsoleList().get(0));
            } else {
                showDisplayFrag(null);
            }
        }
    }

    /**
     *  Saves progress when resource constraints are destroyed
     * @param outState what saves the information
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(IN_PROGRESS_KEY, true);
        outState.putSerializable(CONSOLE_ORGANIZER_KEY, consoleOrganizer);
    }

    /**
     * Restores the MainActivity
     * @param savedInstanceState the saved info to restore
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.consoleOrganizer = (ConsoleOrganizer) savedInstanceState.getSerializable(CONSOLE_ORGANIZER_KEY);
    }

    /*
     * NORMAL METHODS
     */

    /**
     * Loads the data from the device (locally)
     */
    private void loadLocally() {
        IDataPreservation loadData = new LocalDataPreservation();
        this.consoleOrganizer = loadData.loadConsoleOrganizer(this);
    }

    /**
     * Saves data locally to device
     */
    private void saveLocally() {
        IDataPreservation saveData = new LocalDataPreservation();
        saveData.saveConsoleOrganizer(this, this.consoleOrganizer);
    }

    /**
     * Shows the About page
     */
    private void showAboutFrag() {
        this.currentFragment = new AboutVGView(this);
        this.mainView.displayFragment(currentFragment, true, AboutVGView.FRAG_NAME);
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
        List<VideoGame> gamesForConsole;

        if (console == null) {
            console = MainActivity.FAVORITES_KEY;
        }

        if (!console.equals(MainActivity.FAVORITES_KEY)) {
            gamesForConsole = this.consoleOrganizer.getGamesForConsole(console);
        } else {
            gamesForConsole = this.consoleOrganizer.getFavorites();
        }

        List<String> consoleList = this.consoleOrganizer.getConsoleList();
        Bundle fragArgs = new Bundle();
        fragArgs.putSerializable(GAMES_FOR_CONSOLE, (Serializable) gamesForConsole);
        fragArgs.putSerializable(CONSOLE_LIST_KEY, (Serializable) consoleList);
        fragArgs.putString(CONSOLE_NAME_KEY, console);
        fragArgs.putInt(SCROLL_LEFT_KEY, scrollLeft);
        this.currentFragment = new DisplayVGView(this);
        this.currentFragment.setArguments(fragArgs);
        this.mainView.displayFragment(currentFragment, false, DisplayVGView.FRAG_NAME);
    }

    /**
     * Shows the Edit page
     */
    private void showEditFrag(){
        Bundle fragArgs = new Bundle();
        fragArgs.putSerializable(CONSOLE_LIST_KEY, (Serializable) this.consoleOrganizer.getConsoleList());
        fragArgs.putBoolean(IS_EDITED_KEY, false);
        this.currentFragment = new EditVGView(this);
        currentFragment.setArguments(fragArgs);
        this.mainView.displayFragment(currentFragment, true, EditVGView.FRAG_NAME);
    }

    /**
     * Shows the Edit page
     * @param videoGame VideoGame to edit
     */
    private void showEditFrag(VideoGame videoGame){
        Bundle fragArgs = new Bundle();
        fragArgs.putSerializable(VIDEO_GAME_KEY, videoGame);
        fragArgs.putSerializable(CONSOLE_LIST_KEY, (Serializable) this.consoleOrganizer.getConsoleList());
        fragArgs.putBoolean(IS_EDITED_KEY, true);
        this.currentFragment = new EditVGView(this);
        currentFragment.setArguments(fragArgs);
        this.mainView.displayFragment(currentFragment, false, EditVGView.FRAG_NAME);
    }

    /*
     * LISTENER METHODS
     */

    /**
     * Restores the fragment in MainActivity
     * @param curFragment fragment to restore
     */
    @Override
    public void restoreAboutFragment(Fragment curFragment) {
        this.currentFragment = curFragment;
    }

    /**
     * Restores the fragment in MainActivity
     * @param curFragment fragment to restore
     */
    @Override
    public void restoreDisplayFragment(Fragment curFragment) {
        this.currentFragment = curFragment;
    }

    /**
     * Restores the fragment in MainActivity
     * @param curFragment fragment to restore
     */
    @Override
    public void restoreEditFragment(Fragment curFragment) {
        this.currentFragment = curFragment;
    }

    /**
     * Alerts listener to return to display fragment
     */
    @Override
    public void returnToDisplayFromAbout() {
        showDisplayFrag(this.consoleOrganizer.getConsoleList().get(0));
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
     * Alerts listener to toggling favorite
     *
     * @param videoGame game to change favorite
     */
    @Override
    public void favorite(VideoGame videoGame) {
        videoGame.switchFavorite();
        this.consoleOrganizer.editGame(videoGame);
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
     * Alerts listener to adding a new game
     */
    @Override
    public void addNewGame() {
        showEditFrag();
    }

    /**
     * Alerts listener to show the about page
     */
    public void displayAboutPage() {
        showAboutFrag();
    }

    /**
     * Alerts listener to saving on device
     */
    @Override
    public void onDeviceSave() {
        saveLocally();
    }

    /**
     * Alerts listener to loading from device
     */
    @Override
    public void onDeviceLoad() {
        loadLocally();
        if(!this.consoleOrganizer.getConsoleList().isEmpty()) {
            showDisplayFrag(this.consoleOrganizer.getConsoleList().get(0));
        } else {
            showDisplayFrag(null);
        }
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
     * Alerts listener to submitting the video game
     *
     * @param consoleName name of the new console
     */
    @Override
    public void submitNewConsole(String consoleName) {
        String upperConsoleName = consoleName.toUpperCase();
        ((IEditVVGView) this.currentFragment).submitNewConsole(upperConsoleName);
    }
}