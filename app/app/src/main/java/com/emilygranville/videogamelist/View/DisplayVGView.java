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
import com.emilygranville.videogamelist.R;
import com.emilygranville.videogamelist.databinding.FragmentDisplayVgViewBinding;

import java.util.ArrayList;
import java.util.List;


public class DisplayVGView extends Fragment implements IDisplayVGView {

    FragmentDisplayVgViewBinding binding;
    Listener listener;
    List<VideoGame> videoGameList;

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
    public DisplayVGView(Listener listener, List<VideoGame> videoGameList) {
        this.listener = listener;
        this.videoGameList = videoGameList;
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

        RecyclerView.Adapter<VGViewHolder> itemAdapter = new VGDisplayAdapter(this.videoGameList);

        RecyclerView recyclerView = view.findViewById(R.id.displayRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(itemAdapter);
    }

}