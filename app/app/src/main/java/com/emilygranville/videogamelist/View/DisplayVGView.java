package com.emilygranville.videogamelist.View;

import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emilygranville.videogamelist.Controller.MainActivity;
import com.emilygranville.videogamelist.Model.VideoGame;
import com.emilygranville.videogamelist.R;
import com.emilygranville.videogamelist.databinding.FragmentDisplayVgViewBinding;
import com.google.android.material.chip.Chip;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class DisplayVGView extends Fragment implements IDisplayVGView {

    public static final String FRAG_NAME = "display";

    private FragmentDisplayVgViewBinding binding;
    private Listener listener;
    private List<VideoGame> videoGameList;
    private List<String> consoleList;
    // either a valid console or MainActivity.FAVORITES_KEY
    private String curConsole;
    private int scrollLeft;
    private boolean hasInitInfo;

    private RecyclerView.Adapter<VGViewHolder> vgItemAdapter;

    /**
     * Constructors for DisplayVGView
     */
    public DisplayVGView(Listener listener) {
        this.listener = listener;
        this.hasInitInfo = false;
    }

    public DisplayVGView(Listener listener, List<VideoGame> videoGameList,
                         List<String> consoleList, String curConsole, int scrollLeft) {
        this.listener = listener;
        this.videoGameList = videoGameList;
        Collections.sort(consoleList);
        this.consoleList = consoleList;
        this.curConsole = curConsole;
        this.scrollLeft = scrollLeft;
        this.hasInitInfo = true;
    }

    /**
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return the root of the binding
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.binding = FragmentDisplayVgViewBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    /**
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void
    onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null && !hasInitInfo) {
            this.videoGameList = (List<VideoGame>) savedInstanceState.getSerializable(MainActivity.GAMES_FOR_CONSOLE);
            this.consoleList = (List<String>) savedInstanceState.getSerializable(MainActivity.CONSOLE_LIST_KEY);
            this.curConsole = savedInstanceState.getString(MainActivity.CONSOLE_NAME_KEY);
            this.scrollLeft = savedInstanceState.getInt(MainActivity.SCROLL_LEFT_KEY);

        }

        displayFragment();
    }

    private void displayFragment() {
        if (this.videoGameList != null && !this.videoGameList.isEmpty()) {
            displayVideoGameList();
        } else {
            this.binding.noGamesTextview.setVisibility(VISIBLE);
        }

        if (consoleList != null) {
            displayConsoleList();
        }

        if (scrollLeft > 0) {
            this.binding.consoleListScroll.post(new Runnable() {
                @Override
                public void run() {
                    DisplayVGView.this.binding.consoleListScroll.scrollTo(
                            DisplayVGView.this.scrollLeft, 0);
                }
            });
        }

        this.binding.addNewGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayVGView.this.listener.addNewGame();
            }
        });
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(MainActivity.GAMES_FOR_CONSOLE, (Serializable) this.videoGameList);
        outState.putSerializable(MainActivity.CONSOLE_LIST_KEY, (Serializable) this.consoleList);
        outState.putString(MainActivity.CONSOLE_NAME_KEY, this.curConsole);
        outState.putInt(MainActivity.SCROLL_LEFT_KEY, this.scrollLeft);

        // https://stackoverflow.com/a/43547156
//        Parcelable listState = binding.vgListRv.getLayoutManager().onSaveInstanceState();
//        outState.putParcelable("list state", listState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            this.videoGameList = (List<VideoGame>) savedInstanceState.getSerializable(MainActivity.GAMES_FOR_CONSOLE);
            this.consoleList = (List<String>) savedInstanceState.getSerializable(MainActivity.CONSOLE_LIST_KEY);
            this.curConsole = savedInstanceState.getString(MainActivity.CONSOLE_NAME_KEY);
            this.scrollLeft = savedInstanceState.getInt(MainActivity.SCROLL_LEFT_KEY);

            displayFragment();
            this.listener.restoreDisplayFragment(this);
        }
    }

    /**
     * From the list of video games, displays the games
     */
    private void displayVideoGameList() {
        this.vgItemAdapter = new VGDisplayAdapter(this.videoGameList, this.curConsole, this.listener);
        RecyclerView recyclerView = binding.vgListRv;
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(vgItemAdapter);
    }

    /**
     * From the list of consoles, displays the consoles
     */
    private void displayConsoleList() {
        Chip favChip = createConsoleChip(getResources().getString(R.string.favorites_chip_text));
        favChip.setId(-1);
        favChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = favChip.getLeft();
                DisplayVGView.this.listener.switchConsole(MainActivity.FAVORITES_KEY, x);
            }
        });
        this.binding.consoleListChipgroup.addView(favChip);

        for (int i = 0; i < this.consoleList.size(); i++) {
            String consoleName = this.consoleList.get(i);
            Chip consoleNameChip = createConsoleChip(consoleName);
            consoleNameChip.setId(i);
            consoleNameChip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int x = consoleNameChip.getLeft();
                    DisplayVGView.this.listener.switchConsole(consoleNameChip.getText().toString(), x);
                }
            });
            this.binding.consoleListChipgroup.addView(consoleNameChip);
        }
    }

    private Chip createConsoleChip(String consoleName) {
        Chip consoleNameChip = new Chip(this.getContext());
        consoleNameChip.setText(consoleName);
        consoleNameChip.setCheckable(true);
        if (this.curConsole.equals(consoleName)) {
            consoleNameChip.setChecked(true);
        }
        return consoleNameChip;
    }

    /**
     * Updates the view for the deleted item
     * @param index index the item had been
     */
    public void updateDeletedItem(int index) {
        this.vgItemAdapter.notifyItemRemoved(index);
    }
}