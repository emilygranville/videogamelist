package com.emilygranville.videogamelist.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emilygranville.videogamelist.Model.VideoGame;
import com.emilygranville.videogamelist.databinding.FragmentDisplayVgViewBinding;
import com.google.android.material.chip.Chip;

import java.util.Collections;
import java.util.List;

public class DisplayVGView extends Fragment implements IDisplayVGView {

    public static final String FRAG_NAME = "display";

    private FragmentDisplayVgViewBinding binding;
    private Listener listener;
    private List<VideoGame> videoGameList;
    private List<String> consoleList;
    private String curConsole;
    private int scrollLeft;

    private RecyclerView.Adapter<VGViewHolder> vgItemAdapter;

    /**
     * Required empty constructor
     */
    public DisplayVGView() {
        // Required empty public constructor
    }

    public DisplayVGView(Listener listener, List<VideoGame> videoGameList,
                         List<String> consoleList, String curConsole) {
        this.listener = listener;
        this.videoGameList = videoGameList;
        Collections.sort(consoleList);
        this.consoleList = consoleList;
        this.curConsole = curConsole;
        this.scrollLeft = 0;
    }

    public DisplayVGView(Listener listener, List<VideoGame> videoGameList,
                         List<String> consoleList, String curConsole, int scrollLeft) {
        this.listener = listener;
        this.videoGameList = videoGameList;
        Collections.sort(consoleList);
        this.consoleList = consoleList;
        this.curConsole = curConsole;
        this.scrollLeft = scrollLeft;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.binding = FragmentDisplayVgViewBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void
    onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.vgItemAdapter = new VGDisplayAdapter(this.videoGameList, this.curConsole, this.listener);
        RecyclerView recyclerView = binding.vgListRv;
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(vgItemAdapter);
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

    /**
     * From the list of consoles, displays the consoles
     */
    private void displayConsoleList() {
        for (int i = 0; i < this.consoleList.size(); i++) {
            String consoleName = this.consoleList.get(i);
            Chip consoleNameChip = new Chip(this.getContext());
            consoleNameChip.setId(i);
            consoleNameChip.setText(consoleName);
            consoleNameChip.setCheckable(true);
            if (this.curConsole.equals(consoleName)) {
                consoleNameChip.setChecked(true);
            }
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

    /**
     * Notifies the adapter to items being removed from list
     * @param index position of removed item
     */
    public void updateDeletedItem(int index) {
        this.vgItemAdapter.notifyItemRemoved(index);
    }
}