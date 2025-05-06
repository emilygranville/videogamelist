package com.emilygranville.videogamelist.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emilygranville.videogamelist.Model.VideoGame;
import com.emilygranville.videogamelist.databinding.FragmentDisplayVgViewBinding;
import com.google.android.material.chip.Chip;

import java.util.Collections;
import java.util.List;

public class DisplayVGView extends Fragment implements IDisplayVGView {

    private FragmentDisplayVgViewBinding binding;
    private Listener listener;
    private List<VideoGame> videoGameList;
    private List<String> consoleList;
    private String curConsole;

    private RecyclerView.Adapter<VGViewHolder> vgItemAdapter;

    /**
     * Required empty constructor
     */
    public DisplayVGView() {
        // Required empty public constructor
    }

    /**
     * Constructor for display view
     * @param listener listens for edit and delete buttons
     */
    public DisplayVGView(Listener listener, List<VideoGame> videoGameList,
                         List<String> consoleList, String curConsole) {
        this.listener = listener;
        this.videoGameList = videoGameList;
        Collections.sort(consoleList);
        this.consoleList = consoleList;
        this.curConsole = curConsole;
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
    }

    /**
     * From the list of consoles, displays the consoles
     */
    private void displayConsoleList() {
        Log.i("vgl", "curConsole: "+curConsole);
        for (int i = 0; i < this.consoleList.size(); i++) {
            String consoleName = this.consoleList.get(i);
            Chip consoleNameChip = new Chip(this.getContext());
            consoleNameChip.setId(i);
            consoleNameChip.setText(consoleName);
            consoleNameChip.setCheckable(true);
            Log.i("vgl", "consoleName: " + consoleName);
            if (this.curConsole.equals(consoleName)) {
                Log.i("vgl", "is curConsole");
                consoleNameChip.setChecked(true);
            }
            consoleNameChip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DisplayVGView.this.listener.switchConsole(consoleNameChip.getText().toString());
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