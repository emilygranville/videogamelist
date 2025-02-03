package com.emilygranville.videogamelist.Controller;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView.Listener, IDisplayVGView.Listener {

    private IMainView mainView;

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

        List<VideoGame> videoGameList = new ArrayList<>();
        videoGameList.add(new VideoGame("Game1", "switch"));
        videoGameList.add(new VideoGame("Game2", "switch"));
        videoGameList.add(new VideoGame("Game3", "switch"));
        videoGameList.add(new VideoGame("Game4", "switch"));
        videoGameList.add(new VideoGame("Game5", "switch"));
        videoGameList.add(new VideoGame("Game6", "switch"));
        videoGameList.add(new VideoGame("Game7", "switch"));
        videoGameList.add(new VideoGame("Game8", "switch"));

        // TODO: fix this to be related to the ConsoleOrganizer
        Fragment vgView = new DisplayVGView(this, videoGameList);
        this.mainView.displayFragment(vgView, false, "display");

    }

}