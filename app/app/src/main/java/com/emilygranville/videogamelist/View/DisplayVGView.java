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

import java.util.List;
import java.util.Set;

public class DisplayVGView extends Fragment implements IDisplayVGView {

    private FragmentDisplayVgViewBinding binding;
    private Listener listener;
    private List<VideoGame> videoGameList;
    private Set<String> consoleList;
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
                         Set<String> consoleList, String curConsole) {
        this.listener = listener;
        this.videoGameList = videoGameList;
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
    }


    public void updateDeletedItem(int index) {
        this.vgItemAdapter.notifyItemRemoved(index);
    }
}