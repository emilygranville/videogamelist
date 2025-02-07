package com.emilygranville.videogamelist.Controller;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.emilygranville.videogamelist.Model.ConsoleOrganizer;
import com.emilygranville.videogamelist.Model.VideoGame;
import com.emilygranville.videogamelist.R;
import com.emilygranville.videogamelist.View.DisplayVGView;
import com.emilygranville.videogamelist.View.IDisplayVGView;
import com.emilygranville.videogamelist.View.IMainView;
import com.emilygranville.videogamelist.View.MainView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView.Listener, IDisplayVGView.Listener {

    private IMainView mainView;
    private ConsoleOrganizer consoleOrganizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.mainView = new MainView(this,this);
        setContentView(this.mainView.getRootView());

        this.consoleOrganizer = makeTestConsoleOrganizer();

        // TODO: fix this to be related to an actual key in the ConsoleOrganizer
        Fragment vgView = new DisplayVGView(this, this.consoleOrganizer.getGamesForConsole("switch"));
        this.mainView.displayFragment(vgView, false, "display");

    }

    private ConsoleOrganizer makeTestConsoleOrganizer() {
        ConsoleOrganizer testConsoleOrganizer = new ConsoleOrganizer();
        List<VideoGame> videoGameList = new ArrayList<>();

        VideoGame vg = new VideoGame("Game1", "switch");
        vg.addConsole("xbox");
        videoGameList.add(vg);
        VideoGame vg1 = new VideoGame("Game2", "switch");
        vg1.addConsole("xbox");
        videoGameList.add(vg1);
        VideoGame vg2 = new VideoGame("Game3", "switch");
        vg2.addConsole("xbox");
        videoGameList.add(vg2);
        videoGameList.add(new VideoGame("Game4", "switch"));
        videoGameList.add(new VideoGame("Game5", "switch"));
        videoGameList.add(new VideoGame("Game6", "switch"));
        videoGameList.add(new VideoGame("Game7", "switch"));
        videoGameList.add(new VideoGame("Game8", "switch"));

        testConsoleOrganizer.setupConsoleOrganizer(videoGameList);

        return testConsoleOrganizer;
    }

    /**
     * Deletes the video game from the ConsoleOrganizer
     * @param videoGame that needs to be deleted
     */
    @Override
    public void deleteGame(VideoGame videoGame) {
        List<String> consoles = videoGame.getConsoles();
        HashMap<String, List<VideoGame>> consoleMap = this.consoleOrganizer.getConsoleMap();
        for (String console : consoles) {
            List<VideoGame> gameList = consoleMap.get(console);
            gameList.remove(videoGame);
        }
        Log.i("vgl", "delete");
    }

    /**
     * Passes the video game to the edit menu
     * @param videoGame that needs to be updated
     */
    @Override
    public void editGame(VideoGame videoGame) {
        Log.i("vgl", "edit");
    }
}